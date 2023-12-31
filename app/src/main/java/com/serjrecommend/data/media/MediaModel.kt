package com.serjrecommend.data.media

import com.serjrecommend.R


/**
 * MEDIA MODEL
 *
 * Data model that contains all the information about media recommendation:
 * - title
 * - type
 * - tags
 * - coverId
 * - description
 * - paragraphs
 */
data class MediaModel(
    var title: String, var type: String, var category: String, var rating: Double,
    var production: String, var tags: ArrayList<String>, var coverId: Int, var description: String,
    var paragraphs: ArrayList<MediaParagraphModel>): java.io.Serializable


/**
 * MEDIA PARAGRAPH MODEL
 *
 * Data model for media paragraphs that contains:
 * - title
 * - coverId
 * - text
 */
data class MediaParagraphModel(var title: String, var coverId: Int, var text: String): java.io.Serializable


/**
 * MEDIA DATA
 *
 * It store the data about media recommendations
 */
object MediaData {

    // Creates an Arraylist of media data and returns it
    fun getMediaData(): ArrayList<Any> {
        // ArrayList of media recommendations
        val data = ArrayList<MediaModel>()

        // Data collection
        data.add(
            MediaModel(
                "Американская история ужасов: Культ",
                "Сериал",
                "Некоторые хорроры",
                7.3,
                "FX Channel",
                arrayListOf("Хоррор", "Драма", "Триллер"),
                R.drawable.media_cover_ahs_cult,
                "Единственный сезон «Американской истории ужасов», в котором нет мистики. Всё зло — это общественные проблемы и угнетённость населения. Всё остальное по канонам АИУ: кровища, пошлость и жестокость.",
                arrayListOf(
                    MediaParagraphModel(
                        "Страх и власть",
                        R.drawable.media_paragraph_ahs_cult_1,
                        "<p>Всё начинается в день объявления результатов выборов нового президента США. Выиграл Дональд Трамп — республиканец, который известен своими сомнительными идеалами (на мой левый взгляд) и сексистскими и мизогинистские взглядами. У Эллис, одной из главных героинь, начинается истерика только от одной мысли, что теперь президент её страны — ярый приверженец патриархальной системы.</p>" +
                                "<p>Основная идея сезона — <b>«Страх управляет людьми»</b>, и именно этим пользуется культ, который начинает орудовать в Мичигане после победы Трампа. Их цель — всемирное господство. И они всячески пытаются этого добиться, запугивая население Детройта. Культ устраивает серию зверских убийств и манипулирует людьми, для того, чтобы пробить к местной власти своего лидера — Кая Андерсона, который баллотируется в члены городского совета.</p>" +
                                "<p>Культ продвигает идею того, что только напуганные люди способны поддаться власти, ведь нет ничего важнее собственной безопасности, которую (ого как удобно!) предоставит власть, если ты ей поддашься. Но лишь немногие знают, что изначальный источник опасности — та же самая власть. Властям выгодней защищаться и возводить вокруг стены, а не идти на встречу и строить мосты.</p>" +
                                "<p>На протяжении всего сезона <b>противопоставляются правые и левые</b>, республиканцы и демократы, Дональд Трамп и Хиллари Клинтон, Кай Андерсон и Эллис Мейфэр-Ричардс. Но кто же победит?…</p>"
                    ),
                    MediaParagraphModel(
                        "Мизогиния и мизандрия",
                        R.drawable.media_paragraph_ahs_cult_2,
                        "<p>Ещё одна тема, вокруг которой крутится весь сюжет, — <b>сексизм</b>. Как упоминает одна из героинь: «Кай Андерсон — просто мужчина, возмущенный тем, что его привилегии поставили под сомнения женщины». Культ Кая — мужской. Да, в нём есть несколько женщин, но у них де-факто другие права: только мужчины смогут себя почувствовать полноценно в стенах дома Кая.</p>" +
                                "<p>Если же мужчинам внушаются идеи об их непобедимом эго, о том, что все вокруг недооценивают их из-за утраты привилегий и о важности присутствия у власти «настоящего мужчины», то женщинами около-любовно манипулируют. Кай даёт то, что им «нужно»:  ощущение важности и эксклюзивности. Для мужчин важна общая идея, для женщин — Кай.</p>" +
                                "<p>Но после появляется и вторая крайность — <b>женский культ</b>, главой которого оказывается Валери Соланас. Эта тётя которая известна своей трагичной судьбой и <b>покушением на Энди Уорхола</b>. Собственно, с этого покушения и начинается активная деятельность Валери: она создаёт <b>«SCUM Manifesto»</b> («Манифест общества полного уничтожения мужчин») и собирает вокруг себя женщин, жизни которых были разрушены мужчинами.</p>" +
                                "<p>Их собрания перерастают в нечто большее — серия убийств во имя отмщения. Фактически это ещё один культ, которым движут страх и угнетение, но в этот раз все действия происходят более обдуманно, ведь <b>«есть в мире кое-что, что опаснее униженного мужчины — это злая женщина»</b>.</p>" +
                                "<p>Скорее всего, всё вышеперечисленное звучит немного бредово, но мне понравилось, как создатели сериала подвязали эту актуальную тему в формат хоррора. Это всё выглядит немного вычурно и самоиронично, но от того и хорошо! Ещё и историю с Уорхолом подвязали!</p>"
                    ),
                    MediaParagraphModel(
                        "Страх сойти с ума",
                        R.drawable.media_paragraph_ahs_cult_3,
                        "<p>Больша́я часть сюжета крутится вокруг Эллис и её проблем с ментальным здоровьем: коулрофобия, трипофобия, постоянная тревожность и панические атаки.</p>" +
                                "<p>И вот тут у меня появились некоторые мысли: человеку с серьёзными психологическими проблемами <b>сложно в моменте понять, насколько всё происходящее вокруг реально</b>. Культ Кая именно этим и пользуется, пытаясь свести Эллис с ума, и у них это вполне успешно получается.</p>" +
                                "<p>Думаю, страх сойти с ума — один из моих главных страхов. Ведь даже обычная тревога способна тебя поглотить настолько, что ты не сможешь нормально функционировать месяцами. А если это будет не тревога, а что-то страшнее?…</p>"
                    )
                )
            )
        )

        return data as ArrayList<Any>
    }
}