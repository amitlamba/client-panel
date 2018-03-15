package com.und.model.jpa


import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "campaign")
open class Campaign {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "campaign_id_seq")
    @SequenceGenerator(name = "campaign_id_seq", sequenceName = "campaign_id_seq", allocationSize = 1)
    var id: Long? = null

    @Column(name = "client_id")
    @NotNull
    var clientID: Long? = null

    @Column(name = "appuser_id")
    @NotNull
    var appuserID: Long? = null

    @Column(name = "name")
    @NotNull
    var name: String = ""

    @Column(name = "campaign_type") //Email / SMS / Notifications etc
    @NotNull
    @Enumerated(EnumType.STRING)
    var campaignType: CampaignType? = null

    @Column(name = "segmentation_id") //TODO Foreign Key
    @NotNull
    var segmentationID: Long? = null


    @Column(name = "schedule")
    @NotNull
    var schedule: String? = null


    @OneToOne(mappedBy = "campaign",
            cascade = arrayOf(CascadeType.ALL),
            orphanRemoval = true)
    var emailCampaign: EmailCampaign? = null
        set(value) {
            field = value
            field?.campaign = this
        }

    @OneToOne(mappedBy = "campaign",
            cascade = arrayOf(CascadeType.ALL),
            orphanRemoval = true)
    var smsCampaign: SmsCampaign? = null
        set(value) {
            field = value
            field?.campaign = this
        }

    //TODO add sms, and push campaign later
}

enum class CampaignType {
    EMAIL,
    SMS,
    MOBILE_PUSH_NOTIFICATION
}


class Schedule {
    var oneTime: ScheduleOneTime? = null
    var multipleDates: ScheduleMultipleDates? = null
    var recurring: ScheduleRecurring? = null
}

class ScheduleOneTime {
    var nowOrLater: Now? = Now.Later
    var campaignDateTime: CampaignTime? = null
}

class ScheduleMultipleDates {
    var campaignDateTimeList: List<CampaignTime> = mutableListOf()
}

class ScheduleRecurring {
    lateinit var cronExpression: String
    var scheduleStartDate: String? = null
    var scheduleEnd: ScheduleEnd? = null
}


class ScheduleEnd {
    var endType: ScheduleEndType? = null
    var endsOn: Any? = null
    var occurrences: Int = 0
}

enum class ScheduleEndType {
    NeverEnd,
    EndsOnDate,
    Occurrences
}


class CampaignTime {
    lateinit var date: LocalDate
    var hours: Int? = null
    var minutes: Int? = null
    lateinit var ampm: AmPm
}

enum class Now {
    Now,
    Later
}

enum class AmPm {
    AM,
    PM
}
