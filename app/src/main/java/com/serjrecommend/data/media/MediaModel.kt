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
data class MediaModel(var title: String, var type: String, var tags: ArrayList<String>,
                      var coverId: Int, var description: String,
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
    fun getMediaData(): ArrayList<MediaModel> {
        // ArrayList of media recommendations
        val data = ArrayList<MediaModel>()

        // Data collection
        data.add(
            MediaModel(
                "American Horror Story: Cult",
                "Series",
                arrayListOf("Horror", "Drama", "Mystery thriller"),
                R.drawable.media_cover_ahs_cult,
                "«Культ» интригует жутковатым сюжетом и актуальными темами, несмотря на то, что сезону мешают широкие политические обобщения и случайные дыры в повествовании",
                arrayListOf(
                    MediaParagraphModel(
                        "Страх и власть",
                        R.drawable.media_paragraph_ahs_cult_1,
                        "<p>Всё начинается в день объявления результатов выборов нового президента США. Выиграл Дональд Трамп - республиканец, который известен своими сомнительными идеалами (на мой левый взгляд) и сексистскими и мизогинными взглядами. У Эллис, одной из главных героинь, начинается истерика только от одной мысли, что теперь президент её страны - ярый приверженец патрирхальной системы.</p>" +
                                "<p>Основная идея сезона - “<b>Страх управляет людьми</b>”, и именно этим пользуется культ, который начинает орудовать в Мичигане после победы Трампа. Их цель - всемирное господство. И они всячески пытаются этого добиться, запугивая население Детройта. Культ устраивает серию зверских убийств и манипулирует людьми, для того, чтобы пробить к местной власти своего лидера - Кая Андерсона, который баллотируется в члены городского совета.</p>" +
                                "<p>Культ продвигает идею того, что только напуганные люди способны поддаться власти, ведь нет ничего важнее собственной безопасности, которую (ого как удобно!) предоставит власть, если ты ей поддашься. Но лишь немногие знают, что изначальный источник опастности - та же самая власть. Властям выгодней защищаться и возводить вокруг стены, а не идти на встречу и строить мосты.</p>" +
                                "<p>На протяжении всего сезона <b>противоставляются правые и левые</b>, республиканцы и демократы, Дональд Трамп и Хиллари Клинтон, Кай Андерсон и Эллис Мейфэр-Ричардс. Но кто же победит?…</p>"
                    ),
                    MediaParagraphModel(
                        "Мизогиния и мизандрия",
                        R.drawable.media_paragraph_ahs_cult_2,
                        "<p>Ещё одна тема, вокруг которой крутится весь сюжет, - сексизм. Как упоминает одна из героинь: “Кай Андерсон - просто мужчина, возмущенный тем, что его привелегии поставили под сомнения женщины”. Культ Кая - мужской. Да, в нём есть несколько женщин, но у них дефакто другие права: только мужчины смогут себя почувствовать полноценно в стенах дома Кая.</p>" +
                                "<p>Если же мужчинам внушаются идеи об их непобедимом эго, о том, что все вокруг недооценивают их из-за утраты привелений и о важности присутствия у власти “настоящего мужчины”, то женщинами окололюбовно манипулируют. Кай даёт то, что им “нужно”:  ощущение важности и экслюзивности. Для мужчин важна общая идея, для женщин - Кай.</p>" +
                                "<p>Но после появляется и вторая крайность - женский культ, который восхваляет мизандрию.</p>"
                    )
                )
            )
        )

        return data
    }
}