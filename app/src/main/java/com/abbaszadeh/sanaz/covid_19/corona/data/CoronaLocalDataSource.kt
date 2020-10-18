package com.abbaszadeh.sanaz.covid_19.corona.data

import androidx.lifecycle.LiveData
import com.abbaszadeh.sanaz.covid_19.core.MyApplication
import com.abbaszadeh.sanaz.covid_19.core.db.CoronaDatabase
import com.abbaszadeh.sanaz.covid_19.corona.data.database.CompleteInfo
import com.abbaszadeh.sanaz.covid_19.corona.data.database.GeneralStatistics
import com.abbaszadeh.sanaz.covid_19.corona.data.model.CoronaNetworkItem

class CoronaLocalDataSource {

    val db = CoronaDatabase.getDatabase(MyApplication.app)


    suspend fun insertAllCountries(list: ArrayList<CoronaNetworkItem>) {

        list.forEach { corona ->

            var mapNames = mapOf(
                "AFG" to "افغانستان",
                "ALB" to "آلبانی",
                "DZA" to "الجزایر",
                "AND" to "آندورا",
                "AGO" to "آنگولا",
                "ATG" to "آنتیگوئا وباربادو",
                "ARG" to "آرژانتین",
                "ARM" to "ارمنستان",
                "AUS" to "استرالیا",
                "AUT" to "اتریش",
                "AZE" to "آذربایجان",
                "BHS" to "باهاماس",
                "BHR" to "بحرین",
                "BGD" to "بنگلادش",
                "BRB" to "باربادوس",
                "BLR" to "بلاروس",
                "BEL" to "بلژیک",
                "BLZ" to "بلیز",
                "BEN" to "بنین",
                "BTN" to "بوتان",
                "BOL" to "بولیوی",
                "BIH" to "بوسنی و هرزگوین",
                "BWA" to "بوتسوانا",
                "BRA" to "برزیل",
                "BRN" to "برونئی",
                "BGR" to "بلغارستان",
                "BFA" to "بورکینافاسو",
                "BDI" to "بروندی",
                "KHM" to "کامبودیا",
                "CMR" to "کامرون",
                "CAN" to "کانادا",
                "CPV" to "کیپ ورد",
                "CAF" to "جمهوری آفریقای مرکزی",
                "TCD" to "چاد",
                "CHL" to "شیلی",
                "CHN" to "چین",
                "COL" to "کلمبیا",
                "COM" to "کوموروس",
                "COG" to "کنگو",
                "COD" to "جمهوری دموکراتیک کنگو",
                "CRI" to "کاستاریکا",
                "CIV" to "ساحل عاج",
                "HRV" to "کرواسی",
                "CUB" to "کوبا",
                "CYP" to "قبرس",
                "CZE" to "چک",
                "DNK" to "دانمارک",
                "DJI" to "جیبوتی",
                "DMA" to "دومینیکا",
                "DOM" to "جمهوری دومینکن",
                "ECU" to "اکوادور",
                "EGY" to "مصر",
                "SLV" to "ال سالدوادور",
                "GNQ" to "گینه استوایی",
                "ERI" to "اریتره",
                "EST" to "استونی",
                "ETH" to "اتیوپی",
                "FJI" to "فیجی",
                "FIN" to "فنلاند",
                "FRA" to "فرانسه",
                "GAB" to "گابن",
                "GMB" to "گامبیا",
                "GEO" to "گرجستان",
                "DEU" to "آلمان",
                "GHA" to "غنا",
                "GRC" to "یونان",
                "GRD" to "گرانادا",
                "GTM" to "گواتمالا",
                "GIN" to "گینه",
                "GNB" to "گینه بیسائو",
                "GUY" to "گویانا",
                "HTI" to "هائیتی",
                "HND" to "هندوراس",
                "HKG" to "هنگ کنگ",
                "HUN" to "مجارستان",
                "ISL" to "ایسلند",
                "IND" to "هند",
                "IDN" to "اندونزی",
                "IRN" to "ایران",
                "IRQ" to "عراق",
                "IRL" to "ایرلند",
                "ISR" to "اسرائیل",
                "ITA" to "ایتالیا",
                "JAM" to "جاماییکا",
                "JPN" to "ژاپن",
                "JOR" to "اردن",
                "KAZ" to "قزاقستان",
                "KEN" to "کنیا",
                "KIR" to "کیریباتی",
                "PRK" to "کره شمالی",
                "KOR" to "کره",
                "KWT" to "کویت",
                "KGZ" to "قرقیزستان",
                "LAO" to "لائوس",
                "LVA" to "لاتویا",
                "LBN" to "لبنان",
                "LSO" to "لسوتو",
                "LBR" to "لیبریا",
                "LBY" to "لیبی",
                "LIE" to "لیختن اشتاین",
                "LTU" to "لیتوانی",
                "LUX" to "لوکزامبورگ",
                "MAC" to "Macau China",
                "MKD" to "مقدونیه",
                "MDG" to "ماداگاسکار",
                "MWI" to "مالاوی",
                "MYS" to "مالزی",
                "MDV" to "مالدیو",
                "MLI" to "مالی",
                "MLT" to "مالت",
                "MHL" to "جزایر مارشال",
                "MRT" to "موریتانی",
                "MUS" to "ماریتیوس",
                "MEX" to "مکزیک",
                "FSM" to "میکرونزیا",
                "MDA" to "مولداوی",
                "MCO" to "موناکو",
                "MNG" to "مغولستان",
                "MNE" to "مونته نگرو",
                "MAR" to "مراکش",
                "MOZ" to "موزامبیک",
                "MMR" to "میانمار",
                "NAM" to "نامیبیا",
                "NRU" to "نائورو",
                "NPL" to "نپال",
                "NLD" to "هلند",
                "NZL" to "نیوزیلند",
                "NIC" to "نیکاراگوئه",
                "NER" to "نیجر",
                "NGA" to "نیجریه",
                "NOR" to "نروژ",
                "OMN" to "عمان",
                "PAK" to "پاکستان",
                "PLW" to "پالائو",
                "PSE" to "فلسطین",
                "PAN" to "پاناما",
                "PNG" to "گینه نو",
                "PRY" to "پاراگوئه",
                "PER" to "پرو",
                "PHL" to "فیلیپین",
                "POL" to "لهستان",
                "PRT" to "پرتغال",
                "QAT" to "قطر",
                "ROU" to "رومانی",
                "RUS" to "روسیه",
                "RWA" to "رواندا",
                "KNA" to "سنت کیتس و نویس",
                "LCA" to "سنت لوسیا",
                "VCT" to "سنت وینسنت و گرنادین\u200Cها",
                "WSM" to "ساموا",
                "SMR" to "سن مارینو",
                "STP" to "سائوتومه و پرینسیپ",
                "SAU" to "عربستان",
                "SEN" to "سنگال",
                "SRB" to "صربستان",
                "SYC" to "سیشل",
                "SLE" to "سیرالئون",
                "SGP" to "سنگاپور",
                "SVK" to "اسلواکی",
                "SVN" to "اسلونی",
                "SLB" to "جزایر سلیمان",
                "SOM" to "سومالی",
                "ZAF" to "آفریقای جنوبی",
                "ESP" to "اسپانیا",
                "LKA" to "سریلانکا",
                "SDN" to "سودان",
                "SUR" to "سورینام",
                "SWZ" to "سوازیلند",
                "SWE" to "سوئد",
                "CHE" to "سوییس",
                "SYR" to "سوریه",
                "TWN" to "تایوان",
                "TJK" to "تاجیکستان",
                "TZA" to "تانزانیا",
                "THA" to "تایلند",
                "TLS" to "تیمور شرقی",
                "TGO" to "توگو",
                "TON" to "تونگا",
                "TTO" to "ترینیداد و توباگو",
                "TUN" to "تونس",
                "TUR" to "ترکیه",
                "TKM" to "ترکمنستان",
                "TUV" to "تووالو",
                "UGA" to "اوگاندا",
                "UKR" to "اوکراین",
                "ARE" to "امارات",
                "GBR" to "انگلستان",
                "USA" to "آمریکا",
                "URY" to "اروگوئه",
                "UZB" to "اوزبکستان",
                "VUT" to "وانواتو",
                "VAT" to "واتیکان",
                "VEN" to "ونزوئلا",
                "VNM" to "ویتنام",
                "ESH" to "صحرای غربی",
                "YEM" to "یمن",
                "ZMB" to "زامبیا",
                "ZWE" to "زیمبابوه",

                "BMU" to "برمودا",
                "VGB" to "جزایر ویرجین بریتانیا",
                "CYM" to "جزایر کیمن",
                "MYT" to "مایوت",
                "FRO" to "جزایر فارو",
                "FLK" to "جزایر فالکلند",
                "GUF" to "گویان فرانسه",
                "PYF" to "پلی\u200Cنزی فرانسه",
                "GIB" to "جبل طارق",
                "GRL" to "گرینلند",
                "GLP" to "جزیرهٔ گوادلوپ",
                "MAC" to "ماکائو چین",
                "MTQ" to "مارتینیک",
                "MSR" to "مونتسرات",
                "CUW" to "کوراسائو",
                "ABW" to "آروبا",
                "SXM" to "سینت مارتن",
                "BES" to "جزایر کارائیب هلند",
                "NCL" to "کالدونیای جدید",
                "REU" to "رئونیون",
                "BLM" to "سن بارتلمی",
                "AIA" to "آنگویلا",
                "MAF" to "سنت مارتین فرانسه",
                "SPM" to "سن-پیر-ا-میکلون",
                "SSD" to "سودان جنوبی",
                "TCA" to "جزایر تورکس و کایکوس",
                "JEY" to "جزیره\u200Cهای مانش",
                "IMN" to "جزیره من",
                "WLF" to "والیس و فوتونا"
            )
            var mapContinent = mapOf(
                "Asia" to " آسیا",
                "Europe" to " اروپا",
                "Africa" to " آفریقا",
                "North America" to " آمریکای شمالی",
                "Australia/Oceania" to " اقیانوسیه",
                "South America" to " آمریکای جنوبی",
                "" to " "
            )

            corona.persianContinent = mapContinent[corona.continent]
            db.coronaDao().insertCoronas(corona)

            corona.countryInfo?.let {
                if (it.iso3 != null && mapNames.contains(it.iso3)) {
                    it.persianName = mapNames[it.iso3]
                } else {
                    it.persianName = corona.country
                }

                db.coronaDao().insertCountriesInfo(it)

            }
        }
    }

    fun getAllCountries(): List<CompleteInfo> {
        return db.coronaDao().getAllCountries()
    }

    fun getCountryInfo(countryId: Int): CompleteInfo {
        return db.coronaDao().getCountryInfo(countryId)
    }

    fun getGeneralStatistics(): LiveData<GeneralStatistics> {
        return db.coronaDao().getGeneralStatistics()
    }

    fun deleteAll() {
        db.clearAllTables()
    }
}