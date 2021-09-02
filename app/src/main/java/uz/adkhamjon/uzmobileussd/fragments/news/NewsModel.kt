package uz.adkhamjon.uzmobileussd.fragments.news

class NewsModel {
    var name:String?=null
    var description:String?=null
    var date:String?=null
    constructor()
    constructor(name: String?, description: String?, date: String?) {
        this.name = name
        this.description = description
        this.date = date
    }

}