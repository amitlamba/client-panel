package com.und

import org.junit.Ignore
import org.junit.Test
import org.quartz.CronScheduleBuilder
import org.quartz.Trigger
import org.quartz.TriggerBuilder
import org.quartz.TriggerUtils
import org.quartz.core.QuartzScheduler
import java.util.*

@Ignore
class QuartzTest {

    @Test
    fun testQuartz() {
        //val cronSchedule = CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
        //var trigger = TriggerBuilder.newTrigger().withSchedule(cronSchedule).build()
//        TriggerUtils.computeFireTimes(trigger, null, 5)
    }
}