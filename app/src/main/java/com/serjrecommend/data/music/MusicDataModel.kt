package com.serjrecommend.data.music

import android.content.ContentValues
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.File
import java.io.Serializable
import java.util.*


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
data class MusicDataModel (
    val title: String,
    val musician: String,
    val type: String,
    val category: String,
    val tags: List<String>,
    val date: String,
    val coverId: File?,
    val videoId: File?,
    val description: String,
    val paragraphs: ArrayList<MusicParagraphModel>,
    val quote: MusicQuoteModel): Serializable

data class MusicDataModelRemote (
    val title: String = "",
    val musician: String = "",
    val type: String = "",
    val category: String = "",
    val tags: List<String> = listOf(),
    val editDate: Date = Date(0),
    val cover: HashMap<String, String> = hashMapOf(),
    val video: HashMap<String, String> = hashMapOf(),
    val description: String = "",
    val paragraphs: HashMap<String, String> = hashMapOf(),
    val quote: String = "",
    val quoteColor: String = "",
    val explicit: Boolean = false
) {
    fun toMusicModel(coverFile: File, videoURI: File): MusicDataModel {
        val paragraphsArr = arrayListOf<MusicParagraphModel>()
        for (paragraph in paragraphs) {
            paragraphsArr.add(MusicParagraphModel(paragraph.key, paragraph.value))
        }
        val quoteModel = MusicQuoteModel(quote, quoteColor)

        return MusicDataModel(
            title, musician, type, category, tags, editDate.toString(), coverFile, videoURI,
            description, paragraphsArr, quoteModel
        )
    }
}

data class MusicParagraphModel(var title: String, var text: String): java.io.Serializable


data class MusicQuoteModel(var text: String, var color: String): java.io.Serializable



/**
 * MUSIC DATA
 *
 * It store the data about music recommendations
 */
class MusicData {

    var data = arrayListOf<MusicDataModel>()

    init {
        // Create a storage reference from our app
        val storage = Firebase.storage
        val db = Firebase.firestore

        db.collection("music data")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                    val item = document.toObject<MusicDataModelRemote>()

                    // Create a reference to a file from a Google Cloud Storage URI
                    val gsReferenceCover = storage.getReferenceFromUrl(item.cover["link"]!!)
                    val localFileCover = File.createTempFile("music_cover_${item.musician}_${item.title}", "jpg")
                    gsReferenceCover.getFile(localFileCover).addOnSuccessListener {
                        println("Image downloaded")
                    }.addOnFailureListener {
                        // Handle any errors
                    }

                    // Create a reference to a file from a Google Cloud Storage URI
                    val gsReferenceVideo = storage.getReferenceFromUrl(item.video["link"]!!)
                    val localFileVideo = File.createTempFile("music_video_${item.musician}_${item.title}", "mp4")
                    gsReferenceVideo.getFile(localFileVideo).addOnSuccessListener {
                        println("Video downloaded")
                    }.addOnFailureListener {
                        // Handle any errors
                    }

                    Handler(Looper.getMainLooper()).postDelayed({
                        val musicItem = item.toMusicModel(localFileCover, localFileVideo)
                        data.add(musicItem)

                        println("Data added")
                    }, 4000)
                }

                //println(data.toString())
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }



    // Creates an Arraylist of music data and returns it
    fun getMusicData(): ArrayList<Any> {

        /*
        // Data collection
        data.add(
            MusicModel(
                "(It Goes Like) Nanana", "Peggy Gou", "Сингл", "Для спокойного сета",
                arrayListOf("Dance-pop", "House"),
                "2023-07-23",
                R.drawable.music_cover_peggy_gou_nanana,
                "video_peggy_gou_nanana.mp4",
                "Одной июньской ночью весь тикток заполонили видео с диджей сета Пэгги Гу в Марокко. Сначала мне этот трек не особо понравился, но после он стал популярен и зазвучал из каждого утюга. И он мне зашёл!",
                arrayListOf(
                    MusicParagraphModel(
                        "Who is Peggy Gou",
                        "<p>Пегги Гу — известная <b>диджейка</b>, <b>продюсерка</b> и <b>модельерка</b> южнокорейского происхождения. И мне больше всего понравился её attitude: её стиль, звучание её работ, её карьера.</p>" +
                                "<p>Не очень хочется вдаваться в подробности, так что будут хайлайты:</p>" +
                                "<p> — В 14 лет <b>поехала учиться в Лондон</b>, после чего поступила в London College of Fashion</p>" +
                                "<p> — После выпуска работала редактором в </b>Harper's Bazaar Korea</b>, находясь при этом в Лондоне</p>" +
                                "<p> — В 18 её корейский друг начал учить её <b>диджеингу</b>, и тут всё пошло-поехало: диджей сеты в местных клубах Лондона, через 4 года начала писать свои собственные треки, потом её начали приглашать на крутые фестивали</p>" +
                                "<p> — В 2019 году Forbes назвал Гоу <b>одним из азиатских лидеров</b>, пионеров и предпринимателей в возрасте <b>до 30 лет</b></p>" +
                                "<p> — В том же году она запустила свой собственный модный <b>лейбл KIRIN</b> («жираф» по-корейски), а в марте — собственный независимый <b>лейбл Gudu Records</b> («gudu» означает «обувь» на корейском языке)</p>"
                    )
                ),
                MusicQuoteModel(
                    "Just a feeling that I won't, won't leave behind\n" +
                            "Because it's something that is on, it's on my mind\n" +
                            "I guess it goes like\n" +
                            "Na-na-na",
                    "#BC5851"
                )
            )
        )
        data.add(
            MusicModel(
                "One", "Ksenia Dukalis", "Сингл", "Для спокойного сета",
                arrayListOf("Electronic"),
                "2023-08-07",
                R.drawable.music_cover_ksenia_dukalis_one,
                "video_ksenia_dukalis_one.mp4",
                "Очень спокойный и летний трек Ксюши Дукалис (мой любимки), который напоминает мне Fred again… по звучанию и реализации. Насколько я знаю, это её первые шаги в электронную музыку, но уже получилось очень даже вау!",
                arrayListOf(
                    MusicParagraphModel(
                        "Что пишет сама Ксюша",
                        "<p>«Я рассказывала неоднократно, что в моей семье и модели воспитания музыка не была особо в почете, так как “<b>это не практично</b>”. В целом, долгое время диджейская часть моей жизни в целом не воспринималась близкими всерьез или как что-то стоящее (кардинально изменилось только мое отношение к их мнению).</p>" +
                                "<p>Сначала я не могла себе разрешить заниматься музыкой, потом заниматься ей самостоятельно и прислушиваться к тому, что нравится именно мне.</p>" +
                                "<p>Добавим к этому, что когда ты выпускаешь что-то впервые уже имея какой-то медийный статус, говна ты получаешь априори больше, чем человек без него: там есть возможность на право на ошибку, поиск себя и другие нормальные стадии в творчестве.</p>" +
                                "<p>К чему вся эта прелюдия — я очень рада, что <b>наконец делаю сама то, что нравится мне</b>. Что процесс под контролем, что есть люди, которые в меня верят и помогают, не навязывая.</p>" +
                                "<p>Мне хочется и дальше писать, спокойно выпускать композиции, играть на фестивалях заграницей и делать электронную музыку туда. Поэтому уровень моей вовлеченности в музыкальные СМИ в России или блоги честно говоря не высок. При этом новости, что какой-то из плейлистов Spotify взял меня в подборку, вызывают у меня улыбку. Или когда я вижу ваши отметки на видео природы под «One».</p>" +
                                "<p>В конце трека кусочки монолога Джуди Гарленд о том, как она <b>устала слушать чужие версии о том, как проходит ее жизнь</b> и вокруг мертвецы, пытающиеся утащить ее за собой.</p>" +
                                "<p>Собственно, так я себя ощущала после прошлых релизов. <b>I’m gonna talk back</b> — это обещание самой себе не откладывать снова на 2 года композицию и работать так, как это приятно мне»</p>"
                    ),
                    MusicParagraphModel(
                        "И тут, и там, и вообще везде",
                        "<p>И снова меня <b>восхищает чей-то attitude</b>! Возможно, это прозвучит слишком приторно, но мне очень нравятся люди, которые не стоят на месте, постоянно пробуют что-то новое и <b>развиваются в том, что им искренне интересно</b>.</p>" +
                                "<p>Думаю, что всё мною вышеперечисленное относится и к Ксении Дукалис. Ведь она <b>только чем ни занимается</b>:</p>" +
                                "<p> — делает шоу для ютьюба “Есть тема”</p>" +
                                "<p> — руководит коммуникационным агентством Belong Agency;</p>" +
                                "<p> — выпускает мерч с вопросом-девизом Dokole;</p>" +
                                "<p> — коллаборирует с брендами;</p>" +
                                "<p> — занимается производством лимонадов «Река»;</p>" +
                                "<p> — и, конечно же, занимается диджеингом.</p>" +
                                "<p>Могу похвастаться тем, что <b>я был на её диджей-сете</b>! И мне очень понравилось, хоть она и немного странно сводит треки (но не мне судить, я в этом не шарю). Но каков её музыкальный вкус! Я был в восторге!</p>"
                    )
                ),
                MusicQuoteModel(
                    "I'm gonna talk back\n" +
                            "You're dead people",
                    "#666382"
                )
            )
        )
        data.add(
            MusicModel(
                "Paint The Town Red", "Doja Cat", "Сингл", "Мировая попса",
                arrayListOf("Hip-hop", "Rap"),
                "2023-08-08",
                R.drawable.music_cover_doja_cat_paint_the_town_red,
                "video_doja_cat_paint_the_town_red.mp4",
                "Сюрреалистичный перфоманс Доджи Кэт, замысел которого мне не особо понятен (пока что), хороший трек, наполненный смыслом, и немного странный пиар-ход. Обо всём по порядку!",
                arrayListOf(
                    MusicParagraphModel(
                        "Что за перфоманс такой",
                        "<p>Насколько я понял, этот трек — это сингл, который войдёт в <b>будущий альбом</b> Доджи и который задаёт некоторый муд этого альбома.</p>" +
                                "<p>Для общего понимания (и наслаждения стилем) тебе стоит посмотреть клип на эту песню. Но я кратко расскажу, что там вообще происходит: Доджа Кэт совершает какое-то убийство и общается со смертью, после чего попадает в ад и <b>становится дьяволицей</b>.</p>" +
                                "<p>Думаю, этим она символизирует то, что она <b>меняет своё амплуа</b> из новомодной и трендовой Доджи, песни которой залетают в тиктоке, в экспрессивную и bitchy Доджу, которая нашла свой особый стиль. Но вот тут у меня появился вопросик: почему новый стиль супер похож на старый тиктоковский?…</p>"
                    ),
                    MusicParagraphModel(
                        "И это говорит Doja Cat? Та женщина, что мяукала на Met Gala?",
                        "<p>Сам факт убийства в клипе — <b>метафора на её недавно учинённый скандал</b>. Она написала в своём Threads: «Если вы называете себя \"kittenz\" [ярым фанатом Доджи Кэт], это значит, что вам хватит сидеть в телефонах. <b>Нужно устроиться на работу и помочь родителям по дому</b>». Один из комментаторов сказал, что её поклонники всего лишь хотят услышать, что она любит их. Доджа ответила: «<b>Я не люблю вас, потому что я вас даже не знаю</b>».</p>" +
                                "<p>Таким образом она, как бы это бредово ни звучало, “убила” любовь своих фанатов, тем самым <b>выпуская наружу свою внутренную дьяволицу</b>.</p>" +
                                "<p>После не особо неадекватного поведения Доджи многие предрекали конец её карьере. Однако пока об этом даже речи не идёт — более того, <b>её приглашают на новые проекты</b>. Стало известно, что Доджу Кэт назначили хедлайнером шоу Victoria’s Secret.</p>"
                    ),
                    MusicParagraphModel(
                        "Вкусный маркетинг",
                        "<p>Ещё один её пиар-ход — она <b>окрасила в красный</b> практически все обложки своих альбомов / синглов, что выглядит довольно необычно, хотя прошлые обложки мне нравились больше… Уверен, что это всё <b>отсылка к стилю её будущего альбома</b>, чей вайб будет кроваво-дьявольским, как и клип на этот трек.</p>" +
                                "<p>Также меня удивил один крутой ход её пиар-кампании: она не стала делать огромной тайны вокруг своего нового трека и просто включила его на своём прямом эфире в инстаграме. Обычно исполнители борятся со сливами своих треков, так как они из-за этого теряют ощутимую часть дохода. А Доджа <b>сделала слив собственного трека</b>, при этом мило рисуя картину перед камерой!</p>"
                    )
                ),
                MusicQuoteModel(
                    "Yeah, bitch, I said what I said\n" +
                            "I'd rather be famous instead\n" +
                            "I let all that get to my head\n" +
                            "I don’t care, I paint the town red",
                    "#EE0027"
                )
            )
        )
        data.add(
            MusicModel(
                "seven", "Taylor Swift", "Single", "Медиа находки",
                arrayListOf("Folk"),
                "2023-08-09",
                R.drawable.music_cover_taylor_swift_seven,
                "video_taylor_swift_seven.mp4",
                "",
                arrayListOf(
                    MusicParagraphModel(
                        "",
                        ""
                    )
                ),
                MusicQuoteModel(
                "And I think you should come live with me\n" +
                        "And we can be pirates\n" +
                        "Then you won't have to cry\n" +
                        "Or hide in the closet",
                "#757575"
                )
            )
        )

         */

        return data as ArrayList<Any>
    }
}