package com.serjrecommend.data.music

import com.serjrecommend.R


/**
 * MUSIC MODEL
 *
 * Data model that contains all the information about music recommendation:
 * - title
 * - musician
 * - type
 * - tags
 * - coverId
 * - videoId
 * - description
 */
data class MusicModel(var title: String, var musician: String, var type: String,
                      var tags: ArrayList<String>, var coverId: Int, var videoId: String,
                      var description: String): java.io.Serializable


/**
 * MUSIC DATA
 *
 * It store the data about music recommendations
 */
object MusicData {

    // Creates an Arraylist of music data and returns it
    fun getMusicData(): ArrayList<MusicModel> {
        /// ArrayList of media recommendations
        val data = ArrayList<MusicModel>()

        // Data collection
        data.add(
            MusicModel("(It Goes Like) Nanana", "Peggy Gou", "Single",
                arrayListOf("Dance-pop", "House"),
                R.drawable.music_cover_peggy_gou_nanana,
                "video_peggy_gou_nanana.mp4",
                "Одной июньской ночью весь тикток заполонили видео с диджей сета Пэгги Гу в Марокко. Сначала мне этот трек не особо понравился, но после он стал популярен и зазвучал из каждого утюга. И он мне зашёл! \n\n" +
                        "Пегги Гу - известная диджейка, продюсерка и модельерка южнокорейского происхождения. И мне больше всего понравился её attitude: её стиль, звучание её работ, её карьера.\n\n" +
                        "Не очень хочется вдаваться в подробности, так что будут хайлайты:\n\n" +
                        "- В 14 лет поехала учиться в Лондон, после чего поступила в London College of Fashion\n\n" +
                        "- После выпуска работала редактором в Harper's Bazaar Korea, находясь при этом в Лондоне\n\n" +
                        "- В 18 её корейский друг начал учить её диджеингу, и тут всё пошло-поехало: диджей сеты в местных клубах Лондона, через 4 года начала писать свои собственные треки, потом её начали приглашать на крутые фестивали\n\n" +
                        "- В 2019 году Forbes назвал Гоу одним из азиатских лидеров, пионеров и предпринимателей в возрасте до 30 лет.\n\n" +
                        "- В том же году она запустила свой собственный модный лейбл KIRIN («жираф» по-корейски), а в марте — собственный независимый лейбл Gudu Records (gudu означает «обувь» на корейском языке)."
            )
        )
        data.add(
            MusicModel("Heated", "Beyonce", "Single",
                arrayListOf("R&B", "Soul"),
                R.drawable.music_cover_beyonce_heated,
                "video_beyonce_heated.mp4",
                ""
            )
        )
        data.add(
            MusicModel("Glimpse Of Us", "Joji", "Single",
                arrayListOf("Alternative", "Indie"),
                R.drawable.music_cover_joji_glimpse_of_us,
                "video_joji_glimpe_of_us.mp4",
                ""
            )
        )

        return  data
    }
}