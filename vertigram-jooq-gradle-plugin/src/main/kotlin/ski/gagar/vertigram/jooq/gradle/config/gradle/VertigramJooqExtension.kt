package ski.gagar.vertigram.jooq.gradle.config.gradle

import org.gradle.api.Action
import org.gradle.api.GradleException
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Optional
import ski.gagar.vertigram.jooq.app.config.GeneratorConfig
import javax.inject.Inject


abstract class VertigramJooqExtension @Inject constructor(val objectFactory: ObjectFactory) {
    @get:Optional
    abstract val testContainer: Property<TestContainer>
    @get:Optional
    abstract val liveDb: Property<LiveDb>
    abstract val flyway: Property<Flyway>
    abstract val jooq: Property<Jooq>

    init {
        flyway.convention(objectFactory.newInstance(Flyway::class.java))
        jooq.convention(objectFactory.newInstance(Jooq::class.java))
    }

    fun testContainer(action: Action<TestContainer>) {
        val tc = objectFactory.newInstance(TestContainer::class.java)
        action.execute(tc)
        testContainer.set(tc)
    }

    fun liveDb(action: Action<LiveDb>) {
        val live = objectFactory.newInstance(LiveDb::class.java)
        action.execute(live)
        liveDb.set(live)
    }

    fun flyway(action: Action<Flyway>) {
        val fw = objectFactory.newInstance(Flyway::class.java)
        action.execute(fw)
        flyway.set(fw)
    }

    fun jooq(action: Action<Jooq>) {
        val jooq = objectFactory.newInstance(Jooq::class.java)
        action.execute(jooq)
        this.jooq.set(jooq)
    }

    fun pojo(): GeneratorConfig {
        if (testContainer.isPresent && liveDb.isPresent) {
            throw GradleException("testContainer and liveDb are mutually exclusive")
        }

        if (!testContainer.isPresent && !liveDb.isPresent) {
            throw GradleException("Either testContainer or liveDb should be present in config")
        }

        return GeneratorConfig(
            db = if (testContainer.isPresent) testContainer.get().pojo() else liveDb.get().pojo(),
            flyway = flyway.get().pojo(),
            jooq = jooq.get().pojo()
        )
    }

}