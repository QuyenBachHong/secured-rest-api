package me.quyen.entities.fakadata;

import java.util.concurrent.ThreadLocalRandom;

public class PublisherData {
    public static String publisherName(){
        return nameUrlEmailCode()[0];
    }
    public static String publisherUrl(){
        return nameUrlEmailCode()[1];
    }
    public static String publisherEmail(){
        return nameUrlEmailCode()[2];
    }
    public static String publisherCode(){
        return nameUrlEmailCode()[3];
    }
    public static String[] nameUrlEmailCode(){
        int rint = ThreadLocalRandom.current().nextInt(0,
                Math.min(publisherCodes.length,publisherNames.length));
        String name = publisherNames[rint];
        String code = publisherCodes[rint];
        String http = "https://www.";
        String[] domainTails = new String[]{".com",".net",".org",".me"};
        String[] mailTails
                = new String[]{"@gmail.com","@outlook.com","@live.com","@hotmail.com"
                                ,"@msn.com","@yahoo.com", "@email.me"};
        String tname = name.replace(" ","").toLowerCase();
        String email
                = tname + mailTails[ThreadLocalRandom.current().nextInt(0,mailTails.length)];
        String urlString = http + tname
                + domainTails[ThreadLocalRandom.current().nextInt(0, domainTails.length)];
        return new String[]{name, urlString, email, code};
    }
    private static final String[] publisherNames = new String[]{
        "Wyn's Magazine Publishers","Litfire Publishing","Palgrave Macmillan","Cengage","Mills Boon","University of South Carolina Press","Teespring",
                "ABC Publishing","Llumina Press","Canongate books","Central European University Press","Minerva Press","University of Toronto Press","Vistaprint",
                "Abrams & Chronicle Books","Lost Roads Publishers","And Other Stories","Century","Mirage Publishing","University of Wales Press","Zazzle",
                "Africa World Press","Mad Norwegian Press","Andersen Press","Chambers","MIT Press","The University Press Limited","Editura Academiei",
                "Albatross Publishing House","Marick Press","Bloomsbury Publishing","Charles Scribner's Sons","Mkuki na Nyota","University Press of America","Editura Adevărul",
                "AmazonCrossing","Mariner Books","HarperCollins","Chatto Windus","Modern Library","University Press of Kansas","Editura Albatros",
                "AmazonEncore","Marsh Hawk Press","JOFFE BOOKS","Chick Publications","Monstrous Regiment Publishing","University Press of Kentucky","Editura Aldine",
                "The American School Library","Marzani & Munsell","Oxford University Press","Chronicle Books","Morgan James Publishing","Usborne Publishing","Editura ALFA",
                "Another Rainbow Publishing","McClain Printing Company","Random House","Churchill Livingstone","Mother Tongue Publishing","Walter de Gruyter","Editura ALL",
                "Arkham House","Mehring Books","Atlantic Books","Cisco Press","Mycroft Moran","Ward Lock Co","Grupul Editorial AMALTEA",
                "Ashgate Publishing","Meredith Publishing Company","Berkley books","City Lights Publishers","Myriad Editions","WBusiness Books","Amco Press",
                "The Associated Publishers","Mises Institute","Compass Publishing","Cloverdale Corporation","Naiad Press","Weidenfeld Nicolson","Editura Anastasia",
                "Atria Publishing Group","Modern Library","Faber and Faber","Cold Spring Harbor Laboratory Press","Nauka","Well-Trained Mind Press","Editura Antim Ivireanul",
                "August House","Morgan Quitno Press","Hachette","Collector's Guide Publishing","NavPress","Wesleyan University Press","Editura Aquila '93",
                "Auwa Books","The Mosher Press","Severn House","Collins – now part of HarperCollins","New American Library","WestBow Press","Editura Art",
                "Backwaters Press","Mountain Press Publishing Company","TITAN Books","Columbia University Press","New Beacon Books","Westminster John Knox Press","Editura Arta Grafică",
                "Baen Ebooks","Mycroft & Moran","Agenda Publishing","Concordia Publishing House","New Directions Publishing","Wildside Press","Editura Artemis",
                "Bancroft Press","National Academies Press","Aria publishing","Constable Co Ltd","New English Library","Wiley","Editura Paideia",
                "Belmont Books","National Poetry Foundation","Autumn Publishing","Continuum International Publishing Group","New Holland Publishers","William Edwin Rudge","Editura Paralela 45",
                "Bergin & Garvey","Neale Publishing Company","Carcanet Press","Copper Canyon Press","New Village Press","William B. Eerdmans Publishing Company","Polirom",
                "Bilingual Review Press","New American Press","Carlton Books","Cork University Press","New York Review Books","Windgate Press","Editura Politehnica",
                "Bison Books","NewSouth Books","Gardners Books","Cornell University Press","New Win Publishing","Wipf and Stock","Editura Politehnium",
                "BJU Press","O'Reilly Media","Ladybird Books","Coronet Books","Newnes","Wisdom Publications","Editura Politică",
                "Black Lizard (publisher)","Oak Knoll Books and Press","Penguin Random House","Counterpoint","No Starch Press","Witherby Publishing Group","Editura Pontica",
                "Bleak House Books","Other Voices, Inc.","Hachette Livre","Craftsman Book Company","Nonesuch Press","Woodhead Publishing","Editura Prior & Books",
                "Bloodletting Press","Oxmoor House","Macmillan Publishers","CRC Press","Noontide Press","Wordfarm","Editura Prometeu",
                "Blue Ribbon Books","Oyster River Press","Scholastic","Creative Book Publishers International","Northwestern University Press","Workman Publishing Company","Editura Prut Internațional",
                "Book of the Month","Pacific Press Publishing Association","SImon and Schuster","Cresset Press","Nosy Crow","World Publishing Company","Editura Saeculum I.O.",
                "Brash Books","Parallax Press","HarperCollins","Crocker Brewster","Oberon Books","World Scientific","Editura Saga",
                "Broad Press","Passage Publishing","Aria publishing","Crown Publishing Group","Open Book Publishers","Wrecking Ball Press","Editura Scrisul Românesc",
                "Burning Deck Press","Pathfinder Press","Express Publishing","Baen Books","Open Court Publishing Company","Wrox Press","Editura Sigma",
                "Callaway Arts & Entertainment","Pathfinder tendency","Houghton Mifflin Harcourt","Baker Publishing Group","Open University Press","Tachyon Publications","Editura Sitech",
                "Cargo Cult Press","Penfield Books","O’Reilly Media, Inc.","Ballantine Books","OR Books","Tammi, Finland","Editura Socec",
                "Cartridges of the World","Picador (imprint)","Wiley","Banner of Truth Trust","Orchard Books","Target Books","Editura Sport-Turism",
                "Caxton Press (United States)","PM Press","Chronicle Books","Bantam Books","O'Reilly Media","Tarpaulin Sky Press","Editura C. H. Beck",
                "Center for Louisiana Studies","Poetry.com","HarperCollins Publishers","Bantam Spectra","Orion Books","Tartarus Press","Editura Cartea Aromână",
                "Chelsea Green Publishing","Point Blank (publisher)","Pearson","Barrie Jenkins","Orion Publishing Group","Tate Publishing & Enterprises","Cartea Creștină",
                "Cleis Press","Poisoned Pen Press","Skyhorse Publishing","Basic Books","Osprey Publishing","Taunton Press","Editura Cartea de Buzunar",
                "CN Times Books","Purple House Press","Ablex Publishing","BBC Books","Other Press","Taylor & Francis","Cartea Românească",
                "Common Courage Press","The Quarto Group","Ace Books","Belknap Press","The Overlook Press","Ten Speed Press","Editura Cartea Românească Educațional",
                "COMPUTE! Publications","Quinto Sol","Akashic Books","Bella Books","Oxford University Press","Thames & Hudson (UK)","Editura Cartea Rusă",
                "Context Books","R. R. Bowker","Allen And Unwin","Bellevue Literary Press","Palgrave Macmillan","Thames & Hudson USA","Cartea Universitară",
                "Correspondence Publishing Committee","Ramble House","Baen","Bendon Publishing International","Pan Books – now Pan Macmillan","Thieme Medical Publishers","Editura Cartier",
                "Coventry House Publishing","Random House","Baker Publishing Group","Berg Publishers","Pantheon Books","Third World Press","Editura Casa Radio",
                "CRC Press","RCL Benziger","BBC books","Berkley Books","Papadakis Publisher","Thomas Nelson","Editura Casa Şcoalelor",
                "Crown Publishing Group","Ripley Publishing","Beacon Press","Bison Books","Parachute Publishing","Three Sirens Press – defunct","cIMeC – Institutul de Memorie Culturală",
                "Del Sol Press","Rizzoli Libri","Berg Publishers","Black Dog Publishing","Parragon","Ticonderoga Publications","Editura Națională Ciornei",
                "The Derrydale Press","Robert Appleton Company","Abilene","Black Ink Collective","Pathfinder Press","Time Inc.","Editura Christiana",
                "DeVore & Sons","Salina Bookshelf","Ablex Publishing","Black Library","Paulist Press","Times Books","Editura Compania",
                "Dilettante Press","Santa Fe Writers Project","Abrams Books","Black Sparrow Press","Pavilion Books","Titan Books","Editura Contrafort",
                "DinE College Press","SAR Press","Academic Press","Black White Publishing","Pecan Grove Press","Top Shelf Productions","Editura Corint",
                "Disney Hyperion","Sarabande Books","Ace Books","Blackie and Son","Pen and Sword Books","Tor Books","Crime Scene Press",
                "Donald M. Grant, Publisher","ScholarlyEditions","Addison-Wesley","Blackstaff Press","Pendleton Publishing","Triangle Books","Editura Cugetarea",
                "Dos Madres Press","Scholastic Corporation","Adis International","Blackwell Publishing","Penguin Books UK","Troubador Press","Editura Cultura Națională",
                "Dutton Guilt Edged Mysteries","Shasta Publishers","Akashic Books","Blake Publishing","Penguin Random House UK","Tupelo Press","Editura Curtea Veche",
                "E. P. Dutton","Silver Burdett","Aladdin Paperbacks","Bloodaxe Books","Penn State University Press","Tuttle Publishing","Editura Cuvântul",
                "Eakins Press","Skinner House Books","Alfred A. Knopf","Bloomsbury Publishing","Persephone Books","Twelveheads Press","Adarna House",
                "Earthling Publications","Small System Services","Allen Unwin","Blue Ribbon Books","Perseus Books Group","Two Dollar Radio","Affirm Press",
                "Edwin Mellen Press","Smithsonian Institution Press","Allison Busby","Bobbs-Merrill Company","Peter Lang","Amazon's Kindle Direct Publishing","American Girl",
                "Eio Books","Social Contract Press","Alma Books","Bogle-L'Ouverture Publications","Peter Owen Publishers","Apple's App Store (iOS)","Annick Press",
                "Elixir Press","Sophia Institute Press","Alyson Books","Book League of America","Phaidon Press","Barnes & Noble","Anvil Publishing",
                "ESPN Books","South Dakota State Historical Society Press","American Graphics Institute","Book Works","Philosophy Documentation Center","Blurb, Inc.","Arbordale Publishing",
                "Everyman's Library","Southern Progress Corporation","André Deutsch","Booktrope","Philtrum Press","Kobo Writing Life","Barefoot Books",
                "Exact Change","Square One Publishers","Andrews McMeel Publishing","Borgo Press","Picador","Lulu","Beaver's Pond Press",
                "Fitzroy Dearborn Publishers","SR Books","Anova Books","Boundless","Pimlico Books at Random House","Powell's Books","Bendon Publishing International",
                "Four Winds Press","Stratford Press (Cincinnati)","Antenne Books","Bowes Bowes","Playwrights Canada Press","Scribd","Big Guy Books",
                "Gallery Publishing Group","Tarpaulin Sky Press","Anvil Press Poetry","Boydell Brewer","Pluto Press","Smashwords","Bloomsbury",
                "Gareth Stevens","Tate Publishing & Enterprises","Applewood Books","Broadside Lotus Press","Point Blank","Wattpad","The Book House for Children",
                "Georgetown University Press","Technology Press","Apress","Breslov Research Institute","Poisoned Pen Press","Amazon Kindle","Candlewick Press",
                "Glorian Publishing","Telos (journal)","Arbor House","Brill Publishers","Policy Press","Apple Books Store","Capstone Publishers",
                "Grant-Hadley Enterprises","Third Reich Books","Arbordale Publishing","Brimstone Press","Polity","Archive of Our Own","Children's Press",
                "GraphicAudio","This Land Press","Arcade Publishing","Broadview Press","Practical Action","Barnes & Noble Nook","Chronicle Books",
                "Griffin Publishing Company","Threshold Editions","Arcadia Publishing","Burns Oates","J. Q. Preble","FanFiction.Net (aka Fiction Press)","Cuento de Luz",
                "Hannover House","Trident Media Group, LLC","Arkham House","Butterworth-Heinemann","Prentice Hall","Google Play Books","David Fickling Books",
                "Hanuman Books","Triumph Books","Arktos Media","Macmillan Publishers","Prime Books","Hoopla Digital","DK(formerly Dorling Kindersley)",
                "Talk:Hanuman Books/Temp","Two Plus Two Publishing","Armida Publications","Mainstream Publishing","Princeton University Press","Kindle Direct Publishing","Dutton Children's Books",
                "Harbor Mountain Press","Tyrant Books","ArtScroll","Manchester University Press","Profile Books","Kindle Store","Egmont Publishing",
                "Harrison of Paris","U.S. Games Systems","Ash-Tree Press","Mandrake of Oxford","Progress Publishers","Kobo","Eklavya foundation",
                "Harry N. Abrams, Inc.","Editions Underbahn","Athabasca University Press","Mandrake Press","Prometheus Books","Lulu.com","Enslow Publishing",
                "Hayden Book Company","Urban Books","Atheneum Books","Manning Publications","Puffin Books","NoiseTrade","Faber and Faber",
                "Hayden-McNeil","Valancourt Books","Atlantic Books","Manor House Publishing","Purple House Press","OverDrive, Inc.","Figures In Motion",
                "The History Press (U.S.)","Vantage Press","Atlas Press","Marion Boyars Publishers","Pushkin Press","PocketBook Reader","Fremantle Press",
                "Holt McDougal","Velázquez Press","ATOM Books","Mark Batty Publisher","The Quarto Group","Scribd","Gallimard Jeunesse",
                "Hub City Writers Project","Velocity Publishing","Atria Publishing Group","Marshall Cavendish","Que Publishing","Smashwords","Gecko Press",
                "Hyperion Books for Children","Villard (imprint)","Aunt Lute Books","Marshall Pickering","Quebecor","Wattpad","Golden Books",
                "Incunabula (publisher)","Visible Ink Press","Austin Macauley Publishers","Martinus Nijhoff Publishers","Quirk Books","Wikibooks","Goops Unlimited",
                "Independent History & Research","VitalSource","Avery Publishing","Matthias Media","UCL Press","BiblioBazaar","HarperCollins",
                "InterVarsity Press","W. H. Freeman and Company","Avon Publications","McClelland Stewart","United States Government Publishing Office","Bookvault","Hogs Back Books",
                "Jenkins Group","Washington Summit Publishers","Caister Academic Press UK","McFarland Company","Universal Publishers","Books LLC","J. Lumsden and Son",
                "John Day Company","Washington Writers' Publishing House","Cambridge University Press UK","McGraw-Hill Education","University of Akron Press","DiggyPOD","Kerala State Institute of Children's Literature",
                "Juan de la Cuesta Hispanic Monographs","Western Islands (publisher)","Canadian Science Publishing","Medknow Publications","University of Alabama Press","Lightning Source","Kids Can Press",
                "Jump at the Sun","Westminster John Knox Press","Candlewick Press","Melbourne University Publishing","University of Alaska Press","Llumina Press","KimĐồngPublishing House",
                "Kendall Hunt Publishing Company","Westview Press","Canongate Books","Mercier Press","University of British Columbia Press","Lulu","Ladybird Books",
                "Kore Press","Wheatland Press","Capstone Publishers","Methuen Publishing","University of California Press","Powell's Books","Lee & Low Books",
                "League for Yiddish","Whitman Publishing","Carcanet Press, Manchester","Michael Joseph","University of Chicago Press","Cafe Press","Lerner Publishing Group",
                "Leisure Arts","Wit's End Publishing","Carlton Publishing Group UK","Michael O'Mara Books","University of Michigan Press","CustomInk","Mackinac Island Press",
                "Leisure Books","Wodanesdag Press","Carnegie Mellon University Press","Michigan State University Press","University of Minnesota Press","Redbubble","Magabala Books",
                "Library of America","Word Works","Casemate Publishers","Microsoft Press","University of Nebraska Press","Shopify","McLoughlin Brothers",
                "The Limestone Press","Wordfarm","Cassava Republic Press","The Miegunyah Press","University of Pennsylvania Press","Shutterfly","Miles Kelly Publishing",
                "Literary Guild","WWE Books","Cassell","Miles Kelly Publishing","University of Queensland Press","Spreadshirt","Nosy Crow",
                "Orchard Books","Peace Hill Press","Purple House Press","Random House","Salariya Book Company","Simon & Schuster","Tamarind Books",
                "Orion","Prometheus Books","The Quarto Group","Roaring Brook Press","Scholastic","Skyhorse Publishing","Text Publishing"
    };

    private static final String[] publisherCodes = new String[]{
            "NBF-182","LPC-892","PMJ-365","CJH-141","MBV-497","UoA-122","TJG-144",
            "APD-404","LPF-183","CbS-376","CEJ-128","MPI-649","UoJ-430","VJR-877",
            "A&V-157","LRQ-607","AOC-187","CJF-909","MPF-261","UoS-528","ZJW-889",
            "AWI-584","MNZ-963","APL-479","CJQ-225","MPM-824","TUF-716","EAI-991",
            "APR-105","MPA-763","BPY-309","CSF-816","MnC-406","UPH-497","EAA-790",
            "AJR-750","MBB-889","HJM-787","CWA-355","MLC-183","UPR-699","EAW-308",
            "AJK-641","MHR-547","JBM-725","CPM-336","MRN-221","UPJ-175","EAS-830",
            "TAX-882","M&N-234","OUS-572","CBT-186","MJX-609","UPK-524","EAZ-412",
            "ARU-511","MPH-846","RHE-848","CLT-707","MTW-765","WdN-650","EAG-116",
            "AHF-567","MBF-662","ABN-501","CPH-526","MMP-974","WLB-524","GEZ-510",
            "APY-364","MPN-531","BbI-650","CLR-696","MET-564","WBC-316","APM-881",
            "TAY-116","MIC-522","CPQ-394","CCP-150","NPQ-460","WNR-837","EAN-988",
            "APO-558","MLA-115","FaT-100","CSX-682","NJW-742","WMX-280","EAU-125",
            "AHJ-863","MQF-860","HJW-660","CGX-597","NJE-690","WUB-600","EAH-167",
            "ABM-605","TMR-287","SHU-216","C–Q-712","NAC-564","WPZ-653","EAL-142",
            "BPH-365","MPE-273","TBT-920","CUB-253","NBS-150","WJD-616","EAQ-579",
            "BEC-887","M&J-793","APB-434","CPN-984","NDN-915","WPI-710","EAY-864",
            "BPE-299","NAB-204","ApG-270","CCJ-617","NEQ-621","WJP-507","EPK-750",
            "BBP-795","NPC-328","APM-852","CIU-191","NHN-796","WEK-169","EPG-913",
            "B&T-664","NPK-879","CPD-236","CCT-424","NVN-961","WBZ-663","PJH-914",
            "BRJ-191","NAN-221","CBJ-136","CUJ-358","NYB-964","WPC-640","EPZ-656",
            "BBN-597","NBR-185","GBF-441","CUH-480","NWV-156","WaE-325","EPO-415",
            "BPZ-952","OMS-699","LBQ-517","CBU-250","NJG-684","WPO-970","EPR-159",
            "BLY-378","OKO-427","PRS-978","CJZ-928","NSB-981","WPX-799","EPZ-312",
            "BHN-421","OVJ-339","HLM-443","CBT-504","NPH-394","WPP-770","EPY-863",
            "BPM-230","OHV-895","MPY-588","CPF-731","NPP-559","WJN-973","EPL-285",
            "BRN-865","ORU-427","SJJ-838","CBU-573","NUQ-544","WPC-285","EPG-649",
            "BoY-785","PPS-565","SaF-916","CPN-807","NCC-111","WPW-313","ESC-698",
            "BBB-654","PPV-597","HJB-419","CBV-731","OBC-421","WSH-797","ESM-960",
            "BPS-693","PPB-288","ApJ-383","CPY-411","OBM-594","WBL-220","ESV-410",
            "BDE-576","PPH-832","EPW-350","BBH-222","OCI-473","WPD-934","ESY-342",
            "CAJ-531","PtG-583","HMP-342","BPS-214","OUW-363","TPA-588","ESS-460",
            "CCH-726","PBB-909","OMP-463","BBK-940","OBX-815","TFU-101","ESX-447",
            "CoA-408","P(O-491","WJO-818","BoC-393","OBD-314","TBC-469","ESF-533",
            "CPO-571","PPZ-671","CBP-944","BBG-205","OMX-399","TSC-335","ECW-605",
            "CfO-312","PJE-513","HPM-189","BSE-197","OBU-122","TPG-460","ECK-528",
            "CGG-891","PBE-962","PJY-208","BJM-203","OPZ-462","TPO-600","CCR-633",
            "CPK-135","PPB-743","SPC-865","BBG-776","OPC-867","TPR-187","ECR-460",
            "CTU-104","PHE-549","APV-824","BBB-563","OPA-952","T&X-755","CRG-673",
            "CCG-236","TQJ-223","ABT-105","BPK-787","TOK-976","TSR-572","ECP-874",
            "CPQ-363","QSB-144","ABD-954","BBB-720","OUT-882","T&E-321","ECU-889",
            "CBV-516","RRZ-604","AAR-316","BLP-609","PMJ-862","T&E-624","CUR-657",
            "CPV-801","RHR-650","BJV-495","BPW-266","PBB-514","TMS-634","ECY-726",
            "CHE-617","RHZ-385","BPT-368","BPV-155","PBU-906","TWQ-206","ECE-708",
            "CPR-395","RBX-436","BbN-374","BBN-178","PPU-146","TNK-307","ECO-705",
            "CPI-186","RPB-253","BPQ-199","BBD-247","PPW-989","TSR-659","c–Y-925",
            "DSC-721","RLP-711","BPU-960","BDE-743","PJC-444","TPG-572","ENJ-769",
            "TDC-467","RAA-551","AJL-649","BIN-284","PPO-587","TIF-685","ECY-879",
            "D&M-425","SBZ-208","APY-961","BLF-731","PPP-162","TBY-760","ECL-163",
            "DPI-990","SFU-342","ABP-426","BSM-526","PBX-203","TBX-635","ECV-746",
            "DCQ-740","SPM-301","APD-851","BWS-833","PGF-475","TSF-809","ECJ-489",
            "DHR-301","SBO-630","ABB-453","BaU-515","PaN-455","TBE-673","CSW-160",
            "DMP-346","SJV-518","AJP-666","BPK-340","PPL-518","TBZ-782","ECT-193",
            "DME-302","SCX-702","AIA-903","BPA-746","PBX-638","TPN-435","ECH-407",
            "DGA-131","SPI-653","ABU-609","BPF-428","PRB-531","TPE-945","ECH-395",
            "EPL-494","SBS-623","APD-465","BBX-271","PSH-827","TPI-287","ECT-888",
            "EPT-879","SHC-260","AAV-454","BPE-360","PBL-281","TPB-178","AHR-749",
            "EPV-726","SSO-976","AUI-534","BRV-792","PBF-697","TDG-374","APV-662",
            "EML-377","SIB-133","ABJ-893","BCV-357","PLM-726","AKJ-296","AGA-589",
            "EBG-455","SCZ-442","ABX-389","BPC-654","POK-179","AAX-874","APM-866",
            "EPV-231","SIK-240","ABN-963","BLO-827","PPC-851","B&O-746","APL-285",
            "EBP-595","SDK-656","AGX-480","BWU-960","PDA-928","BIN-599","APR-986",
            "ELR-651","SPH-774","ADJ-420","BJF-395","PPJ-397","KWO-738","BBU-711",
            "ECK-733","SOC-420","AMX-306","BPJ-174","PJJ-463","LJU-687","BPS-500",
            "FDG-653","SBS-258","ABX-173","BJV-381","PBO-704","PBR-375","BPU-266",
            "FWW-354","SPN-606","ABE-659","BBD-549","PCQ-558","SJV-510","BGD-893",
            "GPL-634","TSO-161","APN-102","BBR-165","PPR-477","SJW-236","BJH-938",
            "GSJ-302","TPW-577","ABX-604","BLC-627","PBB-250","WJE-551","TBJ-506",
            "GUY-237","TPT-933","AJG-974","BRB-280","PPR-226","AKG-147","CPH-995",
            "GPX-272","T(S-992","AHN-898","BPR-515","PPZ-340","ABF-630","CPK-887",
            "GEO-683","TRH-150","APF-138","BPT-759","PJZ-327","AoU-438","CPS-107",
            "GJQ-721","TLY-865","APO-744","BPB-249","PAH-231","B&W-486","CBX-172",
            "GPG-107","TEN-463","APT-475","BOR-220","JQP-934","F(F-692","CdY-794",
            "HHY-673","TMA-567","AHH-292","BJD-350","PHQ-204","GPJ-613","DFT-651",
            "HBQ-843","TBV-691","AML-233","MPJ-477","PBW-567","HDR-139","DDL-868",
            "TBZ-348","TPU-845","APD-659","MPM-603","PUX-573","KDX-884","DCU-303",
            "HMG-936","TBB-610","AJD-933","MUN-902","PBC-786","KSV-945","EPI-885",
            "HoV-203","UGQ-231","APF-414","MoB-639","PPU-767","KJO-671","EfX-964",
            "HNX-232","EUZ-428","AUQ-831","MPZ-232","PBO-479","LJO-434","EPS-839",
            "HBG-817","UBP-333","ABJ-245","MPK-135","PBC-281","NJJ-502","FaH-685",
            "HJR-321","VBP-334","ABV-172","MHW-657","PHL-727","OIT-289","FIW-542",
            "THO-881","VPN-485","APK-804","MBH-375","PPB-828","PRI-159","FPG-599",
            "HMW-707","VPR-875","ABW-256","MBO-317","TQG-252","SJZ-754","GJA-183",
            "HCT-491","VPX-586","APX-710","MCD-611","QPI-331","SJD-332","GPI-204",
            "HBI-136","V(O-596","ALO-624","MPE-470","QJS-930","WJX-116","GBF-255",
            "I(W-192","VIU-639","AMY-390","MNP-505","QBJ-527","WJI-767","GUI-120",
            "IHS-449","VJF-362","APB-758","MMB-967","UPC-167","BJN-607","HJA-261",
            "IPB-398","WHG-184","APW-179","MSB-687","USY-792","BJU-223","HBQ-464",
            "JGF-560","WSA-912","CAM-301","MCP-423","UPT-979","BLT-718","JLY-669",
            "JDG-954","WWJ-780","CUI-195","MEX-587","UoP-290","DJK-578","KSA-246",
            "JdQ-290","WIL-458","CSY-650","MPE-385","UoB-802","LSG-753","KCZ-371",
            "JaN-933","WJV-434","CPN-595","MUX-431","UoO-655","LPH-230","KHB-514",
            "KHL-529","WPW-163","CBT-168","MPK-721","UoY-538","LJF-285","LBN-178",
            "KPT-651","WPZ-553","CPR-591","MPG-920","UoZ-347","PBN-953","L&V-747",
            "LfL-614","WPO-371","CPZ-430","MJV-976","UoS-999","CPC-939","LPC-640",
            "LAX-891","WED-578","CPE-207","MOV-995","UoX-348","CJS-259","MIP-563",
            "LBR-511","WPN-227","CMP-433","MSQ-708","UoQ-195","RJZ-866","MBM-293",
            "LoP-515","WWB-574","CPS-337","MPF-225","UoL-706","SJE-999","MBD-742",
            "TLA-154","WJL-387","CRV-273","TML-406","UoB-286","SJY-481","MKJ-977",
            "LGJ-924","WBG-583","CJP-952","MKA-473","UoX-706","SJP-915","NCD-763",
            "OBL-110","PHJ-395","PHI-921","RHL-238","SBO-247","S&P-485","TBF-423",
            "OJC-338","PBQ-805","TQN-297","RBL-923","SJY-721","SPE-112","TPL-442"
    };
}
