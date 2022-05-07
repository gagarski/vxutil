package ski.gagar.vxutil.vertigram.client

import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.type.TypeFactory
import ski.gagar.vxutil.vertigram.getFile
import ski.gagar.vxutil.vertigram.methods.TgCallable
import ski.gagar.vxutil.vertigram.types.Update
import ski.gagar.vxutil.vertigram.types.UpdateType
import ski.gagar.vxutil.vertigram.util.TelegramNoFilePathException
import ski.gagar.vxutil.vertigram.util.TypeHints
import ski.gagar.vxutil.vertigram.util.getOrAssert
import ski.gagar.vxutil.vertigram.util.json.TELEGRAM_JSON_MAPPER

abstract class Telegram {
    protected val typeFactory: TypeFactory = TELEGRAM_JSON_MAPPER.typeFactory
    abstract suspend fun getUpdates(offset: Long? = null,
                                    limit: Int? = null,
                                    allowedUpdates: List<UpdateType>? = null): List<Update>


    suspend fun <T> call(callable: TgCallable<T>): T =
        call(TypeHints.returnTypesByClass.getOrAssert(callable.javaClass), callable)

    abstract suspend fun <T> call(
        type: JavaType,
        callable: TgCallable<T>
    ): T

    abstract suspend fun downloadFile(path: String, outputPath: String)

    suspend fun downloadFileById(id: String, outputPath: String) {
        val path = getFile(id).filePath ?: throw TelegramNoFilePathException(id)
        downloadFile(path, outputPath)
    }
}
