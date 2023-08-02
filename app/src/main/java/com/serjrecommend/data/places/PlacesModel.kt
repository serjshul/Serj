package com.serjrecommend.data.places

import com.serjrecommend.R


/**
 * PLACES MODEL
 *
 * Data model that contains all the information about places recommendation:
 * - title
 * - types
 * - location
 * - coverId
 * - description
 * - gallery
 * - address
 * - metro
 * - socialMedia
 * - paragraphs
 */
data class PlacesModel(var title: String, var types: ArrayList<String>, var location: String,
                       var coverId: Int, var description: String, var gallery: ArrayList<Int>,
                       var address: String, var metro: String, var socialMedia: String,
                       var paragraphs: ArrayList<PlacesParagraphModel>): java.io.Serializable


/**
 * PLACES PARAGRAPH MODEL
 *
 * Data model for places paragraphs that contains:
 * - title
 * - text
 */
data class PlacesParagraphModel(var title: String, var text: String): java.io.Serializable


/**
 * PLACES DATA
 *
 * It store the data about places recommendations
 */
object PlacesData {

    // Creates an Arraylist of media data and returns it
    fun getPlacesData(): ArrayList<PlacesModel> {
        // ArrayList of media recommendations
        val data = ArrayList<PlacesModel>()

        // Data collection
        data.add(
            PlacesModel(
                "Civil на Волынском",
                arrayListOf("Coffee", "Bar"),
                "Saint-Petersburg",
                R.drawable.places_cover_civil,
                "Цивил — это Петербургская сеть кофе-баров, в которых вкусно, уютно и приятно.",
                arrayListOf(
                    R.drawable.places_gallery_civil_1,
                    R.drawable.places_gallery_civil_2,
                    R.drawable.places_gallery_civil_3,
                    R.drawable.places_gallery_civil_4,
                    R.drawable.places_gallery_civil_5
                ),
                "Волынский переулок, 6",
                "Невский Проспект",
                "@civil.coffeebar",
                arrayListOf(
                    PlacesParagraphModel(
                        "Soft Coffee Bar",
                        "<p>В Цивиле ты сможешь спокойно насладиться своей трапезой, посидеть с ноутбуком и порешать свои рабочие задачки, встретиться с друзьями и попить вина или же прийти на кинопоказ и посмотреть “Маму Мию”, вкушая бенедикт с лососем.</p>" +
                            "<p>Наверное, Цивил — это <b>одно из самых родных для меня заведений</b>: тут я и делал лабы, и встречался с друзьми, и пытался избавиться от тревоги, и просто радовался жизни. Именно в Цивиле мне понравился фильтр, да и вообще вся моя кофейная история началась тут.</p>" +
                            "<p>Эта кофейня находится напротив ДЛТ, и это её огромный плюс! Поэтому у меня есть некоторый ритуал, связанный с этим местом. После каждого похода сюда я <b>иду домой особым путём</b>: через Дворцовую Площадь, Большую Конюшенную улицу, канал Грибоедова и Площадь Искусств. Для меня такая прогулка — святое. Ведь именно в такие моменты ты спокойно можешь романтизировать свою жизнь под эстетичную музыку.</p>"
                    ),
                    PlacesParagraphModel(
                        "Рекомендации по меню",
                        "<p>Вообще, Цивил не стоит на месте и постоянно развивается, так что меню тут меняется примерно раз в год. Буду говорить про то, что есть сейчас!</p>" +
                                "<p>Очень нравится паста с мисо из пармезана, грибами и маринованным желтком (я не знаю, как они его маринуют…). Сендвич с индейкой, сулугуни, айоли из чеснока и салатом — кайф. Ну, бенедикт на булочке бриошь — это по канону.</p>" +
                                "<p>Ещё понравилось сочетание каши с чем-то вообще отдалённым от мира каш. Например, каша из зелёной гречи с грибами, грибным мисо, яйцом и пармезаном.</p>" +
                                "<p>Ну и, конечно же, тут тоже завтраки готовят all day: каши, омлеты, оладушки, тосты и всё то, чего душа за завтраком хочет.</p>" +
                                "<p>Из напитков есть кофейная классика и hand brew, авторские варианты на основе эспрессо, чай, какао, лимонады и сидры. Винная карта тут тоже довольно крутая! Если честно, я не особо разбираюсь, но в ней более 20 наименнований.</p>"
                    ),
                    PlacesParagraphModel(
                        "Civil Cinema Club",
                        "<p>Ещё одно попадание в моё сердечко — это <b>кинопоказы</b>. Они проводятся <b>каждую среду в 19:00</b> в самом отдалённом зале, где можно в спокойной обстановке вкушать вкусы и смотреть что-то красивое. </p>" +
                                "<p><b>Места бесплатные</b>, но, думаю, работникам Цивила будет приятно, если ты что-нибудь закажешь из меню. <b>Бронь производится в телеграм-беседе</b>, в которую я могу тебя добавить (пиши в лс).</p>" +
                                "<p>Как такового расписания нет — выбор фильма производится организаторами, но их выбору я доверяю! Ведь тут я смотрел “Интерстеллар”, “Мама Мия”, “Брюс Всемогущий”, “Назови меня своим именем”, “Амели”, “Общество мёртвых поэтов” и ещё кучу всего.</p>"
                    )
                )
            )
        )

        return  data
    }
}