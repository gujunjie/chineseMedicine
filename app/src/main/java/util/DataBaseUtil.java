package util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import bean.AcuPoint;
import bean.ChineseMedicine;
import bean.ChinesePatentDrug;
import bean.ChinesePatentDrugSecondCategory;
import bean.Examination;
import bean.HotSearch;
import bean.LearningProgress;
import bean.MedicalBook;
import bean.Prescription;
import bean.SearchHistory;
import bean.User;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.greendao.AcuPointDao;
import com.example.abc.chinesemedicine.greendao.ChineseMedicineDao;
import com.example.abc.chinesemedicine.greendao.ChinesePatentDrugDao;
import com.example.abc.chinesemedicine.greendao.ChinesePatentDrugSecondCategoryDao;
import com.example.abc.chinesemedicine.greendao.ExaminationDao;
import com.example.abc.chinesemedicine.greendao.HotSearchDao;
import com.example.abc.chinesemedicine.greendao.LearningProgressDao;
import com.example.abc.chinesemedicine.greendao.MedicalBookDao;
import com.example.abc.chinesemedicine.greendao.PrescriptionDao;
import com.example.abc.chinesemedicine.greendao.SearchHistoryDao;
import com.example.abc.chinesemedicine.greendao.UserDao;

import java.util.ArrayList;
import java.util.List;

public class DataBaseUtil {

    public static void saveHistorySearch(String keyWord) {

        SearchHistoryDao dao=MyApplication.getDaoSession().getSearchHistoryDao();
        SearchHistory history=new SearchHistory(keyWord);
        dao.insertOrReplace(history);

    }

    public static List<String> getSearchTipsList()
    {
        final List<String> NameList=new ArrayList<>();
         Observable.create(new ObservableOnSubscribe<List<String>>() {
             @Override
        public void subscribe(ObservableEmitter<List<String>> e) {
            ChineseMedicineDao dao= MyApplication.getDaoSession().getChineseMedicineDao();
            List<ChineseMedicine> list=dao.loadAll();

            ChinesePatentDrugDao dao1=MyApplication.getDaoSession().getChinesePatentDrugDao();
            List<ChinesePatentDrug> list1=dao1.loadAll();

            AcuPointDao dao2=MyApplication.getDaoSession().getAcuPointDao();
            List<AcuPoint> list2=dao2.loadAll();

            PrescriptionDao dao3=MyApplication.getDaoSession().getPrescriptionDao();
            List<Prescription> list3=dao3.loadAll();

            MedicalBookDao dao4=MyApplication.getDaoSession().getMedicalBookDao();
            List<MedicalBook> list4=dao4.loadAll();




                 for(int i=0;i<list.size();i++)
                 {
                     NameList.add(list.get(i).getName());
                 }
                 for(int i=0;i<list1.size();i++)
                 {
                     NameList.add(list1.get(i).getName());
                 }
                 for(int i=0;i<list2.size();i++)
                 {
                     NameList.add(list2.get(i).getName());
                 }
                 for(int i=0;i<list3.size();i++)
                 {
                     NameList.add(list3.get(i).getName());
                 }
                 for(int i=0;i<list4.size();i++)
                 {
                     NameList.add(list4.get(i).getName());
                 }

             e.onNext(NameList);

          }
      }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> value) {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

         return NameList;
    }

    public static void checkCopyDbFile(final Context context)
    {
        SharedPreferences sp=context.getSharedPreferences("copyData",Context.MODE_PRIVATE);
        boolean isCopyDbFile=sp.getBoolean("isCopyDbFile",false);
        if(!isCopyDbFile)
        {

            io.reactivex.Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> e) {
                    addChineseMedicine();
                    addHotSearch();
                    addSecondCategory();
                    addChinesePatentDrug();
                    addAcuPoint();
                    addPrescription();
                    addMedicalBook();
                    addUser();
                    addExam();
                    e.onNext("初始化数据成功");
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(String value) {
                            Toast.makeText(context,value,Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

            SharedPreferences.Editor editor=sp.edit();
            editor.putBoolean("isCopyDbFile",true);
            editor.apply();
        }
    }



    public static void addChineseMedicine()
    {
        ChineseMedicineDao chineseMedicineDao= MyApplication.getDaoSession().getChineseMedicineDao();

        ChineseMedicine medicine=new ChineseMedicine();
        medicine.setName("安息香");
        medicine.setSortType("A");
        medicine.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544531276&di=89840e03ec7b126c120665aabf257a80&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D27bbf94a0323dd54357eaf2bb960d9ab%2F574e9258d109b3de702a6146c6bf6c81800a4c03.jpg");
        medicine.setData("【中药名】安息香 anxixiang\n" +
                "\n" +
                "【别名】拙贝罗香、野茉莉\n" +
                "\n" +
                "【英文名】Benzoinum。\n" +
                "\n" +
                "【药用部位】安息香科植物白花树Styrax tonkinensis (Pierre) Craib ex Hart.的树脂。\n" +
                "\n" +
                "【植物形态】乔木。高6～30米，胸径8～60厘米，树皮暗灰色或灰褐色，有不规则纵裂纹。树枝稍扁，被褐色茸毛，成长后无毛。叶互生，纸质至薄革质；叶片椭圆形至卵形。圆锥花序，或渐缩小成总状花序，花多，白色。子房半下位。果实近球形，直径10～12毫米，顶端有细小的喙，外面密被灰色星状茸毛。种子卵形，栗褐色，密被小瘤状突起或星状毛。花期4～6月，果期8～10月。\n" +
                "\n" +
                "【产地分布】生于气候温暖、较潮湿、土层深厚的山坡、山谷、疏林中或林缘。分布于云南、广西、广东等地。\n" +
                "\n" +
                "【采收加工】树干自然损伤或于夏、秋季割裂树干，收集流出的树脂，阴干。\n" +
                "\n" +
                "【药材性状】不规则小块，稍扁平，常黏结成团块。表面橙黄色，具蜡样光泽(自然出脂)；或为不规则的圆柱状、扁平块状，表面灰白色至淡黄白色(人工割脂)。质脆，易碎，断面平坦，白色。放置后逐渐变为淡黄棕色至红棕色。加热则软化熔融。气芳香，味微辛，嚼之有沙粒感。\n" +
                "\n" +
                "【性味归经】性平，味辛、苦。归心经、脾经。\n" +
                "\n" +
                "【功效与作用】开窍清神、行气、活血、止痛。属开窍药。\n" +
                "\n" +
                "【临床应用】用量0.6～1.5克，入丸散。用治中风痰厥、气郁暴厥、中恶昏迷、心腹疼痛、产后血晕、小儿惊风。\n" +
                "\n" +
                "【药理研究】刺激呼吸道黏膜增加分泌，促进痰液排出等作用。药理实验表明，酊剂为刺激性祛痰药，置于热水中吸入其蒸汽，则能直接刺激呼吸道黏膜而增加其分泌；可用治支气管炎以促进痰液排出。吸人时应避免蒸汽的浓度过高而刺激眼、鼻、喉等。外用可作局部防腐剂，并能促进溃疡及创伤的愈合。\n" +
                "\n" +
                "【化学成分】含树脂70%～80%，主要成分为泰国树脂酸和苯甲酸松柏醇酯。还含苯甲酸11.7%、苯甲酸桂皮醇酯2.3%和香荚兰醛0.3%、挥发油、三萜类等化学成分，其中有3-苯甲酰泰国树脂酸酯、松柏醇苯甲酸酯、香草醛等成分。\n" +
                "\n" +
                "【使用禁忌】气虚不足、阴虚火旺者慎服。\n" +
                "\n" +
                "【配伍药方】①治久冷腹痛不止：安息香(研)、补骨脂(炒)各30克，阿魏(研)6克。上三味，捣研，罗为细末，醋研饭为丸，如小豆大。每服十丸，空心粥饮下。(《圣济总录》安息香丸)\n" +
                "\n" +
                "②治风腰脚疼痛冷痹及四肢无力：安息香60克，附子(炮裂，去皮、脐)60克、虎胫骨(涂酥炙令黄)60克。上件药，捣罗为散，每服食前，以温酒调下3克。(《圣惠方》安息香散)\n" +
                "\n" +
                "③治男子妇人暗风痫病：安息香(通明无砂石者)、铅丹各30克。上二味，为细末，入白羊心中血研匀，丸如梧桐子大。每服十丸，空心温水下。(《圣济总录》安息香丸)\n" +
                "\n" +
                "④治小儿惊邪：安息香一豆许，烧之自除。(《奇效良方》)\n" +
                "\n" +
                "⑤治妇人产后血晕、血胀，口噤垂死者：安息香3克，五灵脂(水飞净末)15克。共和匀，每服3克，炒姜汤调下。(《方脉正宗》安息香丸)\n" +
                "\n" +
                "⑥治卒然心痛，或经年频发：安息香研末，沸汤服1.5克。(《医世得效方》)");

        chineseMedicineDao.insert(medicine);

        ChineseMedicine medicine1=new ChineseMedicine();
        medicine1.setName("艾叶");
        medicine1.setSortType("A");
        medicine1.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=dcdc8618caea15ce55e3e85bd7695196/7e3e6709c93d70cfe4204290fbdcd100bba12b51.jpg");
        medicine1.setData("【中药名】艾叶 aiye\n" +
                "\n" +
                "【别名】蕲艾、祁艾、大艾叶、艾蒿、五月艾。\n" +
                "\n" +
                "【英文名】Artemisiae Argyi Folium。\n" +
                "\n" +
                "【药用部位】菊科植物艾Artemisia argyi Levl. et Vant.的叶。\n" +
                "\n" +
                "【植物形态】多年生草本，高0.5～1.2米。茎直立，密被茸毛，上部分枝。茎中部叶卵状三角形或椭圆形，有柄，羽状分裂，裂片椭圆形至椭圆状披针形，边缘具不规则的锯齿，上面深绿色，有腺点和蛛丝状毛，下面被灰白色茸毛；茎顶部叶全缘或3裂。头状花序长约3毫米，直径2～3毫米，排成复总状；总苞卵形，总苞片4～5层，密被白色丝状毛；小花筒状，带红色，雌花长约1毫米，两性花长约2毫米，瘦果椭圆形，长约0.8毫米，无毛。花期7～10月。\n" +
                "\n" +
                "【产地分布】艾生于荒地、林缘；有栽培。分布于华北、华东、西南及陕西、甘肃等地。\n" +
                "\n" +
                "【采收加工】夏季花未开时采摘，除去杂质，晒干。\n" +
                "\n" +
                "【药材性状】多皱缩、破碎，有短柄。完整叶片展开后呈卵状椭圆形，羽状深裂，裂片椭圆状披针形，边缘有不规则的粗锯齿；上表面灰绿色或深黄绿色，有稀疏的柔毛及腺点；下表面密生灰白色茸毛。质柔软。气清香，味苦。\n" +
                "\n" +
                "【性味归经】性温，味苦、辛。归肝经、脾经、肾经。\n" +
                "\n" +
                "【功效与作用】散寒止痛、温经止血。属止血药下属分类的温经止血药。\n" +
                "\n" +
                "【临床应用】用量3～9克，水煎服；外用适量，供炙治或熏洗用。主要用治少腹冷痛、经寒不调、宫冷不孕、吐血、衄血、崩漏经多、妊娠下血；外治皮肤瘙痒。醋艾炭温经止血。用于虚寒性出血。近年对其药理作用有不少研究，如水浸剂对致病金黄色葡萄球菌及某些皮肤真菌有抑制作用。此外，尚有增进食欲等作用。艾叶油有镇咳、祛痰、平喘、抑菌、镇静、抗休克等作用。\n" +
                "\n" +
                "【药理研究】具有抗菌作用；能显著增强网状内皮细胞的吞噬功能；平喘、镇咳、祛痰；抗过敏性休克；抑制心肌收缩，抗血凝和高强度抑制血小板聚集；能明显延长戊巴比妥钠睡眠时间。尚具有利胆、兴奋子宫作用。\n" +
                "\n" +
                "【化学成分】含挥发油0.2%～0.33%，尚含β-谷甾醇、豆甾醇、α-香树脂、β-香树脂、无羁萜、柑橘素、槲皮素与4个桉烷衍生物。另含2-甲基丁醇、艾醇、龙脑、顺式香苇醇、优葛缕酮、α-侧柏烯、甲基丁香油酚、魁蒿内酯等成分。\n" +
                "\n" +
                "【使用禁忌】阴虚血热者及宿有失血病者慎用。\n" +
                "\n" +
                "【配伍药方】①治产后泻血不止：干艾叶15克(炙熟)，老生姜15克；浓煎汤。一服便止。(《食疗本草》)\n" +
                "\n" +
                "②治妊娠卒胎动不安，或腰痛，或胎转抢心，或下血不止：艾叶一鸡子大，以酒四升，煮取二升，分为二服。(《肘后方》)\n" +
                "\n" +
                "③治妊娠心气痛：艾叶、茴香、川楝子(俱炒)等分。醋煎服。(《卫生易简方》)\n" +
                "\n" +
                "④治转筋吐泻：艾叶、木瓜各15克，盐6克。水盅半，煎一盅，待冷饮。(《卫生易简方》)\n" +
                "\n" +
                "⑤治癣：醋煎艾叶涂之。(《千金要方》)");
        chineseMedicineDao.insert(medicine1);
        ChineseMedicine medicine2=new ChineseMedicine();
        medicine2.setName("矮地茶");
        medicine2.setSortType("A");
        medicine2.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=3bccf2e687b1cb132a643441bc3d3d2b/f9dcd100baa1cd117defeb29b312c8fcc3ce2d59.jpg");
        medicine2.setData("【中药名】矮地茶 aidicha\n" +
                "\n" +
                "【别名】紫金牛、叶下红、叶底红、老勿大、平地木、矮脚草。\n" +
                "\n" +
                "【英文名】Ardisiae Japonicae Herba\n" +
                "\n" +
                "【药用部位】紫金牛科植物紫金牛Ardisia japonica (Hornst.) Bl.的全株。\n" +
                "\n" +
                "【植物形态】常绿半灌木。根茎匍匐状。茎单一，枝及花序有褐色柔毛；叶对生，通常3～4叶集生茎梢，呈轮生状，纸质，椭圆形，顶端尖，基部楔形，边缘有尖锯齿，上面绿色，有光泽，下面淡紫色，两面疏生腺点。花着生于茎梢或顶端叶腋，2～6朵集成伞形；萼片、花冠裂片及花药背部均有腺点；萼片卵形、急尖；花瓣长卵形，白色或淡红色；雄蕊5枚；核果球形，熟时红色，有黑色腺点。花期7～8月，果期8～11月。\n" +
                "\n" +
                "【产地分布】生于林下、谷地、溪边阴湿处；分布于长江流域以南各地。\n" +
                "\n" +
                "【采收加工】全年均可采，挖出全株，洗净，晒干。\n" +
                "\n" +
                "【药材性状】根茎匍匐状。茎圆柱形或稍扁，表面棕红色，有细纵纹和叶痕；质脆，易折断，断面淡红棕色，髓白色。叶互生，常成对或3～7片集生茎顶；叶片略卷曲或破碎，完整叶展开后呈椭圆形，嫩叶表面被腺毛，老叶毛稀少，灰绿色、棕红色或棕褐色，近革质，顶端尖，基部楔形，边缘有细锯齿，网状叶脉明显或微凸，中脉有毛。茎端偶见花梗及暗红色球形果实。气微香，味微涩。\n" +
                "\n" +
                "【性味归经】性平，味苦、辛。归肺经、肝经。\n" +
                "\n" +
                "【功效与作用】镇咳、祛痰、活血、利尿、解毒。属化痰止咳平喘分类下的止咳平喘药。\n" +
                "\n" +
                "【临床应用】用量9～12克，煎服；或捣汁。外用：捣敷。用治慢性气管炎、肺结核咳嗽咯血、吐血、脱力劳伤、筋骨酸痛、肝炎、痢疾、急慢性肾炎、高血压、疝气、肿毒。\n" +
                "\n" +
                "【药理研究】主要具有止咳、祛痰平喘、抗菌与抗病毒作用。另外，还有降低大鼠气管-肺组织耗氧的作用。\n" +
                "\n" +
                "【化学成分】主含挥发油。去油后的残渣分离得镇咳有效成分矮茶素1号，即岩白菜素。另含紫金牛酚I、紫金牛酚Ⅱ、紫金牛素、岩白菜内酯、槲皮苷等成分。\n" +
                "\n" +
                "【使用禁忌】少数患者服用本品或有胃脘部不适等消化反应。\n" +
                "\n" +
                "【配伍药方】①治支气管炎：矮地茶20克，六月雪、肺经草各10克。每日1剂，水煎分2次服。(《中国民族药志》)\n" +
                "\n" +
                "②治慢性支气管炎：矮地茶12克，胡颓子叶、鱼腥草各15克，桔梗6克。水煎3次分服，每日1剂。(《全国中草药汇编》)\n" +
                "\n" +
                "③治肺痈：矮地茶30克，鱼腥草30克。水煎，2次分服。(《江西民间草药》)\n" +
                "\n" +
                "④治急性黄疸型肝炎：矮地茶、阴行草、车前草各30克，白茅根15克。水煎服。(《安徽中草药》)\n" +
                "\n" +
                "⑤治肾炎浮肿，尿血尿少：矮地茶、车前草、葎草、鬼针草各9克。水煎服。(《安徽中草药》)\n" +
                "\n" +
                "⑥治白带：矮地茶30克，白扁豆、椿根白皮各12克，煎服。(《安徽中草药》)");
        chineseMedicineDao.insert(medicine2);

        ChineseMedicine medicine3=new ChineseMedicine();
        medicine3.setName("阿魏");
        medicine3.setSortType("A");
        medicine3.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=891038c6262dd42a4b0409f9625230d0/314e251f95cad1c8f9bc91887c3e6709c93d511d.jpg");
        medicine3.setData("【中药名】阿魏 awei\n" +
                "\n" +
                "【别名】熏渠、魏去疾、哈昔泥、五彩魏、臭阿魏。\n" +
                "\n" +
                "【英文名】Ferulae Resina\n" +
                "\n" +
                "【来源】伞形科植物新疆阿魏Ferula sinkiangensisK. M. Shen或阜康阿魏Ferula fukanensis K.M.Shen的树脂。\n" +
                "\n" +
                "【植物形态】多年生草本，高05～1.5米，全株具强烈的葱蒜样气味。根粗大，圆锥形。茎粗壮，被柔毛，自基部分枝并形成圆锥形。基部分枝互生，上部分枝轮生。叶片灰绿色，三角状卵形。三出三次羽状全裂，顶裂片宽椭圆形，先端具齿或浅裂，基部下延，上面被疏毛，下面密生白柔毛。顶生伞形花序近无柄，侧生伞形花序(1～)2～4，对生或互生，花序梗延长，超出顶生花序，伞形花序直径8～12厘米，无苞片，伞幅5～25，被柔毛。小苞片宽披针形，早落。花瓣背面被柔毛。柱基矮圆锥形，基部扩大，边缘波状。果实椭圆形，长10～12毫米，宽5～6毫米，疏被柔毛，棱间油管3～4个，合生面12～14个。花果期4～6月。\n" +
                "\n" +
                "【产地分布】生于海拔800～900米的沙漠带砾石的黏质土壤上。分布于新疆西部。\n" +
                "\n" +
                "【采收加工】春末夏初盛花期至初果期，分次由茎上部往下斜割，收集渗出的乳状树脂，阴干。\n" +
                "\n" +
                "【药材性状】呈不规则的块状和脂膏状。颜色深浅不一，表面蜡黄色至棕黄色。块状者体轻，质地似蜡，断面稍有孔隙；新鲜切面颜色较浅，放置后色渐深。脂膏状者黏稠，灰白色。具强烈而持久的蒜样特异臭气，味辛辣，嚼之有灼烧感。\n" +
                "\n" +
                "【性味归经】性温，味苦、辛。归脾经、胃经。\n" +
                "\n" +
                "【功效与作用】消积，化瘕，散痞，杀虫。属理气药。\n" +
                "\n" +
                "【临床应用】用量1～1.5g，内服：入丸、散。外用：适量，熬制药膏或研末入膏药内敷贴。 用于肉食积滞，瘀血瘾瘕，腹中痞块，虫积腹痛。\n" +
                "\n" +
                "【主要成分】含挥发油(20.74%)。还含阿魏酸酯和阿魏酸。\n" +
                "\n" +
                "【使用禁忌】脾胃虚弱及孕妇忌服。\n" +
                "\n");
        chineseMedicineDao.insert(medicine3);
        ChineseMedicine medicine4=new ChineseMedicine();
        medicine4.setName("桉叶油");
        medicine4.setSortType("A");
        medicine4.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544536675&di=d8ebb279500b2e044fc25da838dbcdef&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.csc86.com%2Fproduct%2F2014%2F12%2F12%2F201412126809072.jpeg");
        medicine4.setData("【药名】桉油 ān yó；u\n" +
                "\n" +
                "【别名】桉油、蓝桉叶油、尤加利油。\n" +
                "\n" +
                "【英文名】oil of eucalyptus.\n" +
                "\n" +
                "【来源】桃金娘科植物蓝桉或同属其他植物的叶经水蒸气蒸馏得到的挥发油。\n" +
                "\n" +
                "【植物形态】大乔木，高达十余米。树皮常片状剥落而呈淡蓝灰色；枝略四棱形，有腺点，棱上具窄翼。叶二型：老树着生正常叶，叶片镰状披针形，先端长渐尖，基部宽楔形且略偏斜；幼株及新枝着生异常叶，单叶对生，叶片椭圆状卵形，无柄抱茎，先端短尖，基部浅心形；两种叶下面均密披白粉而呈绿灰色，两面有明显腺点。花通常单生叶腋或2~3朵聚生，无梗或有极短而扁平的梗；萼筒有棱及小瘤体，具蓝白色蜡被；花瓣与萼片合生成一帽状体，淡黄白色，雄蕊多数，数列分离；花柱较粗大。蒴果杯状，有4棱及不明显瘤体或沟纹。\n" +
                "\n" +
                "【生境分布】多为栽培。分布于福建、广东、广西、云南等地。\n" +
                "\n" +
                "【采收加工】秋季采叶，用水蒸气蒸馏，所得挥发油用乙醚萃取，用无水硫酸钠脱水后回收乙醚，即得。\n" +
                "\n" +
                "【药材性状】无色或微黄色澄清液体；有特异芳香气，微似樟脑，味辛、凉；贮存日久，色稍变深。本品在70%乙醇中易溶。\n" +
                "\n" +
                "【性味归经】味辛、苦，性平。\n" +
                "\n" +
                "【功效与作用】疏风解热、祛湿解毒。属辛凉解表药，叶水煎剂体外对金黄色葡萄球菌、肺炎球菌、绿脓杆菌、大肠杆菌、痢疾杆菌等均有较强抑菌作用，其抗菌作用与所含的没食子酸等有关。蓝桉醛对致癌性启动基因Eb病毒活化具有较强抑制活性。蓝桉中所分得的Ea-Ej等10种成分具抗炎活性，叶水煎剂浸泡兔耳Ⅱ度烫伤面，可使烫伤邻伤组织炎症减轻，局部坏死减轻。桉叶油10%~ 20%混悬液具局部麻醉作用。此外，还具抗氧化作用。\n" +
                "\n" +
                "【临床应用】用量9~15克；外用适量。用治感冒、流感、肠炎、腹泻、皮肤瘙痒、神经痛，并可治烧伤、除蚊虫。\n" +
                "\n" +
                "【主要成分】含挥发油，桉叶油中主成分为：&beta；-桉叶素、&alpha；，&beta；，Y-松油醇、d，&beta；-蒎烯、醋酸松油脂、香橙烯。此外，尚含枯茗醛等多种成分。又含芦丁、槲皮素、槲皮苷、桉树素、鞣质、树脂及苦味质等。\n" +
                "\n" +
                "【使用禁忌】桉叶油对消化道粘膜有刺激性，消化道炎症、溃疡患者慎用。");
        chineseMedicineDao.insert(medicine4);

        ChineseMedicine medicine5=new ChineseMedicine();
        medicine5.setName("儿茶");
        medicine5.setSortType("E");
        medicine5.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=390bb49ab9389b502cf2e800e45c8eb8/9d82d158ccbf6c818fd0826cbc3eb13532fa404a.jpg");
        medicine5.setData("【中药名】儿茶 ercha\n" +
                "\n" +
                "【别名】孩儿茶、西谢、儿茶膏、黑儿茶。\n" +
                "\n" +
                "【英文名】Catechu\n" +
                "\n" +
                "【药用部位】豆科植物儿茶Acacia catechu (L.f.)Willd.的去皮枝、干的煎膏。\n" +
                "\n" +
                "【植物形态】落叶乔木。高6～13米。树皮棕色，呈条状薄片，剥离而不脱离。2回偶数羽状复叶，互生，长4～20厘米，叶轴上被灰色柔毛，着生羽片10～20对，羽片长2～4厘米，每羽片上具小叶片20～50对，小叶线形，长3～6毫米，两面被疏毛。总状花序腋生，萼筒状，先端5裂，有疏毛，花瓣5，黄色或白色，为萼长的2～3倍。雄蕊多数，伸出花冠外；雌蕊1枚，子房上位，长卵形。荚果扁而薄，紫褐色，有光泽。花期8～9月，果期10～11月。\n" +
                "\n" +
                "【产地分布】多生于村旁、路边，或为栽培。主要分布于云南西双版纳地区，现广东、广西、福建有栽培。\n" +
                "\n" +
                "【采收加工】一般在12月至次年3月采集儿茶枝和树干，剥去外皮，将心材砍成碎片，加水煎熬，过滤，滤液浓缩成糖浆状，稍冷，倾于特制的模型中，阴干。\n" +
                "\n" +
                "【药材性状】方形或不规则块状，大小不一。表面棕褐色或黑褐色，光滑而稍有光泽。质硬，易碎，断面不整齐，具光泽，有细孔，遇潮有黏性。无臭，味涩、苦，略回甜。\n" +
                "\n" +
                "【性味归经】性微寒，味苦、涩。归肺经、心经。\n" +
                "\n" +
                "【功效与作用】收湿、生肌、敛疮、止血。属止血药下属分类的收敛止血药。\n" +
                "\n" +
                "【临床应用】用量1～3克，包煎，多入丸散；外用适量，研末撒或调敷。用治溃疡不敛、湿疹、口疮、跌扑伤痛、外伤出血。\n" +
                "\n" +
                "【药理研究】有保肝利胆、调节免疫系统的功能、抗病原微生物、降低血糖、抑制胃肠道运动、抗腹泻作用；抗血小板聚集和抗血栓形成，降低血清胆固醇含量，降低组织耗氧量，尤其是心肌耗氧量，降低血压，调节心血管系统功能；儿茶有一定镇痛、抗放射、升高白细胞和抗肿瘤作用，并因能抑制瘤细胞与纤微蛋白粘连而阻止瘤细胞扩散。药理实验表明，水溶液能抑制家兔十二指肠及小肠的蠕动，且能促进盲肠的逆蠕动，而有止泻作用。水煎剂对金黄色葡萄球菌、白喉杆菌、变形杆菌、福氏痢疾杆菌及伤寒杆菌均有抑制作用，对常见致病性皮肤真菌也有抑制作用。\n" +
                "\n" +
                "【化学成分】含儿茶素、表儿茶素、儿茶鞣质、黏液质、脂肪油、树胶、蜡、儿茶鞣酸、儿茶钩藤碱、二氢柯楠因碱、右旋阿夫儿茶精、二氢山柰酚、双聚原矢车菊素、左旋儿茶精及消旋儿茶精、儿茶红等成分。\n" +
                "\n" +
                "【使用禁忌】寒湿之证禁服。\n" +
                "\n" +
                "【配伍药方】①治牙疳，口疮：孩儿茶、硼砂等分，为末搽。(《纲目》)\n" +
                "\n" +
                "②治走马牙疳：孩儿茶、雄黄、贝母等分。为末，米泔漱净搽之。(《纲目》引《积德堂经验方》)\n" +
                "\n" +
                "③治鼻渊流水：孩儿茶末吹之。(《纲目》引《本草权度》)\n" +
                "\n" +
                "④治下疳阴疮：孩儿茶3克，真珠0.3克，片脑0.15克。为末敷。(《纲目》引《纂要奇方》)\n" +
                "\n" +
                "⑤治痔疮：轻粉、冰片、孩儿茶各等量，以10倍量之香油调配成乳剂。装瓶备用，用时以脱脂棉棒浸沾药物，置肛管内。[《黑龙江医药》1972，(2)：轻冰乳剂]\n" +
                "\n" +
                "⑥治咳嗽：儿茶60克，细辛12克，猪胆1个。前二味药共研末，取胆汁炼熟，三味药共为丸，每丸重3克。每日4次，每次1丸，空腹含化。(《全国中草药新医疗法展览会资料选编》)\n" +
                "\n" +
                "⑦治疗皮肤湿疹，溃疡，分泌物多：儿茶9克，轻粉6克，冰片0.9克，龙骨9克。研末水调外敷。(《中药临床应用》儿轻散)");
        chineseMedicineDao.insert(medicine5);

        ChineseMedicine medicine6=new ChineseMedicine();
        medicine6.setName("鹅不食草");
        medicine6.setSortType("E");
        medicine6.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=ec015b73a7efce1bfe26c098ce3898bb/b2de9c82d158ccbf106fadbf19d8bc3eb0354167.jpg");
        medicine6.setData("【中药名】鹅不食草 ebushicao\n" +
                "\n" +
                "【别名】石胡荽、地胡椒、三芽戟、鸡肠草、野园荽、通天窍。\n" +
                "\n" +
                "【英文名】Small Centipeda Herb。\n" +
                "\n" +
                "【药用部位】菊科植物鹅不食草Centipeda minima (L.)A.Braun et Aschers.的全草。\n" +
                "\n" +
                "【植物形态】一年生匍匐状草本，微臭，揉碎有辛辣味。茎细，基部分枝很多，枝匍匐，着地生根，无毛或略有细柔毛。叶互生；叶片小，倒卵状披针形，先端钝，基部楔形，边缘有疏齿，无柄。头状花序小，扁球形，无柄，单生叶腋；花黄色，外围为雌花，有极细的花管，中央为两性花，花管具4裂片；雄蕊4，花药基部钝圆；子房下位，柱头2裂。瘦果四棱形，棱上有毛。花期4～9月，果期5～10月。\n" +
                "\n" +
                "【产地分布】生于稻田、阴湿山地及路旁或湿润草地。分布于江苏、浙江、安徽、广东等地。\n" +
                "\n" +
                "【采收加工】夏季开花时采集，鲜用或晒干用。\n" +
                "\n" +
                "【药材性状】扭集成团。须根纤细，淡黄色。茎细，质脆，断面黄白色。叶小，多皱缩或破碎，完整者展平后呈匙形。表面灰绿色或棕褐色，边缘有3～5个锯齿。头状花序黄色或黄褐色。气微香，久闻有刺激感，味苦、微辛。\n" +
                "\n" +
                "【性味归经】性温，味辛。归肺经、肝经。\n" +
                "\n" +
                "【功效与作用】通鼻窍、止咳。属解表药下分类的辛温解表药。\n" +
                "\n" +
                "【临床应用】用量3～10克；鲜品加倍，捣汁服可用至60克；外用适量。用治外感风寒之鼻塞、流涕、头痛之症；鼻塞不通；寒痰咳喘、顿咳；头风痛、牙痛、外伤疼痛、风湿痹痛、疮痈肿痛、痧症及泻痢腹痛等多种痛症及癣疮瘙痒。对胃肠道黏膜有一定刺激作用，可引起腹痛、胃脘不适、恶心、呕吐等消化道症状。饭后1小时服药，可减轻其不良反应。\n" +
                "\n" +
                "【药理研究】具有止咳、祛痰、平喘、抗过敏、抗突变及抗肿瘤等作用；对革兰氏阳性、革兰氏阴性球菌、杆菌及某些病毒有一定的抑制作用，对金黄色葡萄球菌也具有抑制作用。\n" +
                "\n" +
                "【化学成分】全草含甾醇类成分，如蒲公英醇、蒲公英甾醇及三萜皂苷类成分。\n" +
                "\n" +
                "【使用禁忌】气虚胃弱者忌用，胃溃疡及胃炎患者慎用。\n" +
                "\n" +
                "【配伍药方】①治伤风头痛、鼻塞：鹅不食草(鲜或干均可)搓揉，嗅其气，即打喷嚏，每日2次。(《贵阳民间药草》)\n" +
                "\n" +
                "②治鼻炎，鼻窦炎，鼻息肉，鼻出血：鹅不食草、辛夷花各3克。研末吹入鼻孔，每日2次;或加凡士林20克，做成膏状涂鼻。(《青岛中草药手册》)\n" +
                "\n" +
                "③治阿米巴痢疾：鹅不食草、乌韭根各15克。水煎服，每日1剂，血多者加仙鹤草15克。(《江西草药》)\n" +
                "\n" +
                "④治膀胱结石：鹅不食草60克。洗净捣汁，加白糖少许，1次服完。(《贵阳民间草药》)\n" +
                "\n" +
                "⑤治痔疮：鹅不食草50克，无花果叶15～18克。煎水，先熏过再洗。(《贵阳民间药草》)\n" +
                "\n" +
                "⑥治黄疸型肝炎：鹅不食草9克，茵陈24克。水煎服。(《河北中草药》)\n" +
                "\n" +
                "⑦治支气管哮喘：鹅不食草、瓜蒌、莱菔子各9克。煎服。(《安徽中草药》)");
        chineseMedicineDao.insert(medicine6);
        ChineseMedicine medicine7=new ChineseMedicine();
        medicine7.setName("藕节");
        medicine7.setSortType("E");
        medicine7.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544541279&di=c47b14afe50c7d44acc53559040bbd07&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Ff7246b600c338744af83e1515a0fd9f9d72aa05d.jpg");
        medicine7.setData("【中药名】藕节 oujie\n" +
                "\n" +
                "【别名】光藕节、藕节疤。\n" +
                "\n" +
                "【英文名】Nelumbinis Rhizomatis Nodus。\n" +
                "\n" +
                "【来源】睡莲科植物莲Nelumbo nucifera Gaertn.的根茎节部。\n" +
                "\n" +
                "【植物形态】多年生水生草本。叶片圆盾形，全缘，稍呈波状，上面光滑，具白粉；叶柄着生于叶背中央，表面散生刺毛。花梗与叶柄等高或略高；花大，单一顶生，粉红色或白色，芳香；萼4～5，绿色，小型，早落；花瓣多数，长圆状椭圆形至倒卵形，先端钝，由外向内逐渐变小；雄蕊多数，早落；花药线形，黄色，药隔先端呈一棒状附属物，花丝细长，着生于花托内，花托倒圆锥形，顶部平，有小孔20～30个，呈海绵状，俗称“莲蓬”。坚果椭圆形或卵形，果皮坚硬、革质；内有种子1枚，俗称“莲子”。花期7～8月，果期9～10月。\n" +
                "\n" +
                "【产地分布】全国大部分地区均产。主产于湖南、福建、江苏等地池沼湖塘中。\n" +
                "\n" +
                "【采收加工】秋、冬季挖藕时，切下节部，洗净，晒干。\n" +
                "\n" +
                "【药材性状】短圆柱形。长2～4厘米，直径2厘米。表面灰黄色至灰棕色，中央节稍膨大，上有多数须根痕，有时可见暗红棕色的鳞叶残基；其横断面中央有较小的圆孔，周围有大孔7～9个。体轻，节部坚硬，难折断。气无，味微甘涩。\n" +
                "\n" +
                "【性味归经】性平，味甘、涩。归肝经、肺经、胃经。\n" +
                "\n" +
                "【功效与作用】收敛止血，化瘀。属止血药下属分类的收敛止血药。\n" +
                "\n" +
                "【临床应用】用量9～15克，煎汤内服，10～30克；鲜用捣汁，可用60克左右取汁冲服；或入散剂。用治胃中瘀热，吐血、咯血、鼻血、尿血、崩漏。\n" +
                "\n" +
                "【药理研究】鲜用能清热凉血，煅炭消瘀止血，收敛作用较强，能缩短出血时间。\n" +
                "\n" +
                "【化学成分】含鞣质、天门冬素、丙氨酸、天冬酰胺、3-表白桦脂酸等成分。\n" +
                "\n" +
                "【使用禁忌】忌铁器。\n" +
                "\n" +
                "【配伍药方】①治卒暴吐血：藕节七个，荷叶顶七个。上同蜜擂细，水二盅，煎八分，去渣温服，或研末蜜调下。(《圣惠方》双荷散)\n" +
                "\n" +
                "②治落马后心胸有积血，唾吐不止：干藕节150克。上件药捣细罗为散，每服以温酒调下9克，日三四服。(《圣惠方》)\n" +
                "\n" +
                "③治吐血、咯血、衄血：用藕节捣汁服之。(《卫生易简方》)\n" +
                "\n" +
                "④治吐衄不止：藕汁、生地黄汁、大蓟汁各三合，生蜜五匙，和匀，每服一小盅，不拘时候。(《赤水玄珠》)\n" +
                "\n" +
                "⑤治大便下血：藕节晒干，每用七个，和白蜜七茶匙，水二碗，煎一碗服。(《百一选方》)");
        chineseMedicineDao.insert(medicine7);
        ChineseMedicine medicine8=new ChineseMedicine();
        medicine8.setName("莪术");
        medicine8.setSortType("E");
        medicine8.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=ed8bcd02003b5bb5aada28ac57babe5c/c83d70cf3bc79f3d3d632899b9a1cd11738b299d.jpg");
        medicine8.setData("【中药名】莪术 eshu\n" +
                "\n" +
                "【别名】文莪、蓬术、羌七、广术、黑心姜、文术。\n" +
                "\n" +
                "【英文名】curcumae rhizoma。\n" +
                "\n" +
                "【来源】姜科植物蓬莪术Curcuma phaeocaulis Val.、广西莪术Curcuma kwangsiensis S.G.Lee et C.F.Liang或温郁金Curcuma wenyujin Y.H.Chen et C.Ling的干燥根茎。\n" +
                "\n" +
                "【植物形态】多年生草本。根细长，末端膨大成肉质纺锤形块根，断面黄绿色或近白色。主根茎圆柱状，侧根茎指状，根茎断面淡蓝色、淡绿色、黄绿色至黄色。叶直立，叶片4～7，2列；叶片长圆状椭圆形或长圆状披针形，先端渐尖，基部渐狭，下面疏被短柔毛，叶片上面沿中脉两侧有紫色带直达基部，中脉绿色。穗状花序从根茎上抽出，圆柱状，苞片20多枚；花冠裂片红色；退化雄蕊较唇瓣小，唇瓣黄色，倒卵状。蒴果。\n" +
                "\n" +
                "【产地分布】生于山坡、村旁或林下半阴湿地，亦有栽培。分布于浙江、江西、福建、台湾、湖南、广东、广西、四川、云南等地。\n" +
                "\n" +
                "【采收加工】冬季茎叶枯萎后采挖，洗净，蒸或煮至透心，晒干或低温干燥后除去须根及杂质。\n" +
                "\n" +
                "【药材性状】蓬莪术：卵形、长卵形、圆锥形或长纺锤形，顶端多钝尖，基部钝圆。表面灰黄色至灰棕色，上部环节凸起，有圆形微凹的须根痕或有残留的须根，有的两侧各有1列下陷的芽痕和类圆形的侧生根茎痕，有的可见刀削痕。体重，质坚实，断面灰褐色至蓝褐色，蜡样，常附有灰棕色粉末，皮层与中柱易分离，内皮层环纹棕褐色。气微香，味微苦而辛。广西莪术：环节稍突起，断面黄棕色至棕色，常附有淡黄色粉末，内皮层环纹黄白色。温莪术：断面黄棕色至棕褐色，常附有淡黄色至黄棕色粉末。气香或微香。\n" +
                "\n" +
                "【性味归经】性温，味辛、苦。归脾经、肝经。\n" +
                "\n" +
                "【功效与作用】行气破血、消积止痛。属于活血化瘀药下属的破血消癥药。\n" +
                "\n" +
                "【临床应用】用量6～9克，煎服；或入丸、散；外用：适量，煎汤洗；或研末调敷。用于治疗癥瘕痞块、瘀血经闭、食积胀痛、早期宫颈癌。\n" +
                "\n" +
                "【药理研究】具有抗肿瘤、抗菌、升高白细胞、活血化瘀的作用；对胃肠平滑肌低浓度紧张、高浓度舒张；具有保肝作用；对急性肾功能衰竭有改善作用；抑制血小板聚集及抗血栓形成；具有抗炎等作用。临床用莪术油注射剂静脉滴注治疗血栓闭塞性脉管炎获较好疗效，对婴儿RSV肺炎有较好疗效且无副作用。油制剂具抗肿瘤作用，治疗卵巢癌、恶性淋巴癌、肺癌、肝癌有一定疗效。\n" +
                "\n" +
                "【化学成分】本品主要含挥发油、黄酮类、萜类等化学成分。主要有莪术呋喃烯酮、莪术醇、龙脑、大栊牛儿酮、樟脑、姜黄烯、姜黄酮、莪术二酮、桂莪术内酯、胡萝卜苷、β-谷固醇、棕榈酸等成分。\n" +
                "\n" +
                "【使用禁忌】孕妇禁用。\n" +
                "\n" +
                "【配伍药方】①治吞酸吐酸：莪术30克，川黄连15克(吴茱萸15克同煮，去吴茱萸)。水煎服。(《丹溪心法》)\n" +
                "\n" +
                "②治妇人血积血块，经闭：莪术、三棱各30克，熟大黄30克。丸如绿豆大，每服一二十丸，白汤下。(《慎斋遗书》)\n" +
                "\n" +
                "③治妇人血气攻心，痛不可忍并走注：莪术(油煎乘熟切片)15克，玄胡索0.3克。上为细末。每服1.5克，食前淡醋汤调下。(《鸡峰普济方》玄胡索散)\n" +
                "\n" +
                "④治伤扑疼痛：莪术、白僵蚕、苏木各30克，没药15克。为末。每服6克，水煎温服，日三五服。(《博济方》蓬莪散)\n" +
                "\n" +
                "⑤治漆疮：以莪术、贯众煎汤洗之。(《普济方》)");
        chineseMedicineDao.insert(medicine8);
        ChineseMedicine medicine9=new ChineseMedicine();
        medicine9.setName("阿胶");
        medicine9.setSortType("E");
        medicine9.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544541703&di=6dafd76339065dfaf915747a8ca231cc&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3Dcb31291ad3f9d72a0369185ebc434241%2F6159252dd42a2834e5e67b2e51b5c9ea15cebf57.jpg");
        medicine9.setData("【中药名】阿胶 ejiao\n" +
                "\n" +
                "【别名】盆覆胶、驴皮胶。\n" +
                "\n" +
                "【英文名】Asini Corii Colla\n" +
                "\n" +
                "【来源】马科动物驴Equus asinus L.的皮，经煎煮、浓缩制成的固体胶。\n" +
                "\n" +
                "【动物形态】我国北方地区主要役用家畜之一。体型比马小，体重一般200千克左右。头形较长，眼圆，其上生有l对显眼的长耳。颈部长而宽厚，颈背鬃毛短而稀少。躯体匀称，四肢短粗，蹄质坚硬，尾尖端处生有长毛。毛色主要有黑色、栗色、灰色3种。颈背有一条短的深色横沟，嘴部有白色嘴圈，腹部和四肢的内侧白色。\n" +
                "\n" +
                "【产地分布】性情温顺，以麦秸、谷草为食。我国北方地区均有饲养。\n" +
                "\n" +
                "【采收加工】将驴皮漂泡，去毛，切成小块，再漂泡洗净，分次水煎，滤过，合并滤液，用文火浓缩(或加适量黄酒、冰糖、豆油)至稠膏状，冷凝，切块，阴干。\n" +
                "\n" +
                "【药材性状】长方形块状，长约8厘米，宽约4厘米，厚约0.7厘米。表面黑色或黑褐色，光滑，有油润光泽，对光照视呈半透明的棕红色。质坚脆，易碎。断面棕褐色，具玻璃样光泽。气微，味微甘。\n" +
                "\n" +
                "【性味归经】性平，味甘。归肺经、肝经、肾经。\n" +
                "\n" +
                "【功效与作用】补血滋阴、润燥、止血。属补虚药下属分类的补血药。\n" +
                "\n" +
                "【临床应用】用量3～9克，烊化兑服。用于血虚萎黄、眩晕心悸、肌痿无力、心烦不眠、虚风内动、肺燥咳嗽、劳嗽咯血、吐血尿血、便血崩漏、妊娠胎漏。\n" +
                "\n" +
                "【主要成分】主含胶原蛋白，系多肽类物质，经水解后产生分子量不等的蛋白质，最终水解产物为氨基酸。尚含纤维粘连蛋白及糖胺多糖。实验表明，本品具有促进造血、增强免疫、抗辐射和抗休克功能。\n" +
                "\n" +
                "【使用禁忌】脾胃虚弱、消化不良者慎服。");

        chineseMedicineDao.insert(medicine9);

        ChineseMedicine medicine10=new ChineseMedicine();
        medicine10.setName("枇杷叶");
        medicine10.setSortType("P");
        medicine10.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C25%2C760%2C502%3Bc0%3Dbaike92%2C5%2C5%2C92%2C30/sign=71b3d923c33d70cf58b5f04dc5ecfd32/d31b0ef41bd5ad6ed349eff08bcb39dbb6fd3c73.jpg");
        medicine10.setData("【中药名】枇杷叶 pipaye\n" +
                "\n" +
                "【别名】广杷叶、巴叶、芦橘叶。\n" +
                "\n" +
                "【英文名】Eriobotryae Folium\n" +
                "\n" +
                "【药用部位】蔷薇科植物枇杷Eriobotrya japonica (Thunb.) Lindl.的叶。\n" +
                "\n" +
                "【植物形态】常绿小乔木，高3～8米。小枝粗壮，被锈色茸毛。单叶互生，革质；具短柄或近无柄；托叶2片。叶片长椭圆形至倒卵状披针形，先端短尖或渐尖，基部楔形，边缘具疏锯齿，上面深绿色，有光泽，下面密被锈色茸毛，侧脉12～15对。圆锥花序顶生，密被锈色茸毛，分枝粗壮；花密集，苞片被褐色茸毛；萼筒壶形，黄绿色，密被茸毛；花瓣5，白色，倒卵形，花丝基部较粗，略呈三角形；子房下位，外被长茸毛，5室，柱头头状。果为浆果状梨果，卵形、椭圆形或近圆形，黄色或橙色。\n" +
                "\n" +
                "【产地分布】多栽于村边、平地或坡地。分布于江苏、浙江、广东、陕西及长江流域以南各地。\n" +
                "\n" +
                "【采收加工】全年均可采收，多在4～5月采叶，晒至七八成干时扎成小把，再晒干。本品分生用和蜜炙。生用时，用水喷润，切丝，干燥。蜜枇杷叶，取枇杷叶丝，用蜜水拌炒，至放凉后不粘手为度。每100千克枇杷叶丝，用炼蜜20千克。\n" +
                "\n" +
                "【药材性状】长圆形或倒卵形。先端尖，基部楔形，边缘有疏锯齿，近基部全缘。上表面灰绿色、黄棕色或红棕色，较光滑；下表面密被黄色茸毛，主脉于下表面显著突起，侧脉羽状；叶柄极短，被棕黄色茸毛。革质而脆，易折断。无臭，味微苦。\n" +
                "\n" +
                "【性味归经】味苦，性微寒。归肺经、胃经。\n" +
                "\n" +
                "【功效与作用】清肺止咳、降逆止呕。属化痰止咳平喘药下属中的止咳平喘药。\n" +
                "\n" +
                "【临床应用】用量6～9克，煎服。用治肺热咳嗽、气逆喘急、胃热呕逆、烦热口渴。润肺下气止咳逆，宜蜜汁炒用；和胃下气止呕逆，宜姜汁炒用。\n" +
                "\n" +
                "【药理研究】对呼吸中枢有镇静、平喘、镇咳作用。局部应用对角叉菜胶性水肿有强大抑制作用，可显著降低遗传性糖尿病小鼠及正常小鼠的尿糖。临床上试用治肾炎水肿、痤疮等。水煎剂或醋酸乙酯提取物具有祛痰和平喘作用，挥发油有轻度祛痰作用。此外，皂苷也是枇杷叶止咳祛痰的主要成分。醇提物灌胃或局部用药具抗炎作用。此外，还具抗菌、降血糖、抗肿瘤的作用。\n" +
                "\n" +
                "【化学成分】新鲜枇杷叶含挥发油0.045%～0.108%，主成分为橙花叔醇、芳樟醇及其氧化物等。三萜化合物以熊果酸、齐墩果酸或委陵菜酸为母体的衍生物。倍半萜类化合物枇杷苷Ⅰ可作定性鉴别的专属性成分。尚含单环倍半萜苷、苦杏仁苷及有机酸、金合欢醇、β-谷固醇、马斯里酸、枸橼酸等成分。\n" +
                "\n" +
                "【使用禁忌】胃寒呕吐入风寒咳嗽禁服。\n" +
                "\n" +
                "【配伍药方】①治咳嗽，喉中有痰声：枇杷叶15克，川贝1.5克，杏仁6克，陈皮6克。为末，每服3～6克，开水送下。(《滇南本草》)\n" +
                "\n" +
                "②治肺热咳嗽：枇杷叶9克，桑白皮12克，黄芩6克，水煎服。或蜜炙枇杷叶12克，蜜炙桑白皮15克，水煎服。(《陕西中草药》)\n" +
                "\n" +
                "③治风热咳嗽：枇杷叶、苦杏仁、桑白皮、菊花、牛蒡子各9克，煎服。(《安徽中草药》)\n" +
                "\n" +
                "④治肺风咳逆：干枇杷叶30克，芫荽菜、前胡各15～18克，艾叶5片。水煎，冲红糖，早晚顿服。(《天目山药用植物志》)\n" +
                "\n" +
                "⑤治肺燥咳嗽：干枇杷叶(去毛)9克，干桑叶9克，茅根15克，水煎服。(《广西民间常用中草药手册》)\n" +
                "\n" +
                "⑥治百日咳：枇杷叶15克，桑白皮15克，地骨皮9克，甘草3克。水煎服。(江西《草药手册》)\n" +
                "\n" +
                "⑦治慢性支气管炎，咳嗽气喘痰多：枇杷叶、冬桑叶、车前草、天浆壳、天花粉等分。煎服。(《上海常用中草药》)\n" +
                "\n" +
                "⑧治面上生疮：枇杷叶，布擦去毛，炙干，为末，食后茶汤调下6克。(《急救良方》)");


            chineseMedicineDao.insert(medicine10);

            ChineseMedicine medicine11=new ChineseMedicine();
            medicine11.setName("蒲黄");
            medicine11.setSortType("P");
            medicine11.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=afd6b9b75aafa40f28cbc68fca0d682a/a08b87d6277f9e2fdb2e138d1f30e924b899f318.jpg");
            medicine11.setData("【中药名】蒲黄 puhuang\n" +
                    "\n" +
                    "【别名】蒲草黄、蒲花、蒲棒花粉、蒲厘花粉。\n" +
                    "\n" +
                    "【英文名】Cattail Pollen。\n" +
                    "\n" +
                    "【药用部位】香蒲科植物水烛香蒲Typha angustifoliaL.的花粉。\n" +
                    "\n" +
                    "【植物形态】多年沼泽生草本。根茎横走，须根多。叶狭线形，半抱茎。花小，单性，雌雄同株，集合成圆柱状肥厚的穗状花序；雌雄花序离生，雄花序在上部，雌花序在下部，雌、雄花序的花被均退化成鳞片状或茸毛。雄花具雄蕊2～3，毛长于花药，顶端单一或2～5分叉，花粉粒单生；雌花有小苞，匙形，与柱头等长，小苞与花柱均较白。果穗长短变化很大，通常短于雄花序，赭褐色。坚果细小，无槽。花期6～7月，果期7～8月。\n" +
                    "\n" +
                    "【产地分布】生于浅水中。分布于安徽、江苏、浙江等地。\n" +
                    "\n" +
                    "【采收加工】6～7月花刚开时，剪取蒲棒顶端雄花序，晒干，碾研，除去花茎等杂质，所得带雄花的花粉称“草蒲黄”；再经细筛，所得纯花粉，习称“蒲黄”。\n" +
                    "\n" +
                    "【药材性状】黄色粉末，质轻，入水飘浮水面，手捻之有滑润感，易附于手指上。气微，味淡。草蒲黄为蒲黄花粉与花丝、花药的混合物，花丝黄棕色，不光滑。在显微镜下，可见花粉粒单生，类球形，直径24～30微米，表面有似网状雕纹，单萌发孔不甚明显。\n" +
                    "\n" +
                    "【性味归经】性平，味甘。归肝经、心经。\n" +
                    "\n" +
                    "【功效与作用】止血、化瘀通淋。属止血药分类下的化瘀止血药。\n" +
                    "\n" +
                    "【临床应用】用量4.5～9克。外用适量，研末撒或调敷。用治各种出血症、瘀滞痛症，如瘀滞胸痛、胃脘疼痛以及产后瘀痛、痛经；血淋、血痢等。\n" +
                    "\n" +
                    "【药理研究】具有改善心肌微循环、降血脂及抗动脉粥样硬化、促进凝血作用，兴奋子宫平滑肌，对肠道平滑肌有解痉作用，抑制细胞免疫和体液免疫，有抗炎、抗菌、抗过敏、溶血作用。\n" +
                    "\n" +
                    "【化学成分】主要含黄酮及甾类成分。此外，尚含有挥发油、多糖、酸类、香蒲新苷、山柰酚、异鼠李素、柚皮素、山柰酚-3-阿拉伯糖苷、3-谷固醇和烷类等化合物。\n" +
                    "\n" +
                    "【使用禁忌】孕妇忌服。\n" +
                    "\n" +
                    "【配伍药方】①治妇人月经过多，血伤漏下不止：蒲黄(微炒)90克，龙骨75克，艾叶30克。上三味，捣罗为末，炼蜜和丸，梧桐子大。每服二十丸，煎米饮下，艾汤下亦得，日再。(《圣济总录》蒲黄散)\n" +
                    "\n" +
                    "②治血崩：蒲黄、黄芩各30克，荷叶灰15克。为末。每服9克，空心酒调下。(《卫生易简方》)\n" +
                    "\n" +
                    "③治咯血，吐血，唾血及治烦躁：生蒲黄、干荷叶等分。上为末，每服9克，浓煎桑白皮汤，放温调下，食后。(《卫生宝鉴》恩袍散)\n" +
                    "\n" +
                    "④治鼻衄，出血过多，昏迷欲死，诸药不效：生蒲黄6克，青黛1.5克，生藕汁调作一服，即验。(《朱氏集验方》)");

             chineseMedicineDao.insert(medicine11);

             ChineseMedicine medicine12=new ChineseMedicine();
             medicine12.setName("啤酒花");
             medicine12.setSortType("P");
             medicine12.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C1%2C1024%2C676%3Bc0%3Dbaike116%2C5%2C5%2C116%2C38/sign=9947c970f3edab64603d1780ca0683f2/bba1cd11728b4710845a4a77cbcec3fdfc032317.jpg");
             medicine12.setData("【中药名】啤酒花 pijiuhua\n" +
                     "\n" +
                     "【别名】忽布、香蛇麻、野酒花。\n" +
                     "\n" +
                     "【英文名】European Hop Flower\n" +
                     "\n" +
                     "【药用部位】桑科植物啤酒花Humulus lupulus L.的雌花序。\n" +
                     "\n" +
                     "【植物形态】多年生缠绕草本，长可达10米，全株有倒钩刺。茎枝和叶柄密生细毛。叶纸质，对生，卵形，3裂或不裂，宽4～8厘米，基部心形或圆形，边缘有粗锯齿，上面密生小刺毛，下面有疏毛和黄色小油点，叶柄长不超过叶片。花单性，雌雄异株，雄花排列成圆锥花序，花被片和雄蕊各5枚，雌花每2朵生一苞片于腋部，苞片覆瓦状排列成一近圆形的穗状花序。果穗呈球果状，宿存苞片膜质且增大，有油点，近无毛，内包扁平的瘦果1～2个。\n" +
                     "\n" +
                     "【产地分布】多为栽培，分布于东北、华北及山东。\n" +
                     "\n" +
                     "【采收加工】夏、秋季花盛开时采摘雌花序，鲜用或晒干。\n" +
                     "\n" +
                     "【药材性状】松球形，长3～6厘米，黄绿色，有多数叶状苞片，苞片椭圆形，覆瓦状排列，饮片内侧基部生有l对雌花，无花被，花柱2裂，具大量黄粉。体轻，质脆。气香，味苦。\n" +
                     "\n" +
                     "【性味归经】性微凉，味苦；无毒。归肝经、胃经。\n" +
                     "\n" +
                     "【功效与作用】健胃消食、利尿安神。属消食药。\n" +
                     "\n" +
                     "【临床应用】用量1.5～5克，内服煎汤或泡水当茶饮，治疗消化不良、腹胀、浮肿、膀胱炎、肺结核、失眠。\n" +
                     "\n" +
                     "【药理研究】具有抗病原微生物、镇静催眠、雌性激素样等作用。浸膏、蛇麻酮、氢草酮试管内能抑制多种革兰氏阳性细菌的生长，对致病性、非致病性真菌及放线菌抑制效力极弱；蛇麻酮、氢草酮具有镇静作用；树脂中的β-酸具有较强的雌性激素样作用；酿造啤酒时加入本品，不仅由于其挥发油具有香味，而且有防腐作用；乙醇提取物对离体兔空肠、豚鼠十二指肠、大鼠子宫平滑肌有强大的解痉作用，并能拮抗乙酰胆碱、氯化钡的致痉作用。毒性：与本品接触者易发生皮炎，主要由鲜花粉所致；蛇麻酮能引起胃肠道反应，如食欲不振、烧灼感、恶心、腹痛、呕吐、腹泻等；动物争性毒性实验，于死亡前有兴奋及抽搐现象，死于呼吸困难，肝肾肺有明显的充血或出血现象。\n" +
                     "\n" +
                     "【化学成分】含氢草酮、蛇麻酮、鞣质、挥发油、葎草二烯酮、葎草烯酮-Ⅱ、γ-去二氢菖蒲烯、紫云英苷、异槲皮苷、芸香苷、山柰酚-3-鼠李糖基二葡萄糖苷、槲皮素-3-鼠李糖二葡萄糖苷等成分。\n" +
                     "\n" +
                     "【使用禁忌】花粉过敏者忌服。\n" +
                     "\n" +
                     "【配伍药方】①治消化不良，腹胀：啤酒花、神曲各9克，土木香6克。水煎服。(《新疆中草药》)\n" +
                     "\n" +
                     "②治胸腹胀满：啤酒花、枳壳、木香、炒山楂各15克。水煎服。(《青岛中草药手册》)\n" +
                     "\n" +
                     "③治肺结核，膀胱炎：牛蒡根、车前草、板蓝根各9克，啤酒花6克，黄芩15克。水煎服。(《新疆中草药》)\n" +
                     "\n" +
                     "④治气管炎：啤酒花1.5～3克，泡茶饮。或啤酒花根、贝母、桔梗、紫菀各9克，水煎服。(《青岛中草药手册》)\n" +
                     "\n" +
                     "⑤治失眠：啤酒花15克，酸枣仁9克，合欢花15克，远志9克，煎服。(《青岛中草药手册》)");

             chineseMedicineDao.insert(medicine12);

             ChineseMedicine medicine13=new ChineseMedicine();
             medicine13.setName("盘龙参");
             medicine13.setSortType("P");
             medicine13.setMedicineImageUrl("http://img011.hc360.cn/y1/M03/DA/89/wKhQc1R1HEuEGTGHAAAAAExChls541.jpg");
             medicine13.setData("【中药名】盘龙参 panlongshen\n" +
                     "\n" +
                     "【别名】猪辽参、猪鞭草、龙缠柱、海珠草。\n" +
                     "\n" +
                     "【英文名】Herba Spiranthis Sinensis\n" +
                     "\n" +
                     "【药用部位】兰科植物绶草Spiranthes sinensis(Pers.)Ames的根和全草。\n" +
                     "\n" +
                     "【植物形态】陆生植物，高15～50厘米。茎直立，基部簇生数条粗厚、肉质的根，近基部生2～4枚叶。叶条状倒披针形或条形，长10～20厘米，宽4～10毫米。花序顶生，长10～20厘米，具多数密生的小花，似穗状，花白色或淡红色，螺旋状排列，花苞片卵形，长渐尖，中萼片条形，先端钝，长约5毫米，宽约1.3毫米，侧萼片等长，较狭，花瓣和中萼片等长但较薄，先端极钝，唇瓣近长圆形，长4～5毫米，宽约2.5毫米，先端极钝，伸展，基部至中部边缘全缘，中部以上呈强烈的皱波状啮齿，在中部以上的表面具皱波状长硬毛，基部稍凹陷，呈浅囊状，囊内具2枚突起。\n" +
                     "\n" +
                     "【产地分布】生于海拔400～3500米的山坡林下、灌丛下、草地、路边或沟边草丛中。分布几遍全国。\n" +
                     "\n" +
                     "【采收加工】夏、秋采收，鲜用或晒干。\n" +
                     "\n" +
                     "【药材性状】干燥的茎呈圆柱状或皱缩成不规则状，略扭曲，长10～35厘米，直径1.5～3.5厘米，表面黄棕色或棕褐色，有纵棱纵沟及节。质脆易折断，断面中间白色或中空。茎基部叶披针形椭圆状，上部叶较小。根3～6条丛生，呈锥状，质脆易折断，断面白色角质状。\n" +
                     "\n" +
                     "【性味归经】性平，味甘、苦。归肺经、心经。\n" +
                     "\n" +
                     "【功效与作用】益气养阴，清热解毒。属补虚药下分类的补气药。\n" +
                     "\n" +
                     "【临床应用】内服：煎汤，9～15克，鲜全草15～30克。外用：适量，鲜品捣敷。主治病后虚弱，阴虚内热，咳嗽吐血，头晕，腰痛酸软，糖尿病，遗精，淋浊带下，咽喉肿痛，毒蛇咬伤，烫火伤，疮疡痈肿。\n" +
                     "\n" +
                     "【化学成分】本品含盘龙参酚A、盘龙参酚B、盘龙参酚C、盘龙参新酚A、盘龙参新酚B、盘龙参醌、盘龙参二聚菲酚、红门兰酚、&beta；-谷固醇、豆固醇、菜油固醇、阿魏酸十九醇酯、阿魏酸二十八醇酯、对羟基苯甲醛等成分。\n" +
                     "\n" +
                     "【使用禁忌】尚不明确。\n" +
                     "\n" +
                     "【配伍药方】①治气虚带下：盘龙参30克，黑鱼1尾。炖服。(《四川中药志》1982年)\n" +
                     "\n" +
                     "②治咽喉肿痛：a.盘龙参根9克。水煎，加冰片0.6克，徐徐含咽。(《江西草药》)b.盘龙参15克，百两金15克。水煎服。(《四川中药志》1982年)\n" +
                     "\n" +
                     "③治小儿夏季热：盘龙参6克，鸭跖草15克。水煎服。(《香港中草药》)\n" +
                     "\n" +
                     "④治糖尿病：鲜盘龙参根30～60克，银杏15克，猪胰1条。水煎服。(《福建药物志》)\n" +
                     "\n" +
                     "⑤治肾炎：鲜盘龙参30～60克，无根藤，星宿菜、丝瓜根各30克。水煎服。(《福建药物志》)\n" +
                     "\n" +
                     "⑥治毒蛇咬伤，痈肿，指疔：鲜盘龙参、一支箭适量。捣敷患处。(《四川中药志》1982年)\n" +
                     "\n" +
                     "⑦治带状疱疹：盘龙参根适量。晒干研末，麻油调搽。(《江西草药》)\n" +
                     "\n" +
                     "⑧治烫火伤：盘龙参30克，蚯蚓5条，白糖少量。共捣烂外敷，每日换药1次。(《陕西中草药》)");

             chineseMedicineDao.insert(medicine13);

             ChineseMedicine medicine14=new ChineseMedicine();
             medicine14.setName("蒲公英");
             medicine14.setSortType("P");
             medicine14.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C2%2C909%2C600%3Bc0%3Dbaike116%2C5%2C5%2C116%2C38/sign=6e7be0715c4e9258b27bdcaea1b2fd6a/810a19d8bc3eb13554a2bfaeac1ea8d3fd1f44b9.jpg");
             medicine14.setData("【中药名】蒲公英 pugongying\n" +
                     "\n" +
                     "【别名】蒲公草、地丁、蒲公丁、金簪草、狗乳草、黄花地丁。\n" +
                     "\n" +
                     "【英文名】Taraxaci Herba。\n" +
                     "\n" +
                     "【药用部位】菊科植物蒲公英Taraxacum mongolicum Hand. -Mazz.的带根全草。\n" +
                     "\n" +
                     "【植物形态】多年生草本，含白色乳汁，高10～25厘米。根深长，单一或分枝。叶根生，排成莲座状，叶片矩圆状披针形、倒披针形或倒卵形，长6～15厘米，宽2～3.5厘米，先端尖或钝，基部狭窄，下延成叶柄状，边缘浅裂或作不规则羽状分裂，裂片牙齿状或三角状，全缘或具疏齿，绿色，或在边缘带紫色斑，被白色丝状毛。花茎上部密被白色丝状毛，头状花序单一，顶生，全部为舌状花，两性，总苞片多层，外层较短，卵状披针形，先端尖，有角状突起，内层线状披针形，先端呈爪状，花冠黄色，先端平截，5齿裂，雄蕊5，着生于花冠管上，花药合生成筒状，包于花柱外，花丝分离，白色，短而稍扁，雌蕊1枚，子房下位，长椭圆形，花柱细长，柱头2裂，有短毛。瘦果倒披针形。\n" +
                     "\n" +
                     "【产地分布】生于山坡草地、路旁、河岸沙地及田野间。全国大部分地区有分布。\n" +
                     "\n" +
                     "【采收加工】春、夏季开花前或刚开花时连根挖取，除净泥土，晒干。\n" +
                     "\n" +
                     "【药材性状】干燥的根略呈圆锥状，弯曲，长4～10厘米，表面棕褐色，皱缩，根头部有棕色或黄白色的茸毛，或已脱落。叶皱缩成团，或成卷曲的条片。外表绿色或暗灰绿色，叶背主脉明显。有时有不完整的头状花序。气微，味微苦。\n" +
                     "\n" +
                     "【性味归经】性寒，味苦、甘。归肝经、胃经。\n" +
                     "\n" +
                     "【功效与作用】清热解毒，利尿散结。属清热药下分类的清热解毒药。\n" +
                     "\n" +
                     "【临床应用】用量9～30克，内服煎汤，捣汁或人散剂。治疗急性乳腺炎、淋巴腺炎、瘰疬、疔毒疮肿、急性结膜炎、感冒发热、急性扁桃体炎、急性支气管炎、胃炎、肝炎、胆囊炎、尿路感染。\n" +
                     "\n" +
                     "【药理研究】具有抗病原微生物作用，抗肿瘤，抗胃溃疡，利胆及保肝，低浓度时直接兴奋离体蛙心，而高浓度时则呈抑制作用。能提高离体十二指肠的紧张性并加强其收缩力，临床认为有健胃和轻泻作用。注射液在试管内对金黄色葡萄球菌耐药菌株、溶血性链球菌有较强的杀菌作用，对肺炎双球菌、脑膜炎球菌、白喉杆菌、绿脓杆菌、变形杆菌、痢疾杆菌、伤寒杆菌及卡他球菌亦有一定的杀菌作用。毒性：小白鼠静脉注射蒲公英注射液的LDso为(58.88±7.94)克/千克，小鼠、兔亚急性毒性试验对肾脏可出现少量管型，肾小管上皮细胞浊肿缎带，煎剂给大鼠口服，吸收良好，尿中能保持一定的抗菌作用。\n" +
                     "\n" +
                     "【化学成分】含蒲公英甾醇、胆碱、菊糖、果胶、蒲公英固醇、蒲公英素、蒲公英赛醇、咖啡酸等成分。\n" +
                     "\n" +
                     "【使用禁忌】非实热证及阴疽者禁服。\n" +
                     "\n" +
                     "【配伍药方】①治乳痈初起：蒲公英30克，忍冬藤60克，生甘草6克。水二盅，煎一盅，食前服。(《洞天奥旨》英藤扬)\n" +
                     "\n" +
                     "②治急性结膜炎：蒲公英30克，菊花9克，薄荷6克(后下)，车前子12克(布包)。煎服。(《安徽中草药》)\n" +
                     "\n" +
                     "③治尿道炎：蒲公英15克，车前草15克，瞿麦15克，忍冬藤9克，石韦4克。水煎服。(《青岛中草药手册》)\n" +
                     "\n" +
                     "④治骨髓炎：蒲公英60克，全蝎1条，蜈蚣1条。研粗粉， 白酒250毫升浸泡3～5天。分数次服用。(《青岛中草药手册》)\n" +
                     "\n" +
                     "⑤治烧烫伤：蒲公英根洗净，捣碎取汁，待凝后涂患处。(《长白山植物药志》)\n" +
                     "\n" +
                     "⑥治急性胆道感染：蒲公英、刺针草各30克，海金沙、连钱草各15克，郁金12克，川楝子6克。水煎两次，浓缩至150毫升。每服50毫升，每日3次。(《全国中草药汇编》)\n" +
                     "\n" +
                     "⑦治急性阑尾炎：蒲公英30克，地耳草、半边莲各15克，泽兰、青木香各9克。水煎服。(《全国中草药汇编》)\n" +
                     "\n" +
                     "⑧治慢性胃炎，胃溃疡：蒲公英干根、地榆根各等分。研末，每服6克，每日3次，生姜汤送服。(《南京地区常用中草药》)");

             chineseMedicineDao.insert(medicine14);

             ChineseMedicine medicine15=new ChineseMedicine();

             medicine15.setName("佩兰");
             medicine15.setSortType("P");
             medicine15.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=a9a584eb9d510fb36c147fc5b85aa3f0/d8f9d72a6059252d9b42d756379b033b5bb5b90f.jpg");
             medicine15.setData("【中药名】佩兰 peilan\n" +
                     "\n" +
                     "【别名】兰草、泽兰、圆梗泽兰、香水兰。\n" +
                     "\n" +
                     "【英文名】Eupatorii Herba。\n" +
                     "\n" +
                     "【药用部位】菊科植物佩兰Eupatorium fortunei Turcz.的地上部分。\n" +
                     "\n" +
                     "【植物形态】一年生草本，高70～120厘米。根状茎横走，稍长。茎直立，圆柱状，下部光滑无毛。叶对生，下部的叶常早枯，中部的叶有短柄，通常3深裂，裂片长圆形或长圆状披针形，先端渐尖，基部楔形，边缘有锯齿，叶脉羽状，背面沿脉被疏毛，无腺点，揉之有香气；上部叶较小，通常不分裂。头状花序排列成伞房状聚伞花序；总苞片10枚左右，2～3列，外列的甚短，内列的较长，膜质，长圆形至倒披针形，常带紫红色。每个头状花序具花4～6朵；花两性，全部为管状花，冠毛较花冠为短，花冠白色或带微红色，先端5齿裂；雄蕊5枚，聚药，不露出于管外；子房下位，柱头2裂，伸出花冠外。瘦果圆柱形，长约3毫米，有5棱，熟时黑褐色。花期秋季。\n" +
                     "\n" +
                     "【产地分布】生于溪边或湿洼地带，有栽培。分布于河北、山东、江苏等地。\n" +
                     "\n" +
                     "【采收加工】夏、秋季分两次采割，去杂质，晒干或鲜用。\n" +
                     "\n" +
                     "【药材性状】茎圆柱形，长30～100厘米，直径0.2～0.5厘米；表面黄棕色或黄绿色，有的带紫色，有明显的节及纵棱线；质脆，断面髓部白色或中空。叶对生，有柄，叶片多皱缩.破碎，绿褐色；完整叶片3裂或不分裂，分裂者中间裂片较大，展平后呈披针形或长圆状披针形，基部狭窄，边缘有锯齿；不分裂者展平后呈卵圆形、卵状披针形或椭圆形。气芳香，味微苦。\n" +
                     "\n" +
                     "【性味归经】性平，味辛。归脾经、胃经、肺经。\n" +
                     "\n" +
                     "【功效与作用】芳香化湿、醒脾开胃、发表解暑。属化湿药。\n" +
                     "\n" +
                     "【临床应用】用量3～9克，水煎服。多用治湿浊中阻、脘痞呕恶、口中甜腻、口臭、多涎、暑湿表症、头胀胸闷。\n" +
                     "\n" +
                     "【药理研究】有祛痰、抗病毒、抗癌的作用。对流行性感冒病毒有抑制作用；并能抑制排卵。\n" +
                     "\n" +
                     "【化学成分】含挥发油；叶含香豆精、香豆酸、麝香草氢醌；根含兰草素。另含乙酸橙醇酯、百里香酚甲醚、对聚伞花素、蒲公英固醇乙酸酯、3-谷固醇等成分。\n" +
                     "\n" +
                     "【使用禁忌】阴虚血燥，气虚腹胀者慎服。\n" +
                     "\n" +
                     "【配伍药方】①治五月霉湿，并治秽浊之气：藿香叶3克，佩兰叶3克，陈皮4.5克，制半夏4.5克，大腹皮(酒洗)3克，厚朴(姜汁炒)2.4克，加鲜荷叶9克为引。煎汤服。(《时病论》芳香化浊法)\n" +
                     "\n" +
                     "②治中暑头痛：佩兰、青蒿、菊花各9克，绿豆衣12克。水煎服。(《青岛中草药手册》)\n" +
                     "\n" +
                     "③治急性胃肠炎：佩兰、藿香、苍术、茯苓、三颗针各9克。水煎服。(《全国中草药汇编》)\n" +
                     "\n" +
                     "④治唇疮：佩兰叶取汁洗之，日三上，瘥。(《普济方》)\n" +
                     "\n" +
                     "⑤治风齿疼痛颊肿及治血出不止：佩兰150克，水一斗，煮取五升。热含吐之，一日尽。(《普济方》)");

             chineseMedicineDao.insert(medicine15);

             ChineseMedicine medicine16=new ChineseMedicine();
             medicine16.setName("胖大海");
             medicine16.setSortType("P");
             medicine16.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=1f47a033ca3d70cf58f7a25f99b5ba65/562c11dfa9ec8a13035ea5eaf403918fa0ecc070.jpg");
             medicine16.setData("【中药名】胖大海 pangdahai\n" +
                     "\n" +
                     "【别名】大洞果、南安子、胡大海、通大海。\n" +
                     "\n" +
                     "【英文名】Sterculiae Lychnophorae Semen。\n" +
                     "\n" +
                     "【药用部位】梧桐科植物胖大海Sterculia lychnophora Hance.的成熟种子。\n" +
                     "\n" +
                     "【植物形态】落叶乔木，高30～40米。树皮粗糙有细条纹。单叶互生，叶片革质，卵圆或椭圆状披针形，长10～20厘米，宽6～14厘米，3裂，中裂片较长，两侧裂片的长约为中裂片的1/2，或稍长，先端钝或锐尖，基部圆形或近截形，全缘或微波状，上面绿色，光滑无毛，下面灰绿色，叶柄5～15厘米，粗壮。圆锥花序顶生或腋生，花杂性同株；花萼钟状，长0.7～1厘米，先端5深裂，裂片披针形，外面被星状柔毛；花瓣缺；雄花具雄蕊10～15枚，花药及花丝均具柔毛，不育心皮被短柔毛；雌花具雌蕊l枚。由5个被短柔毛的心皮组成，具1纤细子房柄，柱头2～5裂，退化雄蕊为1簇无花丝的花药，环绕子房。瞢葖果1～5个，着生于果梗上，长18～24厘米，基部宽5～6厘米，先端长渐尖，呈小舟状，成熟时开裂，初被疏柔毛，后脱落。种子棱形或倒卵形，长1.8～2.8厘米，深黑褐色，表面具皱纹。花期3月，果期4～6月。\n" +
                     "\n" +
                     "【产地分布】生于热带地区。近年在广东、海南及广西有少量引种。\n" +
                     "\n" +
                     "【采收加工】于4～6月果实成熟时采收，干燥。\n" +
                     "\n" +
                     "【药材性状】纺缍形或椭圆形。先端钝圆，基部略尖而歪，具浅色的圆形种脐，表面棕色或暗棕色，微有光泽，具不规则的干缩皱纹。外层种皮极薄，质脆，易脱落。中层种皮较厚，黑褐色，质松易碎，遇水膨胀成海绵状。断面可见散在的树脂状小点。内层种皮可与中层种皮剥离，稍革质，广卵形；子叶菲薄，紧贴于胚乳内侧，与胚乳等大。气微，味淡，嚼之有黏性。\n" +
                     "\n" +
                     "【性味归经】性寒，味甘。归肺经、大肠经。\n" +
                     "\n" +
                     "【功效与作用】清热润肺、利咽解毒、润肠通便。属化痰止咳平喘药下属分类的清化热痰药。\n" +
                     "\n" +
                     "【临床应用】2～3枚，沸水泡服或煎服。用治肺热声哑、干咳无痰、咽喉干痛、热结便闭、头痛目赤。\n" +
                     "\n" +
                     "【药理研究】利尿；镇痛。浸出液对兔有缓泻作用，服后促进肠蠕动而通便；种仁煎液有降血压作用；对流感病毒PR6株有较强的抑制作用；此外，尚有抗菌作用。且还具有一定毒性。\n" +
                     "\n" +
                     "【化学成分】种皮含活性成分胖大海素；胚乳含挥发油、黄芪胶粘素等；种子含水溶性多糖、多种脂肪酸，还含2，4-二羟基苯甲酸、半乳糖、戊糖、β-谷甾醇及胡萝卜苷等。\n" +
                     "\n" +
                     "【使用禁忌】脾胃虚寒忌服。\n" +
                     "\n" +
                     "【配伍药方】①治干咳失音，咽喉燥痛，牙龈肿痛，因于外感者：胖大海五枚，甘草3克，炖茶饮服，老幼者可加入冰糖少许。(《慎德堂方》)\n" +
                     "\n" +
                     "②治肺热音哑：胖大海3枚，金银花、麦冬各6g，蝉蜕3克。水煎服。(《全国中草药汇编》)\n" +
                     "\n" +
                     "③治慢性咽炎：胖大海3克，杭菊花、生甘草各9克。水煎服。(《全国中草药汇编》)\n" +
                     "\n" +
                     "④治大便出血：胖大海数枚，开水泡发，去核，加冰糖调服。因热便血，效。[《医界春秋》1936(1)：93]");

             chineseMedicineDao.insert(medicine16);

             ChineseMedicine medicine17=new ChineseMedicine();

             medicine17.setName("平贝母");
             medicine17.setSortType("P");
             medicine17.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=34b2a0e50e3b5bb5aada28ac57babe5c/a686c9177f3e6709d5bbe4b136c79f3df9dc55c0.jpg");
             medicine17.setData("【中药名】平贝母 pingbeimu\n" +
                     "\n" +
                     "【别名】坪贝、贝母、平贝。\n" +
                     "\n" +
                     "【英文名】Fritillariae Ussuriensis Bulbus。\n" +
                     "\n" +
                     "【来源】百合科植物平贝母Fritillaria ussuriensis Max-im.的干燥鳞茎。\n" +
                     "\n" +
                     "【植物形态】多年生草本。鳞茎扁圆形，茎高40～60厘米。叶轮生或对生，中上部的叶常兼有互生，线形，长9～16厘米，宽2～6.5毫米，先端不卷曲或稍卷曲。花1～3朵，紫色，其浅色小方格，顶花具叶状苞片4～6，先端极卷曲，外轮花被片长约3.5厘米，宽约1.5厘米，内轮花被片稍短而狭，蜜腺窝在背而明显凸出，雄蕊6，花柱具乳突，柱头3深裂，裂片长5毫米。蒴果宽倒卵形，具圆棱。花期5～6月。\n" +
                     "\n" +
                     "【产地分布】生于林下、草甸或河谷地。主产于黑龙江、吉林、辽宁等，有大量栽培。\n" +
                     "\n" +
                     "【采收加工】春季采挖，除去外皮、须根及泥沙，晒干或低温于燥。\n" +
                     "\n" +
                     "【药材性状】呈扁球形，高0.5～1厘米，直径0.6～2厘米。表面乳白色或淡黄白色，外层鳞叶2瓣，肥厚，大小相近或一片稍大抱合，顶端略平或微凹入，常稍开裂；中央鳞片小。质坚实而脆，断面粉性。气微，味苦。\n" +
                     "\n" +
                     "【性味归经】性微寒，味苦、甘。归肺经、心经。\n" +
                     "\n" +
                     "【功效与作用】清热润肺，化痰止咳。属化痰止咳平喘药下属分类的清化热痰药。\n" +
                     "\n" +
                     "【临床应用】3～9克，研粉冲服，一次1～2克。用治肺热燥咳，干咳少痰，阴虚劳嗽，咳痰带血。\n" +
                     "\n" +
                     "【药理研究】对消化性溃疡及应激性溃疡均有一定的抑制作用；尚有抑制中枢、降低血压、祛痰作用。\n" +
                     "\n" +
                     "【化学成分】鳞茎含西贝母碱-3β-D-葡萄糖甙，贝母辛碱，西贝母碱，平贝碱甲，平贝碱乙，平贝碱丙，平贝碱甙及贝母素乙、贝母辛、西贝素、平贝定苷、平贝七环酮碱甲醚、α-丁香烯、γ-榄香烯等成分。\n" +
                     "\n" +
                     "【使用禁忌】不宜与川乌、制川乌、草乌、制草乌、附子同用。\n" +
                     "\n" +
                     "【配伍药方】①治慢性支气管炎：平贝母、百合、苏叶、五味子、桔梗各150克。水煎2次，浓缩至5000克，加糖1000克。每次服15～20毫升日3次。(《全国中草药汇编》)\n" +
                     "\n" +
                     "②治结核咳嗽，痰中带血：平贝母35克，白及50克，白糖25克。共研细末，每服10克，日2次。(《长白山植物药志》)\n" +
                     "\n" +
                     "③治阴虚发热，咳嗽痰少：平贝母10g，知母10克，甘草5克。水煎服。(《长白山植物药志》)\n" +
                     "\n" +
                     "④治淋巴结核：平贝母15克，玄参20克，牡蛎25克。为细末，蜜丸，重10克，每服1丸，日2次。(《长白山植物药志》)");

             chineseMedicineDao.insert(medicine17);

             ChineseMedicine medicine18=new ChineseMedicine();
             medicine18.setName("辟汗草");
             medicine18.setSortType("P");
             medicine18.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=d2c46310763e6709aa0d4dad5aaef458/63d9f2d3572c11dfdcae20146a2762d0f703c28e.jpg");
             medicine18.setData("【中药名】辟汗草 pihancao\n" +
                     "\n" +
                     "【别名】散血草、野苜蓿、省头草、铁扫把。\n" +
                     "\n" +
                     "【英文名】Daghestan Sweetciorer Herb。\n" +
                     "\n" +
                     "【来源】豆科植物草木樨Melilotus suaveotens Ledeb.的全草。\n" +
                     "\n" +
                     "【植物形态】一年生或二年生草本，高60～90厘米，干后有香气。茎直立，多分枝。三出复叶互生，有柄。小叶长椭圆形或倒披针形，长1～1.5厘米，宽约0.5厘米，先端钝，基部楔形，边缘有细齿，叶脉伸至齿端；托叶线形。总状花序腋生，纤细。花萼钟形；花冠蝶形，黄色，旗瓣长于翼瓣；雄蕊10，二体。荚果卵形，光滑，熟后褐色，有网纹。种子1粒。花期5～7月，果期8～9月。\n" +
                     "\n" +
                     "【产地分布】分布于东北、华北、西南及华东等地区。多生于空旷杂草中。\n" +
                     "\n" +
                     "【采收加工】7～8月连根拔起，洗净阴干。\n" +
                     "\n" +
                     "【药材性状】根呈圆柱形，长10～20厘米，上端直径0.5～1.5厘米，下端渐细，分枝较多。根头部较大，常有2至多个地上茎的残基。表面黄棕色至红棕色，多细纵纹，皮孔淡黄色，横向延长。质坚硬，折断面刺状，切断面皮部灰白色至灰黄色，木部淡黄色或黄色。气微弱而特异，味微甜。\n" +
                     "\n" +
                     "【性味归经】性凉，味辛、苦。归肝经 、脾经、胃经。\n" +
                     "\n" +
                     "【功效与作用】和中健胃，解暑化湿。属化湿药。\n" +
                     "\n" +
                     "【临床应用】用量5～15克，水煎服。用于暑湿胸闷、口腻、口臭、头胀痛。\n" +
                     "\n" +
                     "【主要成分】茎、叶、花中均含挥发油.油中主成分为二氢香豆素( dihydrocoumarin)；全草含脂肪油约3.5%，果胶约7%，种子含脂肪油约6%。\n" +
                     "\n" +
                     "【使用禁忌】脾胃寒者慎用，孕妇禁用。");

             chineseMedicineDao.insert(medicine18);

             ChineseMedicine medicine19=new ChineseMedicine();
             medicine19.setName("片姜黄");
             medicine19.setSortType("P");
             medicine19.setMedicineImageUrl("http://imgsrc.baidu.com/imgad/pic/item/6609c93d70cf3bc711c1dfd1da00baa1cc112acd.jpg");
             medicine19.setData("【中药名】片姜黄 pianjianghuang\n" +
                     "\n" +
                     "【别名】片子姜黄。\n" +
                     "\n" +
                     "【英文名】Wenyujin Rhizoma Concisum。\n" +
                     "\n" +
                     "【来源】姜科植物温郁金Curcuma wenyujinY.H.Chen et C.Ling的根茎。\n" +
                     "\n" +
                     "【植物形态】多年生宿根草本。根粗壮，末端膨大呈长卵形块根。块茎卵圆状，侧生，根茎圆柱状，断面黄色。叶基生；叶柄长约5厘米，基部的叶柄短，或近无柄，具叶耳；叶片长圆形，长15~ 37厘米，宽7~10厘米，先端尾尖，基部圆形或三角形。穗状花序，长约13厘米；总花梗长7~15厘米；具鞘状叶，基部苞片阔卵圆形，小花数朵，生于苞片内，顶端苞片较狭，腋内无花；花萼白色筒状，不规则3齿裂；花冠管呈漏斗状，裂片3，粉白色，上面1枚较大，两侧裂片长圆形；侧生退化雄蕊长圆形，药隔矩形，花丝扁阔；子房被伏毛，花柱丝状，光滑或被疏毛，基部有2棒状附属物，柱头略呈二唇形，具缘毛。花期4~6月，极少秋季开花。\n" +
                     "\n" +
                     "【产地分布】分布于江苏、浙江、福建、广东、广西、四川、云南等地。\n" +
                     "\n" +
                     "【采收加工】冬季茎叶枯萎后采挖。洗净，除去须根，趁鲜纵切厚片，晒干。\n" +
                     "\n" +
                     "【药材性状】长圆形或不规则的片状，大小不一，长3~6厘米，宽1~3厘米，厚0.1~0.4厘米。外皮灰黄色，粗糙皱缩，有时可见环节及须根痕。切面黄白色至棕黄色，有一圈环纹及多数筋脉小点。质脆而坚实。断面灰白色至棕黄色，略粉质。气香特异，味微苦而辛凉。\n" +
                     "\n" +
                     "【性味归经】性温，味辛、苦。归脾经、肝经。\n" +
                     "\n" +
                     "【功效与作用】破血行气、通经止痛。属活血化瘀药下属分类活血调经药。\n" +
                     "\n" +
                     "【临床应用】用量3~ 10克。用治血滞经闭、行经腹痛、胸胁刺痛、风湿痹痛、肩臂疼痛，跌打损伤。\n" +
                     "\n" +
                     "【主要成分】有镇痛作用，可促进胆汁的分泌和排泄，对肝脏损伤有保护作用，能提高免疫功能等。\n" +
                     "\n" +
                     "【使用禁忌】血虚无气滞血瘀及孕妇慎服。");

             chineseMedicineDao.insert(medicine19);

             ChineseMedicine medicine20=new ChineseMedicine();

             medicine20.setName("乳香");
             medicine20.setSortType("R");
             medicine20.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=bff80f3665380cd7f213aabfc02dc651/a5c27d1ed21b0ef4145368d8ddc451da80cb3ef8.jpg");
             medicine20.setData("【中药名】乳香 ruxiang\n" +
                     "\n" +
                     "【别名】乳头香、滴乳香、熏陆香、塌香、马思答吉、天泽香、浴香。\n" +
                     "\n" +
                     "【英文名】Olibanum。\n" +
                     "\n" +
                     "【药用部位】橄榄科植物卡氏乳香树Boswellia carterli Birdw.及同属数种植物皮部渗出的油胶树脂。\n" +
                     "\n" +
                     "【植物形态】矮小乔木。树干粗壮，树皮光滑，淡棕黄色。叶互生，密集形成叶簇，或于上部疏生，奇数羽状复叶，叶柄被白毛，小叶卜9对，对生，小叶片长卵形，先端钝，通常略波曲，基部圆形，近心形或楔形，边缘有不规则的圆齿裂，或呈不明显齿裂至近全缘，两面均被白毛或上面无毛，下面被疏毛。花小，排成总状花序，苞片卵形，先端尖；花萼杯状，先端5裂，裂片三角状卵形，花瓣5片，淡黄色，与萼片互生，卵形，先端急尖；雄蕊10枚，着生于花盘外侧，花药橙色，花丝短；子房上位，弘4室，花柱粗，柱头头状。果实小，倒卵形，有三钝棱；果皮光滑，肉质，肥厚。\n" +
                     "\n" +
                     "【产地分布】生于热带沿海山地。分布于非洲东北部、索马里、埃塞俄比亚等地。\n" +
                     "\n" +
                     "【采收加工】春、夏季均可采收。采时于树干的皮部由下向上顺序切伤，并开一狭沟，使树脂从伤口渗出，流人沟中，数天后凝成千硬的固体，即可收取。\n" +
                     "\n" +
                     "【药材性状】呈球形、泪滴状颗粒或不规则小块状，有时粘连成团块。淡黄色、棕黄色或深棕色，常带轻微的绿色、蓝色或棕红色。半透明。表面有一层类白色粉霜，除去粉霜后，表面仍无光泽。质坚脆，断面蜡样，无光泽，亦有少部分显玻璃样光泽。气微芳香，味微苦。嚼之，初破碎成小块，迅速软化成胶块，黏附牙齿、唾液成乳状，并微有香辣感。\n" +
                     "\n" +
                     "【性味归经】性温，味辛、苦。归心经、脾经、肝经。\n" +
                     "\n" +
                     "【功效与作用】调气活血、定痛、消肿、生肌。属活血化瘀药下分类的活血止痛药。\n" +
                     "\n" +
                     "【临床应用】用量3～9克。用治气血瘀滞、心腹疼痛、痈疮肿毒、跌打损伤、风湿痹痛、痛经、产后瘀血刺痛。临床还用于治疗急性阑尾炎。\n" +
                     "\n" +
                     "【药理研究】挥发油为乳香镇痛的有效成分，主要为具镇痛作用的乙酸正辛酯，占挥发油总量的92.46%。以蒎烯作指标成分，可将索马里乳香与埃塞俄比亚乳香及苏丹乳香鉴别开。\n" +
                     "\n" +
                     "【化学成分】含树脂60%～70%、树胶27%～35%、挥发油3%～8%。树脂主要成分为游离a,B-乳香脂酸、结合乳香脂酸、蒎烯、二戊烯、α,3-水芹烯等成分。\n" +
                     "\n" +
                     "【使用禁忌】孕妇、胃弱者及无瘀滞者禁服。\n" +
                     "\n" +
                     "【配伍药方】①治急心痛：胡椒49粒，乳香3克。为末。男用姜汤下，女用当归汤下。(《摄生众妙方》抽刀散)\n" +
                     "\n" +
                     "②治偏头痛不可忍：乳香(如皂子大)、高良姜(如指头大)。上二味，于火上烧，迎烟熏鼻。随痛左右用之。(《圣济总录》乳香散)\n" +
                     "\n" +
                     "③治赤白带下：草果一个(去皮)，入乳香一小块，用面并裹，火炮焦黄留性，取出和面用之。上为细末。每服6克，陈米饮调下，重者9克。(《妇人良方》乳香散)\n" +
                     "\n" +
                     "④治急、慢惊风：乳香15克，甘遂15克。同研细。每服1.5克，用乳香汤调下(《博济方》)\n" +
                     "\n" +
                     "⑤治赤口疮：乳香、没药各3克，白矾1.5克，铜绿少许。研为末，掺之。(《医学纲目》乳香散)\n" +
                     "\n" +
                     "⑥治口疮：乳香、没药、雄黄各3克，轻粉1.5克，巴豆霜少许为末掺之。(《证治准绳》)");

             chineseMedicineDao.insert(medicine20);

             ChineseMedicine medicine21=new ChineseMedicine();

             medicine21.setName("肉苁蓉");
             medicine21.setSortType("R");
             medicine21.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=f9df1caf1bd5ad6ebef46cb8e0a252be/21a4462309f79052b3e660550cf3d7ca7acbd5db.jpg");
             medicine21.setData("【中药名】肉苁蓉 roucongrong\n" +
                     "\n" +
                     "【别名】苁蓉、大芸、荒漠肉苁蓉、肉松蓉、纵蓉。\n" +
                     "\n" +
                     "【英文名】Cistanches Herba\n" +
                     "\n" +
                     "【药用部位】】列当科植物肉苁蓉Cistanche deserticolaY.C.Ma带鳞叶的肉质茎。\n" +
                     "\n" +
                     "【植物形态】多年生寄生草本，茎肉质肥厚，扁平，单一，下部宽，向上逐渐变细。鳞片叶淡黄白色，螺旋状排列，无柄，下部叶排列紧密，宽卵形或三角状卵形，上部叶稀疏，线状披针形，长1～4厘米，宽0.5～1厘米。穗状花序生于茎顶端，每花有苞片1，与叶同行；小苞片2，狭线形，基部与花萼合生，花萼5浅裂；花冠管状钟形，顶端5裂，黄白色、淡紫色或边缘淡紫色，管内有2条纵向的鲜黄色突起，雄蕊4枚，2强，子房上位。蒴果卵形，褐色，种子多数，细小，有光泽。花期5～6月，果期6～7月。\n" +
                     "\n" +
                     "【产地分布】生于荒漠中，寄生在藜科植物琐琐(盐木)Haloxylonammodendron (C.A.Mey.) Bunge的根上。分布于内蒙古、陕西、甘肃等地。\n" +
                     "\n" +
                     "【采收加工】春、秋季均可采，以3～5月采收者为佳，过时则中空。采收后，晾晒至由黄白色变成肉质棕褐色，干后即可；或切成数段晒干。肉苁蓉：除去杂质，稍浸泡，润透，切厚片，干燥；酒苁蓉：去肉苁蓉片，加黄酒拌匀(每100千克，用黄酒20千克)，置炖罐内，密闭，隔水加热蒸透，至酒完全吸尽、表面黑色时取出，干燥。\n" +
                     "\n" +
                     "【药材性状】扁圆柱形，稍弯曲，长3～20厘米，宽2～8厘米，厚1.5～4厘米。表面暗棕色、灰棕色或黑棕色，密被覆瓦状排列的肉质鳞叶，通常鳞叶先端已断或鳞叶脱落而留有横长的短线状鳞叶痕。体重，质坚实而硬，微有韧性，不易折断。断面暗棕色或黑棕色，有淡棕色点状维管束，排列成深波状环纹，有时中空。气微，味甜而微苦。以条粗壮、密被鳞片、色暗棕、质柔润者为佳。\n" +
                     "\n" +
                     "【性味归经】性温，味甘、咸。归肾经、大肠经。\n" +
                     "\n" +
                     "【功效与作用】补肾阳、益精血、润肠通便。属补虚药下补阳药\n" +
                     "\n" +
                     "【临床应用】煎汤内服，用量6～9克。用于腰膝痿软、阳痿、女子不孕、肠燥便秘。\n" +
                     "\n" +
                     "【药理研究】能增强体液和细胞免疫功能；对中枢神经系统，能增加去甲肾上腺素(NE)和5-羟吲哚乙酸(5-HIAA)含量，并增加多巴胺(DA)与二经苯乙酸(D0PAC)比值；具有延缓衰老的作用；能显著提高小鼠小肠推进度，缩短通便时间，能有效对抗阿托品的抑制排便作用，同时对大肠的水分吸收也有明显抑制作用。具有降压、抗突变等作用。有调整内分泌、促进代谢及强壮作用。\n" +
                     "\n" +
                     "【化学成分】主要含苯乙基苷类。内蒙古和新疆所产的肉苁蓉，含多糖8.5%～13%，总糖及多糖含量以内蒙古所产为高。另含松果菊苷、毛蕊花糖苷、洋丁香酚苷、肉苁蓉苷A、肉苁蓉苷B、肉苁蓉苷C、肉苁蓉苷D、肉苁蓉苷E、肉苁蓉苷G、苁蓉素、甘露醇等成分。\n" +
                     "\n" +
                     "【使用禁忌】相火偏旺、胃弱便溏、实热便结者禁服。\n" +
                     "\n" +
                     "【配伍药方】①治遗精：肉苁蓉、桑螵蛸、芡实各15克，莲米18克，黑芝麻30克。共为末，蜜丸。早晚服，每次9克，开水送下。(张继书《锦方选集》)\n" +
                     "\n" +
                     "②强筋健髓：肉苁蓉、鳝鱼。为末，黄精酒丸服之。力可十倍。(《本草拾遗》)\n" +
                     "\n" +
                     "③补精败，面黑劳伤：肉苁蓉120克，水煮令烂，薄切细研、精羊肉，分为四度，下五味，以米煮粥、空心服之。(《药性论》)\n" +
                     "\n" +
                     "④治肾虚白浊：肉苁蓉、鹿茸、山药、白茯苓等分。为末，米糊丸梧桐子大。枣汤每下三十丸。(《圣济总录》)\n" +
                     "\n" +
                     "⑤治发汗、利小便，亡津液，大腑秘结，老人、虚人皆可服：肉苁蓉(酒浸，焙)60克，沉香(另研)30克。上为细末，用麻子仁汁打糊为丸，如梧桐子大。每服七十丸，空心，米饮下。(《济生方》润肠丸)");

             chineseMedicineDao.insert(medicine21);

             ChineseMedicine medicine22=new ChineseMedicine();
             medicine22.setName("肉桂");
             medicine22.setSortType("R");
             medicine22.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=339e8d1abf1c8701c2bbbab44616f54a/63d0f703918fa0ec151757f32a9759ee3c6ddb7f.jpg");
             medicine22.setData("【中药名】肉桂 rougui\n" +
                     "\n" +
                     "【别名】菌桂、大桂、筒桂、辣桂、玉桂、牡桂、紫桂、桂皮、桂。\n" +
                     "\n" +
                     "【英文名】Cinnamomi Cortex\n" +
                     "\n" +
                     "【药用部位】为樟科植物肉桂Cinnamomum cassia Presl的干燥树皮。\n" +
                     "\n" +
                     "【植物形态】乔木，树皮灰褐色，老树树皮厚约1.3厘米，幼枝多有四棱，被褐色茸毛。叶互生或近对生，革质，矩圆至近披针形，长8～20厘米，宽4～5.5厘米，上面绿色，无毛，中脉及侧脉明显凹下，下面有疏柔毛，具基三出脉，叶柄长1.5～2厘米。圆锥花序腋生或近顶生，长8～16厘米，花小，白色，花被片6，与花被管无长2毫米，能育雄蕊9，花药4室，第三轮雄蕊花药外向瓣裂，子房卵形，无毛，细长形，柱头小。果实椭圆形，长1厘米，直径9毫米，黑紫色。花期6～7月，果期10～12月。\n" +
                     "\n" +
                     "【产地分布】栽培于沙土或山地。分布于云南、广西、广东、福建。\n" +
                     "\n" +
                     "【采收加工】多于秋季剥取，阴干，除去杂质及粗皮，用时捣碎。\n" +
                     "\n" +
                     "【药材性状】本品呈槽状或卷筒状，长30～40厘米，宽或直径3～10厘米，厚0.2～0.8厘米。外表面灰棕色，稍粗糙，有不规则的细皱纹和横向突起的皮孔，有的可见灰白色的斑纹；内表面红棕色，略平坦，有细纵纹，划之显油痕。质硬而脆，易折断，断面不平坦，外层棕色而较粗糙，内层红棕色而油润，两层间有1条黄棕色的线纹。气香浓烈，味甜、辣。\n" +
                     "\n" +
                     "【性味归经】性大热，味辛、甘。归肾经、脾经、心经、肝经。\n" +
                     "\n" +
                     "【功效与作用】补火助阳，引火归元，散寒止痛，温通经脉。属温里药。\n" +
                     "\n" +
                     "【临床应用】内服：1～4.5克，不宜久煎；研末，0.5～1.5克；或入丸剂。外用：适量，研末，调敷；浸酒，涂擦。用于阳痿宫冷，腰膝冷痛，肾虚作喘，虚阳上浮，眩晕目赤，心腹冷痛，虚寒吐泻，寒疝腹痛，痛经经闭。\n" +
                     "\n" +
                     "【药理研究】对肠胃有缓和的刺激作用，并能解除胃肠平滑肌痉挛，具有很强的抗溃疡作用；可拮抗血小板聚集，具有改善心血管系统的作用，调节机体免疫功能；对中枢神经系统，具有镇静、镇痛、解热、抗惊厥等作用；对阳虚、阴虚模型有预防和保护作用；具有一定的抗炎作用；具有很强的杀真菌作用；抗肿瘤；升高小鼠血清三酰甘油有明显降低作用，能延长亚硝酸钠中毒小鼠的存活时间。肉桂中含有的桂皮醛对小鼠有明显的镇静作用，表现为自发活动减少，对抗甲基苯丙胺(Methamphetamine)所产生的过多活动、转棒试验产生的运动失调以及延长环己巴比妥钠的麻醉时间等。\n" +
                     "\n" +
                     "【化学成分】肉桂含挥发油1.98%-2.06%，其主要成分为桂皮醛，占52.92%-61.20%，还有乙酸桂皮酯、苯甲酸苄酯、反式桂皮酸、桂皮苷等成分。\n" +
                     "\n" +
                     "【使用禁忌】阴虚火旺，里有实热，血热妄行出血及孕妇均禁服，畏赤石脂。\n" +
                     "\n" +
                     "【配伍药方】①治卒心痛，亦治久心病发作有时节者：肉桂、当归各30克，栀子14枚。捣为散，酒服1克，日三五服。(《肘后方》)\n" +
                     "\n" +
                     "②疗乳痈：桂心、甘草各0.6克，乌头0.3克(炮)，捣为末，和苦酒，涂纸覆之，脓化为水，则神效。(《肘后方》)\n" +
                     "\n" +
                     "③疗产后余血作痛兼块者：肉桂、姜黄。上等分为末，酒服1克，血下尽妙。(《经效产宝》)\n" +
                     "\n" +
                     "④治跌打扑伤破，腹中有瘀血：肉桂、当归各60克，蒲黄一升。上三味，治下筛。以酒服1克，日三，夜一。(《千金要方》)\n" +
                     "\n" +
                     "⑤治白带腥臭，多悲不乐，大寒：黄柏(为引用)、知母各1.5克，肉桂3克，附子9克。水二盏煎至一盏。去渣，食远热服。(《兰室秘藏》桂附汤)");
             chineseMedicineDao.insert(medicine22);
             ChineseMedicine medicine23=new ChineseMedicine();

             medicine23.setName("人参叶");
             medicine23.setSortType("R");
             medicine23.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544625138&di=98b92be1dfd28c450a8ea9ffb39cd106&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.qnong.com.cn%2Fuploadfile%2F2018%2F0417%2F20180417102947577.jpg");
             medicine23.setData("【中药名】人参叶 renshenye\n" +
                     "\n" +
                     "【别名】参叶、人参苗。\n" +
                     "\n" +
                     "【英文名】Ginseng Folium。\n" +
                     "\n" +
                     "【药用部位】五加科植物人参Panax ginseng C.A.Mey.的干燥叶。\n" +
                     "\n" +
                     "【植物形态】多年生草本，高30～70厘米。根肥大，肉质，纺锤形，末端多分歧，外皮淡黄色。叶为掌状复叶，具长柄；轮生叶的数目依生长年限而不同，一般1年生者1片三出复叶，2年生者l片五出复叶，3年生者2片五出复叶，以后每年递增1片复叶，最多可达6片复叶；小叶片披针形，下方2片小叶较小，边缘具绌锯齿。伞形花序单一顶生，每花序有10～80多朵花，集成圆球形；花小，花萼绿色；花瓣淡黄绿色；子房下位。果实为核果状浆果，扁球形。花期5～6月，果期6～9月。\n" +
                     "\n" +
                     "【产地分布】生于海拔数百米的阔叶林或针叶阔叶混交林下。野生于黑龙江、吉林、辽宁及河北等地，也有引种栽培。\n" +
                     "\n" +
                     "【采收加工】秋季采收，晾干或烘干。\n" +
                     "\n" +
                     "【药材性状】常扎成小把，呈束状或扇状，长12～35厘米。掌状复叶带有长柄，暗绿色，3～6枚轮生。小叶通常5枚，偶有7或9枚，呈卵形或倒卵形。基部的小叶长2～8厘米，宽1～4厘米；上部的小叶大小相近，长4～16厘米，宽2～7厘米。基部楔形，先端渐尖，边缘具细锯齿及刚毛，上表面叶脉生刚毛，下表面叶脉隆起。纸质，易碎。气清香，味微苦而甘。\n" +
                     "\n" +
                     "【性味归经】性寒，味苦、甘。归肺经、胃经。\n" +
                     "\n" +
                     "【功效与作用】补气，益肺，祛暑，生津。属补虚药下分类的补气药。\n" +
                     "\n" +
                     "【临床应用】内服：煎汤，用量为3～10克。用于气虚咳嗽。暑热烦躁，津伤口渴，头目不清，四肢倦乏。\n" +
                     "\n" +
                     "【药理研究】能提高学习记忆力，增加脑内RNA的含量和全脑去甲肾上腺素的含量；能显著提高免疫功能；可使冠脉血流量明显增加，耗氧量减少，心肌收缩力明显增强，心率减慢，降低血压，改善心功能及心肌缺血作用；提高耐缺氧能力；能抑制血小板聚集，可明显抑制血瘀大鼠的血栓形成，降低其血细胞比容，增加血瘀动物红细胞流动性；影响体内脂质、蛋白质及水盐代谢；抗肿瘤作用；延缓衰老作用；具有抗疲劳作用，对高温、低温、烫伤和微波辐射的应激条件下的机体具有保护作用。尚具有抗炎、抑制病毒复制、使血清总蛋白含量及胎盘功能明显改善的作用。\n" +
                     "\n" +
                     "【化学成分】人参叶含人参皂苷Rb1、人参皂苷Rb2、人参皂苷Rc、人参皂苷Rd、人参皂苷Re、20(R)原人参二醇、三叶豆苷、1，2-二苯基乙烷、十三烷酸等成分。\n" +
                     "\n" +
                     "【使用禁忌】脾胃虚寒者慎服，不宜与藜芦、五灵脂同用。");

             chineseMedicineDao.insert(medicine23);

             ChineseMedicine medicine24=new ChineseMedicine();
             medicine24.setName("蕤仁");
             medicine24.setSortType("R");
             medicine24.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C32%2C700%2C462%3Bc0%3Dbaike92%2C5%2C5%2C92%2C30/sign=1b705c179682d158afcd03f1bd3a35eb/3ac79f3df8dcd10070d822d8788b4710b8122f87.jpg");
             medicine24.setData("【中药名】蕤仁 ruiren\n" +
                     "\n" +
                     "【别名】马茄子、扁核木、茹茹、蕤子、马茹子、蕤核仁、蕤核。\n" +
                     "\n" +
                     "【英文名】Prinsepiae Nux。\n" +
                     "\n" +
                     "【药用部位】蔷薇科植物蕤核Prinsepia uniflora Batal.的成熟果核。\n" +
                     "\n" +
                     "【植物形态】落叶灌木，高达1.5米。茎多分枝，外皮棕褐色，幼枝细瘦，开展，灰绿色，无毛；叶腋处有短刺，先端微带红色。单叶互生或数叶簇生；具短柄或近于无柄；叶片线状长圆形，狭倒卵形或卵状披针形，先端圆钝，有小突或微凹，基部楔形，全缘或具疏锯齿，上面深绿色，有光泽，背面淡绿色，无毛，侧脉不明显。花1～3朵簇生于叶腋，直径约1.5厘米，萼筒杯状，顶端5裂，裂片阔而短，绿色；花瓣5，白色，近圆形，有爪；雄蕊10枚，花丝短，花药卵圆形；雌蕊1枚，子房卵圆形，花柱侧生于子房的近基部处，柱头头状。核果球形，熟时黑色，表面微被蜡白粉；果核卵圆形，稍扁，有皱纹，棕褐色。花期4～6月，果期7～8月。\n" +
                     "\n" +
                     "【产地分布】生于山坡、林下、稀疏灌丛中或河川固定沙丘上。分布于山西、内蒙古、陕西等地。\n" +
                     "\n" +
                     "【采收加工】夏、秋季采摘成熟果实，除去果肉，洗净，晒干。\n" +
                     "\n" +
                     "【药材性状】类卵圆形，稍扁，长7～10毫米，宽6～8毫米，厚3～5毫米。表面淡黄棕色或深棕色，有明显的网状沟纹，间有棕褐色果肉残留，顶端尖，两侧略不对称，质坚硬。种子扁平卵圆形，种皮薄，浅棕色或红棕色，易剥落；子叶2，乳白色。有油脂。气微，味微苦。\n" +
                     "\n" +
                     "【性味功能】性微寒，味甘。归肝经。\n" +
                     "\n" +
                     "【功效与作用】养肝明目、疏风散热。属安神药下属分类的养心安神药。\n" +
                     "\n" +
                     "【临床应用】用量5～9克。水煎服，外用适量：去油研成膏状点眼或煎水洗。用治目赤肿痛、睑弦赤烂、目暗羞明。\n" +
                     "\n" +
                     "【药理研究】本品具有降压和镇定作用。\n" +
                     "\n" +
                     "【化学成分】含水分、灰分、蛋白质、脂肪、纤维素、山柰酚、异紫堇杷明碱、里白烯、熊果酸、胡萝卜苷、β-谷固醇、香草酸等成分。种仁含油脂、生物碱。壳中含有黄酮、萜类、三萜类化合物、低聚糖类。\n" +
                     "\n" +
                     "【使用禁忌】目痛非关风热，而因于肝肾两虚者，不宜用。\n" +
                     "\n" +
                     "【配伍药方】①治目赤痛：蕤仁二十枚(碎)，苦竹叶一把，细辛15克，上三味，以水三升，煮取半升以洗眼，日三五度。(《外台》洗眼方)\n" +
                     "\n" +
                     "②治风毒冲眼赤痛，晕翳不退：蕤仁0.9克(去赤皮细研)，腻粉0.15克，龙脑0.15克。上件药，都研细令匀，每日三度点之。(《圣惠方》蕤仁膏)");

             chineseMedicineDao.insert(medicine24);

             ChineseMedicine medicine25=new ChineseMedicine();
             medicine25.setName("人参");
             medicine25.setSortType("R");
             medicine25.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=aaaa069f4d4a20a425133495f13bf347/2934349b033b5bb520567e7334d3d539b700bcbe.jpg");
             medicine25.setData("【中药名】人参 renshen\n" +
                     "\n" +
                     "【别名】神草、吉林参、棒槌、土精、黄参、血参、地精、金井玉阑、孩儿参。\n" +
                     "\n" +
                     "【英文名】Ginseng Radix Et Rhizoma\n" +
                     "\n" +
                     "【来源】五加科植物人参Panax ginseng C.A.Mey.的根。\n" +
                     "\n" +
                     "【植物形态】多年生草本。根茎短，直立，每年增生一节，通称“芦头”，有时其上生一至数条不定根(芋)。主根粗壮，肉质，圆柱形或纺锤形。掌状复叶轮生茎端，复叶有长柄，小叶片多为5枚，边缘有细锯齿，上面沿脉有稀疏刚毛。伞形花序顶生。核果。种子2粒。\n" +
                     "\n" +
                     "【产地分布】生于山地的针、阔叶混交林或杂木林下。分布于长白山脉和小兴安岭东南部的山林地带，辽宁、吉林、黑龙江等地有大量栽培。\n" +
                     "\n" +
                     "【采收加工】多于秋季采挖，洗净。园参经晒干或烘干，称生晒参，山参经晒干，称生晒山参。\n" +
                     "\n" +
                     "【药材性状】生晒参：主根呈纺锤形或圆柱形。表面灰黄色，上部或全体有疏浅断续的粗横纹及明显的纵皱，须根上常有不明显的细小疣状突起。根茎(芦头)具不定根(芋)和稀疏的凹窝状茎痕(芦碗)。质较硬，断面淡黄白色。香气特异，味微苦、甘。生晒山参：主根粗短，多具2个支根而呈人字形。表面灰黄色，上部有明显的细密螺旋纹，根茎具细密碗状茎痕，靠近主根的一段根茎较光滑无茎痕，称圆芦。支根上生有稀疏细长的须状根。上有明显疣状突起。\n" +
                     "\n" +
                     "【性味归经】性温，味甘、微苦。归心经、肺经、脾经、肾经。\n" +
                     "\n" +
                     "【功效与作用】大补元气、复脉固脱、补脾益肺、生津安神。属补虚药下分类的补气药。\n" +
                     "\n" +
                     "【临床应用】用量每天3～9克，另煎对入汤剂服；野山参若研粉吞服，一次2克，一天2次。治疗体虚欲脱、气短喘促、自汗肢冷、精神倦怠、食少吐泻、气虚作喘或久咳、津亏口渴、消渴、失眠多梦、惊悸健忘、阳痿、尿频、一切气血津液不足之症。人参对中枢神经系统有双向调节、促智、镇痛、解热、抗惊和肌力减弱等作用。对心血管系统有强心、抗缺血、扩张血管、降压等作用；对血液系统有保护和刺激造血功能，并抗凝血和抗血栓；对内分泌系统有促皮质激素样、促性激素样作用。\n" +
                     "\n" +
                     "【药理研究】调节中枢神经系统的生理功能，提高机体免疫功能，强心、抗休克，保护和刺激骨髓的造血功能，增加肾上腺皮质激素分泌，调整血糖水平，显著抑制胆固醇的吸收，抗肿瘤，延缓衰老，加强机体的适应性，其提取物能明显促进大鼠器官核酸和蛋白质的合成。\n" +
                     "\n" +
                     "【化学成分】含人参皂苷，其中达玛烷系三萜皂苷活性最显著，为评定人参质量的主要指标，其中主要有人参皂苷Rb₁、人参皂苷Re、人参皂苷Rf及人参皂苷Rg₁等。\n" +
                     "\n" +
                     "【使用禁忌】实热证、湿热证及正气不虚者禁服。不宜与茶同服。不宜与藜芦、五灵脂同用。\n" +
                     "\n" +
                     "【配伍药方】①大补气血，治一切虚烦：人参末、人乳粉等分。蜜丸，或化或吞俱可。(《冯氏锦囊》参乳丸)\n" +
                     "\n" +
                     "②治脾胃肾气虚弱，呕吐不下食：人参、丁香各等分。捣罗为散，每服6克。空心热米饮调下。(《普济方》参散)\n" +
                     "\n" +
                     "③治营卫气血不足：人参9～30克，黄芪(蜜酒炙)9～18克，炙甘草3克。水煎，空心服。(《张氏医通》保元汤)\n" +
                     "\n" +
                     "④治虚劳白汗不止：人参45克，白术60克，桂心21克。每服15克，水煎服。阳虚甚者加附子。(《赤水玄珠》参术散)\n" +
                     "\n" +
                     "⑤治小儿惊热盗汗：人参3克，黄芪6克，当归4.5克。加猪心一片。水煎服。(《婴童类萃》团参饮子)");
             chineseMedicineDao.insert(medicine25);

             ChineseMedicine medicine26=new ChineseMedicine();
             medicine26.setName("忍冬藤");
             medicine26.setSortType("R");
             medicine26.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=cd0172516359252db71a155655f2685e/d0c8a786c9177f3e73f16c4673cf3bc79e3d56b7.jpg");
             medicine26.setData("【中药名】忍冬藤 rendongteng\n" +
                     "\n" +
                     "【别名】金银藤、二花藤、银花身。\n" +
                     "\n" +
                     "【英文名】Lonicerae Japonicae Caulis\n" +
                     "\n" +
                     "【来源】忍冬科植物忍冬Lonicera japonica Thunb.的茎枝。\n" +
                     "\n" +
                     "【植物形态】叶攀缘灌水。幼技密生柔毛和腺毛，叶宽披针形至卵状椭圆形，长3~8cm细时两面被毛。花成对生于叶掖，苞片叶状，边缘具纤毛，萼筒无毛，5裂。花冠二唇形，长3～4cm.先白色略带紫色后变黄包.具芳香，外面被柔毛和腺毛，上唇具4裂片.直立， 下唇反转。雄蕊5，和花柱均稍长于花冠。浆果，球形，黑色。花期6~8月，果期8~10月。\n" +
                     "\n" +
                     "【产地分布】生于山坡灌从、疏林中、乱石堆、田埂、路旁。分布于东北、华北、华南、东南等省份。\n" +
                     "\n" +
                     "【采收加工】秋、冬季采割带叶藤茎，扎成小捆、晒干。\n" +
                     "\n" +
                     "【药材性状】长圆柱形，多分枝，常缠绕成束，表面棕红色至暗棕色，外皮易剥落。枝卜.多节。质脆，断面黄白色，中空。\n" +
                     "\n" +
                     "【性味归经】性寒，味甘。归肺经、胃经。\n" +
                     "\n" +
                     "【功效与作用】清热解毒、疏风通络。属清热药下属分类的清热解毒药。\n" +
                     "\n" +
                     "【临床应用】用量9~30克，水煎服。用治温病发热、热毒血痢、痈肿疮疡、风湿热痹、关节红肿热痛。\n" +
                     "\n" +
                     "【主要成分】茎藤含绿原酸(chlorogenic acid)，异绿原酸(isochlorogenic acid)。地上部分含马钱子甙(loganin)，断马钱子甙二甲基缩醛(secologanin dimethylacetal)，断马钱子甙半缩醛内酯(vogeloside)，表断马钱子甙半缩醛内酯(epi-vogeloside)，常春藤皂甙元-3-O-&alpha；-L-吡喃阿拉伯糖甙(hederagenin-3-O-&alpha；-L-arbinopyranoside)，常春藤皂甙元-3-O-&beta；- D-吡喃葡萄糖基(1&rarr；2)-&alpha；-L-吡喃阿拉伯糖甙[hederagenin-3-O-&beta；-D-glucopyranosyl(1&rarr；2)-&alpha；-L- arabinopyranoside]，常春藤皂甙元-3-O-&alpha；-L-吡喃阿拉伯糖基-28-O-&beta；-D-吡喃葡萄糖基(1&rarr；6)-&beta；-D-吡喃葡萄糖甙[hederagenin-3-O-&alpha；-L-arabinopyranosyl-28-O-&beta；-D-glucopyranosyl(1&rarr；6)- &beta；-D-glucopyranoside]。\n" +
                     "\n" +
                     "【使用禁忌】脾胃虚寒，泄泻不止者禁用。");

             chineseMedicineDao.insert(medicine26);

             ChineseMedicine medicine27=new ChineseMedicine();
             medicine27.setName("榕树叶");
             medicine27.setSortType("R");
             medicine27.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C2%2C907%2C598%3Bc0%3Dbaike116%2C5%2C5%2C116%2C38/sign=575418dfbe096b63955604103103ab76/f31fbe096b63f6243672e6f38744ebf81a4ca317.jpg");
             medicine27.setData("【中药名】榕树叶 rongshuye\n" +
                     "\n" +
                     "【别名】小叶榕、细叶榕、落地金钱。\n" +
                     "\n" +
                     "【英文名】Folium Fici Microcarpae。\n" +
                     "\n" +
                     "【来源】桑科植物榕树Ficus microcarpa Linn.F.的叶。\n" +
                     "\n" +
                     "【植物形态】常绿大乔木，全株无毛，树冠扩大成伞状；树皮具白乳汁；大枝生气根，下垂及地后可发展成支柱根。叶互生，革质或略带肉质，椭圆形、卵状椭圆形或卵形，顶端近短尖，钝头，基部钝或近楔形，全缘；基出脉3条，侧脉纤细，每边有5~6条；托叶披针形。隐头花序，单生或成对着生于叶腋内，或生于无叶老枝上，成熟时球形，红色或黄色，基部附有阔卵形的宿存苞片，总梗极短或无。\n" +
                     "\n" +
                     "【产地分布】栽培于村边、庭院、道旁，亦野生于低海拔的疏林中。分布于广东及我国西南部、南部和东南部各地。\n" +
                     "\n" +
                     "【采收加工】全年可采收。除去树枝，取净叶片，晒干。\n" +
                     "\n" +
                     "【药材性状】本品卷缩成筒状或不规则状，有的破碎。完整叶片展平后呈椭圆形或倒卵形；黄褐色或褐绿色；先端钝，短渐尖，基部钝圆或楔尖，全缘。两面光滑；基出脉3条，主脉腹面微突，背面突起，侧脉纤细，在背面较明显。革质。体轻，稍韧。气微，味苦、涩。\n" +
                     "\n" +
                     "【性味归经】性微寒，味微苦、涩。归肺经、大肠经。\n" +
                     "\n" +
                     "【功效与作用】清热祛湿、止咳化痰、活血散瘀、祛风止痒。属解表药下分类的辛凉解表药。\n" +
                     "\n" +
                     "【临床应用】用量9~15克，煎服；外用鲜品适量捣敷患处。用治感冒高热、湿热泻痢、痰多咳嗽、跌打瘀肿、湿疹、痔疮。\n" +
                     "\n" +
                     "【主要成分】含三萜皂苷、黄酮苷、酸性树脂、鞣质。现代药理研究表明，具抗菌、止血作用。\n" +
                     "\n" +
                     "【使用禁忌】麻风患者忌用,否则皮肤之结节更形表露。");

             chineseMedicineDao.insert(medicine27);

             ChineseMedicine medicine28=new ChineseMedicine();

             medicine28.setName("肉豆蔻");
             medicine28.setSortType("R");
             medicine28.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C0%2C650%2C430%3Bc0%3Dbaike80%2C5%2C5%2C80%2C26/sign=16d1ed0a6c224f4a43d6295334c7bc62/6159252dd42a283490b1ad275ab5c9ea15cebf83.jpg");
             medicine28.setData("【中药名】肉豆蔻 roudoukou\n" +
                     "\n" +
                     "【别名】肉豆叩、肉果、玉果、顶头肉。\n" +
                     "\n" +
                     "【英文名】Myristicae Semen。\n" +
                     "\n" +
                     "【来源】肉豆蔻科植物肉豆蔻Myristica fragrans Houtt.的成熟种仁。\n" +
                     "\n" +
                     "【植物形态】常绿乔木。单叶互生，革质；叶片椭圆状披针形或长圆状披针形，先端渐尖，基部急尖，全缘，上面暗绿色，下面色较淡，并有红棕色的叶脉。总状花序腋生，花单生，异株，小苞片鳞片状，花被钟形，3裂，黄白色；雄蕊8~12枚，花丝联合成圆柱状，花药合生；子房1室，柱头无柄。果实梨形或近圆球形，淡黄色或橙红色，肉质，露出红色肉质的假种皮，内含种子1粒，种皮红褐色，木质坚硬。\n" +
                     "\n" +
                     "【产地分布】热带地区广为栽培。分布于马来西亚、印度尼西亚、巴西等国家。我国云南、海南、广东有栽培。\n" +
                     "\n" +
                     "【采收加工】每年4~6月及11~12月各采收1次。成熟果实剖开果皮，剥下假种皮，击破壳状种皮。直接烘干，或将种仁放入石灰乳中浸1天，然后低温烘干。\n" +
                     "\n" +
                     "【药材性状】卵圆形或椭圆形。表面灰棕色或灰黄色，有时外被白粉(石灰粉末)。全体有浅色纵行沟纹及不规则网状沟纹。种脐位于宽端，呈浅色圆形突起，合点呈暗凹陷。种脊呈纵沟状，连接两端。质坚，断面显棕黄色相杂的大理石花纹，宽端可见干燥皱缩的胚，富油性。气香浓烈，味辛。\n" +
                     "\n" +
                     "【性味归经】性温，味辛。有小毒.归大肠经、胃经、脾经。\n" +
                     "\n" +
                     "【功效与作用】温中行气、涩肠止泻。属收涩药下分类的敛肺涩肠药。\n" +
                     "\n" +
                     "【临床应用】用量3~9克，煎服。用治脾胃虚寒、久泻不止、脘腹胀痛、食少呕吐。肉豆蔻(去壳，为末)l两，生姜汁二合，白面2两。上三味，将姜汁和面做饼，裹肉豆蔻末煨令黄熟，研为细散。每服二钱七，空心米饮调下，日午再服。治水泻无度、肠鸣腹痛(《圣济总录》肉豆蔻散)。\n" +
                     "\n" +
                     "【主要成分】主含挥发油，主要活性成分肉豆蔻醚、黄樟醚。另含脂肪油、淀粉、没食子油酸等其他成分。主要有止泻抗炎、抗血小板凝聚、抗癌、中枢镇静、抗菌等作用。\n" +
                     "\n" +
                     "【使用禁忌】凡使，勿令犯铜(不能用金属物品存放)，不宜用生品。");

             chineseMedicineDao.insert(medicine28);

             ChineseMedicine medicine29=new ChineseMedicine();
             medicine29.setName("日草根");
             medicine29.setSortType("R");
             medicine29.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=599773731dce36d3b6098b625b9a51e2/9e3df8dcd100baa1cf33c8124f10b912c8fc2e7f.jpg");
             medicine29.setData("【药名】日草根 rì； cǎo gēn\n" +
                     "\n" +
                     "【别名】黄花菜根。\n" +
                     "\n" +
                     "【来源】百合科植物大苞萱草Hemerocallis middendorjii Trautv. et Mey.的根。\n" +
                     "\n" +
                     "【植物形态】多年生草本。具短缩的根茎及肉质肥厚的纺锤状块根。叶基生，排成2列，宽线形，长50～80厘米，宽1～3.5厘米，柔软，上部下弯。花葶与叶近等长，不分枝，圆柱形，粗壮，在顶端聚生2～6花；苞片宽卵形；花近簇生，具很短的花梗；花被金黄色至橘黄色；花被管长1～1.7厘米，1/3～2/3为苞片所包，花盛开时，花被裂片长6～8厘米，内三片宽2～3厘米。蒴果椭圆形，稍三钝棱，长约2厘米。花果期6～10月。\n" +
                     "\n" +
                     "【生境分布】生于海拔较低的山沟、溪边或林下阴湿地。分布于黑龙江、吉林和秦岭以南各省区。\n" +
                     "\n" +
                     "【采收加工】春、秋季采挖，洗净，开水烫后晒干。\n" +
                     "\n" +
                     "【药材性状】根茎呈短圆柱形，长0.8～1.0厘米，直径约1厘米。有的上端留有叶残基；根簇生在根茎上，呈绳索状，不膨大，直径1～3毫米，长5～10厘米。上半部有多数横向环纹，表面灰褐色。体轻，质脆，易折断，断面黄褐色，可见放射状裂隙，偶有老根中空，只残存黑褐色皮鞘。气微，味稍甜。\n" +
                     "\n" +
                     "【性味归经】性凉，味甘。\n" +
                     "\n" +
                     "【功效与作用】清热，利尿消肿。现代药理试验结果表明，萱草根具有抗血吸虫病和抗结核病的作用。萱草有一定毒性，主要集中在根部，易于在体内积蓄，因此，注意不要长期服用。如有中毒反应可用黄连、黄檗解除部分毒性。\n" +
                     "\n" +
                     "【临床应用】用量3～6克，水煎服。内服用量过多会损害视力，故以外用为主。临床上多用鲜根捣烂敷患处。\n" +
                     "\n" +
                     "【主要成分】根含大黄酸、大黄酚、钝叶决明素及钝叶决明甲醚，还含萱草根素。\n" +
                     "\n" +
                     "【使用禁忌】暂缺");

             chineseMedicineDao.insert(medicine29);

             ChineseMedicine medicine30=new ChineseMedicine();
             medicine30.setName("牛黄");
             medicine30.setSortType("N");
             medicine30.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D41%2C0%2C2008%2C1326%3Bc0%3Dbaike272%2C5%2C5%2C272%2C90/sign=fbf3d5c4d9a20cf452dfa49f4b3d7a1a/4bed2e738bd4b31c2570c6f88ed6277f9e2ff821.jpg");
             medicine30.setData("【中药名】牛黄 niuhuang\n" +
                     "\n" +
                     "【别名】丑宝、犀黄。\n" +
                     "\n" +
                     "【英文名】Bovis Calculus。\n" +
                     "\n" +
                     "【药用部位】牛科动物牛Bos taurus domesticus Gmelin的胆囊、胆管或肝管中的结石。\n" +
                     "\n" +
                     "【动物形态】大型家畜。体高大壮实，各部发育匀称，头大额广、鼻阔口大。上唇上部有2个大鼻孔，其间皮肤光滑，称为鼻镜。眼极大，头上有角1对，左右分开，角之长短、大小因品种而异。全身被短毛，绝大部分为黄色，但由于品种不同，毛色也有很大的差异。四肢健壮，蹄趾坚硬，尾较长。雌性有乳头2对。\n" +
                     "\n" +
                     "【产地分布】草食性动物，性格温顺，生长较快。我国各地均有饲养。\n" +
                     "\n" +
                     "【采收加工】全年均产。宰牛时注意检查胆囊、胆管及肝管等处有无结石，若有，立即取出，去净附着的薄膜。将牛黄用通草丝、灯心草、棉花或纱布裹上，外用毛边纸包好，置阴凉处，至半干时用红线扎好，以防破裂，阴干。忌风吹、日晒、烘烤。\n" +
                     "\n" +
                     "【药材性状】卵形、类球形、三角形或四方形，大小不一，直径0.6～3厘米，少数呈管状或碎片。表面红黄色至棕黄色，有的表面挂有一层黑色光亮的薄膜，习称“乌金衣”，有的粗糙，具疣状突起，有的具龟裂纹。体轻，质酥脆，易分层剥落，断面金黄色，可见细密同心层纹，有的夹有白心。气清香，味苦而后甘，有清凉感。嚼之易碎，不粘牙。水液能将指甲染成黄色，习称“挂甲”。\n" +
                     "\n" +
                     "【性味归经】性凉，味甘。归心经、肝经。\n" +
                     "\n" +
                     "【功效与作用】清心、豁痰、开窍、凉肝、息风、解毒。属平肝息风药下属分类的息风止痉药。\n" +
                     "\n" +
                     "【临床应用】用量0.15～0.35克，多入丸散；外用适量，研末敷患处。用治热病神昏、中风痰迷、惊痫抽搐、癫痫发狂、咽喉肿痛、口舌生疮、痈肿疔疮。\n" +
                     "\n" +
                     "【药理研究】抑制中枢神经系统，具有镇静、镇痛、解热、抗惊厥的作用；强心，改善心功能，治疗多种心律失常，扩张外周血管，收缩冠状动脉，显著持久的降低血压，尚能抑制血小板聚集；促进胆汁分泌及保护实验性肝损伤，对平滑肌主要表现为解痉作用，其能收缩子宫平滑肌；具有祛痰镇咳，兴奋呼吸作用；增加末梢血内的红细胞；具有抗炎、抗病原微生物、抗氧化及抑制肿瘤生长的作用。实验表明，牛黄具有抗心肌损伤及降压作用，并有利胆及保肝作用。此外，能助脂肪消化，使胰酵素活化，并可与多种有机物结合成稳定的化合物，而起到解毒作用。牛黄一直是通过天然获得，进口较多，价格较昂贵。现采用“体外培育牛黄”技术，使市场需求得到缓解。人工牛黄的性状、结构、成分、含量、药效及临床疗效均与天然牛黄同。\n" +
                     "\n" +
                     "【化学成分】天然牛黄含胆汁酸，其中主要含胆酸、胆甾醇、麦角甾醇、脂肪酸、卵磷脂、胆红素、胆绿素、去氧胆酸、鹅去氧胆酸、维生素D及钙、镁、铁、铜、锌等矿质元素。\n" +
                     "\n" +
                     "【使用禁忌】脾虚便溏及孕妇慎服。\n" +
                     "\n" +
                     "【配伍药方】①治痘疹毒气入骨，便血日夜无度，腹痛啼哭：郁金30克，牛黄3克。上研匀细。每二岁儿1.5克，浆水半盏，煎至三分，和滓温服，日二。(《小儿卫生总微论方》牛黄散)\n" +
                     "\n" +
                     "②治胎毒疮疖及一切疮疡：牛黄9克，甘草、金银花各30克，草紫河车15克。上为末，炼蜜丸，量儿服。(《保婴撮要》牛黄解毒丸)\n" +
                     "\n" +
                     "③治伤寒咽喉痛.心中烦躁，舌上生疮：牛黄(研)、朴硝(研)、甘草(炙、锉)各30克，升麻、山栀子(去皮)、芍药各15克。捣研为细散，再同研令匀。每服3克，食后煎姜蜜汤，放冷调下。(《圣济总录》牛黄散)\n" +
                     "\n" +
                     "④治鹅口疮：牛黄、硼砂、雄黄、黄连(酒炒)各0.6克。上研细末，乳调，鹅毛扫入口中。数次即效。(《医方一盘珠》牛黄散)\n" +
                     "\n" +
                     "⑤治酒糟鼻：牛黄末。水调敷之。(《普济方》)\n" +
                     "\n" +
                     "⑥治初生胎热或身体黄者，兼治腹痛夜啼：牛黄一豆大，入蜜调膏。乳汁化开，时时滴儿口中。(《纲目》引《钱氏小儿方》)\n" +
                     "\n" +
                     "⑦治小儿疟疾烦热：牛黄0.3克，杏仁0.3克(汤浸去皮、尖、双仁，麸炒微黄)。上件药同研如膏，炼蜜和丸，如麻子大。每服以温水下三丸，日三服。(《圣惠方》牛黄丸)");

             chineseMedicineDao.insert(medicine30);

             ChineseMedicine medicine31=new ChineseMedicine();

             medicine31.setName("南天竹叶");
             medicine31.setSortType("N");
             medicine31.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=d69d5350e2f81a4c323fe49bb6430b3c/4034970a304e251f4f6405dcae86c9177f3e5307.jpg");
             medicine31.setData("【中药名】南天竹叶 nantianzhuye\n" +
                     "\n" +
                     "【别名】南竹叶、天竹叶。\n" +
                     "\n" +
                     "【英文名】Nanadinae Folium\n" +
                     "\n" +
                     "【药用部位】小檗科植物南天竹Nandina domestica Thunb.的干燥叶。\n" +
                     "\n" +
                     "【植物形态】常绿灌木，高约2米。茎直立，圆柱形，丛生，分枝少，幼嫩部分常为红色。叶互生，革质有光泽，叶柄基部膨大呈鞘状，叶通常为三回羽状复叶，长 30～50厘米，小叶3～5片，小叶片椭圆状披针形，长3～7厘米，宽1～1.2厘米，先端渐尖，基部楔形，全缘，两面深绿色，冬季常变为红色。花成大型 圆锥花序，长13～25厘米，花直径约6毫米。萼片多数，每轮3片，内两轮呈白色花瓣状，雄蕊6，离生，花药纵裂，子房1室，有2个胚珠，花柱短。浆果球 形，熟时红色或有时黄色，直径6～7毫米，内含种子2颗，种子扁圆形。花期5～7月，果期8～10月。\n" +
                     "\n" +
                     "【产地分布】生长于疏林及灌木丛中，多栽培于庭院。分布于陕西、江苏、安徽、浙江、江西、福建、湖北、湖南、广东、广西、四川、贵州等地。\n" +
                     "\n" +
                     "【采收加工】四季均可采叶，洗净，除去枝梗杂质，晒干。\n" +
                     "\n" +
                     "【药材性状】叶为二至三回羽状复叶，最末的小羽片有小叶3～5枚，小叶椭圆状披针形，长3～10厘米，宽0.5～1厘米，先端渐尖，基部楔形，全缘，表面深绿色或红色。革质。气微，味苦。\n" +
                     "\n" +
                     "【性味归经】性寒，味苦。归经无。\n" +
                     "\n" +
                     "【功效与作用】清热利湿，泻火，解毒。属清热药下分类的清热泻火药。\n" +
                     "\n" +
                     "【临床应用】内服：煎汤，用量9～15克。外用：适量，捣烂涂敷，或煎水洗。主治肺热咳嗽、百日咳、热淋、尿血、目赤肿痛、疮痈、瘰疬。\n" +
                     "\n" +
                     "【药理研究】药理学研究表明，南天竹叶在止咳平喘、抗菌、抗痉挛、降压等方面均有明显的活性。\n" +
                     "\n" +
                     "【化学成分】主要含木兰花碱、维生素C、南天竹氰苷、穗花杉双黄酮、南天竹苷A等成分。\n" +
                     "\n" +
                     "【使用禁忌】尚不明确。\n" +
                     "\n" +
                     "【配伍药方】①治尿路感染：南天竹叶，车前草各15克，木通、扁蓄各9克。水煎服。(《万县中草药》)\n" +
                     "\n" +
                     "②治尿血：南天竹叶9～15克。水煎服。(《浙江药用植物志》)\n" +
                     "\n" +
                     "③去风火热肿，眵泪赤痛：南天竹叶煎水洗眼。(《纲目拾遗》)\n" +
                     "\n" +
                     "④治疮毒：南天竹叶，捣烂敷。(《湖南药物志》)\n" +
                     "\n" +
                     "⑤治瘰疬初起：南天竹叶、威灵仙、夏枯草，金银花各120克，陈酒四壶。水煮透，一日三服。每服药酒，须吞丸药。(《百草镜》)\n" +
                     "\n" +
                     "⑥治风火牙痛：南天竹叶15克，蟋蟀草、铁马鞭各12克。水煎服。(《万县中草药》)\n" +
                     "\n" +
                     "⑦治小儿疳病：南天竹叶、煎汤代茶服。(《纲目拾遗》)");

             chineseMedicineDao.insert(medicine31);

             ChineseMedicine medicine32=new ChineseMedicine();
             medicine32.setName("闹羊花");
             medicine32.setSortType("N");
             medicine32.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=2e0a8280b9a1cd1111bb7a72d87ba399/a8ec8a13632762d021d5c20fa3ec08fa503dc693.jpg");
             medicine32.setData("【中药名】闹羊花 naoyanghua\n" +
                     "\n" +
                     "【别名】黄杜鹃、羊不食草、八厘麻、羊踯躅花、惊羊花、石棠花、黄喇叭花。\n" +
                     "\n" +
                     "【英文名】Rhododendri Mollis Flos。\n" +
                     "\n" +
                     "【药用部位】杜鹃花科植物羊踯躅Rhododendron molteG.Don的花。\n" +
                     "\n" +
                     "【植物形态】落叶灌木，高1～2米。老枝棕褐色，幼枝有柔毛及刚毛，冬芽、叶、花梗、花冠、花丝中部以下及子房都有灰色柔毛。叶纸质，常簇生枝顶，矩圆形或圆状披针形，先端钝，具短尖，边缘有睫毛。伞形总状花序顶生，花可达9朵，几与叶同时开放；花萼5裂，宿存；花冠黄色，5裂，裂片椭圆形至卵形，上面一片较大，有绿色斑点；雄蕊5枚；子房上位，5室，密被长柔毛，花柱长于雄蕊。蒴果长椭圆形，具疏刚毛。花期4～5月，果期9～10月。\n" +
                     "\n" +
                     "【产地分布】生于山坡、灌丛或草丛中。分布于长江流域至南部各地。\n" +
                     "\n" +
                     "【采收加工】4～5月花初开时采收，阴干或晒干。\n" +
                     "\n" +
                     "【药材性状】数朵花簇生于一总柄上，多脱落为单朵，灰黄色至黄褐色，皱缩。花萼5裂，裂片半圆形至三角形，边缘有较长的细毛；花冠钟状，筒部较长，约至2.5厘米，顶端卷折，5裂，花瓣宽卵形，先端钝或微凹；雄蕊5枚，花丝卷曲，等长或略长于花冠，中部以下有茸毛，花药红棕色，顶孔裂；雌蕊1枚，柱头头状；花梗长1～2.8厘米，棕褐色，有短茸毛。气微，味微麻。\n" +
                     "\n" +
                     "【性味归经】性温，味辛。归肝经。\n" +
                     "\n" +
                     "【功效与作用】祛风除湿、散瘀定痛。属祛风湿药下属分类的祛风湿强筋骨药。\n" +
                     "\n" +
                     "【临床应用】用量0.6～1.5克，浸酒或入丸散；外用适量，煎水洗或鲜品捣敷。用治风湿痹痛、跌打损伤、皮肤顽癣。有毒，中毒的症状恶心、呕吐、腹泻、心跳缓慢、血压下降、动作失调、呼吸困难，严重者因呼吸停止致死。\n" +
                     "\n" +
                     "【药理研究】具有明显镇痛作用；有较强的抗菌作用及杀虫作用；对心血管系统，具有显著的降压效果，能对抗部分心律失常，对心脏无直接抑制作用；对横纹肌有先兴奋后麻痹作用，对高级神经中枢有麻醉作用，但对脊髓无明显影响；对迷走神经末梢也有先兴奋后麻痹作用，并能兴奋兔支气管和肠平滑肌，此外，尚有中枢性催吐作用及对枪乌贼轴突膜有去极化等作用。\n" +
                     "\n" +
                     "【化学成分】含棱木毒素和石楠素。并分得羊踯躅素Ⅲ、闹羊花毒素Ⅲ、木藜芦毒素Ⅲ和日本杜鹃素Ⅲ、山月桂萜醇。\n" +
                     "\n" +
                     "【使用禁忌】不宜多服、久服，体虚者及孕妇禁用。有大毒，须经医生许可后用。\n" +
                     "\n" +
                     "【配伍药方】①治神经性头痛、偏头痛：鲜闹羊花捣烂，外敷后脑或痛处2～3小时。(《浙江民间常用草药》)\n" +
                     "\n" +
                     "②治跌打损伤：闹羊花6克，小驳骨30克，泽兰60克。共捣烂，用酒炒热，敷患处。(《广西中草药》)\n" +
                     "\n" +
                     "③治风虫牙痛：闹羊花3克，草乌头7.5克。为末，化蜡丸豆大。绵包一丸，咬之。追涎。(《海上仙方》)\n" +
                     "\n" +
                     "④治疠疮初起：闹羊花(酒拌、酒蒸、晒)、草乌(酒浸，炒)、白矾、黄蜡(溶化)各等分。上为末、加蜜少许，丸蔔子大。每服五六十丸，酒下。(《解围元薮》)\n" +
                     "\n" +
                     "⑤治男妇头痛，不论偏正新久，但夏月欲重绵包裹者并效：闹羊花(净末)3克，槿树花(净末)3克，大风子(白肉去油)1.5克。共研。每服1.8克，葱、酒调服，洗浴发汗自愈。(《外科正宗》三圣散)\n" +
                     "\n" +
                     "⑥治皮肤顽癣及瘙痒：鲜闹羊花15克，捣烂敷患处。(《闽东本草》)\n" +
                     "\n" +
                     "⑦治瘌痢头：鲜闹羊花擦患处；或晒干研粉调麻油涂患处。(《浙江民间常用草药》)\n" +
                     "\n" +
                     "⑧治疟疾：闹羊花0.3克，嫩松树梢15克。水煎服。(《湖南药物志》)");

             chineseMedicineDao.insert(medicine32);

             ChineseMedicine medicine33=new ChineseMedicine();
             medicine33.setName("南鹤虱");
             medicine33.setSortType("N");
             medicine33.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=2510f94ecd1b9d169eca923392b7dfea/a71ea8d3fd1f4134c4dc6eef261f95cad0c85eb3.jpg");
             medicine33.setData("【中药名】南鹤虱 nanheshi\n" +
                     "\n" +
                     "【别名】窃衣子、野萝卜子、鹤虱。\n" +
                     "\n" +
                     "【英文名】Caroule Fructus\n" +
                     "\n" +
                     "【药用部位】伞形科植物野胡萝卜Daucus carotaL.的果实。\n" +
                     "\n" +
                     "【植物形态】 二年生草本，高20～120厘米。茎直立，表面有白色粗硬毛。根生叶有长柄，基部鞘状；叶片2～3回羽状分裂，最终裂片线形或披针形；茎生叶的叶柄较短。复伞形花序顶生或侧 生，有粗硬毛，伞梗15～30或更多；总苞片5～8，叶状，羽状分裂，裂片线形，边缘膜质，有细柔毛；小苞片数枚，不裂或羽状分裂；小伞形花序有花15～25，花小，白色、黄色或淡紫红色，花瓣5，大小不等，先端凹陷；子房下位。双悬果卵圆形，分果的主棱不明显，次棱4条。\n" +
                     "\n" +
                     "【产地分布】生于路旁、山沟、溪边、荒地等处。分布于全国各地。\n" +
                     "\n" +
                     "【采收加工】秋季果实成熟时采摘。晒干，除去皮屑、杂质。\n" +
                     "\n" +
                     "【药材性状】 椭圆形，多已分裂成两瓣，长3～4毫米，宽1.5～2.5毫米，表而棕黄色或黄褐色，顶端有残留花柱，基部圆形，偶有小果柄。果瓣被面隆起，有4条突起的纵棱线，其上密生一列黄白色钩刺。结合面稍平坦，有3条纵脉纹，脉上有短柔毛。横切面略呈半圆形，每一棱线内方有1个油管，结合面有2个油管。种仁类白色。具特殊香气。\n" +
                     "\n" +
                     "【性味归经】性平，味辛、微苦。归脾经、胃经。\n" +
                     "\n" +
                     "【功效与作用】有驱虫功能。属驱虫药。\n" +
                     "\n" +
                     "【临床应用】用量6～15克。治疗蛔虫病、绦虫病、蛲虫病、虫积腹痛。\n" +
                     "\n" +
                     "【药理研究】扩张冠状动脉，降血压；舒张小肠、气管平滑肌，收缩子宫；抑制呼吸；抗惊厥等作用。\n" +
                     "\n" +
                     "【化学成分】果实含白杨素、芹菜素、木樨草素、山柰酚、槲皮素等黄酮类化合物，还含挥发油、细辛醚、柑油烯、a-姜黄烯、芳樟醇、亚油酸、油酸等成分。挥发油中含成分50种左右，主要为8-杜松烯和菖蒲烯，两者共占43.12%。\n" +
                     "\n" +
                     "【使用禁忌】尚不明确。\n" +
                     "\n" +
                     "【配伍药方】①治蛔虫病、绦虫病、绕虫病：南鹤虱6克。研末水调服。(《湖北中草药志》)\n" +
                     "\n" +
                     "②治钩虫病：南鹤虱45克，浓煎两(次)汁合并，加白糖适量调味，晚上临睡前服，连用2剂。(《浙江药用植物志》)\n" +
                     "\n" +
                     "③治虫积腹痛：南鹤虱9克，南瓜子、槟榔各15克。水煎服。(《湖北中草药志》)\n" +
                     "\n" +
                     "④治蛲虫病肛痒：南鹤虱、花椒、白鲜皮各15克，苦楝根皮9克。水煎，趁热熏洗或坐浴。(《浙江药用植物志》)\n" +
                     "\n" +
                     "⑤治阴痒：南鹤虱6克。煎水熏洗阴部。(《湖北中草药志》)");

             chineseMedicineDao.insert(medicine33);

             ChineseMedicine medicine34=new ChineseMedicine();
             medicine34.setName("南五味子");
             medicine34.setSortType("N");
             medicine34.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=0f1a13f019d5ad6ebef46cb8e0a252be/78310a55b319ebc46a445ead8026cffc1e1716c1.jpg");
             medicine34.setData("【中药名】南五味子 nanwuweizi\n" +
                     "\n" +
                     "【别名】香苏、红铃子、玄及，会及，五梅子，山花椒。\n" +
                     "\n" +
                     "【英文名】Schiandra Sphenanthera。\n" +
                     "\n" +
                     "【药用部位】木兰科植物华中五味子Schisandra sphenanthera Rehd.et Wils.的成熟果实。\n" +
                     "\n" +
                     "【植物形态】落叶木质藤本。茎皮灰褐色，皮孔明显，小枝褐色，稍具棱角。叶互生，柄细长；叶片薄而带膜质；卵形，阔倒卵形以至阔椭圆形，先端长，基部楔形，阔楔形至圆形，边缘有小齿牙，上面绿色，下面淡黄色，有芳香。花单性，雌雄异株，花被片橙黄色；雄花具长梗，椭圆形，雄蕊10～15，基部合生；雌花雌蕊具心皮30～50枚，螺旋状排列在花托上，子房倒梨形，无花柱，受粉后花托逐渐延长呈穗状。浆果球形，外皮棕红色至暗棕色，干瘪肉薄，内含种子1～2枚。花期4～6月，果期8～9月。\n" +
                     "\n" +
                     "【产地分布】生于海拔600～2400米的林中或溪沟边。分布于河南、江苏、浙江、安徽等地。\n" +
                     "\n" +
                     "【采收加工】霜降后果实完全成熟时采摘，拣去果枝及杂质，晒干或阴干。\n" +
                     "\n" +
                     "【药材性状】类球形或扁球形，直径4～6毫米。表面棕红色至暗棕色，果皮肉质较薄，干瘪，皱缩，果肉常紧贴种子上，种子1～2粒，肾形，有光泽，表面黄棕色，略呈颗粒状。果肉气微，味微酸。种子破碎后有香气。\n" +
                     "\n" +
                     "【性味归经】性温，味酸、甘。归肺经、心经、肾经。\n" +
                     "\n" +
                     "【功效与作用】收敛固涩、益气生津、补肾宁心。属收涩药分类下的敛肺涩肠药。\n" +
                     "\n" +
                     "【临床应用】用量3～6克。用治久咳虚喘、津伤口渴及消渴、自汗、盗汗、遗精、滑精、久泻不止、心悸、失眠、多梦及慢性肝炎转氨酶升高者等。\n" +
                     "\n" +
                     "【药理研究】近年来发现五味子多糖有保肝、抗衰老、耐缺氧和增加机体免疫力等作用。对中枢神经系统有兴奋作用，同时能直接兴奋呼吸中枢；还直接作用于脊髓的运动细胞，增强脊髓反射，缩短反射潜伏期；可加强心脏的收缩力，调节心血管系统而改善血液循环；能兴奋子宫，使子宫节律性收缩加强，并有明显止咳、祛痰作用；五味子乙素等成分对肝细胞有一定的保护作用。\n" +
                     "\n" +
                     "【化学成分】含有挥发油，含量较高的有花侧柏烯、罗汉松烯等；此外，还含有多种木脂素、三萜酸、β-榄香烯苷、α-檀香烯、脱氧五味子素、五味子酯甲、五味子酯乙、五味子酯丙、五味子酯丁、五味子酯戊、当归酰戈米辛P、巴豆酰戈米辛P等成分。\n" +
                     "\n" +
                     "【使用禁忌】凡表邪未解，内有实热，咳嗽初起，麻疹初期，均不宜用。\n" +
                     "\n" +
                     "【配伍药方】①治痰嗽并喘：南五味子、白矾等分，为末。每服9克，以生猪肺炙熟，蘸末细嚼，白汤下。(《普济方》)\n" +
                     "\n" +
                     "②治阳痿不起：南五味子、菟丝子、蛇床子等分。上三味末之，蜜丸如梧桐子大。饮服三丸，日三。(《千金要方》)\n" +
                     "\n" +
                     "③治滑泄：陈米、肉豆蔻(面裹煨)、南五味子、赤石脂(研)各30克。上为末。每服6克，粟米汤饮调下，日进三服。(《世医得效方》豆蔻饮)\n" +
                     "\n" +
                     "④治肾泄：南五味子(拣)60克，吴茱萸(细粒绿色者)15克。上二味同炒香为度，细末。每服6克，陈米饮下。(《本事方》五味子散)\n" +
                     "\n" +
                     "⑤治睡中盗汗：南五味子30克，研末，以唾调作饼。敷脐上，以布扎定后睡，候天明取下，一二晚汗即止。(《医方一盘珠》)");

             chineseMedicineDao.insert(medicine34);

             ChineseMedicine medicine35=new ChineseMedicine();
             medicine35.setName("南瓜子");
             medicine35.setSortType("N");
             medicine35.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=8faa2ecc4b36acaf4ded9eae1db0e675/9825bc315c6034a81a9ad574cb134954082376ce.jpg");
             medicine35.setData("【中药名】南瓜子 nanguazi\n" +
                     "\n" +
                     "【别名】南瓜仁、白瓜子、金瓜米、窝瓜子。\n" +
                     "\n" +
                     "【英文名】Semen Cucurbitae Moschatae\n" +
                     "\n" +
                     "【药用部位】葫芦科植物南瓜Cucurbita moschata (Duch.ex Lam.) Duch.ex Poir.的干燥种子。\n" +
                     "\n" +
                     "【植物形态】一年生蔓生草本，茎长达2～5米。常节部生根。密被白色刚毛。单叶互生；叶柄粗壮，长8～19厘米，被刚毛；叶片宽卵形或卵圆形，有5角或5浅裂，长12～25厘米，宽20～30厘米，先端尖，基部深心形，上面绿色，下面淡绿色，两面均被刚毛和茸毛，边缘有小而密的细齿。卷须稍粗壮，被毛，3～5歧。花单性，雌雄同株，雄花单生，花萼筒钟形，长5～6毫米，裂片条形，长10～15毫米，被柔毛，上部扩大成叶状，花冠黄色，钟状，长约8厘米，5中裂，裂片边缘反卷，雄蕊3，花丝腺体状，长5～8毫米，药室折曲；雌花单生，子房1室，化柱短，柱头3，膨大，先端2裂，果梗粗壮，有棱槽，长5～7厘米，瓜蒂扩大成喇叭状。瓠果形状多样，外面常有纵沟。种子多数，长卵形或长圆形，灰白色。花期6～7月，果期8～9月。\n" +
                     "\n" +
                     "【产地分布】全国各地普遍栽培。\n" +
                     "\n" +
                     "【采收加工】夏、秋季食用南瓜时，收集成熟种子，除去瓤膜，洗净，晒干。\n" +
                     "\n" +
                     "【药材性状】种子扁圆形，长1.2～1.8厘米，宽0.7～1厘米，表面淡黄白色至淡黄色，两面平坦而微隆起，边缘稍有棱，一端略尖，先端有珠孔，种脐稍突起或不明显。除去种皮，有黄绿色薄膜状胚乳。子叶2枚，黄色，肥厚，有油性。气微香，味微甘。\n" +
                     "\n" +
                     "【性味归经】性平，味甘。归大肠经。\n" +
                     "\n" +
                     "【功效与作用】杀虫，下乳，利水消肿。属利水渗湿药下属分类的利水消肿药。\n" +
                     "\n" +
                     "【临床应用】内服：煎汤，30～60克；研末或制成乳剂。外用：适量，煎水熏洗。主治绦虫、蛔虫、血吸虫、钩虫、蛲虫病，产后缺乳，产后手足浮肿，百日咳，痔疮。\n" +
                     "\n" +
                     "【药理研究】有驱虫、抗血吸虫、升压、兴奋呼吸中枢、抑制回肠平滑肌收缩等作用。\n" +
                     "\n" +
                     "【化学成分】本品主要含亚油酸、油酸、棕榈酸、硬脂酸、三酰甘油、磷脂酰已醇胺、南瓜子氨酸等成分。\n" +
                     "\n" +
                     "【使用禁忌】脾胃虚寒者禁服。\n" +
                     "\n" +
                     "【配伍药方】①治小儿蛔虫：南瓜子30克，韭菜叶30克，水竹沥60克。开水冲服。(《湖南药物志》)\n" +
                     "\n" +
                     "②治产后缺奶：南瓜子60克。研末，加红糖适量，开水冲服。(《青岛中草药手册》)\n" +
                     "\n" +
                     "③治产后手脚浮肿，糖尿病：南瓜子30克。炒熟，水煎服。(《食物中药与便方》)\n" +
                     "\n" +
                     "④治内痔：南瓜子1000克，煎水熏之。每日2次，连熏数日。(《岭南草药志》)");

             chineseMedicineDao.insert(medicine35);

             ChineseMedicine medicine36=new ChineseMedicine();

             medicine36.setName("牛蒡子");
             medicine36.setSortType("N");
             medicine36.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=58a4cb3b0cf41bd5ce5ee0a630b3eaae/91529822720e0cf3f27062eb0846f21fbe09aa1e.jpg");
             medicine36.setData("【中药名】牛蒡子 niu bang zi\n" +
                     "\n" +
                     "【别名】大力子、恶实、鼠粘子、鼠尖子。\n" +
                     "\n" +
                     "【药用部位】菊科植物牛蒡Arctium lappaL.的成熟果实。\n" +
                     "\n" +
                     "【植物形态】二年生草本，高1～2米。根粗壮，肉质，圆锥形。茎直立，上部多分枝，带紫褐色，有纵条棱。基生叶大形，丛生，有长柄；茎生叶互生；叶片长卵形或广卵形，先端钝，具刺尖，基部常为心形，全缘或具不整齐波状微齿，上面绿色或暗绿色，有疏毛，下面密被灰白色短茸毛。头状花序簇生于茎顶或排列成伞房状，花序梗表面有浅沟，密生细毛；总苞球形，苞片多数，覆瓦状排列，披针形或线状披针形，先端钩曲；花小，红紫色，均为管状花，两性，花冠先端5浅裂；聚药雄蕊5枚；子房下位，1室，花柱细长，柱头2裂。瘦果长圆形或长圆状倒卵形，灰褐色，具纵棱，冠毛短刺状，淡黄棕色。花期6～8月，果期8～10月。\n" +
                     "\n" +
                     "【产地分布】常栽培；野生时，多生长于山野路旁、沟边、荒地、山坡向阳草地、林边和村镇附近。分布于东北三省、华北等地。\n" +
                     "\n" +
                     "【采收加工】秋季果实成熟时，割除地上部分或剪除果穗，晒干后打下果实，筛去泥土等杂质，再晒干。炒制：取净牛蒡子，文火炒至微黄色、微鼓起而微有香气即得。\n" +
                     "\n" +
                     "【药材性状】瘦果呈长倒卵形，两端平截，略扁，微弯，长5～7毫米，直径2～3毫米。表灰褐色或浅灰褐色，具多数细小黑斑，并有明显的纵棱线。顶端较宽，有一圆环，中心有点状突起的花柱残迹；基部狭窄，有圆形果柄痕。果皮较硬，果实折断后可见子叶2片，淡黄白色，富油性。果实气无；种子气特异，味苦微辛，久嚼稍麻舌。以粒大、饱满、色灰褐者为佳。\n" +
                     "\n" +
                     "【性味归经】性寒，味辛、苦。归肺经、胃经。\n" +
                     "\n" +
                     "【功效与作用】疏散风热、宣肺透疹、消肿解毒。属解表药下分类的辛凉解表药。\n" +
                     "\n" +
                     "【临床应用】煎汤内服，用量4.5～9克；或入散剂；外用适量，煎汤含漱。用于风热感冒、咳嗽、咽喉肿痛、麻疹、荨麻疹、腮腺炎、痈肿疮毒等症；对猩红热的预防、面神经麻痹也有一定的疗效。\n" +
                     "\n" +
                     "【药理研究】抗菌抗病毒；降血糖；降压；抗肾病；轻度利尿，泻下。据有关报道，牛蒡子苷元具有抗肾炎的作用；牛蒡子苷和拉帕酚A、C、F及牛蒡子牛蒡子苷元、粗提物等体外对人子宫癌细胞JTC-26及人正常胎儿成纤维细胞HE-1的增值有一定的抑制作用，与多种化疗药合用可减少或组织抗癌药耐药性的增加。\n" +
                     "\n" +
                     "【化学成分】本品含新牛蒡乙素、牛蒡苷、牛蒡苷元、牛蒡酚、亚油酸、棕榈酸、硬脂酸、花生酸糖等成分。\n" +
                     "\n" +
                     "【使用禁忌】脾虚便溏者禁服。\n" +
                     "\n" +
                     "【配伍药方】①治风热闭塞咽喉，遍身浮肿：牛蒡子一合，半生半熟，杵为末。热酒调下3克。(《经验方》)\n" +
                     "\n" +
                     "②治痦疹不起透：牛蒡子(研细)15克。柽柳煎汤，调下立透。(《本草汇言》)\n" +
                     "\n" +
                     "③治皮肤风热，遍身瘾疹：牛蒡子、浮萍等分。以薄荷汤调下6克，日二服。(《养生必用方》)\n" +
                     "\n" +
                     "④治风肿斑毒作痒：牛蒡子、玄参、僵蚕、薄荷各15克。为末，每服9克，白汤调下。(《方脉正宗》)\n" +
                     "\n" +
                     "⑤治斑疹时毒及痄腮肿痛：牛蒡子、柴胡、连翘、川贝母、荆芥各6克。水煎服。(《本草汇言》)");

             chineseMedicineDao.insert(medicine36);

             ChineseMedicine medicine37=new ChineseMedicine();

             medicine37.setName("女贞子");
             medicine37.setSortType("N");
             medicine37.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=70d2b9afa5c3793169658e7b8aaddc20/3b87e950352ac65c181a1ddaf1f2b21193138a7f.jpg");
             medicine37.setData("【中药名】女贞子 nvzhenzi\n" +
                     "\n" +
                     "【别名】冬青子、女贞实、白蜡树子、鼠梓子。\n" +
                     "\n" +
                     "【英文名】Fructus Ligustri Lucidi、glossy privet fruit。\n" +
                     "\n" +
                     "【来源】木犀科植物女贞Ligustrum lucidum Ait.的成熟果实。\n" +
                     "\n" +
                     "【植物形态】常绿大灌木或小乔木。树皮灰色，枝条光滑，具皮孔。叶对生，叶柄上面有槽；叶片革质，卵形至卵状披针形，先端渐尖至锐尖，基部阔楔形，全缘，上面深绿色，有光泽，下面淡绿色，密布细小的透明腺点，主脉明显。圆锥花序顶生；总花梗长；苞片叶状，线状披针形，无柄，早落，小苞卵状三角形；花萼钟状，4浅裂；花冠管约与萼片等长，裂片4，长方卵形，白色；雄蕊2，着生于花冠管喉部，花丝细，伸出花冠外；雌蕊1，子房上位，球形2室，花柱圆柱状，柱头浅2裂。浆果状核果，长椭圆形，幼时绿色，熟时蓝黑色；种子1～2枚，长椭圆形。花期6～7月，果期8～12月。\n" +
                     "\n" +
                     "【产地分布】生于温暖潮湿的地区或山坡向阳处。主产于浙江、江苏、福建等地。\n" +
                     "\n" +
                     "【采收加工】冬季果实成熟时采收，稍蒸或置沸水中略烫后，干燥。生用或酒制用。\n" +
                     "\n" +
                     "【药材性状】多数椭圆形或肾形，长径4～8毫米，短径2.5～4毫米。表面黑紫色或棕黑色，皱缩不平，基部有果梗或具宿萼及短梗。外果皮薄，中果皮稍厚而松软，内果皮木质，显黄棕色，表面有数个纵棱。横切面子房2室，每室有种子l枚，另一常不发育。种子椭圆形，一侧扁平或微弯曲。商品中尚有少数果实具2粒种子，其果实宽椭圆形，不弯曲，长径7～10毫米，短径5～6毫米，表面皱缩较少，种子椭圆形，两种子结合面略平。气微，味微酸涩。\n" +
                     "\n" +
                     "【性味归经】性凉，味甘、苦。归肝经、肾经。\n" +
                     "\n" +
                     "【功效与作用】滋补肝肾、明目乌发。属补虚药分类下的补阴药。\n" +
                     "\n" +
                     "【临床应用】用量6～12克，煎汤内服；或入丸剂；外用：适量，敷膏点眼。用治肝肾阴虚而致耳鸣、耳聋、头晕、腰膝酸软、须发早白等。\n" +
                     "\n" +
                     "【药理研究】本品有升高外周白细胞、增强网状内皮系统吞噬能力、增强细胞免疫和体液免疫的作用；对化疗或放疗所致的白细胞减少有升高作用；有强心、利尿及保肝作用；并有止咳、抗炎、促进免疫功能、抑制变态反应、预防动脉粥样硬化、降血糖、抗诱变和抗血卟啉微生物光氧化、缓泻、抗菌、抗癌等作用。\n" +
                     "\n" +
                     "【化学成分】主要活性成分为三萜类化合物，主要有齐墩果酸、乙酰齐墩果酸、β-谷固醇、乙酰熊果酸、槲皮素、洋丁香酚苷、女贞苷、新女贞子苷等成分。\n" +
                     "\n" +
                     "【使用禁忌】脾胃虚寒及肾阳不足者禁服。效力和缓宜少量久服。\n" +
                     "\n" +
                     "【配伍药方】①治脂溢性脱发：女贞子、何首乌、菟丝子、当归各10克。水煎服，每日1剂，连服2个月。(《四川中药志》1979年》\n" +
                     "\n" +
                     "②治阴虚骨蒸潮热：女贞子、地骨皮各9克，青蒿、夏枯草各6克。煎服。(《安徽中草药》)\n" +
                     "\n" +
                     "③治白细胞减少症：炙女贞子、龙葵各45克。煎服。(《安徽中草药》)\n" +
                     "\n" +
                     "④治风热赤眼：女贞子不拘多少。捣汁重汤熬膏，净瓶收固，每用点眼。(《济急仙方》)\n" +
                     "\n" +
                     "⑤治视神经炎：女贞子、草决明、青葙子各30克。水煎服。(《浙江民间常用草药》)");

             chineseMedicineDao.insert(medicine37);

             ChineseMedicine medicine38=new ChineseMedicine();
             medicine38.setName("糯稻根");
             medicine38.setSortType("N");
             medicine38.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=105953af0ff3d7ca18fb37249376d56c/cdbf6c81800a19d83445909430fa828ba71e46d8.jpg");
             medicine38.setData("【中药名】糯稻根 nuodaogen\n" +
                     "\n" +
                     "【别名】糯稻根须、稻根须、糯谷根、糯稻草根。\n" +
                     "\n" +
                     "【英文名】Radix oryzae glutinosae\n" +
                     "\n" +
                     "【来源】禾本科植物糯稻Oryza sativea L.var.glutinosa Matsum.的干燥根及根茎。\n" +
                     "\n" +
                     "【植物形态】一年生草本，高1米左右。秆直立，圆柱状。叶鞘与节问等长，下部者长过节间，叶舌膜质而较硬，狭长披针形，基部两侧下延与叶鞘边缘相结合，叶片扁平披针形，长25～60厘米，宽5～15毫米，幼时具明显叶耳。圆锥花序疏松，颖片常粗糙，小穗长圆形，通常带褐紫色，退化外稃锥刺状，能育外稃具5脉，被细毛，有芒或无芒，内稃3脉，被细毛；鳞被2，卵圆形，雄蕊6，花柱2，柱头帚刷状，自小花两侧伸出。颖果平滑。粒饱满，稍圆，色较白。花、果期7～8月。\n" +
                     "\n" +
                     "【产地分布】我国南部和中部各地均有栽培。\n" +
                     "\n" +
                     "【采收加工】夏、秋两季，糯稻收割后，挖取根茎及须根，除去残茎，洗净，晒干。\n" +
                     "\n" +
                     "【药材性状】本品全体集结成疏松的团状，上端有分离的残茎，圆柱形，中空，长2.5～6.5厘米，外包数层灰白色或黄白色的叶鞘，下端簇生多数须根。须根细长而弯曲，直径1毫米。表面黄白色至黄棕色，表皮脱落后显白色，略具纵皱纹。体轻，质软，气微，味淡。\n" +
                     "\n" +
                     "【性味归经】性平，味甘。归肺经、肾经。\n" +
                     "\n" +
                     "【功效与作用】养阴除热，止汗。属收涩药下分类的固表止汗药。\n" +
                     "\n" +
                     "【临床应用】内服：煎汤，15～30克，大剂量可用60～120克。主治阴虚发热、自汗盗汗、口渴咽干、肝炎、丝虫病。\n" +
                     "\n" +
                     "【化学成分】本品主要含胱氨酸、组氨酸等多种氨基酸及葡萄糖、果糖、山柰素成分。\n" +
                     "\n" +
                     "【使用禁忌】尚无。\n" +
                     "\n" +
                     "【配伍药方】①治阴虚盗汗：糯稻根、乌枣各60克，红糖30克，水煎服。(《福建药物志》)\n" +
                     "\n" +
                     "②治肝炎：糯稻根、紫参各62克。加糖适量煎服。(南药《中草药学》)\n" +
                     "\n" +
                     "③治丝虫病(乳糜尿)：糯稻根250～500克，可酌加红枣。水煎服。(南药《中草药学》)\n" +
                     "\n" +
                     "④治鼻衄：糯稻根30克，水车前15克。水煎服。(《福建药物志》)");

             chineseMedicineDao.insert(medicine38);

             ChineseMedicine medicine39=new ChineseMedicine();

             medicine39.setName("南板蓝根");
             medicine39.setSortType("N");
             medicine39.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=098254abd639b60059c307e588395e4f/b90e7bec54e736d13362db4098504fc2d5626900.jpg");
             medicine39.setData("【中药名】南板蓝根 nanbanlangen\n" +
                     "\n" +
                     "【别名】广东板蓝根、马蓝根、板蓝根、蓝靛根。\n" +
                     "\n" +
                     "【英文名】Baphicacanthus cusia (Nees) Bremek.\n" +
                     "\n" +
                     "【来源】爵床科植物马蓝的根茎及根。\n" +
                     "\n" +
                     "【植物形态】多年生草本。茎基部稍木质化，多分枝，茎节明显，嫩枝被褐色细软毛。叶对生，叶片倒卵状长圆形至卵状长圆形，先端渐尖，基部渐窄，边缘浅锯齿，侧脉4～8对。穗状花序，着生枝顶；苞片叶状，早落；花萼裂片5，外被短柔毛；花冠筒状漏斗形，淡紫色，花冠筒近中部略向下弯曲，先端5裂；雄蕊4枚，2强；子房上位，花柱细长，被毛。蒴果。\n" +
                     "\n" +
                     "【产地分布】生于山谷、疏林下阴湿地方，多为栽培。分布于福建、广东、四川，长江流域以南各地均有产。\n" +
                     "\n" +
                     "【采收加工】夏、秋季采挖，除去地上茎，洗净，晒干。\n" +
                     "\n" +
                     "【药材性状】根茎类圆形，多弯曲，有分枝。表面灰棕色，具细纵纹；节膨大，节上长有细根或茎残基；外皮易剥落，呈蓝灰色。质硬而脆，易折断，断面不平坦，皮部蓝灰色，木部灰蓝色至淡黄褐色，中央有髓。根粗细不一，弯曲有分枝，细根细长柔韧。气微，味淡。\n" +
                     "\n" +
                     "【性味归经】味苦，性寒。归心经、胃经。\n" +
                     "\n" +
                     "【功效与作用】清热解毒，凉血消肿。属清热药下属中的清热解毒药。\n" +
                     "\n" +
                     "【临床应用】9～15克，煎服。用治温病发斑、丹毒、流感、流脑，临床主要用治病毒性及细菌性疾病，如乙型肝炎、水痘、扁桃体炎、咽炎等。\n" +
                     "\n" +
                     "【药理研究】水煎剂具一定的抑菌作用，体外对病毒增殖有抑制作用，还具解热、抗炎作用。\n" +
                     "\n" +
                     "【化学成分】全草含三萜类。靛玉红有抗癌活性-(3H)-喹唑酮有抗炎、抗高血压活性，根含吲哚苷、大黄酚及多种氨基酸。另含靛苷、靛玉红羽扇豆酮、羽扇豆醇、白桦脂醇、β-谷固醇等成分。\n" +
                     "\n" +
                     "【使用禁忌】脾胃虚寒、无实火热毒者慎用。\n" +
                     "\n" +
                     "【配伍药方】①治流行性腮腺炎：南板蓝根30克，或配金银花、蒲公英各15克，水煎服;外用鲜马蓝叶捣敷。(《浙南本革新编》)\n" +
                     "\n" +
                     "②治喉痛：南板蓝根30克，开喉箭30克，山豆根30克，马勃9克。煎水服。(《重庆草药》)\n" +
                     "\n" +
                     "③预防小儿喘憋性肺炎：南板蓝根、金银花、一枝黄花，4～7岁各用4.5克，3岁以下各用3克。水煎，每日分3～4次服。(《浙南本草新编》)\n" +
                     "\n" +
                     "④治夏季微热，经久不退：南板蓝根30克，柴胡9克，体虚者加北沙参或孩儿参9克。水煎。每日1剂，连服7～10天。(《浙南本草新编》)\n" +
                     "\n" +
                     "⑤治热毒疮：南板蓝根30克，银花藤30克，蒲公英30克，土茯苓15克。炖肉服。(《重庆草药》)\n" +
                     "\n" +
                     "附1 大叶青\n" +
                     "\n" +
                     "【来源】爵床科植物马蓝的叶。夏、秋季摘取叶或带幼枝的叶，晒干。\n" +
                     "\n" +
                     "【药材性状】多皱缩呈不规则团块状，稍破碎，有时带小枝。展平后呈长椭圆形或倒卵状长圆形。绿色或灰绿色；先端渐尖，基部楔形下延，叶缘有细小钝锯。中脉于背面突出明显。纸质，质脆易碎。气微弱，味淡。\n" +
                     "\n" +
                     "【性味功效】性苦，味寒。清热解毒，凉血消斑。用量9~15克，煎服。用治病毒性及细菌性疾病、扁平疣、腮腺炎、黄疸性肝炎、流脑、带状疹等。");

             chineseMedicineDao.insert(medicine39);
             ChineseMedicine medicine40=new ChineseMedicine();

             medicine40.setName("南沙参");
             medicine40.setSortType("N");
             medicine40.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=64b2ada36e63f62408503151e62d809d/810a19d8bc3eb135a5579121a31ea8d3fc1f4493.jpg");
             medicine40.setData("【中药名】南沙参 nanshashen\n" +
                     "\n" +
                     "【别名】沙参、知母、白沙参、泡参、土人参、志取。\n" +
                     "\n" +
                     "【英文名】Adenophorae Radix。\n" +
                     "\n" +
                     "【来源】桔梗科植物轮叶沙参Adenophora tetraphylla(Thunb.)Fisch.或沙参Adenophor a stricta Miq.的干燥根。\n" +
                     "\n" +
                     "【植物形态】多年生草本，高30～150厘米，有白色乳汁。主根粗壮，圆锥形，黄褐色，有皱纹。茎通常单生，在花序之下不分枝，无毛或少有毛。基生叶丛生，卵形，长椭圆形或近圆形，茎生叶4～6片轮生，无柄或有短柄，叶片卵形，椭圆状卵形，狭倒卵形或披针形，长4～8厘米，宽1.5～3厘米，边缘有锯齿，两面疏毛，上面绿色，下面淡绿色。花序圆锥状，长可达35厘米，下部花枝轮生，顶部花枝有时互生，花下垂，花萼光滑无毛，5裂，裂片钻形，花冠蓝色或蓝紫色，狭钟形，口部缢缩成坛状，长约1厘米，5浅裂，雄蕊5，稍伸出花冠，花丝下部变宽，边缘有密柔毛，子房上部具肉质花盘，花柱明显伸出花冠外。蒴果卵圆形，长约5毫米，孔裂。种子多数，黄棕色。花期7～9月，果期8～10月。\n" +
                     "\n" +
                     "【产地分布】生于阳坡草丛，林缘，路边，分布于黑龙江、吉林、辽宁、内蒙古、河北、山西、山东、安徽、江苏、浙江、江西、福建、广东、广西、四川、云南、贵州等省区。\n" +
                     "\n" +
                     "【采收加工】春、秋二季采挖，除去须根，洗后趁鲜刮去粗皮，洗净，干燥。\n" +
                     "\n" +
                     "【药材性状】呈圆锥形或圆柱形，略弯曲，长7～27厘米，直径0.8～3厘米。表面黄白色或淡棕黄色，凹陷处常有残留粗皮，上部多有深陷横纹，呈断续的环状，下部有纵纹和纵沟。顶端具1或2个根茎。体轻，质松泡，易折断，断面不平坦，黄白色，多裂隙。气微，味微甘。\n" +
                     "\n" +
                     "【性味归经】性微寒，味甘。归肺经、胃经。\n" +
                     "\n" +
                     "【功效与作用】养阴清肺、益胃生津、化痰、益气。属补虚药下属分类的补阴药。\n" +
                     "\n" +
                     "【临床应用】用量9～15克，水煎服，或入丸、散。治疗肺热燥咳，阴虚劳嗽，干咳痰黏，胃阴不足，食少呕吐，气阴不足，烦热口干。\n" +
                     "\n" +
                     "【药理研究】具有调节免疫平衡的功能，有祛痰、抗真菌、强心作用。\n" +
                     "\n" +
                     "【化学成分】根中分离得4个化合物，它们是β-谷甾醇，β-谷甾醇-β-D-吡喃葡萄糖甙，蒲公英赛酮及二十八碳酸。\n" +
                     "\n" +
                     "【使用禁忌】不宜与藜芦同用，风寒咳嗽禁服。\n" +
                     "\n" +
                     "【配伍药方】①治慢性支气管炎，咳嗽，痰不易吐出，口干：南沙参9克，麦冬9克，生甘草6克，玉竹9克。水煎服。(《青岛中草药手册》)\n" +
                     "\n" +
                     "②治肺热咳嗽：沙参15克，水煎服之。(《卫生易简方》)\n" +
                     "\n" +
                     "③治虚火牙痛：沙参根15～60克，煮鸡蛋服。(《湖南药物志》)\n" +
                     "\n" +
                     "④治产后无乳：沙参根12克，煮猪肉食。(《湖南药物志》)\n" +
                     "\n" +
                     "⑤治睾丸肿痛：沙参60克，猪肚一个，炖服，也可加豆腐同煮服。(《福建药物志》)");

             chineseMedicineDao.insert(medicine40);

             ChineseMedicine medicine41=new ChineseMedicine();
             medicine41.setName("牛膝");
             medicine41.setSortType("N");
             medicine41.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=54aa069b710e0cf3b4fa46a96b2f997a/5243fbf2b21193137486543966380cd790238d76.jpg");
             medicine41.setData("【中药名】牛膝 niuxi\n" +
                     "\n" +
                     "【别名】怀牛膝、对节菜、山苋菜、铁平膝、怀夕、红牛膝。\n" +
                     "\n" +
                     "【英文名】Radix Achyranthis Bidentatae 。\n" +
                     "\n" +
                     "【来源】苋科植物牛膝Achyranthes bidentata Bl.的根。\n" +
                     "\n" +
                     "【植物形态】多年生草本。高30～100厘米。根细长，圆柱形。茎直立，四棱形，茎节略膨大，疏被柔毛。叶对生，叶片椭圆形或倒卵圆形，楔形或广楔形，全缘，两面被柔毛。穗状花序腋生和顶生，长可达10厘米；花向下折贴近总花梗，总花梗被柔毛。苞片1，膜质，宽卵形，先端突尖成刺；小苞片2，坚刺状，基部两侧各具卵状膜质小裂片；花被片5，绿色，披针形，多具1脉，边缘膜质；雄蕊5枚，花丝下部于子房合生，与退化雄蕊联为杯状，退化雄蕊短于花丝，舌状，顶端平圆或浅波状；子房上位。胞果长圆形。花期7～8月，果期9～11月。\n" +
                     "\n" +
                     "【产地分布】生于山坡林下及路旁。分布于陕西、山西、山东等地。\n" +
                     "\n" +
                     "【采收加工】冬季茎叶枯萎时采挖，除去须根及泥沙，捆成小把，晒至干皱后，将顶端切齐，晒干。\n" +
                     "\n" +
                     "【药材性状】细长圆柱形，多顺直，有的稍弯曲，长15～50( 90)厘米，直径0.4～1厘米。表面灰黄色或淡棕色，有略扭曲而细微的纵皱纹、横长皮孔及稀疏的细根痕。质硬而脆，易折断，受潮则变柔软，断面平坦，黄棕色，微呈角质样而油润，中心维管束木部较大，黄白色，其外围散有多数点状维管束，排列成2～4轮。气微，味微甜而稍苦涩。\n" +
                     "\n" +
                     "【性味归经】性平，味苦、酸。归肝经、肾经。\n" +
                     "\n" +
                     "【功效与作用】补肝肾、强筋骨、逐瘀通经、引血下行。属活血化瘀药下属分类的活血调经药。\n" +
                     "\n" +
                     "【临床应用】用量4.5～9克，水煎服。用治腰膝酸痛、筋骨无力、经闭癥瘕、肝阳眩晕。\n" +
                     "\n" +
                     "【药理成分】现代药理作用证实，有抗生育作用，对子宫平滑肌有较强的兴奋作用。提取物有抗炎镇痛作用和降血糖、降血脂等作用。对免疫功能正常或低下动物均有免疫增强作用，对细胞免疫和体液免疫均能增强。此外，还证实有延缓衰老和抗肿瘤的作用。对心脏有一定抑制作用，可扩张血管、降压，具有抗溃疡、抗炎镇痛、利胆、兴奋子宫、抗生育、抗凝血、抗瘀、降血糖、降脂、蛋白同化、增强免疫力、延缓衰老、利尿等作用。\n" +
                     "\n" +
                     "【主要成分】含皂苷。三萜皂苷水解后得齐墩果酸、葡萄糖醛酸等;另含蜕皮甾酮、牛膝甾酮、β-蜕皮甾酮、红苋甾酮、β-谷固醇、丝氨酸、谷氨酸、尿囊素、琥珀酸及有免疫活性的牛膝肽多糖ABAB。\n" +
                     "\n" +
                     "【使用禁忌】凡中气下陷，脾虚泄泻，下元不固，梦遗滑精，月经过多及孕妇均禁服。\n" +
                     "\n" +
                     "【配伍药方】①治冷痹脚膝疼痛无力：牛膝(酒浸，切焙)30克，桂(去粗皮)15克，山茱萸30克。上三味，捣罗为散。每服空心温酒下6克，日再服。(《圣济总录》牛膝散)\n" +
                     "\n" +
                     "②治高血压：牛膝、生地黄各15克，白芍、茺蔚子、菊花各9克、水煎服。(《新疆中草药》)\n" +
                     "\n" +
                     "③治痢下先赤后白：牛膝90克。捣碎，以酒一升，渍经一宿，每服饮两杯，日三服。(《肘后方》)\n" +
                     "\n" +
                     "④治龋齿：牛膝(烧为灰)30克。上细研为末，以少许着齿间，含之。(《圣惠方》)\n" +
                     "\n" +
                     "⑤治口及舌上生疮烂：牛膝(去苗)30克，上细锉，以水一中盏，酒半盏，同煎至七分。去滓，放温时时呷服。(《圣惠方》)");

             chineseMedicineDao.insert(medicine41);

             ChineseMedicine medicine42=new ChineseMedicine();

             medicine42.setName("南山楂");
             medicine42.setSortType("N");
             medicine42.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=d2bc3a772e738bd4d02cba63c0e2ecb3/4a36acaf2edda3cc74d1fd8a01e93901213f9266.jpg");
             medicine42.setData("【中药名】南山楂 nanshanzha\n" +
                     "\n" +
                     "【别名】猴楂子、酸枣、大山枣。\n" +
                     "\n" +
                     "【英文名】Fructus Crataegi Cuneatae。\n" +
                     "\n" +
                     "【来源】蔷薇科植物湖北山楂Crataegus hupehensis Sarg.的果实。\n" +
                     "\n" +
                     "【植物形态】乔木或灌木，高3～5米。小枝紫褐色，无毛，有刺。叶三角状卵形至卵形，长4～9厘米，宽4～7厘米，先端短渐尖，基部宽楔形或近圆形，边缘有圆钝重锯齿，上半部有2～4对浅裂片，无毛或仅下面脉腋有髯毛；叶柄长3.5～5厘米，无毛。伞房花序，总花梗和花梗均无毛；花白色，直径约1厘米，萼筒钟状，外面无毛，裂片三角状卵形，全缘；花瓣卵形。梨果近球形，直径2.5厘米，深红色，有斑点，萼片宿存，小核5枚。花期5～7月，果期8～10月。\n" +
                     "\n" +
                     "【产地分布】分布于河南、山西、江苏、浙江、江西、湖北、湖南、陕西等地。\n" +
                     "\n" +
                     "【采收加工】秋季果实成熟时采摘，晒干或压成饼状后再晒干。\n" +
                     "\n" +
                     "【药材性状】果实近球形，直径2.5厘米，多横切成两瓣。表面深红色，顶端有圆形凹窝，基部有短果梗或柄痕；果肉薄，果皮常皱缩，种子5枚，土黄色，内面两侧平滑。质坚硬。气微弱，味微涩。\n" +
                     "\n" +
                     "【性味归经】性微温，味酸、甘。归脾经、胃经、肝经。\n" +
                     "\n" +
                     "【功效与作用】消积化滞，破气散瘀。属理气药。\n" +
                     "\n" +
                     "【临床应用】用量6～12克。用于肉食积滞，小儿乳积，脘腹胀痛，痢疾，泄泻，痛经，产后瘀血腹痛，疝气，高血脂症。药理作用表明，对心血管系统有降压、增加冠脉流量、降血脂等作用。此外，尚有抗菌消炎等作用。\n" +
                     "\n" +
                     "【主要成分】从果实中分离得到槲皮素、金丝桃苷( hyperin)、表儿茶精( epicatechin)、绿原酸(chlorogenic)、枸橼酸及其甲酯类以及黄烷聚合物。\n" +
                     "\n" +
                     "【使用禁忌】脾胃虚弱者慎服。");

             chineseMedicineDao.insert(medicine42);

             ChineseMedicine medicine43=new ChineseMedicine();

             medicine43.setName("牛至");
             medicine43.setSortType("N");
             medicine43.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=13e65819bc3eb13550cabfe9c777c3b6/a5c27d1ed21b0ef48839ecafddc451da80cb3e69.jpg");
             medicine43.setData("【中药名】牛至 niuzhi\n" +
                     "\n" +
                     "【别名】土香薷、土茵陈、白花茵陈。\n" +
                     "\n" +
                     "【英文名】Herba Origani。\n" +
                     "\n" +
                     "【来源】唇形科植物牛至Origanum vulgareL.的全草。\n" +
                     "\n" +
                     "【植物形态】多年生草本。高25~60厘米，茎基部木质，上部有分枝，四棱形，多少带紫色，被柔毛。叶对生，叶片卵圆形，先端钝，基部宽楔形至圆形，全缘，两面具柔毛及腺点。伞房状圆锥花序，苞片长圆状倒披针形。萼钟状，内部喉部有毛环；花冠紫红色或白色，花柱顶端2裂。小坚果，卵圆形，棕褐色，光滑。花、果期7~11月。\n" +
                     "\n" +
                     "【产地分布】生于向阳山坡草地、路边及林缘。分布于江苏、浙江、广东、贵州、四川、云南、新疆、甘肃等地。\n" +
                     "\n" +
                     "【采收加工】夏末秋初开花时采收，将全草齐根头割起，或将全草连根拔起，抖净泥土，晒干后扎成小把。\n" +
                     "\n" +
                     "【药材性状】根圆柱形，表面灰棕色。茎四方形，表面浅棕紫色或浅棕色，密被倒向卷曲的微柔毛。叶对生，稍皱缩，易脱落、破碎。完整叶片呈卵形或长圆状卵形，顶端钝，基部宽楔形、近圆形或浅心形，边全缘或有疏的小锯齿，黄绿或灰绿色，两面均被柔毛和凹陷的腺点。伞房状花序顶生或腋生，苞片倒卵形，花萼钟状，顶端具5齿，外面被小硬毛或近无毛。小坚果卵圆形，近无毛。质脆，易折断。气芳香，味微苦。\n" +
                     "\n" +
                     "【性味归经】性凉，味辛、微苦。归肺经、胃经、肝经。\n" +
                     "\n" +
                     "【功效与作用】解表、理气、清暑、利湿。属解表药下属分类的辛凉解表药。\n" +
                     "\n" +
                     "【临床应用】用量3~9克，大剂量用至15~30克或泡茶，煎服；外用适量，煎水洗，或鲜品捣敷。用治感冒发热、中暑、胸膈胀满、腹痛吐泻、痢疾、黄疸、水肿、带下、小儿疳积、麻疹、皮肤瘙痒、疮疡肿痛、跌打损伤。\n" +
                     "\n" +
                     "【主要成分】含有较大量的香芹酚(通常在精油中占55%~80%的比例)，还含有少量的芳香酚。其挥发油具有明显的镇痛、镇静和抑菌作用。\n" +
                     "\n" +
                     "【使用禁忌】尚不明确。");

             chineseMedicineDao.insert(medicine43);

             ChineseMedicine medicine44=new ChineseMedicine();
             medicine44.setName("牛大力");
             medicine44.setSortType("N");
             medicine44.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=213d21fc8c18367ab984778f4f1ae0b1/0df431adcbef7609da5a754a24dda3cc7dd99e96.jpg");
             medicine44.setData("【中药名】牛大力 niudali\n" +
                     "\n" +
                     "【别名】甜牛大力、山葛、大力薯。\n" +
                     "\n" +
                     "【英文名】Radix Millettiae Speciosae。\n" +
                     "\n" +
                     "【来源】豆科植物美丽崖豆藤Millettia speciosa Champ.的根。\n" +
                     "\n" +
                     "【植物形态】藤状灌木。根肥壮，肠状或不规则念珠状，近肉质而多纤维。嫩枝被褐色茸毛，老枝无毛。叶为奇数羽状复叶，叶柄及叶轴均被茸毛；小叶7~17片，薄革质，长圆形或长圆状披针形；顶生小叶通常最大，顶端短尖或短渐尖，钝头，基部钝或圆，边常背卷，上面光亮无毛，下面干时为暗褐色，被茸毛或无毛；托叶钻状。花白色，为腋生的总状花序，有时数个总状花序结成顶生大型圆锥花序，花序轴、花梗和花萼均被茸毛；萼钟状；花冠蝶形，旗瓣圆形，基部有2个胼胝状附属物；雌蕊被子绒毛。荚果线状长圆形或近线形。\n" +
                     "\n" +
                     "【产地分布】生于山谷、路旁、疏林及灌木丛中。分布于广东、广西、海南等地。\n" +
                     "\n" +
                     "【采收加工】全年均可采收。挖取根部，除去芦头及细根，洗净，大个的趁鲜纵向切厚片或斩为短段，晒干。\n" +
                     "\n" +
                     "【药材性状】原个的为纺锤形或圆柱形，有的2~3个呈串珠状连在一起。表皮土黄色，稍粗糙，有环状横纹。质坚实，不易折断。切成短段或片块的横切面皮部类白色，向内有一圈不甚明显的环纹，嫩根中间白色至黄白色，具粉性；老根及直根多为圆柱形，近木质化，质坚硬。气微，味微甜。\n" +
                     "\n" +
                     "【性味归经】性平，味甘。归肺经、脾经、肾经。\n" +
                     "\n" +
                     "【功效与作用】补脾润肺、舒筋活络。属祛风湿药下分类的祛风湿强筋骨药。\n" +
                     "\n" +
                     "【临床应用】用量15~30克，煎服。用治病后体弱、阴虚咳嗽、腰肌劳损、风湿痹痛及肺结核咳嗽。\n" +
                     "\n" +
                     "【主要成分】含生物碱类。根煎剂或乙醇提取物灌服，对由于氨水喷雾引咳的小鼠有明显的止咳作用。\n" +
                     "\n" +
                     "【使用禁忌】尚不明确。");

             chineseMedicineDao.insert(medicine44);

             ChineseMedicine medicine45=new ChineseMedicine();
             medicine45.setName("牛耳枫");
             medicine45.setSortType("N");
             medicine45.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=b70a9a0839dbb6fd3156ed74684dc07d/42a98226cffc1e176c52a2c24a90f603728de950.jpg");
             medicine45.setData("【中药名】牛耳枫 niuerfeng\n" +
                     "\n" +
                     "【别名】老虎耳、南岭虎皮楠、假楠木。\n" +
                     "\n" +
                     "【英文名】Herba Daphniphylli。\n" +
                     "\n" +
                     "【来源】交让木科植物牛耳枫Daphniphyllum calycinum Benth.的带叶茎枝。\n" +
                     "\n" +
                     "【植物形态】常绿灌木。单叶互生，革质，宽椭圆形至倒卵形，先端钝或近圆形，有时急尖，基部宽楔形或近圆形，全缘；边缘背卷。上表面绿色，下表面有白色细小乳头状突起，侧脉明显。总状花序腋生，单性，雌雄异株；花小，花被萼状，宿存；雄花花被片3~4，雄蕊9~10，花丝极短；雌花子房为不完全的2室，花柱短，柱头2。核果卵圆形，有瘤状突起，常被白粉。花期4～6月，果期6~10月。\n" +
                     "\n" +
                     "【产地分布】常生长于海拔100~700米的山区疏林下、灌木丛或溪沟边。分布于江西、广东、广西、福建、湖南等地。\n" +
                     "\n" +
                     "【采收加工】夏秋二季采收，切段，晒干或鲜用。\n" +
                     "\n" +
                     "【药材性状】茎枝呈圆柱形，表面灰绿色或浅灰棕色，体轻质硬，可折断，断面灰白色或浅棕色，有疏松髓部或髓部中空。单叶互生，完整叶片展开后呈宽椭圆形至倒卵形；先端钝或急尖，全缘，边缘背卷。上表面草绿色至浅灰棕色；下表面浅绿色至灰棕色，叶脉下表面突起，侧脉明显。革质。气微，味微苦。\n" +
                     "\n" +
                     "【性味归经】性凉，味辛、甘，有小毒。归肝经、肾经。\n" +
                     "\n" +
                     "【功效与作用】清热解毒、祛风活血、止痛消肿。种仁含富马酸，与琥珀酸合用，在体外有微弱的抑菌作用。灌胃每千克体重60毫克，对小鼠肉瘤S180有抑制作用。此外，还有镇痛、抗电休克和镇咳作用。\n" +
                     "\n" +
                     "【临床应用】用量9~15克，煎服；外用适量，鲜叶捣烂敷或煎水洗患处。用于感冒发热、扁桃体炎、风湿关节痛；外用治跌打肿痛、骨折、毒蛇咬伤、疮疡肿毒。\n" +
                     "\n" +
                     "【主要成分】含生碱、牛耳枫碱、牛耳枫碱乙、牛耳枫碱丙等，还含少量反丁烯二酸等。\n" +
                     "\n" +
                     "【使用禁忌】孕妇忌用。");

             chineseMedicineDao.insert(medicine45);

             ChineseMedicine medicine46=new ChineseMedicine();
             medicine46.setName("扭肚藤");
             medicine46.setSortType("N");
             medicine46.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=b557fd90890a19d8df0e8c575293e9ee/2f738bd4b31c8701d86316c72e7f9e2f0708ff3e.jpg");
             medicine46.setData("【中药名】扭肚藤 niuduteng\n" +
                     "\n" +
                     "【别名】白花茶、假素馨、猪肚勒。\n" +
                     "\n" +
                     "【英文名】Cacumen Et Folium Jasmini Elongati\n" +
                     "\n" +
                     "【来源】木犀科植物扭肚藤Jasminum elongatum (Bergius) Wud.的嫩茎及叶。\n" +
                     "\n" +
                     "【植物形态】缠绕木质藤本。小枝圆柱形，疏被短柔毛至密被黄褐色茸毛。单叶对生，膜质，卵形至卵状披针形；先端短尖或锐尖，基部近圆形或微心形，两面有柔毛或除下面叶脉外近无毛。聚伞花序密集，顶生或腋生，通常生于侧枝顶端，有花多朵；花白色，微香；花萼有柔毛，裂片8枚，线形，比萼筒长2倍以上；花冠筒裂片8枚，矩圆状条形，锐尖。浆果球状，直径6~7毫米，成熟时黑色。花期4~12月，果期8月至翌年3月。\n" +
                     "\n" +
                     "【产地分布】生于丘陵地和山地的灌丛中或疏林中。分布于广东、广西、海南、贵州等地。\n" +
                     "\n" +
                     "【采收加工】夏季采收，洗净切段，晒干备用。\n" +
                     "\n" +
                     "【药材性状】茎呈圆柱形，长3~5厘米，直径1~5毫米；表面绿棕色或淡褐色，粗枝光滑，幼枝茶褐色被疏毛，或近光滑，节部稍膨大。质坚，断面粗糙，木部白色，中央具有明显的髓部或形成空洞。叶对生，具叶柄，叶片卵状披针形，绿褐色，稍有光泽，基部浑圆，略呈心形，全缘，质脆易碎。\n" +
                     "\n" +
                     "【性味归经】味微苦，性凉。归胃经、大肠经。\n" +
                     "\n" +
                     "【功效与作用】清热解毒、利湿消滞。属清热药下分类的清热燥湿药。\n" +
                     "\n" +
                     "【临床应用】用量15~30克，煎服。外用适量，煎水洗或研末撒或捣敷。主治湿热泻痢、食滞脘胀、风湿热痹、瘰疬、疮疥。\n" +
                     "\n" +
                     "【主要成分】本品含断环烯醚苷类化合物。\n" +
                     "\n" +
                     "【使用禁忌】孕妇禁用。");

             chineseMedicineDao.insert(medicine46);

             ChineseMedicine medicine47=new ChineseMedicine();

             medicine47.setName("昆布");
             medicine47.setSortType("K");
             medicine47.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545028140&di=5733b5cc6f392081ab52116fc3bcb09c&imgtype=jpg&er=1&src=http%3A%2F%2Foss.huangye88.net%2Flive%2Fuser%2F2168856%2F1492500618037766900-0.jpg");
             medicine47.setData("【中药名】昆布 kunbu\n" +
                     "\n" +
                     "【别名】海带、海白菜、江白菜、纶布、海昆布。\n" +
                     "\n" +
                     "【英文名】Laminariae Thallus、Eckloniae Thallus。\n" +
                     "\n" +
                     "【药用部位】海带科植物海带Laminaria japonica Aresch.或翅藻科植物昆布Ecklonia kurome Okam.的叶状体。(右图为昆布)\n" +
                     "\n" +
                     "【植物形态】海带：多年生大型褐藻，藻体分为根状固着器，柄部和片部。片部绿褐色，完整者带状，扁平柔滑，边缘深波状，基部具细短轴柄，柄下端生有树枝状假根，附着于海底岩石上。昆布：多年生大型褐藻。片部宽大，厚革质，暗褐色，扁平近扁圆形，羽状深裂，裂片条状披针形，有时再羽状深裂，边缘常有疏浅齿；孢子囊群在表面生长，片部之下有细长圆柱形柄，柄基有数轮2叉分支的固着器。\n" +
                     "\n" +
                     "【产地分布】海带生于低潮线下2～3米深的岩石上或人工培植，分布于辽宁、山东、浙江等沿海地区。昆布生于低潮线附近的岩礁上，分布于浙江、福建沿海地区。\n" +
                     "\n" +
                     "【采收加工】夏秋季采捞，晒干后，扎成小把。\n" +
                     "\n" +
                     "【药材性状】海带：卷曲折叠呈团状，或缠结成把。全体呈黑褐色或绿褐色，表面附有白霜，用水浸软后则呈扁平长带状，长50～150厘米，宽10～40厘米，中部较厚，边缘较薄而呈波状。类革质，残存柄部扁圆柱状。气腥，味咸。昆布：常卷曲皱缩呈不规则团块，全体呈黑色，较薄。用水浸软后则膨胀呈扁平的叶状，长宽16～26厘米，厚约1.6毫米；两侧呈羽状深裂，裂片呈长舌状。边缘有小齿或全缘。质柔滑。\n" +
                     "\n" +
                     "【性味归经】性寒，味咸。归肝经、胃经、肾经。\n" +
                     "\n" +
                     "【功效与作用】软坚散结、消痰、利水。属化痰止咳平喘药下属分类的止咳平喘药。\n" +
                     "\n" +
                     "【临床应用】用量6～12克。水煎服或入丸散。用治瘿瘤、瘰疬、睾丸肿痛、痰饮水肿。有降压、抑制平滑肌、止咳平喘、预防白血病、止血、清除血脂等功能。也用于治疗地方性甲状腺肿和脚气水肿。\n" +
                     "\n" +
                     "【药理研究】能降低血脂，有明显的抗凝血作用，显著增强机体免疫功能，还有抗肿瘤、降血糖、松弛肠道平滑肌、抗辐射及促进造血等作用，能纠正缺碘引起的甲状腺机能不足。\n" +
                     "\n" +
                     "【化学成分】主要含多糖类成分、藻胶酸、昆布素、甘露醇、无机盐。昆布含藻胶酸、粗蛋白、甘露醇、灰分、钾、碘。另含褐藻酸盐、岩藻依多糖、鹅掌菜酚、荜澄茄油烯、己醛等成分。\n" +
                     "\n" +
                     "【使用禁忌】脾胃虚寒蕴湿者忌服。\n" +
                     "\n" +
                     "【配伍药方】①治瘿气初结，咽喉中壅闷，不治即渐渐肿大：槟榔90克，海藻(洗去咸)90克，昆布(洗去咸水)90克。上药，捣罗为末，炼蜜和丸，如小弹子大。常含一丸咽津。(《圣惠方》)\n" +
                     "\n" +
                     "②治甲状腺肿：昆布、海蜇、牡蛎各30克，夏枯草15克。煎服。(《中国药用海洋生物》)\n" +
                     "\n" +
                     "③治颈淋巴结核：昆布、夏枯草各18克，海藻15克，青皮、白芥子各9克。水煎服。(《青岛中草药手册》)\n" +
                     "\n" +
                     "④治脚气水肿：昆布、海藻、泽泻、桑白皮、防己各适量。水煎服。(《浙江药用植物志》)\n" +
                     "\n" +
                     "⑤治高血压：海带30克，决明子15克。水煎服。(《中国药用海洋生物》)\n" +
                     "\n" +
                     "⑥治气管炎，咳嗽，肺结核：昆布500克，百部500克，知母(蜜炙)1000克，用50%乙醇浸泡1星期，回收乙醇，加蒸馏水至5000毫升每次10毫升，每日3次。(《中国药用海洋生物》)");

             chineseMedicineDao.insert(medicine47);

             ChineseMedicine medicine48=new ChineseMedicine();
             medicine48.setName("苦木");
             medicine48.setSortType("K");
             medicine48.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=16183ec24c086e067ea5371963611091/7acb0a46f21fbe09c5e261e76b600c338644ad81.jpg");
             medicine48.setData("【中药名】苦木 kumu\n" +
                     "\n" +
                     "【别名】黄楝瓣树、狗胆木、苦木霜、苦胆木、熊胆树、黄楝树。\n" +
                     "\n" +
                     "【英文名】Picrasmae Ramulus Et Folium。\n" +
                     "\n" +
                     "【药用部位】苦木科植物苦木Picrasm,a quassioides(D.Don)Benn.的干燥枝和叶。\n" +
                     "\n" +
                     "【植物形态】落叶小乔木或灌木，高7～10米。树皮有苦味，紫棕色，平滑，有灰色斑纹。单数羽状复叶互生，小叶9～15，叶卵状披针形或宽卵形，长4～10厘米，宽2～4.5厘米，先端锐尖，基部楔形，偏斜(除顶生小叶外)，边缘有钝锯齿，叶两面无毛，但下面脉上幼时有柔毛。花杂性异株。聚伞花序腋生，总花梗长，有柔毛，花黄绿色，簇生，雄花萼片4～5，背面有细毛，花瓣4～5，卵形或宽卵形，与萼片对生，雄蕊4～5。雌花较雄花小，雌花萼片，花瓣与雄花相等，心皮4～5。核果倒卵形，成熟时蓝绿色至红色。花期5～6月。果期8～9月。\n" +
                     "\n" +
                     "【产地分布】生于海拔1400～3200米的树木中。分布于辽宁、河北、山西、陕西、甘肃、河南、湖北、湖南、江西、山东、江苏、安徽、浙江、福建、台湾、广东、广西、海南、贵州、四川、西藏、云南。\n" +
                     "\n" +
                     "【采收加工】夏、秋二季采收，干燥。\n" +
                     "\n" +
                     "【药材性状】枝呈圆柱形，长短不一，直径0.5～2厘米；表面灰绿色或棕绿色，有细密的纵纹和多数点状皮孔；质脆，易折断，断面不平整，淡黄色，嫩枝色较浅且髓部较大。叶为单数羽状复叶，易脱落；小叶卵状长椭圆形或卵状披针形，近无柄，长4～16厘米，宽1.5～6厘米；先端锐尖，基部偏斜或稍圆，边缘具钝齿；两面通常绿色，有的下表面淡紫红色，沿中脉有柔毛。气微，味极苦。\n" +
                     "\n" +
                     "【性味归经】性寒，味苦。归肺经、大肠经。\n" +
                     "\n" +
                     "【功效与作用】 清热解毒，祛湿。属清热药下属分类的清热解毒药。\n" +
                     "\n" +
                     "【临床应用】用量6～15克，大剂量30克，煎汤内服，或入丸、散，外用：适量，煎水洗；研末撒或调敷；或浸酒搽。用治风热感冒，咽喉肿痛，湿热泻痢，湿疹，疮疖，蛇虫咬伤。\n" +
                     "\n" +
                     "【药理研究】可增加胃肠血流量，具有抗癌作用。体外有抗单纯性疱疹病毒的活性。对银环蛇毒中毒有保护作用。\n" +
                     "\n" +
                     "【化学成分】含苦木碱A～I、苦木西碱C～E、苦木内酯A～M，并含苦木酮、甲基苦木酮等。\n" +
                     "\n" +
                     "【使用禁忌】有小毒，内服不宜过量，孕妇慎服。\n" +
                     "\n" +
                     "【配伍药方】①治阿米巴痢疾：苦木茎枝15克，石榴皮15克，竹叶椒根9克。水煎，分2次服。(《浙江药用植物志》)\n" +
                     "\n" +
                     "②治菌痢：苦木茎枝9～15克。研粉，分3～4次吞服。(《浙江药用植物志》)\n" +
                     "\n" +
                     "③治局部化脓性感染和预防外伤感染：苦木500克，粉碎过120目筛，与凡士林500克制成软膏。化脓处先用苦木水洗净，外敷，每日1～2次。[《中草药通讯》1977，(9)28]\n" +
                     "\n" +
                     "④治疮疖，体癣，湿疹：苦木树茎适量，水煎外洗。(《广西本草选编》)");

             chineseMedicineDao.insert(medicine48);

             ChineseMedicine medicine49=new ChineseMedicine();
             medicine49.setName("苦楝皮");
             medicine49.setSortType("K");
             medicine49.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=8c7fc111d933c895b2739029b07a1895/b8014a90f603738d2a2cbaa4b31bb051f919ecd6.jpg");
             medicine49.setData("【中药名】苦楝皮 kulianpi\n" +
                     "\n" +
                     "【别名】川楝皮、楝木皮、楝皮、楝根皮、苦楝根皮。\n" +
                     "\n" +
                     "【英文名】Meliae Cortex。\n" +
                     "\n" +
                     "【药用部位】楝科植物川楝Melia toosendan Sieb.et Zucc.的树皮及根皮。\n" +
                     "\n" +
                     "【植物形态】乔木。树皮灰褐色，有纵沟纹，幼嫩部分密被星状鳞片。2回奇数羽状复叶互生；小叶片长卵圆形；花淡紫色或紫色；花萼及花瓣均为5～6，花盘环状；雄蕊数为花瓣的2倍，花丝连合成一管；雌蕊1枚，子房上位，瓶状，6～8室。核果椭圆形或近球形，黄色或黄棕色；种子3～5粒；种子扁平长椭圆形，黑色。花期3～4月，果期9～11月。\n" +
                     "\n" +
                     "【产地分布】生于疏林潮湿处，分布于四川、湖北、湖南等地。\n" +
                     "\n" +
                     "【采收加工】春、秋季剥取皮晒干，或除去粗皮晒干。\n" +
                     "\n" +
                     "【药材性状】树皮稍呈槽状，少数呈卷筒状。外表面灰褐色或灰棕色，粗糙，有纵裂纹及细的横纹，可见横向皮孔。除去粗皮者淡黄色，内表面黄白色。质韧，不易折断，断面纤维性，呈层片状，可剥离。无臭，味苦。根皮全形狭长，不规则条块状、卷筒状、槽状、厚2～3毫米，木栓层常呈鳞片状，剥落后呈砖红色。余同树皮。\n" +
                     "\n" +
                     "【性味归经】性寒，味甘。归胃经、脾经、肝经。\n" +
                     "\n" +
                     "【功效与作用】驱虫、疗癣。属驱虫药。\n" +
                     "\n" +
                     "【临床应用】用量4.5～9克，煎服或入丸散；外用适量，煎水洗或研末调敷患处。用治蛔虫和蛲虫病、虫积腹痛；外治疥癣瘙痒。\n" +
                     "\n" +
                     "【药理研究】具有驱虫作用；抑制呼吸中枢；影响神经肌肉传递功能；对肉毒中毒具有治疗作用，可影响心肌电和机械特性。对实验性曼氏血吸虫病有一定疗效。\n" +
                     "\n" +
                     "【化学成分】含有苦楝素及川楝素、苦楝酮、苦楝内酯、苦楝萜酮内酯、苦洛内酯、山柰酚、苦楝子三醇及鞣质。另外，含β-谷甾醇、正三十烷及水溶性成分。\n" +
                     "\n" +
                     "【使用禁忌】弱及肝肾功能障碍者、孕妇及脾胃虚寒者均慎服。亦不宜持续和过量服用。苦楝皮有一定的毒性，服药中毒后可有头痛、头晕、恶心、呕吐、腹痛等症状。严重中毒，可出现内脏出血、中毒性肝炎、精神失常、呼吸中枢麻痹，甚至休克、昏迷、死亡。\n" +
                     "\n" +
                     "【配伍药方】①治钩虫病：苦楝皮(去粗皮)5000克，加水25000毫升，熬成5000毫升；另用石榴皮24克，加水2500毫升，熬成1000毫升。再把两种药水混合搅匀，成人每次服30毫升。(《湖南药物志》)\n" +
                     "\n" +
                     "②治痢疾：苦楝树皮12克，骨碎补9克，荆芥6克，青木香6克，檵木花9克。水煎服。(《湖南药物志》)\n" +
                     "\n" +
                     "③治虫牙痛：苦楝树皮水煎漱口。(《湖南药物志》)\n" +
                     "\n" +
                     "④治疥疮风虫：苦楝根皮、皂角(去皮，子)各等分。为末，猪脂调涂。(《奇效良方》)\n" +
                     "\n" +
                     "⑤治顽固性湿癣：苦楝根皮，洗净晒干烧灰，调茶油涂抹患处，隔日洗去再涂，如此三四次。(《福建中医药》1959，(2)，43)");

             chineseMedicineDao.insert(medicine49);

             ChineseMedicine medicine50=new ChineseMedicine();
             medicine50.setName("苦丁茶");
             medicine50.setSortType("K");
             medicine50.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=58b63e9cc61349546a13e0363727f93d/d000baa1cd11728b41790a3fc5fcc3cec3fd2c52.jpg");
             medicine50.setData("【中药名】苦丁茶 kudingcha\n" +
                     "\n" +
                     "【别名】苦灯茶、大叶茶。\n" +
                     "\n" +
                     "【英文名】Folium Ilecis Latifoliae。\n" +
                     "\n" +
                     "【药用部位】冬青科植物大叶冬青Ilex latifolia Thunb.的叶。\n" +
                     "\n" +
                     "【植物形态】常绿乔木。树皮红黑色或灰黑色，粗糙；小枝粗壮，灰褐色，无毛。叶互生，革质或厚革质，长圆状椭圆形或倒披针状椭圆形，顶端短渐尖或钝，基部渐狭，边缘有小锯齿，齿钝而有黑色尖头，上面光亮，两面无毛；叶脉在上面凹入成沟状，侧脉每边10～14条；叶柄有翅。花白色，单性，雌雄异株。雄花序为腋生聚伞花序，通常有花3～7朵，花萼裂片4枚，卵形或三角状圆形；花冠轮状，花瓣4片，倒卵状长圆形，雄蕊4枚，比花瓣短。果序腋生，假总状，总轴粗壮，核果球形，红色，顶部有盘状宿存柱头；果梗粗壮，分核4个，长圆形。\n" +
                     "\n" +
                     "【产地分布】生于沟谷或山坡疏林中，分布于广东、安徽、浙江、广西等地。野生或栽培。\n" +
                     "\n" +
                     "【采收加工】夏、秋季采收。摘取叶片，晒干；或将干叶片叠齐，扎成小束。\n" +
                     "\n" +
                     "【药材性状】叶片革质。完整而平直，呈长椭圆形，叶缘有疏尖锯齿如刺状，叶面光滑，青黄色或青灰色；叶背颜色稍浅，主脉、网脉均明显。气微，味苦微甘。\n" +
                     "\n" +
                     "【性味归经】性寒，味苦、甘。归肝经、肺经、胃经。\n" +
                     "\n" +
                     "【功效与作用】疏风清热、除烦止咳、消食化痰。属清热药下分类的清热凉血药。\n" +
                     "\n" +
                     "【临床应用】用量6～9克，煎服。用治热病烦渴、风热头痛、牙痛、目赤、聤耳流脓、湿热痢疾。\n" +
                     "\n" +
                     "【药理研究】能显著增加心脏冠脉流量及脑血流量，保护急性心肌缺血，降低脑血管阻力和血压，提高小鼠耐缺氧能力。尚具有降血脂、抗生育及兴奋子宫平滑肌的作用。苦丁茶煎剂皮下或腹腔注射对大鼠有抗早孕和抗着床的作用。水提液能增加离体豚鼠心的冠脉流量，显著地提高小鼠耐缺氧能力，延长缺氧鼠的存活时间，对垂体后叶素引起的大鼠急性心肌缺血亦有保护作用；水提液静注后能明显增加麻醉兔的脑血流量，降低脑血管的阻力和血压。\n" +
                     "\n" +
                     "【化学成分】叶含香树精、α-苦丁内酯、羽扇豆醇、β-香树脂醇、乌发醇、β-谷甾醇、蒲公英赛醇和熊果酸等成分。新鲜叶中含苦味质皂苷、阔叶糖苷甲和阔叶糖苷乙。\n" +
                     "\n" +
                     "【使用禁忌】脾胃虚寒者慎服。\n" +
                     "\n" +
                     "【配伍药方】①治口腔炎：苦丁茶30克，水煎咽下。(《浙江药用植物志》)\n" +
                     "\n" +
                     "②治烫伤：苦丁茶适量。水煎外洗，并用叶研粉，茶油调涂。(《浙江药用植物志》)\n" +
                     "\n" +
                     "③治外伤出血：鲜苦丁茶捣烂绞汁涂搽；或干叶研细末，麻油调搽。(《安徽中草药》)");

             chineseMedicineDao.insert(medicine50);

             ChineseMedicine medicine51=new ChineseMedicine();
             medicine51.setName("款冬花");
             medicine51.setSortType("K");
             medicine51.setMedicineImageUrl("http://img0.ph.126.net/QZYsdskl_2W82kLhd5TClw==/6597268778262398985.jpg");
             medicine51.setData("【中药名】款冬花 kuandonghua\n" +
                     "\n" +
                     "【别名】冬花、九九花、连三朵、款花、艾冬花。\n" +
                     "\n" +
                     "【英文名】Farfarae Flos。\n" +
                     "\n" +
                     "【药用部位】菊科植物款冬Tussdago farfaraL.的花蕾。\n" +
                     "\n" +
                     "【植物形态】多年生草本。根茎细长，横生。叶基生，阔心形，长7～15厘米，宽8～16厘米，边缘具波状疏齿，下面密生白色茸毛，掌状脉5～9条；叶柄长5～15厘米。花黄色，先叶开放；花茎数枝，高5～10厘米，被茸毛，具淡紫褐色互生鳞叶10余片；头状花序单生茎顶，总苞片1～2层，被茸毛；边缘舌状花，雌性；中央筒状花，两性，通常不结实。瘦果长椭圆形，有5～10纵棱；冠毛纤细。花期1～2月。\n" +
                     "\n" +
                     "【产地分布】生于河边沙地、山谷沟旁湿地；有栽培。分布于河南、河北、湖北、四川、陕西、山西、甘肃、青海、内蒙古、新疆、西藏等地。\n" +
                     "\n" +
                     "【采收加工】12月或地冻前当花尚未出土时采挖，除去花梗及泥沙，阴干。\n" +
                     "\n" +
                     "【药材性状】长圆棒状，单生或2～3个基部连生，习称“连三朵”，长1～2.5厘米，直径0.5～1厘米。上端较粗，下端渐细或带有短梗，外面被有多数鱼鳞状苞片。苞片外表面紫红色或淡红色，内表面密被白色絮状茸毛。体轻，撕开后可见白色茸毛。气香，味微苦而辛，久嚼似棉絮。\n" +
                     "\n" +
                     "【性味归经】性温，味辛、微苦。归肺经。\n" +
                     "\n" +
                     "【功效与作用】润肺下气、止咳化痰。属化痰止咳平喘药下属分类的止咳平喘药。\n" +
                     "\n" +
                     "【临床应用】用量5～9克；或熬膏；或入丸、散。外用：适量、研末调敷。临床上用治新久咳嗽、喘咳痰多、劳嗽咳血。主要用于咳喘的治疗，特别用于慢性支气管炎咳嗽，并用于哮喘及哮喘性支气管炎。\n" +
                     "\n" +
                     "【药理研究】具有镇咳、祛痰、平喘、呼吸兴奋、收缩平滑肌、收缩血管、减慢心率、阻断钙离子通道作用，具致癌活性。动物试验证明，有较强的镇咳作用，水煎剂能促进呼吸道分泌物增加，有较明显的祛痰作用。\n" +
                     "\n" +
                     "【化学成分】含款冬二醇、山金车二醇、款冬花碱、克氏千里光碱、金丝桃苷、α-十一碳烯、1-十一碳烯-3-醇、款冬花内酯、款冬二醇等成分。\n" +
                     "\n" +
                     "【使用禁忌】阴虚及肺火盛者慎服。\n" +
                     "\n" +
                     "【配伍药方】①治肺虚咳嗽：人参、白术、款冬花(去梗)、甘草(炙)、川姜(炮)、钟乳粉。上各15克为细末，炼蜜丸。每30克十丸。每服一丸，米饮下，食前。(《传信适用方》款冬花膏)\n" +
                     "\n" +
                     "②治喘嗽不已，或痰中有血：款冬花、百合(蒸焙)。上等分为细末，炼蜜为丸，如龙眼大。每服一丸，食后、临卧细嚼，姜汤咽下，噙化尤佳。(《济生方》百花膏)\n" +
                     "\n" +
                     "③治肺痈，嗽而胸满，振寒，脉数，咽干，大渴，时出浊唾腥臭，臭久吐脓如粳米粥状者：款冬花(去梗)45克，甘草(炙)30克，桔梗60克，薏苡仁30克。上作十剂，水煎服。(《疮疡科经验全书》款花汤)\n" +
                     "\n" +
                     "④治口中疳疮：款冬花、黄连等分。为细末。用唾津调成饼子。先以蛇床子煎汤嗽口，乃以饼子傅之。(《纲目》引《杨诚经验方》)\n" +
                     "\n" +
                     "⑤治痔漏：款冬花蕾研末，水调敷。《湖南药物志》)");


             chineseMedicineDao.insert(medicine51);

             ChineseMedicine medicine52=new ChineseMedicine();
             medicine52.setName("苦地丁");
             medicine52.setSortType("K");
             medicine52.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=476ae896a251f3ded7bfb136f5879b7a/78310a55b319ebc46d3956c58f26cffc1e17162c.jpg");
             medicine52.setData("【中药名】苦地丁 kudiding\n" +
                     "\n" +
                     "【别名】地丁、地丁草、紫花地丁、小鸡菜、扁豆秧。\n" +
                     "\n" +
                     "【英文名】Corydalis Bungeanae Herba\n" +
                     "\n" +
                     "【药用部位】罂粟科植物紫堇Corydalis bungean.a Turcz.的干燥全草。\n" +
                     "\n" +
                     "【植物形态】多年生或栽培为二年生草本。株高10～40厘米，无毛，微被白粉。地下具细长主根。基生叶和茎下部叶长3～10厘米，具长柄。叶片轮廓卵形，长2～4厘米，2回羽状全裂，一回裂片2～3对，末回裂片狭卵形至线形，宽0.5～1.2毫米，先端钝圆或成短突尖，两面灰绿色，无毛。总状花序，上有花数要。苞片叶状，羽状深裂。花梗长1～2毫米。萼片小，2枚，近三角形，鳞片状，长1～2毫米，早落。花瓣4，淡紫色，倒卵状长椭圆形，外2片大，前面1片平展，倒卵状匙形，先端兜状，背面具宽翅，后1片先端兜状，基部延伸成距，距长4.5～6.5毫米，内有2瓣较小，先端连合。蒴果，长圆形，扁平。种子黑色，有光泽，花期4～5月。\n" +
                     "\n" +
                     "【产地分布】生于山沟、旷地、林缘。分布于辽宁、河北、内蒙古、山东、山西、陕西、甘肃、宁夏等。\n" +
                     "\n" +
                     "【采收加工】夏季花果期采收，除去杂质，晒干。\n" +
                     "\n" +
                     "【药材性状】皱缩成团，长10～30厘米。主根圆锥形，表面棕黄色。茎细，多分枝，表面灰绿色或黄绿色，具5纵棱，质软，断面中空。叶多皱缩破碎，暗绿色或灰绿色，完整叶片二至三回羽状全裂。花少见，花冠唇形，有距，淡紫色。蒴果扁长椭圆形，呈荚果状。种子扁心形，黑色，有光泽。气微，味苦。\n" +
                     "\n" +
                     "【性味归经】性寒，味苦。归心经、肝经、大肠经。\n" +
                     "\n" +
                     "【功效与作用】清热解毒，散结消肿。属清热药下属分类的清热解毒药。\n" +
                     "\n" +
                     "【临床应用】用量9～15g，煎汤内服，外用适量，煎汤洗患处。用治时疫感冒，咽喉肿痛，疔疮肿痛，痈疽发背，痄腮丹毒。\n" +
                     "\n" +
                     "【药理研究】在体外对甲型链球菌、肺炎链球菌、卡他球菌、痢疾杆菌、大肠杆菌、绿脓杆菌有抑制作用，对葡萄球菌、八叠球菌亦有抑制作用；对副流感病毒仙台株有抑制作用。\n" +
                     "\n" +
                     "【化学成分】全草含多种生物碱，如消旋的和右旋的紫堇醇灵碱、乙酰紫堇醇灵碱、四氢黄连碱、原阿片碱、右旋异紫堇醇灵碱、四氢刻叶紫堇明碱、二氢血根碱、乙酰异紫堇醇灵碱。\n" +
                     "\n" +
                     "【使用禁忌】体虚者忌之，孕妇慎用。\n" +
                     "\n" +
                     "【配伍药方】①治麻疹热毒：苦地丁9克，连翘12克，菊花9克。煎服。(《青海常用中草药手册》)\n" +
                     "\n" +
                     "②治水痘：苦地丁6克，甘草3克。水煎服。(南药《中草药学》)\n" +
                     "\n" +
                     "③治急性黄疸型肝炎：苦地丁、茵陈各15克。水煎服。(《山西中草药》)\n" +
                     "\n" +
                     "④治痢疾：苦地丁配火线草、地榆。煎汤服。(《高原中草药治疗手册》)\n" +
                     "\n" +
                     "⑤治疔疮肿痛，蜂窝组织炎，丹毒：鲜苦地丁，捣烂外敷;或苦地丁15克，蒲公英30克，连翘、野菊花各15克，黄芩9克，水煎服。(《青岛中草药手册》)\n" +
                     "\n" +
                     "⑥治指头感染初起，淋巴管炎(红丝疔)红肿热痛：苦地丁、野菊花各30克。水煎服。(《河北中药手册》)\n" +
                     "\n" +
                     "⑦治湿热疮疡：苦地丁、金银花、蒲公英各3克，大青叶9克。水煎服。(《辽宁常用中草药手册》)");

             chineseMedicineDao.insert(medicine52);

             ChineseMedicine medicine53=new ChineseMedicine();
             medicine53.setName("苦地胆");
             medicine53.setSortType("K");
             medicine53.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=f497529e920a304e462fa8a8b0a1cce3/4d086e061d950a7b0b80685e0fd162d9f3d3c9f6.jpg");
             medicine53.setData("【中药名】苦地胆 kudidan\n" +
                     "\n" +
                     "【别名】天芥菜、鸡疴粘、土柴胡、苦龙胆草、草鞋底、铁灯台。\n" +
                     "\n" +
                     "【英文名】Herba Elephantopi\n" +
                     "\n" +
                     "【药用部位】菊科植物地胆草Eleph-antopus scaber L.的全草。\n" +
                     "\n" +
                     "【植物形态】多年生直立草本。茎分枝极多，粗糙，被白色紧贴粗毛。单叶生于茎枝上，椭圆形或矩圆形，边缘稍具钝锯齿。秋、冬季开花；头状花序约有小花4朵，密集成复头状花序，并围以2～3片叶状苞片，再排成伞房花序式；总苞圆筒形，总苞片椭圆形，被粗毛，小花全为管状花，两性，花冠白色，先端4裂。瘦果纺锤形，有棱，冠毛为4～6条长而硬的刺毛。\n" +
                     "\n" +
                     "【产地分布】生于向阳山谷、村边、路旁、荒地等处低草丛中。分布于广东各地及我国东南部至西南部各地。\n" +
                     "\n" +
                     "【采收加工】夏末采收，鲜用或晒干。\n" +
                     "\n" +
                     "【药材性状】根茎短，具环节，密被紧贴的灰白色茸毛，其下着生多数细根。基生叶多皱缩，展开后呈匙形或长圆状倒披针形，黄绿或暗绿色，有腺点，边缘具钝锯齿；茎生叶互生，形小，两面均被紧贴的灰白色粗毛。茎圆柱形，常2歧分枝。茎顶头状花序聚伞状排列。全体有灰色毛。气芳香，味苦。\n" +
                     "\n" +
                     "【性味归经】性寒，味苦、微辛。归肺经、肝经、肾经。\n" +
                     "\n" +
                     "【功效与作用】清热解毒、利水消肿。属清热药下分类的清热解毒药。\n" +
                     "\n" +
                     "【临床应用】用量9～15克，煎服；鲜品用量50～100克；或捣汁。外用：捣敷或煎水熏洗。用治感冒高热、肺热咳嗽、咽喉肿痛；湿热黄疸、泻痢、热淋；肾炎水肿、肝硬化腹水；外治痈肿疔毒、蛇虫咬伤。\n" +
                     "\n" +
                     "【药理研究】具有抗菌、抗炎及抗肿瘤作用。\n" +
                     "\n" +
                     "【化学成分】主含羽扇豆醇、豆甾醇、地胆草内酯、表无羁萜醇、蛇麻脂醇、豆固醇、乙酸蛇麻脂醇酯、去氧地胆草素、异去氧地胆草素、去氧地胆草内酯等成分。\n" +
                     "\n" +
                     "【使用禁忌】体虚者忌之，孕妇慎用。\n" +
                     "\n" +
                     "【配伍药方】①治黄疸：地胆头连根叶洗净，鲜者120～180克煮猪肉食，连服4～5天。(《岭南草药志》)\n" +
                     "\n" +
                     "②治糖尿病：地胆头10株(连根叶)，生姜15克。水煎代茶饮。(《岭南草药志》)\n" +
                     "\n" +
                     "③治痢疾：地胆头60克。水服。(《贵州草药》)\n" +
                     "\n" +
                     "④治百日咳：地胆头、天胡荽、马蹄金各9克，三叶青3克。水煎服。(《浙江药用植物志》)\n" +
                     "\n" +
                     "⑤治疟疾：地胆草全草15克，火烧花树皮30克。水煎服。(《中国民族药志》)\n" +
                     "\n" +
                     "⑥治月经不调，闭经：地胆草全草50克，红糖50克。水煎服。(福州军区《中草药手册》)\n" +
                     "\n" +
                     "⑦治疖肿，乳痛：地胆头全草适量。捣烂，加米醋调匀。敷患处。(《北海民间常用中草药手册》)\n" +
                     "\n" +
                     "⑧治眼结膜炎：地胆草、小叶榕树叶各30克。水煎服，每日1剂。(《全国中草药汇编》)");

             chineseMedicineDao.insert(medicine53);

             ChineseMedicine medicine54=new ChineseMedicine();
             medicine54.setName("苦杏仁");
             medicine54.setSortType("K");
             medicine54.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C0%2C1024%2C676%3Bc0%3Dbaike116%2C5%2C5%2C116%2C38/sign=a2e98a4efbedab64603d1780ca0683f3/bba1cd11728b4710bff40949c3cec3fdfd03235f.jpg");
             medicine54.setData("【中药名】苦杏仁 kuxingren\n" +
                     "\n" +
                     "【别名】山杏仁、杏仁、杏核仁、杏子、木落子、杏梅仁。\n" +
                     "\n" +
                     "【英文名】Armeniacae Semen Amarum。\n" +
                     "\n" +
                     "【药用部位】蔷薇科植物山杏Prun.us armemaca L.var. ansuMaxim.、西伯利亚杏Prunus sibiricaL.、东北杏Prunusmandshurica (Maxim. )Koehne或杏Prunus armeniacaL.的成熟种子。(本文以杏为例，右图亦为杏的种子)\n" +
                     "\n" +
                     "【植物形态】落叶乔木，高达10米，树冠圆形，树皮暗灰色，单叶互生，叶片宽卵形或近圆形，先端具短尖头，基部圆形或近心形，边缘具圆钝锯齿，具长柄。花单生顶端，先叶开放，几无柄；核果心状卵扁圆形，侧面具浅凹槽，黄白色或黄红色，成熟时不开裂；果核扁圆形，近平滑，腹缝中部具龙骨状棱，两侧有扁平棱或浅沟。花期3～4月，果期4～6月。\n" +
                     "\n" +
                     "【产地分布】生于山坡、丘陵地，可耐瘦土。分布于河北、山西、陕西等地。\n" +
                     "\n" +
                     "【采收加工】夏季采收成熟果实，除去果肉及核壳，取出种子，晒干。\n" +
                     "\n" +
                     "【药材性状】扁心形，长1～1.9厘米，宽0.8～1.5厘米，厚0.5～0.8厘米。表面黄棕色至深棕色，一端尖，另一端钝圆，肥厚，左右不对称。尖端一侧有短线形种脐，圆端合点处向上具多数棕色脉纹。种皮薄，子叶2，乳白色，富油性。无臭，味苦。\n" +
                     "\n" +
                     "【性味归经】性微温，味苦。归肺经、大肠经。\n" +
                     "\n" +
                     "【功效与作用】降气、止咳平喘、润肠通便。属化痰止咳平喘药下属分类的止咳平喘药。\n" +
                     "\n" +
                     "【临床应用】用量4.5～9克，水煎服，生品入煎剂宜后下；或入丸、散。杏仁用时须打碎，杏仁霜入煎剂须布包。外用：适量，捣敷。用治咳嗽气喘、胸满痰多、血虚津枯、肠燥便秘。\n" +
                     "\n" +
                     "【药理研究】保肝；止咳平喘；润肠通便；抗肿瘤；抗炎和抗癌；抗突变；杀蛔虫。毒性：苦杏仁苷被肠道微生物水解产生出较多的氢氰酸，可致死。\n" +
                     "\n" +
                     "【化学成分】含苦杏仁苷、脂肪油、苦杏仁酶、苦杏仁苷酶、樱叶酶、雌酮、α-雌二醇、链甾醇、苦杏仁酶、胆固醇等成分。\n" +
                     "\n" +
                     "【使用禁忌】阴虚咳嗽及大便溏泻者禁服，婴儿慎服。内服不宜过量，以免中毒。剂量大时，轻者可出现头晕乏力，吐泻，腹痛，上腹部烧灼感，血压升高，呼吸加快;严重者，呼吸明显减慢而表浅，昏迷，并可有强直性、阵发性痉挛，瞳孔散大，血压下降，最后因呼吸或循环衰竭而死亡。\n" +
                     "\n" +
                     "【配伍药方】①治小肠气痛欲死者：苦杏仁、茴香各30克，葱白15克(焙干)。同为末，酒调，嚼胡桃肉咽下。(《卫生易简方》)\n" +
                     "\n" +
                     "②治上气喘急：桃仁、苦杏仁(并去双仁皮尖，炒)各15克。上二味，细研，水调生面少许，和丸如梧桐子大。每服十丸，生姜、蜜汤下，微利为度。(《圣济总录》双人丸)\n" +
                     "\n" +
                     "③治气喘促浮肿，小便涩：苦杏仁30克，去皮尖，熬研，和米煮粥极熟，空心吃二合。(《食医心镜》)\n" +
                     "\n" +
                     "④治咯血：苦杏仁四十粒研细，用黄蜡炒黄色，入青黛3克，捏作饼子，同时以柿子一枚破开，以饼置其中合定，湿纸包煨。研，水服。(《医学入门》圣饼子)\n" +
                     "\n" +
                     "⑤治小儿久患咳嗽：苦杏仁45克(去皮，焙)，茯苓30克，紫菀茸、皂角各15克(去皮、弦、核，蜜炙黄)。上末，每1.5克生蜜调入，薄荷汤泡开服。(《仁斋小儿方》杏仁膏)");

             chineseMedicineDao.insert(medicine54);

             ChineseMedicine medicine55=new ChineseMedicine();
             medicine55.setName("苦瓜");
             medicine55.setSortType("K");
             medicine55.setMedicineImageUrl("http://www.glnyw.gov.cn/zwb/zwbmtyc/201801/W020180102524959130025.jpg");
             medicine55.setData("【中药名】苦瓜 kugua\n" +
                     "\n" +
                     "【别名】锦荔枝、癞葡萄、红姑娘、凉瓜。\n" +
                     "\n" +
                     "【英文名】Fructus Momordicae Charantiae\n" +
                     "\n" +
                     "【药用部位】葫芦科植物苦瓜Momordica charantia L.的干燥成熟果实。\n" +
                     "\n" +
                     "【植物形态】一年生攀援草本。多分枝，茎枝被细柔毛。卷须不分枝，纤细，长可达20厘米，被微柔毛。叶柄细，初时被白色柔毛，后变近无毛，长4～6厘米；叶片轮廓为卵状椭圆状肾形或近圆形，膜质，长宽均为4～12厘米，上面绿色，背面淡绿色，脉上被明显的微柔毛，5～7深裂，裂片卵状长圆形，边缘具粗锯齿或者不规则的小裂片，先端多半钝圆形，基部弯曲成半圆形，叶脉掌状。雌雄同株；雄花单生，有柄，长5～15厘米，中部或基部有苞片，苞片肾状圆心形，萼筒钟形，5裂，裂片卵状披针形，先端渐尖，花冠黄色，5裂，长1.5～2厘米，先端钝圆或微凹，雄蕊3，贴生于萼筒之喉部；雌花单生，有柄。长5～10厘米，基部有苞片，子房纺锤形，具刺瘤，先端有喙，花柱细长，柱头3枚。果实为长椭圆形、卵形或两端狭窄，长8～30厘米，全体具钝圆不整齐的瘤状突起，成熟时橘黄色，自先端3瓣开裂。种子椭圆形扁平，两端均有角状齿，两面均有凹凸不平的条纹，包于红色肉质的假种皮内。花期6～7月，果期9～10月。\n" +
                     "\n" +
                     "【产地分布】我国南北均普遍栽培。\n" +
                     "\n" +
                     "【采收加工】秋季采收果实，切片晒干或鲜用。\n" +
                     "\n" +
                     "【药材性状】干燥的苦瓜片呈椭圆形或矩圆形，厚约2～8毫米，长3～15厘米，宽0.4～2厘米，全体皱缩，弯曲，果皮浅灰棕色，粗糙，有纵皱或瘤状突起。中间有时夹有种子或种子脱落后留下的孔洞。质脆，易断。气微味苦。\n" +
                     "\n" +
                     "【性味归经】性寒，味苦。归心经、脾经、肺经。\n" +
                     "\n" +
                     "【功效与作用】祛暑涤热，明目，解毒。属清热药下分类的清热泻火药。\n" +
                     "\n" +
                     "【临床应用】内服：煎汤，6～15克，鲜品30～60克；或煅存性研末。外用：适量，鲜品捣敷；或取汁涂。主治暑热烦渴，消渴，赤眼疼痛，痢疾，疮痈肿毒。\n" +
                     "\n" +
                     "【药理研究】降血糖；抗癌；抗病毒。\n" +
                     "\n" +
                     "【化学成分】本品含5,25-豆甾二烯醇-3-葡萄糖苷、苦瓜苷等成分。\n" +
                     "\n" +
                     "【使用禁忌】脾胃虚寒者慎服。\n" +
                     "\n" +
                     "【配伍药方】①治中暑暑热：鲜苦瓜截断去瓤，纳好茶叶再合起，悬挂阴干。用时取6～9克煎汤，或切片泡开水代茶服。(《泉州本草》)\n" +
                     "\n" +
                     "②治烦热消渴引饮：苦瓜绞汁调蜜冷服。(《泉州本草》)\n" +
                     "\n" +
                     "③治痢疾：鲜苦瓜捣绞汁1小杯泡蜂蜜服。(《泉州本草》)\n" +
                     "\n" +
                     "④治痈肿：鲜苦瓜捣烂敷患处。(《泉州本草》)\n" +
                     "\n" +
                     "⑤治汗斑：鲜苦瓜去瓤及种子，用砒霜0.6克，研末，纳入瓜内，以物盖口，用火烤出汁，取汁涂患处。(《福建药物志》)");

             chineseMedicineDao.insert(medicine55);

             ChineseMedicine medicine56=new ChineseMedicine();
             medicine56.setName("苦参");
             medicine56.setSortType("K");
             medicine56.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=46c3fafbc2cec3fd9f33af27b7e1bf5a/a1ec08fa513d2697a63f0f6a54fbb2fb4316d81d.jpg");
             medicine56.setData("【中药名】苦参 kushen\n" +
                     "\n" +
                     "【别名】苦骨、川参、牛参、地骨、野槐根。\n" +
                     "\n" +
                     "【英文名】Sophorae Flavescentis Radix。\n" +
                     "\n" +
                     "【来源】为豆科植物苦参Sophora flavescens Ait.的根。\n" +
                     "\n" +
                     "【植物形态】亚灌木。根圆柱状，外皮黄色。茎枝具不规则的纵沟，幼时被黄色细毛。单数羽状复叶，互生，下具线形托叶，小叶5～21枚，卵状椭圆形至长椭圆状披针形，先端圆形或钝尖，基部圆形或广楔形，全缘。总状花序顶生，被短毛，苞片线形，花淡黄白色，花冠蝶形，雄蕊10枚，花丝离生，仅基部愈合，雌蕊1枚，子房上位。荚果线形，先端具长喙，成熟时不开裂。种子间有缢缩，近球形。\n" +
                     "\n" +
                     "【产地分布】生于山坡草地、平原、路旁、沙质土壤地的向阳处。我国各地都有分布。\n" +
                     "\n" +
                     "【采收加工】春、秋季采收，以秋采者为佳。挖出根后，去掉根头、须根，洗净泥沙，晒干。鲜根切片晒干，称苦参片。\n" +
                     "\n" +
                     "【药材性状】圆柱形，长10～30厘米，直径1～2厘米。表面灰棕色或棕黄色，具有明显纵皱，皮孔明显突出而稍反卷，横向延长。栓皮很薄，棕黄色或灰棕色，多数破裂向外卷曲，易剥落而显现黄色的光滑皮部。质坚硬，不易折断，折断面粗纤维状。横断面黄白色，形成层明显。气刺鼻，味极苦。 苦参片为斜切的薄片，形状大小不一，斜圆形或长椭圆形，长2.5厘米，宽1～1.5厘米，厚2～5亳米。质坚硬，切面淡黄白色，有环状年轮，木质部作放射纹。\n" +
                     "\n" +
                     "【性味归经】性寒，味苦。归心经、肝经、胃经、大肠经、膀胱经。\n" +
                     "\n" +
                     "【功效与作用】清热燥湿、杀虫、利尿。属清热药下分类的清热燥湿药。\n" +
                     "\n" +
                     "【临床应用】用量4.5～9克，内服煎汤，或入丸散，治疗热痢、便血、黄疸尿闭、赤白带下、阴肿阴痒、湿疹、湿疮、皮肤瘙痒、疥癣麻风；外用适量，煎汤洗患处，治疗滴虫性阴道炎。\n" +
                     "\n" +
                     "【药理研究】煎剂及其中所含苦参碱给家兔口服或注射，皆可产生利尿作用；煎剂在试管中高浓度对结核杆菌有抑制作用；煎剂、水浸液在体外对某些常见皮肤真菌有抑制作用；醇浸膏在体外有抗滴虫作用；苦参碱注射于家兔，发现中枢神经麻痹现象，同时发生痉挛，终则呼吸停止而死。注射于青蛙，初呈兴奋，继则麻痹，呼吸变为缓慢而不规则，最后发生痉挛，以致呼吸停止而死，其痉挛的发作可能起因于脊髓反射的亢进。对家兔的最小致死量为0.4克/千克。有正性肌力作用，剂量过大则心脏出现自发性收缩及兴奋性降低；抗心律失常；抗心肌缺血；扩张血管，增加灌流量；可拮抗咖啡因的中枢兴奋作用；可平喘、抗过敏；抑制免疫系统；升高白细胞；抗肿瘤、抗炎、抗病原微生物、增强小鼠小肠的推进功能，保护胃黏膜损伤等。有毒。\n" +
                     "\n" +
                     "【化学成分】主要含多种生物碱，右旋苦参碱、左旋臭豆碱、苦参碱、槐定碱、苦参素、苦参查耳酮醇、木犀草素- 7-葡萄糖苷、1，8-桉叶素、伞形花内酯、右旋槐花醇等成分。\n" +
                     "\n" +
                     "【使用禁忌】本品苦寒败胃损肾，脾胃虚寒、肾虚无热者禁服。不宜与藜芦同用。\n" +
                     "\n" +
                     "【配伍药方】①治血痢不止：苦参炒焦为末，水丸梧桐子大。每服十五丸，米饮下。(《仁存堂经验方》)\n" +
                     "\n" +
                     "②治齿缝出血：苦参30克，枯矾3克。为末，日三揩之。(《普济方》)\n" +
                     "\n" +
                     "③治大小便不利：苦参、滑石、贝齿各等分。上三味捣筛为散。每服饮下一匕，或煮葵根汁服之，弥佳。(《外台》)\n" +
                     "\n" +
                     "④治赤白带下：苦参60克，牡蛎45克。为末，以雄猪肚一个，水三碗煮烂，捣泥和丸，梧桐子大。每服百丸。温酒下。(《积善堂经验方》)\n" +
                     "\n" +
                     "⑤治疥疮：苦参、蛇床子、白矾、荆芥穗各等分。上四味煎汤，放温洗。(《济生方》苦参汤)");

             chineseMedicineDao.insert(medicine56);

             ChineseMedicine medicine57=new ChineseMedicine();
             medicine57.setName("榼藤子");
             medicine57.setSortType("K");
             medicine57.setMedicineImageUrl("http://s6.sinaimg.cn/middle/5d5112bdtb8ec4c16a385&690");
             medicine57.setData("【药名】榼藤子 kē té；ng zǐ\n" +
                     "\n" +
                     "【别名】象豆、合子、眼镜豆。\n" +
                     "\n" +
                     "【英文名】Entada phaseoloides (L.) Merr.\n" +
                     "\n" +
                     "【来源】豆科植物榼藤的成熟种子。\n" +
                     "\n" +
                     "【植物形态】木质大藤本。叶为2回偶数羽复叶；有羽片3对，顶生1对变态为卷须，下部2对各有2~4对；小叶对生，有短柄，近革质，长圆形或长圆状披针形，顶端钝或圆，通常微凹头，基部歪斜，楔尖或稍钝。春、夏季开黄色花；芳香，排成腋生、密花且柔弱的穗状花序；萼阔钟状，5浅裂；花瓣5片，长椭圆形，顶端近短尖，无毛；雄蕊10枚，离生；子房有短柄，花柱丝状。荚果大，木质，扁平，弯曲，有10~ 30节，成熟时逐节脱落，每节都含1粒种子。种子扁平，圆形；种皮厚，木质，暗褐色或黑褐色，有光泽。\n" +
                     "\n" +
                     "【生境分布】常生于林中，攀援于大树上。分布于广东、云南、广西、海南、福建、台湾等地方，越南亦有分布。\n" +
                     "\n" +
                     "【采收加工】秋季采收，摘取成熟荚果，剥除果壳，晒干。\n" +
                     "\n" +
                     "【药材性状】卵圆形或椭圆形。表面灰黄色或黄棕色，微具纵棱，一端钝圆，有一椭圆形疤痕种脐，色较淡，在其两侧各有一个小突起；另一端稍尖。种皮质硬脆，破开后内面红棕色，有麻纹。种仁1枚卵圆形，皱而坚实，表面有灰棕色皱缩的薄膜状外胚乳，内胚乳黄白色，坚实，有油性。气微香，味微甜。炒熟后具香气。\n" +
                     "\n" +
                     "【性味归经】味微甘、涩，性平，有毒(一说无毒)。归胃经、肝经、大肠经。\n" +
                     "\n" +
                     "【功效与作用】行气止痛，利湿消肿。属收涩药下属的敛肺涩肠药，从种仁中分离出有抗肿瘤作用的皂苷元，酸解产生槛藤子酸、阿拉伯糖、木糖。并具有抗病原体作用；种子核仁所含毒性皂苷对哺乳动物主要引起溶血作用。\n" +
                     "\n" +
                     "【临床应用】用量3~9克，烧存性研末，煎服。用治便血、血痢、痔疮；黄疸、脚气、水肿；胃痛、疝气痛、喉痹肿痛等。植榼子烧成黑炭，微存性，米饮调服，治五痔(《本草衍义》)。\n" +
                     "\n" +
                     "【主要成分】含脂肪油、甾醇、黄酮类、酚性成分、氨基酸、有机酸等。\n" +
                     "\n" +
                     "【使用禁忌】暂缺。");

             chineseMedicineDao.insert(medicine57);

             ChineseMedicine medicine58=new ChineseMedicine();
             medicine58.setName("苦玄参");
             medicine58.setSortType("K");
             medicine58.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=be36fb39828ba61ecbe3c07d205dfc6f/0ff41bd5ad6eddc45ed9490839dbb6fd52663302.jpg");
             medicine58.setData("【中药名】苦玄参 kuxuanshen\n" +
                     "\n" +
                     "【别名】蛇总管、鱼胆草、落地小金钱、四环素草。\n" +
                     "\n" +
                     "【英文名】Picriae Herba。\n" +
                     "\n" +
                     "【来源】玄参科植物苦玄参Picria fel-terrae Lour.的干燥全草。。\n" +
                     "\n" +
                     "【植物形态】草本，长达1米，基部节上生根。枝被短糙毛。叶对生，卵形，有时近圆形，长达5.5厘米，宽达3厘米，先端急尖，边缘有圆钝锯齿，上面密被粗糙短毛，下面脉上有糙毛，侧脉约4～5对，叶柄长达1.8厘米为。花序总状，有4～8花。花梗长达1厘米，花萼长圆状卵形，在果时长达1.4厘米，侧方2片近线形，花冠白或红褐色，长约1.2厘米，花冠筒长约6.5毫米，中部稍细缩，下唇宽阔，长约6.5毫米，上唇直立，长约4.5毫米，基部很宽，向上变窄，近长方形，先端微缺。蒴果卵圆形，长5～6毫米。\n" +
                     "\n" +
                     "【产地分布】生于海拔700～1400米的疏林中或荒野。主产于海南、广西、贵州及云南。\n" +
                     "\n" +
                     "【采收加工】秋季采收，除去杂质，晒干。\n" +
                     "\n" +
                     "【药材性状】须根细小，茎略呈方柱形，节稍膨大，多分枝，长30～80厘米，直径1.5～2.5毫米，黄绿色，老茎略带紫色；折断面纤维性，髓部中空。单叶对生，多皱缩，完整者展平后呈卵形或卵圆形，长3～5厘米，宽2～3厘米，黄绿色至灰绿色；先端锐尖，基部楔形，边缘有圆钝锯齿。叶柄长1～2厘米。全体被短糙毛。总状花序顶生或腋生，花萼裂片4，外2片较大，卵圆形，内2片细小，条形；花冠唇形。蒴果扁卵形，包于宿存的萼片内。种子细小，多数。气微，味苦。\n" +
                     "\n" +
                     "【性味归经】性寒，味苦。归肺经、胃经、肝经。\n" +
                     "\n" +
                     "【功效与作用】清热解毒，消肿止痛。属清热药下属分类的清热解毒药。\n" +
                     "\n" +
                     "【临床应用】用量9～15g，煎汤内服，外用适量。用治风热感冒，咽喉肿痛，喉痹，痄腮，脘腹疼痛，痢疾，跌打损伤，疖肿，毒蛇咬伤。\n" +
                     "\n" +
                     "【主要成分】苦玄参有效成分主要为四环三萜苷类和黄酮类化合物。\n" +
                     "\n" +
                     "【使用禁忌】尚不明确。");

             chineseMedicineDao.insert(medicine58);

             ChineseMedicine medicine59=new ChineseMedicine();
             medicine59.setName("苦石莲");
             medicine59.setSortType("K");
             medicine59.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=6513946693ef76c6c4dff379fc7f969f/faedab64034f78f077f8dcb47a310a55b2191c81.jpg");
             medicine59.setData("【中药名】苦石莲 kushilian\n" +
                     "\n" +
                     "【别名】石莲子、土石莲子、青蛇子。\n" +
                     "\n" +
                     "【英文名】Semen Caesalpiniae Minacis。\n" +
                     "\n" +
                     "【来源】豆科植物喙荚云实Caesalpinia minax Hance.的种子。\n" +
                     "\n" +
                     "【植物形态】有刺藤本。全株被短柔毛。2回双数羽状复叶，羽片5~8对，托叶锥状；小叶12~24，近无柄，矩形或倒卵形。圆锥花序顶生，花序轴有刺，被柔毛；苞片大，椭圆形、倒披针形，两面有茸毛，花萼管阔倒卵形。花瓣5，红紫色，倒卵形，上面1枚花瓣较短；雄蕊10枚，不等长，花丝分离，下部密被柔毛，花药“丁”字形着生；子房密生细刺，花柱比雄蕊稍长，无毛。荚果椭圆状矩形，长8~14厘米，宽4.5~5厘米；密被棕色针状刺，先端圆形而有尖喙，内有种子7粒。花期3~4月，果期5～9月。\n" +
                     "\n" +
                     "【产地分布】生于山沟中空旷的溪旁、路边或灌木丛中。分布于云南、广西、四川等地。\n" +
                     "\n" +
                     "【采收加工】8~9月采成熟果实，取出种子，晒干。\n" +
                     "\n" +
                     "【药材性状】椭圆形或长圆形，两端钝圆，长1.2~2.2厘米，直径0.7~1.2厘米，外面黑褐色或暗棕色，光滑，有的具细密的环状横纹或横裂纹，基部有珠柄残基，旁有小圆形的合点。质坚硬，不易破开。种皮厚约1毫米，内表面灰黄色，平滑而有光泽；除去种皮，可见2片棕色肥厚的子叶，富油质，子叶中间有浅棕色的胚芽及胚根。气微，味极苦。\n" +
                     "\n" +
                     "【性味归经】性苦，味寒。归心经、脾经、肾经。\n" +
                     "\n" +
                     "【功效与作用】散瘀、止痛、清热、去湿。属活血化瘀药下属分类的活血止痛药。\n" +
                     "\n" +
                     "【临床应用】用量6~9克，内服煎汤；外用适量，煎水洗或捣敷。用治哕逆、痢疾、淋浊、尿血、跌打损伤。\n" +
                     "\n" +
                     "【主要成分】目前有关的研究资料较少。\n" +
                     "\n" +
                     "【使用禁忌】虚寒无火者忌用");

             chineseMedicineDao.insert(medicine59);

             ChineseMedicine medicine60=new ChineseMedicine();
             medicine60.setName("宽筋藤");
             medicine60.setSortType("K");
             medicine60.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=d332b95373cf3bc7fc0dc5beb069d1c4/0bd162d9f2d3572ca5e4d9828913632762d0c36e.jpg");
             medicine60.setData("【中药名】宽筋藤 kuanjinteng\n" +
                     "\n" +
                     "【别名】松根藤、大接筋藤、舒筋藤。\n" +
                     "\n" +
                     "【英文名】Caulis Tinosporae Sinensis。\n" +
                     "\n" +
                     "【来源】防己科植物中华青牛胆Tinospora sinensis (Lour.) Merr.的藤茎。\n" +
                     "\n" +
                     "【植物形态】落叶木质藤本，长3~10米。嫩枝被柔毛，老枝无毛，有许多皮孔。单叶互生；宽卵形至圆状卵形，先端骤尖，基部心形，上面被短硬毛，下面被茸毛，基出脉5～7条；叶柄长4～10厘米，被柔毛。总状花序腋生，先叶开放；单性异株；花淡黄色。果序长约10厘米，核果鲜红色，内果皮半卵球形，腹面平坦，背面具棱脊及多数小疣状突起。种子半圆球形，腹面内陷。花期3～4月，果期7～8月。\n" +
                     "\n" +
                     "【产地分布】生于山坡、沟边或灌木丛中。分布于广东、广西、海南、云南等地。\n" +
                     "\n" +
                     "【采收加工】全年可采，除去杂质，切段或厚片，晒干。\n" +
                     "\n" +
                     "【药材性状】圆柱形，略扭曲，长短不一，直径0.5～2厘米。表面黄绿色，较光滑或具皱纹，有明显的白色皮孔和叶痕。质硬，可折断，断面灰白色，木质部呈放射状纹理，可见众多细小的圆孔；剖开扭曲的茎枝，可见木质部从射线部分分裂呈摺纸扇的扇骨状张开。气微，味微苦。\n" +
                     "\n" +
                     "【性味归经】性凉，味微苦。归肝经。\n" +
                     "\n" +
                     "【功效与作用】舒筋活络、祛风止痛。属祛风湿药下属分类的祛风湿强筋骨药。\n" +
                     "\n" +
                     "【临床应用】用量9~15克，煎服；外用适量，捣敷。用治风湿痹痛、坐骨神经痛、腰肌劳损、跌打损伤。\n" +
                     "\n" +
                     "【主要成分】含有氨基酸、糖类和酚苷类等成分。\n" +
                     "\n" +
                     "【使用禁忌】孕妇及产后慎服。");

             chineseMedicineDao.insert(medicine60);

             ChineseMedicine medicine61=new ChineseMedicine();
             medicine61.setName("苦荬菜");
             medicine61.setSortType("K");
             medicine61.setMedicineImageUrl("http://s14.sinaimg.cn/mw690/001ssu7fgy6JswNE72R1d&690");
             medicine61.setData("【药名】苦荬菜 kǔ mǎi cà；i\n" +
                     "\n" +
                     "【别名】苦菜、盘儿草、败酱草。\n" +
                     "\n" +
                     "【来源】菊科植物苦荬菜Ixeris denticulata( Houtt.)Stebb.的全草。\n" +
                     "\n" +
                     "【植物形态】多年生草本，高30&mdash；80厘米，含乳汁，全株光滑无毛。茎直立，多分枝，圆柱形，有纵棱，紫红色。基生叶丛生，花期枯萎，卵形，矩圆形或披针形，长4～10厘米，宽2～4厘米，顶端急尖，基部渐狭成柄，边缘羽状分裂，有时为波状齿裂或琴状羽裂，裂片边缘具不细齿；茎生叶互生，舌状卵形，无柄，长4～8厘米，宽1～4厘米，顶端尖，基部微抱茎，耳状，边缘具不规则锯齿。头状花序排列成伞房状，具细柄；总苞圆筒形，长7～8毫米，无毛，外层总苞片短小，长约l毫米，内层总苞片线状披针形，每轮8；花全部为舌状花，黄色，长6～9毫米，花舌长4～6毫米，顶端5齿裂；花药黄色，联合；花柱细长，柱头2裂，黄色，有细毛。瘦果纺锤形，稍扁平，长1～2毫米，喙长约0.8毫米，成熟后黑褐色；冠毛白色。花期4～6月，果期7～10月。\n" +
                     "\n" +
                     "【产地分布】生于低山的山坡、田野和路旁。分布于全国大部分地区。\n" +
                     "\n" +
                     "【采收加工】春夏秋季均可采收，除去杂草，洗净，鲜用或晒干。\n" +
                     "\n" +
                     "【药材性状】全草约50厘米。茎呈圆柱形，直径1～4毫米.多分枝，光滑无毛，有纵棱；表面紫红色或青紫色；质硬而脆，断面髓部呈白色。叶皱缩，展开后呈舌状卵形，无柄，长4～8厘米，宽1～4厘米，顶端尖，基部耳状，抱茎，边缘具不规则锯齿，无毛，表面黄绿色。头状花序着生枝顶，黄色，冠毛白色；总苞圆筒形，果实纺锤形或圆形，稍扁平。气微，味苦，微酸涩。\n" +
                     "\n" +
                     "【性味归经】性微寒，味苦。\n" +
                     "\n" +
                     "【功效与作用】清热解毒，消肿。\n" +
                     "\n" +
                     "【临床应用】用量6～9克，水煎服，外用适量，捣烂敷患处。用于肺痈、乳痈、疖肿、跌打损伤、毒蛇咬伤。\n" +
                     "\n" +
                     "【主要成分】全草主要含黄酮类、香豆素和甾醇等成分。\n" +
                     "\n" +
                     "【使用禁忌】");

             chineseMedicineDao.insert(medicine61);

             ChineseMedicine medicine62=new ChineseMedicine();
             medicine62.setName("蜂房");
             medicine62.setSortType("F");
             medicine62.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545109218&di=39d41d9b2d6daab84c49ea94d014c4cc&imgtype=jpg&er=1&src=http%3A%2F%2Fstatic.fuwo.com%2Fupload%2Fattachment%2F1603%2F12%2Fce2d966ee82511e5ac2200163e00254c.jpg");
             medicine62.setData("【中药名】蜂房 fengfang\n" +
                     "\n" +
                     "【别名】露蜂房、蜂肠、马蜂窝、蜂巢、马蜂包。\n" +
                     "\n" +
                     "【英文名】Vespae Nidus\n" +
                     "\n" +
                     "【药用部位】胡蜂科昆虫果马蜂Polistes olivaceus (De Geer)等同属多种马蜂的蜂巢。\n" +
                     "\n" +
                     "【动物形态】雌蜂体长约17毫米。头部宽与胸部略相等。体各部黄色或暗黄色。额部后单眼处有一弧形黑斑。唇基略隆起，稀布浅刻点。上颌有浅刻点，端部3齿黑色。前胸背板两侧各有1棕色带。中胸背板中间有黑色纵隆线。小盾片、后小盾片、中胸侧板、后胸侧板各骨片连接处为黑色。胸腹节中央沟黑色，两侧各有1棕色带，布有横皱褶。翅棕色，前翅前缘色略深。\n" +
                     "\n" +
                     "【产地分布】群栖性，营巢于树木或房屋附近。分布于四川、云南、广东、广西等地。\n" +
                     "\n" +
                     "【采收加工】秋、冬季采收，直接晒干；或略蒸，除去死蜂死蛹，晒干。\n" +
                     "\n" +
                     "【药材性状】圆盘状、莲蓬状或不规则扁块状，大小不一。表面灰白色或灰褐色，腹面有多数排列整齐六角形小孔，孔径3～8毫米；背面有1个或数个黑色突起的短柄。体轻，质韧，略有弹性。气微，味辛、淡。\n" +
                     "\n" +
                     "【性味归经】性平，味甘。归胃经。\n" +
                     "\n" +
                     "【功效与作用】祛风、攻毒、杀虫、止痛。属杀虫止痒药。\n" +
                     "\n" +
                     "【临床应用】用量3～5克，煎服；外用适量，研末油调敷或煎水漱洗患处。用治龋齿牙痛、疮疡肿毒、乳痈、瘰疬、皮肤顽癣、鹅掌风。\n" +
                     "\n" +
                     "【药理研究】具明显的抗炎、镇痛、降温、促凝血的作用;可使心脏运动加强，降低外周阻力;能使离体肠蠕动及张力稍有减弱作用;对肝癌、胃癌有一定的抑制作用，对某些菌有一定的抑制作用。\n" +
                     "\n" +
                     "【化学成分】主含蜂蜡、树脂，尚含多种氨基酸、挥发油和钙、铁等多种无机元素。实验表明，本品水提液具有抗炎、补肾壮阳、促进血液凝固和扩张血管作用。此外还有降压和利尿作用。\n" +
                     "\n" +
                     "【使用禁忌】气虚血弱及肾功能不全者慎服。\n" +
                     "\n" +
                     "【配伍药方】①治手足风痹：黄蜂巢大者一个。小者三四个(烧灰)，独头蒜一碗，百草霜4.5克。同捣敷上。忌生冷荤腥。(《乾坤秘韫》)\n" +
                     "\n" +
                     "②治小儿脐风湿肿久不瘥：露蜂房，烧末敷之。(《子母秘录》)\n" +
                     "\n" +
                     "③治风瘾疹：以水煮蜂房取二升，入芒硝敷上，日五度。即瘥。(《梅师集验方》)\n" +
                     "\n" +
                     "④治蜂螫人：a.露蜂房末，猪膏和敷之。(《千金要方》)b.露蜂房、白矾各15克。上件药捣罗为末。以水煎如膏，厚涂螫处。(《圣惠方》)\n" +
                     "\n" +
                     "⑤治崩中漏下，青黄赤白：蜂房末，三指撮，酒服之。(《千金要方》)\n" +
                     "\n" +
                     "⑥治阴痿不起：蜂巢烧研，新汲井水服6克。(《峋嵝神书》)\n" +
                     "\n" +
                     "⑦治牙痛：露蜂房、天仙藤各等分。上件嚼咀。每用6克，水半盏，煎数沸，去滓漱之。(《杨氏家藏方》露蜂房散)\n" +
                     "\n");

             chineseMedicineDao.insert(medicine62);

             ChineseMedicine medicine63=new ChineseMedicine();
             medicine63.setName("茯苓");
             medicine63.setSortType("F");
             medicine63.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=47551b926e061d95694b3f6a1a9d61b4/7acb0a46f21fbe098537a1be6b600c338744ad2b.jpg");
             medicine63.setData("【中药名】茯苓 fuling\n" +
                     "\n" +
                     "【别名】茯灵、云苓、茯菟、松腴、松薯、松苓。\n" +
                     "\n" +
                     "【英文名】Poria。\n" +
                     "\n" +
                     "【药用部位】多孔菌科真菌茯苓Poria cocos (Schw.) Wolf.的菌核。\n" +
                     "\n" +
                     "【植物形态】子实体生于菌核上，一年生，平伏贴生。管口面白色，后变为淡褐色；管口多角形至不规则形；菌管单层，白色。菌肉白色至乳黄色。菌丝无锁状联合，有小囊状体，孢子长椭圆形至圆柱形，光滑无色。菌核球形、卵形至不规则形，大小不等，大者直径可达30厘米以上，新鲜时较软，干后变硬，有厚而多皱的皮壳，表面褐色至红褐色，干后变为黑褐色。菌核内部粉粒状，外层淡粉红色，内部白色。菌丝结构与子实体相似。\n" +
                     "\n" +
                     "【产地分布】多寄生于气候凉爽、干燥、向阳山坡上的马尾松、黄山松、赤松、云南松等针叶树的根部，深入地下20～30厘米处。分布于河北、山东、四川等地。\n" +
                     "\n" +
                     "【采收加工】全年均可采挖，一般多在7～9月，挖后去泥土、堆积，以草垫覆盖，使内部水分渗出，取出置通风处阴干，反复数次，直至干燥，即为“茯苓个”；在稍干、表面起皱时，削取外皮，为“茯苓皮”；中心部分切成的块片，为“茯苓块”与“茯苓片”；带棕红色或淡红色部分切成的片块称“赤茯苓”；近白色部分切成的片块称“白茯苓”；带松根者称“茯神”。\n" +
                     "\n" +
                     "【药材性状】类球形、椭圆形或为不规则团块，大小圆扁不一，长10～30厘米，外皮薄而粗糙，棕褐色至黑褐色，有明显皱纹，质坚实而重，难破碎，一般重1~1.5千克，小者重约0.5千克。断面颗粒性，有时具裂隙，外层淡棕色，内部白色，少数淡红色，有的中间抱有松根。无臭，味淡，嚼之粘牙。\n" +
                     "\n" +
                     "【性味归经】性平，味甘、淡。归心经、肺经、脾经、肾经。\n" +
                     "\n" +
                     "【功效与作用】利水渗湿、健脾宁心。属利水渗湿药下属分类的利水消肿药。\n" +
                     "\n" +
                     "【临床应用】用量9～15克，煎服或入丸服。用治水肿尿少、痰饮眩悸、脾虚食少、便溏泄泻、心神不安、惊悸失眠。\n" +
                     "\n" +
                     "【药理研究】可预防胃溃疡，对肝损伤有防治作用；有抗癌作用；且能加快心率。药理实验表明，具有利尿、抗菌作用，能提高机体的免疫能力。此外，还有降低血糖、降低胃酸、增强离体心脏心缩等作用。\n" +
                     "\n" +
                     "【化学成分】含三萜羧酸，有茯苓酸、土莫酸、齿孑L菌、松苓酸、松苓新酸等，还含有多聚糖，主要为茯苓聚糖，含量最高可达75%，经结构改造后可得到具有较强抗肿瘤活性的茯苓次聚糖。此外，尚含有组氨酸、腺嘌呤、胆碱、β-茯苓聚糖酶、蛋白酶、脂肪酸、脂肪、卵磷脂、麦角甾醇、茯苓素、茯苓新酸、3-氢化松苓酸、麦角甾-7，22-二烯-3β-醇、7，9(11)去氢茯苓酸等。\n" +
                     "\n" +
                     "【使用禁忌】阴虚而无湿热、虚寒滑精、气虚下陷者慎服。\n" +
                     "\n" +
                     "【配伍药方】①治水肿：白术(净)6克，茯苓9克，郁李仁4.5克。加生姜汁煎。(《不知医必要》茯苓汤)\n" +
                     "\n" +
                     "②治心下有痰饮，胸胁支满目眩：茯苓120克，桂枝、白术各90克，甘草60克。上四味。以水六升，煮取三升，分温三服，小便则利。(《金匮要略》苓桂术甘汤)\n" +
                     "\n" +
                     "③治卒呕吐，心下痞，膈间有水，眩悸者：半夏一升，生姜240克，茯苓90克。上三味，以水七升，煮取一升五合，分温再服。(《金匮要略》小半夏加茯苓汤)\n" +
                     "\n" +
                     "④治胃反，吐而渴欲饮水者：茯苓240克，泽泻120克，甘草60克，桂枝60克，白术90克，生姜120克。上六味。以水一斗，煮取三升，内泽泻，再煮取二升半，温服八合，日三服。(《金匮要略》茯苓泽泻汤)\n" +
                     "\n" +
                     "⑤治盗汗：茯苓75克，为末。每服6克，浓煎艾汤调下。(《普济方》陈艾汤)\n" +
                     "\n" +
                     "⑥治头风虚眩，暖腰膝，主五劳七伤：茯苓粉同曲米酿酒饮。(《纲目》茯苓酒)");

             chineseMedicineDao.insert(medicine63);

             ChineseMedicine medicine64=new ChineseMedicine();
             medicine64.setName("枫香脂");
             medicine64.setSortType("F");
             medicine64.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike72%2C5%2C5%2C72%2C24/sign=04fc8842bf3eb13550cabfe9c777c3b6/86d6277f9e2f0708acce8eaaea24b899a801f255.jpg");
             medicine64.setData("【中药名】枫香脂 fengxiangzhi\n" +
                     "\n" +
                     "【别名】白胶香、白胶、枫脂、胶香。\n" +
                     "\n" +
                     "【英文名】Liquidambaris Resina。\n" +
                     "\n" +
                     "【药用部位】金缕梅科植物枫香Liquidambar formosana Hance的干燥树脂。\n" +
                     "\n" +
                     "【植物形态】落叶乔木。树皮幼时灰白、平滑，老时褐色、粗糙。叶互生，叶柄长；叶片心形，常3裂，幼时及萌发枝上的叶多为掌状5裂，裂片卵状三角形或卵形，边缘有细锯齿。花单性，雌雄同株，无花被；雄花淡黄绿色，呈总状花序，雄蕊多数，密生成球形；雌花呈圆球形的头状花序，被毛，子房半下位，四周有许多钻形小苞片围绕，2室，花柱2，柱头弯曲。复果圆球形，下垂，表面有刺，蒴果多数，密集于复果之内，长椭圆形；种子多数，细小，扁平。花期3～4月，果期9～10月。\n" +
                     "\n" +
                     "【产地分布】生于低山次生林中及山谷疏林下。分布于我国南部、中部及西部地区。\n" +
                     "\n" +
                     "【采收加工】7～8月割裂树干，使树脂流出，10月至次年4月采收，阴干。\n" +
                     "\n" +
                     "【药材性状】不规则块状，淡黄色至黄棕色，半透明或不透明。质脆，断面具光泽。气香，味淡。\n" +
                     "\n" +
                     "【性味归经】性平，味辛、微苦。归肺经、脾经。\n" +
                     "\n" +
                     "【功效与作用】活血止痛、解毒、生肌、凉血。属拔毒生肌药。\n" +
                     "\n" +
                     "【临床应用】用量1.5～3克，宜入丸散服；外用适量。用治跌打损伤、痈疽肿痛、吐血、外伤出血等。\n" +
                     "\n" +
                     "【药理研究】具有抗血栓作用，促进纤溶活性和提高血小板cAMP，还有止血作用。\n" +
                     "\n" +
                     "【化学成分】主要含阿姆布酮酸、阿姆布醇酸、阿姆布二醇酸、枫香脂熊果酸、路路通酮酸、路路通二醇酸、枫香脂诺维酸。\n" +
                     "\n" +
                     "【使用禁忌】孕妇禁服。\n" +
                     "\n" +
                     "【配伍药方】①治臁疮：白胶香、黄柏、软石膏各30克，青黛、龙骨各15克，共研细末，香油调敷。(《证治准绳》)\n" +
                     "\n" +
                     "②治下疳：透明白胶香为末，入轻粉、麝香少许，用油调敷。(《袖珍方》)\n" +
                     "\n" +
                     "③治小儿疥癣杂疮：白胶香、黄柏、轻粉。上药研为细末，羊骨髓调涂癣上。(《儒门事亲》)\n" +
                     "\n" +
                     "④治金疮断筋：枫香(脂)末，敷之。(《代医得效方》)\n" +
                     "\n" +
                     "⑤治叶血不止：白胶香不计多少，细研为散，每服6克，新汲水调下。(《简要济众方》)\n" +
                     "\n" +
                     "⑥治虚劳咯血不止：枫香脂不计多少，细研为散。每服3克，煎人参糯米饮调下，不计时候。(《圣济总录》独圣散)\n" +
                     "\n" +
                     "⑦治衄血：蛤粉、白胶香等分。以好松烟墨汁调服。(《百一选方》)");

             chineseMedicineDao.insert(medicine64);

             ChineseMedicine medicine65=new ChineseMedicine();
             medicine65.setName("番泻叶");
             medicine65.setSortType("F");
             medicine65.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C36%2C816%2C539%3Bc0%3Dbaike92%2C5%2C5%2C92%2C30/sign=5d0989c8a54bd1131082ed72679f883c/4afbfbedab64034fed6cafbda7c379310b551de3.jpg");
             medicine65.setData("【中药名】番泻叶 fanxieye\n" +
                     "\n" +
                     "【别名】旃那叶、泻叶、泡竹叶、翼泻叶。\n" +
                     "\n" +
                     "【英文名】Sennae Folium。\n" +
                     "\n" +
                     "【药用部位】豆科植物狭叶番泻Cassia angustifolia Vahl或尖叶番泻Cassia acutifolia Delile的干燥小叶。\n" +
                     "\n" +
                     "【植物形态】狭叶番泻：小灌木，高达1米。叶为羽状复叶，具5～8对小叶，小叶具短柄。小叶片披针形，长23～46毫米，宽3.5～9毫米，先端渐尖，基部稍不对称，两面疏被毛近无毛，托叶卵状披针形，长2～4毫米。总状花序腋生，生花6～14朵，萼片5，长卵形，不等大，花瓣5，黄色，倒卵形，下面2瓣较大，雄蕊10，上部3枚不育且小形，中部4枚等长，下部3枚身下弯曲，花药略呈四方形，基部箭形，雌蕊弯曲呈镰刀状，子房具柄，疏被毛。荚果呈扁平长方形，长4～6厘米，宽1～1.7厘米，幼时疏被白毛，后渐脱落。种子4～7，略呈长方形而扁。花期9～12月，果期翌年3月。尖叶番泻：形态与狭叶番泻相似，主要不同点：小叶4～8对，小叶片长卵圆形，长1.3～1.7厘米，宽0.7～1.4厘米，先端急尖或月棘尖，叶基不对称，全缘，两面均有细短毛茸。夹果较宽，宽2～2.5厘米，先端尖突微小，不显，含种子6～7粒。\n" +
                     "\n" +
                     "【产地分布】原产于东非。在印度及巴基斯坦有大量栽培。我国云南也有小量引种栽培。\n" +
                     "\n" +
                     "【采收加工】春季花开时采收，除去泥沙，晒干。\n" +
                     "\n" +
                     "【药材性状】狭叶番泻：呈长卵形或卵状披针形，长1.5～5厘米，宽0.4～2厘米，叶端急尖，叶基稍不对称，全缘。上表面黄绿色，下表面浅黄绿色，无毛或近无毛，叶脉稍隆起。革质。气微弱而特异，味微苦，稍有黏性。尖叶番泻：呈披针形或长卵形，略卷曲，叶端短尖或微突，叶基不对称，两面均有细短毛茸。\n" +
                     "\n" +
                     "【性味归经】性寒，味甘、苦。归大肠经。\n" +
                     "\n" +
                     "【功效与作用】泻热行滞，通便，利水。属泻下药下属分类的润下药。\n" +
                     "\n" +
                     "【临床应用】用量2～6克，后下，或开水泡服。治疗热结积滞，便秘腹痛，水肿胀满。\n" +
                     "\n" +
                     "【药理研究】具有显著的泻下作用；止血；对多种细菌以及白色念珠菌和某些致病性皮肤真菌有抑制作用。可使降低的肠黏膜组胺含量恢复至正常水平，能阻断神经一肌肉接头冲动的传递，阻止乙酰胆碱与M受体的结合而使肌肉松弛。\n" +
                     "\n" +
                     "【化学成分】狭叶番泻叶含番泻甙A、B C、D，大黄酚，大黄素，大黄素甲醚，3-甲基-8-申氧基-2-乙酰基-1，6-萘二酚-6-O-β-D-葡萄糖甙，小叶中含山奈酚。尖叶番泻叶含番泻甙A、B、C、D，大黄素，大黄素甲醚，大黄酚。嫩叶中含山奈酚。此外，同属植物耳叶番泻叶含花白甙，树皮含多酚氧化酶。\n" +
                     "\n" +
                     "【使用禁忌】体虚及孕妇、经期及哺乳期禁服。用量过大，易致腹痛、恶心、呕吐。\n" +
                     "\n" +
                     "【配伍药方】①治胃弱消化不良，便秘腹胀、胸闷：番泻叶3克，生大黄2克，橘皮3克，黄连1.5克，丁香2克，生姜3克。沸开水100毫升，温浸2小时，去渣滤过，每日3次分服。(《现代实用中药》)");

             chineseMedicineDao.insert(medicine65);

             ChineseMedicine medicine66=new ChineseMedicine();
             medicine66.setName("翻白草");
             medicine66.setSortType("F");
             medicine66.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=717d230479899e516c83324623ceb256/3812b31bb051f819c29ff3b3d8b44aed2f73e795.jpg");
             medicine66.setData("【中药名】翻白草 fanbaicao\n" +
                     "\n" +
                     "【别名】鸡腿儿、天藕儿、鸡脚草、鸡脚爪、金钱吊葫芦。\n" +
                     "\n" +
                     "【英文名】Potentillae Discoloris Herba\n" +
                     "\n" +
                     "【药用部位】蔷薇科植物翻白草Potentilla discolor Bunge的干燥全草。\n" +
                     "\n" +
                     "【植物形态】 多年生草本。根粗壮，下部常肥厚呈纺锤状。花茎直立，上升或铺散，高10～45厘米，密被白色绒毛。基生叶有小叶2～4对，对生或互生；叶柄密被白色绵 毛，有时并有长柔毛，小叶无柄；托叶膜质，褐色，外面密被白色长柔毛；小叶片长圆形或长圆状披针形，长1～5厘米，宽5～8毫米，先端圆钝，稀急尖，下面 暗绿色，被疏白色绵毛或脱落几无毛，下面密被白色或灰白色绵毛；茎生叶1～2，有掌状3～5小叶，托叶草质，卵形或宽卵形，边缘常有缺刻状牙齿，下面密被 白色绵毛。花两性；聚伞花序，花梗长1～2.5厘米，外被绵毛；花直径1～2厘米；萼片三角状卵形，副萼片披针形，比萼片短，外被白色绵毛；花瓣黄色，倒 卵形，先端微凹或圆钝，比萼片长；花柱近顶生。瘦果近肾形，宽约1毫米，光滑。花、果期5～9月。\n" +
                     "\n" +
                     "【产地分布】生于海拔100～1850米的荒地、山谷、沟边、山坡草地、草甸及疏林下。分布于东北、华北、华东、中南及陕西、四川等地。\n" +
                     "\n" +
                     "【采收加工】夏、秋二季开花前采挖，除去泥沙和杂质，干燥。\n" +
                     "\n" +
                     "【药材性状】 块根呈纺锤形或圆柱形，长4～8厘米，直径0.4～1厘米；表面黄棕色或暗褐色，有不规则扭曲沟纹；质硬而脆，折断面平坦，呈灰白色或黄白色。基生叶丛 生，单数羽状复叶，多皱缩弯曲，展平后长4～13厘米；小叶5～9片，柄短或无，长圆形或长椭圆形，顶端小叶片较大，上表面暗绿色或灰绿色，下表面密被白 色绒毛，边缘有粗锯齿。气微，味甘、微涩。\n" +
                     "\n" +
                     "【性味归经】性平，味甘、微苦。归肝经、胃经、大肠经。\n" +
                     "\n" +
                     "【功效与作用】清热解毒，止痢，止血。属清热药下属分类的清热解毒药。\n" +
                     "\n" +
                     "【临床应用】内服：煎汤，用量10～15克；或浸酒服。外用：适量，煎水熏洗或鲜品捣敷。用于湿热泻痢，痈肿疮毒，血热吐衄，便血，崩漏。\n" +
                     "\n" +
                     "【药理研究】抗菌作用。\n" +
                     "\n" +
                     "【化学成分】含延胡索酸、没食子酸、原儿茶酸、槲皮素、柚皮素、山柰酚、间苯二酸等成分。\n" +
                     "\n" +
                     "【使用禁忌】孕妇慎用。\n" +
                     "\n" +
                     "【配伍药方】①治肺痈：鲜翻白草根30克，老鼠刺根、杜瓜根各15克，加水煎成半碗，饭前服，日服二次。(《福建民间草药》)\n" +
                     "\n" +
                     "②治急性喉炎，扁桃体炎，口腔炎：翻白草鲜全草适量，捣烂取汁含咽。(《浙江药用植物志》)\n" +
                     "\n" +
                     "③治慢性鼻炎，咽炎，口疮：翻白草15克，地丁12克。水煎服。(《山西中草药》)\n" +
                     "\n" +
                     "④治疟疾寒热及无名肿毒：翻白草根五七个，煎酒服之。(《纲目》)\n" +
                     "\n" +
                     "⑤治大便下血：翻白草根45克，猪大肠不拘量。加水同炖，去渣，取汤及肠同服。(江西《民间草药》)\n" +
                     "\n" +
                     "⑥治赤白痢疾(包括阿米巴痢疾)：a.翻白草15克，白头翁30克。水煎服。(山东《中药学》)；b.翻白草18～24克，赤芍、甘草各6克。水煎去渣。每日2次分服。(《食物中药与便方》)\n" +
                     "\n" +
                     "⑦治痛经：翻白草(连根)45克，益母草10克。水煎酌加红糖，黄酒服。(《河南中草药手册》)\n" +
                     "\n" +
                     "⑧治牙痛：翻白草根。炖猪肉服。(《湖南药物志》)");

             chineseMedicineDao.insert(medicine66);

             ChineseMedicine medicine67=new ChineseMedicine();

             medicine67.setName("浮萍");
             medicine67.setSortType("F");
             medicine67.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=138a5b74b91bb0519b29bb7a5713b1d1/7af40ad162d9f2d3edd173b9a3ec8a136227cca0.jpg");
             medicine67.setData("【中药名】浮萍 fuping\n" +
                     "\n" +
                     "【别名】苹、水萍、田萍、萍子草、九子萍。\n" +
                     "\n" +
                     "【英文名】Spirodelae Herba\n" +
                     "\n" +
                     "【药用部位】浮萍科植物紫萍Spirodela polyrrhiza (L.) Schleid.的全草。\n" +
                     "\n" +
                     "【植物形态】多年生漂浮植物。叶状茎扁平，倒卵形或椭圆形，直径3～6毫米，长6～9毫米，先端圆，上面绿色，有光泽，下面紫红色，常3～4片相连，自中央下垂10余条纤维状须根，中心有明显的维管束一条，束端有根侔。佛焰苞矮小，唇形。花序由2朵雄花及1朵雌花组成，白色或淡绿色。花期夏季。\n" +
                     "\n" +
                     "【产地分布】生于湖沼、池塘或水田中。我国各地都有分布。\n" +
                     "\n" +
                     "【采收加工】6～9月捞取，洗净，除去杂质，晒干。\n" +
                     "\n" +
                     "【药材性状】扁平叶状体，卵形或卵圆形，长径2～5毫米。上表面淡绿色至灰绿色，偏侧有一小凹陷，边缘整齐或微卷曲。下表面紫绿色至紫棕色，着生数条须根。体轻，手捻易碎。气微，味淡。\n" +
                     "\n" +
                     "【性味归经】性寒，味辛。归肺经。\n" +
                     "\n" +
                     "【功效与作用】宣散风热、透疹、利尿。属解表药下分类的辛凉解表药。\n" +
                     "\n" +
                     "【临床应用】用量3～9克，内服煎汤，或捣汁，或入丸散，治疗麻疹不透、风疹瘙痒、水肿尿少。外用适量，煎汤浸洗，或研末撒或调敷。\n" +
                     "\n" +
                     "【药理研究】具有解热作用；对正常蛙心无明显影响，对衰竭的蛙心有显著强心作用，尚有收缩血管和升高血压作用；具有一定的抗感染作用，能抑制蚊类幼虫生长，降低蚊类幼虫密度。尚有一定抗凝作用；可用于防治高氟所致的氟中毒。\n" +
                     "\n" +
                     "【化学成分】含醋酸钾及氯化钾、碘、溴、荭草素、木犀草素-7-单糖苷、牡荆素、谷固醇、堇黄质、棕榈酸等成分。\n" +
                     "\n" +
                     "【使用禁忌】表虚自汗者禁服。\n" +
                     "\n" +
                     "【配伍药方】①治风热感冒：浮萍、防风各9克，牛蒡子、薄荷、紫苏叶各6克。水煎服。(《全国中草药汇编》)\n" +
                     "\n" +
                     "②治身上虚痒：浮萍末3克，以黄芩3克同四物汤煎汤调下。(《丹溪纂要》)\n" +
                     "\n" +
                     "③治小便不利，膀胱水气流滞：浮萍日干为末，饮服方寸匕，日一二服。(《千金翼方》)\n" +
                     "\n" +
                     "④治急性肾炎：a.浮萍60克，黑豆30克。水煎服。(《全国中草药汇编》)；b.单用浮萍干品9～12克，为末，白糖调服。(《浙南本草新编》)\n" +
                     "\n" +
                     "⑤治吐血不止：紫背浮萍(焙)15克，黄芪(炙)7.5克。为末，每服3克，姜、蜜水调下。(《圣济总录》)\n" +
                     "\n" +
                     "⑥主生发：以浮萍作汤沐浴，又为膏敷之。(《普济方》)");

             chineseMedicineDao.insert(medicine67);

             ChineseMedicine medicine68=new ChineseMedicine();
             medicine68.setName("覆盆子");
             medicine68.setSortType("F");
             medicine68.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=19827eaa4836acaf4ded9eae1db0e675/38dbb6fd5266d016db49a3bb942bd40734fa35d4.jpg");
             medicine68.setData("【中药名】覆盆子 fupenzi\n" +
                     "\n" +
                     "【别名】乌藨子、覆盆、小托盘、山泡、竻蔗子。\n" +
                     "\n" +
                     "【英文名】Rubi Fructus\n" +
                     "\n" +
                     "【药用部位】蔷薇科植物华东覆盆子Rubus chingii Hu的聚合果。\n" +
                     "\n" +
                     "【植物形态】落叶灌木。茎直立，枝条细长，疏生微变倒刺。裂片卵状披针形，先端长渐尖，边缘有重齿，基部浅心形，主脉5条。花白色，花瓣5片，卵圆形。雄蕊多数；雌蕊有多数离生心皮。聚合果卵球形，红色，下垂。花期4～5月，果期6～7月。\n" +
                     "\n" +
                     "【产地分布】生于溪旁或山坡灌木丛中、林缘及乱石堆中。分布于安徽、江苏、浙江等地。\n" +
                     "\n" +
                     "【采收加工】6～8月，将未成熟的青色聚合果摘下，放入沸水中稍浸后，置烈日下晒干，除去果梗杂质。\n" +
                     "\n" +
                     "【药材性状】多数小核果聚合，全体呈半球形、扁圆锥形或类球形，上部钝圆，基部中心凹入。长6～13毫米，直径5～12毫米。表面灰绿色或淡棕色，密被灰白色或灰绿色短毛；宿萼棕褐色，5裂，裂片先端多已折断，上有许多棕色残存花丝，下有细长果柄或其痕迹。横切面类圆形，周围有小核果紧密排列成环，小核果囊状，长2～2.5毫米，宽0.8～1.2毫米，基部较小，背面隆起，密被灰白色或黄绿色茸毛，两侧有网状凹纹，腹部有突起棱线，质硬，内含棕色种子1粒。气清香，味微酸涩。\n" +
                     "\n" +
                     "【性味归经】性温，味甘、酸。归肾经、膀胱经。\n" +
                     "\n" +
                     "【功效与作用】益肾、固精、缩尿。属收涩药下分类的固精缩尿止带药。\n" +
                     "\n" +
                     "【临床应用】用量6～12克，煎汤内服；或入丸、散，亦可浸酒或熬膏。用治肾虚不固所致的遗精、滑精、遗尿、尿频；肝肾不足之目暗不明等。\n" +
                     "\n" +
                     "【药理研究】本品似有雌激素样作用，并能抑制霍乱弧菌生长。\n" +
                     "\n" +
                     "【化学成分】含覆盆子酸、鞣花酸、覆盆子苷F1、覆盆子苷F2、覆盆子苷F3、覆盆子苷F4、覆盆子苷F5、覆盆子酸、β-谷固醇等成分。聚合果每100克中含维生素C3379毫克、水分79.93%、蛋白质2.232%、总糖6.750%、有机酸1.472%，以及维生素A类物质，其含量按维生素A计算为0.058 6%。\n" +
                     "\n" +
                     "【使用禁忌】肾虚火旺，小便短赤者慎服。\n" +
                     "\n" +
                     "【配伍药方】①治尿崩症，年老体虚小便失禁：覆盆子9克，山药、益智仁、乌梅各6克，炙甘草4.5克。煎服。(《安徽中草药》)\n" +
                     "\n" +
                     "②治阳事不起：覆盆子，酒浸，焙研为末。每旦酒服9克。(《濒湖集简方》)\n" +
                     "\n" +
                     "③治小儿肾虚遗尿：覆盆子30克，用水2碗，文火煎至1碗，去渣取汤，再用药汤煮瘦猪肉60～90克，不加作料，文火煮熟。肉和汤同时吃下。每日服1次，一般2～3次可愈。[《中医杂志》1983，24 (5)：377]");

             chineseMedicineDao.insert(medicine68);

             ChineseMedicine medicine69=new ChineseMedicine();
             medicine69.setName("榧子");
             medicine69.setSortType("F");
             medicine69.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=9a2b6a5ed8b44aed4d43b6b6d275ec64/2fdda3cc7cd98d101dda6525233fb80e7bec9093.jpg");
             medicine69.setData("【中药名】榧子 feizi\n" +
                     "\n" +
                     "【别名】榧实、玉山果、赤果、玉榧、香榧、枝子。\n" +
                     "\n" +
                     "【英文名】Torreyae Semen。\n" +
                     "\n" +
                     "【药用部位】红豆杉科植物榧Torreya grandis Fort.的干燥成熟种子。\n" +
                     "\n" +
                     "【植物形态】常绿乔木，高达25米。一年生枝绿色，二至三年生枝暗绿黄色或灰褐色，稀微带紫色。叶螺旋状排列，扭曲成二列；叶片线形，长11～25毫米，宽2.5～3.5毫米，先端凸尖，上面深紫色，有光泽，下面淡绿色，中脉两侧各有一条与中脉带等宽的黄白色气孔带。花单性，雌雄异株，雄球花圆柱状，长约8毫米，雄蕊多数，各有4个花药；雌球花无柄，成对生于叶腋。种子核果状，椭圆形、卵圆形或倒卵状长圆形，长2～4.5厘米，熟时假种皮淡紫褐色，有白粉，顶端微凹，基部具宿存的苞片，胚乳微皱。花期4月，种子翌年10月成熟。\n" +
                     "\n" +
                     "【产地分布】生于温暖多雨的黄壤、红壤及黄褐土地区。为我国特有树种。分布于江苏、浙江、安徽、福建、江西、湖南、贵州等省区。亦有人工栽培。\n" +
                     "\n" +
                     "【采收加工】秋季种子成熟时采收，除去肉质假种皮，洗净，晒干。\n" +
                     "\n" +
                     "【药材性状】本品呈卵圆形或长卵圆形，长2～3.5厘米，直径1.3～2厘米。表面灰黄色或淡黄棕色，有纵皱纹，一端钝圆，可见椭圆形的种脐，另端稍尖。种皮质硬，厚约1毫米。种仁表面皱缩，外胚乳灰褐色，膜质； 内胚乳黄白色，肥大，富油性。气微，味微甜而涩。\n" +
                     "\n" +
                     "【性味归经】性平，味甘。归肺经、胃经、大肠经。\n" +
                     "\n" +
                     "【功效与作用】杀虫消积，润肺止咳，润燥通便。属驱虫药。\n" +
                     "\n" +
                     "【临床应用】用量内服：煎汤，15～50克，连壳生用，打碎入煎；或10～40枚，炒熟去壳，取种仁嚼服。用于钩虫病、蛔虫病、绦虫病，虫积腹痛，小儿疳积，肺燥咳嗽，大便秘结。\n" +
                     "\n" +
                     "【药理研究】驱钩虫。日本产榧子Torreya nucifera 含生物碱，对子宫有收缩作用，民间用以堕胎。\n" +
                     "\n" +
                     "【化学成分】种子含54.3%的脂肪油，其不饱和脂肪酸含量高达74.88%。\n" +
                     "\n" +
                     "【使用禁忌】脾虚泄泻及肠滑大便不实者慎服。\n" +
                     "\n" +
                     "【配伍药方】①治十二指肠钩虫、蛔虫、蛲虫等：榧子(切碎)30克，使君子仁(切细)30克，大蒜瓣(切细)30克。水煎去滓，每日3次，食前空腹时服。(《现代实用中药》)\n" +
                     "\n" +
                     "②治寸白虫：榧子日食七颗，满七日。(《食疗本草》)");

             chineseMedicineDao.insert(medicine69);

             ChineseMedicine medicine70=new ChineseMedicine();
             medicine70.setName("浮小麦");
             medicine70.setSortType("F");
             medicine70.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=10de994b9925bc313f5009ca3fb6e6d4/fc1f4134970a304e1834ec7fd1c8a786c9175c24.jpg");
             medicine70.setData("【中药名】浮小麦 fuxiaomai\n" +
                     "\n" +
                     "【别名】浮麦。\n" +
                     "\n" +
                     "【英文名】Fructus Tritici Levis\n" +
                     "\n" +
                     "【药用部位】为禾本科植物小麦Triticum aestivum L.干瘪轻浮的干燥颖果。\n" +
                     "\n" +
                     "【植物形态】一年生或越年生草本，高60～100厘米。秆直立，通常6～9节。叶鞘光滑，常较节间为短；叶舌膜质，短小；叶片扁平，长披针形，长15～40厘米，宽8～14毫米，先端渐尖，基部方圆形。穗状花序直立；长3～10厘米；小穗两侧扁平，长约12毫米，在穗轴上平行排列或近于平行，每小穗具3～9花，仅下部的花结实；颖短，第1颖较第2颖为宽，两者背面均具有锐利的脊，有时延伸成芒；外稃膜质，微裂成3齿状，中央的齿常延伸成芒，内稃与外稃等长或略短，脊上具鳞毛状的窄翼；雄蕊3；子房卵形。颖果长圆形或近卵形，长约6毫米，浅褐色。花期4～5月，果期5～6月。\n" +
                     "\n" +
                     "【产地分布】全国各地大量栽培。\n" +
                     "\n" +
                     "【采收加工】夏至前后，成熟果实采收后，取瘪瘦轻浮与未脱净皮的麦粒，筛去灰屑，用水漂洗，晒干。\n" +
                     "\n" +
                     "【药材性状】干瘪颖果呈长圆形，两端略尖，长约7毫米，直径约2.6毫米。表面黄白色，皱缩。有时尚带有未脱净的外稃与内稃。腹面有一深陷的纵沟，顶端钝形，带有浅黄棕色柔毛，另一端成斜尖形，有脐。质硬而脆，易断，断面白色，粉性差。无臭，味淡。\n" +
                     "\n" +
                     "【性味归经】性凉，味甘。归心经。\n" +
                     "\n" +
                     "【功效与作用】除虚热、止汗。属清热药下属分类的清虚热药，或属收涩药下属分类的固表止汗药。\n" +
                     "\n" +
                     "【临床应用】内服：煎汤，用量15～30克，或研末；止汗，宜微炒用。主治阴虚发热、盗汗、自汗。\n" +
                     "\n" +
                     "【化学成分】主要含淀粉、蛋白质、糖类等成分。\n" +
                     "\n" +
                     "【使用禁忌】无汗而烦躁或虚脱汗出者忌用。\n" +
                     "\n" +
                     "【配伍药方】①治盗汗及虚汗不止：浮小麦不以多少。文武火炒令焦，为细末，每服6克，米饮汤调下，频服为佳。(《卫生宝鉴》独圣散)\n" +
                     "\n" +
                     "②治盗汗：用浮小麦一抄。煎汤。调防风末6克服。(《卫生易简方》)\n" +
                     "\n" +
                     "③治男子血淋不止：浮小麦加童便炒为末，砂糖煎水调服。(《奇方类编》)\n" +
                     "\n" +
                     "④治脏躁症：浮小麦30克，甘草15克，大枣10枚。水煎服。(《青岛中草药手册》)");

             chineseMedicineDao.insert(medicine70);

             ChineseMedicine medicine71=new ChineseMedicine();
             medicine71.setName("佛手");
             medicine71.setSortType("F");
             medicine71.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=1066ea2bae345982d187edc06d9d5ac8/7c1ed21b0ef41bd52ad596ab52da81cb38db3dee.jpg");
             medicine71.setData("【中药名】佛手 foshou\n" +
                     "\n" +
                     "【别名】广佛手、佛手果、五指柑、佛手柑、密罗柑。\n" +
                     "\n" +
                     "【英文名】Citri Sarcodactμlis Fructus。\n" +
                     "\n" +
                     "【药用部位】芸香科植物佛手Citrus medicaL.var. sarcodactylis Swingle.的果实。\n" +
                     "\n" +
                     "【植物形态】常绿小乔木或灌木。老枝灰绿色，幼枝略带紫红色，有短而硬的刺。单叶互生，革质，具透明油点；叶柄短，无翅，无关节；叶片长椭圆形或倒卵状长圆形，先端钝，有时微凹，基部近圆形或楔形，边缘有浅波状钝锯齿。花单生、簇生或为总状花序；花萼杯状，裂片三角形；花瓣内面白色，外面紫色；雄蕊多数；子房椭圆形，上部窄尖。柑果卵形或长圆形，顶端分裂如拳状，或张开似指状，其裂数代表心皮数，表面橙黄色，粗糙，果肉淡黄色。种子数粒，卵形，先端尖，有时不完全发育。花期4～5月，果期10～12月。\n" +
                     "\n" +
                     "【产地分布】生长于热带、亚热带，喜阳光充足、排水良好的沙质壤土。分布于广东等地。\n" +
                     "\n" +
                     "【采收加工】秋季果实由绿开始变黄将成熟时采摘。晾数天，待大部分水分蒸发后，纵切成薄片，晒干或低温干燥。\n" +
                     "\n" +
                     "【药材性状】类椭圆形或卵圆形薄片，常皱缩或卷曲。顶端稍宽，基部略窄，有的可见果梗痕。外皮黄绿色或橙黄色，有皱纹及油点。果肉浅黄白色，散有凹凸不平的线状或点状维管束。质硬而脆，受潮后柔韧。气香，味微甜后苦。\n" +
                     "\n" +
                     "【性味归经】性温，味辛、苦、酸。归肺经、脾经、肝经。\n" +
                     "\n" +
                     "【功效与作用】舒肝理气、和胃止痛。属理气药。\n" +
                     "\n" +
                     "【临床应用】用量3～9克，煎服。用治肝胃气滞、胸胁胀痛、胃脘痞满、食少呕吐。陈佛手2～3钱，水煎饮，治痰气咳嗽(《闽南民间草药》)。\n" +
                     "\n" +
                     "【药理研究】平喘；解痉；中枢抑制；增加心脏的冠脉流量和提高耐缺氧能力，增强毛细血管的抵抗力和减少肾上腺抗坏血酸耗竭；抗炎。醇提取物有解胃肠平滑肌痉挛作用以及增加冠脉血流量和降压作用。醇提取物对离体大鼠肠管有明显抑制作用；且给猫静脉注射有短时间抑制心脏和降压作用；其柠檬油素具对豚鼠离体气管有抗过敏作用。\n" +
                     "\n" +
                     "【化学成分】含挥发油、黄酮、香豆精、多糖、橙皮苷、柠檬油素、6,7-二甲氧基香豆素、香叶木素、香叶木苷、白当归素、5-甲氧基糠醛等成分。\n" +
                     "\n" +
                     "【使用禁忌】阴虚有火，无气滞症状者慎服。\n" +
                     "\n" +
                     "【配伍药方】①治面寒痛，胃气痛：佛手柑，新瓦焙，为末(黄色)。烧酒送下，每服9克。(《滇南本草》)\n" +
                     "\n" +
                     "②治食欲不振：佛手、枳壳、生姜各3克，黄连l克。水煎服，每日1剂。(《全国中草药汇编》)\n" +
                     "\n" +
                     "③治肝胃气痛：鲜佛手12～15克，开水冲泡，代茶饮。或佛手、延胡索各6克，水煎服。(《全国中草药汇编》)\n" +
                     "\n" +
                     "④治臌胀发肿：佛手120克，人中白90克。共为末。空腹白汤下。(《岭南采药录》)\n" +
                     "\n" +
                     "⑤治湿痰咳嗽：佛手、姜半夏各6克，砂糖等分。水煎服。(《全国中草药汇编》)");

             chineseMedicineDao.insert(medicine71);

             ChineseMedicine medicine72=new ChineseMedicine();
             medicine72.setName("附子");
             medicine72.setSortType("F");
             medicine72.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike220%2C5%2C5%2C220%2C73/sign=de943d2d8c5494ee932f074b4c9c8b9b/fc1f4134970a304e08551c68d1c8a786c9175c28.jpg");
             medicine72.setData("【中药名】附子 fuzi\n" +
                     "\n" +
                     "【别名】侧子、虎掌、熟白附子、黑附子、明附片、刁附、川附子。\n" +
                     "\n" +
                     "【英文名】Aconiti Lateralis Radix Praeparata。\n" +
                     "\n" +
                     "【来源】毛茛科植物乌头Aconitum carmichaelii Debx的子根的加工品。\n" +
                     "\n" +
                     "【植物形态】多年生草本。主根纺锤形或倒卵形，通常2个连生，栽培品的主根周围常生有数个肥大的侧根(子根)。茎直立，高60～150厘米，中部以上疏被反曲的短柔毛，等距离生叶，有分枝。茎下部叶在花期枯萎。茎中部叶互生；叶片革质或纸质，五角形，长6～11厘米，宽9～15厘米，基部浅心形，掌状三全裂或三深裂达近基部，中央全裂片常为宽菱形，顶端急尖，有时近羽状分裂，二回裂片斜三角形，侧全裂片不等二深裂，表面疏被短伏毛，背面常沿脉疏被短柔毛；叶柄长1～2.5厘米，疏被短柔毛。顶生总状花序长6～25厘米，花序轴和花梗多少密被反曲而紧贴的短柔毛；花梗中部或下部生有小苞片；花萼片5，蓝紫色，外面被短柔毛；上萼片高盔形，高2～2.6厘米，喙不明显，侧萼片长1.6～2厘米；花瓣2，无毛，瓣片长约11毫米，唇长约6毫米；微凹，距长1～2.5毫米，通常拳卷；雄蕊多数，无毛或疏被短毛；心皮3～5，离生。蓇葖果长1.5～1.8厘米；种子三棱形，长3～3.2毫米，只在二面密生横膜翅。花期6～8月，果期8～10月。\n" +
                     "\n" +
                     "【产地分布】生于山地草坡、灌丛及林缘。分布于云南、四川、湖北、贵州、湖南、广西、广东、江西、浙江、江苏、安徽、陕西、河南、山东、辽宁等省区。四川、陕西为主要栽培产区，栽培品的主根(母根)加工为川乌，侧根(子根)加工为附子。\n" +
                     "\n" +
                     "【采收加工】6月下旬至8月上旬采挖，除去母根、须根及泥沙，习称“泥附子”，加工成下列规格：(1)选择个大、均匀的泥附子，洗净，浸入食用胆巴昀水溶液中过夜，再加食盐.继续浸泡，每日取出晒晾，并逐渐延长晒晾时间，直至附子表面出现大量结晶盐粒(盐霜)、体质变硬为止，习称“盐附子”。(2)取泥附子.按大小分别洗净，浸入食用胆巴的水溶液中数日，连同浸液煮至透心，捞出，水漂，纵切成厚约0.5厘米的片，再用水浸漂，用调色液使附片染成浓茶色，取出，蒸至出现油面、光泽后，烘至半干，再晒干或继续烘干，习称“黑顺片”。(3)选择大小均匀的泥附子，洗净，浸入食用胆巴的水溶液中数日，连同浸液煮至透心，捞出，剥去外皮，纵切成厚约0.3厘米的片，用水浸漂，取出，蒸透，晒干，习称“白附片”。\n" +
                     "\n" +
                     "【药材性状】 盐附子：呈圆锥形，长4～7厘米，直径3～5厘米。表面灰黑色，被盐霜，顶端有凹陷的芽痕，周围有瘤状突起的支根或支根痕。体重，横切面灰褐色，可见充满盐霜的小空隙和多角形形成层环纹，环纹内侧导管束排列不整齐。气微，味成而麻，刺舌。盐附子：为纵切片，上宽下窄，长1.7～5厘米，宽0.9～3厘米，厚0.2～0.5厘米。外皮黑褐色，切面暗黄色，油润具光泽，半透明状，并有纵向导管束。质硬而脆，断面角质样。气微，味淡。白附片：无外皮，黄白色，半透明，厚约0.3厘米。\n" +
                     "\n" +
                     "【性味归经】性热，味辛、甘，有大毒。归心经、肾经、脾经。\n" +
                     "\n" +
                     "【功效与作用】回阳救逆，补火助阳，散寒止痛。属温里药。\n" +
                     "\n" +
                     "【临床应用】用量3～15克，先煎，久煎。用于亡阳虚脱，肢冷脉微，心阳不足，胸痹心痛，虚寒吐泻，脘腹冷痛，肾阳虚衰，阳痿宫冷，阴寒水肿，阳虚外感，寒湿痹痛。\n" +
                     "\n" +
                     "【药理研究】强心；抗心肌缺血；抗休克；抑制凝血功能和抗血栓形成；抗炎；镇痛；局部麻醉；增强体液免疫；兴奋肠管自发性收缩，抑制胃排空；平喘、松弛气管；具有对抗平滑肌痉挛等作用。\n" +
                     "\n" +
                     "【化学成分】附子含乌头碱，中乌头碱，次乌头碱，塔拉乌头胺，和乌胺即是消旋去甲基衡州乌药碱，棍掌碱氯化物，异飞燕草碱，苯甲酰中乌头碱，新乌宁碱，附子宁碱，北乌头碱，多根乌头碱，去氧乌头碱，附子亭碱，准葛尔乌头碱尿嘧啶，江油乌头碱，新江油乌头碱，去甲猪毛菜碱，附子苷等。\n" +
                     "\n" +
                     "【使用禁忌】阴虚阳盛，真热假寒及孕妇均禁服。服药时不宜饮酒，不宜以白酒为引。不宜与半夏、瓜蒌、天花粉、贝母、白蔹、白及同用。\n" +
                     "\n" +
                     "【配伍药方】①治吐利汗出，发热恶寒，四肢拘急，手足厥冷者：甘草60克(炙)，干姜45克，附子(生用，去皮，破八片)一枚。上三味，以水三升，煮取一升二合，去滓。分温再服。(《伤寒论》四逆汤)\n" +
                     "\n" +
                     "②治小儿飨泄：附子15克，诃子肉30克，灶心土30克。上为末。陈米糊丸，如粟大，清米汤下。(《痘疹传心录》附诃丸)\n" +
                     "\n" +
                     "③治呕逆翻胃：大附子一个，生姜一个(细锉)。煮研如面糊，米饮下。(《经验方》)\n" +
                     "\n" +
                     "④治阴虚牙痛：生附子研末，口津调敷两足心，极效。(《华佗神医秘传》)\n" +
                     "\n" +
                     "⑤治中风厥冷：生附子0.3克，木香0.15克。上锉细，每服1.5克，姜二片，煎服。(《普济方》附子散)");

             chineseMedicineDao.insert(medicine72);

             ChineseMedicine medicine73=new ChineseMedicine();

             medicine73.setName("防己");
             medicine73.setSortType("F");
             medicine73.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=2545b0786d061d95694b3f6a1a9d61b4/e4dde71190ef76c62bab326b9e16fdfaaf516771.jpg");
             medicine73.setData("【中药名】防己 fangji\n" +
                     "\n" +
                     "【别名】汉防己、石蟾蜍、白木香、山乌龟。\n" +
                     "\n" +
                     "【英文名】Radix Stephaniae Tetrandrae(拉)。\n" +
                     "\n" +
                     "【来源】防己科植物粉防己Stephania tetrandraS.Moore的块根。\n" +
                     "\n" +
                     "【植物形态】多年生缠绕藤本。根圆柱状，有时呈块状，外皮淡棕色或棕褐色。茎柔韧，圆柱形，有时稍扭曲，具细条纹，枝光滑无毛，基部稍带红色。叶互生，质薄较柔，叶柄盾状着生，长与叶片相等；叶片外形近圆形，先端锐尖，基部截形或稍心形，全缘，两面均被短柔毛，上面绿色，下面灰绿色。花小，雌雄异株，为头状的聚伞花序；雄花花萼4，肉质，三角状，基部楔形，外面被毛，花瓣4，略呈半圆形，边缘微向内弯，具爪，雄蕊4花药近圆形；雌花的花萼、花瓣与雄花同数，无退化雄蕊，心皮1，花柱3枚。核果球形。花期5～6月，果期7～9月。\n" +
                     "\n" +
                     "【产地分布】生于山坡、丘陵地带的草丛及灌木林的边缘。分布于江苏、安徽南部、浙江等地。\n" +
                     "\n" +
                     "【采收加工】秋季采挖，洗净或刮去栓皮，切成长段，粗根纵剖为2～4瓣，晒干。\n" +
                     "\n" +
                     "【药材性状】不规则圆柱形、半圆柱形块状或块片状，常弯曲如结节状，长3～10厘米，直径1～6厘米。去栓皮的药材表面淡灰黄色，可见残留的黑褐色栓皮，弯曲处有深陷的横沟。体重，质坚实，断面平坦，灰白色至黄白色，富粉性，有排列稀疏的放射状纹理，纵剖面浅灰白色，维管束浅棕色，呈弯曲筋脉状纹理。气微、味苦。\n" +
                     "\n" +
                     "【性味归经】性寒，味苦。归膀胱经、肺经。\n" +
                     "\n" +
                     "【功效与作用】利水消肿、祛风止痛。属祛风湿药下分类的祛风湿清热药。\n" +
                     "\n" +
                     "【临床应用】用量4.5～9克，煎汤内服，或入丸、散。用治水肿、小便不利、风湿痹痛、下肢湿热、疥癣疮肿等。\n" +
                     "\n" +
                     "【药理研究】有镇痛、抗炎、抗过敏、松弛横纹肌等药理作用，对心血管系统有降压、抗心肌正性肌力、抗心律失常、抗心肌缺血、抗心室肥厚等作用。\n" +
                     "\n" +
                     "【化学成分】含多种生物碱，其中主要为粉防己碱、去甲基粉防己碱、较环藤酚碱、氧化防己碱、防己诺林碱等。可应用比色法及薄层色谱法进行总生物碱及粉防己碱、去甲基粉防己碱的含量测定。\n" +
                     "\n" +
                     "【使用禁忌】脾胃虚弱及阴虚无湿热者禁服。\n" +
                     "\n" +
                     "【配伍药方】①治遗尿，小便涩：防己、葵子、防风各30克。上三味。以水五升，煮取二升半，分三股，散服亦佳。(《千金要方》)\n" +
                     "\n" +
                     "②治膀胱水蓄胀满，几成水肿：防己6克，车前子、韭菜子、泽泻各9克。水煎服。(《本草切要》)\n" +
                     "\n" +
                     "③治脚气肿痛：防己、木瓜、牛膝各9克，桂枝1.5克，枳壳3克。水煎服。(《本草切要》)\n" +
                     "\n" +
                     "④治水膨胀：防己30克、生姜15克。同炒，随入水煎服。半饥时饮之。(《本草汇言》)\n" +
                     "\n" +
                     "⑤治鼻衄：防己(生用)90克，捣罗为细散。每服6克，新汲水调下。老人小儿酒调3克服。更用热汤调少许、鼻中喘气，佳。(《圣济总录》)");

             chineseMedicineDao.insert(medicine73);

             ChineseMedicine medicine74=new ChineseMedicine();
             medicine74.setName("防风");
             medicine74.setSortType("F");
             medicine74.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=3cbb34d8a8ec8a1300175fb2966afaea/9c16fdfaaf51f3dedf18a95395eef01f3a29795a.jpg");
             medicine74.setData("【中药名】防风 fangfeng\n" +
                     "\n" +
                     "【别名】铜芸、茴芸、屏风、百韭。\n" +
                     "\n" +
                     "【英文名】Saposhnikoviae Radix。\n" +
                     "\n" +
                     "【来源】伞形科植物防风Saposhnikovia divaricata (Turcz.) Schischk.的根。\n" +
                     "\n" +
                     "【植物形态】多年生草本。根粗壮，有分枝，根茎处密被纤维状叶残基。茎单生，两歧分枝，有细棱。基生叶有长叶柄，基部鞘状，稍抱茎。叶片卵形或长圆形，2～3回羽状分裂，第一次裂片卵形，有小叶柄，第二次裂片在顶部的无柄，在下部的有短柄，又分裂成狭窄的裂片，顶端锐尖，茎生叶较小，有较宽的叶鞘。复伞形花序多数，顶生，形成聚伞状圆锥花序，无总苞片，花瓣5，白色，先端钝截，子房下位，2室。双悬果卵形。\n" +
                     "\n" +
                     "【产地分布】生于草原、丘陵、多石砾的山坡上。分布于黑龙江、吉林、辽宁、内蒙古等地。\n" +
                     "\n" +
                     "【采收加工】春季或秋季采挖未抽花茎植株的根，栽培者种植2～3年后采挖，除去须根及泥沙，晒干。\n" +
                     "\n" +
                     "【药材性状】长圆锥形或长圆柱形，稍弯曲，长20～30厘米，直径0.5～2厘米。根头部表面粗糙，常有剥落的栓皮，有明显密集的环节，节上有黑棕色毛状的残存叶基，长至5厘米。根表面灰棕色或棕色，皱缩而粗糙，有纵皱纹及致密的细横纹，并可见多数横长的皮孔及细根痕。体轻，质松，断面皮部棕黄色，疏松，裂隙较多，散生黄棕色油点，木部浅黄色，占根的绝大部分。气特异，味微甘。以条粗壮断面韧皮部色浅棕、木质部色浅黄者为佳。\n" +
                     "\n" +
                     "【性味归经】性温，味甘、辛。归膀胱经、肝经、脾经\n" +
                     "\n" +
                     "【功效与作用】发表、祛风、除湿。属解表药下分类辛温解表药。\n" +
                     "\n" +
                     "【临床应用】用量4.5～9克，煎汤，或入丸、 散，外用适量，煎水熏洗。内服治疗感冒、头痛、发热、无汗、风湿痹痛、四肢拘挛、皮肤风湿瘙痒、破伤风等症。\n" +
                     "\n" +
                     "【药理研究】解热、降温；镇痛、镇静和抗惊厥；抗菌；抗炎；增强免疫系统功能；抑制离体十二指肠、离体气管和回肠平滑肌收缩；抗组胺；抗凝。防风煎剂或醇浸剂，给兔灌胃或给大鼠腹腔注射，有解热作用；给小鼠灌胃或腹腔及皮下注射防风煎剂及醇浸剂，有镇痛作用；肌注正丁醇提取物，可降低大鼠血液黏度；防风煎剂、醇浸剂灌服或腹腔给药，对大鼠、小鼠具抗炎免疫作用。防风煎剂具抗菌、抗病毒作用。\n" +
                     "\n" +
                     "【化学成分】含挥发油，油中有20种化合物，含量较高的有辛醛、β-甜没药烯、升麻素苷、β-荜澄茄烯、防风色酮醇、亥茅酚、升麻素、甲基丁香酚、β-蒎烯、石防风素、防风酸性多糖、镰叶芹二醇、香草酸，人参炔醇 (相对含量为82.6%)，此外含有色原酮、香豆素、聚乙炔类、多糖类等成分。\n" +
                     "\n" +
                     "【使用禁忌】体虚风动发痉者慎服，肝阳上亢头痛眩晕者禁服。\n" +
                     "\n" +
                     "【配伍药方】①治自汗：防风、黄芪各30克，白术60克。每服9克，水一钟半，姜三片煎服。(《丹溪心法》玉屏风散)\n" +
                     "\n" +
                     "②偏正头风，痛不可忍者：防风、白芷各120克。上为细末，炼蜜为丸，如弹子大，空心服。未愈连进三服。(《普济方》)\n" +
                     "\n" +
                     "③眼暴赤暴肿：防风、羌活、黄芩、黄连各30克。水煎，食后温服。(《活法机要》散热饮子)\n" +
                     "\n" +
                     "④治泄痢飨泻，身热，脉弦，腹痛而渴及头痛微汗：防风、芍药、黄芩各30克。每服15克或30克，水三盏，煎至一盏，温酒服。(《保命集》防风芍药汤)\n" +
                     "\n" +
                     "⑤治老人大肠秘涩：防风、枳壳(麸炒)各30克，甘草15克。为末，每食前白汤服6克。(《简便单方》)");

             chineseMedicineDao.insert(medicine74);

             ChineseMedicine medicine75=new ChineseMedicine();
             medicine75.setName("飞龙掌血");
             medicine75.setSortType("F");
             medicine75.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=82018cd4d9f9d72a0369184fb5434351/30adcbef76094b3634e2f306a3cc7cd98c109ded.jpg");
             medicine75.setData("【中药名】飞龙掌血 feilongzhangxue\n" +
                     "\n" +
                     "【别名】飞龙斩血、见血飞、飞见血、三百棒。\n" +
                     "\n" +
                     "【英文名】Radix Toddaliae Asiaticae。\n" +
                     "\n" +
                     "【来源】芸香科植物飞龙掌血Toddalia asiatica (L.) Lam.的根。\n" +
                     "\n" +
                     "【植物形态】木质藤本。枝及分枝常有下弯的皮刺，小枝被锈色短柔毛，并有白色皮孔。三出复叶互生，具柄；小叶无柄，倒卵形，椭圆形或倒卵状披针形，边缘细锯齿，齿间及叶片均有透明腺点。花单性，白色、青色或黄色；雄花常组成腋生伞房状圆锥花序；雌花常组成聚伞状圆锥花序；萼片、花瓣、雄蕊4～5，子房5室。核果近球形，熟时橙红色或朱红色，具深色腺点。花期10～12月，果期12月至翌年2月。\n" +
                     "\n" +
                     "【产地分布】生于丛林中。分布于湖南、广西、陕西、四川、贵州等地。\n" +
                     "\n" +
                     "【采收加工】全年可采挖，洗净晒干。\n" +
                     "\n" +
                     "【药材性状】圆柱形，略弯曲，直径0.8～3厘米，有分枝。表面黄色或土黄色，有细纵纹及多数疣状突起，突起处栓皮多脱落，露出鲜黄色或红黄色皮部。质硬，不易折断，断面平坦，皮部灰棕色，颗粒性，木质部红黄色，具密集细孔。气微，味辛、微苦。\n" +
                     "\n" +
                     "【性味归经】性温，味辛、苦。归脾经、胃经。\n" +
                     "\n" +
                     "【功效与作用】祛风止痛、散瘀止血。属止血药下属分类的化瘀止血药。\n" +
                     "\n" +
                     "【临床应用】用量6～15克，煎服。用治风湿痹痛、胃痛、跌打损伤、吐血、衄血、刀伤出血、痛经、经闭、阿米巴痢疾、牙痛、疟疾。外用适量，捣烂或研末敷患处。\n" +
                     "\n" +
                     "【药理研究】抗炎，镇痛，抗病毒，抑菌，利尿，解痉。实验表明，本品生物总碱具有抗炎作用，能抑制二甲苯所致小鼠耳肿胀，抑制琼脂所致小鼠足肿胀，抑制羧甲基纤维素钠所致白细胞游走;同时具有镇痛作用，能减少冰醋酸所致小鼠扭体反应的次数。\n" +
                     "\n" +
                     "【化学成分】含白屈菜红碱、二氢白屈菜红碱、飞龙掌血默碱、小檗碱、飞龙掌血内酯酮、香叶木苷等成分。\n" +
                     "\n" +
                     "【使用禁忌】孕妇忌用。\n" +
                     "\n" +
                     "【相关药方】①治风湿性关节炎：飞龙掌血、薛荔、鸡血藤、菝葜各18克，威灵仙9克。浸白酒500ml。每服30～60ml，每日3次。(《全国中草药汇编》)\n" +
                     "\n" +
                     "②治风湿肿痛，外伤疼痛，肋间神经痛：飞龙掌血干根皮12～18在。水煎服，亦可浸酒服。(广州部队《常用中草药手册》)\n" +
                     "\n" +
                     "③治跌打损伤：见血飞9克，月月红根6克，牛膝9克。共研末，用酒为引。(《贵阳民间药草》)\n" +
                     "\n" +
                     "④治血滞经闭：见血飞60克，大血藤60克，川牛膝60克，红花15克，泡酒。每服5～15克。(《四川中药志》1979年)\n" +
                     "\n" +
                     "⑤治劳伤吐血，瘀滞崩漏：见血飞30克。水煎，加童便服。(《四川中药志》1979年)");

             chineseMedicineDao.insert(medicine75);

             ChineseMedicine medicine76=new ChineseMedicine();
             medicine76.setName("凤眼果");
             medicine76.setSortType("F");
             medicine76.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=18e1643e7e094b36cf9f13bfc2a517bc/d1160924ab18972bfd4b9a2beecd7b899f510ad9.jpg");
             medicine76.setData("【中药名】凤眼果 fengyanguo\n" +
                     "\n" +
                     "【别名】九层皮，七姐果，富贵子，红皮果，罗晃子。\n" +
                     "\n" +
                     "【英文名】Nobilis Sterculia Seed。\n" +
                     "\n" +
                     "【药用部位】为梧桐科植物苹婆Sterculia nobilis Smith的种子\n" +
                     "\n" +
                     "【植物形态】乔木。树皮黑褐色，小枝幼时略被星状毛。叶互生，叶片薄革质，长圆形或椭圆形，长8～25厘米，宽5～15厘米，先端急尖或钝，基部圆或钝。圆锥花序顶生或腋生，披散，有短柔毛；花单性，无花冠，花萼淡红色，钟状，外面被短毛柔毛，5列，裂片条状披针形，先端渐尖且向内曲，在先端互相黏合，与钟状萼筒等长，雄花较多，雌雄蕊柄弯曲，雌花较少，略大，子房圆球形，有5条沟纹，密被毛，柱头5浅裂。蓇葖果鲜红色，厚革质，长圆状卵形，先端有喙，种子1～4颗。种子椭圆形或长圆形，黑褐色。\n" +
                     "\n" +
                     "【产地分布】野生山坡林内或灌丛中，亦有栽培。产广东、广西的南部、福建东南部、云南南部和台湾。\n" +
                     "\n" +
                     "【采收加工】果实成熟时采收，剥取种子晒干备用。\n" +
                     "\n" +
                     "【药材性状】种子椭圆球形，黑褐色或暗栗色，直径约1.5厘米。气微，味淡。以种子个大，色均匀者为佳。\n" +
                     "\n" +
                     "【性味归经】性平，味甘。归胃经、大肠经、小肠经。\n" +
                     "\n" +
                     "【功效与作用】和胃消食，解毒杀虫。属消食药。\n" +
                     "\n" +
                     "【临床应用】煎汤，6～8枚；或研末为散。外用：适量，煅存性研末调搽。主治反胃吐食，虫积腹痛，疝痛，小儿烂头疮。\n" +
                     "\n" +
                     "【药理研究】有增强肠道功能。\n" +
                     "\n" +
                     "【化学成分】富含维生素A。\n" +
                     "\n" +
                     "【使用禁忌】脾虚便泄者禁服。\n" +
                     "\n" +
                     "【相关药方】①治疝痛：罗晃子七个。酒煎服。(选方出姚可成《食物本草》)\n" +
                     "\n" +
                     "②治翻胃吐食，食下即出；或朝食暮吐，暮食朝吐：凤眼果七枚。煅存性，每日酒调下方寸匕，服完为度。");

             chineseMedicineDao.insert(medicine76);

             ChineseMedicine medicine77=new ChineseMedicine();
             medicine77.setName("伏龙肝");
             medicine77.setSortType("F");
             medicine77.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545115394&di=48ea04f7a3285226f0e9304d5df1ddd8&imgtype=jpg&er=1&src=http%3A%2F%2Fi.zyccst.com%2Fupload%2Fupay%2F788590%2F2%2F5442314A766B784A565858585861745846585858585858585858585F2121302D6974656D5F7069632E6A7067.jpg");
             medicine77.setData("【中药名】伏龙肝 fulonggan\n" +
                     "\n" +
                     "【别名】灶心土、灶中黄土、灶中土。\n" +
                     "\n" +
                     "【英文名】Terra Flavausta。\n" +
                     "\n" +
                     "【来源】经多年用柴草熏烧而成的灶心土。\n" +
                     "\n" +
                     "【分布】全国各地均产。\n" +
                     "\n" +
                     "【采收加工】全年均可采收。在拆修柴锅灶时，将灶心烧结成的月牙形土块凿下，除去四周焦黑部分及杂质，取中心红黄色者入药。用煤火烧者不可供药用。\n" +
                     "\n" +
                     "【药材性状】不规则的块状，大小不一。全体橙黄色或红褐色，表面具刀削痕。体轻，质较硬，用指甲可刻划成痕，断面细软，色调深，显颗粒状，具蜂窝状的孔隙。具烟熏气，味淡。有吸湿性。\n" +
                     "\n" +
                     "【性味归经】性温，味辛。归脾经、胃经。\n" +
                     "\n" +
                     "【功效与作用】收敛止血、温中止呕、止泻。属止血药下属分类的收敛止血药。\n" +
                     "\n" +
                     "【临床应用】用量15～30克，煎服，或60～120克，布包煎汤，澄清代水用，或人散剂；外用适量，研末调敷。用治虚寒失血、呕吐、泻泄。\n" +
                     "\n" +
                     "【主要成分】主含硅酸、氧化铝及三氧化二铁，还含有氧化钠、氧化钾、氧化钙、氧化镁、磷酸钙等。本品具有止呕作用，对静注洋地黄酊所致家鸽呕吐可使呕吐次数减少，呕吐的潜伏期无改变；但对去水吗啡引起的犬呕吐无效。\n" +
                     "\n" +
                     "【使用禁忌】烧煤灶心土不得作为伏龙肝药用。阴虚失血、热症呕吐，反胃者忌服。");

             chineseMedicineDao.insert(medicine77);

             ChineseMedicine medicine78=new ChineseMedicine();

             medicine78.setName("佛甲草");
             medicine78.setSortType("F");
             medicine78.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=fe8fcc19384e251ff6faecaac6efa272/6f061d950a7b0208c44f51c26ad9f2d3572cc80b.jpg");
             medicine78.setData("【中药名】佛甲草 fojiacao\n" +
                     "\n" +
                     "【别名】指甲草、禾雀脷、打不死。\n" +
                     "\n" +
                     "【英文名】herba sedi linearig。\n" +
                     "\n" +
                     "【来源】景天科植物佛甲草Sedum lineare Thunb.的全草。\n" +
                     "\n" +
                     "【植物形态】多年生草本。全株肉质，高10~ 20厘米。茎直立或倾斜。叶常3叶轮生，少有对生或互生，线状披针形或条形，长20~ 25厘米，宽约2毫米，基部有短距，常带紫红色。聚散花序顶生，有2~3个分枝，花黄色，萼片5，狭披针形，花瓣5，宽披针形，雄蕊10，心皮5，成熟时略开叉，瞢葖果。花期4~5月。\n" +
                     "\n" +
                     "【产地分布】生于山坡石缝中或低山阴湿处。分布于江苏南部至广东、四川、云南、甘肃东南部等地。\n" +
                     "\n" +
                     "【采收加工】夏、秋季收割全草，洗净，在沸水中烫一下，捞起，晒干或随采随用。\n" +
                     "\n" +
                     "【药材性状】根细小。茎弯曲，长7~ 12厘米，直径约0.1厘米，表面淡褐色或棕褐色，有明显的节，偶有残留的不定根。叶轮生，无柄；叶片皱缩卷曲，多脱落，展平后呈条形或条状披针形，常1~2厘米，宽约0.1厘米，有的基部可见短距。聚散花序顶生，花小，浅棕色。果实为瞢荚果。气微，味淡。\n" +
                     "\n" +
                     "【性味归经】性寒，味甘。归心经、肺经、肝经、脾经。\n" +
                     "\n" +
                     "【功效与作用】清热解毒。属清热药下属分类的清热解毒药。\n" +
                     "\n" +
                     "【临床应用】用量10~15克，鲜用15~ 30克，煎汤内服；外用捣敷或捣汁含漱、滴眼。用治热毒所致的咽喉肿痛、痈肿、疔疮、丹毒等阳证肿毒及汤烫火伤、毒蛇咬伤。\n" +
                     "\n" +
                     "【主要成分】含金圣草素、红车轴草素、香豌豆苷、香豌豆-3'； -甲醚、&beta；-谷甾醇、三十烷、景天庚糖、葡萄糖、果糖。10%煎液在体外对金黄色葡萄球菌有抑制作用。\n" +
                     "\n" +
                     "【使用禁忌】尚不明确。\n" +
                     "\n");

             chineseMedicineDao.insert(medicine78);

             ChineseMedicine medicine79=new ChineseMedicine();
             medicine79.setName("扶芳藤");
             medicine79.setSortType("F");
             medicine79.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545115652&di=d20255538832df999a0938f12b5679f5&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.wotianmiaomu.com%2FupLoad%2Fproduct%2Fmonth_1702%2F20170210102734306.jpg");
             medicine79.setData("【中药名】扶芳藤 fufangteng\n" +
                     "\n" +
                     "【别名】爬墙虎、千斤藤。\n" +
                     "\n" +
                     "【英文名】Herba Euonymi。\n" +
                     "\n" +
                     "【来源】卫矛科植物爬行卫矛Euonymus fortunei (Turcz.) Hand.-Mazz.的带叶藤茎。\n" +
                     "\n" +
                     "【植物形态】常绿或半常绿灌木，匍匐或攀援，高约1.5米。枝上通常生长细根并具小瘤状突起。叶对生，广椭圆形或椭圆状卵形至长椭圆状倒卵形，先端尖或短锐尖，基部阔楔形，边缘具细锯齿，质厚或稍带革质，上面叶脉稍突起，下面叶脉甚明显；叶柄短。聚伞花序腋生；萼片4；花瓣4，绿白色，近圆形；雄蕊4，着生于花盘边缘；子房上位，与花盘连生。蒴果球形。种子外被橘红色假种皮。花期6～7月，果期9～10月。\n" +
                     "\n" +
                     "【产地分布】生于林缘、村边的树上或墙壁上或匍匐于石上。分布我国华北、华东、中南、西南、华南各地。\n" +
                     "\n" +
                     "【采收加工】全年可采，洗净鲜用或晒干备用。\n" +
                     "\n" +
                     "【药材性状】茎呈圆柱形，常有不定根，具纵皱纹，略弯曲，直径0.3~1厘米，灰褐色至棕褐色，有突起皮孔；质坚硬，不易折断，断面不整齐。单叶对生，叶片薄革质，灰绿色或黄绿色，完整叶片椭圆形或宽椭圆形，长2～10厘米，宽1～6厘米，边缘有细锯齿，叶脉两面隆起，侧脉每边5～6条。聚伞花序；花4数。蒴果近球形。气微，味淡。\n" +
                     "\n" +
                     "【性味归经】性微温，味微苦。归肝经、脾经、肾经。\n" +
                     "\n" +
                     "【功效与作用】益气血、补肝肾、舒筋活络。属祛风湿药下属分类的祛风湿强筋骨药。\n" +
                     "\n" +
                     "【临床应用】用量15～30克，煎服或浸酒服；外用适量，煎洗或捣敷。用治气血虚弱、腰肌劳损、风湿痹痛、跌打骨折、创伤出血。\n" +
                     "\n" +
                     "【主要成分】本品含卫矛醇；种子含前番茄红素和前-&gamma；-胡萝卜素。药理实验表明，对金黄色葡萄球菌、肺炎球菌、伤寒杆菌均有抑制作用；还具有明显的镇静、镇痛作用。\n" +
                     "\n" +
                     "【使用禁忌】孕妇忌服。");

             chineseMedicineDao.insert(medicine79);

             ChineseMedicine medicine80=new ChineseMedicine();
             medicine80.setName("凤尾草");
             medicine80.setSortType("F");
             medicine80.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=b6481a900833874488c8272e3066b29c/a9d3fd1f4134970a702254ef91cad1c8a6865d98.jpg");
             medicine80.setData("【中药名】凤尾草 fengweicao\n" +
                     "\n" +
                     "【别名】凤尾蕨、井口边草、山鸡尾、山凤尾、小凤尾。\n" +
                     "\n" +
                     "【英文名】Herba Pteridis Multifidae\n" +
                     "\n" +
                     "【来源】凤尾蕨科植物凤尾蕨Pteris multifida Poir.的全草。\n" +
                     "\n" +
                     "【植物形态】多年生草本。地下茎粗壮，密被线形的黑褐色鳞片。叶丛生，叶柄灰棕色或禾秆色，无毛；孢子叶2回羽状分裂，上面绿色，下面淡绿色，中轴具宽翅，羽片3~7对，对生或近对生，上部羽片无柄，不分裂，先端渐尖，长线形，全缘，顶端的羽片最长，下部羽片有柄，羽状分裂或基部具1～2裂片，羽状分裂者具小羽片数枚，长线形，小羽片在叶轴上亦下延成翅，叶脉明显，细脉由中脉羽状分出，单一或二叉分枝，直达边缘；营养叶叶片较小，2回小羽片较宽，线形或卵圆形，边缘均有锯齿。孢子囊群线形，沿孢子叶羽片下面边缘着生，孢子囊群盖稍超出叶缘，膜质。\n" +
                     "\n" +
                     "【产地分布】生于半阴湿的岩石及墙角石隙中。分布于云南、四川、广东、广西、江西等地。\n" +
                     "\n" +
                     "【采收加工】夏、秋季采全草，洗净，晒干。\n" +
                     "\n" +
                     "【药材性状】为数十株扎成的小捆，全株长15~40厘米。根状茎短，棕褐色，下面丛生须根，上面簇生数十片叶。叶柄细长，三棱形，棕黄色，光滑，长10~30厘米，直径约1毫米，易折断；叶革质，无毛，灰绿色。孢子叶长条形，宽3~4厘米，边缘无锯齿，孢子囊群着生于叶缘；营养叶较宽，4~6毫米，边缘有不整齐的锯齿。气微，味微苦。\n" +
                     "\n" +
                     "【性味归经】性寒，味微苦。归大肠经、心经、肝经。\n" +
                     "\n" +
                     "【功效与作用】清热利湿、凉血止血、消肿解毒。属清热药下属分类的清热解毒药。\n" +
                     "\n" +
                     "【临床应用】用量15~60克，煎服；外用适量，捣敷或煎水洗。用治痢疾、肠炎、黄疸型肝炎、咳血、衄血、便血、尿血、白带、淋浊、崩漏、痈肿疮毒。临床上用煎剂治疗急性菌痢、传染性肝炎、伤寒和胆道疾患均有一定疗效。\n" +
                     "\n" +
                     "【主要成分】含黄酮类、甾醇、内酯、酯类和酚性成分。\n" +
                     "\n" +
                     "【使用禁忌】虚寒证忌服。");

             chineseMedicineDao.insert(medicine80);

             ChineseMedicine medicine81=new ChineseMedicine();

             medicine81.setName("扶桑花");
             medicine81.setSortType("F");
             medicine81.setMedicineImageUrl("http://pic30.nipic.com/20130622/9167307_113845357139_2.jpg");
             medicine81.setData("【中药名】扶桑花 fusanghua\n" +
                     "\n" +
                     "【别名】大红花、朱槿、吊丝红花、状元红。\n" +
                     "\n" +
                     "【英文名】Flowr Hibisci Rosae-Sinensis。\n" +
                     "\n" +
                     "【来源】锦葵科植物朱槿Hibiscus rosa-sinensis L.的花。\n" +
                     "\n" +
                     "【植物形态】灌木或小乔木，高1~6米；小枝圆柱形，被星状柔毛。叶互生，宽卵形或狭卵形，先端突尖或渐尖，边缘有粗齿或缺刻，或除近先端外几乎全缘，秃净，或于背脉有少许疏毛。花大型，单生于上部叶腋，常下垂；小苞片6~7；花萼钟状，5裂，裂片卵形至披针形；花冠漏斗状，花瓣5，倒卵形，玫瑰红色、淡红色或淡黄色，有时重瓣；雄蕊多数，花丝结合成圆筒；子房上位，5室，花柱5裂，柱头头状，雄蕊筒与柱头甚长，超出花冠外。蒴果卵形，有喙，5瓣裂。花期全年。\n" +
                     "\n" +
                     "【产地分布】多为栽培。分布于广东、广西、福建及西南各地。\n" +
                     "\n" +
                     "【采收加工】全年可采，花初开时采摘，随开随采，摊薄晒干。\n" +
                     "\n" +
                     "【药材性状】长条形，长5.5~7厘米。小苞片6~7，线形。花萼长约2.5厘米，有星状毛，5裂。花瓣5，有重瓣，顶端圆或具粗圆齿。雄蕊柱长，突出于花冠外。子房5棱形，被毛，花柱5。气清香，味微苦。\n" +
                     "\n" +
                     "【性味归经】性寒，味甘。归心经、肺经、肝经、脾经。\n" +
                     "\n" +
                     "【功效与作用】清肺、化痰、凉血、解毒。属清热药下属分类的清热解毒药。\n" +
                     "\n" +
                     "【临床应用】用量15~30克，煎服。用治痰火咳嗽、鼻衄、痢疾、赤白浊。外用适量，捣烂敷患处，用治痈肿、毒疮、汗斑。\n" +
                     "\n" +
                     "【主要成分】含矢车菊素一二糖苷、矢车菊素槐糖葡萄糖苷和槲皮素二葡萄糖苷。动物实验表明，其提取物有降压作用，对平滑肌有致痉作用。\n" +
                     "\n" +
                     "【使用禁忌】尚不明确。");


             chineseMedicineDao.insert(medicine81);

             ChineseMedicine medicine82=new ChineseMedicine();
             medicine82.setName("蜈蚣");
             medicine82.setSortType("W");
             medicine82.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C28%2C650%2C430%3Bc0%3Dbaike80%2C5%2C5%2C80%2C26/sign=2f9ae39e3287e9505658a92c2d087f71/2fdda3cc7cd98d109873e06c293fb80e7aec90cc.jpg");
             medicine82.setData("【中药名】蜈蚣\n" +
                     "\n" +
                     "【别名】天龙、百脚、百足虫、天虫、吴公、千足虫。\n" +
                     "\n" +
                     "【英文名】Scolopendra。\n" +
                     "\n" +
                     "【药用部位】蜈蚣科动物少棘巨蜈蚣Scolopendra subspinipes multilans L.Koch.的全体。\n" +
                     "\n" +
                     "【动物形态】体扁平而长，全体由22个同形环节构成。头部红褐色；头板近圆形，前端较窄而突出。头板和第1背板为金黄色，生触角l对，单眼4对；头部之腹面有颚肢1对，上有毒钩；颚肢底节内侧有1矩形突起，上具4枚小齿，颚肢齿板前端具小齿5枚。身体自第2背板起为墨绿色，末板黄褐色。背板自第2～19节各有2条不显著的纵沟；腹板及步肢均为淡黄色，步肢21对，足端黑色，尖端爪状；末对附肢基部侧板端有2尖棘，同肢前腿节腹面外侧有2棘，内侧l棘，背面内侧l～3棘。\n" +
                     "\n" +
                     "【产地分布】栖居于潮湿阴暗处，食肉性。伞国各地均有分布。\n" +
                     "\n" +
                     "【采收加工】春、夏季捕捉，捕得后，用两端削尖的竹片插入头尾两端，绷直、晒干；或先用沸水烫过，然后晒干或烘干。\n" +
                     "\n" +
                     "【药材性状】扁平长条形，由22个体节组成。头部暗红或红褐色，略有光泽，有头板覆盖，头板近圆形，前端稍突出，有触角及颚肢各1对。躯干部第1背板与头同色，其余20个背板为棕绿或墨绿色，具光泽，自第4～22背板上常有2条纵沟线，腹部淡黄或棕黄色，皱缩；自第2节起，每节两侧有步足1对，步足黄色或红褐色，偶有黄白色，呈弯钩状，最末1对步足尾状，易脱落。质脆，断面有裂隙。气微腥，有特殊刺鼻臭气，味辛、微咸。\n" +
                     "\n" +
                     "【性味归经】性温，味辛。归肝经。\n" +
                     "\n" +
                     "【功效与作用】息风镇痉、攻毒散结、通络止痛。属平肝息风药下属分类的息风止痉药。\n" +
                     "\n" +
                     "【临床应用】用量3～5克，煎服或入丸、散；外用适量，研末调敷。用治小儿惊风、抽搐痉挛、中风口歪、半身不遂、破伤风、风湿顽痹、疮疡、瘰疬、毒蛇咬伤。\n" +
                     "\n" +
                     "【药理研究】蜈蚣提取物对戊四氮、纯烟碱及硝酸士的宁碱引起的惊厥均有不同程度的对抗作用;对多种皮肤真菌有不同程度的抑制作用，对结核杆菌有抑制和杀灭的功能;还具有抗肿瘤、抗炎、镇痛、抗衰老和增加心肌收缩力的功能。\n" +
                     "\n" +
                     "【化学成分】蜈蚣含有2种类似蜂毒的成分，即组织胺样物质及溶血蛋白质，还含有胆甾醇、脂肪酸、蛋白质和多种氨基酸。\n" +
                     "\n" +
                     "【使用禁忌】蜈蚣有毒，用量不宜过大。血虚生风及孕妇禁用。\n" +
                     "\n" +
                     "【配伍药方】①治破伤风：蜈蚣头、乌头尖、附子底、蝎梢各等分。为细末。每用一字，或半字，热酒调下。如禁了牙关，用此药，斡开灌之。(《濡门事亲》蜈蚣散)\n" +
                     "\n" +
                     "②治瘰疬溃疮：茶、蜈蚣，二味炙至香熟，捣筛为末。先以甘草汤洗净，敷之。(《神枕方》)\n" +
                     "\n" +
                     "③治一切便毒，连连作痛，更不肿起，名曰阴毒：活蜈蚣二条，炭火烧存性，为末。好酒调服。食前下。(《直指方》秘传独圣散)\n" +
                     "\n" +
                     "④治蛇咬：白芷30克(取白色者)，雄黄15克，蜈蚣三条，樟脑9克。各为极细末。以香油调搽肿处，随干随扫。(《洞天奥旨》蜈蚣散)\n" +
                     "\n" +
                     "⑤治趾疮，甲内恶肉突出不愈：蜈蚣一条。焙研敷之。外以南星末醋和敷四围。(《医方摘要》)");


             chineseMedicineDao.insert(medicine82);

             ChineseMedicine medicine83=new ChineseMedicine();
             medicine83.setName("乌梢蛇");
             medicine83.setSortType("W");
             medicine83.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=abffe7711a38534398c28f73f27adb1b/738b4710b912c8fc5d1be2d0f6039245d78821c6.jpg");
             medicine83.setData("【中药名】乌梢蛇 wushaoshe\n" +
                     "\n" +
                     "【别名】乌蛇、乌风蛇、黑乌梢、乌花蛇、剑脊蛇、黑风蛇、黄风蛇、剑脊乌梢蛇。\n" +
                     "\n" +
                     "【英文名】Zaocys。\n" +
                     "\n" +
                     "【药用部位】游蛇科动物乌梢蛇Zaocys dhumnades (Cantor)去内脏的全体。\n" +
                     "\n" +
                     "【动物形态】体长可达2米左右。头扁圆形，尾细长，眼大而不陷，有光泽。鼻孔大而椭圆，位于两鼻鳞间。吻鳞微露于头顶，鼻间鳞宽大于长，前额鳞宽大于长，外缘包于头侧，额鳞前宽后窄，略呈五角形，眼上鳞宽大。上唇鳞8枚，第4、5枚人眼；颊鳞1枚，与第2、3枚上唇鳞邻接；眼前鳞2～3枚，上缘包至头背。眶后鳞2枚，前额鳞2枚，后颞鳞2&mdash；3枚，下唇鳞10枚，前5枚与咽头鳞相接，第6枚最大。脊部高耸成屋脊状，俗称剑脊。背鳞14～18列，从颈后起背部中央有2～4行鳞片起棱显著，形成两条纵贯全体的黑线。腹鳞186～205枚，肛鳞2裂，尾下鳞101～128。尾部渐细，呈青灰色或黑褐色。\n" +
                     "\n" +
                     "【产地分布】生活在丘陵地带及田野间，主要以蛙类为食。无毒。分布于河南、陕西、甘肃、四川、安徽、江苏、浙江、江西、湖南、福建、台湾、广东及广西等地。\n" +
                     "\n" +
                     "【采收加工】春至秋捕捉，捉后处死，用刀从腹部剖开，除去内脏，卷成圆盘，置于铁丝架上，用柴火熏至焦黑，取下晒一两日，干透即成。\n" +
                     "\n" +
                     "【药材性状】圆盘状，盘径约16厘米。头盘于中央，口内为多数同形细齿，上下唇鳞片近无色，上唇鳞8片。颊鳞1片，眼较大，有光泽，有一较小的眼前下鳞。头背及体背部黑褐色或绿黑色，背脊高耸呈屋脊状，习称“剑脊”，背鳞大部平滑，仅中央2～4行起棱，鳞行为偶数排列。腹部剖开边缘向内反卷，内呈黄白色或淡棕色，可见众多排列整齐的肋骨。尾明显细长，尾下鳞双行，气腥，味淡。\n" +
                     "\n" +
                     "【性味归经】性平，味甘。归肝经。\n" +
                     "\n" +
                     "【功效与作用】祛风、通络、止痉。属祛风湿药下属分类的祛风湿强筋骨药。\n" +
                     "\n" +
                     "【临床应用】用量9～12克，焙干研粉内服或入丸散；外用适量，烧灰调敷。用治风湿顽痹、麻木拘挛、中风口眼歪斜、半身不遂、抽搐痉挛、破伤风、麻风疥癣、瘰疬恶疮。\n" +
                     "\n" +
                     "【药理研究】乌梢蛇有抗炎、镇痛、镇静及调节免疫等作用。乌梢蛇血清有抗蛇毒作用。\n" +
                     "\n" +
                     "【化学成分】含17种氨基酸、脂肪酸和多种微量元素，鲜肉含蛇肌果糖、蛇肌醛缩酶等。实验表明，乌梢蛇水煎和醇提取物具有抗炎、镇痛、抗惊厥等作用。\n" +
                     "\n" +
                     "【使用禁忌】血虚生风者慎服。\n" +
                     "\n" +
                     "【配伍药方】①治面上疮：乌蛇30克，烧灰，细研如粉，以腊月猪脂调涂之。(<圣惠方》)\n" +
                     "\n" +
                     "②治身体顽麻风：乌蛇30克(酒浸，去皮骨，炙令微黄)，防风15克(去芦头)，细辛15克，白花蛇30克(酒浸，去皮骨，炙令微黄)，天麻15克，独活S克，肉桂15克(去皱皮)，枳壳15克(麸炒微黄去瓤)，苦参15克(锉)。上药，捣罗为末，炼蜜和捣二、三百杵。丸如梧桐子大，每服食前以温酒下二十丸。(《圣惠方》乌蛇丸)");

             chineseMedicineDao.insert(medicine83);

             ChineseMedicine medicine84=new ChineseMedicine();
             medicine84.setName("瓦楞子");
             medicine84.setSortType("W");
             medicine84.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=d8d5aa3f4710b912abccfeaca2949766/c995d143ad4bd11366345ab65aafa40f4afb05bc.jpg");
             medicine84.setData("【中药名】瓦楞子 walengzi\n" +
                     "\n" +
                     "【别名】蚶壳、瓦垄子、魁蛤壳、瓦屋子、蚶子壳、瓦垄蛤皮、血蛤皮。\n" +
                     "\n" +
                     "【英文名】Arcae Concha\n" +
                     "\n" +
                     "【药用部位】蚶科动物毛蚶Arca subcrenata Lischke、泥蚶Arca gran.osa Linnaeus或魁蚶Arca injlata Reeve等同属数种的贝壳。\n" +
                     "\n" +
                     "【动物形态】贝壳大，斜卵圆形，极膨胀，左右两壳稍不等，壳顶膨胀，稍接近，壳前端钝圆，向后渐变狭，背后方斜下，壳上具放射肋纹42~48条(常43条)，光滑而整齐，肋纹与肋间沟宽度相等，壳表面被有棕色外皮及毛，极易脱落。壳内面白色，近顶部处略带灰色，边缘厚，具有与壳表放射肋及沟相当而突出的长形齿，无放射肋纹，但有极细弱的放射条纹。铰合部直，铰合齿约70枚，自两端向中央渐细密，外套痕与闭壳肌痕均明显，前闭壳肌痕较小，近似圆形，后者略大，似方形，外套膜边缘厚，有褶襞。\n" +
                     "\n" +
                     "【产地分布】生活于浅海数十米深的软泥或泥沙质海底中。分布于我国沿海各地，以渤海湾产量最大。\n" +
                     "\n" +
                     "【采收加工】春秋两季采收，于退潮时到浅海泥沙中拾捕，洗净泥沙，煮熟，肉供食用，将壳晒干即可。\n" +
                     "\n" +
                     "【药材性状】略呈三角形或扇形，长7～9厘米，高6～8厘米。壳外面隆起，有棕色绒毛或已脱落；壳顶突出，向内卷曲；自壳顶向腹面有延伸的放射肋42~48条。壳内面平滑，白色，壳缘有与壳外面直棱相对应的凹陷，铰合部具铰齿1列。质坚。气微，味淡。\n" +
                     "\n" +
                     "【性味归经】性平，味咸。归肺经、胃经、肝经。\n" +
                     "\n" +
                     "【功效与作用】消痰化瘀、软坚散结、制酸止痛。属化痰止咳平喘药下属分类的清化热痰药。\n" +
                     "\n" +
                     "【临床应用】用量9～15克，宜先煎；外用研末调敷。用治顽痰积结、黏稠难咯、瘿瘤、瘰疬、癥瘕痞块、胃痛泛酸。\n" +
                     "\n" +
                     "【药理研究】实验表明，复方瓦楞子冲剂抗消化溃疡面愈合作用优于甲氰咪胍，具有抑制幽门螺旋杆菌生长的作用，其抑菌能力亦优于甲氰咪胍。\n" +
                     "\n" +
                     "【化学成分】含大量碳酸钙、少量磷酸钙。尚含少量镁、铁、硅酸盐、硫酸盐、氯化物及有机质。\n" +
                     "\n" +
                     "【使用禁忌】无瘀血痰积者勿用。\n" +
                     "\n" +
                     "【配伍药方】①治胃痛吐酸水，噫气，甚则吐血者：瓦楞子(醋煅七次)270克，乌贼骨180克。广陈皮90克(炒)。研极细末，每日三次，每次服6克，食后开水送下。(《经验方》)\n" +
                     "\n" +
                     "②治急性胃炎：煅瓦楞子9克，良姜3克，香附6克，甘草6克。共研末。每服6克，日服2次。(《青岛中草药手册》)\n" +
                     "\n" +
                     "③治痰饮：瓦楞子壳，不拘多少，炭火煅，研末，候栝篓黄熟时，正捣和瓦粉作饼子，晒干为末。用蜜汤调3克，或入诸药为丸，其效过于海粉多矣。(《古今医统》)\n" +
                     "\n" +
                     "　　④治烧烫伤：煅瓦楞子研成细末，加冰片少许，用香油调匀，涂患处。(《山东药用动物》)\n" +
                     "\n" +
                     "⑤治皮肤刀伤及冻疮溃疡：瓦楞子30克，冰片15克。共研末外敷。(《青岛中草药手册》)\n" +
                     "\n" +
                     "⑥治外伤出血：煅瓦楞子研末外敷。(《青岛中草药手册》)");

             chineseMedicineDao.insert(medicine84);

             ChineseMedicine medicine85=new ChineseMedicine();
             medicine85.setName("五灵脂");
             medicine85.setSortType("W");
             medicine85.setMedicineImageUrl("http://img001.hc360.cn/hb/MTQ3MjAxOTU2NDc3MDI0NTA2NDA0Nw==.jpg");
             medicine85.setData("【中药名】五灵脂 wulingzhi\n" +
                     "\n" +
                     "【别名】药本、寒号虫粪、寒雀粪。\n" +
                     "\n" +
                     "【英文名】Faeces Trogopteri。\n" +
                     "\n" +
                     "【药用部位】鼯鼠科动物复齿鼯鼠Trogopterus xanthipes Milne-Edwards的粪便。\n" +
                     "\n" +
                     "【动物形态】形如松鼠，较松鼠略大。体长20~30厘米，体重250~400克。头宽，吻部较短，眼大而圆，耳壳明显，耳基部前后方生有黑色细长的簇毛。前后肢间有皮膜相连。尾呈扁平状，略短于体长，尾毛长而蓬松。全身被毛为灰褐色，毛基部黑灰色，上部黄色，尖端黑褐色。颜脸部较淡，为灰色，耳同身色。腹部毛色较浅。皮膜上下及背腹面色相同，仅侧缘呈鲜橙黄色。四足棕黄色，后肢长于前肢，均有钩爪。尾灰黄色，尾尖有黑褐色长毛。\n" +
                     "\n" +
                     "【产地分布】生活于长有松柏树的高山岩石陡壁的石洞或石缝中。分布于河北、山西、河南、陕西、甘肃等地。\n" +
                     "\n" +
                     "【采收加工】春秋两季到山野寻找鼯鼠住过的峭壁洞穴，收集或刮取粪便，拣去砂石、泥土等杂质，晒干。\n" +
                     "\n" +
                     "【药材性状】灵脂块：不规则块状，大小不一。表面黑棕色、黄棕色、紫棕色、灰棕色或红棕色，凹凸不平，略有油润光泽。体轻，质硬易碎裂，断面不平坦，黄棕色或棕褐色，可见长椭圆形粪粒，间或有黄棕色树脂样物质。气腥臭，带有柏树叶样气，味苦、辛。灵脂米：为长椭圆形粪粒，两端钝圆，长0.5～1.5厘米，直径0.3~0.6厘米。表面黑褐色，较平滑或微粗糙，常见淡黄色纤维残痕，有的略具光泽。体轻松，捻之易碎，断面黄色、黄绿色或棕褐色，略有纤维性。具柏树叶样气味，味微苦。\n" +
                     "\n" +
                     "【性味归经】性温，味咸、甘。归肝经。\n" +
                     "\n" +
                     "【功效与作用】活血止痛、化瘀止血、消积解毒。属活血化瘀药下属分类的活血止痛药。\n" +
                     "\n" +
                     "【临床应用】用量5~10克，煎服；或入丸、散。外用适量，研末撒或调敷。用治心腹血气诸痛、妇女经闭、产后瘀滞腹痛、崩漏下血、小儿疳积；外治蛇、蝎、蜈蚣咬伤。\n" +
                     "\n" +
                     "【药理研究】具有抗菌及抗炎作用，具有抑制血小板聚集和抗血栓作用，能改善心血管系统及微循环障碍，提高抗应激性损伤的能力及超氧化物歧化酶活力，减少胃溃疡的发生率，抑制白血病细胞的增殖作用，缓解平滑肌痉挛等作用。\n" +
                     "\n" +
                     "【化学成分】含多量的树脂、尿素、尿酸及维生素A类物质。动物实验表明，本品有抑制血小板聚集作用;增加冠脉血流量作用和改善微循环作用;并有抗应激性损伤、增强免疫功能、抑制胃酸分泌和抗氧化作用。\n" +
                     "\n" +
                     "【使用禁忌】血虚无瘀及孕妇慎敷。\n" +
                     "\n" +
                     "【配伍药方】①治急心痛，胃脘疼痛：五灵脂、玄胡索、蓬莪术、当归、良姜各等分。上为细末。每服淡醋汤调6克，食前服。(《杏苑生春》愈痛散)\n" +
                     "\n" +
                     "②治妇人经血不止：五灵脂末，炒令过熟，出尽烟气。每服6克，用当归两片，酒一中盏，与药末同煎至六分，去滓热服，连三五服。(《证类本草》引《经效方》)\n" +
                     "\n" +
                     "③治风冷气血闭，手足身体疼痛，冷麻：五灵脂60克，没药30克，乳香15克，川乌头(炮去皮)45克。同为末，滴水丸如弹子大。每用一丸。生姜温酒磨服。(《本草衍义》)\n" +
                     "\n" +
                     "④治毒蛇咬伤：五灵脂30克，雄黄15克。同为末，以酒调6克灌之，药滓敷咬处。(《本草衍义》)\n" +
                     "\n" +
                     "⑤治目生浮翳：五灵脂、海螵蛸各等分。为细末，熟猪肝蘸食。(《明目经验方》)\n" +
                     "\n" +
                     "⑥治重舌，喉痹：五灵脂30克。为细末，用米醋一大碗煎，旋噙漱口。(《经验良方》)\n" +
                     "\n" +
                     "⑦治积年口疮：五灵脂30克，杏仁(汤浸去皮、尖、双仁)49枚，黄丹(炒令紫色)15克。上件药，捣细罗为散，用生蜜调令得所，每取少许涂于疮上，有涎即吐之。(《圣惠方》)\n" +
                     "\n" +
                     "⑧治恶血牙痛：a.五灵脂，以米醋煎汁含咽。(《直指方》灵脂醋)b.五灵脂30克，川椒15克。共末，擦患处。(《疑难急症简方》)");
             chineseMedicineDao.insert(medicine85);

             ChineseMedicine medicine86=new ChineseMedicine();
             medicine86.setName("五倍子");
             medicine86.setSortType("W");
             medicine86.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=54d085b04b36acaf4ded9eae1db0e675/fcfaaf51f3deb48f136b6757f01f3a292df5783d.jpg");
             medicine86.setData("【中药名】五倍子 wubeizi\n" +
                     "\n" +
                     "　【别名】文蛤、百虫仓、木附子、旱倍子、乌盐泡、漆倍子、红叶桃。\n" +
                     "\n" +
                     "【英文名】Galla Chinensis。\n" +
                     "\n" +
                     "【药用部位】漆树科植物盐肤木Rhus chinensis Mill.、青麸杨Rhus potaninii Maxim.或红麸杨Rhus punjabensisStew.var.sinica(Diels) Rehd.et Wils.叶上的虫瘿，主要由五倍子蚜Mela phis chinensis(Bell)Baker寄生而形成。\n" +
                     "\n" +
                     "【植物形态】落叶小乔木或乔木，高2～10米。树皮灰黑色，小枝密被棕色柔毛。单数羽状复叶，互生，总叶柄基部膨大，叶轴与总叶柄有宽翅，被淡黄色棕色短柔毛；小叶5～13枚，无柄，卵形、卵状椭圆形至椭圆形或长卵形，长5～14厘米，宽2.5～5厘米，先端渐尖、短渐尖或急尖。基部圆形或楔形，边缘有粗锯齿，上面绿色，疏生短柔毛，下面灰绿色，密被淡褐色短柔毛；无小叶柄。圆锥花序顶生，长20～30厘米厘米，花小，兼有两性花和雄花；两性花的萼片5，绿黄色，长卵形，长约0.6毫米，先端钝，外侧及边缘被短柔毛；花瓣5，白色，倒卵状长椭圆形，长1.6毫米，先端圆形，边缘及内侧基部具柔毛；雄蕊5，着生于花盘边缘，较花瓣略短，花药黄色，椭圆形，“丁”字着生，花丝黄色；雌蕊较雄蕊短，子房上位，密生长柔毛，花柱3，柱头头状，黄色；雄花略小于两性花，花萼、花瓣与两性花相似，雄蕊5，形小，中央有退化子房。果序直立；核果扁果形，直径约3～4毫米，熟时橙红色至红色，被灰白色短柔毛，内含种子1枚，扁圆形，灰色。花期6～9月，果期9～11月。\n" +
                     "\n" +
                     "【产地分布】生于向阳多沙砾的山坡上、荒野或灌丛中。分布于除青海、新疆、黑龙江、辽宁以外的各省区。但能产结五倍子的只有四川、贵州、云南、湖南、湖北、河南、浙江。\n" +
                     "\n" +
                     "【采收加工】秋季采摘，置沸水中略煮或蒸至表面呈灰色，杀死蚜虫，取出，干燥。按外形不同，分为“肚倍”和“角倍”。\n" +
                     "\n" +
                     "【药材性状】肚倍：呈长圆形或纺锤形囊状，长2.5～9厘米，直径1.5～4厘米。表面灰褐色或灰棕色，微有柔毛。质硬面脆，易破碎，断面角质样，有光泽，壁厚0.2～0.3厘米，内壁平滑，有黑褐色死蚜虫及灰色粉状排泄物。气特异，味涩。角倍：呈菱形，具不规则的钝角状分枝，柔毛较明显，壁较薄。\n" +
                     "\n" +
                     "【性味归经】性寒，味酸、涩。归肺经、大肠经、肾经。\n" +
                     "\n" +
                     "【功效与作用】敛肺降火，涩肠止泻，敛肺，止血，收湿敛疮。属收涩药下属分类的敛肺涩肠药。\n" +
                     "\n" +
                     "【临床应用】内服：煎汤，3～10克；研末，1.5～6克，或入丸、散。外用：适量，煎汤熏洗；研末撒或调敷。用治肺虚久咳，肺热痰嗽，久泻久痢，自汗盗汗，消渴，便血痔血，外伤出血，痈肿疮毒，皮肤湿烂。\n" +
                     "\n" +
                     "【药理研究】具有收敛、抗菌、杀精子、抗肿瘤等作用。对蛋白质有沉淀作用，鞣酸能和很多重金属离子、生物碱及甙类形成不溶性的复合物，故可用作化学解毒剂。\n" +
                     "\n" +
                     "【化学成分】主要含五倍子鞣质。\n" +
                     "\n" +
                     "【使用禁忌】外感风寒、肺有实热之咳嗽以及积滞未清之泻痢忌服。\n" +
                     "\n" +
                     "【配伍药方】①治痔疮：艾叶、五倍子、白胶、苦楝根等分，锉碎，如烧香法，置长桶内，坐熏疮处。(《直指方》)\n" +
                     "\n" +
                     "②治手足皲裂：五倍子末，同牛骨髓填纳缝中。(《医方大成论》)\n" +
                     "\n" +
                     "③治滴虫性阴道炎：五倍子15克，水煎冲洗患部。(《四川中药志》1979)\n" +
                     "\n" +
                     "④治肺虚久咳：五倍子6克，五味子6克，罂粟壳6克。水煎服。(《四川中药志》1979年)\n" +
                     "\n" +
                     "⑤治泻痢不止：五倍子30克，半生半烧，为末，糊丸梧桐子大，每服三十丸，红痢烧酒下，白痢水酒下，水泄米汤下。(《纲目》)\n" +
                     "\n" +
                     "⑥治鼻出血：五倍子末吹之，仍以末同新绵灰等分，米饮服9克。(《纲目》)\n" +
                     "\n" +
                     "⑦牙缝出血不止：五倍子，烧存性，研末敷之。(《卫生易简方》)\n" +
                     "\n" +
                     "⑧治小便尿血：五倍子末，盐梅捣和丸，梧桐子大，每空心酒服五十丸。(《濒湖集简方》)");

             chineseMedicineDao.insert(medicine86);

             ChineseMedicine medicine87=new ChineseMedicine();
             medicine87.setName("五加皮");
             medicine87.setSortType("W");
             medicine87.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=147e9c1bdec451dae2fb04b9d7943903/0df3d7ca7bcb0a46fdb48f7c6863f6246a60afe4.jpg");
             medicine87.setData("【中药名】五加皮 wujiapi\n" +
                     "\n" +
                     "【别名】五加、南五加皮、五谷皮、红五加皮。\n" +
                     "\n" +
                     "【英文名】Acanthopanacis Cortex。\n" +
                     "\n" +
                     "【药用部位】五加科植物细柱五加Acanthopanax gracilistylus W.W.Smith的根皮。\n" +
                     "\n" +
                     "【植物形态】落叶灌木，高2～3米。枝无刺或于叶柄基部单生扁平的刺。掌状复叶在长枝上互生，在短枝上簇生，小叶通常5，中央一片最大，倒卵形或倒披针形，长3～8厘米，宽1～3.5厘米，边缘具钝细锯齿，两面无毛或沿脉疏生刚毛，下面脉腋有淡棕色毛。伞形花序腋生或单生于短枝上；花小，萼全缘或具5细齿；花瓣5，黄绿色；雄蕊5枚；子房下位，2～3室，花柱2～3，分离。核果浆果状，近球形，黑色。种子2，扁平，细小。花期4～7月，果期9～10月。\n" +
                     "\n" +
                     "【产地分布】生于山坡、丘陵、河边等较潮湿处；有栽培。分布于湖北、河南、山东、陕西等地。\n" +
                     "\n" +
                     "【采收加工】夏、秋季采挖根部，洗净，剥取根皮，晒干。\n" +
                     "\n" +
                     "【药材性状】根皮呈不规则卷筒状，长5～15厘米，直径0.4～1.4厘米，厚约0.2厘米。外表面灰褐色，有稍曲的纵皱纹及横长皮孔；内表面淡黄色或灰黄色，有细纵纹。体轻，质脆，易折断，断面不整齐，灰白色。气微香，味微辣而苦。\n" +
                     "\n" +
                     "【性味归经】性温，味辛、苦。归肝经、肾经。\n" +
                     "\n" +
                     "【功效与作用】祛风湿、补肝肾、强筋骨。属祛风湿药下分类的祛风湿强筋骨药。\n" +
                     "\n" +
                     "【临床应用】用量4.5～9克，水煎服，用治风湿痹痛、筋骨痿软、小儿行迟、体虚乏力、水肿、脚气。\n" +
                     "\n" +
                     "【药理研究】具有抗炎、抗应激、抗辐射作用，对造血功能有保护作用。能明显降低肾上腺中维生素C含量，具有促进动物肾上腺皮质的功能。对离体肠肌或离体子宫均有相似的兴奋作用，可使肠肌或子宫的张力略微升高，收缩幅度变大。对S180实体瘤有抑制作用。具有明显的抗氧化作用可使血中谷胱甘肽过氧化物酶活性增高和过氧化脂质含量明显增加。药理实验表明，水煎剂对金黄色葡萄球菌、绿脓杆菌有抑制作用。\n" +
                     "\n" +
                     "【化学成分】含硬脂酸、芝麻素、β-谷甾醇、紫丁香苷、β-谷甾醇葡萄糖苷等。另含挥发油、树脂、丁香苷、刺五加苷、无梗五加苷A、无梗五加苷B、无梗五加苷C、无梗五加苷D、无梗五加苷K2、无梗五加苷K3、五加苷Bl、菜油固醇、3-谷固醇、3-谷固醇葡萄糖苷、亚麻酸等成分。\n" +
                     "\n" +
                     "【使用禁忌】阴虚火旺者慎服。\n" +
                     "\n" +
                     "【配伍药方】①治阴囊水肿：五加皮9克，地骷髅30克。水煎服。(南药《中草药学》)\n" +
                     "\n" +
                     "②治老伤腰痛：五加皮根、野荞麦根各30克。放童便内浸7日，取出晒干研末，每服6克，日2次，米酒冲服。(《江西草药》)\n" +
                     "\n" +
                     "③治跌打损伤，青肿疼痛：五加皮、泽兰叶、芋儿七。共捣绒，用酒炒热，包敷患处。(《四川中药志》1960年)\n" +
                     "\n" +
                     "④治老人腰痛脚弱，小儿佝偻病：五加皮120克，鹿角霜60克，烧酒0.5克。泡10天，去渣过滤，加赤砂糖适量，每日2～3次，适量饮服。(《食物中药与便方》)\n" +
                     "\n" +
                     "⑤治肾虚腰痛，小儿麻痹后遗症，脚冷，阳痿：五加根皮9～15克。水煎服，或炖猪骨服。(《广西本草选编》)");

             chineseMedicineDao.insert(medicine87);

             ChineseMedicine medicine88=new ChineseMedicine();
             medicine88.setName("委陵菜");
             medicine88.setSortType("W");
             medicine88.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=15c1fa1d8db1cb132a643441bc3d3d2b/f9dcd100baa1cd1153e2e3d2b912c8fcc2ce2def.jpg");
             medicine88.setData("【中药名】委陵菜 weilingcai\n" +
                     "\n" +
                     "【别名】白头翁、翻白草、鸡爪草、天青地白。\n" +
                     "\n" +
                     "【英文名】Potentillae Chinensis Herba\n" +
                     "\n" +
                     "【药用部位】蔷薇科植物委陵菜Potentilla chinensis Ser.的带根全草。\n" +
                     "\n" +
                     "【植物形态】多年生草本，高30～60厘米，全株密生长柔毛。主根发达，圆锥形或圆柱形。茎直立或略斜生。羽状复叶，顶端小叶最大，两侧小叶渐次变小，有托叶；基生叶通常有小叶15片以上，少数可达31片；茎生叶有小叶3～13片；小叶片长圆形至长圆状倒披针形，边缘缺刻状羽状深裂，裂片三角形，常反卷，上面绿色，具疏短柔毛，下面灰白色，密被白色绵毛。聚伞花序聚集；花萼5，阔卵圆形，与副萼互生，副萼线状披针形；花瓣5片，深黄色；雄蕊多数，子房近卵形，花柱侧生，短。瘦果有毛，多数，聚生于被有绵毛的花托上，花萼宿存。花期5～8月，果期6～9月。\n" +
                     "\n" +
                     "【产地分布】生于向阳山坡或荒地。分布于黑龙江、吉林、辽宁等地。\n" +
                     "\n" +
                     "【采收加工】4～8月采收，将带根全草除去花枝及果枝，晒干；或将地上部分茎、叶全部除去，仅用根；也有的地区将根、叶分别人药。\n" +
                     "\n" +
                     "【药材性状】主根圆锥形或圆柱形，偶有弯曲，长短不一，时有分枝，直径0.5～1厘米；表面暗棕色或红棕色，有不规则纵裂纹及横裂纹，栓皮粗糙，多呈片状剥落，露出浅棕色内皮；质坚实.断面皮部及射线浅棕红色，不平坦。叶基生，单数羽状复叶，有柄；小叶狭长椭圆形，多向内对折，边缘羽状深裂，向外反卷，下表面及叶柄均密被灰白色茸毛。气微，味苦。以干燥、无花茎、色灰白、无杂质者为佳。\n" +
                     "\n" +
                     "【性味归经】性寒，味苦。归大肠经、肺经、肝经。\n" +
                     "\n" +
                     "【功效与作用】清热解毒、凉血止血。属清热药下属分类的清热解毒药。\n" +
                     "\n" +
                     "【临床应用】用于痢疾、痔疮出血、吐血、咯血、痈疮肿毒、咽喉炎、百日咳。煎汤内服，用量9～15克，外用鲜品适量，煎水洗或捣烂敷患处。临床用委陵菜30～60克治疗菌痢；委陵菜煎汁冲洗阴道，治阴道滴虫病；其复方治疗功能性子宫出血。\n" +
                     "\n" +
                     "【药理研究】抗病原体，对离体蛙、兔心脏呈抑制作用，对兔离体及在体肠管亦有抑制作用；还可扩张豚鼠离体支气管，兴奋豚鼠离体子宫。煎剂对痢疾杆菌、绿脓杆菌、枯草杆菌、金黄色葡萄球菌、阿米巴原虫等均有一定的抑制作用。在一定的剂量下对子宫有兴奋作用，大剂量可引起子宫痉挛性收缩。\n" +
                     "\n" +
                     "【化学成分】含黄酮类，如山柰素、槲皮素、α-茶酚；三萜类，如熊果酸、丝石竹皂苷元；有机酸，如没食子酸、壬酸、3,4,3’-三甲氧基-鞣化酸。还含有维生素C、蛋白质、脂肪、纤维素等。\n" +
                     "\n" +
                     "【使用禁忌】孕妇忌服。\n" +
                     "\n" +
                     "【配伍药方】①治赤白痢疾：委陵菜15克，马齿苋15克，茶叶6克。水煎服，每日2次。(《甘肃中草药手册》)\n" +
                     "\n" +
                     "②治阿米巴痢疾：委陵菜30克，炒槐花12克。煎服。(《安徽中草药》)\n" +
                     "\n" +
                     "③治休息痢：委陵菜根15克，十大功劳15克，车前草9克。水煎服。(《湖南药物志》)\n" +
                     "\n" +
                     "④治久痢不止：委陵菜、白木槿花各15克。煎水服。(《贵阳民间药草》)\n" +
                     "\n" +
                     "⑤治疮疖痈肿：委陵菜15克，蒲公英15克，水煎服。(《山西中草药》)\n" +
                     "\n" +
                     "⑥治便血：委陵菜根15克，小蓟炭12克，侧柏炭9克。煎服。(《安徽中草药》)\n" +
                     "\n" +
                     "⑦治消化道溃疡：委陵菜干根60克，鸡1只(约500克)。水炖服。(福建晋江《中草药手册》)\n" +
                     "\n" +
                     "⑧治百日咳：委陵菜全草15克，海金沙9克。水煎服。(《湖南药物志》)");

             chineseMedicineDao.insert(medicine88);

             ChineseMedicine medicine89=new ChineseMedicine();
             medicine89.setName("乌蔹莓");
             medicine89.setSortType("W");
             medicine89.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=4ec9600f9058d109d0eea1e0b031a7da/b812c8fcc3cec3fd6f9ea404d488d43f8794271a.jpg");
             medicine89.setData("【中药名】乌蔹莓 wulianmei\n" +
                     "\n" +
                     "【别名】五爪藤、野葡萄藤、五叶莓。\n" +
                     "\n" +
                     "【英文名】Herba Cayratia Japonicae。\n" +
                     "\n" +
                     "【药用部位】葡萄科植物乌蔹莓Cayratia japonica (Thunb.) Gagn.的全草。\n" +
                     "\n" +
                     "【植物形态】多年生蔓生草本。茎紫绿色，有纵棱，具卷须；幼枝有柔毛，后变光滑。掌状复叶，具小叶5枚，排列成鸟爪状，中间小叶椭圆状卵形，两侧的4枚小叶渐小，成对生于同一小叶柄上，但又各具小分叶柄，小叶边缘具较均匀的圆钝锯齿。聚伞花序腋生，花小，黄绿色，具短梗；萼杯状；花瓣4，卵状三角形；雄蕊4，与花瓣对生，花药长椭圆形；雌蕊1，子房上位，2室。浆果倒卵形，成熟时黑色；种子2～4粒。花期5～11月，果期8～10月。\n" +
                     "\n" +
                     "【产地分布】生于山坡、路旁、旷野或园篱旁，攀附于他物上或蔓生。分布于华东、中南、西南各地。\n" +
                     "\n" +
                     "【采收加工】夏、秋季采集全草，洗净泥土，除去杂质，晒干。\n" +
                     "\n" +
                     "【药材性状】草黄色，全株光滑无毛；根粗壮，浅褐色，横切面可见皮部约占1/2，射线明显；茎具纵棱，节间膨大，有与叶对生的卷须。掌状复叶，小叶片5枚，呈鸟爪状排列，多皱缩或破碎，完整者异型后可见中间l枚最大，侧生者各2枚着生于同一小叶柄上，小叶片椭圆状卵形至长卵形，边缘具疏钝齿。伞房花状聚伞花序腋生；花细小，径4～5毫米。气微，味苦、酸。\n" +
                     "\n" +
                     "【性味归经】性寒，味苦、酸。归心经、肝经、胃经。\n" +
                     "\n" +
                     "【功效与作用】清热解毒，利湿消肿。属清热药下属分类的清热燥湿药。\n" +
                     "\n" +
                     "【临床应用】用量9～15克；外用适量，常用鲜品捣烂外敷。用治痈肿、疔疮、痄腮、丹毒等热毒疮疡症；湿热流注经络之风湿骨痛；湿热壅滞之黄疸、痢疾及尿血、白浊等症。\n" +
                     "\n" +
                     "【药理研究】抗病毒、抗炎、解热；抗菌；具有抗凝血和调节免疫功能的作用。水煎液(30毫克/毫升)在试管内能抑制钩端螺旋体的生长。\n" +
                     "\n" +
                     "【化学成分】含阿聚糖、黏液质、黄酮类、酚类、樟脑、香桧烯、δ-荜澄茄醇、α-松油醇、乌蔹色苷等成分。\n" +
                     "\n" +
                     "【使用禁忌】尚不明确。\n" +
                     "\n" +
                     "【配伍药方】①治项下热肿，俗名虾蟆瘟：乌蔹莓捣敷之。(《丹溪纂要》)\n" +
                     "\n" +
                     "②治发背，臀痈，便毒：乌蔹莓全草水煎2次过液，将2次煎汁合并一处，再隔水煎浓缩成膏，涂纱布上，贴敷患处，每日换一次。(《江西民间草药》)\n" +
                     "\n" +
                     "③治乳腺炎：鲜乌蔹莓，捣烂敷患处。(《青岛中草药手册》)\n" +
                     "\n" +
                     "④治淋巴腺炎：乌蔹莓叶适量，和等量水仙花鳞茎，红糖少许，共捣烂，加温敷患处。(《福建药物志》)\n" +
                     "\n" +
                     "⑤治带状疱疹：乌蔹莓根，磨烧酒与雄黄，抹患处。(《福建药物志》)\n" +
                     "\n" +
                     "⑥治风湿关节疼痛：乌蔹莓根30克，泡酒服。(《贵州草药》)");

             chineseMedicineDao.insert(medicine89);

             ChineseMedicine medicine90=new ChineseMedicine();
             medicine90.setName("瓦松");
             medicine90.setSortType("W");
             medicine90.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=d810613fa61ea8d39e2f7c56f6635b2b/c2fdfc039245d6883fd56420a4c27d1ed31b245b.jpg");
             medicine90.setData("【中药名】瓦松 wasong\n" +
                     "\n" +
                     "【别名】向天草、瓦花、瓦塔、天蓬草、石蓬花、狗指甲。\n" +
                     "\n" +
                     "【英文名】Orostachyis Fimbriatae Herba。\n" +
                     "\n" +
                     "【药用部位】景天科植物瓦松Orosta.chys fimbriata(Turcz.)Berg.的干燥地上部分。\n" +
                     "\n" +
                     "【植物形态】二年生草本植物，基生叶莲座状排列，短。先端为白色附属物，半圆形，边缘有流苏状，中央有1针状尖头。茎生叶线形至倒卵形，长1.9～3厘米，宽0.2～0.5厘米，先端长尖。花茎高10～20(～40)厘米。总状花序或圆锥状花序。苞片线形，花梗长达1厘米。萼片长圆形，长1～3厘米，花瓣红色或白色，长卵状披针形或针形，雄蕊短于或几与花瓣等长，花药紫色。蓇葖果长圆形，种子多数，卵形。花期8～9月，果期9～20月。\n" +
                     "\n" +
                     "【产地分布】生于海拔1600米(青海及甘肃可达3500)以下的屋顶瓦缝、墙头、山坡石缝及长苔藓的树干上。分布于全国各省区。\n" +
                     "\n" +
                     "【采收加工】夏、秋二季花开时采收，除去根及杂质，晒干。\n" +
                     "\n" +
                     "【药材性状】茎呈细长圆柱形，长5～27厘米，直径2～6厘米。表面灰棕色，具多数突起的残留叶基，有明显的纵棱线。叶多脱落.破碎或卷曲，灰绿色。圆锥花序穗状，小花白色或粉红色，花梗长约5毫米。体轻，质脆，易碎。气微，味酸。\n" +
                     "\n" +
                     "【性味归经】性凉，味酸、苦。归肝经、肺经、脾经。\n" +
                     "\n" +
                     "【功效与作用】凉血止血，解毒，敛疮。属清热药分类下的清热解毒药。\n" +
                     "\n" +
                     "【临床应用】内服：煎汤，5～15克；捣汁；或入丸剂。外用：适量，捣敷；或煎水熏洗；或研末调敷。用治血痢，便血，痔血，疮口久不愈合。\n" +
                     "\n" +
                     "【药理研究】具有强心作用。毒性测试：小鼠腹腔注射流浸膏50~100克生药1千克可以致死，豚鼠腹腔注射50克生药1千克亦引起死亡。\n" +
                     "\n" +
                     "【化学成分】瓦松全草含槲皮素，槲皮素-3-葡萄糖甙，山奈酚，山奈酚-7-鼠李糖甙，山奈酚-3-葡萄糖甙-7-鼠李糖甙及草酸，晚红瓦松含草酸。\n" +
                     "\n" +
                     "【使用禁忌】脾胃虚寒者忌用。\n" +
                     "\n" +
                     "【配伍药方】①治肺热喘咳：鲜瓦松60～90克。煎水加少许白糖，连渣服。(《四川中药志》1982年)\n" +
                     "\n" +
                     "②治乳糜尿：瓦松6克。煎水适量，白糖冲服。[《山东中医杂志》1988，7(4)：51]\n" +
                     "\n" +
                     "③治急性无黄疸型传染性肝炎：瓦松60克，麦芽30克，垂柳嫩枝9克。水煎服。(《浙江民间常用草药》)\n" +
                     "\n" +
                     "④治痔疮：a.鲜瓦松，煎水熏洗患处。(《浙江民间常用草药》)b.治痔疮肿痛出血：瓦松18克，二花、连翘各6克，薏苡仁24克。水煎服。(《中药临床手册》)\n" +
                     "\n" +
                     "⑤治烫伤火泡：瓦松、生柏叶。同捣敷，干者为末。(《医方摘要》)\n" +
                     "\n" +
                     "⑥治牙龈肿痛：瓦松、白矾等分。水煎漱之。(《摘玄方》)\n" +
                     "\n" +
                     "⑦治湿疹：瓦松(晒干)、烧灰研末，合茶油调抹，止痛止痒。(《泉州本草》)");

             chineseMedicineDao.insert(medicine90);

             ChineseMedicine medicine91=new ChineseMedicine();
             medicine91.setName("梧桐子");
             medicine91.setSortType("W");
             medicine91.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=2d597d287b0e0cf3b4fa46a96b2f997a/d058ccbf6c81800aa4d35934b83533fa828b47d2.jpg");
             medicine91.setData("【中药名】梧桐子 wutongzi\n" +
                     "\n" +
                     "【别名】瓢儿果、桐麻豌、凤眼果、红花果。\n" +
                     "\n" +
                     "【英文名】Semen Firmianae。\n" +
                     "\n" +
                     "【药用部位】梧桐科植物梧桐Firmiana simplex (L.) W.F.Wight的种子。\n" +
                     "\n" +
                     "【植物形态】落叶乔木，高16米，树皮青绿色，平滑。单叶互生，3～5掌状深裂，长15～30厘米，宽11～20厘米，基部心形，裂片三角形，顶端渐尖，两面均无毛或略被短柔毛，脉掌状；叶柄与叶片近等长。圆锥花序顶生；花单性，淡黄绿色；萼片5，条形，长约8毫米，外面密被淡黄色短柔毛；花梗与花近等长；雄花中的雄蕊柱约与萼片等长，花药15枚，聚集在花蕊柱的顶端；雌花的子房柄发达，心皮5，基部分离。蓇葖果膜质，有柄，成熟时开裂成叶状，长6～11厘米，宽1.5～2.5厘米，每个蓇葖果有种子2～4个。种子圆球形，表面有皱纹，直径约7毫米。花期6～7月，果期8～11月。\n" +
                     "\n" +
                     "【产地分布】多为人工栽培。分布于我国南北各地。\n" +
                     "\n" +
                     "【采收加工】夏、秋季采收，鲜用或晒干。\n" +
                     "\n" +
                     "【药材性状】呈球形，状如豌豆。表面淡绿色至黄棕色，微具光泽，有明显隆起的网状皱纹。质轻而硬，外层种皮较脆易碎裂，内层种皮坚韧。剥除种皮，可见淡红色的数层外胚乳，内为肥厚的淡黄色内胚乳，油质，子叶两片薄而大，紧贴在内乳上，胚根在较小的一端。气味均微。\n" +
                     "\n" +
                     "【性味归经】性平，味甘。归心经、肺经、肾经。\n" +
                     "\n" +
                     "【功效与作用】行气健脾、消食和中、止血。属消食药。\n" +
                     "\n" +
                     "【临床应用】用量5～10克；或研末，2～3克。外用：适量，煅存性研末敷。用治脾虚气滞、饮食不消、胃痛腹泻、鼻衄。此外，尚可治须发早白、小儿口疮等。\n" +
                     "\n" +
                     "【药理研究】给麻醉兔、猫静脉注射梧桐子总生物碱，能使血压迅速下降，但不持久，同时出现心率减慢。煎剂可使大鼠创伤性出血时间缩短。\n" +
                     "\n" +
                     "【化学成分】含脂肪油39.69%，油中分离出苹婆酸、锦葵酸、咖啡碱等成分。\n" +
                     "\n" +
                     "【使用禁忌】咳嗽多痰者、患有耳疾者不宜用。\n" +
                     "\n" +
                     "【配伍药方】①治伤食腹痛腹泻：梧桐子炒焦研粉。冲服，每服3克。(广州部队《常用中草药手册》)\n" +
                     "\n" +
                     "②治伤食腹痛腹泻：梧桐子(炒焦)15克，青藤香12克。共为细末。每服3克，开水送服。(《四川中药志》1979年)\n" +
                     "\n" +
                     "③治疝气：梧桐子炒香，剥壳食之。(《贵州省中医经验秘方》)\n" +
                     "\n" +
                     "④治白发：梧桐子9克，何首乌15克，黑芝麻9克，熟地黄15克。水煎服。(《山东中草药手册》)");

             chineseMedicineDao.insert(medicine91);

             ChineseMedicine medicine92=new ChineseMedicine();
             medicine92.setName("吴茱萸");
             medicine92.setSortType("W");
             medicine92.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=8faa200271f08202399f996d2a929088/b21c8701a18b87d6059e6f3e050828381f30fd37.jpg");
             medicine92.setData("【中药名】吴茱萸 wuzhuyu\n" +
                     "\n" +
                     "【别名】吴萸、左力、曲药子、气辣子、漆辣子、优辣子。\n" +
                     "\n" +
                     "【英文名】Euodiae Fructus。\n" +
                     "\n" +
                     "【药用部位】芸香科植物吴茱萸Evodia rutaecarpa (Juss.) Benth.的近成熟果实。\n" +
                     "\n" +
                     "【植物形态】常绿灌木或小乔木。幼枝、叶轴、小叶柄均密被黄褐色长柔毛。单数羽状复叶对生；小叶2～4对，叶片椭圆形至卵形，全缘，两面密被淡黄色长柔毛，有油点。聚伞花序顶生，花单性，雌雄异株；花轴基部有苞片2枚，上部苞片鳞片状；花小，黄白色，萼片5，广卵形；花瓣5，长圆形；雄花有雄蕊5；雌花较大，具退化雄蕊5。蒴果扁球形，成熟时紫红色，每心皮有种子1枚，卵圆形，黑色，有光泽。花期6～8月，果期9～10月。\n" +
                     "\n" +
                     "【产地分布】生于山坡草丛中。主产于贵州、广西、湖南、浙江、四川等地。\n" +
                     "\n" +
                     "【采收加工】9～10月，果实茶绿色而心皮尚未分离时采收，摘下晒干或低温烘干。\n" +
                     "\n" +
                     "【药材性状】果实类球形或略呈五角状扁球形，直径2～5毫米；表面暗黄绿色至污绿色，有许多点状突起；顶端稍有下凹，五角星状裂隙，有时裂隙中央有突起的柱头残基；基部有花萼及短小果柄。在放大镜下观察，表面粗糙，有圆形而稍下凹的油腺，花萼及果柄上可见黄色茸毛。质硬而脆，具浓郁香气；味苦而微辛辣。\n" +
                     "\n" +
                     "【性味归经】性热，味辛、苦。有小毒。归肝经、脾经、胃经、肾经。\n" +
                     "\n" +
                     "【功效与作用】散寒止痛、降逆止呕，助阳止泻。属温里药。\n" +
                     "\n" +
                     "【临床应用】用量3～10克；外用适量。用治肝经寒凝之疝气腹痛、厥阴头痛、冲任虚寒、瘀血阻滞之痛经、寒湿脚气肿痛、胃寒或脾胃虚寒之脘腹冷痛、呕吐、外寒内侵、胃失和降之呕吐、脾肾阳虚之五更泄泻、湿疹等。\n" +
                     "\n" +
                     "【药理研究】本品具有芳香健胃作用，能祛除肠内积气及抑制肠内异常发酵，增加消化液分泌，抑制胃肠蠕动而解痉、止吐，并有镇痛、抗胃溃疡、降血压、兴奋子宫、抗血栓形成、杀虫、抗菌、升高体温、保肝利胆、抑制中枢神经系统、改善心血管系统功能、抗血栓、抗缺氧等药理作用。\n" +
                     "\n" +
                     "【化学成分】含挥发油，其中吴茱萸烯是其主要成分，含量达30%。此外，还含有多种柠檬苦素类、生物碱、黄酮类、酮类、氨基酸、吴茱萸酰胺、吴茱萸碱、吴茱萸啶酮、去甲基吴茱萸酰胺、吴茱萸苦素等成分。\n" +
                     "\n" +
                     "【使用禁忌】不宜多服久服，无寒湿滞气及阴虚火旺者忌服。\n" +
                     "\n" +
                     "【配伍药方】①治呕吐涎沫，头痛及少阴病吐利、手足逆冷、烦躁欲死者：吴茱萸(洗)一升，人参90克，生姜(切)180克，大枣(擘)十二枚。以水七升，煮取二升，去滓。温服七合，日三服。(《伤寒论》吴茱萸汤)\n" +
                     "\n" +
                     "②治蛔心痛：吴茱萸(水浸一宿，焙干炒)15克，鹤虱(微炒)45克。上为细散。每服6克，空心温酒调下。(《普济方》吴茱萸散)\n" +
                     "\n" +
                     "③治牙齿疼痛：吴茱萸煎酒，含漱之。(《食疗本草》)\n" +
                     "\n" +
                     "④治口疮：吴茱萸末，醋调涂足心。亦治咽喉作痛。(《濒湖集简方》)");

             chineseMedicineDao.insert(medicine92);

             ChineseMedicine medicine93=new ChineseMedicine();
             medicine93.setName("乌梅");
             medicine93.setSortType("W");
             medicine93.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=cd936068d100baa1ae214fe92679d277/0e2442a7d933c89583ed35ffd11373f0830200a6.jpg");
             medicine93.setData("【中药名】乌梅 wumei\n" +
                     "\n" +
                     "【别名】梅实、熏梅、桔梅肉。\n" +
                     "\n" +
                     "【英文名】Dark Plum Fruit、Smoked Plum、Dark Plum.\n" +
                     "\n" +
                     "【药用部位】蔷薇科植物梅Prunus mume Sieb. et Zucc.的近成熟果实。\n" +
                     "\n" +
                     "【植物形态】落叶乔木。树皮淡灰色或淡绿色，多分枝。单叶互生；有叶柄，通常有腺体；嫩枝叶柄基部有线形托叶2片，托叶边缘具不整齐细锐锯齿；叶片卵形至长圆状卵形，先端长尾尖，基部阔楔形，边缘具细锐锯状齿，沿脉背呈褐黄色。花单生或2朵簇生，白色或粉红色；芳香；通常先叶开放；有短梗；苞片鳞片状，褐色；萼筒钟状，裂片5，基部与花托合生；花瓣单瓣或重瓣，通常5片，阔倒卵形；雄蕊多数，生于花托边缘；雌蕊1，子房密被毛，花柱细长，弯曲。核果球形，一侧有浅槽，被毛，绿色，熟时黄色，核硬，有槽纹。花期1～2月，果期5～6月。\n" +
                     "\n" +
                     "【产地分布】主产于浙江、福建、云南等地。\n" +
                     "\n" +
                     "【采收加工】5～6月果青黄色时采收，按大小分开，分别炕焙，火力不宜过大，温度保持在40℃左右。焙至果肉呈黄褐色起皱皮为度。焙后再焖2～3天，待变成黑色即可。\n" +
                     "\n" +
                     "【药材性状】类球形或扁球形，直径2～3厘米，表面棕黑色至乌黑色，极皱缩，于放大镜下可见茸毛，基部有圆形果梗痕。果肉软或略硬，果核坚硬，椭圆形，棕黑色，表面有凹点，内含卵圆形、淡黄色种子1粒。具焦糖气，味极酸而涩。\n" +
                     "\n" +
                     "【性味归经】性平，味酸、涩。归肝经、脾经、肺经、大肠经。\n" +
                     "\n" +
                     "【功效与作用】敛肺、涩肠、生津、安蛔。属收涩药分类下的敛肺涩肠药。\n" +
                     "\n" +
                     "【临床应用】用量6～12克。外用适量，捣烂或炒炭研末外敷。用治肺虚久咳、久泻、久痢、蛔厥腹痛、呕吐、虚热消渴、崩漏下血、疮毒等。\n" +
                     "\n" +
                     "【药理研究】药理实验证明，能使胆囊收缩，促进胆汁分泌，并有抗蛋白过敏作用；对多种致病菌有抗菌作用，对各种皮肤真菌亦有抑制作用；有驱蛔虫的作用；有抗疲劳、抗衰老、抗辐射、拮抗钙离子、增强免疫功能等作用。\n" +
                     "\n" +
                     "【化学成分】含有72种挥发油成分、有机酸、氨基酸及多糖。用酸碱滴定法测定乌梅中枸橼酸的含量，按干燥品计算，含有机酸以枸橼酸计，不得少于15.0%。另含苹果酸、5-羟甲基-2-糠醛、延胡索酸、亚油酸等成分。\n" +
                     "\n" +
                     "【使用禁忌】表邪未解者禁服，内有实邪者慎用。不宜多食。\n" +
                     "\n" +
                     "【配伍药方】①治咯血：乌梅不以多少，煎汤，调百草霜。一服愈。(《朱氏集验方》)\n" +
                     "\n" +
                     "②治咽喉肿痛：乌梅30克，双花60克，雄黄12克。为末，蜜丸，每丸3克，每次含化1丸，徐徐咽下，每日3次。(《全国中草药新医疗法展览会资料选编》)\n" +
                     "\n" +
                     "③治诸疮水毒肿痛：乌梅、皂荚子等分。上各烧存性研匀，贴疮上，毒汁即出。(《普济方》)\n" +
                     "\n" +
                     "④治鸡眼：乌梅肉、荔枝肉各等分，捣膏敷贴。(《疡医大全》)");

             chineseMedicineDao.insert(medicine93);

             ChineseMedicine medicine94=new ChineseMedicine();
             medicine94.setName("五味子");
             medicine94.setSortType("W");
             medicine94.setMedicineImageUrl("http://img14.360buyimg.com/n0/jfs/t2479/28/1349050606/197079/6626b8be/56936b31N10d25088.jpg");
             medicine94.setData("【中药名】五味子 wuweizi\n" +
                     "\n" +
                     "【别名】北五味子、玄及、会及、五梅子、山花椒。\n" +
                     "\n" +
                     "【英文名】Schisandrae Chinensis Fructus。\n" +
                     "\n" +
                     "【药用部位】为木兰科植物五味子Schisandra chinensis(Turcz.)Baill.的干燥成熟果实。\n" +
                     "\n" +
                     "【植物形态】落叶木质藤本。小枝褐色，有棱角，全株近无毛。单叶，互生，叶柄长2～4.5厘米，叶倒卵形、宽卵形或椭圆形，长5～10厘米，宽3～5厘米，先端急尖或渐尖，基部楔形，边缘有腺状细齿，上面光滑无毛，下面叶脉上嫩时有短柔毛。花单性，雌雄异株，花单生或簇生于叶腋，花梗细长而柔弱，花被6～9片，乳白色或粉红色，雄花有雄蕊5枚，雌花的雌蕊群椭圆形，有17～40个离生的心皮，覆瓦状排列在花托上。开花后期，花托逐渐延长，果熟时成穗状聚合果。浆果，肉质，直径约5毫米，紫红色。种子肾形，淡橙色，有光泽。花期5～6月，果期8～9月。\n" +
                     "\n" +
                     "【产地分布】生于山坡杂木林下，常缠绕在其他植物上。分布于黑龙江、吉林、辽宁、河北、山西、内蒙古、陕西，主产于黑龙江、吉林、辽宁。\n" +
                     "\n" +
                     "【采收加工】秋季果实成熟时采摘，晒干或蒸后晒干，除去果梗和杂质。\n" +
                     "\n" +
                     "【药材性状】本品呈不规则的球形或扁球形，直径5～8毫米。表面红色、紫红色或暗红色，皱缩，显油润；有的表面呈黑红色或出现“白霜”。果肉柔软，种子1～2，肾形，表面棕黄色，有光泽，种皮薄而脆。果肉气微，味酸，种子破碎后，有香气，味辛、微苦。\n" +
                     "\n" +
                     "【性味归经】性温，味酸、甘。归肺经、心经、肾经。\n" +
                     "\n" +
                     "【功效与作用】收敛固涩、益气生津、补肾宁心。属收涩药分类下的敛肺涩肠药。\n" +
                     "\n" +
                     "【临床应用】用量2～6克，煎汤内服；研末，每次1～3克；熬膏；或入丸、散。外用：适量，研末掺；或煎水洗。用治久嗽虚喘，梦遗滑精，遗尿尿频，久泻不止，自汗盗汗，津伤口渴，内热消渴，心悸失眠。\n" +
                     "\n" +
                     "【药理研究】促进机体免疫功能；抗氧化及抗衰老；护肝、诱导肝脏药物代谢酶；镇咳祛痰，抗肿瘤；降血糖；强心；增强机体适应能力；抗溃疡；抗肾病变；抗菌等作用。据报导，五味子煎剂静脉注射，对正常兔子都有呼吸兴奋作用，使吸收加深、加快，并能对抗吗啡的呼吸抑制作用，酊剂亦有同样效果。\n" +
                     "\n" +
                     "【化学成分】果实含多种木脂素，还含挥发油，对中枢兴奋药戊四唑和士的宁半数致死量的影响。种仁含五味子素A、B、C，五味子醇A及五味子醇B[17]。另含五味子醇甲、戈米辛A、戈米辛D、戈米辛E、戈米辛N、戈米辛O、去当归酰戈米辛B、异五味子素、表戈米辛O、华中五味子酯A、a-水芹烯、橙花叔醇、巴豆酰戈米辛等成分。\n" +
                     "\n" +
                     "【使用禁忌】外有表邪，内有实热，或咳嗽初起、痧疹初发者忌服。\n" +
                     "\n" +
                     "【配伍药方】①治阳痿不起：五味子、菟丝子、蛇床子各等分。上三味末之，蜜丸如梧桐子。饮服三丸，日三。(《千金要方》)\n" +
                     "\n" +
                     "②治滑泄：陈米、肉豆蔻(面裹煨)、五味子、赤石脂(研)各30克。上为末。每服6克，粟米汤饮调下，日进三服。(《世医得效方》豆蔻饮)\n" +
                     "\n" +
                     "③治睡中盗汗：五味子30克，研末，以唾调作饼。敷脐上，以布扎定后睡，候天明取下，一二晚汗即上。(《医方一盘珠》)\n" +
                     "\n" +
                     "④治肺经感寒，咳嗽不已：白茯苓120克，甘草90克，干姜90克，细辛90克，五味子75克。上为细末。每服6克，水一盏，煎至七分，去滓，温服，不以时。(《鸡峰普济方》五味细辛汤)\n" +
                     "\n" +
                     "⑤治小儿暴嗽：五味子、桂(去粗皮)、干姜(炮)等分。上三味，粗捣筛。每服3克，水七分，煎至四分，去滓，量大小加减温服。(《圣济总录》五味子汤)");

              chineseMedicineDao.insert(medicine94);

              ChineseMedicine medicine95=new ChineseMedicine();
              medicine95.setName("无花果");
              medicine95.setSortType("W");
              medicine95.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D480/sign=bf8492a473f40ad115e4c6eb672e1151/8644ebf81a4c510f6c0647866859252dd52aa568.jpg");
              medicine95.setData("【中药名】无花果 wuhuaguo\n" +
                      "\n" +
                      "【别名】天生子、文仙果、奶浆果。\n" +
                      "\n" +
                      "【英文名】Syconium Fici Caricae\n" +
                      "\n" +
                      "【来源】桑科植物无花果Ficus caricaL.的成熟花托与果实。\n" +
                      "\n" +
                      "【植物形态】落叶灌木或小乔木。具乳汁。多分枝，小枝粗壮，表面褐色，被稀短毛。叶互生，倒卵形或近圆形，宽与长几相等，3～5裂，少不分裂，基部心形，裂片倒卵形，顶端钝，有不规则细齿；掌状脉明显；上表面粗糙，下表面有毛；厚革质。隐头花序单生于叶腋，梨形，成熟时带绿色或青褐色。花单性同株，小花白色，极多数； 雄花生于花托近口处苞片间，花被2～6片，线形，雄蕊1枚，丝状；雌花花被4片，广卵形。瘦果三棱状卵形。花期夏季，隐头果成熟期秋季。\n" +
                      "\n" +
                      "【产地分布】常栽培在温暖向阳的边角隙地，分布于全国各地。\n" +
                      "\n" +
                      "【采收加工】7～10月果实呈绿色时，分批采摘，或拾取落地的未成熟果实，鲜果用开水烫后，晒干或烘干。\n" +
                      "\n" +
                      "【药材性状】倒圆锥形或类球形，长约2厘米，直径1.5～2.5厘米。表面淡黄棕色至暗棕色、青黑色，有波状弯曲的纵棱线，顶端稍平截，中央有圆形突起，基部较狭，带果柄及残存苞片。质坚硬，横切面黄白色，内壁着生众多细小瘦果，有时上部可见枯萎的雄花。瘦果卵形或三棱状卵形，淡黄色，外有宿萼包被。气微，味甜。\n" +
                      "\n" +
                      "【性味归经】性平，味甘。归肺经、胃经、大肠经。\n" +
                      "\n" +
                      "【功效与作用】健胃清肠、消肿解毒。属清热药下属分类的清热解毒药。\n" +
                      "\n" +
                      "【临床应用】用量50～100克，煎汤内服，用治肠炎、痢疾、便秘、痔疮、咽喉刺痛、痈疮疥癣。外用适量，煎水洗患处或研末吹喉。\n" +
                      "\n" +
                      "【药理研究】石油醚、乙醚提物对兔、猫、狗均有降压作用，乳汁中含有抗肿瘤成分，能增强免疫功能。\n" +
                      "\n" +
                      "【化学成分】含葡萄糖、果糖、蔗糖、柠檬酸和少量延胡索酸、枸橼酸、莽草酸、琥珀酸等多种有机酸，另含叶黄素、无花果朊酶等成分。\n" +
                      "\n" +
                      "【使用禁忌】脾胃虚寒者慎服。\n" +
                      "\n" +
                      "【配伍药方】①治咽痛：无花果7个，金银花15克。水煎服。(《山东中草药手册》)\n" +
                      "\n" +
                      "②治肺热音嘶：无花果干果15克。水煎，调冰糖服。(《福建中草药》)\n" +
                      "\n" +
                      "③治干咳，久咳：无花果9克，葡萄干15克，甘草6克。水煎服。(《新疆中草药手册》)\n" +
                      "\n" +
                      "④治大便秘结：鲜无花果适量，嚼食或干果捣碎煎汤。加生蜂蜜适量，空腹时温服。(《安徽中草药》)\n" +
                      "\n" +
                      "⑤治久泻不止：无花果5～7枚。水煎服。(《湖南药物志》)");

              chineseMedicineDao.insert(medicine95);

              ChineseMedicine medicine96=new ChineseMedicine();
              medicine96.setName("王不留行");
              medicine96.setSortType("W");
              medicine96.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=017939072edda3cc1fe9b07260805264/5366d0160924ab18dd6441af35fae6cd7b890b33.jpg");
              medicine96.setData("【中药名】 王不留行 wangbuliuxing\n" +
                      "\n" +
                      "【别名】不留子、牡牛、奶米、王不留、麦蓝子、剪金子、留行子。\n" +
                      "\n" +
                      "【英文名】Vaccariae Semen。\n" +
                      "\n" +
                      "【来源】石竹科植物麦蓝菜Vaccaria segetalis (Neck.) Garcke的成熟种子。\n" +
                      "\n" +
                      "【植物形态】一年生或二年生草本，高30～70厘米，全体平滑无毛，唯梢有白粉。茎直立，上部呈二叉状分枝，近基部节间粗壮而较短，节略膨大，表面呈乳白色。单叶对生，无柄；叶卵状椭圆形至卵状披针形，先端渐尖，基部圆形或近心形，稍连合抱茎，全缘，两面均呈粉绿色，中脉在下面突起，近基部较宽。夏季开淡红色花，疏生聚伞花序着生于枝顶，花梗细长，下有鳞片状小苞片2枚；花萼圆筒状，花后增大呈5棱状球形；花瓣5，倒卵形，先端有不整齐小齿；雄蕊10枚，子房上位。蒴果包于宿存的花萼内，成熟后先端呈4齿状开裂。种子多数，暗黑色，球形，有明显的粒状突起。花期4～5月，果期6月。\n" +
                      "\n" +
                      "【产地分布】生于山坡、路旁及丘陵地带荒地上，尤以麦田中生长最多；也有栽培。主产于河北、黑龙江、山东等地。\n" +
                      "\n" +
                      "【采收加工】6～7月种子成熟、蒴果未开裂时，割取全株，晒干，打下种子，去尽杂质，晒干。\n" +
                      "\n" +
                      "【药材性状】球形，直径约2毫米。表面黑色，少数红棕色，略带光泽，有细密颗粒状突起，一侧有一凹陷的纵沟。质硬，胚乳白色，胚弯曲成环。子叶2。无臭，味微涩苦。\n" +
                      "\n" +
                      "【性味归经】性平，味苦。归肝经、胃经。\n" +
                      "\n" +
                      "【功效与作用】活血通经、下乳消肿。属活血化瘀药下属分类的活血调经药。\n" +
                      "\n" +
                      "【临床应用】用量4.5～9克，煎汤内服。用治血瘀、经闭、乳汁不下、痈肿疔毒等。现在用治带状疱疹，或用作耳穴压迫治疗胆囊炎、胆石症。\n" +
                      "\n" +
                      "【药理研究】抗着床；抗早孕；兴奋子宫；收缩平滑肌和镇痛等作用。\n" +
                      "\n" +
                      "【化学成分】主要含王不留行皂苷、王不留行三萜皂苷、多种环肽、黄酮化合物、氢化阿魏酸、尿核苷、王不留行黄酮苷、麦蓝菜咕吨酮、王不留行咕吨酮、异肥皂草苷、王不留行次皂苷、D-葡萄糖等成分。\n" +
                      "\n" +
                      "【使用禁忌】孕妇、血虚无瘀滞者均禁服。\n" +
                      "\n" +
                      "【配伍药方】①治乳痈初起：王不留行30克，蒲公英、瓜 仁各9克，当归梢9克，酒煎服。(《本草汇言》)\n" +
                      "\n" +
                      "②治痈肿：王不留行(成末)二升，甘草150克，野葛60克，桂心120克，当归120克。上五物，以酒服方寸匕，日三夜一。(《医心方》王不留行散)\n" +
                      "\n" +
                      "③治疗肿初起：王不留行子为末，蟾酥，丸黍米大。每服一丸，酒下，汗出即愈。(《濒湖集简方》)\n" +
                      "\n" +
                      "④治血淋不止：王不留行30克，当归身、川续断、白芍药、丹参各6克。分作二剂，水煎服。(《本草汇言》引《东轩产科方》)\n" +
                      "\n");

              chineseMedicineDao.insert(medicine96);

              ChineseMedicine medicine97=new ChineseMedicine();
              medicine97.setName("威灵仙");
              medicine97.setSortType("W");
              medicine97.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=b2223a78abd3fd1f2204aa6851274e7a/fd039245d688d43fb73d38a47e1ed21b0ff43bcb.jpg");
              medicine97.setData("【中药名】威灵仙 weilingxian\n" +
                      "\n" +
                      "【别名】老虎须、铁扫帚、铁脚威灵仙、黑骨头、黑脚威灵仙。\n" +
                      "\n" +
                      "【英文名】Clematidis Radix Et Rhizoma。\n" +
                      "\n" +
                      "【来源】毛茛科植物威灵仙Clematis chinensis Osbeck.、棉团铁线莲Clematis hexapetala Pall.或东北铁线莲Clematis manshurica Rupr.的根及根茎。(本文以威灵仙为例，右图亦为威灵仙)\n" +
                      "\n" +
                      "【植物形态】藤本，植物干时变黑。根丛生于块状根茎上，细长圆柱形。茎具明显条纹，近无毛。叶对生，1回羽状复叶，小叶5，略带革质，狭卵形或三角状卵形，先端钝或渐尖，基部圆形或宽楔形，全缘，主脉3条，上面沿叶脉有细毛，下面无毛。圆锥花序顶生及腋生；总苞片窄线形，密生细白毛；萼片4，有时5，花瓣状，长圆状倒卵形，白色或绿白色，外被白色柔毛，内侧光滑无毛；雄蕊多数，不等长，花丝扁平；心皮多数，离生，子房及花柱上密生白毛。瘦果扁平，略生细短毛，花柱宿存，延长成白色羽毛状。花期5～6月，果期6～7月。\n" +
                      "\n" +
                      "【产地分布】威灵仙主要生于山谷、山坡林边或灌木丛中。分布于广西、四川、贵州、云南等地。\n" +
                      "\n" +
                      "【采收加工】秋季采挖出，除去泥沙，晒干。\n" +
                      "\n" +
                      "【药材性状】根茎柱状，长1.5～10厘米，直径0.3～1.5厘米；表面淡棕黄色；顶端残留茎基；质较坚韧，断面纤维性；下侧着生多数细根。根呈细长圆柱形，稍弯曲，长7～15厘米，直径0.1～0.3厘米；表面黑褐色，有细纵纹，有的皮部脱落，露出黄白色木部；质硬脆，易折断，断面皮部较广，木部淡黄色，略呈方形，皮部与木部间常有裂隙。气微，味淡。\n" +
                      "\n" +
                      "【性味归经】性温，味辛、咸。归膀胱经。\n" +
                      "\n" +
                      "【功效与作用】祛风除湿、通络止痛。属祛风湿病下属分类的祛风湿散寒药。\n" +
                      "\n" +
                      "【临床应用】用量6～9克，煎汤，消骨鲠可用至30克，或入丸、散剂。用治风湿痹痛、肢体麻木、筋脉拘挛、屈伸不利、骨鲠咽喉。具有镇痛、抗疟，降血糖，利胆，增强食管蠕动，软化鱼骨刺，松弛咽、食道及肠平滑肌等作用。\n" +
                      "\n" +
                      "【药理研究】对平滑肌有增强节律或松弛作用，具有镇痛、利胆、引产、抗微生物、抗利尿、降血压等作用。\n" +
                      "\n" +
                      "【化学成分】威灵仙含齐墩果酸、常春藤皂苷元、威灵仙-23-0-阿拉伯糖皂苷、白头翁素、白头翁内酯、甾醇、糖类、皂苷、内酯、酚类、氨基酸等。\n" +
                      "\n" +
                      "【使用禁忌】气血亏虚及孕妇慎用。\n" +
                      "\n" +
                      "【配伍药方】①治手足麻痹，时发疼痛，或打扑伤损，痛不可忍，或瘫痪等：威灵仙(炒)150克，生川乌、五灵脂各120克，为末。醋糊丸，梧子大：每服七丸，用盐汤下，忌茶。(《普济方》)\n" +
                      "\n" +
                      "②治尿路结石：威灵仙60～90克，金钱草50～60克，每日1剂，煎服。[《上海中医药杂志》1983，(5)：30]\n" +
                      "\n" +
                      "③治诸骨哽咽：或灵仙36克，砂仁30克，砂糖一盏，水二钟，煎一钟。温服。(《纲目》)\n" +
                      "\n" +
                      "④治急性乳腺炎：威灵仙适量。研末，以米醋拌和成糊状，30分种后贴敷于患处，随干随换，一般1～3天即愈。[《浙江中医杂志》1984，(1)：39]\n" +
                      "\n");

              chineseMedicineDao.insert(medicine97);

              ChineseMedicine medicine98=new ChineseMedicine();
              medicine98.setName("乌药");
              medicine98.setSortType("W");
              medicine98.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=42aebc49523d26973ade000f3492d99e/3b292df5e0fe99255e73e8d337a85edf8db17132.jpg");
              medicine98.setData("【中药名】乌药 wuyao。\n" +
                      "\n" +
                      "【别名】天台乌药、台乌、台乌药、矮樟根、鲫鱼姜。\n" +
                      "\n" +
                      "【英文名】Linderae Radix。\n" +
                      "\n" +
                      "【来源】樟科植物乌药Lindera aggregata (Sims) Kosterm.的块根。\n" +
                      "\n" +
                      "【植物形态】常绿灌木或小乔木。根木质，膨大粗壮，略呈念珠状。树皮灰绿色。小枝幼时密被锈色短柔毛，老时平滑无毛；茎枝坚韧，不易断。叶互生，革质，椭圆形至倒卵形，先端渐尖或尾状渐尖，基部圆形或广楔形，全缘，上面绿色，有光泽，除中脉外，均光滑无毛，下面灰白色，被淡褐色长柔毛，后变光滑，基出三脉，极明显；叶柄短有短柔毛。伞形花序腋生，几无总梗；小花梗被毛，簇生多数小花；花单性，雌雄异株，黄绿色；花被广椭圆形，雄花有雄蕊9枚，排成3轮，最内一轮的基部有腺体；雌花有退化雄蕊多枚，子房上位，球形，1室，胚珠1枚。核果近球形，初绿色，成熟后变黑色。花期3～4月，果期9～10月。\n" +
                      "\n" +
                      "【产地分布】生于向阳山坡灌木林中或林缘以及山麓、旷野等处。分布于江苏、安徽、浙江等地。\n" +
                      "\n" +
                      "【采收加工】冬、春二季采挖；以初夏采者粉性大，质量好。挖取后，除去须根，洗净晒干，商品称为“乌药个”。如刮去栓皮，切片，烘干者，称为“乌药片”。\n" +
                      "\n" +
                      "【药材性状】根纺锤形，略弯曲，有的中部收缩成连珠状，称“乌药珠”。长5～15厘米，直径1～3厘米。表面黄棕色或灰棕色，有细纵纹及稀疏的细根痕，有的中有环状裂纹。质极坚硬，不易折断，断面棕白色。气芳香，味微苦、辛，有清凉感。乌药片近圆形，厚1～5毫米或更薄，切面黄白色至淡黄色而微红，有放射性纹理(木射线)和环纹(年轮)。\n" +
                      "\n" +
                      "【性味归经】性温，味辛。归肺经、脾经、肾经、膀胱经。\n" +
                      "\n" +
                      "【功效与作用】顺气、开郁、散寒、止痛。属理气药。\n" +
                      "\n" +
                      "【临床应用】用量3～9克；磨汁或入丸、散。用治气逆胸腹胀痛、宿食不消、反胃吐食、寒疝、脚气、小便频数等。\n" +
                      "\n" +
                      "【药理研究】有增强胃肠活动、止痛、止血、保肝、抗菌、平喘、抗组胺、止血、抗单纯疱疹病毒、抗癌等药理作用，对离体平滑肌有兴奋和抑制的双重作用。\n" +
                      "\n" +
                      "【化学成分】主要含挥发油成分。含多种倍半萜类成分，有一些属于桉烷的生物碱，有一些属于乌药烷的衍生物；此外，还含有生物碱、乌药醚内酯、乌药内酯、新乌药内酯、乌药烯、α-姜黄烯、乌药醚、双香樟内酯等。\n" +
                      "\n" +
                      "【使用禁忌】气虚及内热证患者禁服，孕妇及体虚者慎服。\n" +
                      "\n" +
                      "【配伍药方】①治气喘：乌药末、麻黄五合，韭菜绞汁一碗，冲末药服即止，不止再服。(《心医集》)\n" +
                      "\n" +
                      "②治男子气厥头痛，妇人气盛头痛及产后头痛：川芎、乌药，上等分。为细末，每服6克，腊茶清调服，或用葱茶汤调服，并食后。(《严氏济生方》)\n" +
                      "\n" +
                      "③治小肠气痛不可忍：乌药(捣碎，酒浸一宿)、良姜、舶上茴香、青皮(去白)各30克。为末。每服6克，以发时热酒调下。(《卫生易简方》)\n" +
                      "\n" +
                      "④治室女月水不调或赤或浊，断续不定，心膈迷闷，腹脏撮痛：乌药60克，当归(切、焙)，蓬莪术(炮)各30克。为细末。每服6克，以温酒调下。(《圣济总录》乌药散)\n" +
                      "\n" +
                      "⑤治小儿慢惊，昏沉或搐：乌药磨水灌之。(《济急仙方》)");

              chineseMedicineDao.insert(medicine98);

              ChineseMedicine medicine99=new ChineseMedicine();
              medicine99.setName("巫山淫羊藿");
              medicine99.setSortType("W");
              medicine99.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=5165c9b43a292df583cea447dd583705/b219ebc4b74543a947adf6571e178a82b80114f2.jpg");
              medicine99.setData("【中药名】巫山淫羊藿 wushanyinyanghuo\n" +
                      "\n" +
                      "【别名】千两金、干鸡筋。\n" +
                      "\n" +
                      "【英文名】Epimedii Wushanensis Herba。\n" +
                      "\n" +
                      "【来源】小檗科植物巫山淫羊藿Epimedium wushanenseT.S.Ying的干燥叶。\n" +
                      "\n" +
                      "【植物形态】多年生常绿草本，植株高50～80厘米。根状茎结节状，粗短。一回三出复叶基生和茎生，叶片革质，小叶披针形至狭披针形，长9～23厘米，宽1.8～4 5厘米，叶背面具柔毛或无毛，叶面无毛，先端渐尖或长渐尖，边缘具刺齿.基部心形，顶生小叶基部具均等的圆形裂片，侧生小叶基部的裂片偏斜，内边裂片小，圆形，外边裂片大，三角形，渐尖，上面无毛，背面被绵毛或秃净，叶缘具刺锯齿，花茎具2枚对生叶。圆锥花序顶生，长15～30厘米，偶达50厘米，具多数(25～70)花朵，花序轴无毛，花梗长1～2厘米，疏被腺毛或无毛，花淡黄色。直径选3.5厘米，萼片2轮，外萼片近圆形，长2～5mm，宽1.5～3毫米，内萼片阔椭圆形，长3～15毫米，宽1.5～8毫米。先端钝，花瓣呈角状距，淡黄色。向内弯曲，基部浅杯状.有时基部带紫色，长于内侧萼片，长0.6～2厘米，雄蕊长约5毫米，花丝长1～1.5毫米，花药长约4毫米，瓣裂，裂片外卷，雌蕊长约5毫米，子房斜圆柱状，含胚珠10～12枚。蒴果长约1.5厘米。花期4～5月。果期5～6月。\n" +
                      "\n" +
                      "【产地分布】生于海拔300~1700米的林下、灌丛、草丛或石缝中。分布于湖北、广西、四川、贵州。\n" +
                      "\n" +
                      "【采收加工】夏、秋季茎叶茂盛时采收，除去杂质，晒干或阴干。\n" +
                      "\n" +
                      "【药材性状】为二回三出复叶，小叶片披针形至狭披针形，长9～23厘米，宽1.8～4.5厘米，先端渐尖或长渐尖，边缘具刺齿，侧生小叶基部的裂片偏斜，内边裂片小，圆形，外边裂片大，三角形，渐尖。下表面被绵毛或秃净。近革质。气微，味微苦。\n" +
                      "\n" +
                      "【性味归经】性温，味辛、甘。归肝经、肾经。\n" +
                      "\n" +
                      "【功效与作用】补肾阳、强筋骨、祛风湿。属补虚药下属分类的补阳药。\n" +
                      "\n" +
                      "【临床应用】用量3~9克，煎汤服。用治肾阳虚衰，阳痿遗精，筋骨痿软，风湿痹痛，麻木拘挛，绝经期眩晕。\n" +
                      "\n" +
                      "【主要成分】含巫山淫羊藿黄酮苷。\n" +
                      "\n" +
                      "【使用禁忌】阴虚而相火易动者禁服。");

              chineseMedicineDao.insert(medicine99);

              ChineseMedicine medicine100=new ChineseMedicine();

              medicine100.setName("无根藤");
              medicine100.setSortType("W");
              medicine100.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=18a38b4851b5c9ea76fe0bb1b450dd65/c8177f3e6709c93d5cf7b3b4953df8dcd100546f.jpg");
              medicine100.setData("【中药名】无根藤 wugenteng\n" +
                      "\n" +
                      "【别名】无头藤、无娘藤、无根草。\n" +
                      "\n" +
                      "【英文名】Herba Cassythae\n" +
                      "\n" +
                      "【来源】樟科植物无根藤CassythailiformisL.的全草。\n" +
                      "\n" +
                      "【植物形态】寄生缠绕草本，借盘状吸根附于寄主上。茎线状，绿色或绿褐色，嫩茎被锈色短柔毛。叶退化为微小鳞片。穗状花序长2~5厘米，苞片及小苞片微小，宽卵形，被缘毛。花极小，两性，白色，长不到2毫米，花被裂片6，排成2轮，外轮3枚小，圆形；能育雄蕊9，分3轮。果实小，球形，包藏于花后增大的肉质果托内。花、果期5~12月。\n" +
                      "\n" +
                      "【产地分布】寄生缠绕于山坡灌木丛或疏林中。分布于云南、贵州、广西、广东、海南、湖南、江西、浙江、福建及台湾等地。\n" +
                      "\n" +
                      "【采收加工】全年可采，除去杂质，洗净，切段，晒干或阴干。\n" +
                      "\n" +
                      "【药材性状】呈细长圆柱形，略扭曲，直径1~2.5毫米。表面黄绿色或黄褐色，具细纵纹和黄棕色茸毛，分枝处可见小鳞片，扭曲处常有盘状吸根。花小，排成穗状花序，长2~5厘米。果卵球形，包藏于肉质果托内，顶端开口，直径约4毫米，无柄。质脆，断面皮部纤维性，木质部黄白色。气微，味淡。\n" +
                      "\n" +
                      "【性味归经】性凉，味淡。归经无。\n" +
                      "\n" +
                      "【功效与作用】清热利湿、凉血解毒。属清热药下属分类的清热解毒药\n" +
                      "\n" +
                      "【临床应用】用量9~10克，煎服；外用适量，鲜品捣烂外敷，或煎水洗。用治结膜炎、急性黄疸型肝炎、小儿疳积、水肿、咯血，痈疮、烫伤。\n" +
                      "\n" +
                      "【主要成分】从乙醇提取物中分离出无根藤碱、无根藤定碱、无根藤末丁、无根藤末里丁、六驳碱、月桂若实碱及卫矛醇、鞣质等。\n" +
                      "\n" +
                      "【使用禁忌】孕妇忌服。");

              chineseMedicineDao.insert(medicine100);

              ChineseMedicine medicine101=new ChineseMedicine();
              medicine101.setName("问荆");
              medicine101.setSortType("W");
              medicine101.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=5628bd7184025aafc73f76999a84c001/14ce36d3d539b600bdc8f406ef50352ac65cb779.jpg");
              medicine101.setData("【中药名】问荆 wè；n jīng\n" +
                      "\n" +
                      "【别名】连续草、笔头菜、节节草、猪鬃草。\n" +
                      "\n" +
                      "【英文名】Bottle Brush Herb。\n" +
                      "\n" +
                      "【来源】木贼科植物问荆Equisetum arvense L.的地上部分。\n" +
                      "\n" +
                      "【植物形态】多年生草本，地上茎直立，二型。营养茎在孢子茎枯萎后长出，高15～60厘米，有棱脊6～15条，分枝轮生，叶退化，下部联合成鞘，鞘齿披针形，黑色，边缘灰白色，膜质。孢子茎早春由根茎发出，褐色，肉质，不分枝，鞘长而大。孢子囊穗顶生，孢子叶六角形，盾状着生，下生孢子囊6～8个，孢子成熟时孢子茎即枯萎。\n" +
                      "\n" +
                      "【产地分布】生于海拔600～2300米的田边、沟旁。分布于陕西、河北及东北、江西、安徽、贵州、四川等地。\n" +
                      "\n" +
                      "【采收加工】夏季割取地上部分，晒干。\n" +
                      "\n" +
                      "【药材性状】茎呈圆柱形，有棱脊6～16条，节上轮生小枝，小枝实心，有 棱脊3~4条，通常不再分枝或有时可再分枝；叶退化，下部联合成鞘，鞘齿披针形，黑色，边缘灰白色，膜质。生孢子囊穗的茎，早春生出，无叶绿素，棕褐色，顶端生有长圆形的孢子囊穗，有总梗，钝头，黑色。孢子叶六角形，盾状着生，螺丝排列，边缘着生长形孢子囊。\n" +
                      "\n" +
                      "【性味归经】性平，味苦。归肺经、胃经、肝经。\n" +
                      "\n" +
                      "【功效与作用】清热，凉血，止咳，利尿。属清热药下属分类的清热凉血药。\n" +
                      "\n" +
                      "【临床应用】用量5~15克，水煎服。主治鼻衄、月经过多、肠出血、咯血、|痔出血以及咳嗽气喘、小便不利、水肿、淋病等。\n" +
                      "\n" +
                      "【主要成分】全草皂苷、黄酮类、三萜及甾体类、生物碱及大量硅酸、&beta；-谷甾醇、维生素C和胡萝卜素等。\n" +
                      "\n" +
                      "【使用禁忌】尚不明确。\n" +
                      "\n");

              chineseMedicineDao.insert(medicine101);

              ChineseMedicine medicine102=new ChineseMedicine();
              medicine102.setName("五指毛桃");
              medicine102.setSortType("W");
              medicine102.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=8dab89ec2f1f95cab2f89ae4a87e145b/38dbb6fd5266d016138512ba9d2bd40734fa3587.jpg");
              medicine102.setData("【中药名】五指毛桃 wuzhimaotao\n" +
                      "\n" +
                      "【别名】五指毛桃、五爪龙、五指牛奶、土北芪。\n" +
                      "\n" +
                      "【英文名】Radix Fici Hirtae。\n" +
                      "\n" +
                      "【来源】桑科植物粗叶榕Ficus hirta Vahl.的根。\n" +
                      "\n" +
                      "【植物形态】灌木，落叶小乔木。全株多少被锈色或黄色刚毛和贴伏的硬毛；嫩枝圆柱状，常中空。叶互生，纸质，多型，为长圆状披针形或卵状椭圆形，有时为阔卵形，顶端短尖或渐尖，基部圆或心形，常3~5深裂或浅裂，间有不规则分裂，很少浅波状或不裂，叶缘和裂片边缘有锯齿，两面粗糙，常有凸点；基出脉3~5条，偶有7条，中脉每边有侧脉4~7条；托叶卵状披针形。隐头花序腋生或生于无叶老枝上，几乎全年可见，球形，顶端有脐状凸起，无总花梗。\n" +
                      "\n" +
                      "【产地分布】常生于旷野、山地灌木丛中或疏林中。分布于广东及我国西南部至东南部各地。\n" +
                      "\n" +
                      "【采收加工】全年均可采收。挖取根部，洗净，除去细根，斩短段或切片，晒干。\n" +
                      "\n" +
                      "【药材性状】圆柱形短段或片状。表面灰黄色或黄棕色，有红棕色花斑纹及细密纵皱纹，可见横向皮孔。质坚硬，不易折断。横切面皮部薄而韧，易剥离，富纤维性，似麻皮样；木部宽大，淡黄白色，有较密的同心性环纹。纵切面木纹顺直。气微香，有类似油臆气，味微甘。\n" +
                      "\n" +
                      "【性味归经】性微温，味甘。归肺经、脾经、胃经、大肠经、肝经。\n" +
                      "\n" +
                      "【功效与作用】益气健脾、祛痰平喘、行气化湿、舒筋活络。属补虚药下属分类的补气药。\n" +
                      "\n" +
                      "【临床应用】用量15~30克，煎服。用治肺虚痰喘咳嗽、脾胃气虚之肢倦无力、食少腹胀、脾虚水肿、带下、风湿痹痛、腰腿痛；也用治慢性肝炎、肝硬化腹水。\n" +
                      "\n" +
                      "【主要成分】含挥发油、氨基酸、糖类、甾类、香豆精、生物碱及有机酸等。水煎剂、乙醇提取物等对小鼠有较好的镇咳、祛痰作用；对金黄色葡萄球菌、甲型链球菌有较好的抑菌作用。\n" +
                      "\n" +
                      "【使用禁忌】虚寒者禁服。");
              chineseMedicineDao.insert(medicine102);

              ChineseMedicine medicine103=new ChineseMedicine();
              medicine103.setName("乌桕子");
              medicine103.setSortType("W");
              medicine103.setMedicineImageUrl("http://img.bimg.126.net/photo/kCywoiEox4EN-mc1MujCfQ==/2034219656688782725.jpg");
              medicine103.setData("【药名】乌桕子 wū jiù； zǐ\n" +
                      "\n" +
                      "【别名】乌茶子。\n" +
                      "\n" +
                      "【来源】大戟科植物乌桕Sapium sebiferum(L.)Roxb.的种子。\n" +
                      "\n" +
                      "【植物形态】落叶乔木，高3~15米。树皮灰褐色，深纵裂，皮孔细点状；枝细长，灰白色。叶互生；叶片纸质，菱形至菱状卵形，长宽3~9厘米，顶端骤尖或尾状长尖，基部宽楔形或近圆形，全缘，上面绿色，稍有光泽，下面初时粉白色，后渐变黄色，秋时变红赭色；叶柄长2~7厘米，上部接近叶片基部具2圆形腺体。花单生，雌雄同株，无花瓣及花盘，穗状花序顶生，初时全部为雄花；后于花序基部生1~4朵雌花；雄花小，萼杯状，3浅裂，雄蕊2，稀3，花丝分离；雌花具短梗，着生处两侧各有近肾形腺体1，花萼3深裂，子房光滑，3室，花柱3。蒴果木质梨状球形，具尖头。种子近圆形黑色，外被白色蜡层。花期6~7月，果期10~11月。\n" +
                      "\n" +
                      "【产地分布】生于郊野、路边、溪边、低山杂木林中；也有栽培。分布于广东、广西、江苏、山东、安徽、江西等地。\n" +
                      "\n" +
                      "【采收加工】秋末、初冬采摘，鲜用或晒干。\n" +
                      "\n" +
                      "【药材性状】椭圆形或半球形，长5～8毫米，直径4～6毫米，表面具白色或灰白色较厚的蜡层，种脊条形，稍隆起，基部有小种阜。除去蜡层，种皮黑褐色或棕褐色，坚硬，不易压碎。胚乳肥厚，类白色，富含脂肪油。气味均淡。\n" +
                      "\n" +
                      "【性味归经】性凉，味甘。\n" +
                      "\n" +
                      "【功效与作用】杀虫、利水、通便。\n" +
                      "\n" +
                      "【临床应用】用量3~5克，水煎服，外用适量，煎水洗或捣烂敷患处。外用可治脓疮、疥癣、湿疹、皮肤皲裂。内服可与逐水攻下药配伍用于水肿、便秘。老弱体虚、孕妇及胃溃疡病患者忌服。中毒症状为恶心、呕吐、腹痛、腹泻等，严重者尚可出现四肢发麻、口唇发麻、面色苍白，心慌、胸闷等。\n" +
                      "\n" +
                      "【主要成分】乌桕子油中含有强光学活性的三脂酸甘油酯。还含3&alpha；-羟基-21&alpha；H-何帕-28-烯、21&alpha；H-何帕-28-烯-3-酮。\n" +
                      "\n" +
                      "【使用禁忌】有毒。");

              chineseMedicineDao.insert(medicine103);

              ChineseMedicine medicine104=new ChineseMedicine();

              medicine104.setName("七叶莲");
              medicine104.setSortType("Q");
              medicine104.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=ef9f4c11f4faaf5190ee89eded3dff8b/aec379310a55b319ca4066424aa98226cffc174f.jpg");
              medicine104.setData("【中药名】七叶莲 qiyelian\n" +
                      "\n" +
                      "【别名】小叶鸭，脚木汉桃叶，手树，七加皮，七叶藤，狗脚蹄。\n" +
                      "\n" +
                      "【英文名】Scandent Schefflera Stem and Leaf。\n" +
                      "\n" +
                      "【药用部位】五加科鹅掌柴属植物鹅掌藤Schefflera arboricola Hayata和密脉鹅掌柴的茎叶。\n" +
                      "\n" +
                      "【植物形态】常绿藤状灌木。茎圆筒形，有细纵条纹；小枝有不规则纵皱纹。掌状复叶互生，托叶在叶柄基部与叶柄合生成鞘状，宿存或与叶柄一起脱落；小叶7～9；小叶片革质，倒卵状长椭圆形，长9～16厘米，宽2.5～4厘米；先端渐尖或急尖，基部渐狭或钝形，全缘。伞形花序集合成长圆锥花序，顶生；总花梗短，疏生星状绒毛，花萼5齿裂；花瓣5～6片，分离，卵形，白色；雄蕊5；子房下位，无花柱。浆果球形，有明显的5棱，橙黄色。\n" +
                      "\n" +
                      "【产地分布】分布于台湾、广东、海南、广西等地。生于山谷或阴湿的疏林中。\n" +
                      "\n" +
                      "【采收加工】全年均可采收，洗净，鲜用或切片晒干。\n" +
                      "\n" +
                      "【药材性状】本品茎呈圆柱形，直径0.5～3厘米，淡棕色，有直线纹；质轻，硬而易折断，断面木质部宽广，中心有髓或中空。掌状复叶，叶柄长，基部扩大，小叶通常7片，故称“七叶莲”，小叶片革质，上面绿色，有光泽，下面淡绿色，侧脉和网脉明显。气微，味微苦涩。以叶多、色绿者为佳。\n" +
                      "\n" +
                      "【性味归经】性温，味辛、微苦。有小毒。无归经。\n" +
                      "\n" +
                      "【功效与作用】祛风止痛，活血消肿。属活血化瘀药下属分类的活血止痛药。\n" +
                      "\n" +
                      "【临床应用】内服：煎汤，9～15克；或泡酒。外用适量，捣烂敷患处。主治风湿痹痛，头痛，牙痛，脘腹疼痛，痛经，产后腹痛，跌打肿痛，骨折，疮肿。\n" +
                      "\n" +
                      "【药理研究】七叶莲可降低血压，切断迷走神经，降压作用不受影响；加强心肌收缩力，剂量加大可出现传导阻滞；对抗组胺和乙酰胆碱引起的气管和回肠收缩；大剂量时兴奋离体妊娠子宫，而对离体非妊娠子宫则呈现抑制作用。此外，还有明显的镇静、镇痛、抗惊厥作用。\n" +
                      "\n" +
                      "【化学成分】含镰叶芹醇( falcarinol)、(E)-β-金合欢烯[(E) -β-farnescene]、植物醇(phytol)和多孔甾醇(poriferasrerol)等。\n" +
                      "\n" +
                      "【使用禁忌】气血虚弱者、孕妇禁服。\n" +
                      "\n" +
                      "【相关药方】①治风湿关节痛：①干七叶莲茎500克。浸酒1000克，浸7天后饮。每次15克。(《广东省惠阳地区中草药》)\n" +
                      "\n" +
                      "②治跌打损伤：七叶莲全株30克。水煎服。或用鲜叶适量捣烂，调酒炒热外敷。(《广西本草选编》)\n" +
                      "\n" +
                      "③治外伤出血：汉桃叶适量，捣烂敷患处。(《广西民间常用草药手册》)");
              chineseMedicineDao.insert(medicine104);

              ChineseMedicine medicine105=new ChineseMedicine();
              medicine105.setName("青礞石");
              medicine105.setSortType("Q");
              medicine105.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545378754&di=8315dd828f3459a653eef4981eaf3782&imgtype=jpg&er=1&src=http%3A%2F%2Fstatic-news.17house.com%2Fweb%2Farticle%2Fa5%2F1%2F20150908_c75e43d8253188f39774q7eZEu4aCoaM.jpg%3F");
              medicine105.setData("【中药名】青礞石 qingmengshi\n" +
                      "\n" +
                      "【别名】礞石。\n" +
                      "\n" +
                      "【英文名】Chloriti Lapis。\n" +
                      "\n" +
                      "【药用部位】变质岩类矿物黑云母片岩Biotite Schist及绿泥石化云母碳酸盐片岩Mica Carbonate Schist by Chloritization的复矿岩。\n" +
                      "\n" +
                      "【产地分布】产于河南、江苏、浙江、湖北、湖南、四川等地。黑云母片岩主产于河南省新乡地区，绿泥石化云母碳酸盐片岩主产于浙江省醇安地区。\n" +
                      "\n" +
                      "【采收加工】矿物采挖后，拣净杂石，除去泥土。\n" +
                      "\n" +
                      "【药材性状】黑云母片岩：为鳞片状或片状集合体。呈不规则扁斜块状或长斜块状，无明显棱角。褐黑色或绿褐色，具玻璃样光泽。质软，易碎，断面呈较明显的层片状。碎粉主为褐黑色鳞片，有似星点样闪光。气微，味淡。\n" +
                      "\n" +
                      "绿泥石化云母碳酸盐片岩：为鳞片状或粒状集合体。呈灰色或灰绿色，夹有银色或淡黄色鳞片，微带珍珠样光泽。体松，易碎，粉末为灰绿色鳞片和颗粒，片状者具星点样闪光。遇盐酸产生气泡，加热后泡腾激烈。气微，味淡。\n" +
                      "\n" +
                      "【性味功能】性平，味甘、咸。归肺经、心经、肝经。\n" +
                      "\n" +
                      "【功效与作用】坠痰下气、平肝镇惊。属安神药下属分类的重镇安神药。\n" +
                      "\n" +
                      "【临床应用】用量3～6克，多入丸、散。用治顽痰胶结、咳嗽喘急、癫痫发狂、烦躁胸闷、惊风抽搐。\n" +
                      "\n" +
                      "【药理研究】本品能促进阳离子交换，产生吸附作用，故有攻痰利水作用。临床可用于治疗食管贲门癌梗阻和癫痫等症。\n" +
                      "\n" +
                      "【化学成分】含二氧化硅、三氧化二铁、氧化铁、二氧化锑、五氧化二磷、氧化锰、氧化钙、氧化镁、氧化钾、氧化钠等。尚含铅、锌、铜、镍、铬、钴、钒等多种无机元素。\n" +
                      "\n" +
                      "【使用禁忌】脾胃虚弱、阴虚燥痰者及孕妇忌服。\n" +
                      "\n" +
                      "【配伍药方】①治中痰并一切痰症：礞石(煅，乳淬)60克，大黄(九蒸)60克，沉香30克，半夏(姜、矾制)60克，陈皮60克，黄芩(酒制)60克。为末，陈米糊为丸。绿豆大。每服9克。(《惠直堂经验方》礞石化痰丸)\n" +
                      "\n" +
                      "②治大人小儿食积成痰，胃实多眩晕者：礞石21克，火硝21克(同研炒，以火硝过性为度)，枳实、木香、白术各60克。共为末，红曲60克为末，打糊丸梧桐子大。每早服9克，白汤下。(《方脉正宗》)\n" +
                      "\n" +
                      "③治百日咳：青礞石27克，白矾9克，芒硝18克。共为细末，分30份，每次1份，每日3次。(《河南省秘验单方集锦》)\n" +
                      "\n" +
                      "④治妇人食癥，块久不消，攻刺心腹疼痛：青礞石(末)0.6克，木香(末)0.3克，硇砂(不夹石者，细研)15克，朱砂(细研)0.3克，粉霜(研)0.6克，巴豆(去皮、心，研，纸裹压去油)0.9克。上药都研令匀，以糯米饭和丸，如绿豆大。每服空心以温酒下二丸，取下恶物为效。(《圣惠方》礞石丸)\n" +
                      "\n" +
                      "⑤治一切积，不问虚实冷热酒食，远年日久：青礞石(研)60克，滑石(研)30克，青黛15克，轻粉9克。上同研匀。每服3克，面汤调下，急以水漱口。未服药前一日，先吃淡粥，至晚服药;候次日晚未动，再服1.5克，取下恶物，更以汤粥将息二三日，如是无积，药随大便下，并无所损忌，次日将息。(《普济方》礞石散)");

              chineseMedicineDao.insert(medicine105);

              ChineseMedicine medicine106=new ChineseMedicine();
              medicine106.setName("青黛");
              medicine106.setSortType("Q");
              medicine106.setMedicineImageUrl("http://file.cnkang.com/cnkfile1/M00/14/2F/o4YBAFlPkgCAPNawAAOTbCgqw8A082.png");
              medicine106.setData("【中药名】青黛 qingdai\n" +
                      "\n" +
                      "【别名】靛、靛花、靛沫、蓝靛、蓝露、青蛤粉、靛沫花。\n" +
                      "\n" +
                      "【英文名】Indigo Naturalis。\n" +
                      "\n" +
                      "【药用部位】蓼科植物蓼蓝(靛蓝)Potygonum tinctorium Ait.的地上部分经加工制得的干燥粉末或团块。\n" +
                      "\n" +
                      "【植物形态】一年生草本，高50~ 80厘米，茎圆柱形，淡紫红色，下部节处生须根。单叶互生。花序穗状，顶生或腋生，花淡红色，密集；花被5深裂。瘦果卵形，具3棱，黑褐色，含种子1枚，包于宿存的花被内。\n" +
                      "\n" +
                      "【产地分布】多栽培或半野生状态。主要分布于我国东北、华北、陕西、山东、湖北、四川、贵州、广西及广东等地。\n" +
                      "\n" +
                      "【采收加工】夏、秋季采收，割取地上茎叶部分，去杂质，切段，晒干。置木桶或缸内，水浸2~3昼夜，至叶自枝条脱落时捞出枝条，加入适量石灰充分搅拌，至浸液由乌绿色转变为深紫红色时，捞出液面产生的蓝色泡沫，晒干。\n" +
                      "\n" +
                      "【药材性状】极细粉末，灰蓝色或深蓝色，质轻易飞扬，黏手黏纸，投水中浮于水面，也有呈多孔性小块。有特殊草腥气，味微酸。\n" +
                      "\n" +
                      "【性味归经】性寒，味咸。归肝经。\n" +
                      "\n" +
                      "【功效与作用】清热凉血、定惊。属清热药下分类的清热凉血药。\n" +
                      "\n" +
                      "【临床应用】用量0.9~1.5克，吞服或冲服。用治温毒、发斑、血热吐衄、胸痛咳血、口疮、痄腮、喉痹、小儿惊痫等。治疗慢性粒细胞白血病用青黛鳖甲汤：青黛、鳖甲各62克，生地32克，龟板、生牡蛎、太子参、生出药、地骨皮各31克，鸡内金、丹皮、赤芍各12克，当归、炮山甲各15克，红花、广木香各9克，甘草3克研末，炼蜜为丸，每丸9克，日服4~6丸。\n" +
                      "\n" +
                      "【主要成分】含靛玉红、靛蓝3%~7%，还含色胺酮、青黛酮、异靛蓝、N-苯基-α萘胺、虫漆蜡醇、β -谷甾醇。靛玉红对小鼠白血病的抑制率较高，对大鼠瓦克瘤256，皮下和腹腔注射200毫克/千克，共6~7天，抑制率分别为47%~52%和50%~58%，灌胃500毫克/千克，抑制率为23%~33%，皮下注射200毫克/千克2次，可延长荷大鼠瓦克瘤腹水型大鼠生存时间43%，对小鼠肺癌亦有明显的抑制作用。");

               chineseMedicineDao.insert(medicine106);

               ChineseMedicine medicine107=new ChineseMedicine();
               medicine107.setName("青风藤");
               medicine107.setSortType("Q");
               medicine107.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=360cbcc735fae6cd18b9a3336eda6441/728da9773912b31b117690d98618367adab4e12c.jpg");
               medicine107.setData("【中药名】青风藤 qingfengteng\n" +
                       "\n" +
                       "【别名】寻风藤、追骨风、汉防己、大风藤、吹风散、黑防己、排风藤、青防己。\n" +
                       "\n" +
                       "【英文名】Sinomenii Caulis。\n" +
                       "\n" +
                       "【药用部位】防己科植物青藤Sinomenium acutum(Thunb.)Rehd. et Wils.的藤茎。\n" +
                       "\n" +
                       "【植物形态】木质落叶藤本。枝条灰褐色，无毛，具细沟纹。叶互生，厚纸质或革质，宽卵形，长7～12厘米，宽5～10厘米，先端渐尖，基部圆形、截形或心形，全缘，基部叶常5～7浅裂，上部叶有时3～5浅裂，上面浓绿色，下面苍白色，近无毛，基出5～7脉；叶柄长6～10厘米。花单性异株，聚伞花序排成圆锥状；雄花序长10～20厘米，花小，雄花萼片6，淡黄色，2轮，花瓣6，淡绿色，雄蕊9～12枚；雌花序长8～12厘米，雌花萼片、花瓣与雄花相似，具退化雄蕊9枚，心皮3，离生。核果扁球形，蓝黑色。花期6～7月，果期8～9月。\n" +
                       "\n" +
                       "【产地分布】生于山区路旁及山坡林缘、沟边。分布于湖北、陕西、江苏、浙江等地。\n" +
                       "\n" +
                       "【采收加工】秋末冬初采割，扎把或切成长段，晒干。\n" +
                       "\n" +
                       "【药材性状】长圆柱形，常微弯曲，长20～70厘米或更长，直径0.5～2厘米。表面绿褐色至棕褐色，有的灰褐色，有细纵纹及皮孔。节部稍膨大，有分枝。体轻，质硬而脆，易折断，断面不平坦，灰黄色或淡灰棕色，皮部窄，木部射线呈放射状排列，髓部淡黄白色或黄棕色。气微，味苦。\n" +
                       "\n" +
                       "【性味归经】性平，味苦、辛。归肝经、脾经。\n" +
                       "\n" +
                       "【功效与作用】祛风湿、通经络、利小便。属祛风湿药下属分类的祛风湿强筋骨药。\n" +
                       "\n" +
                       "【临床应用】用量6～12克，水煎服或水煎冲黄酒服。外用水煎液擦洗痛处。用治风湿痹痛、关节肿胀、麻痹瘙痒。药理试验证实，有镇痛、镇静和抗炎、抗过敏等作用。对心血管系统的作用表现在多方面，如抗心律失常、抗心肌缺血、降血压等。\n" +
                       "\n" +
                       "【药理研究】镇痛、镇静、镇咳，抗炎，降压，免疫抑制，抗心律失常，抗心肌缺血、保护再灌注损伤，阻断神经节及神经肌肉传递，释放组胺，降低心肌的收缩性，抑制肾上腺素诱发的自律性；对胃肠道有兴奋作用，但也有轻度的胃肠不良反应等作用。\n" +
                       "\n" +
                       "【化学成分】主含青藤碱、华青藤碱、尖防己碱、光千金藤碱、N-去甲尖防己碱、双青藤碱等多种生物碱及消旋丁香树脂酚、乙酰齐墩果酸等成分。\n" +
                       "\n" +
                       "【使用禁忌】可出现痛痒、皮疹、头昏头痛、皮肤发红、腹痛、畏寒发热、过敏性紫瘢、血小板减少、白细胞减少等副反应，使用时应予注意。\n" +
                       "\n" +
                       "【配伍药方】①治风湿痹痛：青风藤、红藤各15克。水煎，加酒适量冲服;或青风藤30～60克，上肢痛加桂枝3克，下肢痛加牛膝6克。全身痛三味同用，水煎，加黄酒适量，晚饭后服。(《浙江药用植物志》)\n" +
                       "\n" +
                       "②治关节疼痛：青藤15克，红藤15克。水煎服，每日1次。酒为引。(《陕西中草药》)");

                 chineseMedicineDao.insert(medicine107);

                 ChineseMedicine medicine108=new ChineseMedicine();
                 medicine108.setName("秦皮");
                 medicine108.setSortType("Q");
                 medicine108.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=0753a6b6c0fdfc03f175ebeab556ecf1/3c6d55fbb2fb4316a28d2a3223a4462308f7d3ec.jpg");
                 medicine108.setData("【中药名】秦皮 qinpi\n" +
                         "\n" +
                         "【别名】蜡树皮、秦白皮、梣皮。\n" +
                         "\n" +
                         "【英文名】Fraxini Cortex\n" +
                         "\n" +
                         "【药用部位】木犀科植物尖叶白蜡树Fraxinus szaboana Lingelsh.的枝皮或干皮。\n" +
                         "\n" +
                         "【植物形态】落叶乔木，高10～12米。树皮灰褐色，较平滑，老时浅裂。奇数羽状复叶，对生；叶轴具柔毛及腺毛，后渐脱落；小叶3～5，卵形或长卵形，先端长渐尖，边缘有锐锯齿，上面光滑，下面沿主脉及侧脉被柔毛；花与叶同时或稍后开放，雄性与两性异株，圆锥花序生于嫩枝顶端及叶腋；花小，花萼钟状，不规则4～5裂；无花冠；雄蕊2枚，外露；柱头2深裂；常钳形内弯。翅果长倒卵形或倒披针形。花期4～5月，果期8～9月。\n" +
                         "\n" +
                         "【产地分布】均生于山坡路边或林中。分布于陕西、甘肃、湖北、四川等地。\n" +
                         "\n" +
                         "【采收加工】春、秋季剥取枝皮或干皮，晒干。\n" +
                         "\n" +
                         "【药材性状】枝皮：卷筒状或槽状，长10～60厘米，厚1.5～3毫米。外表面灰白色、灰棕色至黑棕色或相间呈斑状，平坦或稍粗糙，并有灰白色圆点状皮孔及细斜皱纹，有的具分枝痕。内表面黄白色或棕色，平滑。质硬而脆，断面纤维性，黄白色。无臭，味苦。干皮：长条状块片，厚3～6毫米。外表面灰棕色，具龟裂状沟纹及红棕色圆形或横长的皮孔。质坚硬，断面纤维性较强。\n" +
                         "\n" +
                         "【性味归经】性寒，味苦、涩。归肝经、胆经、大肠经。\n" +
                         "\n" +
                         "【功效与作用】清热燥湿、收涩、明目。属清热药下属分类的清热燥湿药。\n" +
                         "\n" +
                         "【临床应用】用量6～12克，水煎服；外用适量，煎洗患处。用治热痢、泄泻、赤白带下、目赤肿痛、目生翳膜。\n" +
                         "\n" +
                         "【药理研究】抗菌、抗炎、镇痛，可用于“痛风”性关节炎，影响花生四烯酸代谢；还有止咳、祛痰、平喘作用。\n" +
                         "\n" +
                         "【化学成分】含七叶树苷、秦皮苷、七叶树内酯等成分，并分得微量成分N-苯基-2-苯胺，该成分首次从木樨科中得到。另含马栗树皮苷、马栗树皮素、秦皮素、秦皮甲素、秦皮乙素、熊果酸、胡萝卜苷等成分。\n" +
                         "\n" +
                         "【使用禁忌】脾胃虚寒者禁服。\n" +
                         "\n" +
                         "【配伍药方】①治急性菌痢：秦皮、苦参各12克，炒莱菔子、广木香各9克。共为细末，开水调服，每次9～12克，每日3～4次。(《国医论坛》1986，(2)，52)\n" +
                         "\n" +
                         "②治慢性细菌性痢疾：秦皮12克，生地榆、椿皮各9克。水煎服。(《河北中药手册》)\n" +
                         "\n" +
                         "③治急性肝炎：秦皮9克，茵陈、蒲公英各30克，黄柏9克，大黄9克。水煎服。(《山西中草药》)\n" +
                         "\n" +
                         "④治麦粒肿，大便干燥：秦皮9克，大黄6克。水煎服。孕妇忌服。(《河北中药手册》)");

                 chineseMedicineDao.insert(medicine108);

                 ChineseMedicine medicine109=new ChineseMedicine();
                 medicine109.setName("瞿麦");
                 medicine109.setSortType("Q");
                 medicine109.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=9ccd15cfff039245b5b8e95de6fdcfa7/54fbb2fb43166d221049f696452309f79052d2f2.jpg");
                 medicine109.setData("【中药名】瞿麦 qumai\n" +
                         "\n" +
                         "【别名】石竹子花、十样景花、洛阳花、大兰、南天竺草、麦句姜。\n" +
                         "\n" +
                         "【英文名】Dianthi Herba。\n" +
                         "\n" +
                         "【药用部位】石竹科植物瞿麦Dianthus superbus L.或石竹Dianthus chinensis L.的地上部分。\n" +
                         "\n" +
                         "【植物形态】多年生草本，高30～50厘米。茎簇生，直立，基部稍呈匍匐状，上部分枝，圆柱形，下部节间较短，光滑，全体呈白绿色。单叶对生，无柄，叶片宽披针形，先端渐尖，基部狭窄成短鞘围抱节上，边缘有细齿或全缘。夏季开白色或红色花，花单生或数朵簇生成聚伞花序，萼下小苞片呈卵形，长约为萼管的1/4；花瓣5，先端剪裂至中部以下成丝状；雄蕊10枚，子房上位，1室，花柱2。蒴果包于宿存的萼管内，先端4裂。花期6～9月，果期7～10月。\n" +
                         "\n" +
                         "【产地分布】生于山坡疏林边及溪边草丛中，分布于全国各地。\n" +
                         "\n" +
                         "【采收加工】夏、秋季花、果期采割，除去杂质，干燥。\n" +
                         "\n" +
                         "【药材性状】茎圆柱形，上部有分枝，表面淡绿色或黄绿色，光滑无毛，节明显，略膨大，断面中空。叶对生，多皱缩，展平叶片呈条形至条状披针形。枝端具花和果实，花萼筒状，苞片4~6，宽卵形，长约为萼筒的1/4；花瓣棕紫色或棕黄色，卷曲，先端深裂成丝状。蒴果长筒形，与宿萼等长。种子细小，多数。无臭，味淡。\n" +
                         "\n" +
                         "【性味归经】性寒，味苦。归心经、小肠经。\n" +
                         "\n" +
                         "【功效与作用】利尿通淋、破血通经。属利水渗湿药下属分类的利尿通淋药。\n" +
                         "\n" +
                         "【临床应用】用量9～15克，水煎服或入丸散；外用研末调敷。用治热淋、石淋、小便不通、淋沥涩痛、月经闭止。\n" +
                         "\n" +
                         "【药理研究】药理研究表明，有利尿、降血压的作用；对离体蛙心、兔心有明显抑制作用；尚有微溶血作用；对金黄色葡萄球菌、大肠杆菌、伤寒杆菌、福氏痢疾杆菌、绿脓杆菌有抑制作用。\n" +
                         "\n" +
                         "【化学成分】含皂苷、糖类维生素A样物质。全草尚含少量生物碱、金圣草素-6-顺式-α-D-吡喃葡萄糖苷、金圣草素-6-反式-α-D-吡喃葡萄糖苷、茴香脑、瞿麦皂苷、瞿麦吡喃酮苷、水杨酸甲酯等成分。\n" +
                         "\n" +
                         "【使用禁忌】下焦虚寒，小便不利以及妊娠、新产者禁服。\n" +
                         "\n" +
                         "【配伍药方】①治目赤肿痛：瞿麦、菊花各9克。水煎服。(《陕甘宁青中草药选》)\n" +
                         "\n" +
                         "②治血淋：鲜瞿麦30克，仙鹤草15克，炒栀子9克，甘草梢6克。煎服。(《安徽中草药》)\n" +
                         "\n" +
                         "③治石淋，小便涩痛不可忍：瞿麦30克，车前子45克，葳蕤30克，滑石45克。上件药，捣粗罗为散。每服12克，以水一中盏，煎至六分，去滓，每于食前温服。(《圣惠方》)\n" +
                         "\n" +
                         "④治妇人经血不通：瞿麦、木通、大黄各60克。上为细末。酒一盏煎至七分，温服，食前。(《普济方》)\n" +
                         "\n" +
                         "⑤治血瘀经闭：瞿麦、丹参、益母草各15克，赤芍、香附各9克，红花6克。煎服。(《安徽中草药》)\n" +
                         "\n" +
                         "⑥治妇女外阴糜烂，皮肤湿疮：瞿麦适量。煎汤洗之，或为细面撒患处。(《河北中药手册》)\n" +
                         "\n" +
                         "⑦治食管癌、直肠癌：瞿麦鲜品30～60克(干品18～30克)。水煎服。(《陕甘宁青中草药选》)");

                 chineseMedicineDao.insert(medicine109);

                 ChineseMedicine medicine110=new ChineseMedicine();
                 medicine110.setName("青蒿");
                 medicine110.setSortType("Q");
                 medicine110.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=acf68fd190ef76c6c4dff379fc7f969f/b03533fa828ba61ee1f895df4134970a304e592f.jpg");
                 medicine110.setData("【中药名】青蒿 qinghao\n" +
                         "\n" +
                         "【别名】草蒿、臭蒿、臭青蒿、香丝草、酒饼草。\n" +
                         "\n" +
                         "【英文名】Artemisiae Annuae Herba。\n" +
                         "\n" +
                         "【药用部位】菊科植物黄花蒿Artemisia annuaL.的地上部分。\n" +
                         "\n" +
                         "【植物形态】一年生草本。茎直立，具纵纹，多分枝，光滑无毛。叶互生，无毛，常为3回羽状分裂。裂片短而细，先端尖，表面深绿色，有极小的粉末状短柔毛，背面淡绿色，具细小的毛或粉末状腺状斑点；叶轴两侧具狭翅；叶柄基部稍扩大抱茎；茎上部的叶向上逐渐细小呈线形，无柄，基生叶在开花时凋谢。头状花序细小球形，具细软短梗，排列成圆锥状；总苞的苞片2～3层，无毛，外层卵形，绿色；内层椭圆形，边缘膜质，背面中央为绿色。花托长椭圆形，无毛；花皆为管状花，黄色；雌花较少，围于外层，雌蕊1枚，柱头2裂，呈长叉状开展；内为两性花，花冠先端分裂；雄蕊5枚，聚药，药先端呈三角形，基部两侧下延呈一短尖。瘦果椭圆形。花期8～10月，果期10～11月。\n" +
                         "\n" +
                         "【产地分布】生于山坡草地、荒地、河岸、路旁、村边。分布于广东等地。\n" +
                         "\n" +
                         "【采收加工】夏、秋季花盛开或结果时采收。割取地上部分，除去老茎，阴干或晒干。\n" +
                         "\n" +
                         "【药材性状】茎圆柱形，上部多分枝；表面黄绿色或棕黄色，具纵棱线；质略硬，易折断，断面中部有髓。叶互生，暗绿色或棕绿色，卷缩易碎，完整者展平后为3回羽状深裂，裂片及小裂片矩圆形或长椭圆形，两面被短毛。气香特异，味微苦。\n" +
                         "\n" +
                         "【性味归经】性寒，味苦、辛。归胆经、肝经。\n" +
                         "\n" +
                         "【功效与作用】清热解毒、除骨蒸、截疟。属清热药下分类的清虚热药。\n" +
                         "\n" +
                         "【临床应用】用量6～12克，煎服，入煎剂宜后下。用治温病、暑热、骨蒸劳热、暑邪发热、疟疾、痢疾、阴虚发热、疮痒、湿热黄疸等。\n" +
                         "\n" +
                         "【药理研究】有抗菌、抗病毒、抗寄生虫、抗肿瘤、解热作用；调节机体免疫功能；可减慢心率，抑制心肌收缩力，降低冠脉流量，降低血压，且有一定抗心律失常作用；尚能保护肝脏，防护辐射，缩短戊巴比妥睡眠时间等。\n" +
                         "\n" +
                         "【化学成分】主含多种倍半萜内酯、黄酮类、香豆素类、挥发油等。另含青蒿素、青蒿醇、青蒿酸、青蒿酸甲酯、槲皮素、小茴香酮、蒿属香豆精等成分。\n" +
                         "\n" +
                         "【使用禁忌】产后血虚，内寒作泻，及饮食停滞泄泻者，勿用。\n" +
                         "\n" +
                         "【配伍药方】①治中暑：青蒿嫩叶捣烂，手捻成丸，黄豆大。新汲水吞下，数丸立愈。(《本草汇言》)\n" +
                         "\n" +
                         "②治暑毒热痢：青蒿叶30克，甘草3克。水煎服。(《圣济总录》)\n" +
                         "\n" +
                         "③治温疟痰甚，但热不寒：青蒿60克(童子小便浸，焙)，黄丹15克为末。每服6克，白汤调下。(《仁存堂经验方》)\n" +
                         "\n" +
                         "④治鼻中衄血：青蒿捣汁服之，并塞鼻中。(《卫生易简方》)\n" +
                         "\n" +
                         "⑤治聤耳脓血出不止：青蒿捣末，绵裹纳耳中。(《圣惠方》)\n" +
                         "\n" +
                         "⑥治牙齿肿痛：青蒿一握，煎水漱之。(《济急仙方》)\n" +
                         "\n" +
                         "⑦治瘊子：新汲水按青蒿汁，调蛤粉敷之。(《百一选方》)\n" +
                         "\n" +
                         "⑧治蜂螫人：嚼青蒿敷之。(《肘后方》)");

                 chineseMedicineDao.insert(medicine110);

                 ChineseMedicine medicine111=new ChineseMedicine();
                 medicine111.setName("千里光");
                 medicine111.setSortType("Q");
                 medicine111.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=dd7c5da4034f78f0940692a118586130/29381f30e924b8998f90bac86e061d950b7bf696.jpg");
                 medicine111.setData("【中药名】千里光 qianliguang\n" +
                         "\n" +
                         "【别名】九里明、千里急、眼明草、九龙光、百花草。\n" +
                         "\n" +
                         "【英文名】Climbing Groundsel Herb\n" +
                         "\n" +
                         "【药用部位】菊科植物千里光Senecio scandens Buch.-Ham.的全草。\n" +
                         "\n" +
                         "【植物形态】多年生攀援状亚灌木。茎木质，枝具线纹，稍呈“之”字形，被脱落性微毛。叶互生，卵形或卵状披针形，不分裂或基部有2～4对裂片，边缘具不规则齿或微波状至全缘，两面被细微毛。头状花序排成疏松、开展的伞房花序式，总苞片10～12枚，狭长圆形，基部有极小的苞片数枚，小花异型，缘花为舌状花，雌性，黄色，顶端3齿裂，盘花两性，管状，顶端5裂。瘦果圆柱形，冠毛白色，细软。花期10月至翌年3月，果期2～5月。\n" +
                         "\n" +
                         "【产地分布】生于山坡、路旁、林边、山脚疏林下、沟边草丛中。分布于广东、广西、浙江等地。\n" +
                         "\n" +
                         "【采收加工】9～10月采收，割取地上部分，洗净，鲜用或晒干。\n" +
                         "\n" +
                         "【药材性状】茎圆柱形，表面棕黄色；质坚硬，断面髓部发达，白色。叶多皱缩，破碎，完整叶呈椭圆状三角形或卵状披针形，基部戟形或截形，边缘有不规则缺刻，暗绿色或灰棕色，质脆，偶见枝梢带黄色头状花序。瘦果成熟时则露出白色冠毛。气微，味苦。\n" +
                         "\n" +
                         "【性味归经】性寒，味苦。归肝经、肺经。\n" +
                         "\n" +
                         "【功效与作用】清热解毒、凉血消肿、清肝明目、杀虫止痛。属清热药下分类的清热解毒药。\n" +
                         "\n" +
                         "【临床应用】用量9～15克，煎服，鲜品50克，外用：煎水洗、捣敷或熬膏涂。用治风热感冒、流行性感冒、上呼吸道感染、急性扁桃体炎、咽喉肿痛、肺炎、大叶性肺炎等。\n" +
                         "\n" +
                         "【药理研究】千里光煎剂具有抗菌、抑制钩端螺旋体生长、抗滴虫等作用，毒性小，煎剂灌服不能测出小鼠的半数致死量，对人阴道滴虫有一定的抑制作用，尤其对金黄色葡萄球菌、伤寒杆菌、甲型副伤寒杆菌、乙型副伤寒杆菌、志贺痢疾杆菌、鲍氏痢疾杆菌、宋内痢疾杆菌抗菌作用相当强。其所含酚酸对流感杆菌、肺炎链球菌、甲型链球菌、卡他球菌、变形杆菌、金黄色葡萄球菌等均有抑菌作用。\n" +
                         "\n" +
                         "【化学成分】主要含千里光宁碱、千里光菲林碱、羽扇烯酮、齐墩果烷、β-谷固醇、胡萝卜苷、生物碱、对羟基苯乙酸、金丝桃苷、蒙花苷、齐墩果醇、槲皮素、消旋丁香脂素、大黄素、绿原酸、咖啡酸等成分。\n" +
                         "\n" +
                         "【使用禁忌】中寒泄泻者勿服。\n" +
                         "\n" +
                         "【配伍药方】①治风热感冒：鲜千里光全草30克，六角仙(爵床)、野菊鲜全草各30。水炖。分三次服，每日1剂。(《常用青草药选编》)\n" +
                         "\n" +
                         "②治咽喉肿痛：千里光干全草15克，元参9克，蚤休9克，桔梗6克，甘草3克。水煎服。(《福州中草药临床手册》)\n" +
                         "\n" +
                         "③治疮痈溃烂：千里光、半边莲、犁头草各适量。共捣烂，敷患处。(《广西民间常用中草药手册》)\n" +
                         "\n" +
                         "④治梅毒：千里光30克，土茯苓60克。水煎浓缩成膏，外搽。(《恩施中草药手册》)\n" +
                         "\n" +
                         "⑤治急性泌尿系感染：千里光、穿心莲各30克。煎服。(《安徽中草药》)\n" +
                         "\n" +
                         "⑥治烫火伤：千里光8份，白及2份。水煎浓汁，外搽。(《江西中草药》)\n" +
                         "\n" +
                         "⑦治痔疮：千里光、青鱼胆草各250克。加水煎成浓汁，搽患处。(《湖南农村常用中草药手册》)\n" +
                         "\n" +
                         "⑧治慢性湿疹：千里光、杉树叶、黄菊花、金银花各适量。煎水内服并外洗。(江西《草药手册》)");

                 chineseMedicineDao.insert(medicine111);
                 ChineseMedicine medicine112=new ChineseMedicine();
                 medicine112.setName("牵牛子");
                 medicine112.setSortType("Q");
                 medicine112.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=987b0acfb11c8701c2bbbab44616f54a/b3fb43166d224f4a96acc7380bf790529822d1e0.jpg");
                 medicine112.setData("【中药名】牵牛子 qianniuzi\n" +
                         "\n" +
                         "【别名】二丑、黑丑、白丑、草金铃、金铃、黑牵牛、白牵牛。\n" +
                         "\n" +
                         "【英文名】Pharbitidis Semen。\n" +
                         "\n" +
                         "【药用部位】旋花科植物圆叶牵牛Pharbitis purpurea(L.)Voigt的成熟种子。\n" +
                         "\n" +
                         "【植物形态】一年生缠绕草本。茎左旋，长2米以上，多分枝，被短毛。叶互生；具长叶柄；叶片心状卵形，长3～6厘米，常3裂至中部，呈戟形，先端急尖，基部心形，两面均被伏生毛。夏季开花，花1～3朵腋生，小花梗极短，总梗一般较叶柄短；萼5深裂，裂片条状披针形，先端长尖，基部被长毛，外展；花冠漏斗状，形似喇叭，蓝色、紫色或白色，边缘5浅裂，早晨开放，日中渐萎；雄蕊5枚，不等长，花丝基部有毛，子房3室，每室有2胚珠。蒴果球形，基部有外层或反卷的宿萼。种子3棱卵状；花色浅的种子黄褐色，入药称“白丑”，花色深的种子多黑褐色，入药称“黑丑”。花、果期夏秋季。\n" +
                         "\n" +
                         "【产地分布】生于山坡灌木林中或住宅旁；多栽培。全国各地均产。\n" +
                         "\n" +
                         "【采收加工】秋末果实成熟、果壳未开裂时采割植株，晒干，打下种子，除去杂质。\n" +
                         "\n" +
                         "【药材性状】橘瓣状。长4～8毫米，宽3～5毫米。表面灰黑色或淡黄白色。背面有1条浅纵沟，腹面棱线的下端处有一点状种脐，微凹。质硬，横切面可见淡黄色或黄绿色皱缩折叠的子叶，微显油性。无臭，味辛、苦，有麻感。\n" +
                         "\n" +
                         "【性味归经】性寒，味苦。归肺经、肾经、大肠经。\n" +
                         "\n" +
                         "【功效与作用】泻水通便、消痰涤饮、杀虫攻积。属泻下药下属分类的润下药。\n" +
                         "\n" +
                         "【临床应用】用量3～6克，水煎服。用治水肿胀满、二便不通、痰饮积聚、气逆喘咳、虫积腹痛、蛔虫、绦虫病。\n" +
                         "\n" +
                         "【药理研究】具有泻下、利尿、兴奋平滑肌、驱虫等作用。\n" +
                         "\n" +
                         "【化学成分】含牵牛树脂苷(又称牵牛子素)，是牵牛子泻下的有效成分。另含牵牛子苷、巴豆酸、裸麦角碱、赤霉素、亚油酸、牵牛子酸C、咖啡酸等成分。\n" +
                         "\n" +
                         "【使用禁忌】孕妇禁服，体质虚弱者慎服。不宜多服、久服，以免引起头晕头痛，呕吐，剧烈腹痛腹泻，心率加快，心音低钝，语言障碍，突然发热，血尿，腰部不适，甚至高热昏迷，四肢冰冷，口唇发绀，全身皮肤青紫，呼吸急促短浅等中毒反应。不宜与巴豆、巴豆霜同用。\n" +
                         "\n" +
                         "【配伍药方】①治水肿：牵牛子末之。水服方寸匕，日一，以小便利为度。(《千金要方》)\n" +
                         "\n" +
                         "②治停饮肿满：牵牛子末120克，茴香30克(炒)或加木香30克。上为细末。以生姜自然汁调3～6克，临卧服。(《儒门事亲》禹功散)\n" +
                         "\n" +
                         "③治风热赤眼：牵牛子为末，调葱白汤，敷患处。(《泉州本草》)\n" +
                         "\n" +
                         "④治一切虫积：牵牛子60克(炒，研为末)，槟榔30克(微炒)，使君子肉50个(微炒)。俱为末。每服6克，砂糖调下，小儿减半。(《永类钤方》)");

                chineseMedicineDao.insert(medicine112);

                ChineseMedicine medicine113=new ChineseMedicine();
                medicine113.setName("苘麻子");
                medicine113.setSortType("Q");
                medicine113.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C2%2C800%2C528%3Bc0%3Dbaike92%2C5%2C5%2C92%2C30/sign=660e300fad86c9171c4c0879f40d5cfd/b7fd5266d016092458dc76ecde0735fae7cd3448.jpg");
                medicine113.setData("【中药名】苘麻子 qingmazi\n" +
                        "\n" +
                        "【别名】苘实、空麻子、磨盘树子、冬葵子。\n" +
                        "\n" +
                        "【英文名】Abutili Semen。\n" +
                        "\n" +
                        "【药理研究】锦葵科植物苘麻Abutilon theophrastii Medic.的成熟种子。\n" +
                        "\n" +
                        "【植物形态】一年生草本，高1～2米，栽培的可达3～4米。茎直立，上部分枝，全株被星状毛。单叶互生，圆心形，长7～18厘米，宽与长几相等，先端渐尖，基部心形，边缘具圆锯齿，两面密被星状毛，叶脉掌状，6～7出；叶柄8～18厘米，密被星状毛。花单生于叶腋，花梗长1～3厘米；花萼绿色，下部呈管状，上部5裂，裂片卵圆形，先端渐尖，长约8毫米；花瓣5，黄色，长约l厘米，阔倒卵形；雄蕊多数，雄蕊筒甚短；雌蕊心皮15～ 20，轮状排列。蒴果半球形，直径约2厘米，分果爿15～ 20，顶端有二长芒，外被粗毛。种子1至数粒，三角状黑肾形，黑褐色。花期7～8月，果期9～10月。\n" +
                        "\n" +
                        "【产地分布】生于路旁、田野、荒地、堤岸上；或为栽培。分布全国各地。\n" +
                        "\n" +
                        "【采收加工】秋季果实成熟时采收，晒干，脱粒，扬净。\n" +
                        "\n" +
                        "【药材性状】三角状肾形，长3.5～6毫米，宽2.5～4.5毫米，厚1～2毫米。表面灰黑色或暗褐色，有白色稀疏茸毛，凹陷处有类椭圆状种脐，淡棕色，四周有放射状细纹。种皮坚硬，子叶2，重叠折曲，富油性。气微，味淡。\n" +
                        "\n" +
                        "【性味归经】性凉，味苦。归大肠经、小肠经。\n" +
                        "\n" +
                        "【功效与作用】清热解毒、利水消肿、通乳、退翳。属利水渗湿药下分类的利水消肿药。\n" +
                        "\n" +
                        "【临床应用】用于赤白痢疾、水肿尿少、热淋小便涩痛、产后乳汁不通、乳房胀痛及痈肿、目翳等症。内服：煎汤，用量5～10克；或入丸散。\n" +
                        "\n" +
                        "【化学成分】主要含油(58%为亚油酸)、氨基酸等成分，种子含胆甾醇(cholesterol)、十六碳酸、十八碳烯酸、十八碳二烯酸等7种脂肪酸。提取物又含球蛋白(globulin)C。\n" +
                        "\n" +
                        "【使用禁忌】孕妇慎服。\n" +
                        "\n" +
                        "【配伍药方】①治赤白痢：苘麻子30克。炒令香熟，为末，以蜜浆下3克，不过再服。(《杨氏产乳方》)\n" +
                        "\n" +
                        "②治腹泻：苘麻子焙干，研细末。每次3克，每日服2次。(《吉林中草药》)\n" +
                        "\n" +
                        "③治尿道炎，小便涩痛：苘麻子15克。水煎服。(《长白山植物药志》)\n" +
                        "\n" +
                        "④治腰痛眼疾，乌须黑发：苘麻子(去壳)1斤，白军姜120克。共为细末，蒸饼，糊为丸，如梧桐子大。每服二十五丸，空心黄酒送下，以干物压之。(《鲁府禁方》)\n" +
                        "\n" +
                        "⑤治乳汁不通：苘麻子12克，王不留行15克，穿山甲6克。水煎服。(《长白山植物药志》)");

                chineseMedicineDao.insert(medicine113);

                ChineseMedicine medicine114=new ChineseMedicine();
                medicine114.setName("青葙子");
                medicine114.setSortType("Q");
                medicine114.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=704a7258cb3d70cf58f7a25f99b5ba65/7af40ad162d9f2d35858048eabec8a136327cc01.jpg");
                medicine114.setData("【中药名】青葙子 qingxiangzi\n" +
                        "\n" +
                        "【别名】草决明、牛尾花子、狗尾巴子、野鸡冠花子。\n" +
                        "\n" +
                        "【英文名】Celosiae Semen\n" +
                        "\n" +
                        "【来源】苋科植物青葙Celosia argenteaL.的成熟种子。\n" +
                        "\n" +
                        "【植物形态】一年生草本，高60～80厘米，全体无毛。茎直立，绿色或红紫色，通常分枝。叶互生，披针形或椭圆状披针形，先端渐尖，基部下延成叶柄，全缘。穗状花序单生于茎顶或分枝末端，圆柱状或圆锥状，花着生甚密，初为淡红色，后变为银白色，每花具干膜质苞片3，花被5，干膜质，长圆状披针形，雄蕊5枚，子房长圆形，花柱线形。胞果球形盖裂，种子数粒，扁圆形，质坚硬，黑色有光泽。花期5～7月，果期7～9月。\n" +
                        "\n" +
                        "【产地分布】生于荒野路旁、山沟、河滩、沙丘等疏松土壤上，也有栽培。我国大部分地区有野生或栽培。\n" +
                        "\n" +
                        "【采收加工】8～10月采收，割取地上部分或花穗，晒干，搓出种子，除去杂质，晒干。\n" +
                        "\n" +
                        "【药材性状】呈扁圆形，少数呈圆肾形，中心较边缘稍厚，直径1～1.5毫米，厚约0.5毫米。表面平滑，黑色或红黑色，光亮，中间微隆起，侧边微凹处有种脐。种皮薄而脆，易破碎，内面白色。无臭，无味。\n" +
                        "\n" +
                        "【性味归经】性微寒，味苦。归肝经\n" +
                        "\n" +
                        "【功效与作用】清肝、明日、退翳。属清热药下分类的清热泻火药。\n" +
                        "\n" +
                        "【临床应用】用量9～15克，内服煎汤，治疗肝热目赤、眼生翳膜、视物昏花、肝炎眩晕。\n" +
                        "\n" +
                        "【药理研究】降眼压；降血压；抑菌；缩短家兔血浆再钙化时间。青果水提液具抗乙肝表面抗原(HBsAg)作用；青果能兴奋唾液腺、增加唾液分泌从而起助消化作用。\n" +
                        "\n" +
                        "【化学成分】含青葙子油脂、脂肪油和丰富的硝酸钾、烟酸。\n" +
                        "\n" +
                        "【使用禁忌】肝虚目疾不宜单用，瞳孔散大、青光眼患者禁服。\n" +
                        "\n" +
                        "【配伍药方】①治头昏痛伴有眼噱、眉棱骨痛：青葙子9克，平顶莲蓬5个。水煎服。(江西《草药手册》)\n" +
                        "\n" +
                        "②治肝阳亢盛型高血压：青葙子、草决明、野菊花各10克，夏枯草、大蓟各15克。水煎服。(《四川中药志》 1979年)\n" +
                        "\n" +
                        "③治视物不清：青葙子6克，夜明砂60克。蒸鸡肝或猪肝服。(《四川中药志》1960年)\n" +
                        "\n" +
                        "④治暴发火眼，目赤涩痛：青葙子、黄芩、龙胆草各9克，菊花12克，生地黄15克。水煎服。(《青岛中草药手册》)\n" +
                        "\n" +
                        "⑤治白带，月经过多：青葙子18克，响铃草15克。配猪瘦肉炖服。(《西昌中草药》)\n" +
                        "\n" +
                        "⑥治风热泪眼：青葙子15克，鸡肝炖服。\n" +
                        "\n");

                chineseMedicineDao.insert(medicine114);

                ChineseMedicine medicine115=new ChineseMedicine();
                medicine115.setName("青果");
                medicine115.setSortType("Q");
                medicine115.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D43%2C0%2C502%2C331%3Bc0%3Dbaike80%2C5%2C5%2C80%2C26/sign=71fdcbab67224f4a43d6295334c3a37e/d52a2834349b033b98b341571fce36d3d539bd00.jpg");
                medicine115.setData("【中药名】青果 qingguo\n" +
                        "\n" +
                        "【别名】橄榄子、白榄、黄榄果、忠果、黄榄。\n" +
                        "\n" +
                        "【英文名】Canarii Fructus\n" +
                        "\n" +
                        "【药用部位】橄榄科植物橄榄Canarium album Raeusch.的成熟果实。\n" +
                        "\n" +
                        "【植物形态】常绿乔木。树冠呈圆塔形，树干直立，呈灰白色，有黏性的芳香树脂溢出。奇数羽状复叶，互生，长15~30厘米；小叶9~15片，对生，椭圆状披针形，革质，长6~15厘米，宽2.5~5厘米，先端渐尖，基部偏斜，全缘，上面深绿色，光滑，下面黄绿色，网脉上有小窝点，略粗糙。圆锥花序顶生或腋生，与叶等长或略短；花小，两性或杂性；萼杯状，通常3裂，很少5裂；花瓣3~5片，白色，芳香，顶端钝；花盘明显；雄蕊6枚，着生于花盘的边缘，花药箭状，花丝粗短；雌蕊1枚，子房上位，3室，每室胚珠2。核果卵状纺锤形，青绿色或青黄色，光滑；果核坚硬，纺锤形，两端锐尖，有棱及槽，内有1~3颗种子。花期5~7月，果期8~10月。\n" +
                        "\n" +
                        "【产地分布】栽培于低海拔的杂木林或山坡上。分布于福建、台湾、广东、广西、云南、四川等地。\n" +
                        "\n" +
                        "【采收加工】秋季果实成熟时采摘，晒干或阴干；亦有用盐水浸渍或开水烫过后晒干。\n" +
                        "\n" +
                        "【药材性状】纺锤形，两端钝尖。表面棕黄色或黑褐色，有不规则皱纹。果肉灰棕色或棕褐色，质硬。果核梭形，暗红棕色，具纵棱，内分3室，各有种子1粒。无臭，果肉味涩，久嚼微甜。\n" +
                        "\n" +
                        "【性味归经】性平，味甘、酸。归肺经、胃经。\n" +
                        "\n" +
                        "【功效与作用】清热、利咽、生津、解毒。属清热药下分类的清热解毒药。\n" +
                        "\n" +
                        "【临床应用】用量4.5~9克，煎服。用治咽喉肿痛、咳嗽、烦渴、鱼蟹中毒等症。临床用治细菌性痢疾，雾化吸入疗法治疗咽喉炎，其复方外敷治疗口疮、皮肤病，复方口服治疗咽痒干咳等。\n" +
                        "\n" +
                        "【药理研究】青果水提液具抗乙肝表面抗原(HBsAg)作用；青果能兴奋唾液腺、增加唾液分泌从而起助消化作用。\n" +
                        "\n" +
                        "【化学成分】含蛋白质、脂肪、碳水化合物、钙、磷、铁、复合维生素，并含滨蒿内酯、没食子酸等。种子含挥发油，油中含香树脂醇等；种仁含多种脂肪酸，油酸是主要成分，还含麝香草酚、香芹酚、己酸、辛酸、癸酸、月桂酸、肉豆蔻酸、硬脂酸、棕榈酸、亚麻酸和十八碳二烯酸。\n" +
                        "\n" +
                        "【使用禁忌】阴虚火旺，咯痰带血者禁用。\n" +
                        "\n" +
                        "【配伍药方】①治时行风火喉捅，喉间红肿：橄榄，生芦菔。水煎服。(《王氏医案》青龙白虎汤)\n" +
                        "\n" +
                        "②治咽喉肿痛，声嘶音哑，口舌干燥，吞咽不利：青果(去核)，桔梗、寒水石、薄荷各240克，青黛、硼砂各1240克，甘草620克，冰片36克。共研末，为蜜丸。每服3克，日服2次。(《四川中药志》1982年)\n" +
                        "\n" +
                        "③治孕妇胎动心烦，口渴咽干：青果适量。置猪肚内，炖熟，食肉喝汤。(《四川中药志》1982年)\n" +
                        "\n" +
                        "④治酒伤昏闷：青果肉十个，煎汤饮。(《本草汇言》)\n" +
                        "\n" +
                        "⑤治河豚、鱼、鳖诸毒、诸鱼骨鲠：青果捣汁，或煎浓汤饮。(《随息居饮食谱》)");

                chineseMedicineDao.insert(medicine115);

                ChineseMedicine medicine116=new ChineseMedicine();
                medicine116.setName("青皮");
                medicine116.setSortType("Q");
                medicine116.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=c1ee185fb5003af359b7d4325443ad39/bba1cd11728b47104e2f059ac0cec3fdfc032360.jpg");
                medicine116.setData("【中药名】青皮 qingpi\n" +
                        "\n" +
                        "【别名】青橘皮、青柑皮。\n" +
                        "\n" +
                        "【英文名】Citri Reticulatae Pericarpium Viride。\n" +
                        "\n" +
                        "【药用部位】芸香科植物橘Citrus reticulata Blanco及其栽培变种的干燥幼果或未成熟果实的果皮。\n" +
                        "\n" +
                        "【植物形态】常绿小乔木或灌木，高3～4米。枝柔弱，有刺或无刺。单身复叶互生；叶柄长0.5～1.5毫米，有不明显的叶翼，顶端有关节；叶片披针形或椭圆形，长4～11厘米，宽1.5～4厘米，先端渐尖，微凹，基部楔形，全缘或有不明显的波状钝锯齿，有半透明油腺点。花单生或数朵生于枝端或叶腋，有花梗；花萼杯状，5裂，裂片三角形；花瓣5，长椭圆形，长1～1.5厘米，白色或带淡红色，向外反卷；雄蕊15～25枚，长短不一，花丝常3～5个连合，与柱头等长或略长；雌蕊1，子房圆形，柱头头状。柑果近圆形或扁圆形，横径4～7厘米，红色、朱红色、黄色或橙黄色；果皮薄而松宽易剥，瓤囊7～12瓣，容易分离。种子卵圆形，一端尖，白色，数粒至数十粒，或无，胚深绿色，子叶淡绿色。花期3～4月，果熟期10～12月。\n" +
                        "\n" +
                        "【产地分布】栽培于丘陵、低山地带、江河湖泊沿岸或平原。在陕西、湖北、湖南、广东、广西、江苏、浙江、安徽、福建、江西、台湾、云南、贵州、四川等省区。\n" +
                        "\n" +
                        "【采收加工】5～6月收集自落的幼果，晒干，习称“个青皮”；7～8月采收未成熟的果实，在果皮上纵剖成四瓣至基部，除尽瓤瓣，晒干，习称“四花青皮”。\n" +
                        "\n" +
                        "【药材性状】四花青皮：果皮剖成4裂片，裂片长椭圆形，长4～6厘米，厚0.1～0.2厘米。外表面灰绿色或黑绿色，密生多数油室；内表面类白色或黄白色，粗糙，附黄白色或黄棕色小筋络。质稍硬，易折断，断面外缘有油室1～2列。气香，味苦、辛。个青皮：呈类球形，直径0.5～2厘米。表面灰绿色或黑绿色，微粗糙，有细密凹下的油室，顶端有稍突起的柱基，基部有圆形果梗痕。质硬，断面果皮黄白色或淡黄棕色.厚0.1～0.2厘米，外缘有油室1～2列。瓤囊8～10瓣，淡棕色。气清香，味酸、苦、辛。\n" +
                        "\n" +
                        "【性味归经】性温，味苦、辛。归肝经、胆经、胃经。\n" +
                        "\n" +
                        "【功效与作用】疏肝破气，消积化滞。属理气药。\n" +
                        "\n" +
                        "【临床应用】用量3～10克，煎汤内服，或入丸、散。用治胸胁胀痛，疝气疼痛，乳癖，乳痈，食积气滞，脘腹胀痛。\n" +
                        "\n" +
                        "【药理研究】调整胃肠功能；祛痰平喘；利胆；保肝；抗休克；对心肌的兴奋性、传导性、收缩性和自律性均有正性作用。\n" +
                        "\n" +
                        "【化学成分】主要含橙皮苷、左旋辛弗林乙酸盐、天冬氨酸、胱氨酸、亮氨酸、脯氨酸等。茶枝柑的干燥成熟果皮含挥发油3.541%，其中主要成分为柠檬烯。\n" +
                        "\n" +
                        "【使用禁忌】气虚者慎服。\n" +
                        "\n" +
                        "【配伍药方】①治肝气不和，胁肋刺痛如击如裂者：青皮240克(酒炒)，白芥子、苏子各120克，龙胆草、当归尾各90克。共为末。每早晚各服9克，韭菜煎汤调下。(《本草汇言》引《方脉正宗》)\n" +
                        "\n" +
                        "②治因久积忧郁，乳房内有核如指头，不痛不痒，五七年成痈，名乳癌：青皮12克。水一盏半，煎一盏，徐徐服之，日一服，或用酒服。(《纲目》引《丹溪方》)\n" +
                        "\n" +
                        "③治乳痈初发：青皮(去瓤)、穿山甲(炒)、白芷、甘草、贝母各2.4克。上为细末。温酒调服。(《疡科选粹》青皮散)\n" +
                        "\n" +
                        "④治疝气：青皮(炒黄色)、小茴香(炒黄)。上为末，空心酒调服。(《众妙仙方》偏气方)\n" +
                        "\n" +
                        "⑤治心胃久痛不愈，得饮食米汤即痛极者：青皮15克，玄胡索(俱醋拌炒)9克，甘草3克，大枣三个。水煎服。(《本草汇言》引《方脉正宗》)");

                chineseMedicineDao.insert(medicine116);

                ChineseMedicine medicine117=new ChineseMedicine();
                medicine117.setName("芡实");
                medicine117.setSortType("Q");
                medicine117.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=164ceea1b9a1cd1111bb7a72d87ba399/f603918fa0ec08fabfad19365aee3d6d54fbdaf6.jpg");
                medicine117.setData("【中药名】芡实 qianshi\n" +
                        "\n" +
                        "【别名】鸡头米、水流黄、鸡头果、苏黄、黄实、鸡嘴莲。\n" +
                        "\n" +
                        "【英文名】Euryales Semen。\n" +
                        "\n" +
                        "【药用部位】睡莲科植物芡EuryaLe feror Salisb.的成熟种仁。\n" +
                        "\n" +
                        "【植物形态】一年生水生草本，全株有很多尖刺。根状茎粗壮而短，具白色须根及不明显的茎。初生叶沉水，箭形，后生叶浮于水面，叶柄长，叶片稍带心形或圆状盾形，直径65～130厘米，上面深绿色，多皱褶，下面深紫色，叶脉凸起，边缘向上折。花紫色，单生于花葶顶端，花葶粗长，部分伸出水面。花昼开夜闭，花萼2片，花瓣多数，雄蕊多数，子房下位，心皮8个，嵌入于膨大的花托顶端，柱头圆盘状，扁平，略身下凹入。浆果球形，海绵质，污紫红色，密生尖刺，与花蕾均形似鸡头，故称“鸡头米”，种子球形，黑色，花期夏、秋季。\n" +
                        "\n" +
                        "【产地分布】多生池、沼及湖泊中，水底须为疏松的黏泥，否则不易生长。分布于东北、华北、华东、华中及四川、贵州等地。\n" +
                        "\n" +
                        "【采收加工】秋末冬初采收成熟果实，除去果皮，取出种子，洗净，再除去硬壳(外种皮)，晒干。\n" +
                        "\n" +
                        "【药材性状】呈类球形，多为破粒，完整者直径5～8毫米。衰面有棕红色内种皮，一端黄白色，约占全体1/3，有凹点状的种脐痕，除去内种皮显白色。质较硬，断面白色，粉性。气微，味淡。\n" +
                        "\n" +
                        "【性味归经】性平，味甘、涩。归脾经、肾经。\n" +
                        "\n" +
                        "【功效与作用】益肾固精，补脾止泻，除湿止带。属收涩药下属分类的固精缩尿止带药。\n" +
                        "\n" +
                        "【临床应用】用量内服：煎汤，15～30克；或入丸、散，亦可适量煮粥食。用治遗精滑精，遗尿尿频，脾虚久泻，白浊，带下。\n" +
                        "\n" +
                        "【化学成分】本品主要含淀粉、蛋白质及脂肪。此外，尚含钙、磷、铁和维生素B1、维生素B2、维生素C、烟酸及胡萝卜素等成分。\n" +
                        "\n" +
                        "【使用禁忌】大小便不利者禁服，食滞不化者慎服。\n" +
                        "\n" +
                        "【配伍药方】①治膏淋：生山药30克，生芡实18克，生龙骨(捣细)18克，生牡蛎(捣细)18克，大生地(切片)18克，潞党参9克，生杭芍9克。水煎服。(《衷中参西录》膏淋汤)\n" +
                        "\n" +
                        "②治黄带下：山药(炒)30克，芡实(炒)30克，黄柏(盐水炒)6克，车前子(酒炒)3克，白果(碎)十枚。水煎服。(《傅青主女科》易黄汤)\n" +
                        "\n" +
                        "③治老幼脾肾虚热及久痢：芡实、山药、茯苓、白术、莲肉、薏苡仁、白扁豆各120克，人参30克。俱炒燥为末，白汤调服。(《方脉正宗》)\n" +
                        "\n" +
                        "④治风湿足膝疼痛不能步履：山药、莲肉(去心)、芡实(净肉)、茯苓(去皮)、薏苡仁(炒)、糖霜各1斤，粳米二升(炒)。上为末拌匀，用土茯苓煎汤常调服，不过半月即愈。服此药忌茶，以上茯苓汤当茶。(《万氏家传方》)");

                chineseMedicineDao.insert(medicine117);

                ChineseMedicine medicine118=new ChineseMedicine();
                medicine118.setName("七叶一枝花");
                medicine118.setSortType("Q");
                medicine118.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=b550324f0e46f21fdd395601974d0005/18d8bc3eb13533fae011ef9aacd3fd1f40345bda.jpg");
                medicine118.setData("【中药名】七叶一枝花 qiyeyizhihua\n" +
                        "\n" +
                        "【别名】蚤休、草河车、金线重楼、独角莲。\n" +
                        "\n" +
                        "【英文名】Polyphylla Paris Rhizome。\n" +
                        "\n" +
                        "【药用部位】百合科植物七叶一枝花Paris poly-phylla Smith的根茎。\n" +
                        "\n" +
                        "【植物形态】多年生草本。根茎肥厚，黄褐色，结节明显。茎直立，圆柱形，常带紫红色或青紫色，基部有1～3片膜质叶鞘包茎。叶轮生茎顶，通常7片；叶片长圆状披针形、倒卵状披针形或倒披针形，长8～27厘米，宽2.2～10厘米，先端急尖或渐尖，基部楔形，全缘，膜质或薄纸质。花柄出自轮生叶中央，通常比叶长，顶生一花；花两性，外轮花被片4～6，狭卵状披针形；内轮花被片狭条形，长超过外轮或近等长;雄蕊8～12，花药短，与花丝近等长或稍长；花柱粗短。蒴果紫色，3～6瓣开裂。种子多数，具鲜红色多浆汁的外种皮。\n" +
                        "\n" +
                        "【产地分布】生长于高海拔1800米至3200米林下。主要产于广西、贵州、云南、西藏、四川等地\n" +
                        "\n" +
                        "【采收加工】秋季采挖，除去须根，洗净，晒干。\n" +
                        "\n" +
                        "【药材性状】根茎类圆柱形，多平直，直径1～2.5厘米。外表黄褐色或灰棕色，有环节，膨大顶端具凹陷的茎残基。质坚实，易折断，断面平坦，粉质，少数部分角质，粉质者粉白色，角质者淡黄棕色，可见草酸钙针晶束亮点。气微，味苦。以粗壮、质坚实、断面色白、粉性足、无泥沙者为佳。\n" +
                        "\n" +
                        "【性味归经】性微寒，味苦。有小毒。归肝经。\n" +
                        "\n" +
                        "【功效与作用】清热解毒，消肿止痛，凉肝定惊。属清热药下属分类的清热解毒药。\n" +
                        "\n" +
                        "【临床应用】内服：煎汤，3～10克；研末，每次1～3克。外用：适量，磨汁涂布、研末调敷或鲜品捣敷。主治痈肿疮毒，咽肿喉痹，乳痈，毒蛇咬伤，跌打伤痛，肝热抽搐。\n" +
                        "\n" +
                        "【药理研究】七叶一枝花煎剂体外对金黄色葡萄球菌、溶血性链球菌、脑膜炎双球菌、痢疾杆菌、伤寒杆菌、副伤寒杆菌、大肠杆菌和铜绿假单胞菌均有不同程度的抑菌作用；甲醇提取物在体外对宋内痢疾杆菌、黏质沙雷杆菌、大肠杆菌、敏感和耐药金黄色葡萄球菌均有明显的抑制作用；乙醇提取物和水提物对流感甲型病毒及流感亚洲甲型病毒均有抑制作用，而乙醇提取物有杀灭钩端螺旋体作用，同浓度的水煎剂则无此作用。其甲醇提取物体外可抑制HeLa瘤株、L929瘤株生长；在体内实验中，甲醇提取物和水提物均对艾氏腹水瘤EAC有效；总皂苷亦可抑制H22实体瘤的生长。此外，七叶一枝花尚有杀精子、平喘、止咳、收缩子宫等作用。\n" +
                        "\n" +
                        "【化学成分】七叶一枝花含薯蓣皂苷元-3-O-β-D-吡喃葡萄糖苷(diosgenin-3-O-β-D- glucopyranoside)、蚤休甾酮(parister-one)、肌酐(creatinine)和氨基酸等。\n" +
                        "\n" +
                        "【使用禁忌】虚寒证、阴性疮疡及孕妇禁服。\n" +
                        "\n" +
                        "【配伍药方】①治蛇咬伤：七叶一枝花根10克，研末开水送服，每日二至三次；另以七叶一枝花鲜根捣烂，或加甜酒酿捣烂敷患处。(《浙江民间常用草药》)\n" +
                        "\n" +
                        "②治中鼠莽毒：金线重楼根。磨水服。(《濒湖集简方》)\n" +
                        "\n" +
                        "③治脱肛：蚤休，用醋磨汁。外涂患部后，用纱布压送复位，每日可涂二至三次。(《广西民间常用草药》)");

                chineseMedicineDao.insert(medicine118);

                ChineseMedicine medicine119=new ChineseMedicine();
                medicine119.setName("千金子");
                medicine119.setSortType("Q");
                medicine119.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=601f3d77a6cc7cd9ee203c8b58684a5a/d50735fae6cd7b8952e11c190a2442a7d8330e9e.jpg");
                medicine119.setData("【中药名】千金子 qianjinzi\n" +
                        "\n" +
                        "【别名】千两金、菩萨豆、续随子。\n" +
                        "\n" +
                        "【英文名】Euphorbiae Semen。\n" +
                        "\n" +
                        "【来源】大戟科植物续随子Euphorbia lathyrisL.的成熟种子。\n" +
                        "\n" +
                        "【植物形态】二年生草本。全株无毛，微被白粉，含白色乳汁。茎直立粗壮，基部稍木质化。单叶，对生，茎下部叶较密，线状披针形，无柄，茎上部叶具短柄，叶片广披针形，顶端锐尖，基部近心形，全缘，上面绿色，下面灰绿色。总花序顶生，伞状，基部有2～4叶状苞片，花序总苞杯状，花单性，无花被，雄花多数，每花有雄蕊1枚，雌花1朵。蒴果近球形。种子短圆形。\n" +
                        "\n" +
                        "【产地分布】生于向阳山坡；多栽培。分布于黑龙江、吉林、辽宁等地。\n" +
                        "\n" +
                        "【采收加工】夏、秋季果实成熟时采收，割取植株，打下种子，除去杂质，干燥。\n" +
                        "\n" +
                        "【药材性状】椭圆形或倒卵形，长约5毫米，直径约4毫米。表面灰棕色或灰褐色，具不规则网状皱纹，网孔凹陷处灰黑色，形成细斑点。一侧有纵沟状种脊，上端有突起的合点，下端有一灰白色线形种脐，长约1毫米，基部有类白色突起的种阜，常已脱落，留有圆形疤痕。种皮薄脆，种仁白色或黄白色，胚乳丰富，油质，胚直，细小。气微，味辛。\n" +
                        "\n" +
                        "【性味归经】性温，味辛。归肝经、肾经、大肠经。\n" +
                        "\n" +
                        "【功效与作用】逐水消肿、破血消癥。属泻下药下分类的峻下逐水药。\n" +
                        "\n" +
                        "【临床应用】用量1.5～3克；去壳，去油用，内服多入丸散服，治疗水肿、痰饮、积滞胀满、二便不通、血瘀经闭；外用适量，捣烂敷患处，治顽癣、疣赘。\n" +
                        "\n" +
                        "【药理研究】对胃肠道黏膜有强烈的刺激作用。有毒。\n" +
                        "\n" +
                        "【化学成分】含脂肪油约48%，其中含油酸等的甘油酯及多种二萜醇酯等。此外，含有游离的二萜醇、甾类、香豆精类、黄酮类、千金子固醇、巨大戟二萜醇、a-檀香萜醇、千金子固醇、β-谷固醇、七叶树内酯、马栗树皮苷、瑞香素等成分。\n" +
                        "\n" +
                        "【使用禁忌】体弱便溏者及孕妇忌服。千金子对胃肠黏膜有刺激作用，对中枢神经系统也有毒性作用。大量口服可产生头晕头痛、恶心流涎、剧烈呕吐、精神不振、腹痛腹泻、心悸、发热、冷汗自出、面色苍白、尿少而浑浊、心率加快，甚至血压下降、大汗淋漓、四肢厥冷、气息微弱、呼吸浅促、舌光无苔、脉细欲绝。\n" +
                        "\n" +
                        "【配伍药方】①治血瘀经闭：千金子3克，丹参、制香附各9克，煎服。(《安徽中草药》)\n" +
                        "\n" +
                        "②治黑子，去疣赘：千金子熟时坏破之，以涂其上，便落。(《普济方》)\n" +
                        "\n" +
                        "③治蛇咬肿毒闷欲死：重台1.8克，千金子(去皮)七颗。二物捣筛为散，酒服方寸匕，兼唾和少许，敷咬处。(《海上集验方》)\n" +
                        "\n" +
                        "④治阳水肿胀：千金子(炒，去油)60克，大黄30克。为末，酒、水丸绿豆大，每服以白汤送下五十丸。(《摘玄方》)\n" +
                        "\n" +
                        "附 千金子霜\n" +
                        "\n" +
                        "【加工方法】取拣净的千金子，搓去壳，碾碎，置蒸器内蒸透，用吸油纸包裹，压榨至油尽，碾细，过筛。\n" +
                        "\n" +
                        "【药材性状】均匀、疏松的淡黄色粉末，微显油性。味辛辣。\n" +
                        "\n" +
                        "【性味功能】味辛，性温。通水消肿、破血消癥。\n" +
                        "\n" +
                        "【临床应用】用量0.5～1克，多入丸散服。外用适量。");

                chineseMedicineDao.insert(medicine119);

                ChineseMedicine medicine120=new ChineseMedicine();
                medicine120.setName("拳参");
                medicine120.setSortType("Q");
                medicine120.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=bf10d3e7184c510fbac9ea4801304e48/b3b7d0a20cf431adf3bb4e164936acaf2edd98b7.jpg");
                medicine120.setData("【中药名】拳参 quanshen\n" +
                        "\n" +
                        "【别名】紫参、山虾子、疙瘩参、牡蒙、众戎、童肠。\n" +
                        "\n" +
                        "【英文名】Bistortae Rhizoma。\n" +
                        "\n" +
                        "【来源】蓼科植物拳参Polygonum bistortaL.的根茎。\n" +
                        "\n" +
                        "【植物形态】多年生草本，高35～85厘米。根茎肥厚，常弯曲，表面棕褐色至黑褐色并带暗紫色。茎单一，不分枝，无毛，具纵沟纹。基生叶具长柄，长15～35厘米，叶片长圆披针形或披针形，先端长渐尖，基部圆钝或截形，沿叶柄长延成窄翅，边缘外卷，两面稍被毛，老时渐脱落；茎生叶互生，向上柄渐短至抱茎；托叶鞘筒状，膜质。总状花序穗状，顶生，圆柱形，直立或稍弯，小花密集，苞片卵形，膜质，淡棕色，中脉色深而明显，花梗纤细，花被淡红色或白色，5片，椭圆形；雄蕊8，较花被稍长；花柱3。瘦果椭圆形，有3棱，棕褐色，稍有光泽，包于宿存萼内。花期6～9月，果期9～11月。\n" +
                        "\n" +
                        "【产地分布】生于较高的山坡、草丛或林间阴湿草甸中。分布于吉林、辽宁、河北、山西、内蒙古、陕西、新疆等地。\n" +
                        "\n" +
                        "【采收加工】秋、冬季采挖根茎，除去残茎叶及须根，晒干。\n" +
                        "\n" +
                        "【药材性状】扁圆柱形，常弯曲成虾状，两端圆钝或稍细。表面呈紫褐色或紫黑色，稍粗糙，有较密环节及根痕，一面隆起，另面较平坦或略具凹槽。质硬，断面近肾形，浅棕红色，有数十个黄白色细点排成断续环状。气微、味苦涩。\n" +
                        "\n" +
                        "【性味归经】性微寒，味苦、涩。归肺经、肝经、大肠经。\n" +
                        "\n" +
                        "【功效与作用】清热解毒、止血、消肿。属清热药下属分类的清热解毒药。\n" +
                        "\n" +
                        "【临床应用】用量3～10克，鲜用加倍，内服煎汤，外用适量煎水洗或捣敷。用治湿热泄泻、痢疾、肝炎、血热、吐血、衄血、崩漏、便血、痔血等。外伤出血、无名肿毒、痈疖疔疮可取鲜品捣敷患处。咽喉肿痛、口舌生疮，可单味煎汤漱口或含咽。此外，本品内服尚可治温病高热惊痫抽搐，多加入止痉药中同用。\n" +
                        "\n" +
                        "【药理研究】拳参提取物体外试验对金黄色葡萄球菌、绿脓杆菌、枯草杆菌、大肠杆菌等均有抗菌作用。拳参渗滤液与明胶制成的制剂有一定的止血作用。现发现还对肿瘤细胞有一定的抑制作用。能显著降低胆碱酯酶活性，并能降低大鼠血清和肝脏中的胆固醇，对四氧嘧啶引起的大鼠糖尿有预防作用。\n" +
                        "\n" +
                        "【化学成分】含并没食子酸、没食子酸以及水解鞣质和缩合鞣质。又含羟基甲基蒽醌、维生素C、β-谷甾醇的异构体等。还含右旋儿茶酚，左旋表儿茶酚，6-没食子酰葡萄糖，3，6-二没食子酸葡萄糖和葡萄糖等成分。\n" +
                        "\n" +
                        "【使用禁忌】拳参毒性很小，无实火实热者慎服，阴疽患者禁服。\n" +
                        "\n" +
                        "【配伍药方】①治痢疾：鲜拳参、鲜蒲公英各12克，鲜黄芩9克。水煎服。小儿酌减。(《全国中草药汇编》)\n" +
                        "\n" +
                        "②治慢性气管炎：拳参9克，陈皮9克，甘草6克。水煎服。(《西宁中草药》)\n" +
                        "\n" +
                        "③治急性扁桃体炎：拳参9克，蒲公英15克。水煎服。(《西宁中草药》)\n" +
                        "\n" +
                        "④治烧烫伤：拳参研末，调麻油匀涂患处，每日1～2次。(《贵州省中草药资料》)\n" +
                        "\n" +
                        "⑤治吐血不止：拳参、人参、阿胶(炒)等分。为末。乌梅汤服3克;另一方去人参，加甘草，以糯米汤服。(《圣惠方》)\n" +
                        "\n" +
                        "⑥治咯血，鼻出血，胃溃疡：拳参45克，研细末。每服4.5克，每日2次。(《宁夏中草药手册》)\n" +
                        "\n" +
                        "⑦治痔疮出血：用拳参15克水煎，熏洗患处。(南药《中草药》)\n" +
                        "\n" +
                        "⑧治无名肿毒：拳参根6～9克，水煎服。(《湖南药物志》)");

                chineseMedicineDao.insert(medicine120);

                ChineseMedicine medicine121=new ChineseMedicine();
                medicine121.setName("秦艽");
                medicine121.setSortType("Q");
                medicine121.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=fafe36fd503d26973ade000f3492d99e/3b292df5e0fe9925e623626735a85edf8db171dd.jpg");
                medicine121.setData("【中药名】秦艽 qinjiao\n" +
                        "\n" +
                        "【别名】秦胶、秦纠、大艽。\n" +
                        "\n" +
                        "【英文名】Gentianae Macrophyllae Radix。\n" +
                        "\n" +
                        "【来源】龙胆科植物秦艽Gentiana macrophylla Pall.的根。\n" +
                        "\n" +
                        "【植物形态】多年生草本，高20～60厘米。主根粗长，扭曲不直，近圆锥形，根茎部有许多纤维状残存叶基。茎直立或斜生。叶披针形或长圆状披针形，基生叶多数丛生，全缘，主脉5条，茎生叶3～4对，较小，对生。花多集成顶生及茎上部腋生的轮伞花序，花萼管一侧开裂，略呈佛焰苞状，萼齿浅，花冠管状，深蓝紫色，长约2厘米，先端5裂，裂片间有5片短小褶片，雄蕊5枚，子房长圆形，无柄。蒴果长圆形或椭圆形。\n" +
                        "\n" +
                        "【产地分布】生于山区草地、溪旁两侧、路边坡地、灌丛中。分布于黑龙江、吉林、辽宁、内蒙古等地。\n" +
                        "\n" +
                        "【采收加工】春、秋季均可采挖，但以秋季质量最好。挖出后去掉茎叶，晒至柔软时，堆成堆，使其自然发热，到根内部变成肉红色时，晒干。也可在挖根后，直接晒干。\n" +
                        "\n" +
                        "【药材性状】略圆锥形，上粗下细，长7～30厘米，直径1～3厘米。表面灰黄色或棕黄色，有纵向或扭曲的纵沟。根头部常膨大，多由数个根茎合着。残存的茎基上有纤维状叶基维管束。质坚脆，易折断，断面皮部黄色或棕黄色，木部黄色。气特殊，味苦而涩。\n" +
                        "\n" +
                        "【性味归经】性平，味苦、辛。归胃经、肝经、胆经。\n" +
                        "\n" +
                        "【功效与作用】祛风湿、退虚热、舒筋止疼。属祛风湿药下分类的祛风湿清热药。\n" +
                        "\n" +
                        "【临床应用】用量5～10克，内服治疗风湿关节痛、筋脉拘挛、结核病潮热、小儿疳积发热、黄疸、小便不利等症。治疗关节痛、头痛、牙痛等：秦艽注射液，肌肉注射每次2毫升。\n" +
                        "\n" +
                        "【药理研究】给大鼠腹腔注射秦艽碱甲、醇提物(含总苦苷)和氨化秦艽醇提物(含总生物碱)具有明显抗炎作用；秦艽碱甲具有抗过敏作用，能明显减轻组胺喷雾引起的豚鼠哮喘，对兔蛋清过敏性休克有明显的保护作用，小用量时对大鼠、小鼠有镇静作用，较大用量时出现兴奋、惊厥、导致麻痹而死；龙胆苦苷和当药苷有明显延长戊巴比妥钠引起的小鼠睡眠时间，水提物和醇提物对醋酸诱发小鼠扭体反应有明显镇痛作用，且能直接抑制心脏引起血压下降及心率减慢，对大鼠、小鼠均有升高血糖作用。毒性：秦艽碱甲小鼠灌胃及腹腔给药的LDso分别为486毫克/千克和300毫克/千克。\n" +
                        "\n" +
                        "【化学成分】含龙胆碱(秦艽碱甲)、龙胆次碱(秦艽碱乙)、龙胆醛碱(秦艽碱丙)、龙胆苦苷、α-香树脂醇、秦艽碱甲、秦艽碱乙等成分。\n" +
                        "\n" +
                        "【使用禁忌】久病虚寒，尿多，便溏者禁服。\n" +
                        "\n" +
                        "【配伍药方】①治头风痛：秦艽、白芷、川芎各6克，藁本9克。水煎服。(《沙漠地区药用植物》)\n" +
                        "\n" +
                        "②治小便艰难，胀满闷：秦艽(去苗)30克。以水一大盏，煎取七分，去滓，食前分作二服。(《圣惠方》)\n" +
                        "\n" +
                        "③治一切疮口不合：秦艽细末，掺之。(《直指方》秦艽掺方)\n" +
                        "\n" +
                        "④治久痈疽：秦艽15克，捣罗为末。涂敷疮上，以帛裹缚之，日三次。(《圣济总录》秦艽涂敷方)");

                chineseMedicineDao.insert(medicine121);

                ChineseMedicine medicine122=new ChineseMedicine();
                medicine122.setName("前胡");
                medicine122.setSortType("Q");
                medicine122.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=88ef9c3290529822113e3191b6a310ae/c75c10385343fbf27cdd06d6b07eca8064388f56.jpg");
                medicine122.setData("【中药名】前胡 qianhu\n" +
                        "\n" +
                        "【别名】毛前胡。\n" +
                        "\n" +
                        "【英文名】Peucedani Radix。\n" +
                        "\n" +
                        "【来源】伞形科植物白花前胡Peucedanum praeruptorum Dunn的干燥根。\n" +
                        "\n" +
                        "【植物形态】多年生草本。主根粗壮，呈长圆锥形。茎单一，圆柱形，有纵直沟纹，基部光滑，顶端近花序处密生白色柔毛。基生叶有柄，基部扩大成鞘，抱茎；叶为2～3回3出羽状复叶，最终裂片条形，边缘有粗锯齿，偶呈缺刻状，无毛。复伞形花序顶生，花较大，无总苞，全体散生白色短柔毛；小伞形花序有花25～30朵，小总苞片数片，披针形，密生刺毛；萼齿不明显；花瓣5，白色，倒卵形，顶端突起向内折；雄蕊5枚；子房卵形，下位，花柱2，基部圆锥形。双悬果扁平状卵圆形，无毛，分果背棱显著，侧棱有翅。花期8～9月，果期9～10月。\n" +
                        "\n" +
                        "【产地分布】野生于多石砾的草原、山坡上。产于四川、贵州、云南等地。\n" +
                        "\n" +
                        "【采收加工】春、秋季采挖根部，洗净，晒干。\n" +
                        "\n" +
                        "【药材性状】圆柱形，略弯曲，有时分枝，长15～30厘米，直径0.6～1.5厘米。表面灰棕色或棕褐色，有纵皱纹、多数疣状突起及支根痕；根头部较长，顶端常有残茎，四周有环节及纤维状叶鞘残基。体轻，质硬脆，断面不整齐，皮部黄白色，散生棕色油点，木部淡黄色。气微香，味微苦、辛。\n" +
                        "\n" +
                        "【性味归经】性微寒，味苦、辛。归肺经。\n" +
                        "\n" +
                        "【功效与作用】发表镇痛、降气化痰。属解表药下属分类的辛凉解表药。\n" +
                        "\n" +
                        "【临床应用】用量3～9克，煎服。用治外感表证、头痛昏眩、痰热喘满、咯痰黄稠、风热咳嗽痰多。\n" +
                        "\n" +
                        "【药理研究】动物试验表明，水煎液有镇痛、镇静、解热和抗炎作用。\n" +
                        "\n" +
                        "【化学成分】含白花前胡甲素、白花前胡乙素、白花前胡丙素、白花前胡丁素、欧前胡素、氧化前胡素、紫花前胡种苷Ⅳ、紫花前胡皂苷V、白花前胡香豆精Ⅰ、白花前胡香豆精Ⅱ、白花前胡香豆精Ⅲ等香豆素类及挥发油等成分，油中主要成分为佛手内酯、阿魏酸等。\n" +
                        "\n" +
                        "【使用禁忌】阴虚咳嗽、寒饮咳嗽患者慎服。半夏为之使，恶皂荚，畏藜芦。\n" +
                        "\n" +
                        "【配伍药方】①治咳嗽涕唾稠黏，心胸不利，时有烦热：前胡30克(去芦头)，麦门冬(去心)45克，贝母(煨微黄)30克，桑根白皮(锉)30克，杏仁(汤浸，去皮、尖，麸炒微黄)15克，甘草(炙微赤，锉)0.3克，上药捣筛为散。每服12克，以水一中盏，入生姜0.15克，煎至六分，去滓，不计时候，温服。(《圣惠方》前胡散)\n" +
                        "\n" +
                        "②治肺喘，毒雍滞心膈，昏闷：前胡(去芦头)、紫菀(洗去苗土)，诃黎勒皮、枳实(麸炒微黄)各30克。上为散。每服3克，不计时候，以温水调下。(《普济方》前胡汤)\n" +
                        "\n" +
                        "③治妊娠伤寒，头痛壮热：前胡(去芦头)、黄芩(去黑心)、石膏(碎)阿胶(炙，焙)各30克。上粗捣筛，每服9克，水一盏，煎至七分去滓，不计时温服。(《普济方》前胡汤)\n" +
                        "\n" +
                        "④治骨蒸热：前胡3克，柴胡6克，胡黄连3克，猪脊髓一条，猪胆一个，水煎，入猪胆汁服之。(《国医宗旨》)\n" +
                        "\n" +
                        "⑤治小儿风热气啼：前胡(去芦)。上为末，炼蜜和丸小豆大。日服一丸，熟水下。服至五六丸即瘥。(《小儿卫生总微论方》前胡丸)\n" +
                        "\n");
                chineseMedicineDao.insert(medicine122);

                ChineseMedicine medicine123=new ChineseMedicine();
                medicine123.setName("茜草");
                medicine123.setSortType("Q");
                medicine123.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=9f867a579d8fa0ec6bca6c5f47fe328b/8d5494eef01f3a29d0a36daa9525bc315c607c6f.jpg");
                medicine123.setData("【中药名】茜草 qiancao\n" +
                        "\n" +
                        "【别名】血见愁、活血丹、地血、风车草、八仙草、破血草、红内消、红茜根。\n" +
                        "\n" +
                        "【别名】Rubiae Radix Et Rhizoma。\n" +
                        "\n" +
                        "【来源】茜草科植物茜草Rubia cordifoliaL.的根及根茎。\n" +
                        "\n" +
                        "【植物形态】多年生攀缘草本，长1～3米。支根数条或数十条，细长，外皮黄赤色。茎方形，有4棱，棱上有倒生刺。叶4片轮生，有长柄，叶片卵状心形或狭卵形，先端渐尖，基部心形或圆形，全缘，叶脉3～5条，自基部射出，叶柄和叶下面中肋上均有倒刺。聚伞花序圆锥状，腋生或顶生，花小，花冠5裂，淡黄色，子房下位。浆果。\n" +
                        "\n" +
                        "【产地分布】生于原野、山地的林边、灌丛中。全国大部分地区有分布。\n" +
                        "\n" +
                        "【采收加工】春、秋季采挖，除去茎苗，去净泥土及细须根，晒干。一般以秋季采者为佳。茜草炭：取茜草片，置锅内炒至外表呈焦黑色，内部老黄色，喷洒清水，放凉。\n" +
                        "\n" +
                        "【药材性状】根茎呈结节状，根丛生，粗细不等。根呈圆柱形，略弯曲，长10～ 25厘米，直径0.2～1厘米，表面红棕色或暗棕色，具细纵皱纹及少数细根痕，皮部脱落处呈黄红色。质脆，易折断，断面平坦，皮部狭，紫红色，木部宽广，浅黄红色，导管孔多数。无臭，味微苦，久嚼刺舌。\n" +
                        "\n" +
                        "【性味归经】性寒，味苦。归肝经。\n" +
                        "\n" +
                        "【功效与作用】凉血、止血、祛瘀、通经。属止血药下分类的凉血止血药。\n" +
                        "\n" +
                        "【临床应用】用量6～9克，内服煎汤，或入丸散，治疗吐血、衄血、崩漏、外伤出血，经闭阏阻、关节痹痛、跌扑肿痛。脾胃虚寒及无瘀滞者忌服。\n" +
                        "\n" +
                        "【药理研究】具有缩短出血时间、抗血小板聚集、升高白细胞的作用；具有镇咳祛痰、抗菌、抗癌作用，能防止实验性肾和膀胱结石的形成，尤其对碳酸钙结石的形成有抑制作用；对实验性心肌梗死有治疗作用；对抗乙酸胆碱所致的离体肠痉挛，有解痉作用。对离体子宫有兴奋作用；具有扩张血管、抑制皮肤结缔组织通透性的作用。小鼠口服根煎剂有明显止咳和祛痰作用，但加酒精沉淀后，滤液即无效；根煎剂能对抗离体兔回肠的收缩作用；根水提物对离体豚鼠子宫有兴奋作用，产妇口服亦有加强子宫收缩的作用；根温浸液能扩张蛙足蹼膜血管，并稍能缩短家兔的血液凝固时间；根在试管内对金黄色与白色葡萄球菌、肺炎球菌及流感杆菌等有一定的抑制作用。\n" +
                        "\n" +
                        "【化学成分】本品主要含紫茜素、茜草色素、齐墩果酸乙酸酯、齐墩果醛乙酸酯、茜草萜三醇、羟基茜草素、茜草素、异茜草素、二氢大叶茜草素、1-羟基-2-羧基-3-甲氧基葸醌、2'-甲氧基大叶茜草素、大叶茜草素等成分。\n" +
                        "\n" +
                        "【使用禁忌】脾胃虚寒及无瘀滞者慎服。\n" +
                        "\n" +
                        "【配伍药方】①治吐血不定：茜草30克，生捣罗为散。每服6克，水一中盏，煎至七分，放冷，饭后服之良。(《简要济众方》)\n" +
                        "\n" +
                        "②治咯血、尿血：茜草9克，白茅根30克。水煎服。(《河南中草药手册》)\n" +
                        "\n" +
                        "③治月经过多，子宫出血：茜草根7克、艾叶5克，侧柏叶6克，生地黄10克。水500毫升，煎至200毫升，去渣后，加阿胶10克，溶化。每日3次分服。(《现代实用中药》)\n" +
                        "\n" +
                        "④治女子经水不通：茜草30克。黄酒煎、空心服。(《经验广集》)\n" +
                        "\n" +
                        "⑤治跌打损伤：茜草根30～60克，水酒各半炖服；或茜草根和地鳖虫各15克，酒水各半炖服。(《福建药物志》)\n" +
                        "\n" +
                        "⑥治风湿痛，关节炎：鲜茜草根120克，白酒500克。将茜草根洗净捣烂，浸入酒内1星期，取酒炖温，空腹饮。第1次要饮到八成醉，然后睡觉，覆被取汗，每日1次。服药后7天不能下水。(《江苏验方草药选编》)\n" +
                        "\n" +
                        "⑦治肾炎：茜草根30克，牛膝、木瓜各15克。水煎备用。另取童子鸡1只，去肠杂，蒸出鸡汤后，取汤一半同上药调服，剩下鸡肉和汤同米炖吃。(《福建药物志》)\n" +
                        "\n" +
                        "⑧治牙痛：鲜茜草30～60克，水煎服。(《河南中草药手册》)");

                chineseMedicineDao.insert(medicine123);

                ChineseMedicine medicine124=new ChineseMedicine();
                medicine124.setName("青木香");
                medicine124.setSortType("Q");
                medicine124.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=6be896461a4c510fbac9ea4801304e48/b17eca8065380cd77d8fea63a144ad34588281af.jpg");
                medicine124.setData("【中药名】青木香 qingmuxiang\n" +
                        "\n" +
                        "【别名】马兜铃根、兜铃根、青藤香。\n" +
                        "\n" +
                        "【英文名】Radix Aristolochiae\n" +
                        "\n" +
                        "【来源】马兜铃科植物马兜铃Aristolochia debilis Seib.et Zucc的根。\n" +
                        "\n" +
                        "【植物形态】草质藤本。根圆柱形。茎柔弱，无毛。叶互生，叶柄柔弱，叶片卵状三角形、长圆状卵形或戟形，先端钝圆或短渐尖，基部心、形，两侧裂片圆形，下垂或稍扩展;基出脉5～7条，各级叶脉在两面均明显。花单生或2朵聚生于叶腋，小苞片三角形，易脱落，花被基部膨大呈球形，向上收狭成一长管，管口扩大成漏斗状，黄绿色，口部有紫斑，内面有腺体状毛，檐部一侧极短，另一侧渐延伸成舌片，舌片卵状披针形，顶端钝，花药贴生于合蕊柱近基部，子房圆柱形，6棱，合蕊柱先端6裂，稍具乳头状凸起，裂片先端钝，向下延伸形成波状圆环。蒴果近球形，先端圆形而微凹，具6棱，成熟时由基部向上沿室间5瓣二裂，果梗常撕裂成6条。种子扁平，钝三角形，边缘具白色膜质宽翅。花期7～8月，果期9～10月。\n" +
                        "\n" +
                        "【产地分布】生于山谷、沟边阴湿处或山坡灌土中。分布于山东、河南及长江流域以南各地。\n" +
                        "\n" +
                        "【采收加工】10～11月茎叶枯萎时挖取根部，除去项根、泥土，晒干。\n" +
                        "\n" +
                        "【药材性状】本品呈圆柱形或稍扁，略弯曲，长3～10厘米，直径0.5～1.5厘米。表面黄褐色或灰棕色，有纵皱纹及须根痕。质坚脆，折断面形成层环隐约可见，皮部淡黄色，木射线宽广，乳白色，木质部束淡黄色，呈放射状，导管孔明显。香气特异，味苦。\n" +
                        "\n" +
                        "【性味归经】性寒，味辛、苦。归肺经、胃经、肝经。\n" +
                        "\n" +
                        "【功效与作用】行气止痛，解毒消肿，平肝降压。属理气药。\n" +
                        "\n" +
                        "【临床应用】内服：煎汤，3～9克；研末，1.5～2克，每日2～3次。外用：适量，研末调敷；或磨汁涂。主治胸胁脘腹疼痛，疝气痛，肠炎，下痢腹痛，咳嗽痰喘，蛇虫咬伤，痈肿疔疮，湿疹，皮肤瘙痒，高血压病。\n" +
                        "\n" +
                        "【药理研究】抑制平滑肌的运动；镇痛抗炎；抗病原体；降压；镇静；催吐；松弛横纹肌等。\n" +
                        "\n" +
                        "【化学成分】青木香主要含挥发油，其主要成分为马兜铃酮，并含马兜铃酸、尿囊素、青木香酸、木兰花碱、土青木香甲素及丙素等。\n" +
                        "\n" +
                        "【使用禁忌】脾胃虚寒者慎服。服用过量，可引起肠胃反应，如恶心、呕吐、腹胀、腹痛、口苦、口干等。\n" +
                        "\n" +
                        "【配伍药方】①治肠炎，腹痛下痢：青木香9克，槟榔4.5克，黄连4.5克。共研细末，温开水冲服。(《现代实用中药》)\n" +
                        "\n" +
                        "②治中暑腹痛：青木香根(鲜)9～15克，捣汁，温开水送服;亦用青木香根3～6克，研末，温开水送服。(《江西草药》)\n" +
                        "\n" +
                        "③治牙痛：青木香鲜品一块，放牙痛处咬之。(《东北常用中草药手册》)\n" +
                        "\n" +
                        "④治妇人小便出血不止：青木香根、刺蓟根各30克。捣细罗为散。每服食前，当归酒调下6克。(《圣惠方》)\n" +
                        "\n" +
                        "⑤治腋气：青木香做厚片，好醋浸一宿，夹腋下数次，即愈。(《卫生易简方》)");

                chineseMedicineDao.insert(medicine124);

                ChineseMedicine medicine125=new ChineseMedicine();
                medicine125.setName("羌活");
                medicine125.setSortType("Q");
                medicine125.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike220%2C5%2C5%2C220%2C73/sign=1a2f939e1038534398c28f73f27adb1b/37d12f2eb9389b5060c780a08535e5dde7116e03.jpg");
                medicine125.setData("【中药名】羌活 qianghuo\n" +
                        "\n" +
                        "【别名】川羌、竹节羌、大头羌、条羌、蚕羌、羌滑、退风使者、黑药。\n" +
                        "\n" +
                        "【英文名】Notopterygii Rhizoma Et Radix。\n" +
                        "\n" +
                        "【来源】伞形科植物羌活Notopterygium incisum Ting ex H.T.Chang或宽叶羌活Notopterygium, franchetii H. de Boiss.的干燥根茎和根。\n" +
                        "\n" +
                        "【植物形态】多年生草本，高60～120厘米。根茎粗壮。茎直立，中空，表面淡紫色，有纵直细条纹。基生叶及茎下部叶有长柄，叶片为三出三回羽状复叶，小叶3～4对，末回裂片边缘缺刻状浅裂至羽状深裂，茎上部简化成鞘状，近于无柄。复伞形花序顶生或腋生。花瓣白色或绿白色，卵形或长圆状卵形，长约1.5毫米，顶端圆钝，反折，分果长圆形，长5～6毫米，主棱均扩展为翅，翅等宽或不等宽，棱槽3油管，合生面6油管。花期7～8月，果期8～9月。\n" +
                        "\n" +
                        "【产地分布】生于海拔1600～5000米的林缘、灌丛下。分布于陕西、甘肃、青海、四川、西藏等省，主产于四川。\n" +
                        "\n" +
                        "【采收加工】春、秋二季采挖，除去须根及泥沙，晒干。\n" +
                        "\n" +
                        "【药材性状】羌活：为圆柱状略弯曲的根茎，长4～13厘米，直径0.6～2.5厘米，顶端具茎痕。表面棕褐色至黑褐色，外皮脱落处呈黄色。节间缩短，呈紧密隆起的环状，形似蚕，习称“蚕羌”；节间延长，形如竹节状，习称“竹节羌”。节上有多数点状或瘤状突起的根痕及棕色破碎鳞片。体轻，质脆，易折断，断面不平整，有多数裂隙，皮部黄棕色至暗棕色，油润，有棕色油点，木部黄白色，射线明显，髓部黄色至黄棕色。气香，味微苦而辛。宽叶羌活：为根茎和根。根茎类圆柱形，顶端具茎和叶鞘残基，根类圆锥形，有纵皱纹和皮孔；表面棕褐色，近根茎处有较密的环纹，长8～15厘米，直径1～3厘米，习称“条羌”。有的根茎粗大，不规则结节状，顶部具数个茎基，根较细，习称“大头羌”。质松脆，易折断，断面略平坦，皮部浅棕色，木部黄白色。气味较淡。\n" +
                        "\n" +
                        "【性味归经】性温，味辛、苦。归膀胱经、肾经。\n" +
                        "\n" +
                        "【功效与作用】 解表散寒，祛风除湿，止痛。属解表药下属分类的辛温解表药。\n" +
                        "\n" +
                        "【临床应用】用量6～12克，内服：煎汤；或入丸、散。用治风寒感冒，头痛项强，风湿痹痛，肩背酸痛。\n" +
                        "\n" +
                        "【药理研究】具有解热、镇痛、抗炎、抗过敏、抗心肌缺血、抗心律失常、抗血栓形成、抗癫痫、抗氧化、抗菌、延长睡眠时间、抑癌等作用：\n" +
                        "\n" +
                        "【化学成分】羌活根茎含香豆精类化合物、酚性化合物、挥发油(约2.7%)、脂肪酸类、氨基酸类、糖类，羌活还含苯乙基阿魏酸酯。宽叶羌活的地下部分含香豆精类化合物、酚性化合物，还含β-谷甾醇葡萄糖甙及挥发油。另含羌活醇、异欧前胡素、异欧前胡内酯、紫花前胡苷元、匙叶桉油烯醇、绿原酸、阿魏酸、佛手柑内酯等成分。\n" +
                        "\n" +
                        "【使用禁忌】血虚痹痛、气虚多汗者慎服。\n" +
                        "\n" +
                        "【配伍药方】①治水气肿：羌活、萝卜子(炒)各30克。上为末，用酒调下。(《医得效方》川活散)\n" +
                        "\n" +
                        "②治小儿伤风：羌活3克，人参3克，防风3克，川芎3克。上锉一剂，生姜三片，薄荷七叶，水一盏，煎至七分，不拘时服。(《婴重百间》羌活汤)\n" +
                        "\n" +
                        "③治产后伤寒：羌活、香附、紫苏各4.5克，当归3克，白芍、柴胡、陈皮各3.6克。加葱白三茎，水煎，不拘时服。(《丹台玉案》羌苏饮)\n" +
                        "\n" +
                        "④治太阳经头痛：防风0.6克，羌活0.6克，红豆二个。为末，滴鼻内。(《玉机微义》)\n" +
                        "\n" +
                        "⑤治头风眩晕，闷起欲倒：川芎、羌活、蔓荆子、防风、白芷、细辛、藁本、石膏各等分。水煎服。(《医学启蒙》川芎羌活汤)");
                chineseMedicineDao.insert(medicine125);

                ChineseMedicine medicine126=new ChineseMedicine();
                medicine126.setName("千年健");
                medicine126.setSortType("Q");
                medicine126.setMedicineImageUrl("http://imgs.bzw315.com/UploadFiles/image/News/2016/11/15/20161115141545_777.jpg");
                medicine126.setData("【中药名】千年健 qiannianjian\n" +
                        "\n" +
                        "【别名】一包针、千年见、千颗针。\n" +
                        "\n" +
                        "【英文名】Homalomenae Rhizoma。\n" +
                        "\n" +
                        "【来源】天南星科植物千年健Homalomena occulta (Lour.) Schott.的根茎。\n" +
                        "\n" +
                        "【植物形态】多年生草本。根茎匍匐，长圆柱形，肉质。鳞叶线状披针形，向上渐狭；叶具肉质长柄，上部圆柱形，有浅槽，下部膨大，呈翼状，基部扩大呈叶鞘，叶片近纸质，箭状心形或卵状心形，先端长渐尖，基部近心形，两面光滑无毛，侧脉平展，向上斜升，干后呈有规则的皱缩。花序1～3，生于鳞叶之腋，短于叶柄，佛焰苞长圆形或椭圆形，开花前卷成纺锤形，先端尖，有喙，肉穗花序具短柄或无柄，花单性同株；雄花生在花序上部，雌花在下部，紧密连接；无花被；雄花密集，通常由3枚雄蕊组成一束，分离，雄蕊呈片状长圆形，花药纵裂；雌花具退化雄蕊，呈棒状，雌蕊长圆形，子房3室，胚珠多数，柱头盘状，具不明显的3裂。浆果。种子长圆形，褐色。花期7～9月，果期8～10月。\n" +
                        "\n" +
                        "【产地分布】生于山谷溪边或密林下、竹林下、灌丛下阴湿地。分布于海南、广西、云南等地。\n" +
                        "\n" +
                        "【采收加工】春、秋季采挖，洗净，去叶，洗净，刮去外皮，晒干。\n" +
                        "\n" +
                        "【药材性状】圆柱形，稍弯曲，有的略扁，长15～40厘米，直径0.8～1.8厘米。表面黄棕色至红棕色，粗糙，可见多数扭曲的纵沟纹、圆形根痕及黄色针状纤维束。质硬而脆，断面红褐色，黄色针状纤维束多而明显，相对另一断面呈多数针眼状小孔及有少数黄色针状纤维束，可见深褐色具光泽的油点。气香，味辛，微苦。\n" +
                        "\n" +
                        "【性味归经】性温，味苦、辛。归肝经、肾经。\n" +
                        "\n" +
                        "【功效与作用】祛风湿、健筋骨。属祛风湿药下属分类的祛风湿强筋骨药。\n" +
                        "\n" +
                        "【临床应用】用量4.5～9克，煎汤内服。用治风寒湿痹、腰膝冷痛、下肢拘挛麻木。外敷适量，用治中风关节肿痛、慢性盆腔炎、骨折愈合迟缓。\n" +
                        "\n" +
                        "【药理研究】抗组胺，抗凝血，抗菌，抗病毒，抗炎镇痛等。临床用治风寒湿痹有较好疗效，但药理作用尚不清楚。\n" +
                        "\n" +
                        "【化学成分】含挥发油，油中主要组分为α-蒎烯、β-蒎烯、香芹酚、伞花烃、樟脑、赤鲜醇、葡萄糖和芳樟醇等。\n" +
                        "\n" +
                        "【使用禁忌】阴虚内热者慎服。\n" +
                        "\n" +
                        "【相关药方】治风寒筋骨疼痛、拘挛麻木；千年健、地风各30g，老鹳草90g。共研细粉。每服3g。(《全国中草药汇编》)。");

                chineseMedicineDao.insert(medicine126);

                ChineseMedicine medicine127=new ChineseMedicine();
                medicine127.setName("千斤拔");
                medicine127.setSortType("Q");
                medicine127.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=548d88b90d2442a7ba03f5f7b02ac62e/6159252dd42a2834fe47843159b5c9ea15cebf23.jpg");
                medicine127.setData("【中药名】千斤拔 qianjinba\n" +
                        "\n" +
                        "【别名】金鸡落地、大力黄、千金坠。\n" +
                        "\n" +
                        "【英文名】Radix Et Caulis Flemingiae\n" +
                        "\n" +
                        "【来源】豆科植物蔓性千斤拔Flemingia prostrata Roxb.的干燥根。\n" +
                        "\n" +
                        "【植物形态】直立或平卧半灌木。幼枝有棱角，披短柔毛。叶互生，叶柄被长茸毛；托叶2片，三角状，具疏茸毛，三出复叶，顶生小叶卵状披针形，先端钝，基部圆形，上面有疏短柔毛，下面密生柔毛，侧生小叶基部斜，基出脉3条，叶柄有柔毛。总状花序腋生，花密集，萼齿5，披针形，下面1个较长，密生白色长硬毛，有密集的腺点，花冠紫色，稍长于萼，旗瓣椭圆形，基部变狭，无明显的爪，雄蕊10，二体，子房有毛。荚果长圆形，有黄色短柔毛。种子2颗，圆球形，黑色。花期10～11月。\n" +
                        "\n" +
                        "【产地分布】生于山坡草丛中。分布于福建、台湾、湖北、湖南、广东、海南、广西、贵州、云南等地。\n" +
                        "\n" +
                        "【采收加工】秋后采挖，洗净，切段，晒干。\n" +
                        "\n" +
                        "【药材性状】根长圆柱形，上粗下渐细，极少分枝，长30～70厘米，上部直径1～2厘米。表面棕黄色、灰黄色至棕褐色，有稍突起的横长皮孔及细皱纹，近顶部常成圆肩膀状，下半部间见须根痕；栓皮薄，鲜时易刮离，刮去栓皮可见棕红色或棕褐色皮部。质坚韧，不易折断。横切面皮部棕红色，木部宽广，淡黄白色，有细微的放射状纹理。气微，味微甘、涩。以根条粗长、除净芦茎及须根、断面黄白色者为佳。\n" +
                        "\n" +
                        "【性味归经】性平，味甘、微涩。归肝经、肾经。\n" +
                        "\n" +
                        "【功效与作用】祛风除湿，强筋壮骨，活血解毒。属杀祛风湿药下分类的祛风湿强筋骨药。\n" +
                        "\n" +
                        "【临床应用】内服：煎汤，用量15～30克。外用：适量，磨汁涂，或研末调敷。主治风湿痹痛，腰肌劳损，四肢痿软，跌打损伤，咽喉肿痛。\n" +
                        "\n" +
                        "【化学成分】主要含黄酮类化合物，其中有蔓性千斤拔素C、蔓性千斤拔素D、千斤拔素、羽扇豆醇、β-谷固醇等。\n" +
                        "\n" +
                        "【使用禁忌】孕妇慎服。\n" +
                        "\n" +
                        "【相关药方】①治风湿性关节炎，腰腿痛：千斤拔30克，半枫荷15克，水煎服。(《香港中草药》)\n" +
                        "\n" +
                        "②治坐骨神经痛：蔓性千斤拔根、肖梵天花根各30克。水煎服。(《福建药物志》)\n" +
                        "\n" +
                        "③治产后腰膝痛：蔓性千斤拔根30克，茜草、威灵仙各9克。水煎服。(《福建药物志》)\n" +
                        "\n" +
                        "④治劳倦乏力：蔓性千斤拔根15克，称星树(梅叶冬青)30克。水煎服。(《福建药物志》)\n" +
                        "\n" +
                        "⑤治跌打损伤：千斤拔20～30克。酒、水各半煎服。[《江西中医药》1957，(10)：6]");

                chineseMedicineDao.insert(medicine127);

                ChineseMedicine medicine128=new ChineseMedicine();
                medicine128.setName("青叶胆");
                medicine128.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=9a62c362b01bb0519b29bb7a5713b1d1/f603918fa0ec08fa6228dce75aee3d6d55fbda42.jpg");
                medicine128.setData("【中药名】青叶胆 qingyedan\n" +
                        "\n" +
                        "【别名】肝炎草、小青鱼胆、七疸药、小苦草、细龙胆。\n" +
                        "\n" +
                        "【英文名】Swertiae Mileensis Herba。\n" +
                        "\n" +
                        "【来源】龙胆科植物青叶胆Swertia mileensis T.N.HoetW.L.Shih的干燥全草。\n" +
                        "\n" +
                        "【植物形态】一年生草本，高15～45cm。根棕黄色。茎直立，四棱形，从基部起呈塔形分枝。叶无柄，叶片线形狭矩圆形至披针形，长4～40mm，宽 2～4mm。先端急尖，基部楔形。具3脉，花单生枝顶，4数。花梗细，长0.4～3cm，基部具有1对苞片，花萼绿色，叶状，稍短于花冠，裂片线状披针 形，长6～10mm，先端急尖，背面中脉明显，花冠淡蓝色，直径约1cm，裂片矩圆形或卵状披针形，长7～12mm，先端急尖，具小尖头，下部具2个腺 窝，腺窝杯状，仅顶端具短柔毛状流苏，花丝长4.5～6mm，花药蓝色，椭圆形，长2～2.5mm，子房卵状矩圆形，长3.5～4.5mm。蒴果椭圆状卵 形或长椭圆形，长达1cm。种子棕褐色，卵球形，直径约0.6mm。花果期9～11月。\n" +
                        "\n" +
                        "【产地分布】生于海拔1300~1700米的荒山坡稀疏小灌木或草丛间。分布于云南南部。\n" +
                        "\n" +
                        "【采收加工】秋季花果期采收，除去泥沙，晒干。\n" +
                        "\n" +
                        "【药材性状】长15～45cm。根长圆锥形，长2～7cm，直径约0.2cm，有的有分枝；表面黄色或黄棕色。茎四棱形，棱角具极狭的翅，直径 0.1～0.2cm；表面黄绿色或黄棕色，下部常显红紫色，断面中空。叶对生，无柄；叶片多皱缩或破碎，完整者展平后呈条形或狭披针形，长1～4cm，宽 0.2～0.7cm。圆锥状聚伞花序，萼片4，条形，黄绿色；花冠4，深裂，黄色，裂片卵状披针形，内侧基部具2腺窝；雄蕊4。蒴果狭卵形，种子多数，细 小，棕褐色。气微，味苦。\n" +
                        "\n" +
                        "【性味归经】性寒，味苦、甘。归肝经、胆经、膀胱经。\n" +
                        "\n" +
                        "【功效与作用】清肝利胆，清热利湿。属清热药下属分类的清热燥湿药。\n" +
                        "\n" +
                        "【临床应用】用量10～15克，煎汤内服；外用：适量，鲜品捣敷或前水洗。用治肝胆湿热，黄疸尿赤，胆胀胁痛，热淋涩痛。\n" +
                        "\n" +
                        "【主要成分】青叶胆全草含齐墩果酸，日本当药素即木犀草素-7-甲醚6-C-&beta；-葡萄糖甙，当药素即6-C-&beta；-葡萄糖-芫花素，另含五种呫吨酮成分。\n" +
                        "\n" +
                        "【使用禁忌】尚不明确。");

                chineseMedicineDao.insert(medicine128);

                ChineseMedicine medicine129=new ChineseMedicine();
                medicine129.setName("千日红");
                medicine129.setSortType("Q");
                medicine129.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=a920d23f9925bc313f5009ca3fb6e6d4/fc1f4134970a304ea1caa70bd1c8a786c8175c52.jpg");
                medicine129.setData("【中药名】千日红 qianrihong\n" +
                        "\n" +
                        "【别名】千年红、吕宋菊、球形鸡冠花。\n" +
                        "\n" +
                        "【英文名】Flos Gomphrenae。\n" +
                        "\n" +
                        "【来源】苋科植物千日红Gomphrena globosaL.的头状花序。\n" +
                        "\n" +
                        "【植物形态】一年生草本，高30~80厘米，全株有白色长毛。茎近四棱形，节部膨大，带紫红色。叶对生，具短柄；叶片长椭圆形至椭圆状披针形，长5~ 10厘米，宽2~5厘米，两面均有较长的白柔毛，边缘有纤毛。花序球形或长圆形，通常单生于枝顶，总苞2枚，叶状，每花基部有干膜质卵形苞片1枚，三角状披针形小苞片2枚，紫红色或白色；花被片5，外被白柔毛；雄蕊5，花丝合生呈管状；子房卵圆形，柱头2裂。胞果近圆形，种子黑色。花期7~10月。\n" +
                        "\n" +
                        "【产地分布】我国各地均有栽培。\n" +
                        "\n" +
                        "【采收加工】花期较长，8~11月初采摘，以9月采摘为宜，晒干。全草7~9月采收，晒干。\n" +
                        "\n" +
                        "【药材性状】头状花序类球形或长圆球形，长2~2.5厘米，宽1.5~2厘米，由多数稠密覆瓦状排列的花集合而成；花序基部具2枚叶状圆三角形的总苞片，绿色，总苞片的背面密被细长的白柔毛，腹面的毛短而稀。每花有膜质苞片3枚，外片短小卵形，内轮2片，淡紫色或紫红色，长于花被，花被5片，贮久色淡。胞果类球形。气微弱，味淡。\n" +
                        "\n" +
                        "【性味归经】性平，味甘。归肺经、肝经。\n" +
                        "\n" +
                        "【功效与作用】化痰止咳平喘、清肝明目经止惊。属化痰止咳平喘药下属分类的止咳平喘药。\n" +
                        "\n" +
                        "【临床应用】用量3~10克。用治急慢性支气管炎、支气管哮喘、百日咳等；头痛、目赤、肝火头痛眩晕；小儿惊风、夜啼；赤白痢疾。\n" +
                        "\n" +
                        "【主要成分】含千日红苷I、千日红苷Ⅱ、千日红苷Ⅲ、千日红苷V、千日红苷Ⅵ，苋菜红苷、异苋菜红苷、甜菜苷。小鼠酚红排泌法，表明本品的提出物总黄酮、皂苷和挥发油均具有祛痰作用；组胺引喘法证明，水煎液和总氨基酸部分对豚鼠有一定平喘作用。\n" +
                        "\n" +
                        "【使用禁忌】尚不明确。\n" +
                        "\n");
                chineseMedicineDao.insert(medicine129);

                ChineseMedicine medicine130=new ChineseMedicine();
                medicine130.setName("青牛胆");
                medicine130.setSortType("Q");
                medicine130.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=edbeadcb5866d0166a14967af642bf62/79f0f736afc37931de30a82fe1c4b74543a9115f.jpg");
                medicine130.setData("【中药名】青牛胆 qingniudan\n" +
                        "\n" +
                        "【别名】地苦胆、九牛胆、金牛胆。\n" +
                        "\n" +
                        "【英文名】Limacia Sagittata。\n" +
                        "\n" +
                        "【来源】防己科植物青牛胆Tinospora sagittata( Oliv.)Gagnep.的块根。\n" +
                        "\n" +
                        "【植物形态】常绿缠绕藤本。块根卵圆形或近圆形，表面黄褐色或黄色。茎具纵槽纹。叶互生，箭状披针形，长7~17厘米，宽3~6厘米，先端长渐尖或渐尖，基部箭形或戟状箭形，两面被短硬毛，主脉5。雌雄异株，总状花序；萼片6，2轮；花瓣6，较萼短，雄花雄蕊6枚；雌花花瓣较小，匙形，退化雄蕊棒状，心皮3~4。核果近球形，红色，内果皮坚硬，背线具不明显的疣状突起。花期3~5月，果期8~10月。\n" +
                        "\n" +
                        "【产地分布】生于灌木林下石隙间。分布于湖北、湖南、陕西、云南、贵州等地。\n" +
                        "\n" +
                        "【采收加工】秋季挖根，除去须根和茎，洗净晒干。\n" +
                        "\n" +
                        "【药材性状】长圆形、陀螺形或不规则圆块状，两端圆钝或稍尖，大小悬殊，长3~ 15厘米，直径1.5~9厘米。表面黄棕色、绿黄色或灰棕色，有细皱纹或较深而密的纵横皱纹。质坚实，不易击破，横切面黄白色，粉性，皮部甚狭，形成层环隐约可见，木部外缘可见少数导管束，呈放射状。气微，味极苦。\n" +
                        "\n" +
                        "【性味归经】性寒，味苦。无归经。\n" +
                        "\n" +
                        "【功效与作用】清热解毒、利咽，止痛。属清热药下属分类的清热解毒药。\n" +
                        "\n" +
                        "【I临床应用】用量3~9克，水煎服。用治急性咽喉炎、扁桃体炎、急性胃肠炎、菌痢、胃及十二指肠溃疡、胃痛、痈肿疮疖、瘰疬、蛇咬伤等症。外用适量，研末久喉或醋磨涂敷患处。\n" +
                        "\n" +
                        "【主要成分】含金果榄酮A、金果榄酮B、金果榄酮C、金果榄酮D、表金果榄苷、掌叶防己碱、药根碱及其他微量生物碱。现代药理研究表明，有明显降血糖作用和抗肿瘤作用。水煎剂对小鼠肉瘤180有抑制作用。\n" +
                        "\n" +
                        "【使用禁忌】尚不明确。");
                chineseMedicineDao.insert(medicine130);

                ChineseMedicine medicine131=new ChineseMedicine();
                medicine131.setName("全蝎");
                medicine131.setSortType("Q");
                medicine131.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike220%2C5%2C5%2C220%2C73/sign=d36a97014aed2e73e8e48e7ee668caee/a1ec08fa513d2697bcfe70d855fbb2fb4316d802.jpg");
                medicine131.setData("【中药名】全蝎 quanxie\n" +
                        "\n" +
                        "【别名】全虫、蝎子、伏背虫。\n" +
                        "\n" +
                        "【英文名】Scorpio。\n" +
                        "\n" +
                        "【来源】钳蝎科动物东亚钳蝎Buthus martensii Karsch的全体。\n" +
                        "\n" +
                        "【动物形态】成体长约6厘米。头胸部和前腹部绿褐色，后腹部土黄色。头胸部背甲呈梯形。侧眼3对。胸板三角形，螯肢的钳状上肢有2齿。触肢钳状，上下肢内侧有12行颗粒斜列。第三、四对步足胫节有距，各步足末端有2爪和1距。前腹部的前背板上有5条隆脊线。生殖板由两个半圆形甲片组成。栉状器有16～25枚齿。后腹部尾状，前四节各有10条隆脊线，第五节仅有5条，第六节的毒针下方无距。\n" +
                        "\n" +
                        "【产地分布】多栖息于山坡石砾、树皮、落叶下以及墙隙土穴、荒地的潮湿阴暗处。分布于辽宁、河北、山东、河南、江苏、福建、台湾。现已有人工饲养。\n" +
                        "\n" +
                        "【采收加工】春至秋季捕捉，捕后放入清水中呛死，然后倒人锅内水煮，水量以没过蝎子为度。每500克蝎子加食盐150克，煮3～4小时，至水将尽时进行检查，用手指掐其尾端，如能挺直竖立，背见抽沟，腹瘪，即可捞出，置通风处阴干，用时以清水漂去盐质。\n" +
                        "\n" +
                        "【药材性状】头胸部和前腹部呈扁平长椭圆形，后腹部呈尾状，皱缩弯曲，完整者约6厘米。头胸部绿褐色，前面有1对短小的鳌肢及1对较长大的先端钳状脚须，形似蟹鳌，背面覆有梯形背甲，腹面有足4对。前腹部7节，背甲上有5条隆脊线；后腹部棕黄色，6节，节上有纵沟，末端有锐钩状毒刺。气微腥，味咸。\n" +
                        "\n" +
                        "【性味归经】性平，味辛，有毒。归肝经。\n" +
                        "\n" +
                        "【功效与作用】息风镇痉、攻毒散结、通络止痛。属平肝息风药下属分类的息风止痉药。\n" +
                        "\n" +
                        "【临床应用】用量2.5～4.5克，煎服；外用适量。用于小儿惊风、抽搐痉挛、中风口歪、半身不遂、破伤风、风湿顽痹、偏正头痛、疮疡、瘰疬。\n" +
                        "\n" +
                        "【主要成分】含全蝎毒素，为一种含碳、氢、氧、氮及硫等元素的毒性蛋白，与蛇的神经毒类似。此外，尚含三甲胺、甜菜碱、牛磺酸、胆甾醇、卵磷脂等。本品具有一定的抗惊厥作用；全蝎制剂给狗灌胃、肌注及静注，均有显著、持久的降压作用；全蝎煎剂或其提取物的降压作用，较其浸剂作用持久。用蝎毒素作用于蛙、豚鼠、家兔等动物，均可产生中毒现象。\n" +
                        "\n" +
                        "【使用禁忌】孕妇禁用。");

                chineseMedicineDao.insert(medicine131);

                ChineseMedicine medicine132=new ChineseMedicine();
                medicine132.setName("青天葵");
                medicine132.setSortType("Q");
                medicine132.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=a6b9cd9d35fae6cd18b9a3336eda6441/f7246b600c338744da6a03d6510fd9f9d62aa0e8.jpg");
                medicine132.setData("【中药名】青天葵 qingtiankui\n" +
                        "\n" +
                        "【别名】青莲、珍珠叶、半边伞。\n" +
                        "\n" +
                        "【英文名】Herba Nervilia Plicatae\n" +
                        "\n" +
                        "【来源】兰科植物青天葵Nervilia fordii (Hance) Schltr.的叶或带球茎的叶。\n" +
                        "\n" +
                        "【植物形态】多年生草本，块茎球形或扁球形，叶于花后长出，基生，通常1片，少有2片，叶片近圆形或卵状心形，边缘微波状，基部心形，叶脉隆起，约20条，横脉网状，叶柄圆柱形，有凹槽，下部被紫红色的叶鞘包围。春季开花，总状花序有花5~10朵，萼片和花瓣近相等，线状披针形，唇瓣白色，具紫脉，内面密生长柔毛，3裂，侧裂片合抱蕊柱，中裂片顶端圆或稍尖，无距。蒴果椭圆形。\n" +
                        "\n" +
                        "【产地分布】生于石灰岩山地疏林下或田边、溪旁肥沃阴湿处。分布于广东、海南、广西、云南等地。\n" +
                        "\n" +
                        "【采收加工】夏季采收。广西地区采叶，洗净，晒至半干时用手搓成粒状，边晒边搓，至晒干为度。广东地区挖取全株，除去根茎，洗净曝晒，将叶片包裹球茎，搓成球状，再晒至足干为度。\n" +
                        "\n" +
                        "【药材性状】干燥叶灰褐色至灰绿色，卷成团粒状，叶柄扁平，有纵向条纹。有的带球茎，淡黄色，气香，味微甘。\n" +
                        "\n" +
                        "【性味归经】性凉，味甘。归心经、肺经、肝经。\n" +
                        "\n" +
                        "【功效与作用】润肺止咳、清热解毒。属清热药下属分类的清热解毒药。\n" +
                        "\n" +
                        "【临床应用】用量9~15克，煎服；或浸酒。外用：捣敷。用治肺结核咳嗽、支气管炎、小儿肺炎、疮疖肿痛、跌打损伤等。\n" +
                        "\n" +
                        "【主要成分】含黄酮类、生物碱类、氨基酸等成分。对喘息型慢性气管炎症有显著作用，对治疗鼻咽癌有一定的缓解作用。\n" +
                        "\n" +
                        "【使用禁忌】阳虚者慎服。\n" +
                        "\n");

                chineseMedicineDao.insert(medicine132);

                ChineseMedicine medicine133=new ChineseMedicine();
                medicine133.setName("天南星");
                medicine133.setSortType("T");
                medicine133.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=ba527ee4e3f81a4c323fe49bb6430b3c/4034970a304e251f23ab2868af86c9177e3e53c6.jpg");
                medicine133.setData("【中药名】 天南星 tiannanxing\n" +
                        "\n" +
                        "【别名】山苞米、独脚莲、蛇包谷。\n" +
                        "\n" +
                        "【英文名】Arisaematis Rhizoma。\n" +
                        "\n" +
                        "【药用部位】天南星科植物天南星Arisaema erubescens (Wall.) Schott的块茎。\n" +
                        "\n" +
                        "【植物形态】多年生草本。块茎扁球形。叶1片，稀2片，叶柄长达70厘米，绿色，叶片放射分裂，裂片7～20，无柄，披针形、长圆形至椭圆形，先端长渐尖，成线形长尾，尾长7厘米，基部狭窄，上面深绿色，下面粉绿色。花单性，雌雄异株，无花被，肉穗花序由叶柄鞘部抽出，具褐色斑纹；佛焰苞绿色，展开部分外卷，然后扩大成檐部，先端渐窄呈线形尾状；雄花序长2～2.5厘米，花密，雄蕊2～4；雌花序长约2厘米，下部常具钻形中性花。浆果红色。花期5～7月，果期8～9月。\n" +
                        "\n" +
                        "【产地分布】生于林下、灌丛中阴湿地。分布于东北及山东等地。\n" +
                        "\n" +
                        "【采收加工】秋、冬季茎叶枯萎时采挖，除去须根及外皮，干燥。\n" +
                        "\n" +
                        "【药材性状】扁球形，表面类白色或淡棕色，较光滑，顶端有凹陷的茎痕，周围有麻点状根痕，有的块茎周边有小扁球状侧芽。质坚硬，不易破碎，断面不平坦，白色，粉性。气微辛，味麻辣。\n" +
                        "\n" +
                        "【性味归经】性温，味苦、辛。归肺经、肝经、脾经。\n" +
                        "\n" +
                        "【功效与作用】燥湿化痰、祛风止痉、散结消肿。属化痰止咳平喘药下属分类的温化寒痰药。\n" +
                        "\n" +
                        "【临床应用】一般炮制后用，用量3～9克；用治顽痰咳嗽、风痰眩晕、中风痰壅、口眼歪斜、半身不遂、癫痫、惊风、破伤风。生用外治痈肿、蛇虫咬伤。\n" +
                        "\n" +
                        "【药理研究】果实含类似毒蕈碱样物质。有抗肿瘤作用，疗效较肯定，可望成为抗癌新药物，也可成为抗癫痫的辅助药。另有祛痰，镇静，抗惊厥，抗心律失常，抗氧化等作用。\n" +
                        "\n" +
                        "【主要成分】天南星的块茎含三萜皂苷、安息香酸、黏液质、氨茎酸、甘露醇、生物碱、L-脯氨酰-L-缬氨酸酐、L-缬氨酰-L-缬氨酸酐、芹菜素、掌叶半夏碱、芹菜素-6-C-阿拉伯糖-8-C-半乳糖苷、赖氨酸、胡萝卜苷、没食子酸、夏佛托苷、β-谷固醇等。\n" +
                        "\n" +
                        "【使用禁忌】孕妇慎用，有毒，生品内服宜慎，阴虚燥咳、热极、血虚动风者禁服。\n" +
                        "\n" +
                        "【配伍药方】①治头痛，偏正头风，痛攻眼目额角：天南星、川乌各等分，共研极细末，同莲须葱白捣烂作饼。贴太阳穴。(《全国中药成药处方集》止痛膏)\n" +
                        "\n" +
                        "②治痈疽疮肿：天南星60克，赤小豆90克，白及120克。上三味，各为细末，和匀，冷水调，摊上四面肿处，用绢压之。(《刘绢子鬼遗方》收脓散)\n" +
                        "\n" +
                        "③治乳赤肿、欲作痈者：天南星为细末，生姜自然汁调涂，自散。才作便用之。(《百一选方》)\n" +
                        "\n" +
                        "④治瘰疬：天南星、半夏等分为末，米醋或鸡子清调敷。(《潜斋简效方》)\n" +
                        "\n" +
                        "⑤治瘿瘤：生南星末，醋调敷之。(《外科证治全书》)");

                chineseMedicineDao.insert(medicine133);

                ChineseMedicine medicine134=new ChineseMedicine();
                medicine134.setName("土鳖虫");
                medicine134.setSortType("T");
                medicine134.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=d92505583d6d55fbd1cb7e740c4b242f/14ce36d3d539b600c5416631e950352ac75cb7ad.jpg");
                medicine134.setData("【中药名】土鳖虫 tubiechong\n" +
                        "\n" +
                        "【别名】地鳖虫、土元、蟅虫。\n" +
                        "\n" +
                        "【英文名】Eupolyphaga、Steleophaga。\n" +
                        "\n" +
                        "【药用部位】鳖蠊科昆虫地鳖Eupolyphaga sinensis Walker或冀地鳖Steleophagphancyi (Boleny)的雌虫全体。\n" +
                        "\n" +
                        "【动物形态】地鳖：雌雄异形。雌虫无翅，长约3厘米，体上下扁平，黑色带光泽。头小，向腹面弯曲。口器咀嚼式，大颚坚硬。复眼发达，肾形；单眼2个。触角丝状，长而多节。前胸背板盾状，前狭后阔，盖于头上。雄虫前胸呈波状纹，有缺刻，具翅2对。 冀地鳖：雌虫体宽卵圆形，较地鳖宽。虫体表面暗黑色无光泽。前胸背板前缘及身体周围具红褐色或黄褐色边缘。体背面有密集的小颗粒状突起，无翅。雄虫有翅，体灰黑色，除前胸背板前缘处有明显的淡色宽边外，身体其他部分无细碎斑纹。\n" +
                        "\n" +
                        "【产地分布】地鳖生活于地下或沙土间，多见于粮仓底下或油坊阴湿处。全国大部分地区均有分布。冀地鳖多生活于厨房、灶脚及阴湿处。分布于河北、河南、陕西、甘肃、青海及湖南等地。\n" +
                        "\n" +
                        "【采收加工】5～8月采收。捕捉后，置沸水中烫死，晒干或烘干。\n" +
                        "\n" +
                        "【药材性状】地鳖：扁平卵形，长1.3～3厘米，宽1.2～2.4厘米。前端较窄，后端宽，背部紫褐色，具光泽，无翅。前胸背板较发达，盖住头部。腹背板9节，呈覆瓦状排列。腹面红棕色，头较小，有1对丝状触角，常脱落；胸部有3对足。腹部有横环节。气腥，味微咸。冀地鳖：长椭圆形。背部黑棕色，边缘具淡黄褐色斑块及黑色小点。\n" +
                        "\n" +
                        "【性味归经】性寒，味咸，有小毒。归肝经。\n" +
                        "\n" +
                        "【功效与作用】破瘀血，续筋骨。属活血化瘀药下属分类的活血疗伤药。\n" +
                        "\n" +
                        "【临床应用】常用量3～9克，煎服，用治筋骨折伤、瘀血经闭、癥瘕痞块。\n" +
                        "\n" +
                        "【药理研究】改善心脑血管功能，具有显著的调脂和对白血病细胞有抑制等作用。动物实验表明，本品水提物有抗凝血作用。还有抗缺氧、抗突变、降低总胆固醇和提高高密度脂蛋白/胆固醇比值的作用。\n" +
                        "\n" +
                        "【化学成分】含蛋白质、挥发油、脂肪酸、氨基酸、甾醇及多种微量元素。另含各种脂肪醛和芳香醛及二氯苯和二甲基二硫醚等其他中药少见的成分。\n" +
                        "\n" +
                        "【使用禁忌】年老体弱及月经经期者慎服，孕妇禁用。\n" +
                        "\n" +
                        "【配伍药方】①治血鼓，腹皮上有青筋：桃仁24克，大黄1.5克，鳖虫三个，甘遂1.5克(为末冲服)。水煎服。与膈下逐瘀汤轮流服之。(《医林改错》下瘀血汤)\n" +
                        "\n" +
                        "②治折伤，接骨：土鳖焙存性，为末，每服6～9克。(《医方摘要》)\n" +
                        "\n" +
                        "③治小儿脐赤肿或脓血清水出者：干鳖火煅为灰，研末，敷之。(《小儿卫生总微论方》)\n" +
                        "\n" +
                        "④治瘘疮肿：干地鳖末、麝香各研少许。上二味，研匀。干掺或贴，随干湿治之。(《圣济总录》)");
                chineseMedicineDao.insert(medicine134);

                ChineseMedicine medicine135=new ChineseMedicine();
                medicine135.setName("天竺黄");
                medicine135.setSortType("T");
                medicine135.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545408796&di=d6e3d07a3c99cfc63aa3e8d64d104ef4&imgtype=jpg&er=1&src=http%3A%2F%2Ffile.cnkang.com%2Fcnkfile1%2FM00%2F0F%2FCE%2FoYYBAFk2mFiABfh_AANsXyrMKUk07.jpeg");
                medicine135.setData("【中药名】天竺黄 tianzhuhuang\n" +
                        "\n" +
                        "【别名】竹黄、竹膏、天竹黄、竹糖。\n" +
                        "\n" +
                        "【英文名】Bambusae Concretio Silicea。\n" +
                        "\n" +
                        "【药用部位】禾本科植物青皮竹的秆内分泌液干燥后Bambus textilis McClure.的块状物。\n" +
                        "\n" +
                        "【植物形态】秆高8～10米，尾梢弯垂，下部挺直；绿色，幼时被白蜡粉，贴生或疏或密的淡棕色刺毛，以后变为无毛；节处平坦，无毛；分枝常自秆中、下部第7节至第11节开始，以数枝乃至多枝簇生，中央一枝略微较粗长。箨鞘早落；革质，先端稍向外缘倾斜而呈不对称的宽拱形，箨耳较小，大耳狭长圆形至披针形；箨舌边缘齿裂；箨片直立，卵状狭三角形；叶鞘无毛，背部具脊；叶耳通常呈镰刀形；叶舌极低矮，边缘啮蚀状，无毛；叶片线状披针形至狭披针形，上面无毛，下面密生短柔毛，先端渐尖具钻状细尖头，基部近圆形或楔形。假小穗单生或多枚簇生于花枝各节，鲜时暗紫色，干时古铜色，线状披针形；先出叶宽卵形，具2脊；具芽苞片2片或3片，宽卵形；小穗含小花5～8朵；小穗轴节间为半圆柱形或扁形，顶端膨大；颖1片，宽卵形，具21脉；外稃椭圆形，具25脉；内稃披针形，具2脊，脊间20脉；鳞片不相等，边缘被长纤毛；花丝细长，花药黄色；子房宽卵球形，花柱被短硬毛，柱头3。未见成熟颖果。\n" +
                        "\n" +
                        "【产地分布】常栽培于低海拔地的河边、村落附近。分布于广东和广西，现我国西南、华中、华东各地均有引种栽培。\n" +
                        "\n" +
                        "【采收加工】秋、冬季采收，砍断秆，剖取竹黄，晒干。\n" +
                        "\n" +
                        "【药材性状】不规则片块状或颗粒，大小不一。表面灰蓝色、灰黄色或灰白色，有的洁白色，半透明，略带光泽。体轻，质硬而脆，易破碎，吸湿性强。无臭，味淡。\n" +
                        "\n" +
                        "【性味归经】性寒，味甘。归心经、肝经。\n" +
                        "\n" +
                        "【功效与作用】清热豁痰、凉心定惊。属化痰止咳平喘药下属分类的清化热痰药。\n" +
                        "\n" +
                        "【临床应用】用量3～9克，煎服。用治热病神昏、中风痰迷、小儿痰热惊痫、抽搐、夜啼。\n" +
                        "\n" +
                        "【药理研究】具镇痛、局部麻醉的作用，可使离体蛙心收缩力减弱、心率减慢。另外，可引起光敏性皮炎。\n" +
                        "\n" +
                        "【化学成分】含多糖、氨基酸、竹红菌素A、竹红菌素B、竹红菌素C、甘露醇、硬脂酸、竹黄色素A、竹黄色素B、竹黄色素C。另含钠、镁、铝、硅等14种无机元素；14种氨基酸及生物碱。\n" +
                        "\n" +
                        "【使用禁忌】无实热痰火者慎服，脾虚胃寒便溏者禁服。\n" +
                        "\n" +
                        "【配伍药方】①治哮喘即发：陈皮3克，半夏(制)、竹黄各4.5克，麻黄(先煎去沫)2.1克，苏子1.8克，沉香(研末冲药)1.2克，细辛1.5克，炙甘草1.8克，加生姜二片煎。(《不知医必要》麻黄苏子汤)\n" +
                        "\n" +
                        "②治小儿疳积：雄黄(研，水飞)、天竺黄各6克，牵牛末3克，上同再研，面糊为丸，粟米大。每服二至五丸，食后，薄荷汤下。大者加丸数。(《小儿药证直诀》牛黄丸)\n" +
                        "\n" +
                        "③治鼻衄不止：天竺黄、川芎各0.3克，防己15克，上三味，捣研为散。每服3克，新汲水调下。肺损吐血，用药6克，生面3克，水调下。并食后服。(《圣济总录》天竺黄散)\n" +
                        "\n" +
                        "④掺口疮：天竺黄、月石各等分，冰片少许。为末掺之。(《景岳全书》)\n" +
                        "\n");
                chineseMedicineDao.insert(medicine135);

                ChineseMedicine medicine136=new ChineseMedicine();
                medicine136.setName("檀香");
                medicine136.setSortType("T");
                medicine136.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545408938&di=38d8c5804e57427c8570174d4134e876&imgtype=jpg&er=1&src=http%3A%2F%2Fimg0.114pifa.com%2Fg%2Fimg%2Fibank%2F2014%2F521%2F469%2F1589964125_362173448.jpg");
                medicine136.setData("【中药名】檀香 tanxiang\n" +
                        "\n" +
                        "【别名】白檀、真檀、白檀香、旃檀、檀香木。\n" +
                        "\n" +
                        "【英文名】Santali Albi Lignum。\n" +
                        "\n" +
                        "【药用部位】檀香科植物檀香树干Santalum album L.的心材。\n" +
                        "\n" +
                        "【植物形态】常绿乔木，高6～9米，具寄生根，树皮棕灰色，粗糙或有纵裂，多分枝，枝柔软，开展，幼枝圆形，光滑无毛。单叶对生，革质，椭圆状卵形或卵状披针形，先端渐尖，基部楔形，全缘，上表面绿色，下表面苍白色。3歧或聚伞状圆锥花序，花梗约与花被管等长，花小，初为淡黄色后变为紫黄色，花被钟形，先端4裂，裂片卵圆形，密腺4，略呈圆形，着生于花被管的中部，与花被片互生。雄蕊4枚，略与雌蕊等长，花药2室，纵裂，花丝线形。子房半下位，花柱柱状，柱头3裂。核果球形，成熟时黑色，肉质多汁，内果皮坚硬，具3短棱。种子圆形，光滑无毛。\n" +
                        "\n" +
                        "【产地分布】原产于印度、澳大利亚、印度尼西亚和南亚等地，野生或栽培。我国广东、海南、云南有引种栽培。\n" +
                        "\n" +
                        "【采收加工】采伐木材后，切成段，除去树皮和边材即得。\n" +
                        "\n" +
                        "【药材性状】木段长短不一，圆柱形，有的略弯曲。外表面灰黄色或黄褐色，光滑细腻，有的具疤节或纵裂，横截面呈棕黄色，显油迹；棕色年轮明显或不明显，纵向劈开纹理顺直。质坚实，不易折断。气清香，燃烧时香气更浓；味淡，嚼之微有辛辣感。\n" +
                        "\n" +
                        "【性味归经】性温，味辛。归肺经、胃经、脾经、心经。\n" +
                        "\n" +
                        "【功效与作用】行气温中、开胃止痛。属理气药。\n" +
                        "\n" +
                        "【临床应用】用量2～5克，煎服；或入丸、散。外用，适量，磨汁涂。用治寒凝气滞、胸痛、腹痛、胃痛食少、冠心病、心绞痛。\n" +
                        "\n" +
                        "【药理研究】有较强的抗菌作用。挥发油对痢疾杆菌、乌型结核杆菌及金黄色葡萄球菌均有抑制作用；有利尿作用。此外，对离体兔小肠有麻痹作用。临床用檀香复方治疗心绞痛、萎缩性胃炎、浅表性胃炎、胃痛、痛经、乳腺增生等。\n" +
                        "\n" +
                        "【化学成分】含挥发油3%～5%，其主要成分为a-檀香醇及β-檀香醇，占90%以上，并含a-檀香萜烯及β-檀香萜烯、檀香烯、檀香二环酮、檀香酸、檀香酮、檀油酸、檀油醇、紫檀萜醛等。\n" +
                        "\n" +
                        "【使用禁忌】如阴虚火盛，有动血致嗽者，勿用之。\n" +
                        "\n" +
                        "【配伍药方】①治心腹冷痛：白檀香9克(为极细末)，干姜15克。泡汤调下。(《本草汇言》)\n" +
                        "\n" +
                        "②治气厥：白豆蔻，丁香、檀香、木香各9克，藿香、甘草(炙)各24克，砂仁12克。上为末。每服6克，入盐少许，沸汤点服。(《丹溪心法》调气散)\n" +
                        "\n" +
                        "③治阴寒霍乱：白檀香、藿香梗、木香、肉桂各4.5克。为极细末。每用3克，炒姜15克，泡汤调下。(《本草汇言》)\n" +
                        "\n" +
                        "④治噎膈饮食不下：白檀香4.5克，茯苓、橘红各6克。俱为极细末，人参汤调下。(《本草汇言》)\n" +
                        "\n" +
                        "⑤治神经性胃肠病，呕吐下痢，胸闷腹痛：檀香细粉10克，沉香细粉3克，甘草细粉5克，菖蒲根(磨粉)10克。混合，密贮瓶中勿泄气。每日3回，每回1克，食前温开水送服。(《现代实用中药》)");

                chineseMedicineDao.insert(medicine136);

                ChineseMedicine medicine137=new ChineseMedicine();
                medicine137.setName("通草");
                medicine137.setSortType("T");
                medicine137.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=a488ac5cf703918fc3dc359830544df2/b58f8c5494eef01ffd86e379e0fe9925bd317df8.jpg");
                medicine137.setData("【中药名】通草 tongcao\n" +
                        "\n" +
                        "【别名】大通草、通花、方通草、寇脱、白通草、五加风。\n" +
                        "\n" +
                        "【英文名】Tetrapanacis Medulla\n" +
                        "\n" +
                        "【药用部位】五加科植物通脱木Tetrapanax papyriferus (Hook.)K.koch的茎髓。\n" +
                        "\n" +
                        "【植物形态】灌木或小乔木，幼枝、叶背及花序被白色或褐色星状毛。髓大，白色，纸质。叶大，聚生茎顶，直径50～70厘米，5～11掌状分裂，每一裂片又2～3裂，基部心形，全缘或有粗齿；叶柄粗长；托叶膜质，锥形，基部合生。多数球状伞形花序集成大型复圆锥花序；花小，花萼不明显；花瓣4，白色；雄蕊4枚，子房下位，2室，花柱2，离生。核果状浆果扁球形，紫黑色。花期10～12月。\n" +
                        "\n" +
                        "【产地分布】生于向阳山坡、屋旁、路边及杂木林中。分布于湖南、湖北、福建、台湾等地。\n" +
                        "\n" +
                        "【采收加工】秋季割取茎，截成段，趁鲜取出髓部，理直，晒干。将茎髓加工成方形薄片，称“方通草”。切下的边条，称“丝通草”。\n" +
                        "\n" +
                        "【药材性状】圆柱形，长20～40厘米，直径1～2.5厘米。表面白色或淡黄色，有浅纵沟纹。体轻，质松软，稍有弹性，易折断，断面平坦，显银白色光泽，中部有直径0.3～1.5厘米的空心或半透明的薄膜，纵剖面呈梯状排列，实心者少见。气微，味淡。\n" +
                        "\n" +
                        "【性味归经】性微寒，味甘、淡。归肺经、胃经。\n" +
                        "\n" +
                        "【功效与作用】清热利尿、通气下乳。属利水渗湿药下属分类的利尿通淋药。\n" +
                        "\n" +
                        "【临床应用】用量3～5克，水煎服。用治湿热尿赤、淋病涩痛、水肿尿少、乳汁不下。\n" +
                        "\n" +
                        "【药理研究】有利尿排钾作用。\n" +
                        "\n" +
                        "【化学成分】含肌醇。另含多聚戊糖约14.3%，多聚甲基戊糖约3%以及阿拉伯糖、葡萄糖、果糖、乳糖、果胶、半乳糖醛酸、灰分、脂肪、戊聚糖及糖醛酸等成分，还含天冬氨酸、苏氨酸、谷氨酸、苯丙氨酸等13种氨基酸以及钙、钡、镁、铁等18种微量元素以及木质素。\n" +
                        "\n" +
                        "【使用禁忌】气阴两虚、中寒、内无湿热及孕妇慎服。\n" +
                        "\n" +
                        "【配伍药方】①治气热淋疾，小便数急痛，小腹虚满：通草煎汤。并葱食之。(《普济方》)\n" +
                        "\n" +
                        "②治水肿、小便不利，淋浊：通草、茯苓皮、滑石、泽泻、白术各9克。水煎服。(《常用中草药图谱》)\n" +
                        "\n" +
                        "③治膀胱积热尿闭：通草9克，车前草9克，龙胆草9克，瞿麦9克。水煎服。(《曲靖专区中草药》)\n" +
                        "\n" +
                        "④治急性肾炎：通草6克，茯苓皮12克，大腹皮9克。水煎服。(《浙江药用植物志》)\n" +
                        "\n" +
                        "⑤冶湿热稽留，小便不利：通草、白蔻仁各3克，金银花、薏苡仁各9克，滑石12克，苦杏仁6克。煎服。(《安徽中草药》)\n" +
                        "\n" +
                        "⑥治产后乳汁不通：通草9克，与猪蹄炖汤同服，或通草9克，王不留行4.5克，水煎服。体弱加炙黄芪12克，同煎服。(《青岛中草药手册》)\n" +
                        "\n" +
                        "⑦治月经不调：通草6克，归尾3克，桃仁12克，红花6克。煎服。(《云南中草药选》)\n" +
                        "\n" +
                        "⑧治白带：通草茎髓30～60克。炖肉吃。(《恩施中草药手册》)\n" +
                        "\n");

                chineseMedicineDao.insert(medicine137);

                ChineseMedicine medicine138=new ChineseMedicine();
                medicine138.setName("天仙藤");
                medicine138.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=15d6c678cb177f3e0439f45f11a650a2/0bd162d9f2d3572cd5c1c8128813632763d0c3d9.jpg");
                medicine138.setData("【中药名】天仙藤 tianxianteng\n" +
                        "\n" +
                        "【别名】马兜铃藤、青木香藤、都淋藤、长痧藤、香藤。\n" +
                        "\n" +
                        "【英文名】Aristolochiae Herba\n" +
                        "\n" +
                        "【药用部位】马兜铃科植物北马兜铃Aristolochia contorta Bunge的茎叶。\n" +
                        "\n" +
                        "【植物形态】多年生缠绕或匍匐草本。有香气。根圆柱形，外皮黄褐色。茎扭曲，有棱。叶互生，三角状心形至阔卵状心形，先端短锐尖或钝，基部心形，基出脉5～7条，下面略带灰白色。花3～10朵，簇生于叶腋；花梗细长；花被紫色，斜喇叭状，基部膨大呈球状；雄蕊6，贴生于肉质花柱上；子房下位，花柱6，愈合成柱体，柱头短。蒴果倒广卵形或椭圆状倒卵形，下垂，熟时裂成6瓣。种子扁平，有翅。花期7～8月，果期9月。\n" +
                        "\n" +
                        "【产地分布】生于路旁、沟边阴湿处及山坡丛林中。主产于浙江、湖北、江苏、陕西、江西、河南。\n" +
                        "\n" +
                        "【采收加工】7～9月采收，除去杂质，晒干。\n" +
                        "\n" +
                        "【药材性状】茎细长圆柱形，略扭曲，直径1～3毫米；表面黄绿色或淡黄褐色，有纵棱及节；质脆，易折断，断面有数个大小不等的维管束。叶多皱缩，破碎，暗绿色或淡黄褐色，基生脉明显，叶柄细长。气清香，味淡。\n" +
                        "\n" +
                        "【性味归经】性温，味苦。归肝经、脾经、肾经。\n" +
                        "\n" +
                        "【功效与作用】行气活血、利水消肿。属活血化瘀药下属分类的活血止痛药\n" +
                        "\n" +
                        "【临床应用】用量4.5～9克，水煎服；外用适量，煎水洗或捣烂敷。治疗脘腹刺痛、关节痹痛、妊娠水肿。\n" +
                        "\n" +
                        "【药理研究】本品有降血压作用。\n" +
                        "\n" +
                        "【化学成分】含木兰碱、轮环藤酚碱、马兜铃酸、β-谷固醇。\n" +
                        "\n" +
                        "【使用禁忌】本品含马兜铃酸，长期大量使用能引起急性肾衰、慢性肾衰、肾小管坏死、尿道癌等肾病，临床应慎用。儿童及老年人慎用；孕妇、婴幼儿及肾功能不全者禁用。\n" +
                        "\n" +
                        "【配伍药方】①治疝气作痛：天仙藤30克，好酒一碗，煮至半碗服之。(《孙天仁集效方》)\n" +
                        "\n" +
                        "②治风湿性关节炎：天仙藤、威灵仙、海桐皮各9克。水煎服。(《秦岭巴山天然药物志》)\n" +
                        "\n" +
                        "③治毒蛇咬伤，痔疮肿痛：天仙藤鲜品捣烂，敷患处。(《东北常用中药手册》)\n" +
                        "\n" +
                        "④治乳腺炎：鲜天仙藤适量，揉软外敷，每日换药1次。(《江西草药》)\n" +
                        "\n" +
                        "⑤治赤流丹肿：天仙藤30克，焙干为末，乳香(研)3克。上每3克，温酒下。(《证治准绳》乳香散)");

                chineseMedicineDao.insert(medicine138);

                ChineseMedicine medicine139=new ChineseMedicine();
                medicine139.setName("土荆皮");
                medicine139.setSortType("T");
                medicine139.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=eb649790c4fcc3cea0cdc161f32cbded/e7cd7b899e510fb3a3506c25d533c895d1430c32.jpg");
                medicine139.setData("【中药名】土荆皮 tujingpi\n" +
                        "\n" +
                        "【别名】土槿皮、金钱松皮、罗汉松皮、荆树皮。\n" +
                        "\n" +
                        "【英文名】Pseudolaricis Cortex\n" +
                        "\n" +
                        "【药用部位】松科植物金钱松Pseudolarix kaempferi Gord.的根皮或近根树皮。\n" +
                        "\n" +
                        "【植物形态】落叶乔木，高20～40厘米。树干直立，枝分长枝和短枝，长枝上的叶螺旋状散生，短枝上的叶15～30枚簇生，辐射平展，秋后显金黄色。叶线形或倒披针状线形，扁平，柔软，长3～7厘米，宽2～3.5毫米，先端尖，淡绿色，下面沿隆起中脉的两侧有气孔线。花单性同株；雄球花葇荑状，数个或十余个聚生在短枝顶端，黄色；雌球花单生短枝顶端，紫红色。球果淡红棕色，卵形，有短柄；种鳞木质，卵形，腹面基部有种子2枚，种子具长翅，与种鳞等长。花期4月，球果10月成熟。\n" +
                        "\n" +
                        "【产地分布】生长于海拔100～1500米地带，散生在针叶树、阔叶树林中。有栽培。多分布于长江中下游各省区。\n" +
                        "\n" +
                        "【采收加工】秋末挖根，洗净，剥取根皮，刮去外皮后晒干，夏季剥取树皮，晒干。\n" +
                        "\n" +
                        "【药材性状】根皮呈板状或不规则长条状，较小者呈槽状，扭曲而稍卷，厚2～5毫米。外表面灰黄色，粗糙，有皱纹及横长皮孔，外皮常呈鳞片状剥落，露出红棕色的内皮；内表面黄棕色至红棕色，平坦，有细密的纵向纹理。质韧，折断面裂片状，可成层剥离。气微，味苦而涩。\n" +
                        "\n" +
                        "【性味归经】性温，味辛。归肺经、脾经。\n" +
                        "\n" +
                        "【功效与作用】杀虫、止痒。属杀虫止痒药。\n" +
                        "\n" +
                        "【临床应用】外用适量，醋或酒浸涂擦，或研末调涂患处。用于疥癣疹痒、顽癣。\n" +
                        "\n" +
                        "【药理研究】抗真菌，抗生育，止血，对肝癌细胞活性有影响。\n" +
                        "\n" +
                        "【化学成分】土槿皮抗真菌有效成分为二萜酸类化合物，有土槿甲酸、土槿乙酸。近年又分得土槿丁酸和土槿戊酸。尚含鞣质、挥发油、土荆皮乙酸、土荆皮酸A、土荆皮酸B、土荆皮酸C、土荆皮酸D、土荆皮酸E、土荆皮酸B-β-D-葡萄糖苷等成分。\n" +
                        "\n" +
                        "【使用禁忌】本品有毒，只供外用，不宜内服。\n" +
                        "\n" +
                        "【配伍药方】①治皮肤癣疮：土荆皮30克，白酒60克，浸泡7天，搽患处，搽前用老生姜片擦破癣痂。(《安徽中草药》)\n" +
                        "\n" +
                        "②治癞：土荆皮1斤，白及、尖槟榔、白芷各30克，研细擦三天。(《疡医大全》)\n" +
                        "\n" +
                        "③治癣：土荆皮30克，斑猫二个，鸡心槟榔三个，番木鳖四个，火酒半斤，浸一伏时，蘸搓癣上。(《疡医大全》)\n" +
                        "\n" +
                        "④治干癣：土荆皮15克，樟脑3克，白酒60克，浸3天后搽患处。(《青岛草药手册》)\n" +
                        "\n" +
                        "⑤治湿疹作痒：土荆皮，煎浓汁，温洗患处。(《安徽中草药》)");

                chineseMedicineDao.insert(medicine139);

                ChineseMedicine medicine140=new ChineseMedicine();
                medicine140.setName("甜地丁");
                medicine140.setSortType("T");
                medicine140.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545409471&di=0348978c9ff2163cfc52b1d7cdeb67e1&imgtype=jpg&er=1&src=http%3A%2F%2Ffile.cnkang.com%2Fcnkfile1%2FM00%2F16%2F9E%2Fo4YBAFmo0ROAKJdIAALR_jhkKDI26.jpeg");
                medicine140.setData("【中药名】甜地丁 tiandiding\n" +
                        "\n" +
                        "【别名】米口袋、米布袋、地丁、痒痒草。\n" +
                        "\n" +
                        "【英文名】Herba Gueldenstaedtiae\n" +
                        "\n" +
                        "【药用部位】豆科植物米口袋Gueldenstaedtia multiflora Bunge的干燥带根全草。\n" +
                        "\n" +
                        "【植物形态】多年生草本。根圆锥状。茎缩短，在根茎丛生。托叶三角形，具有长柔毛；奇数羽状复叶；小叶11～21，椭圆形、卵圆或长椭圆形，长6～22毫米，宽3～6毫米；伞形花序有花4～6朵；花萼钟状，上面2萼齿较大，与花梗均被有长柔毛；花冠紫色，旗瓣卵形，长约13毫米，翼瓣长约10毫米，龙骨瓣短，长5～6毫米；雄蕊10，二体；子房圆筒状，花柱内卷。荚果圆筒状，无假隔膜，长17～22毫米。种子肾形，具凹点，有光泽。花期4月，果期5～6月。\n" +
                        "\n" +
                        "【产地分布】生于山坡、草地或路旁。分布于东北、华北、陕西、甘肃、山东、江苏、安徽、湖北、湖南等地。\n" +
                        "\n" +
                        "【采收加工】夏、秋季采收，鲜用或扎把晒干。\n" +
                        "\n" +
                        "【药材性状】本品为不规则的段，根、茎、叶、花、果混合。根圆柱形，直径0.3～0.8厘米，红棕色或灰黄色，切面黄白色，边缘绵毛状。茎细，灰绿色，有茸毛叶。多皱缩，破碎，完整小叶片展平后呈椭圆形或长椭圆形，灰绿色，有茸毛，蝶形花冠紫色。英果圆柱形，棕色，有茸毛。气微，味淡，微甜，嚼之有豆腥叶。\n" +
                        "\n" +
                        "【性味归经】性寒，味甘、苦。归心经、肝经。\n" +
                        "\n" +
                        "【功效与作用】清热解毒，凉血消肿。属清热药下属分类的清热解毒药。\n" +
                        "\n" +
                        "【临床应用】内服：煎汤6～30克。外用：适量，鲜品捣敷；或煎水洗。主治痈肿疔疮，丹毒，肠痈，瘰疬，毒虫咬伤，黄疸，肠炎，痢疾。\n" +
                        "\n" +
                        "【药理研究】甜地丁水煎液和乙酸乙酯萃取物均具有良好的免疫活性、抗炎活性、镇痛作用，且乙酸乙酯萃取物活性强于水煎液。\n" +
                        "\n" +
                        "【化学成分】本品含叶虱硬脂醇、β-谷固醇、大豆皂醇等成分。\n" +
                        "\n" +
                        "【使用禁忌】尚不明确。\n" +
                        "\n" +
                        "【配伍药方】①治肠痈（慢性阑尾炎）：地丁60克，红藤60克，水煎，每日服2次。（《吉林中草药》）\n" +
                        "\n" +
                        "②治急性脓肿，疔疮：板蓝根30克，地丁、金银花、大青叶、蒲公英各15克。水煎，每日服3次。外用鲜地丁，捣烂敷患处。（《吉林中草药》）\n" +
                        "\n" +
                        "③治疔毒：地丁30克，甘草9克，明矾3克。水煎，黄酒为引，每日服2次。（《吉林中草药》）\n" +
                        "\n" +
                        "④治烫火伤：地丁研末，香油调涂患处。（《吉林中草药》）\n" +
                        "\n" +
                        "⑤治急性传染性肝炎：地丁30克。水煎服。（《内蒙古中草药》）\n" +
                        "\n" +
                        "⑥治指头感染：地丁、野菊花各30克。水煎服。（《沙漠地区药用植物》）");

                chineseMedicineDao.insert(medicine140);

                ChineseMedicine medicine141=new ChineseMedicine();
                medicine141.setName("天胡荽");
                medicine141.setSortType("T");
                medicine141.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=04f86f1204f41bd5ce5ee0a630b3eaae/91529822720e0cf3ae2cc6c20046f21fbe09aa34.jpg");
                medicine141.setData("【中药名】天胡荽 tianhusui\n" +
                        "\n" +
                        "【别名】满天星、破铜钱、盘上芫茜、鸡肠菜、破钱草、鼠迹草。\n" +
                        "\n" +
                        "【英文名】Herba Hydrocotyles\n" +
                        "\n" +
                        "【药用部位】伞形科植物天胡荽Hydrocotyle sibthorpioides Lam.的全草。\n" +
                        "\n" +
                        "【植物形态】多年生草本。茎纤弱细长，匍匐，平铺地上成片，茎节上生根。单叶互生；叶片圆形或近肾形，基部心形，5～7浅裂，裂片短，有2～3个钝齿。伞形花序与叶对生，单生于节上，每伞形花序具花10～15朵，萼齿缺乏；花瓣卵形，镊合状排列，绿白色。双悬果略呈心脏形，分果侧面扁平，光滑或有斑点，背棱略锐。花、果期4～9月。\n" +
                        "\n" +
                        "【产地分布】生于海拔50～3000米的湿润草地、沟边或林下草丛中。分布于江苏、浙江等地。\n" +
                        "\n" +
                        "【采收加工】夏、秋季花叶茂盛时采收全草，洗净，阴干或鲜用。\n" +
                        "\n" +
                        "【药材性状】根细圆柱形，外表面淡黄色或灰黄色；茎黄绿色，细长，弯曲，节结处残留丝状细根。叶淡绿色多呈碎片或皱缩，异型后，完整叶片为圆形或肾圆形，基部心形，两耳有时相接，边缘5～7浅裂或全缘，通常连有纤细的叶柄。有气味，微苦。\n" +
                        "\n" +
                        "【性味归经】性寒，味苦、辛。归脾经、胆经、肾经。\n" +
                        "\n" +
                        "【功效与作用】清热利尿、解毒消肿。属清热药下属分类的清热解毒药。\n" +
                        "\n" +
                        "【临床应用】用量9～15克；外用适量。用治湿热郁结之黄疸、大肠湿热之赤白痢疾、咽喉肿痛、痈疽疔疮、跌打损伤之局部瘀肿等。\n" +
                        "\n" +
                        "【药理研究】抗病原微生物；对正常和糖尿病大鼠有显著降血糖作用。具有对金黄色葡萄球菌的较强抑制作用，对变形杆菌、福氏痢疾杆菌、伤寒杆菌也有不同程度的抑制作用。\n" +
                        "\n" +
                        "【化学成分】本品含槲皮素、异鼠李素、槲皮素-3-半乳糖苷、左旋芝麻素、豆固醇等成分。\n" +
                        "\n" +
                        "【使用禁忌】孕妇、脾胃虚寒者慎用。\n" +
                        "\n" +
                        "【配伍药方】①治肝炎、胆囊炎：鲜天胡荽60克。水煎，调冰糖服。(《福建药物志》)\n" +
                        "\n" +
                        "②治石淋：鲜天胡荽60克，海金沙茎叶30克。水煎服，每日1剂。(《湖北中草药志》)\n" +
                        "\n" +
                        "③治百日咳：鲜天胡荽15～30克。捣烂绞汁，调蜂蜜或冰糖炖，温服。(《福建药物志》)\n" +
                        "\n" +
                        "④治荨麻疹：天胡荽30～60克。捣汁，以开水冲服。(《福建中草药》)\n" +
                        "\n" +
                        "⑤治毒蛇咬伤：天胡荽、连钱草(均用鲜品)各60克。捣烂绞汁内服，并用药渣敷伤处。(《湖北中草药志》)\n" +
                        "\n" +
                        "⑥治蛇头疔：鲜天胡荽加冷饭、红糖或雄黄少许。捣烂敷患处。(《福建药物志》)");

                chineseMedicineDao.insert(medicine141);

                ChineseMedicine medicine142=new ChineseMedicine();
                medicine142.setName("土荆芥");
                medicine142.setSortType("T");
                medicine142.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=74c87b0ca1ec8a1300175fb2966afaea/b58f8c5494eef01f19cd4726e8fe9925bd317d73.jpg");
                medicine142.setData("【中药名】土荆芥 tujingjie\n" +
                        "\n" +
                        "【别名】鹅脚草、臭草、火油草。\n" +
                        "\n" +
                        "【英文名】Herba Chenopodii Ambrosioidis\n" +
                        "\n" +
                        "【药用部位】藜科植物土荆芥Chenopodium ambrosioides L.的干燥带果穗全草。\n" +
                        "\n" +
                        "【植物形态】一年生或多年生直立草本，高50～80厘米，有强烈气味。茎直立，有棱，多分枝。被腺毛或无毛。单叶互生，具短柄;叶片披针形至长圆状披针形，长3～16厘米，宽达5厘米，先端短尖或钝，下部的叶边缘有不规则钝齿或呈波浪形，上部的叶较小，为线形，或线状披针形，全缘，上面绿色，下面有腺点，揉之有一种特殊的香气。穗状花序腋生，分枝或不分枝。花小，绿色，两性或雌性。3～5朵簇生于上部叶腋；花被5裂，果时常闭合；雄蕊5；花柱不明显，柱头通常3，伸出花被外。胞果扁球形，完全包于花被内。种子横生或斜生黑色或暗红色，平滑，有光泽。花期8～9月，果期9～10月。\n" +
                        "\n" +
                        "【产地分布】生于旷野、路旁、河岸和溪边。分布于华东、中南、西南等地，北方各地常有栽培。\n" +
                        "\n" +
                        "【采收加工】8月下旬至9月下旬收割全草，摊放在通风处，或捆束悬挂阴干，避免日晒及雨淋。\n" +
                        "\n" +
                        "【药材性状】本品为绿色带有果穗的茎枝。茎下部圆柱形、光滑，上部方形有纵沟，具毛茸，下部叶大多脱落，仅留有茎梢线状披针形的苞片，果穗簇生于枝腋及茎梢，果实扁球形，绿色或黄绿色，直径约1～1.5毫米，外被一层薄囊状宿萼，胞果棕黑色或红黑色，具光泽，搓之具强烈而特殊的气味，味辣而微苦。\n" +
                        "\n" +
                        "【性味归经】性微温，味苦、辛。归肺经、膀胱经。\n" +
                        "\n" +
                        "【功效与作用】祛风除湿，杀虫止痒，活血消肿。属杀虫止痒药。\n" +
                        "\n" +
                        "【临床应用】内服：煎汤，3～9克，鲜品15～24克，或入丸、散。外用：适量，煎水洗或捣敷。主治钩虫病，蛔虫病，蛲虫病，头虱，皮肤湿疹，疥癣，风湿痹痛，经闭，痛经，口舌生疮，咽喉肿痛，跌打损伤，蛇虫咬伤。\n" +
                        "\n" +
                        "【药理研究】具有抗菌及驱肠虫作用。抗疟原虫。\n" +
                        "\n" +
                        "【化学成分】本品含松香芹酮、土荆芥酮、土荆芥苷、丁反式桂皮酸等成分。\n" +
                        "\n" +
                        "【使用禁忌】不宜多服、久服、空腹服，服前不宜用泻药。孕妇及有肾、心、肝功能不良或消化道溃疡者禁服。\n" +
                        "\n" +
                        "【配伍药方】①治小儿痘后脱痂：土荆芥全草，煎汁，外洗。(《青岛中草药手册》)\n" +
                        "\n" +
                        "②治下肢溃烂：土荆芥，水煎，洗患处。(《苗族药物集》)\n" +
                        "\n" +
                        "③治胆道蛔虫病：土荆芥鲜叶6克，牡荆根、香薷各15克，鬼针草30克。水煎服。(《福建药物志》)\n" +
                        "\n" +
                        "④治头虱：土荆芥，捣烂，加茶油敷。(《湖南药物志》)\n" +
                        "\n" +
                        "⑤治关节风湿痛：土荆芥鲜根15克。水炖服。(《福建中草药》)\n" +
                        "\n" +
                        "⑥治湿疹：土荆芥鲜全草适量。水煎，洗患处。(《福建中草药》)\n" +
                        "\n" +
                        "⑦治阴囊湿疹：土荆芥、乌蔹莓、山梗菜叶，各适量。捣烂，取汁涂或煎汤洗患处。(《福建药物志》)\n" +
                        "\n" +
                        "⑧治毒蛇咬伤：土荆芥鲜叶。捣烂，敷患处。(《福建中草药》)");

                chineseMedicineDao.insert(medicine142);

                ChineseMedicine medicine143=new ChineseMedicine();
                medicine143.setName("葶苈子");
                medicine143.setSortType("T");
                medicine143.setMedicineImageUrl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=201677946,740971666&fm=26&gp=0.jpg");
                medicine143.setData("【中药名】葶苈子 tinglizi\n" +
                        "\n" +
                        "【别名】丁历、眉毛蒿、婆婆蒿、米米蒿、大逋，大室。\n" +
                        "\n" +
                        "【英文名】Descurainiae Semen、Lepidii Semen。\n" +
                        "\n" +
                        "【药用部位】十字花科植物播娘蒿Descurainia sophia (L.) Webb ex Prantl的成熟种子，习称“南葶苈子”。或独行菜Lepidium apetalum Willd.的干燥成熟种子，习称“北葶苈子”。(本文以南葶苈子为例，右图亦为南葶苈子)\n" +
                        "\n" +
                        "【植物形态】一年生草本，高30～70厘米。茎直立，不分枝，或上部分枝，被星状毛。叶互生，较密，茎下部的叶柄较明显；叶片2～3回羽状全裂或深裂，裂片线形，柔软，下面多毛，上面无毛或较下面之毛少。夏季开黄色小花，总状花序顶生。长角果细长，成略扁平的圆柱形，近光滑或被柔毛。花期4～6月，果期5～8月。\n" +
                        "\n" +
                        "【产地分布】生于麦地、田边及路旁、住宅附近。主产于山东、江苏、安徽等地。\n" +
                        "\n" +
                        "【采收加工】夏季果实成熟时采割植株，晒干，搓出种子，除去杂质。\n" +
                        "\n" +
                        "【药材性状】长圆形略扁，长约1毫米，宽约0.5毫米。表面棕色或红棕色，微有光泽，一端钝圆，另一端微凹或较平截。味微辛、苦，略带粘性。\n" +
                        "\n" +
                        "【性味归经】性大寒，味辛、苦。归肺经、膀胱经。\n" +
                        "\n" +
                        "【功效与作用】泻肺平喘、行气消肿。属化痰止咳平喘药下属分类的止咳平喘药。\n" +
                        "\n" +
                        "【临床应用】用量3～9克，包煎。用治痰涎壅肺、喘咳痰多、胸胁胀满、不得平卧、胸腹水肿、小便不利、肺原性心脏病水肿。具有强心利尿、抗菌、抗癌和祛痰等功效，对胰蛋白酶有较强的抑制作用。\n" +
                        "\n" +
                        "【药理研究】强心。\n" +
                        "\n" +
                        "【化学成分】含芥子苷、芥酸、异硫氢酸苄酯、异硫氢酸烯丙酯、二烯丙基二硫化合物、脂肪油、蛋白质、糖类等。另含黑芥子苷、芥子碱、芥子油苷、异硫氰酸烯丙酯等成分。\n" +
                        "\n" +
                        "【使用禁忌】葶苈子遇水发粘，不宜用水淘洗。肺虚咳喘，脾虚肿满，肾虚水肿者慎服，不宜久服。\n" +
                        "\n" +
                        "【配伍药方】①治肺痈喘不得卧：葶苈子(熬令黄色，捣，丸如弹子大)，大枣十二枚。上先以水三升煮枣，取二升，去枣纳葶苈，取一升，顿服。(《金匮要略》葶苈大枣泻肺汤)\n" +
                        "\n" +
                        "②治腹胀积聚癥瘕：葶苈子一升(熬)，以酒五升浸七日。服三合，日三。(《千金要方》)\n" +
                        "\n" +
                        "③治一切痈疽恶疮：葶苈子15克，木通(锉)15克，川大黄(生锉)15克，莽草15克。上四味，捣罗为细散，以水和如稀膏，涂肿上，干即更涂，以差为度。(《圣济总录》)\n" +
                        "\n" +
                        "④治小儿白秃：以葶苈子杵末，汤洗去其痂，涂之。(《小儿卫生总微论方》)");

                chineseMedicineDao.insert(medicine143);
                ChineseMedicine medicine144=new ChineseMedicine();
                medicine144.setName("甜瓜蒂");
                medicine144.setSortType("T");
                medicine144.setMedicineImageUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2440927904,2898640835&fm=26&gp=0.jpg");
                medicine144.setData("【中药名】甜瓜蒂 tianguadi\n" +
                        "\n" +
                        "【别名】瓜蒂、瓜丁、苦丁香、甜瓜把。\n" +
                        "\n" +
                        "【英文名】Pedicellus melo\n" +
                        "\n" +
                        "【药用部位】葫芦科植物甜瓜Cucumis melo L.的干燥果柄。\n" +
                        "\n" +
                        "【植物形态】一年生匍匐或攀援草本。茎、枝有黄褐色或白色的糙毛和疣状突起。卷须单一，被微柔毛。叶互生，叶柄长8～12厘米，具槽沟及短刚柔毛，叶片厚纸质，近圆形或肾形，上面被白色糙硬毛，下面沿脉密被糙硬毛，边缘不分裂或3～7浅裂，裂片先端圆钝，有锯齿。花单性，雌雄同株，雄花数朵，簇生于叶腋，花梗纤细，长0.5～2厘米，被柔毛，花萼筒狭钟形，密被白色长柔毛，裂片近钻形，花冠黄色，长约2厘米，裂片卵状长圆形，急尖，雄蕊3，花丝极短，药室折曲，雌花单生，花梗被柔毛，子房长椭圆形，密被长柔毛和硬毛，花柱长1～2毫米，柱头靠合。果实形状、颜色变异较大，一般为球形或长椭圆形，果皮平滑，有纵沟或斑纹，果肉白色、黄色或绿色。种子污白色或黄白色，卵形或长圆形。花、果期夏季。\n" +
                        "\n" +
                        "【产地分布】全国各地广泛栽培。\n" +
                        "\n" +
                        "【采收加工】夏季采收成熟果实，在食用时将切下的果柄收集，阴干或晒干。\n" +
                        "\n" +
                        "【药材性状】本品呈细圆柱形，常扭曲，长3～6厘米，直径0.2～0.4厘米，连接瓜的一端略膨大，直径约8毫米，有纵沟纹，外表面灰黄色，有稀疏短毛茸。带果皮的果柄较短，长0.3～2.6厘米，略弯曲或扭曲，有纵沟纹，果皮部分近圆盘形，直径约2厘米，外表面暗黄色至棕黄色，皱缩，边缘薄而内卷，内表面黄白色至棕色。果柄质较而韧，不易折断，断面纤维性，中空。气微，味苦\n" +
                        "\n" +
                        "【性味归经】性寒，味苦。归脾经、胃经、肝经。\n" +
                        "\n" +
                        "【功效与作用】涌吐痰食、除湿退黄。属涌吐药。\n" +
                        "\n" +
                        "【临床应用】内服：煎汤，3～6克，或入丸、散，0.3～1.5克。外用：适量，研末吹鼻。主治中风、癫痫、喉痹、痰涎壅盛、呼吸不利、宿食不化、胸脘胀痛、湿热黄疸。\n" +
                        "\n" +
                        "【药理研究】保肝；抗癌；增强细胞免疫功能；抗炎和避孕；致吐。\n" +
                        "\n" +
                        "【化学成分】本品主要含氨基酸，葫芦苦素B、葫芦苦素D、葫芦苦素E，异葫芦苦素β、α-菠菜固醇等成分。\n" +
                        "\n" +
                        "【使用禁忌】体虚、失血及上部无实邪者禁服。本品有毒，不宜大量服用，过量则易出现头晕眼花，脘腹不适，呕吐，腹泻，严重者可因脱水，造成电解质紊乱终致循环衰竭及呼吸中枢麻痹而死亡。\n" +
                        "\n" +
                        "【配伍药方】①治牙齿痛：甜瓜蒂七枚。炒黄研散，以麝香相和，新绵裹，病牙处咬之。(《圣济总录》瓜蒂散)\n" +
                        "\n" +
                        "②治耳重：甜瓜蒂、麝香(研)、地龙、地丁各15克。上四味，捣罗为散。每以少许，掺耳内。(《圣济总录》抵圣散)\n" +
                        "\n" +
                        "③治疟：甜瓜蒂二七枚。捣，水渍一宿服之。(《千金要方》)\n" +
                        "\n" +
                        "④治诸痔：甜瓜蒂(末)9克，密陀僧6克(另研)，朱砂1.5克，冰片少许。上为末，干以唾调敷。(《古今医统大全》)");

                chineseMedicineDao.insert(medicine144);

                ChineseMedicine medicine145=new ChineseMedicine();
                medicine145.setName("甜瓜子");
                medicine145.setSortType("T");
                medicine145.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545410133&di=8e042af7c9d5d5ae49c503131089ed3e&imgtype=jpg&er=1&src=http%3A%2F%2Fimg002.hc360.cn%2Fhb%2FMTQ3MjA4NTE2NjM3MTE4MjgyNTA5OTU%3D.jpg");
                medicine145.setData("【中药名】甜瓜子 tianguazi\n" +
                        "\n" +
                        "【别名】甘瓜子、甜瓜仁、甜瓜瓣。\n" +
                        "\n" +
                        "【英文名】Semen Melo。\n" +
                        "\n" +
                        "【药用部位】葫芦科植物甜瓜Cucumis melo L.的成熟种子。\n" +
                        "\n" +
                        "【植物形态】一年生攀缘蔓生草本。茎圆形，具比例行凹形，具纵行凹，被众多短刚毛。卷须先端不分叉，具刺毛。单叶互生，具长柄，长约10厘米，叶片近圆形或肾形，长4～12厘米，宽与长几相等，3～7掌状浅裂，丙面有柔毛，下面脉上具短刚毛。叶脉掌状，边缘有上整齐锯齿。花单性，雌雄同株，生于叶腋，雄花数朵簇生，雌花单生，花萼筒状，5裂，裂片先端尖，密被白色柔毛，花冠黄色、5裂，裂片卵状长圆形，有小尖头，长约2厘米，雄花具长梗，雄蕊5，联生成3，花丝极短，紧贴于花冠筒内，药室S形折曲，药隔顶端引长，雌花梗较短，子房下位，长椭圆形，花柱极短，柱头3，靠合。瓠果，其形状与颜色因品种而异，一般为长圆形，长10～15厘米，外皮黄色、黄白色或绿色，有时具花纹，光滑，略有香气，味甜。种子多数，扁长圆形，多呈黄白色或灰白色。花期6～7月，果期7～8月。\n" +
                        "\n" +
                        "【产地分布】我国各地均有栽培。\n" +
                        "\n" +
                        "【采收加工】夏、秋二季果实成熟时收集，洗净，晒干。\n" +
                        "\n" +
                        "【药材性状】呈扁平长卵形，长5～9毫米，宽2～4毫米。表面黄白色、浅棕红色或棕黄色，平滑，微有光泽。一端稍尖，另端钝圆。种皮较硬而脆，内有膜质胚乳和子叶2片。气微，味淡。\n" +
                        "\n" +
                        "【性味归经】性寒，味甘。归肺经、胃经、大肠经。\n" +
                        "\n" +
                        "【功效与作用】清肺，润肠，化瘀，排脓，疗伤止痛。属活血化瘀药下属分类的活血止痛药。\n" +
                        "\n" +
                        "【临床应用】用量9～30克，水煎服，或研末。用治肺热咳嗽，便秘，肺痈，肠痈，跌打损伤，筋骨折伤。\n" +
                        "\n" +
                        "【药理研究】驱虫；抑菌。\n" +
                        "\n" +
                        "【化学成分】种子含蛋白30.6%，脂肪48.7%，维生素C，胡萝卜素和多种氨基酸，还含球蛋白及谷蛋白5.78%和半乳聚糖，葡萄糖，树胶，树脂等。果仁中含脂类49.4%，其中中性脂类占91.5%，糖脂类占6.4%，磷脂占2.1%。\n" +
                        "\n" +
                        "【使用禁忌】脾胃虚寒，腹泻者忌服。\n" +
                        "\n" +
                        "【配伍药方】①治慢性气管炎：甜瓜子60克。研粉，每次6克，开水送服，每日2次。(《浙江药用植物志》)\n" +
                        "\n" +
                        "②治肺水肿，渗出性胸膜炎：冬瓜子、甜瓜子各120克。打碎煮汤代茶饮。(《施今墨对药》)\n" +
                        "\n" +
                        "③治心烦口渴：甜瓜子9克，麦门冬12克，天花粉12克。水煎服。(《青岛中草药手册》)\n" +
                        "\n" +
                        "④治肠痈，肺痈：甜瓜子30克，加白糖适量，捣烂研细，以开水冲服。(《食物中药与便方》)\n" +
                        "\n" +
                        "⑤治口臭：甜瓜子作末，蜜和。每日空心洗漱，含一丸如枣核大，亦敷齿。(《千金要方》)\n" +
                        "\n");

                chineseMedicineDao.insert(medicine145);

                ChineseMedicine medicine146=new ChineseMedicine();
                medicine146.setName("菟丝子");
                medicine146.setSortType("T");
                medicine146.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=8e40448614dfa9ece9235e4503b99c66/f2deb48f8c5494ee8c8c298328f5e0fe98257ef9.jpg");
                medicine146.setData("【中药名】菟丝子 tusizi\n" +
                        "\n" +
                        "【别名】菟丝实、吐丝子、黄藤子。\n" +
                        "\n" +
                        "【英文名】Cuscutae Semen\n" +
                        "\n" +
                        "【药用部位】旋花科植物菟丝子Cuscuta chinensis Lam.的成熟种子。\n" +
                        "\n" +
                        "【植物形态】一年生寄生草本。茎纤细呈丝线状，橙黄色，多分枝，缠绕于其他植物体上，随处生吸器，侵入寄主体内。叶退化为三角状小鳞片。花白色，簇生；苞片卵圆形；花萼杯状，先端5裂，裂片卵形或椭圆形；花冠钟形，5浅裂，裂片三角形；雄蕊5枚，花丝短，与花冠裂片互生。雌蕊l枚，子房上位，2室，每室有胚珠2枚，花柱2，柱头头状。蒴果扁球形，长约3毫米，褐色。花期7～9月，果期8～10月。\n" +
                        "\n" +
                        "【产地分布】生于山坡路旁、田边、荒地及灌木丛中，多寄生于豆科、菊科、藜科植物上，尤以大豆上为常见。全国大部分地区有分布。药材菟丝子主产于辽宁、黑龙江、吉林、内蒙古、山东、河北、山西等地。\n" +
                        "\n" +
                        "【采收加工】10月中旬，菟丝子果壳变黄，大豆有1/3植株已干枯时，割下寄主，晒干，脱粒，扬净。净菟丝子与大豆分开后贮藏。炮制：除去杂质，洗净，晒干。酒菟丝饼：取净菟丝子置锅内，加适量水煮至开裂，不断翻动，待水被吸尽呈稠粥状时，加入黄酒白面拌匀，取出，压成大片，切成方块，干燥(每菟丝子100千克，用黄酒15千克，白面15千克)。\n" +
                        "\n" +
                        "【药材性状】类球形或卵圆形，膨大部分稍扁，直径1～1.5毫米，一端略呈喙状突出，偏向一侧，微凹处有浅色圆点，中央有条形的种脐。表面淡褐色或灰黄色，略粗糙。质坚硬。开水浸泡后，表面显黏性，加热煮至种皮破裂时露出黄白色细长卷旋状的胚，形如吐丝。无臭，味微苦涩。\n" +
                        "\n" +
                        "【性味归经】性平，味辛、甘。归肝经、肾经、脾经。\n" +
                        "\n" +
                        "【功效与作用】补肾益精，养肝明日。属补虚药下分类的补阳药。\n" +
                        "\n" +
                        "【临床应用】用于肾虚腰痛，阳痿，早泄，尿浊，带下，小便频数；胎动不安，先兆流产；肝肾不足，视物昏花，视力减退。此外，本品还可用于脾虚食少，大便不实，常与白术、山药、茯苓、党参等同用。内服：煎汤：用量10～15克，或入丸散。外用：炒研调敷。\n" +
                        "\n" +
                        "【药理研究】对下丘脑-垂体-性腺有兴奋作用；促进造血；增强免疫功能；防治心肌缺血；抑制肿瘤发生；抗肝损害等。菟丝子浸剂、酊剂可增强离体蟾蜍心脏收缩力，降低心率，使麻醉犬血压下降。\n" +
                        "\n" +
                        "【化学成分】含槲皮素、紫云英苷、金丝桃苷、桷皮素-β-O-D半乳糖-7-O-β-葡萄糖苷、山柰酚-3-0-β-D-吡喃葡萄糖苷、山柰酚、新菟丝子苷、菟丝子多糖等成分。又含菟丝子胺、菟丝子苷A、菟丝子苷B、熊果酚苷、绿原酸、咖啡酸、对-香豆酸。还含钾、钙、磷、硫、铁、铜、锰、硒、钼等微量元素，以及缬氨酸、蛋氨酸、异亮氨酸等人体必需氨基酸。\n" +
                        "\n" +
                        "【使用禁忌】阴虚火旺、阳强不萎及大便燥结者禁服。\n" +
                        "\n" +
                        "【配伍药方】①治小便淋涩：车前子(焙)、菟丝子等分。上为末，炼蜜为丸，食后服之。(《医方类聚》引《于金月令》驻景丸)\n" +
                        "\n" +
                        "②治脾肾两虚，大便溏泄：菟丝子、石莲子各9克，茯苓12克，山药15克。煎服。(《安徽中草药》)\n" +
                        "\n" +
                        "③治关节炎：菟丝子6克，鸡蛋壳9克，牛骨粉15克。研面，每服6克，每日3次。(《辽宁常用中草药手册》)\n" +
                        "\n" +
                        "④治面上粉刺：捣菟丝子，绞取汁涂之。(《肘后方》)\n" +
                        "\n" +
                        "⑤治白癜风：菟丝子9克，浸入95%乙醇60克内，2～3天后取汁，外涂，每日2～3次。(《青岛中草药手册》)");

                chineseMedicineDao.insert(medicine146);

                ChineseMedicine medicine147=new ChineseMedicine();
                medicine147.setName("桃仁");
                medicine147.setSortType("T");
                medicine147.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=f1cd01dfad4bd11310c0bf603bc6cf6a/728da9773912b31b890928858618367adab4e131.jpg");
                medicine147.setData("【中药名】桃仁 taoren\n" +
                        "\n" +
                        "【别名】毛桃仁、扁桃仁、大桃仁。\n" +
                        "\n" +
                        "【英文名】Persicae Semen。\n" +
                        "\n" +
                        "【药用部位】蔷薇科植物桃Prunus persica (L.) Batsch的成熟种子。\n" +
                        "\n" +
                        "【植物形态】落叶小乔木，高3～8米。叶互生，在短枝上呈簇生状，具线状托叶1对；叶片椭圆状披针形至阔披针形，长8～15厘米，宽2～3.5厘米，先端渐尖，基部阔楔形，边缘有锯齿。花单生，先叶开放；萼片5，外面被毛；花瓣5，淡红色，稀白色；雄蕊多数，短于花瓣；心皮1，稀2，有毛。核果肉质，多汁，心状卵形至椭圆形，一侧有纵沟，表面具短柔毛；果核坚硬，木质，扁卵圆形，顶端渐尖，表面具不规则的深槽及窝孔。种子1粒。花期3～4月，果期5～9月。\n" +
                        "\n" +
                        "【产地分布】生于较温湿的肥沃土壤中，多栽培于平地或丘陵地带。主产于四川、陕西、河北等地。\n" +
                        "\n" +
                        "【采收加工】果实成熟后收集果核，除去果肉及核壳，取出种子晒干。\n" +
                        "\n" +
                        "【药材性状】扁长卵形，长1.2～1.8厘米，宽0.8～1.2厘米，厚0.2～0.4厘米。表面黄棕色至红棕色，密布颗粒状突起。一端尖，中部膨大，另一端钝圆而偏斜，边缘较薄。尖端一侧有短线形种脐。圆端有颜色略深、不甚明显的合点，自合点处散出多数纵向维管束。种皮薄，子叶2，类白色，富油质。气微，味微苦。\n" +
                        "\n" +
                        "【性味归经】性平，味苦、甘。归心经、肝经、大肠经。\n" +
                        "\n" +
                        "【功效与作用】活血祛痰、润肠通便。属活血化瘀药下属分类的活血调经药。\n" +
                        "\n" +
                        "【临床应用】用量4.5～9克，水煎服或入丸散。外用捣敷。用治经闭、痛经、痞块、跌扑损伤、肠燥便秘。\n" +
                        "\n" +
                        "【药理研究】毒性试验发现本品无毒性。具有抗凝血、抗直栓、改善血流、抗炎、镇痛和抗过敏等作用。并能止咳、平喘，对肺结核有一定疗效。还有驱虫作用\n" +
                        "\n" +
                        "【化学成分】含苦杏仁苷、野樱桃苷、2,4-次甲基珂阿尔廷醇、多花蔷薇苷、尿囊素、维生素B1、苦杏仁酶、脂肪油、脂肪酸、蛋白质等。另含24-亚甲基环木菠萝烷醇、柠檬甾二烯醇、7-去氢燕麦固醇等成分。\n" +
                        "\n" +
                        "【使用禁忌】孕妇慎用。\n" +
                        "\n" +
                        "【配伍药方】①治妇人、室女血闭不通，五心烦热：红花、当归(洗焙)、杜牛膝、桃仁(焙)各等分，为细末。每服9克，温酒调下，空心，食前。(《杨氏家藏方》桃仁散)\n" +
                        "\n" +
                        "②治产后恶露不净，脉弦滞涩者：桃仁9克，当归9克，赤芍、桂心各4.5克，砂糖9克(炒炭)。水煎，去渣温服。(《医略六书》桃仁煎)\n" +
                        "\n" +
                        "③治食郁久，胃脘有瘀血作痛：生桃仁连皮细嚼、以生韭菜捣自然汁一盏送下。(《万病回春》)\n" +
                        "\n" +
                        "④治老人虚秘：桃仁、柏子仁、火麻仁、松子仁等分。同研，熔白蜡和丸如梧桐子大。以少黄丹汤下。(《汤液本草》)\n" +
                        "\n");
                chineseMedicineDao.insert(medicine147);

                ChineseMedicine medicine148=new ChineseMedicine();
                medicine148.setName("土牛膝");
                medicine148.setSortType("T");
                medicine148.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=179a76fd0a087bf469e15fbb93ba3c49/77c6a7efce1b9d16f7f5d3d7fbdeb48f8c546451.jpg");
                medicine148.setData("【中药名】土牛膝 tuniuexi\n" +
                        "\n" +
                        "【别名】牛舌头，倒扣草，倒钩草，倒勒草，铁马鞭。\n" +
                        "\n" +
                        "【英文名】Common Achyranthes Herb。\n" +
                        "\n" +
                        "【药用部位】为苋科植物粗毛牛膝Achyranthes aspera L.全草或者牛膝Achyranthes biden-tata Blume的野生种、柳叶牛膝Alongifolia(Makino) Makino、钝叶土牛膝A.asperaL.var. indicaL.的根及根茎。\n" +
                        "\n" +
                        "【植物形态】多年生草本。根细长，土黄色。茎四棱形，有柔毛，节部稍膨大，分枝对生。叶对生;叶片纸质，宽卵状倒卵形或椭圆状长圆形，长1.5～7cm，宽0.4～4cm，先端圆钝，具突尖，基部楔形或圆形，全缘或波浪波状，两面密生粗毛。穗状花序顶生，花疏生;苞片披针形，小苞片刺状，坚硬，光亮，常带紫色，基部两侧各有1个薄膜质翅，全缘，全部贴生在刺部;花被片披针形，花后变硬且锐尖，具1脉;雄蕊长2.5～3.5mm;退化雄蕊先端截状或细圆齿状，有具分枝流苏状长缘毛。胞果卵形。种子卵形，棕色。\n" +
                        "\n" +
                        "【产地分布】除东北外全国广布，朝鲜、苏联、印度、越南、菲律宾、马来西亚、非洲均有分布。\n" +
                        "\n" +
                        "【采收加工】全年均可，除去茎叶，洗净，鲜用或晒干。\n" +
                        "\n" +
                        "【药材性状】根圆柱形，微弯曲，表面灰黄色，具细顺纹及侧根痕；质柔韧，不易折断，断面纤维性，小点状维管束排成数个轮环。茎类圆柱形，嫩枝略呈方柱形，有分枝，表面褐绿色，嫩枝被柔毛，节膨大如膝状。质脆，易折断，断面黄绿色。气微，味甘。\n" +
                        "\n" +
                        "【性味归经】性微寒，味苦、酸。归肝经、肾经。\n" +
                        "\n" +
                        "【功效与作用】活血化瘀，利尿通淋，清热解表。属活血化瘀下属分类的活血调经药。\n" +
                        "\n" +
                        "【临床应用】内服：煎汤，10～15g。外用：适量，捣敷;或研末，吹喉。主治经闭、痛经、月经不调，跌打损伤，风湿关节痛，外感发热，疟疾，痢疾。\n" +
                        "\n" +
                        "【药理研究】根茎煎剂能明显兴奋子宫平滑肌，并有抗早孕、抗着床、抗炎等作用。\n" +
                        "\n" +
                        "【化学成分】根茎蜕皮甾酮(ecdysterone)、倒扣草皂苷(achyranthes saponin)、氨基酸、三十三烷醇( tritriacontanol)、倒扣草碱( achyranthine)等。\n" +
                        "\n" +
                        "【使用禁忌】孕妇、月经过多者忌用。\n" +
                        "\n" +
                        "【配伍药方】①治男妇诸淋，小便不通：土牛膝连叶，以酒煎服数次。血淋尤验。(《岭南采药录》)\n" +
                        "\n" +
                        "②治肝硬变水肿：鲜土牛膝六钱至一两(干的四至六钱)。水煎，饭前服，日服两次。(《福建民间草药》)\n" +
                        "\n" +
                        "③治痢疾：土牛膝五钱，地桃花根五钱，车前草三钱，青荔三钱。水煎，冲蜜糖服。(《广西中草药》)。\n" +
                        "\n" +
                        "④治跌打损伤：土牛膝三至五钱。水煎，酒对服。(《江西草药》)");

                chineseMedicineDao.insert(medicine148);

                ChineseMedicine medicine149=new ChineseMedicine();
                medicine149.setName("土人参");
                medicine149.setSortType("T");
                medicine149.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike220%2C5%2C5%2C220%2C73/sign=14246f80e5dde711f3df4ba4c686a57e/d058ccbf6c81800ad89e568bb13533fa828b4738.jpg");
                medicine149.setData("【中药名】土人参 turenshen\n" +
                        "\n" +
                        "【别名】锥花土人参，假人参，飞来参，参草，土洋参。\n" +
                        "\n" +
                        "【英文名】Panicled Fameflower Root。\n" +
                        "\n" +
                        "【药用部位】马齿苋科植物锥花土人参Talinum paniculatum (Jacq.) Gaertn.的根。\n" +
                        "\n" +
                        "【植物形态】一年生草本，肉质。主根粗壮有分枝，外表棕褐色。茎直立，有分枝，圆柱形，基部稍木质化。叶互生，倒卵形或倒卵状长圆形，长5～7cm，宽2.5～3.5cm，先端渐尖或钝圆，全缘，基部渐狭而成短柄。圆锥花序顶生或侧生，二歧状分枝，小枝或花梗基部均具苞片，花小，两性，淡紫红色;萼片2，早落，花瓣5，倒卵形或椭圆形，雄蕊10枚以上，子房球形，花柱线形，柱头3深裂，先端外展而微弯。蒴果近球形，熟时灰褐色。种子多数，细小，扁圆形，黑色有光泽，表面具细腺点。\n" +
                        "\n" +
                        "【产地分布】我国河南以南各地均有栽培。\n" +
                        "\n" +
                        "【采收加工】秋、冬季挖根，洗净，晒干或蒸后晒干。\n" +
                        "\n" +
                        "【药材性状】根圆锥形或长纺锤形，分枝或不分枝。顶端具木质茎残基。表面灰褐色，有纵皱纹及点状突起的须根痕。除去栓皮并经蒸煮后表面为灰黄色半透明状，有点状须根痕及纵皱纹，隐约可见内部纵走的维管束。质坚硬，难折断。折断面，未加工的平坦，已加工的呈角质状，中央常有大空腔。气微，味淡、微有黏滑感。以条粗、干燥、质坚实、断面色乳白者为佳。\n" +
                        "\n" +
                        "【性味归经】性平，味甘、淡。归肺经、脾经。\n" +
                        "\n" +
                        "【功效与作用】补气润肺，止咳，调经。属补虚药下属分类的补气药。\n" +
                        "\n" +
                        "【临床应用】内服：煎汤，30～60克。外用：适量，捣敷。主治气虚劳倦，食少，泄泻，肺痨咳血，眩晕，盗汗，自汗，月经不调，产妇乳汁不足\n" +
                        "\n" +
                        "【药理研究】初步药理试验，土人参乙醇提取物有镇痛作用。土人参含有的总皂苷还可抗炎、抗菌。皂苷有溶血作用，刺激性强，毒性大。\n" +
                        "\n" +
                        "【化学成分】土人参根含甜菜色素(betalain)、草酸(oxalic acid)、芸苔甾醇、β-谷甾醇、豆甾醇等。\n" +
                        "\n" +
                        "【使用禁忌】孕妇忌服。\n" +
                        "\n" +
                        "【配伍药方】①治虚劳咳嗽：土洋参、隔山撬、通花根、冰糖。炖鸡服。(《四川中药志》)\n" +
                        "\n" +
                        "②治劳倦乏力：土人参五钱至一两，或加墨鱼干一只。酒水炖服。(《福建中草药》)\n" +
                        "\n" +
                        "③治脾虚泄泻：土人参五钱至一两，大枣五钱。水煎服。(《福建中草药》)\n" +
                        "\n");

                chineseMedicineDao.insert(medicine149);

                ChineseMedicine medicine150=new ChineseMedicine();
                medicine150.setName("天仙子");
                medicine150.setSortType("T");
                medicine150.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=c43711149b529822113e3191b6a310ae/dc54564e9258d10931ca8461da58ccbf6c814d33.jpg");
                medicine150.setData("【中药名】天仙子 tianxianzi\n" +
                        "\n" +
                        "【别名】莨菪子、牙痛子、莨茸子。\n" +
                        "\n" +
                        "【英文名】Black Henbane\n" +
                        "\n" +
                        "【来源】茄科植物莨菪Hyoscyamus niger L.的种子。\n" +
                        "\n" +
                        "【植物形态】二年生草本，高达1米，全株被黏性的腺毛。根粗壮，肉质，直径2～3厘米。一年生植株的茎极短，茎基部有莲座状叶丛。二年生植株的茎伸长并分枝，茎生叶互生，无叶柄，基部半抱茎，叶片卵形至长圆形，长4～10厘米，宽2～6厘米，先端钝或渐尖，边缘常羽状浅裂或深裂。花在茎中部以上单生于叶腋，通常偏向一侧，近无梗或仅有极短的梗，花萼筒状，5浅裂，花后增大成壶状，花冠钟状，黄色而有紫堇色网纹，雄蕊5枚，插生在花冠筒的近中部，稍伸出花冠外，子房2室，柱头头状，2浅裂。蒴果藏在宿存的萼内，长卵圆形，成熟时盖裂。种子小，近圆盘形，有多数网状凹穴。\n" +
                        "\n" +
                        "【产地分布】生于村边、田野、路旁、宅旁等处；有栽培。分布于黑龙江、吉林、辽宁、内蒙古等地。\n" +
                        "\n" +
                        "【采收加工】夏末秋初果实成熟时，割下地上部或拔起全植物，晒干，打下种子，洗净，除去杂质，晒干。\n" +
                        "\n" +
                        "【药材性状】种子略呈肾形或卵圆形，稍扁，直径约1毫米，表面棕黄色或灰黄色，有隆起的细密网纹，略尖的一端有点状种脐。纵剖面可见胚弯曲，子叶2枚，胚根明显。气无，味微辛。\n" +
                        "\n" +
                        "【性味归经】性温，味苦、辛。归心经、肝经、胃经。\n" +
                        "\n" +
                        "【功效与作用】解痉、止痛、安神、定喘。属安神药下分类的养心安神药。\n" +
                        "\n" +
                        "【临床应用】用量0.06～0.6克，内服，入丸散，治疗胃痉挛疼痛、喘咳、癫狂。外用煎水洗、研末调敷或烧烟熏。\n" +
                        "\n" +
                        "【药理研究】可引起暂时性闭眼，侧卧、翻正反射消息，并与中枢抑制药有协同作用。可影响脑电及条件反射，提高痛阈，具有一定强度的镇痛作用。与神经递质相互影响，增加乙酰胆碱的释放量，具有对抗去甲肾上腺素的作用。能解除迷走神经对心脏的抑制，使交感神经作用占优势，故心率加快，其加速的程度随迷走神经对心脏控制的强弱而不同。有解除血管痉挛，改善微循环的作用，增加肾血流量的作用。能兴奋呼吸中枢。使呼吸加快，并能对抗冬眠药物的呼吸抑制。抑制呼吸道腺体分泌，有松弛支气管平滑肌的作用。在麻醉时影响机体体温等作用。莨菪碱(其消旋体为阿托品)及东莨菪碱均为抗胆碱药，能阻断M胆碱受体，主要表现在抑制腺体分泌和解痉，扩大瞳孔，解除迷走神经对心脏的抑制，从而使心率加快，阿托品对中枢神经有一定的兴奋作用，而东莨菪碱有镇静作用，莨菪类药物可调节微血管管径，降低血管内皮细胞损伤，改善血液流变性，增加微血管的自律运动；莨菪类药物能改变生物膜膜脂状态；东莨菪碱有一定的镇痛作用，并与杜冷丁合用具有协同作用。不良反应：有误服天仙子中毒报道多例，主要症状是头晕眼花、站立不稳、恶心呕吐、躁狂多言、重则惊厥、神志昏迷。\n" +
                        "\n" +
                        "【化学成分】含莨菪碱0.02%～0.17%和东莨菪碱0.01%～0.08%。另含天仙子胺、阿托品、脂肪油等。\n" +
                        "\n" +
                        "【使用禁忌】有大毒，慎内服，不可过量及连续服用。孕妇、心脏病、心动过速、青光眼患者禁服。。\n" +
                        "\n" +
                        "【配伍药方】①治龋齿：天仙子烧烟，用竹筒抵牙，引烟熏之即虫孔不再发。(《证治准绳》)\n" +
                        "\n" +
                        "②治年久呷嗽：天仙子、木香、薰黄等分。为末。以羊脂涂青纸上，撒末于上，卷作筒。烧烟熏吸之。(《崔氏篡要方》)\n" +
                        "\n" +
                        "③治水泻日久：青州干枣十个(去核)，入天仙子填满：扎定烧存性。每粟米饮服3克。(《圣惠方》)\n" +
                        "\n" +
                        "④治赤白痢，哜腹疼痛，肠滑后重：大黄15克，天仙子30克。上捣罗为散，每服3克，米饮调下，食前。(《普济方》妙功散)\n" +
                        "\n" +
                        "⑤治石痈坚如石，不作脓者：醋和莨菪子末。敷头上。(《千金要方》)");

                chineseMedicineDao.insert(medicine150);

                ChineseMedicine medicine151=new ChineseMedicine();
                medicine151.setName("太子参");
                medicine151.setSortType("T");
                medicine151.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=378b91a85fafa40f28cbc68fca0d682a/37d3d539b6003af37efcfafa302ac65c1138b6e1.jpg");
                medicine151.setData("【中药名】太子参 taizishen\n" +
                        "\n" +
                        "【别名】四叶参、童参、孩儿参、米参。\n" +
                        "\n" +
                        "【英文名】Pseudostellariae Radix。\n" +
                        "\n" +
                        "【来源】石竹科植物孩儿参Pseudostellaria heterophylla( Miq. )Pax ex Paxet Hoffm.的块根。\n" +
                        "\n" +
                        "【植物形态】多年生草本，高15～20厘米。块根细长纺锤形，外皮淡黄白色，疏生须根。茎直立，有2行短柔毛。叶对生，下部叶匙形或倒披针形，基部渐狭；上部叶卵状披针形或长卵形；茎顶叶较大，常4叶排成“十”字形。花2型，腋生，茎顶的花大，白色，花梗被短柔毛，萼片5，披针形，花瓣5，先端2齿裂，雄蕊10，花柱3；茎下部的花小，紫色，萼片4，闭合，无花瓣，雄蕊通常2枚。蒴果卵形，种子扁圆或长圆状肾形，褐色，具疣点。花期4～5月，果期5～6月。\n" +
                        "\n" +
                        "【产地分布】生于林下肥沃阴湿地或阴湿山坡石缝中，有栽培。分布于河北、河南、山东、山西、陕西等地。\n" +
                        "\n" +
                        "【采收加工】夏季茎叶大部分枯萎时采挖，洗净，除去须根，置沸水中略烫后晒干或直接晒干。\n" +
                        "\n" +
                        "【药材性状】细长纺锤形或细长条形，稍弯曲，长3～10厘米，直径0.2～0.6厘米。表面黄白色，较光滑，微有纵皱纹，凹陷处有须根痕。顶端有茎痕。质硬而脆，断面平坦，淡黄白色，角质样(烫后晒干者)；或类白色，有粉性(生晒者)。气微，味微甘。\n" +
                        "\n" +
                        "【性味归经】性平，味甘、微苦。归脾经、肺经。\n" +
                        "\n" +
                        "【功效与作用】益气健脾、生津润肺。属补虚药下属分类的补气药。\n" +
                        "\n" +
                        "【临床应用】用量9～30克，水煎服。用治脾虚体倦、食欲不振、病后虚弱、气阴不足、自汗口渴、肺燥干咳。\n" +
                        "\n" +
                        "【化学成分】含环肽，如太子参环肽A、B、C，假繁缕环肽等，还含有挥发油，其成分已鉴定的有12种，主要糠醇等。另含有多种游离氨基酸，以精氨酸、谷氨酸、天冬氨酸含量最高，占游离氨基酸的30%～40%，还含有麦芽糖等。近年从中分得三棕榈酸甘油酯、棕榈酸、β-谷甾醇、△7-豆甾烯醇 -3-8-D-吡喃葡萄糖苷、肌醇-3-甲醚、a-槐糖和蔗糖、山萮酸等。\n" +
                        "\n" +
                        "【使用禁忌】脏腑燥热，阴虚津液不足者慎服。\n" +
                        "\n" +
                        "【配伍药方】①治肺虚咳嗽：太子参15克，麦冬12克，甘草6克。水煎服。(《湖北中草药志》)\n" +
                        "\n" +
                        "②治病后气血亏虚：太子参15克，黄芪12克，五味子3克，嫩白扁豆4克，大枣4枚。煎水代茶饮。(《安徽中草药》)\n" +
                        "\n" +
                        "③治病后虚弱，伤津口干：太子参、生地黄、白芍、生玉竹各9克。水煎服。(《浙江药用植物志》)\n" +
                        "\n" +
                        "④治心悸：太子参9克，南沙参9克，丹参9克，苦参9克。水煎服，每1剂。[《辽宁中医杂志》1984，(1)：25]\n" +
                        "\n" +
                        "⑤治神经衰弱：太子参15克，当归、酸枣仁、远志、炙甘草各9克。煎服。(《安徽中草药》)");

                chineseMedicineDao.insert(medicine151);

                ChineseMedicine medicine152=new ChineseMedicine();

                medicine152.setName("天葵子");
                medicine152.setSortType("T");
                medicine152.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=d50cf53ece1b9d169eca923392b7dfea/4afbfbedab64034fdb3aa5e7afc379310b551d5f.jpg");
                medicine152.setData("【中药名】天葵子 tiankuizi\n" +
                        "\n" +
                        "【别名】紫背天葵子、千年老鼠屎、野乌头子、地丁子、天葵根。\n" +
                        "\n" +
                        "【英文名】Semiaquilegiae Radix。\n" +
                        "\n" +
                        "【来源】毛茛科天葵Semiaquilegia adoxoides (DC.) Makino的块根。\n" +
                        "\n" +
                        "【植物形态】多年生小草本。块根外皮棕黑色。茎直立，1～3条，上部有分枝，被稀疏白色柔毛。基生叶为三出复叶，叶柄茎部扩大呈鞘状，叶片圆形或肾形，小叶扇状菱形或倒卵状菱形，3深裂，两面无毛，下面常带紫色，小叶柄短，有细柔毛；茎生叶较小，互生。花单生叶腋，花柄果后伸长，中部有细苞片2枚；花小，白色；萼片5，花瓣状，卵形；花瓣5，楔形，较萼片稍短；雄蕊通常10，其中有2枚不完全发育；雌蕊3～4，子房狭长，花柱短，向外反卷。瞢荚果3～4枚，熟时开裂；种子细小，倒卵形。花期3～4月，果期5～6月。\n" +
                        "\n" +
                        "【产地分布】主产于江苏、湖南、江西、浙江等地。\n" +
                        "\n" +
                        "【采收加工】5～6月间挖块根，除去须根，晒干。\n" +
                        "\n" +
                        "【药材性状】不规则短块状、纺锤状或块状，略弯曲，长1～3厘米，直径0.5～1厘米。表面暗褐色至灰黑色，具不规则的皱纹及须根或须根痕。顶端常有茎叶残基，外被数层黄褐色的鞘状鳞片。质较软，易折断，断面皮部类白色，木部黄白色或黄棕色，略呈放射状。气微，味甘、微苦辛。\n" +
                        "\n" +
                        "【性味归经】性寒，味甘、苦，归肝经、胃经。\n" +
                        "\n" +
                        "【功效与作用】清热解毒、消肿散结。属清热药下属分类的清热解毒药。\n" +
                        "\n" +
                        "【临床应用】用量9～15克。用治热毒壅结所致之痈肿疔疮、瘰疬、痔疮，以及疝气、跌打肿痛、毒蛇咬伤等。\n" +
                        "\n" +
                        "【药理研究】本品100%煎剂用平板纸片法，对金黄色葡萄球菌有抑制作用。断面置于紫外光(365纳米)下 观察，显黄白色荧光，加酸或碱后荧光减退。取粉末1克，加入70%乙醇10毫升，加热回流半小时，滤过，滤液蒸干，残渣加盐酸溶液(1%)5毫升溶解，滤过，滤液分置两试管中，一管中加碘化铋钾试液1～2滴，生成橘红色沉淀;另一管中加硅坞酸试液1～2 滴，生成黄色沉淀。\n" +
                        "\n" +
                        "【主要成分】含生物碱、槲皮素、苹果酸、内酯、香豆精、绿原酸、乌头酸、七叶内酯、琥珀酸、酚性成分及氨基酸等成分。\n" +
                        "\n" +
                        "【使用禁忌】脾胃虚寒者慎服。\n" +
                        "\n" +
                        "【相关药方】①治胃热气痛：天葵子6克，捣烂或嚼烂，开水吞服。(《贵阳民间药草》)\n" +
                        "\n" +
                        "②治肺痨：天葵子120克，放在1只大猪肚子内，煮烂去渣，连吃3只。(《贵阳民间药草》)\n" +
                        "\n" +
                        "③治腰酸痛：紫背天葵块根13个(或大块根)，捣烂外敷或吞服。(《浙江民间草药》)\n" +
                        "\n" +
                        "④治瘰疬，乳癌：天葵子块根1.5克，加象贝6～9克，煅牡蛎9～12克，甘草3克，同煎服数次。(《浙江民间草药》)\n" +
                        "\n" +
                        "⑤治肠粘连：天葵子15克，猪瘦肉60克。水炖至肉烂，食肉喝汤。(《安徽中医药》)");

                chineseMedicineDao.insert(medicine152);

                ChineseMedicine medicine153=new ChineseMedicine();
                medicine153.setName("天花粉");
                medicine153.setSortType("T");
                medicine153.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=f1035d3fd21373f0e13267cdc566209e/2f738bd4b31c870181cb77a2247f9e2f0708ff71.jpg");
                medicine153.setData("【中药名】天花粉 tianhuafen\n" +
                        "\n" +
                        "【别名】川花粉、栝楼根、花粉、楼根。\n" +
                        "\n" +
                        "【英文名】Radix Trichosanthis。\n" +
                        "\n" +
                        "【来源】葫芦科植物双边栝楼Trichosanthes rosthornii Harms.或栝楼Trichosanthes kirilowii Maxim.的根。(本文以双边栝楼为例，右图亦为双边栝楼)\n" +
                        "\n" +
                        "【植物形态】多年生草质藤本。块根横生，肥厚，多为圆柱形或长纺锤形。茎无毛，有棱线；卷须2～3歧。叶互生，叶片宽卵状心形或扁心形。雄花3～8朵成总状花序，花生于上端1/3处；小苞片菱状倒卵形；萼片线形，全缘；花冠白色；雌花单生。果实宽卵状椭圆形至球形。种子扁平，长椭圆形，深棕色。花期6～8月，果期9～10月。\n" +
                        "\n" +
                        "【产地分布】生于山坡、草丛、林缘半阴处。分布于我国西南、中南、华南及陕西、甘肃等地。\n" +
                        "\n" +
                        "【采收加工】秋、冬季采挖，洗净，除去须根，除去外皮，纵剖成2～4瓣，粗大者再横切成数段或斜片晒干。或直接晒干。\n" +
                        "\n" +
                        "【药材性状】不规则圆柱形、纺锤形或瓣块状；表面黄白色或淡棕黄色，有纵皱纹、细根痕及略凹陷的横长皮孔，有的有黄棕色外皮残留。质坚实，断面白色或淡黄色，富粉性，横切面可见黄色木质部，略呈放射状排列，纵切面可见黄色条纹状木质部。无臭，味微苦。\n" +
                        "\n" +
                        "【性味归经】性微寒，味甘、微苦。归肺经、胃经。\n" +
                        "\n" +
                        "【功效与作用】清热生津、消肿排脓。属清热药下属分类的清热泻火药。\n" +
                        "\n" +
                        "【临床应用】用量10～15克，煎服。用治热病烦渴、肺热燥咳、内热消渴、疮疡肿毒。\n" +
                        "\n" +
                        "【药理研究】天花粉蛋白注射液大鼠皮下给药有抗早孕作用。天花粉注射液对大鼠和小鼠有抗癌作用，还有抗艾滋病病毒，降血糖等作用。天花粉制剂临床上用于治中期妊娠、死胎、过期流产等引产。\n" +
                        "\n" +
                        "【化学成分】含淀粉及皂苷、泻根醇酸、葫芦苦素、精氨酸、天冬氨酸、果糖、木糖等成分，并含天花粉蛋白及多种氨基酸。\n" +
                        "\n" +
                        "【使用禁忌】不宜与乌头类药材同用。\n" +
                        "\n" +
                        "【相关药方】①治热病烦渴，诸脏不安：以生栝楼根，捣绞取汁，每服一合，时时服之。(《圣惠方》)\n" +
                        "\n" +
                        "②治内热痰多咳嗽：天花粉30克，杏仁、桑皮、贝母各9克，桔梗、甘草各3克。水煎服。(《本草汇言》)\n" +
                        "\n" +
                        "③治虚热咳嗽：天花粉30克，人参9克，为末。每服3克，米汤下。(《濒湖集简方》)\n" +
                        "\n" +
                        "④治乳无汁：栝楼根(切)一升，酒四升。煮三沸。去滓分三服。(《千金要方》)\n" +
                        "\n" +
                        "⑤治脾经火盛，牙龈肿痛：天花粉15克，白芍药、薄荷各6克，甘草3克。水煎服。(《本草汇言》)");

                chineseMedicineDao.insert(medicine153);

                ChineseMedicine medicine154=new ChineseMedicine();
                medicine154.setName("天麻");
                medicine154.setSortType("T");
                medicine154.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545461064&di=8d8e61ab89df6ff62b31008a3fbed9db&imgtype=jpg&er=1&src=http%3A%2F%2Fpic.baike.soso.com%2Fugc%2Fbaikepic2%2F16373%2F20180210095333-187110688_jpg_800_800_168224.jpg%2F0");
                medicine154.setData("【中药名】天麻 tianma\n" +
                        "\n" +
                        "【别名】赤箭、木浦、明天麻、定风草、神草、水洋芋。\n" +
                        "\n" +
                        "【英文名】Gastrodiae Rhizoma\n" +
                        "\n" +
                        "【来源】兰科植物天麻Gastrodia elata Bl.的块茎。\n" +
                        "\n" +
                        "【植物形态】多年生寄生植物，其寄主为蜜环菌Armillaria mellea (Vahl,ex Fr.) Quel，以蜜环菌的菌丝或菌丝的分泌物为营养来源，借以生长发育。块茎椭圆形或卵圆形，横生，肉质。茎单一，圆柱形，黄褐色。叶呈鳞片状，膜质，下部鞘状抱茎。总状花序顶生，苞片膜质，窄披针形，或条状长椭圆形，花淡黄绿色或黄色，萼片和花瓣合生成歪壶状，口部偏斜，顶端5裂；合蕊柱顶端有2个小的附属物；子房倒卵形，子房柄扭转。蒴果长圆形，有短梗。种子多数而细小，粉尘状。花期6～7月，果期7～8月。\n" +
                        "\n" +
                        "【产地分布】生于湿润的林下及肥沃的土壤上。分布于四川、云南、贵州、西藏等地。现各地有栽培。\n" +
                        "\n" +
                        "【采收加工】春季4～5月采挖为“春麻”，立冬前9～10月采挖为“冬麻”，质量较好。挖起后除去泥土，大小分档，用清水或白矾水略泡，刮去外皮，蒸或煮透心，摊开晾干或用无烟火烘干。亦可切片晒干。“明天麻”一般用硫黄熏过，略呈半透明状，色泽较好。\n" +
                        "\n" +
                        "【药材性状】椭圆形或长条形，略扁，皱缩而稍弯曲，长3～15厘米，宽1.5～6厘米，厚0.5～2厘米。表面黄白色至淡黄棕色，有纵皱纹及由潜伏芽排列而成的横环纹多轮，有时可见棕褐色菌索。顶端有红棕色至深棕色鹦鹉嘴状的芽或残留茎基；另端有圆脐形疤痕。质坚硬，不易折断，断面较平坦，黄白色至淡棕色，角质样。气微，味甘。\n" +
                        "\n" +
                        "【性味归经】性平，味甘。归肝经。\n" +
                        "\n" +
                        "【功效与作用】平肝息风止痉。属平肝息风药下属分类的息风止痉药。\n" +
                        "\n" +
                        "【临床应用】用量3～9克，煎服。用治头痛眩晕、肢体麻木、小儿惊风、癫痫抽搐、破伤风。\n" +
                        "\n" +
                        "【药理研究】动物试验证明，天麻浸膏及水煎液有镇静、镇痛、抗惊厥作用；天麻多糖有增强实验动物机体非特异性免疫及细胞免疫和抗炎作用。另有延缓衰老，抑制血小板聚集，保护心肌细胞等作用。\n" +
                        "\n" +
                        "【主要成分】本品主要含天麻苷、天麻素、天麻醚苷、天麻核苷、胡萝卜苷、巴利森苷A、腺苷、微量生物碱、多糖等，另含镍、铬、钡、锰、锌、铜等微量元素。\n" +
                        "\n" +
                        "【使用禁忌】气血虚甚者慎服。\n" +
                        "\n" +
                        "【相关药方】①治肝阳偏亢，肝风上扰，头痛眩晕，失眠：天麻9克，钩藤(后下)、川牛膝各12克，石决明(先煎)18克，山栀、黄芩、杜仲、益母草、桑寄生、夜交藤、茯神各9克。水煎服。(《杂病证治新义》天麻钩藤饮)\n" +
                        "\n" +
                        "②治高血压：天麻5克，杜仲、野菊花各10克，川芎9克；水煎服。(《秦岭巴山天然药物志》)\n" +
                        "\n" +
                        "③治偏正头痛，首风攻注，眼目肿疼昏暗，头目旋运，起坐不能：天麻45克，附子(炮制，去皮、脐)30克，半夏(汤洗七遍)30克，荆芥穗15克，木香 15克，桂枝(去粗皮)0.3克，川芎15克。上之珠，捣罗为末，入乳香匀和，滴水为丸，如梧桐子大。每服五丸。渐加至十丸。茶清下，日三。(《圣济总 录》天麻丸)\n" +
                        "\n" +
                        "④治腰脚疼痛：天麻、细辛、半夏各60克。上用绢袋二个，各盛药90克，煮熟。交互熨痛处，汗出则愈。(《世传神效名方》)\n" +
                        "\n" +
                        "⑤治风湿麻木瘫痪：天麻、扭子七各30克，羌活、独活各5克。白酒(40度)500毫米，浸泡7天。早晚适量服用。(《秦岭巴山天然药物志》)");
                    chineseMedicineDao.insert(medicine154);

                    ChineseMedicine medicine155=new ChineseMedicine();
                    medicine155.setName("天冬");
                    medicine155.setSortType("T");
                    medicine155.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545461220&di=692b830437498d740f7f6e9ada35a20b&imgtype=jpg&er=1&src=http%3A%2F%2Fspe.zwbk.org%2Ffile%2Fupload%2F201410%2F20%2F15-16-06-12-64.jpg");
                    medicine155.setData("【中药名】天冬 tiandong\n" +
                            "\n" +
                            "【别名】天门冬、小叶青、三百棒、大当门根。\n" +
                            "\n" +
                            "【英文名】Asparagi Radix。\n" +
                            "\n" +
                            "【来源】百合科植物天冬Asparagus cochinchinensis (Lour.)Merr.的块根。\n" +
                            "\n" +
                            "【植物形态】多年生攀援草本，全株无毛。块根肉质，在中部及近末端呈纺锤状膨大。茎不能直立，分枝具棱或狭翅；叶状枝通常每3枚成簇，扁平，或由于中脉龙骨状而略呈锐三角形、镰刀状，退化；叶成鳞片状，顶端长尖，基部有木质倒生刺，刺在分枝上较短或不明显。花通常2朵腋生，单性，雌雄异株，淡绿色；雄花花被片6，雄蕊稍短于花被，花丝不贴生于花被片上，花药卵形；雌花与雄花大小相似，具6枚退化雄蕊。浆果球形，成熟时红色；具1粒种子。花期5～7月，果期8月。\n" +
                            "\n" +
                            "【产地分布】生于阴湿的山野林边、山坡草丛中或丘陵地带灌木丛中，也有栽培。分布于甘肃、四川、贵州、云南等地。\n" +
                            "\n" +
                            "【采收加工】秋、冬季采挖，洗净，除去茎基和须根，置沸水中煮或蒸至透心，趁热除去外皮，洗净，干燥。\n" +
                            "\n" +
                            "【药材性状】长纺锤形，略弯曲，长5～18厘米，直径0.5～2厘米。表面黄白色至淡黄棕色，半透明，光滑或具深浅不等的纵皱纹，偶有残存的灰棕色外皮。质硬或柔润，有黏性，断面角质样，中柱黄白色。气微，味甜、微苦。\n" +
                            "\n" +
                            "【性味归经】性寒，味甘、苦。归肺经、肾经。\n" +
                            "\n" +
                            "【功效与作用】养阴润燥、润肺生津。属补虚药下属分类的补阴药。\n" +
                            "\n" +
                            "【临床应用】用量6～12克，煎汤服。用治肺热干咳、顿咳痰黏、咽干口渴、肠燥便秘。\n" +
                            "\n" +
                            "【药理研究】临床上用治乳房肿瘤、扩张子宫颈以及子宫出血。抗菌，杀灭孑孓，抗肿瘤。\n" +
                            "\n" +
                            "【化学成分】本品含苷类、皂苷及其苷元、氨基酸、糖类等化学成分。含天冬素、5-甲氧基-甲基糠醛、葡萄糖、果糖、β-谷甾醇、黏液质以及甾体皂苷类。动物试验表明，水煎剂有抑菌、镇咳、抗肿瘤和杀虫作用。\n" +
                            "\n" +
                            "【使用禁忌】虚寒泄泻及风寒咳嗽者禁服。\n" +
                            "\n" +
                            "【相关药方】①治肺胃燥热，痰涩咳嗽：天冬(去心)、麦门冬(去心)等分。上两味熬膏，炼白蜜收，不时含热咽之。(《张氏医通》二冬膏)\n" +
                            "\n" +
                            "②治女子白带：天冬捣汁，井水调服。(《普济方》)\n" +
                            "\n" +
                            "③催乳：天冬60克。炖肉服。(《云南中草药》)\n" +
                            "\n" +
                            "④治心烦：天冬、麦冬各15克，水杨柳9克。水煎服。(《湖南药物志》)\n" +
                            "\n" +
                            "⑤治扁桃体炎，咽喉肿痛：天冬、麦冬、板蓝根、桔梗、山豆根各9克，甘草6克。水煎服。(《山东中草药手册》)\n" +
                            "\n");
                    chineseMedicineDao.insert(medicine155);

                    ChineseMedicine medicine156=new ChineseMedicine();
                    medicine156.setName("土党参");
                    medicine156.setSortType("T");
                    medicine156.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=fc4772e30255b31988f48a2722c0e943/faf2b2119313b07e99046eea04d7912396dd8c92.jpg");
                    medicine156.setData("【中药名】土党参 tudangshen\n" +
                            "\n" +
                            "【别名】土羊乳、野党参、土人参。\n" +
                            "\n" +
                            "【英文名】Radix Campanumoeae\n" +
                            "\n" +
                            "【来源】桔梗科植物大花金钱豹Campanumoea javanica Bl的干燥根。\n" +
                            "\n" +
                            "【植物形态】多年生草质缠绕藤本，长可达2米。根茎极短，根肥大，肉质，有分枝，外皮淡黄色。全株光滑无毛，具白色粉霜，有白色乳汁。叶通常对生，叶柄与叶片近等长；叶片卵状心形，先端钝尖，基部心形，边缘有浅钝齿。花1～2朵腋生，萼管短，与子房贴生，5深裂，裂片三角状披针形，花冠钟状，下部与子房连生，5裂近中部，裂片卵状三角形，向外反卷，外面淡黄绿色，内面下部紫色；雄蕊5，线形，花丝窄线形，基部变宽；子房半下位，花柱无毛，柱头通常5裂。浆果近球形。熟时黑紫色。花期8～9月，果期9～10月。\n" +
                            "\n" +
                            "【产地分布】生于海拔400～1800米的向阳草坡或丛林中。分布于广东、广西、贵州、云南。\n" +
                            "\n" +
                            "【采收加工】秋季采挖，洗净，晒干。\n" +
                            "\n" +
                            "【药材性状】本品呈圆柱形，少分枝，扭曲不直，长10～25厘米，直径0.5～1.5 厘米。顶部有密集的点状茎痕。表面灰黄色，全体具纵皱纹，质硬而脆，易折断。断面较平坦，可见明显的形成层。木质部黄色，木化程度较强，气微，味淡而微甜。\n" +
                            "\n" +
                            "【性味归经】性平，味甘。归脾经、肺经。\n" +
                            "\n" +
                            "【功效与作用】健脾益气，补肺止咳，下乳。属补虚药下分类的补气药。\n" +
                            "\n" +
                            "【临床应用】内服：煎汤，15～30克；干品9～15克。外用：鲜品适量，捣烂敷。主治虚劳内伤，气虚乏力，心悸，多汗，脾虚泄泻，白带，乳汁稀少，小儿疳积，遗尿，肺虚咳嗽。\n" +
                            "\n" +
                            "【化学成分】土党参90%乙醇提取物的醋酸乙酯萃取部位中分离得到14个化合物,分别鉴定为金钱豹苷(1)、lobetyol(2)、4E,8E,12E-三烯-10-炔-1,6,7-十四烷三醇(3)、9-(2-四氢吡喃)-8E-烯-4,6-二炔-3-壬醇(4)、9-(2-四氢吡喃)-2E,8E-二烯-4,6-二炔-1-壬醇(5)、lobetyolinin(6)、(Z)-3-己烯-O-&alpha；-L-吡喃阿拉伯糖基-(1&rarr；6)-&beta；-D-吡喃葡萄糖苷(7)、3,4-二羟基苯甲酸(8)、党参苷II(9)、zanthocapensol(10)、蛇葡萄素(11)、贝壳杉双芹素(12)、&beta；-脱皮甾酮(13)、&alpha；-托可醌(14)\n" +
                            "\n" +
                            "【使用禁忌】无。\n" +
                            "\n" +
                            "【相关药方】①治虚劳：土党参60克，糯米300克。水煎克。(《湖北中草药志》)\n" +
                            "\n" +
                            "②治多汗、心悸：土党参15克。水煎服。(《湖北中草药志》)\n" +
                            "\n" +
                            "③治脾虚泄泻：土党参15～30克，大枣9～15克。水煎服。(《福建中草药》)\n" +
                            "\n" +
                            "④治小几疳积：鲜土党参30克，白糖适量，水煎服。或取汤冲鲜鸡蛋1枚服。(《福建药物志》)\n" +
                            "\n" +
                            "⑤治小儿遗尿：土党参根60～120克，猪瘦肉120克。水炖，服汤食肉。(《江西草药》)");

                    chineseMedicineDao.insert(medicine156);

                    ChineseMedicine medicine157=new ChineseMedicine();
                    medicine157.setName("土木香");
                    medicine157.setSortType("T");
                    medicine157.setMedicineImageUrl("http://img5.imgtn.bdimg.com/it/u=3619404052,1575578345&fm=26&gp=0.jpg");
                    medicine157.setData("【中药名】土木香 tumuxiang\n" +
                            "\n" +
                            "【别名】青木香、祁木香、藏木香。\n" +
                            "\n" +
                            "【英文名】Inulae Radix。\n" +
                            "\n" +
                            "【来源】菊科植物土木香Inula helenium L.的根。\n" +
                            "\n" +
                            "【植物形态】多年生高大草本，高1～2米，全株被短柔毛。主根肥大，圆柱形至长圆形，有香气。基生叶大，椭圆状披针形，先端锐尖，基部渐窄下延成翅状，边缘具不整齐锯齿，上面粗糙，下面密被白色或淡黄色茸毛，茎生叶较小，无柄，基部有耳，半抱茎。头状花序数个排成伞房状，总苞片5～6层，多至10层，内层干膜质，较外层长。花黄色，边花一层，雌性，舌状，中央管状花，两性。瘦果有棱角，冠毛污白色。\n" +
                            "\n" +
                            "【产地分布】生于河边、田边等潮湿处，或为栽培。分布于黑龙江、吉林、辽宁等地。\n" +
                            "\n" +
                            "【采收加工】秋末采挖根部，除去残茎、泥沙，截段，较粗的纵切成瓣，晒干。\n" +
                            "\n" +
                            "【药材性状】根呈圆柱形或长圆锥形，稍弯曲或扭曲，少数为圆锥状的短段或不规则的块片，长10～20厘米，直径0.5～2厘米。表面深棕色，具纵皱纹及不明显的横向皮孔，顶端有稍凹陷的茎痕及棕红色叶柄残基，根头部稍膨大，多纵切开或斜切成截形，边缘稍向外反卷。质坚硬，不易折断，断面不平坦，稍呈角质样，乳白色至浅黄棕色，形成层环状明显，木质部略显放射状纹理。气微，味微苦而灼辣。\n" +
                            "\n" +
                            "【性味归经】性温，味辛、苦。归肝经、脾经。\n" +
                            "\n" +
                            "【功效与作用】健脾和胃、调气解郁、止痛安胎。属理气药。\n" +
                            "\n" +
                            "【临床应用】用量3~9克，内服煎汤，或入丸散，治疗胸胁、脘腹胀痛、呕吐泻痢、胸胁挫伤、岔气作痛、胎动不安。内热口干、喉干舌绛者忌用。\n" +
                            "\n" +
                            "【药理研究】挥发油中所含土木香内酯对猪、犬、猫均有驱虫作用，而且毒性较低；土木香内酯能抑制结核杆菌的生长，异土木香内酯对须癣毛菌、大小孢菌有明显的抑制作用；土木香内酯低浓度对离体蛙心有兴奋作用，较高浓度则有抑制作用。毒性：因含毒性很强的蛋白质，人应用土木香过量，可发生四肢疼痛、吐、泻、眩晕及皮疹等。 土木香内酯对蛙、小鼠及家兔的作用类似印防己毒素类。\n" +
                            "\n" +
                            "【主要成分】根含挥发油1%～3%，菊糖约40%，油中主要成分为土木香内酯，此外含异土木香内酯及三萜类成分。地上部分含双氧代大栊牛儿内酯和2-&alpha；-羟基内酯。叶中含土木香苦素。\n" +
                            "\n" +
                            "【使用禁忌】内热口干，喉干舌绛者忌用。\n" +
                            "\n" +
                            "【相关药方】①治胃痛：土木香3克，元胡15克。研末水冲服，每日2次。(《山西中草药》)\n" +
                            "\n" +
                            "②治胸胁胃胀痛不适：土木香、藿香、枳壳、陈皮各9克。水煎服。(《山西中草药》)\n" +
                            "\n" +
                            "③治腹泻肠鸣：土木香、雄黄连各等量。研粉，每次2～3克，开水吞服。(《湖北中草药志》)\n" +
                            "\n" +
                            "④治细菌性痢疾：土木香、黄连各9克。水煎服。(《河北中草药》)\n" +
                            "\n" +
                            "⑤治牙痛：土木香适量。捣烂或嚼烂，含患处或入虫牙孔内。(《湖北中草药志》)\n" +
                            "\n" +
                            "⑥洎肋问神经痛：土木香、郁金各9克。水煎服。(《河北中草药》)");

                    chineseMedicineDao.insert(medicine157);

                    ChineseMedicine medicine158=new ChineseMedicine();
                    medicine158.setName("土大黄");
                    medicine158.setSortType("T");
                    medicine158.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=b3775569a74bd11310c0bf603bc6cf6a/728da9773912b31bcbb37c338c18367adbb4e1c9.jpg");
                    medicine158.setData("【中药名】土大黄 tudahuang\n" +
                            "\n" +
                            "【别名】金不换、止血草、化血莲、红筋大黄。\n" +
                            "\n" +
                            "【英文名】\n" +
                            "\n" +
                            "【来源】蓼科植物红丝酸模Rumex chalepensis Mill.或钝叶酸模Rumex obtusifolius L.的根及根茎。\n" +
                            "\n" +
                            "【植物形态】多年生草本，高约1米。主根粗大肥厚，黄色。茎直立，紫绿色，有多数纵沟。基生叶卵形至卵状长椭圆形，长20～30厘米，宽10～20厘米，先端钝或钝圆，基部心形，下面有小瘤状突起，有长叶柄，托叶鞘膜质，早落；茎生叶卵状披针形，向上渐小，叶脉红色。花成簇集成圆锥状总状花序；花被片6，淡绿色，2轮，内轮花被片圆心形，每边具4～7齿；雄蕊6；子房三角卵状，花柱3，柱头毛状。瘦果卵形，有3棱，褐色，包于增大的内轮花被内。花期5～6月，果期6～7月。\n" +
                            "\n" +
                            "【产地分布】多生于山脚或山坡近水处。分布于江苏、安徽、浙江、江西、河南、湖南、河北、山东等地。\n" +
                            "\n" +
                            "【采收加工】秋季挖取根部，洗净泥土及杂质，切片，晒干或鲜用。\n" +
                            "\n" +
                            "【药材性状】根及根茎呈类圆锥形，长15～17厘米，根茎直径达3厘米，根直径为1.5～1.8厘米。全体棕红色至灰棕色。根茎部顶端有茎基残基，并具有棕色鳞片状物及须毛状纤维。根部常有分枝，表面具多数纵皱纹，表面散有横长皮孔样疤痕及点状须根痕。质硬，切断面黄色，有棕色环纹及放射状纹理。气微，味微苦。\n" +
                            "\n" +
                            "【性味归经】性凉，味苦、辛。归经无。\n" +
                            "\n" +
                            "【功效与作用】清热解毒，止血，祛瘀，通便，杀虫。属清热药下分类的清热解毒药。\n" +
                            "\n" +
                            "【临床应用】用量9～15克，水煎服。外用适量。常用于肺脓疡，肺结核咯血、衄血、痈肿疮毒、湿症，跌打损伤、便秘及烫伤等症。\n" +
                            "\n" +
                            "【药理】具有抗菌、止血等作用。\n" +
                            "\n" +
                            "【化学成分】土大黄根及根茎主要含蒽醌衍生物大黄素、大黄素甲醚及大黄酚，还含酸模素、6-O-丙二酰基-&beta；-甲基-D-吡喃葡萄糖苷、阿斯考巴拉酸及止血有效成分磷酸铵镁，尚含多量鞣质。\n" +
                            "\n" +
                            "【使用禁忌】无。\n" +
                            "\n" +
                            "【相关药方】①治小腹瘀痛：土大黄根9～15g。酒水各半，煎服。(《海南药物志》)\n" +
                            "\n" +
                            "②治烧伤：土大黄15克，地榆15克，研细末，加冰片0.3克。菜油调敷。(《四川中药志》1982年)\n" +
                            "\n" +
                            "③治血小板减少：土大黄15克。水煎，每日1剂，分3次服。(《青岛中草药手册》)\n" +
                            "\n" +
                            "④治咳嗽吐血，跌打受伤吐血：金不换15～21克，和精猪肉切细，做成肉饼，隔水蒸熟食之。(《中医药实验研究》)\n" +
                            "\n" +
                            "⑤治皮炎，湿疹：土大黄适量。煎水洗。(广州部队《常用中草药手册》)\n" +
                            "\n" +
                            "⑥治腮腺炎：鲜土大黄根、鲜天葵根各适量，酒糟少许，捣烂外敷。(《江西草药》)\n" +
                            "\n" +
                            "⑦治肺痈：金不换草根30克。捣汁酒煎服，三次愈。(《白草镜》)\n" +
                            "\n" +
                            "⑧治癣癞：土大黄根以石灰水浸2小时，用醋磨搽。(《湖南药物志》)");

                    chineseMedicineDao.insert(medicine158);

                    ChineseMedicine medicine159=new ChineseMedicine();
                    medicine159.setName("铁皮石斛");
                    medicine159.setSortType("T");
                    medicine159.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=efef2042ae773912d02b8d339970ed7d/d1a20cf431adcbef6fe518f9abaf2edda2cc9f7f.jpg");
                    medicine159.setData("【中药名】铁皮石斛 tiepishihu\n" +
                            "\n" +
                            "【别名】耳环石斛、风斗、枫斗、黑节。\n" +
                            "\n" +
                            "【英文名】Dendrobii Officmalis Caulis。\n" +
                            "\n" +
                            "【来源】兰科植物铁皮石斛Dendrobium officinale Kimura et Migo的干燥茎。\n" +
                            "\n" +
                            "【植物形态】茎圆柱形，长9~35厘米，多节。叶3~5，生于茎上部，无柄，叶片长圆状披针形，长3~7厘米，宽0.8~2厘米，叶鞘灰色有紫斑，鞘口张开。总状花序有花2~5朵，生于茎上部，花被片淡黄绿色，直径3~4厘米，唇瓣卵状披针形，近上部中央有圆形紫色斑块，近下部中间有绿色或黄色胼胝体，雄蕊白色。蒴果长圆形，具3棱。花期3~6月。\n" +
                            "\n" +
                            "【产地分布】附生于山中潮湿岩石上。主产于安徽、浙江、福建、广西、四川、云南。\n" +
                            "\n" +
                            "【采收加工】11月至翌年3月采收，除去杂质，剪去部分须根，边加热边扭成螺旋形或弹簧状，烘干；或切成段，干燥或低温烘干，前者习称“铁皮枫斗”(耳环石斛)；后者习称“铁皮石斛”。\n" +
                            "\n" +
                            "【药材性状】铁皮枫斗：本品呈螺旋形或弹簧状.通常为2～6个旋纹，茎拉直后长3.5～8厘米，直径0.2～0.4厘米。表面黄绿色或略带金黄色，有细纵皱纹，节明显，节上有时可见残留的灰白色叶鞘；一端可见茎基部留下的短须根。质坚实，易折断，断面平坦，灰白色至灰绿色，略角质状。气微，味淡，嚼之有黏性。铁皮石斛：本品呈圆柱形的段，长短不等。\n" +
                            "\n" +
                            "【性味归经】性微寒，味甘。归胃经、肾经。\n" +
                            "\n" +
                            "【功效与作用】益胃生津，滋阴清热。属补虚药下属分类的补阴药。\n" +
                            "\n" +
                            "【临床应用】用量6～12克，鲜品15～30g，内服煎汤。用治热病津伤，口干烦渴，胃阴不足，食少于呕，病后虚热不退，阴虚火旺，骨蒸劳热，目暗不明，筋骨痿软。\n" +
                            "\n" +
                            "【主要成分】含鼓槌菲(chrysoxene)、毛兰素(erianin)等。对半乳糖所致的白内障晶状体中醛糖还原酶、多元醇脱氢酶的活性异常变化有抑制或纠正作用。石斛多糖具有增强T细胞及巨噬细胞免疫活性的作用；能显著提高超氧化物歧化酶(SOD)水平，从而起到降低脂质过氧化物(LPO)的作用。\n" +
                            "\n" +
                            "【使用禁忌】尚不明确。");

                    chineseMedicineDao.insert(medicine159);

                    ChineseMedicine medicine160=new ChineseMedicine();
                    medicine160.setName("桃枝");
                    medicine160.setSortType("T");
                    medicine160.setMedicineImageUrl("http://img.xiangshu.com/Day_180406/52_905625_a62554963b7fa81.jpg");
                    medicine160.setData("【中药名】桃枝 taozhi\n" +
                            "\n" +
                            "【别名】\n" +
                            "\n" +
                            "【英文名】Persicae Ramulus。\n" +
                            "\n" +
                            "【来源】蔷薇科植物桃Prunus persica (L.) Batsch的干燥枝条。\n" +
                            "\n" +
                            "【植物形态】落叶乔木。株高4~8米。嫩枝无毛，有光泽。叶椭圆状披针形或长圆状披针形，长8~12厘米，宽3~4厘米，先端长渐尖，基部楔形，边缘有较密的锯齿，两面无毛或下面脉腋间具稀疏短柔毛，叶柄长1~2厘米，无毛，具腺点。花常单生，先叶开放，直径2.5~3.5厘米，花梗极短。萼筒钟形，被短柔毛。萼片卵圆形或长圆状三角形，被短柔毛。花瓣粉红色。雄蕊多数，子房被毛。核果，近球形或卵圆形，直径5~7厘米，表面被绒毛。果肉多汁，不开裂。果核表面具沟和皱纹。花期4~5月，果期6~8月。\n" +
                            "\n" +
                            "【产地分布】全国各地多有栽培。\n" +
                            "\n" +
                            "【采收加工】夏季采收，切段，晒干。\n" +
                            "\n" +
                            "【药材性状】呈圆柱形，长短不一，直径0.2~1厘米，表面红褐色，较光滑，有类白色点状皮孔。质脆，易折断，切面黄白色，木部占大部分，髓部白色。气微，叶微苦、涩。\n" +
                            "\n" +
                            "【性味归经】性平，味苦。归心经、胃经。\n" +
                            "\n" +
                            "【功效与作用】活血通络，解毒，杀虫。属活血化瘀药下属分类的活血调经药。\n" +
                            "\n" +
                            "【临床应用】用量9-15克，水煎服或入丸散。外用煎水含漱或洗浴。用治心腹痛，风湿关节痛，腰痛，跌打损伤，疮癣。\n" +
                            "\n" +
                            "【主要成分】茎中含柚皮素及其葡萄糖甙，山柰素及其葡萄糖甙，二氢山柰酚，山柰素葡萄糖甙，橙皮素葡萄糖甙，槲皮甙葡萄糖甙，右旋儿茶精，&beta；-谷甾醇葡萄糖甙，洋李甙即柚皮素-7-O-葡萄糖，橙皮素-5-O-葡萄糖甙。\n" +
                            "\n" +
                            "【使用禁忌】尚不明确。\n" +
                            "\n");
                    chineseMedicineDao.insert(medicine160);
                    ChineseMedicine medicine161=new ChineseMedicine();
                    medicine161.setName("天山雪莲");
                    medicine161.setSortType("T");
                    medicine161.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=d5d8408db6003af359b7d4325443ad39/4a36acaf2edda3cc9fd218a401e93901213f9204.jpg");
                    medicine161.setData("【中药名】天山雪莲 tianshanxuelian\n" +
                            "\n" +
                            "【别名】新疆雪莲。\n" +
                            "\n" +
                            "【英文名】Saussureae Involucratae Herba。\n" +
                            "\n" +
                            "【来源】系维吾尔族常用药材。为菊科植物天山雪莲Saus-surea involucrata(Kar.et Kir.) Sch.-Bip.的干燥地上部分。\n" +
                            "\n" +
                            "【植物形态】多年生草本，高15～35cm。根茎粗壮，具棕色宿存叶柄。茎无毛，直径2~3cm。叶密集，基生叶和茎生叶无柄，叶椭圆形或卵状椭圆形，基部下延，顶端急尖，边缘有尖齿，两面无毛，最上部叶苞叶状，宽卵形，长5.5～7cm，边缘有尖齿，膜质，淡黄色，包被总花序。头状花序无柄，10～20个在茎密集成球形总花序，总苞半球形，径1cm，总苞片3~4层，边缘或全部紫褐色，外层长圆形，长1.1cm，疏被长柔毛，中层及内层披针形，长1.5～1.8cm。小花紫色。瘦果长圆形，冠毛污白色，2层。花果期7~9月。\n" +
                            "\n" +
                            "【产地分布】生于海拔2400～4100米高山谷、石缝、水边、草丛。分布于新疆。\n" +
                            "\n" +
                            "【采收加工】夏、秋二季花开时采收，拔起全株，除去泥沙，阴干。\n" +
                            "\n" +
                            "【药材性状】茎呈圆柱形，长20～48cm，直径0.5～3cm；表面黄绿色或黄棕色，有的微带紫色，具纵棱，断面中空。茎生叶密集排列，无柄，或脱落留有残基，完整叶片呈卵状长圆形或广披针形，两面被柔毛，边缘有锯齿和缘毛，主脉明显。头状花序顶生，10～42个密集成圆球形，无梗。苞叶长卵形或卵形，无柄，中部凹陷呈舟状，膜质，半透明。总苞片3～4层，披针形，等长，外层多呈紫褐色，内层棕黄色或黄白色。花管状，紫红色，柱头2裂。瘦果圆柱形，具纵棱，羽状冠毛2 层。体轻，质脆。气微香，味微苦。\n" +
                            "\n" +
                            "【性味归经】性温，味微苦。归经无。\n" +
                            "\n" +
                            "【功效与作用】温肾助阳，祛风胜湿，通经活血。属活血化瘀药下属分类的活血调经药。\n" +
                            "\n" +
                            "【临床应用】用量3～6g，水煎或酒浸服。外用适量。用于风寒湿痹痛、类风湿性关节炎，小腹冷痛，月经不调。\n" +
                            "\n" +
                            "【主要成分】全草含具药理活性的多糖，平均分子量16000，还含倍半萜内酯成分。\n" +
                            "\n" +
                            "【使用禁忌】孕妇忌用。");

                    chineseMedicineDao.insert(medicine161);
                    ChineseMedicine medicine162=new ChineseMedicine();
                    medicine162.setName("土贝母");
                    medicine162.setSortType("T");
                    medicine162.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=6864f43a76c6a7efad2ba0749c93c434/5bafa40f4bfbfbed2411679a78f0f736aec31f8e.jpg");
                    medicine162.setData("【中药名】土贝母 tubeimu\n" +
                            "\n" +
                            "【别名】大贝母、假贝母。\n" +
                            "\n" +
                            "【英文名】Bolbostemmatis Rhizoma。\n" +
                            "\n" +
                            "【来源】葫芦科植物土贝母Bolbostemma paniculatum( Maxim. )Franquet的块茎。\n" +
                            "\n" +
                            "【植物形态】多年生攀援草本。鳞茎近球形，由数个至十余个肥厚鳞叶聚生而成。茎细弱，卷须单一或分叉。叶具短柄；叶片卵状近圆形，长5~10厘米，宽4~9厘米，掌状5深裂，裂片再3~5浅裂，基部裂片的顶端有近白色腺体1~2对。花单性，雌雄异株，呈疏散圆锥状花序或单生；花黄绿色，花萼与花冠相似，基部合生，上部5深裂；雄蕊5枚，分生；子房下位，3室，花柱3。果圆柱形，成熟时由顶端盖裂。种子6，斜方形，先端具膜质翅。花期6~7月，果期8~9月。\n" +
                            "\n" +
                            "【产地分布】生于山阴坡、林下。现多栽培。分布于辽宁、河北、河南、山东、山西、陕西、甘肃、云南等地。\n" +
                            "\n" +
                            "【采收加工】秋季采挖，洗净，掰开，煮至无白心，取出，晒干。\n" +
                            "\n" +
                            "【药材性状】多角形、三棱形或不规则半透明块状，大小不一。表面棕色或棕红色，凹凸不平。腹面常有一纵凹沟，基部有连在中轴上的短柄，背面多隆起。质坚硬，不易折断，断面角质样，平滑，发亮。气无，味微苦。\n" +
                            "\n" +
                            "【性味归经】性微寒，味苦。归肺经、脾经。\n" +
                            "\n" +
                            "【功效与作用】散结、消肿、解毒。属拔毒生肌药。\n" +
                            "\n" +
                            "【临床应用】用量4.5~9克，内服煎汤。用治乳痈、瘰疬、乳腺炎、颈淋巴结结核、慢性淋巴结炎、肥厚性鼻炎。\n" +
                            "\n" +
                            "【主要成分】含生物碱、蔗糖等成分。土贝母的成分与贝母不同，作用有很大差异。药理试验结果已表明，有抗炎和抗癌作用，另对免疫功能也有多方面的影响作用。\n" +
                            "\n" +
                            "【使用禁忌】尚不明确。");

                    chineseMedicineDao.insert(medicine162);

                    ChineseMedicine medicine163=new ChineseMedicine();
                    medicine163.setName("铁包金");
                    medicine163.setSortType("T");
                    medicine163.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=87a653bd7f1ed21b6dc426b7cc07b6a1/79f0f736afc379310a674466e9c4b74543a91175.jpg");
                    medicine163.setData("【中药名】铁包金 tiebaojin\n" +
                            "\n" +
                            "【别名】老鼠耳、乌口仔、乌龙根。\n" +
                            "\n" +
                            "【英文名】Radix Berchemiae Lineatae。\n" +
                            "\n" +
                            "【来源】鼠李科植物老鼠耳Berchemia lineata(L.)DC.的茎和根。\n" +
                            "\n" +
                            "【植物形态】藤状灌木，高1~4米；小枝灰褐色，稍有短柔毛。主根粗壮，表面褐黑色，里面黄色，故称“铁包金”。单叶互生，具短柄；纸质，叶片阔卵形或近圆形，先端圆钝，基部圆形，全缘；侧脉5~7，直而平行，两面无毛。花2~4朵簇生于叶腋或枝顶端，白色，有短柔毛；萼片花瓣各5，狭披针形或条形。核果长圆形，黄豆大，基部具宿存的花盘和花萼；熟时紫黑色，味甜可食，食时把唇舌染成紫黑色，故有“乌口仔”之名。花期7~10月，果期11月。\n" +
                            "\n" +
                            "【产地分布】生于山地灌丛中、路旁或田边。分布于广西、广东、福建、台湾等地。\n" +
                            "\n" +
                            "【采收加工】全年均可采收，除去叶、嫩枝及须根，洗净，干燥，或趁鲜切片或段，干燥。\n" +
                            "\n" +
                            "【药材性状】根呈不规则纺锤形或圆柱形，弯曲分枝，多切成小段或厚片。表面暗黄棕色、黑褐色至深褐色，栓皮结实，有网状裂隙、纵皱纹及支根痕。质坚硬，断面木部纹理细密，暗黄棕色至橙黄色。气微，味淡微涩。\n" +
                            "\n" +
                            "【性味归经】性平，味甘、淡、涩。归肺经、心经。\n" +
                            "\n" +
                            "【功效与作用】理肺止咳、祛瘀止痛、舒肝退黄、健胃消积。属活血化瘀药下属分类的活血止痛药。\n" +
                            "\n" +
                            "【临床应用】用量15~30克，煎服。外用，捣敷或煎水洗。用于劳伤咯血，胃、十二指肠溃疡出血，精神分裂症，跌打损伤，风湿骨痛，疔疮疖肿，颈淋巴结肿大，睾丸肿痛。根及枝叶单味煎服，治小儿疳积、小儿胃纳呆滞。\n" +
                            "\n" +
                            "【主要成分】据抗菌试验，本品对金黄色葡萄球菌、溶血性链球菌、福氏痢疾杆菌、伤寒杆菌等有抑制作用。\n" +
                            "\n" +
                            "【使用禁忌】尚不明确。");

                    chineseMedicineDao.insert(medicine163);

                    ChineseMedicine medicine164=new ChineseMedicine();
                    medicine164.setName("土茯苓");
                    medicine164.setSortType("T");
                    medicine164.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=01e9115e4710b912abccfeaca2949766/63d0f703918fa0ecc40232e8269759ee3c6ddb0a.jpg");
                    medicine164.setData("【中药名】土茯苓 tufuling\n" +
                            "\n" +
                            "【别名】土萆薜、硬饭头、冷饭团。\n" +
                            "\n" +
                            "【英文名】Smilacis Glabrae Rhizoma\n" +
                            "\n" +
                            "【来源】百合科植物光叶菝葜Smilax glabra Roxb.的根茎。\n" +
                            "\n" +
                            "【植物形态】攀援状灌木，具圆柱状或弯曲的根状茎；地上茎无刺。叶互生，革质，椭圆形、卵状披针形或披针形；掌状脉5条；常有2条纤细的卷须。花雌雄异株；伞形花序单生于叶腋，雄花的总花梗极短，小苞片三角形，宿存；花蕾三棱形，花被片6，外轮倒心形，背部凸起，内轮较小，圆形；雄蕊6枚；雌花序的总花梗较长，退化雄蕊3枚。浆果球形，成熟时红至黑色，外被白粉。种子球形。\n" +
                            "\n" +
                            "【产地分布】生于山地、山坡、山谷疏林下和灌木丛中或河岸林缘。分布于广东、海南、广西、福建等地。\n" +
                            "\n" +
                            "【采收加工】夏、秋季采挖，除去须根，洗净，干燥；或趁鲜切成薄片，干燥。\n" +
                            "\n" +
                            "【药材性状】略圆柱形，稍扁或不规则条块，有结节状隆起，具短分枝。表面黄棕色或灰褐色，凹凸不平，有坚硬的须根残基，分枝顶端有圆形芽痕，有的外皮现不规则裂纹，并有残留的鳞叶。质坚硬。无臭，味微甘、涩。\n" +
                            "\n" +
                            "【性味归经】性平，味甘、淡。归胃经、肝经。\n" +
                            "\n" +
                            "【功效与作用】解毒利尿、通利关节。属清热药下分类的清热燥湿药。\n" +
                            "\n" +
                            "【临床应用】用量15~60克，煎服。用治湿热淋浊、带下、痈肿、瘰疬、疥癣、梅毒及汞中毒所致的肢体拘挛、筋骨疼痛。现代临床用土茯苓复方治疗急性肾小球肾炎和慢性肾炎急性发作疗效良好；还可用于治疗乙型肝炎、前列腺炎、急性睾丸炎、阴道炎、溃疡性结肠炎以及治疗痛风、膝关节积液、淋病性尿道炎。\n" +
                            "\n" +
                            "【主要成分】土茯苓与其伪品菝葜、肖菝葜在( 220±；2)纳米处的紫外吸收峰均有，但在280~ 290纳米处紫外线吸收峰位置不同，可将其区别开。根主含落新妇苷、异黄杞苷、胡萝卜苷、琥珀酸、棕榈酸、黄杞苷表儿茶精、土茯苓苷、豆甾醇-3-0-B-D-吡喃葡萄糖苷、槲皮素、异落新妇苷等，此外，尚含鞣质、树脂、薯蓣皂苷元及微量挥发油。水提物在抗原致敏及攻击后给药均明显地抑制了三硝基氯苯所致的小鼠接触性皮炎，以攻击后给药作用较强。具抗菌作用。此外，对移植性肿瘤艾氏腹水癌和对黄曲霉素B，( AFB)致大鼠肝癌病变均有一定抑制作用。\n" +
                            "\n" +
                            "【使用禁忌】肝肾阴虚者慎服。");

                    chineseMedicineDao.insert(medicine164);

                    ChineseMedicine medicine165=new ChineseMedicine();
                    medicine165.setName("土三七");
                    medicine165.setSortType("T");
                    medicine165.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=e3eedbe3454a20a425133495f13bf347/3b87e950352ac65cb0c0e582f1f2b21192138a7e.jpg");
                    medicine165.setData("【药名】土三七 tǔ sān qī\n" +
                            "\n" +
                            "【别名】红背三七、狗头三七、三七草。\n" +
                            "\n" +
                            "【来源】菊科植物菊叶三七Gynura segetum( Lour.) Merr.的根。\n" +
                            "\n" +
                            "【植物形态】多年生草本，高1～1.5米。块根肉质肥大，具疣状突起及须根。茎直立，稍肉质，幼时紫绿色，有细纵棱，具细毛。基生叶簇生，匙形，全缘或有锯齿或羽状分裂，背面带紫绿色，花时凋落；茎生叶互生，长椭圆形，长10~25厘米，宽~10厘米，羽状分裂，裂片卵形至披针形，边缘浅裂或有疏锯齿；叶柄基部有假托叶l对。头状花序多数，排成伞房状；总苞圆柱状，苞片2层，外层丝状；筒状花金黄色，两性，柱头分枝顶端有细长线形的具毛的尖端。瘦果狭圆柱形，褐色，有棱，冠毛多数。花期9～10月。\n" +
                            "\n" +
                            "【产地分布】生于阴湿肥沃处。部分地区有栽培。分布于河北、四川、云南、广东、广西、江苏等地。\n" +
                            "\n" +
                            "【采收加工】秋季挖取块根，除去残存的茎、叶及泥土，晒干或鲜用。\n" +
                            "\n" +
                            "【药材性状】呈拳形肥厚的圆块状，长3~6厘米，直径约3厘米。表面灰棕色或棕黄色，全体有瘤状突起及断续的弧状沟纹，在突出物顶端常有茎基或芽痕，下部有须根或已折断。质坚实，不易折断，断面不平，新鲜时白色，干燥者呈淡黄色，有菊花心。气微，味甘淡，微苦。\n" +
                            "\n" +
                            "【性味归经】性温，味甘，苦。\n" +
                            "\n" +
                            "【功效与作用】破血散瘀，止血消肿。\n" +
                            "\n" +
                            "【临床应用】用量10~15克，研末口服，0.25~5克。外用：适量捣敷。用于跌打损伤、创伤。\n" +
                            "\n" +
                            "【主要成分】根含蛋白质、多糖、鞣质、有机酸、色素和生物碱等。后又从根中分得千里光碱( seneciomine)、千里光菲灵碱(seneciphylline)、菊三七碱甲( seneciphylinine A)、菊三七碱乙(seneciphylinine B)。\n" +
                            "\n" +
                            "【使用禁忌】\n" +
                            "\n"
                            );
                    chineseMedicineDao.insert(medicine165);

                    ChineseMedicine medicine166=new ChineseMedicine();
                    medicine166.setName("兔儿伞");
                    medicine166.setSortType("T");
                    medicine166.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=ef77795785b1cb132a643441bc3d3d2b/a8773912b31bb05123c6a1903e7adab44bede0ac.jpg");
                    medicine166.setData("【药名】兔儿伞 tù； é；r sǎn\n" +
                            "\n" +
                            "【别名】雨伞菜、一把伞、铁凉伞。\n" +
                            "\n" +
                            "【来源】菊科植物兔儿伞Syneilesis aconitifotia( Bunge) Maxim.的全草。\n" +
                            "\n" +
                            "【植物形态】多年生草本，高70～120厘米。根茎横走，有多数土黄色细根。茎单一，略带棕褐色。基生叶1片，花后凋落；茎生叶伞状，通常2片，互生，有长柄，叶片圆盾形，掌状全裂，裂片7～9，作2～3回叉状分裂，边缘有不规则锯齿，下面具银白色蛛丝毛；花序下的叶披针形至线形，具短柄或无柄。头状花序密集成复伞房状；苞片l层，5片；筒状花8～11；花冠粉红色，先端5裂；雄蕊5，聚药；柱头2裂，长短不等。瘦果长椭圆形，冠毛灰白色或带红色。花期8～9月。\n" +
                            "\n" +
                            "【生境分布】多生于山坡下。分布于华北、东北及华东等地。\n" +
                            "\n" +
                            "【采收加工】夏秋季采收全草，去除杂草和泥土，晒干。\n" +
                            "\n" +
                            "【药材性状】根茎呈圆柱形，其上簇生有多数细长的根。根圆柱形，略扭曲，表面灰黄色，密被毛茸。质脆，易折断，断面黄白色，中央有棕色小点。茎棕褐色，叶多皱缩或破碎，棕黄色，味辛，微苦。\n" +
                            "\n" +
                            "【性味归经】性微温，味辛。\n" +
                            "\n" +
                            "【功效与作用】活血舒筋，祛风湿。治颈部淋巴结炎，毒蛇咬伤。\n" +
                            "\n" +
                            "【临床应用】用量6～15克，水煎服或泡酒服用。多用于治疗跌打损伤、腰腿疼痛、月经不调、经期腹痛等症。外用鲜草捣敷治蛇咬伤。\n" +
                            "\n" +
                            "【主要成分】从地上部分分得D-d-萜品醇-p-D-O-葡萄吡喃糖苷.3，4-二当归酸酯(D-&alpha；-terpineol-&beta；-D-O-gluucopyranoside-3，4-diangelicate)。\n" +
                            "\n" +
                            "【使用禁忌】暂缺。\n" +
                            "\n");
                    chineseMedicineDao.insert(medicine166);

                    ChineseMedicine medicine167=new ChineseMedicine();
                    medicine167.setName("雄黄");
                    medicine167.setSortType("X");
                    medicine167.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545834118&di=0d9c01ce3545ff82a31e51851198b03a&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.qnong.com.cn%2Fuploadfile%2F2017%2F0329%2F20170329062925911.jpg");
                    medicine167.setData("【中药名】雄黄 xionghuang\n" +
                            "\n" +
                            "【别名】石黄、雄精、黄食石、黄金石、鸡冠石、黄石、天阳石。\n" +
                            "\n" +
                            "【英文名】Realgar。\n" +
                            "\n" +
                            "【药用部位】硫化物类矿物雄黄族雄黄Realgar的矿石。\n" +
                            "\n" +
                            "【分布】主产于湖南、贵州、陕西、湖北、四川、甘肃等地亦产。\n" +
                            "\n" +
                            "【采收加工】全年可采挖。采挖后用竹刀剔取其熟透部分，除去杂质、泥土。\n" +
                            "\n" +
                            "【药材性状】不规则的块状或粉末，大小不一。全体深红色或橙红色，块状者表面常有橙黄色粉末，手触之易染成橙黄色。质脆，易碎，断面粗糙，暗红色，常具树脂样光泽，常可见柱状结晶，半透明至微透明，具金属光泽。微有特异臭气，味淡。燃之易熔融成紫红色液体，并产生黄白色烟，有强烈大蒜臭气。\n" +
                            "\n" +
                            "【性味归经】性温，味辛，有毒。归肝经、大肠经。\n" +
                            "\n" +
                            "【功效与作用】解毒杀虫，燥湿祛痰，截疟。属杀虫止痒药。\n" +
                            "\n" +
                            "【临床应用】用量0.05～0.1克，入丸散用；外用适量，研末撒或调敷患处。用治痈肿疗疮，蛇虫咬伤，虫积腹痛，惊痫，疟疾。\n" +
                            "\n" +
                            "【药理研究】具有抗菌、抗血吸虫的作用。实验表明，雄黄对金黄色葡萄球菌、绿脓杆菌、多种皮肤真菌和人型、牛型结核杆菌有抑制作用。临床用于治疗带状疱疹、慢性粒细胞型白血病及慢性支气管炎有显著疗效。\n" +
                            "\n" +
                            "【化学成分】主含硫化砷。尚含铅、锌、镍等16种元素。\n" +
                            "\n" +
                            "【使用禁忌】 本品辛热有毒，内服宜慎，中病即止，不可多服久服。外用亦不可大面积涂搽或长期持续使用，以免皮肤吸收积蓄中毒。孕妇及阴亏血虚者禁用，其中毒症状主要为上吐下泻。\n" +
                            "\n" +
                            "【配伍药方】①治臁疮日久：雄黄6克，陈艾15克。青布卷作大捻，烧烟熏之。(《纲目》引《卫生杂兴》)\n" +
                            "\n" +
                            "②治蛇缠疮：雄黄为末，醋调涂，仍用酒服。凡为蛇伤及蜂虿、蜈蚣、毒虫、颠犬所伤，皆可用。(《世医得效方》)\n" +
                            "\n" +
                            "③治小儿一切丹毒：雄黄3克，蜗牛五十个，大黄末30克。上共研为一处，用铁锈水调搽患处。(《鲁府禁方》牛黄消毒膏)\n" +
                            "\n" +
                            "④治痔疮并肠红：雄黄4.5克，五倍子30克，白矾6克。共研末，乌梅肉为丸。每服3克，空心白汤下。(《医方易简》)\n" +
                            "\n" +
                            "⑤治癣：雄黄粉，大酢和。先以新布拭之，令癣伤，敷之。(《千金翼方》)");

                    chineseMedicineDao.insert(medicine167);

                    ChineseMedicine medicine168=new ChineseMedicine();
                    medicine168.setName("熊胆");
                    medicine168.setSortType("X");
                    medicine168.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=955daf4836d12f2eda08a6322eabbe07/0eb30f2442a7d93326242aecad4bd11372f001a5.jpg");
                    medicine168.setData("【中药名】熊胆 xiongdan\n" +
                            "\n" +
                            "【别名】狗熊胆、黑瞎子胆、黑熊胆。\n" +
                            "\n" +
                            "【英文名】Fel Ursi。\n" +
                            "\n" +
                            "【药用部位】熊科动物黑熊Selenarctos thibetanus G. Cuvier或棕熊Ursusarctos Linnaeus的胆囊。\n" +
                            "\n" +
                            "【动物形态】黑熊：体长1.5～2米。头部宽，吻部较短，鼻端裸出，耳大而圆，被长毛，颈部两侧毛较长。四肢粗壮，5趾均有爪。前足腕部肉垫和掌部肉垫相接。全身被毛，毛基灰黑色，毛尖乌黑色，绒毛灰黑色。面部毛近于黄色，下颏白色。胸部有一明显的新月形白斑。棕熊：体形大，长约2米，肩高1米。全身为棕黑色，头部色较浅，稍带褐色，腹面毛色比背部浅暗，四肢黑色。\n" +
                            "\n" +
                            "【产地分布】棕熊和黑熊皆有冬眠习性，栖于森林，多独居，喜白天活动，可直立行走。分布于我国东北、华北，南至华南。\n" +
                            "\n" +
                            "【采收加工】割取胆囊，将口扎紧，剥去附着的脂肪后悬挂通风处阴干。\n" +
                            "\n" +
                            "【药材性状】扁长圆形，上部狭细，下部膨大呈囊状。表面灰褐色、黑褐色或棕黄色，微有皱褶，囊皮较薄。囊内含干燥胆汁，习称“胆仁”，呈不规则块状、颗粒状或硬膏状，色泽深浅不一，有金黄色、黑色或黑绿色、黄绿色。气微清香或微腥，入口即化，味极苦而微回甜。\n" +
                            "\n" +
                            "【性味归经】性寒，味苦。归肝经、胆经、心经\n" +
                            "\n" +
                            "【功效与作用】清热解毒、清肝明目。属清热药下属分类的清热解毒药。\n" +
                            "\n" +
                            "【临床应用】用量0.15～0.3克，研末冲服或入丸、散；外用适量，研末调敷或点眼。用治肝热炽盛、惊风、癫痫、目赤肿痛、翳障、疮痈、痔疮、咽喉肿痛。\n" +
                            "\n" +
                            "【药理研究】熊胆仁具解痉作用，熊去氧胆酸能促进体内疲劳物质的分解与排泄，并能增加维生素Bi、维生素B2的吸收。熊去氧胆酸钠对士的宁引起的小鼠中毒有解毒作用，与鹅去氧胆酸钠及胆酸钠合用其作用能增强。尚有健胃、镇痛及促进胆汁分泌等作用。\n" +
                            "\n" +
                            "【化学成分】熊胆含胆汁酸20%～80%，主要为熊去氧胆酸，并有鹅去氧胆酸、胆酸、去氧胆酸等。这些胆酸常与牛磺酸或甘氨酸结合，形成钠盐或钙盐。此外，含有胆甾醇、胆色素及氨基酸等。\n" +
                            "\n" +
                            "【使用禁忌】虚证禁服。恶防己、地黄。\n" +
                            "\n" +
                            "【配伍药方】①治跌打昏迷：熊胆汁1.5~3克。冲酒服。(《广西药用动物》)\n" +
                            "\n" +
                            "②治蛔心痛：熊胆如大豆。和水服。(《外台》)\n" +
                            "\n" +
                            "③治小儿疳疮：熊胆0.15克。汤化调涂于鼻中。(《圣惠方》)\n" +
                            "\n" +
                            "④治神经性胃痛：熊胆研末，每日服3次，每次0.9克，开水送服。(《广西药用动物》)\n" +
                            "\n" +
                            "⑤治痔疮：熊胆汁、片脑(研细)各等分。用水调匀，用棉签蘸取涂痔上。(《广西药用动物》)\n" +
                            "\n" +
                            "⑥治风虫牙痛：熊胆9克，片脑1.2克。上为末，用猪胆汁调搽患处。(《摄生众妙方》)\n" +
                            "\n" +
                            "⑦治胆道炎、胆石症、黄疸：熊胆0.5克，郁金10克，茵陈蒿15克。水煎，日服2次。(《中国动物药》)\n" +
                            "\n" +
                            "⑧治目赤障翳：熊胆0.3克，黄连3克，冰片0.9克。加冷水12克调匀，贮在瓶内备用。常点患处。孕妇慎用。(《广西药用动物》)");

                    chineseMedicineDao.insert(medicine168);

                    ChineseMedicine medicine169=new ChineseMedicine();
                    medicine169.setName("血余炭");
                    medicine169.setSortType("X");
                    medicine169.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545556493&di=4708557f0c77e145eb82445da76831d3&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zhuyun.cn%2FM00%2F68%2FE0%2FwKgJNFPttPWAWGo_AAYo1dSnjz4862.jpg");
                    medicine169.setData("【中药名】血余炭 xueyutan\n" +
                            "\n" +
                            "【别名】乱发、发灰、头发、人发灰。\n" +
                            "\n" +
                            "【英文名】Crinis Carbonisatus\n" +
                            "\n" +
                            "【药用部位】人的头发制成的炭化物。\n" +
                            "\n" +
                            "【产地分布】全国各地均可收集。\n" +
                            "\n" +
                            "【采收加工】取头发，除去杂质，碱水洗去油垢，清水漂净，晒干，炯煅成炭，放凉。\n" +
                            "\n" +
                            "【药材性状】本品呈不规则块状，大小不一。乌黑光亮，表面有多数细孔，如海绵状。质轻，质脆易断，断面蜂窝状。用火烧之有焦发气，味苦。以色黑、发亮、质轻、无杂质者为佳。\n" +
                            "\n" +
                            "【性味归经】性平，味苦。归肝经、胃经。\n" +
                            "\n" +
                            "【功效与作用】收敛止血，化瘀，利尿。属止血药下属分类的收敛止血药。\n" +
                            "\n" +
                            "【临床应用】内服：煎汤，用量5～10克；研末，每次用量1.5～3克。外用：适量，研末掺或油调、熬膏涂敷。用于吐血，咯血，衄血，血淋，尿血，便血，崩漏，外伤出血，小便不利。\n" +
                            "\n" +
                            "【药理】有止血、抗炎、抗病原微生物的作用。\n" +
                            "\n" +
                            "【化学成分】主要成分为一种优质蛋白。\n" +
                            "\n" +
                            "【使用禁忌】无。\n" +
                            "\n" +
                            "【相关药方】①治咯血，兼治吐衄，二便下血：花蕊石(煅存性)9克，三七6克，血余炭(煅存性)3克。共研细，分两次，开水送服。(《衷中参西录》化血丹)\n" +
                            "\n" +
                            "②治小便尿血：头发不拘多少，烧灰存性，研为细末，另用新采侧柏叶捣汁，调糯米粉打糊为丸，如梧桐子大。每服五十丸，空心白滚汤下，或煎四物汤送下。(《松崖医经》秘传发灰丸)\n" +
                            "\n" +
                            "③治小便不利：滑石0.6克，乱发0.6克，白鱼0.6克。上三味，杵为散。饮服1.5克，日三服。(《金匮要略》滑石白鱼散)\n" +
                            "\n" +
                            "④治黄疸：烧乱发，水调服3克，日三服。(《肘后方》)\n" +
                            "\n" +
                            "⑤治恶露不尽，腹胀痛：乱发如鸡子大，灰汁洗净，烧末，酒服。(《外台》救急方)\n" +
                            "\n" +
                            "⑥治手足裂：头发一大握，桐油一碗。于瓦器内熬。候油沸，头发熔烂，出火摊冷，以瓦器收贮，勿令灰入。每用百沸汤泡洗皲裂令轻，拭干敷上。(《卫生易简方》)");

                    chineseMedicineDao.insert(medicine169);

                    ChineseMedicine medicine170=new ChineseMedicine();
                    medicine170.setName("血竭");
                    medicine170.setSortType("X");
                    medicine170.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545557324&di=cd70244571d101b4408f37eabfa24c5a&imgtype=jpg&er=1&src=http%3A%2F%2Fimage04.71.net%2Fimage04%2F43%2F53%2F82%2F24%2Ff4231321-ba22-4a39-9baa-071a3d2d69e7.jpg");
                    medicine170.setData("【中药名】血竭 xuejie\n" +
                            "\n" +
                            "【别名】麒麟竭、血结、血力花、海蜡、木血竭。\n" +
                            "\n" +
                            "【英文名】Draconis Sanguis\n" +
                            "\n" +
                            "【药用部位】棕榈科植物麒麟竭Daemonorops draco Bl.果实渗出的树脂经加工制成。\n" +
                            "\n" +
                            "【植物形态】多年生常绿藤本，长达10～20米。茎被叶鞘并遍生尖刺。羽状复叶在枝梢互生，在下部有时近对生；小叶互生，线状披针形，长约20～30厘米，宽约3厘米，先端锐尖，基部狭，脉3出平行；叶柄及叶轴具锐刺。肉穗花序，开淡黄色的冠状花，单性，雌雄异株；花被6，排成2轮；雄花雄蕊6，花药长锥形；雌花有不育雄蕊6，雌蕊1，瓶状，子房略呈卵状，密被鳞片，花柱短，柱头3深裂。果实核果状，卵状球形，径约2～3厘米，赤褐色，具黄色鳞片，果实内含深赤色的液状树脂，常由鳞片下渗出，干后如血块样。\n" +
                            "\n" +
                            "【产地分布】分布于印度尼西亚、马来西亚、伊朗。我国台湾、广东有栽培。\n" +
                            "\n" +
                            "【采收加工】除去杂质，打成碎粒或研成细末。\n" +
                            "\n" +
                            "【药材性状】不规则块状，大小不一，精制品呈片状。表面红褐色、红色、紫色，具光泽，局部有红色粉尘黏附。质硬，易碎，断面平滑，有玻璃样光泽。研粉为血红色。无臭，味微涩，嚼之有黏牙感。\n" +
                            "\n" +
                            "【性味归经】性平，味甘、咸。归心经、肝经。\n" +
                            "\n" +
                            "【功效与作用】祛瘀定痛、止血生肌。属止血药下属分类的化瘀止血药。\n" +
                            "\n" +
                            "【临床应用】用量1～2克，研末内服或入丸剂；外用研末撒或人膏药用。用治跌打损伤、内伤瘀痛、外伤出血不止。临床用治妇女月经过多、痛经、咳血、便血。\n" +
                            "\n" +
                            "【药理研究】有抗炎、抑菌、抗血栓作用，对环核苷酸有影响，影响纤维蛋白溶解活性等。\n" +
                            "\n" +
                            "【化学成分】含红色树脂90%以上，为血竭树脂鞣醇与苯甲酸及苯甲酰乙酸的化合物。经成分初步分析，含有血竭红素、血竭素、去甲基血竭红素、去甲基血竭素、松脂酸、异松脂酸、松香酸、血竭黄烷A、去氢松香酸、异海松酸、海松酸、血竭二氧杂庚醚等成分。\n" +
                            "\n" +
                            "【使用禁忌】凡无瘀血者慎用。\n" +
                            "\n" +
                            "【配伍药方】①治下疳：血竭、儿茶、乳香(去油)、龙骨(研细末)、没药(去油)各0.9克。研细掺之。(《疡医大全》)\n" +
                            "\n" +
                            "②治腹中血块：血竭、没药、滑石、牡丹皮(同煮过)各30克，为末，醋糊丸、梧桐子大，服之。(《摘玄方》)\n" +
                            "\n" +
                            "③治产后血冲心膈喘满，命在须臾：麒麟竭、没药各4.5克。研细末，童便和酒调服。(《本草汇言》引《广利方》)\n" +
                            "\n" +
                            "④治鼻衄：血竭、蒲黄等分。为末，吹之。(《医林集要》)\n" +
                            "\n" +
                            "⑤治一切金疮及肿毒溃烂，不生肌肉：麒麟竭、净发灰、乳香、没药、轻粉、象牙末各等分。冰片少许，共为末，掺之。(《本草汇言》引《广利方》)\n" +
                            "\n" +
                            "⑥治一切不测，恶疮，年深不愈：血竭30克，铅丹15克(炒紫色)，上二味，捣研为散，先用盐汤洗疮后贴之。(《圣济总录》血蝎散)\n" +
                            "\n" +
                            "⑦治嵌甲疼痛：血竭末调敷之。(《医林集要》)\n" +
                            "\n" +
                            "⑧治痔漏疼痛不可忍：血竭，为细末，用自津唾调涂，颇为妙。(《杨氏家藏方》血竭散)");

                    chineseMedicineDao.insert(medicine170);

                    ChineseMedicine medicine171=new ChineseMedicine();
                    medicine171.setName("西河柳");
                    medicine171.setSortType("X");
                    medicine171.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=ecdb704b87d6277ffd1f3a6a49517455/4e4a20a4462309f7fb8849e2720e0cf3d6cad65f.jpg");
                    medicine171.setData("【中药名】西河柳 xiheliu\n" +
                            "\n" +
                            "【别名】山川柳、柽柳、红荆条、河柳、春柳、赤柽柳。\n" +
                            "\n" +
                            "【英文名】Tamaricis Cacumen\n" +
                            "\n" +
                            "【药用部位】柽柳科植物柽柳Tamarix chinensis Lour.的细嫩枝叶。\n" +
                            "\n" +
                            "【植物形态】落叶灌木或小乔木，高达5米。枝条红紫色或淡棕色，小枝细长下垂。叶互生，细小，鳞片状。总状花序集成疏散的圆锥花序，顶生于当年生枝端；花小，粉红色；苞片三角形；萼片5，卵形；花瓣5，雄蕊5枚，生于花盘裂片间；花盘10或5裂；子房1室，花柱3，棍棒状。蒴果小，常3瓣裂。种子密生毛。花期7～9月，果期8～10月。\n" +
                            "\n" +
                            "【产地分布】生于山野、海滨盐碱沙滩地；庭园有栽培。分布于东北、华北至长江中下游，南至广东、广西、云南。\n" +
                            "\n" +
                            "【采收加工】6～8月花未开放时采收嫩枝叶，阴干。\n" +
                            "\n" +
                            "【药材性状】枝条呈细圆柱形，直径0.5～1.5毫米。表面灰绿色，有多数互生的鳞片状小叶。质脆，易折断。稍粗的枝表面红褐色，叶片常脱落而残留突起的叶基，断面黄白色，中心有髓。气微，味淡。\n" +
                            "\n" +
                            "【性味归经】性平，味甘、辛。归心经、肺经、胃经。\n" +
                            "\n" +
                            "【功效与作用】散风、解表、透疹。属解表药下属分类的辛温解表药。\n" +
                            "\n" +
                            "【临床应用】用量3～6克，水煎服；外用适量，煎汤擦洗。用治麻疹不透、风湿痹痛等。\n" +
                            "\n" +
                            "【药理研究】对呼吸系统具有抑制作用；具有护肝作用；抑制细菌生长，尚具有解热等作用。水煎剂进行药理试验，结果证明，有明显的止咳祛痰作用，对肺炎链球菌、甲型链球菌、白色葡萄球菌及流感杆菌均有抑制作用。\n" +
                            "\n" +
                            "【化学成分】含柽柳酚、柽柳酮、柽柳醇、β-谷甾醇、胡萝卜苷、3’，4’-二甲基槲皮素、硬脂酸、山柰酚-4'-甲醚、槲皮素-3'，4'-二甲醚、水杨苷等多种成分。\n" +
                            "\n" +
                            "【使用禁忌】麻疹已透及体虚多汗者禁服，用量过大能令人心烦不安。\n" +
                            "\n" +
                            "【配伍药方】①治感冒，发热，头痛：西河柳、薄荷各9克，荆芥6克，绿豆衣9克，生姜3克，水煎服。(《青岛中草药手册》)\n" +
                            "\n" +
                            "②治风湿痹痛：西河柳、虎杖根、鸡血藤各30克。水煎服。(《浙江药用植物志》)\n" +
                            "\n" +
                            "③治痞：西河柳煎汤，露一宿，至五更饮数次。痞自消。(《卫生易简方》)\n" +
                            "\n" +
                            "④治酒病：西河柳，不以多少，晒干为细末。每服3克，用酒调下。(《履峻岩本草》)");

                    chineseMedicineDao.insert(medicine171);

                    ChineseMedicine medicine172=new ChineseMedicine();
                    medicine172.setName("小通草");
                    medicine172.setSortType("X");
                    medicine172.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=277ba9fed73f8794c7f2407cb3726591/3c6d55fbb2fb4316d81c303c23a4462309f7d371.jpg");
                    medicine172.setData("【中药名】小通草 xiaotongcao\n" +
                            "\n" +
                            "【别名】小通花、通草棍、旌节花、小通花、山通草、通条树。\n" +
                            "\n" +
                            "【英文名】Stachyuri Medulla、Helwingiae Medulla\n" +
                            "\n" +
                            "【药用部位】旌节花科植物喜马山旌节花Stachyurus himalaicus Hook.f.etThoms.、中国旌节花Stachyurus chinensisFranch.或山茱萸科植物青荚叶Heluringia jaPonica (Thunb.)Dietr.的茎髓。\n" +
                            "\n" +
                            "【植物形态】灌木或小乔木。枝梢呈蔓状，高达4米，树皮棕色，光滑。单叶互生，厚纸质，倒卵形或椭圆形，长7～10厘米，宽3.5～5.5厘米，先端尾状渐尖，基部圆形或略心形，边缘具细而密的锯齿，齿端向前，有增厚的小圆头。穗状花序腋生，长6～10厘米，多下垂，2枚小苞片卵形；花小，黄色；萼片4枚，长卵形；花瓣4枚，分离，椭圆形；雄蕊8枚，略短于花瓣，花药黄色；子房上位，花柱单生，柱头浅裂或呈头状，中轴胎座，4室，胚珠多数。浆果黑褐色，圆球形，先端有尖头，直径5～8毫米。花期3～4月。\n" +
                            "\n" +
                            "【产地分布】生于山坡或丛林中。分布于江西、台湾、湖北、湖南、广东、广西、陕西、甘肃、四川、贵州、云南、西藏等地。\n" +
                            "\n" +
                            "【采收加工】夏、秋季采收，将枝截成30～50厘米长一段，趁新鲜时用细竹签由小端向大端顶出髓部，理直，阴干或晒干。有些地区则用刀纵剖，剥去皮部及木质部，取出髓部，晒干。\n" +
                            "\n" +
                            "【药材性状】圆柱形，长30～50厘米，直径0.5～1厘米。表面白色或淡黄色，无纹理。体轻，质松软，捏之能变形，有弹性，易折断，断面平坦，无空心，显银白色光泽。水浸后有黏滑感。无臭，无味。\n" +
                            "\n" +
                            "【性味归经】性寒，味甘、淡。归肺经、胃经。\n" +
                            "\n" +
                            "【功效与作用】清热、利尿、下乳。属利水渗湿药下分类的利尿通淋药。\n" +
                            "\n" +
                            "【临床应用】用量2.5～4.5克。治疗小便不利、乳汁不下、尿路感染。\n" +
                            "\n" +
                            "【药理研究】本品多糖含量高，低用量能降低9月龄小鼠肝脏及血清中过氧化脂质产物(LPO)的含量。其中4种多糖对肝脏中LPO含量以80毫克/千克用量降低作用明显(P<0.05)，但对血清中LPO含量以160毫克/千克用量作用明显(P<0.05)。对小鼠心肌及脑组织中老化代谢产物LF含量均有明显降低作用(P<0.01)，但对肝脏中LF含量降低作用不明显p>0.05；均能提高小鼠全血老化相关酶SOD的活力，作用明显(P<0.05)。\n" +
                            "\n" +
                            "【化学成分】茎髓中含有灰分、脂肪、蛋白质、粗纤维、戊聚糖及糖醛酸、还含a-半乳糖、葡萄糖与木糖、半乳糖醛酸、多种氨基酸以及钙、钡、镁、铁等18种微量元素等。\n" +
                            "\n" +
                            "【使用禁忌】气虚无湿热及孕妇慎服。\n" +
                            "\n" +
                            "【配伍药方】①治小便黄赤：小通草6克，木通4.5克，车前子9克(布包)。煎服。(《安徽中药志》)\n" +
                            "\n" +
                            "②治热病烦躁，小便不利：小通草6克，栀子、生地黄、淡竹叶、知母、黄芩各9克。煎服。(《安徽中药志》)\n" +
                            "\n" +
                            "③治急性尿道炎：小通草6克，地肤子、车前子(布包)各15克。煎服。(《安徽中药志》)\n" +
                            "\n" +
                            "④治小便不利：小通草15克，车前仁15克，水菖蒲15克，水灯草3克，生石膏3克。煎服。(《湖南药物志》)\n" +
                            "\n" +
                            "⑤治淋病，小便不利：滑石30克，甘草6克，小通草9克。水煎服。(《甘肃中草药手册》)\n" +
                            "\n" +
                            "⑥治产后乳汁不通：小通草6克，王不留行9克，黄蜀葵根12克。煎水当茶饮。如因血虚乳汁不多，加猪蹄1对，炖烂去药渣，吃肉喝汤。(《安徽中草药》)\n" +
                            "\n" +
                            "⑦治乳少：黄芪30克，当归15克，小通草9克。水煎。(《甘肃中草药手册》)\n" +
                            "\n" +
                            "⑧治闭经：小通草、川牛膝各9～15克。水煎服。(《浙江药用植物志》)");

                    chineseMedicineDao.insert(medicine172);

                    ChineseMedicine medicine173=new ChineseMedicine();
                    medicine173.setName("香加皮");
                    medicine173.setSortType("X");
                    medicine173.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike220%2C5%2C5%2C220%2C73/sign=c060537151da81cb5aeb8b9f330fbb73/aec379310a55b31986ca39f043a98226cffc1707.jpg");
                    medicine173.setData("【药名】香加皮 xiangjiapi\n" +
                            "\n" +
                            "【别名】北五加皮、杠柳皮、香五加皮。\n" +
                            "\n" +
                            "【药用部位】萝蘼科植物杠柳Periploca sepium Bge.的根皮。\n" +
                            "\n" +
                            "【植物形态】落叶蔓生灌木，高达2米，全体含乳汁。茎深紫红色或灰棕色，小枝多对生，黄棕色或灰黄色，有细条纹及淡棕褐色圆点状突起的皮孔。叶对生，叶片披针形或长圆状披针形，先端渐尖，基部楔形或圆形，全缘，上面深绿色，有光泽，下面淡绿色，先端近边缘处沿叶缘联成一线。聚伞花序，腋生，花l～5朵，花萼5深裂，裂片卵形，花冠黄绿色，5深裂，裂片内面中部有一小块白色毡毛，外围紫褐色斑，近边缘密被白色细长毛，副花冠5枚，雄蕊5枚，联合成圆锥状，雌蕊包于其中。瞢荚果。\n" +
                            "\n" +
                            "【产地分布】生于山坡灌丛、道旁、沟边向阳处。分布于黑龙江、吉林、辽宁、内蒙古等地。\n" +
                            "\n" +
                            "【采收加工】春、秋季采挖根部，趁湿敲打后，剥下根皮，除去木心，晒干。\n" +
                            "\n" +
                            "【药材性状】多呈单卷筒状，偶见双卷筒状、槽状或不规则碎片状，长3～8(～15)厘米，厚1～5毫米。外表面土黄色、浅棕色至深棕色。多数具有层状可剥落的栓皮，偶见横向突起的皮孔。内表面黄白色或淡黄棕色，光滑，有细纵纹。体轻，质脆，易折断，断面黄白色。有特殊的浓郁香气，味苦。\n" +
                            "\n" +
                            "【性味归经】性温，味辛、苦。归肝经、肾经、心经。\n" +
                            "\n" +
                            "【功效与作用】祛风湿，壮筋骨，利小便。属祛风湿药下分类的祛风湿强筋骨药。\n" +
                            "\n" +
                            "【临床应用】用量5～10克，内服煎汤；浸酒或入丸散。治疗风湿筋骨疼痛、浮肿尿少、心悸气短、风寒湿痹、腰膝酸软。\n" +
                            "\n" +
                            "【药理研究】具有强心、升压作用，不能对抗巴比妥的中枢抑制作用；具有增强呼吸系统功能、抗癌、抗胆碱酯酶，抗放射、杀虫等作用。醇提物对在体、离体蛙心与在体猫心、猫离体心肺装置均使心脏收缩加强，大用量使心脏停止在收缩期；杠柳苷一次或多次给药可增加肾上腺皮质和胆固醇含量，增加肾上腺重量；杠柳酊表现中枢兴奋(蟾蜍、小鼠)；醇提物酸化后的醚提物皮下注射，使猫自主活动减少，呼吸徐缓；氯仿-甲醇洗脱部分对小鼠S-O肉瘤具抑制作用。毒性：过量时强心苷成分可使动物及人中毒，中毒后可出现血压骤升，心收缩力加强，心律不齐乃至心肌纤颤至死，并伴有呕吐。\n" +
                            "\n" +
                            "【化学成分】含甾类糖苷、杠柳毒苷、游离孕烯醇类化合物等。另含北五加皮苷G、北五加皮苷K、北五加皮苷Hl、北五加皮苷H2、杠柳苷A、杠柳苷B、杠柳苷C、杠柳加拿大麻糖苷、4-甲氧基水杨醛等成分。\n" +
                            "\n" +
                            "【使用禁忌】本品有毒，服用不宜过量。不可作五加科植物五加皮的代用品。阴虚火旺者慎服。\n" +
                            "\n" +
                            "【配伍药方】①治风湿性关节炎，关节拘挛疼痛：香加皮、穿山龙、白鲜皮各15克。用白酒泡24小时。每天服10毫升。(《陕甘宁青中草药选》)\n" +
                            "\n" +
                            "②治水肿，小便不利：香加皮、陈皮、生姜皮、茯苓皮、大腹皮各9克。水煎服。(《陕甘宁青中草药选》)\n" +
                            "\n" +
                            "③治阴囊水肿：香加皮9克，仙人头30克。水煎服。(《山东中草药手册》)\n" +
                            "\n" +
                            "④治皮肤、阴部湿痒：香加皮适量。煎汤外洗。(《青岛中草药手册》)");

                    chineseMedicineDao.insert(medicine173);

                    ChineseMedicine medicine174=new ChineseMedicine();
                    medicine174.setName("旋覆花");
                    medicine174.setSortType("X");
                    medicine174.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=2387fa8613d8bc3ed2050e98e3e2cd7b/8694a4c27d1ed21b41f26159a76eddc451da3f56.jpg");
                    medicine174.setData("【中药名】旋覆花\n" +
                            "\n" +
                            "【别名】旋覆华、金钱花、夏菊、六月菊、金盏花。\n" +
                            "\n" +
                            "【英文名】Inulae Flos\n" +
                            "\n" +
                            "【药用部位】菊科植物旋覆花Inula japonica Thunb.的头状花序。\n" +
                            "\n" +
                            "【植物形态】多年生草本，高30～70厘米。根茎短，横走或斜升，具须根。茎单生或簇生，绿色或紫色，有细纵沟，被毛。基部叶花期枯萎；中部叶长圆形或长圆状披针形，叶端尖，叶基渐狭，无柄，全缘或有疏齿，上面有疏毛或近无毛，下面有疏伏毛；上部叶渐狭小，基部有时稍宽。头状花序直径2.5～4厘米，单生或数个排列成疏散伞房花序，总苞半球形，直径1.3～1.5厘米，总苞片5层，线状披针形，最外层披针形而较长，外层基部革质，内层苞片干膜质。舌状花黄色，雌性，长9～14毫米，顶端3齿裂，管状花两性，长4～5毫米，顶端5齿裂，雄蕊5枚，聚药，雌蕊1枚，子房下位，柱头2深裂。瘦果圆柱形，有10条纵沟，被疏短毛，冠毛白色。\n" +
                            "\n" +
                            "【产地分布】野生于山谷、河滩、路边等较潮湿处。分布于东北等地。\n" +
                            "\n" +
                            "【采收加工】夏季或秋季花开放时采摘头状花序，晒干。\n" +
                            "\n" +
                            "【药材性状】花序呈球形或扁球形，直径1～1.5厘米，多松散。总苞半球形，直径1.3～1.5厘米，总苞片5层，最外层苞片常叶质而长，或上部叶质下部革质，内层苞片干膜质，较窄。舌状花1轮.雌性，花冠黄色，长0.9～1.4厘米，宽0.8～1毫米，舌片带状，开展，顶端有3齿。花柱细长，顶端2裂，分枝稍扁，子房圆柱形，中部膨大，长0.8～1毫米，具10条纵棱，棱部被毛。冠毛1轮，22～30条，白色粗糙，长4～5毫米。管状花两性，黄色，密集于中央，花冠长4～5毫米，顶端具5个尖裂片。雄蕊5枚，扁平带状，花药聚合成筒状，基部延伸成长尾，花丝下部贴生于花冠上部游离。气微，味苦、辛、咸。\n" +
                            "\n" +
                            "【性味归经】性微温，味苦、辛、咸。归肺经、胃经。\n" +
                            "\n" +
                            "【功效与作用】降气、消痰、行水、止呕。属化痰止咳平喘药下分类的温化寒痰药。\n" +
                            "\n" +
                            "【临床应用】用量3～9克，煎汤内服，治疗风寒咳嗽、痰饮蓄结、胸膈痞满、喘咳痰多、呕吐噫气、以下痞硬。包煎。\n" +
                            "\n" +
                            "【药理研究】具有镇咳祛痰、平喘、抗炎作用、抗菌、杀虫等作用。\n" +
                            "\n" +
                            "【化学成分】含大花旋覆花素、旋覆花素、槲皮素、胡萝卜苷、去乙酰旋覆花次内酯、旋覆花次内酯、环醚大花旋覆花内酯、氧化大花旋覆花内酯等成分。\n" +
                            "\n" +
                            "【使用禁忌】阴虚劳嗽，风热燥咳者禁服。\n" +
                            "\n" +
                            "【配伍药方】①治咳嗽气逆：旋覆花9克，半夏6克，前胡6克，苏子9克，生姜9克。水煎服。(《青岛中草药手册》)\n" +
                            "\n" +
                            "②治风痰呕逆，饮食不下，头目昏闷：旋覆花、枇杷叶、川芎、细辛、赤茯苓各3克，前胡4.5克。姜、枣水煎服。(《妇人良方》旋覆花汤)\n" +
                            "\n" +
                            "③治痰饮在胸膈，呕不止，心下痞硬者：旋覆花、半夏、茯苓、青皮。水煎服。(《产科发蒙》旋覆半夏汤)\n" +
                            "\n" +
                            "④治伏暑、湿温，胁痛或咳或不咳，无寒，但潮热或寒热为疟状：生香附9克，旋覆花9克(绢包)，苏子霜9克，广陈皮6克、半夏15克，茯苓块9克，薏苡仁15克。水八杯，煮取三杯，分三次温服。(《温病条辩》香附旋覆花汤)\n" +
                            "\n" +
                            "⑤治风湿痰饮上攻，头目眩胀：旋覆花、天麻、甘菊花各等分，为末，每晚服6克，白汤下。(《本草汇言》引《方脉正宗》)\n" +
                            "\n" +
                            "⑥治唾如胶漆稠黏，咽喉不利：旋覆花为末，每服6～9克，水煎，时时呷服。(《卫生易简方》)\n" +
                            "\n" +
                            "⑦治小便不行，因痰饮留闭者：旋覆花一握。捣汁。和生白酒服。(《本草汇言》引《方脉正宗》)");

                    chineseMedicineDao.insert(medicine174);

                    ChineseMedicine medicine175=new ChineseMedicine();
                    medicine175.setName("夏枯草");
                    medicine175.setSortType("X");
                    medicine175.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike72%2C5%2C5%2C72%2C24/sign=354a35a461d9f2d3341c2cbdc885e176/cf1b9d16fdfaaf51bd608bfa8f5494eef11f7af7.jpg");
                    medicine175.setData("【中药名】夏枯草 xiakucao\n" +
                            "\n" +
                            "【别名】夏枯球、棒头草、白花草、铁线夏枯、铁色草、灯笼草、棒槌草。\n" +
                            "\n" +
                            "【英文名】Prunellae Spica。\n" +
                            "\n" +
                            "【药用部位】唇形科植物夏枯草Prunella vulgarisL.的果穗。\n" +
                            "\n" +
                            "【植物形态】多年生草本，有匍匐茎。茎方形，直立，高达40厘米，带红色，被有向上的细毛。叶对生，卵形或卵状矩圆形，长1.5～6厘米，宽0.5～2.5厘米，全缘或有疏齿，两面均有毛，下面有细点，基部叶有长柄。轮状花序密集成顶生假穗状花序；苞片心形，具骤尖头；花萼唇形，上唇3齿，下唇2齿，果熟时由于下唇2齿向上斜伸而闭合；花冠紫、蓝紫或红紫色，上唇盔状，下唇3裂，中间裂片边缘有流苏状小裂片，花冠筒内基部有毛环；雄蕊4枚，2强，花丝顶端2叉，一叉具药；子房上位，4裂。小坚果矩圆状卵形。花期4～6月，果期6～10月。\n" +
                            "\n" +
                            "【产地分布】生于路边、山坡、田野、草丛中。全国大部分地区均有分布。\n" +
                            "\n" +
                            "【采收加工】夏季果穗呈棕红色时采收，除去杂质及果穗柄，晒干。\n" +
                            "\n" +
                            "【药材性状】棒状，略扁，长1.5～8厘米，直径0.8～1.5厘米，淡棕色或棕红色。全穗由数轮至十数轮宿萼与苞片组成，每轮有对生苞片2片，呈扇形，先端尖尾状，脉纹明显，外表面有白毛。每一苞片内有花3朵，花冠多已脱落，宿萼二唇形，内有小坚果4枚，卵圆形，棕色，尖端有白色突起。体轻。气微，味淡。\n" +
                            "\n" +
                            "【性味归经】性寒，味辛、苦。归肝经、胆经。\n" +
                            "\n" +
                            "【功效与作用】清火、明目、散结、消肿。属清热药下属分类的清热泻火药。\n" +
                            "\n" +
                            "【临床应用】用量9～15克；大剂量30克，水煎服。用治目赤肿痛、目珠夜痛、头痛眩晕、瘰疠、瘿瘤、乳痈肿痛、甲状腺肿大、淋巴结结核、乳腺增生、高血压。\n" +
                            "\n" +
                            "【药理研究】具有降压、抗心律失常、抗炎、免疫抑制、降血糖、抗菌、抗病毒、抗细胞毒作用。\n" +
                            "\n" +
                            "【化学成分】薄层层析鉴定证明，不同生长时期的果穗(红棕色、青色、黑色)均含熊果酸、齐墩果酸、咖啡酸、没食子酸、夏枯草皂苷A、夏枯草皂苷B、伞形花内酯、油酸等成分，黑色果穗所含熊果酸的量较低，而根和茎叶检不出熊果酸和齐墩果酸。\n" +
                            "\n" +
                            "【使用禁忌】脾胃气虚者慎服。\n" +
                            "\n" +
                            "【配伍药方】①治羊癫风，高血压病：夏枯草(鲜)90克，冬蜜30克。开水冲服。(《闽东本草》)\n" +
                            "\n" +
                            "②治口眼歪斜：夏枯草9克，胆南星1.5克，防风3克，钩藤3克。水煎，引点水酒卧时服。(《滇南本草》)\n" +
                            "\n" +
                            "③治肝气胀痛：夏枯草30克。煎水服之。(《吉人集验方》)\n" +
                            "\n" +
                            "④治瘰疬，不问已溃未溃：夏枯草9克，大黄0.9克，甘草0.6克。水煎，顿服。(《方家方选》夏枯草汤)\n" +
                            "\n" +
                            "⑤治赤白带下：夏枯草，花开时采，阴干为末。每服6克，米饮下，食前。(《纲目》引自《徐氏家传方》)");

                    chineseMedicineDao.insert(medicine175);

                    ChineseMedicine medicine176=new ChineseMedicine();
                    medicine176.setName("辛夷");
                    medicine176.setSortType("X");
                    medicine176.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=edebceb3efc4b7452099bf44ae957572/9213b07eca806538eb69ec8993dda144ac3482ad.jpg");
                    medicine176.setData("【中药名】辛夷 xinyi\n" +
                            "\n" +
                            "【别名】望春花、木笔花、迎春花、毛辛夷、姜朴花。\n" +
                            "\n" +
                            "【英文名】Magnoliae Flos。\n" +
                            "\n" +
                            "【药用部位】木兰科植物望春花Magnolia biondii Pamp.、玉兰Magnolia denudata Desr.或武当玉兰Magnolia sprengeriPamp.的花蕾。(本文以望春花为例)\n" +
                            "\n" +
                            "【植物形态】落叶乔木，高达6～12米。树皮淡灰色，平滑。芽卵形，密被淡黄色柔毛。叶片长圆状披针形或卵状披针形，长10～18厘米，宽3.5～6.5厘米，先端渐尖，基部圆形或楔形，全缘，两面无毛；叶柄长1～2厘米。花先叶开放，顶生幼枝顶，直径6～8厘米；芳香；花被片9，外轮3片，条形，长约1厘米，内两轮近匙形，长4～5厘米，宽1.3～2.5厘米，内轮较窄小，白色，外面基部带紫红色；雄蕊多数，花丝外面紫色，内面白色。聚合果圆柱形。花期3月，果期9月。\n" +
                            "\n" +
                            "【产地分布】生于海拔400～2000米的山地阔叶林中。分布于甘肃、陕西、湖北、河南等地。\n" +
                            "\n" +
                            "【采收加工】冬末春初花未开放时采收，除去枝梗，阴干。\n" +
                            "\n" +
                            "【药材性状】呈长卵形，似毛笔头，长1.2～2.5厘米。基部常具短梗，梗上有类白色点状皮孔。苞片2～3层，每层2片，两层苞片间有小鳞芽，苞片外表面密被灰白色或灰绿色茸毛，内表面类棕色，无毛。花被片9，类棕色，外轮花被片3，条形，约为内两轮长的1/4，呈萼片状，内两轮花被片6，每轮3，轮状排列。雄蕊和雌蕊多数，螺旋状排列。体轻，质脆。气芳香，味辛凉而稍苦。\n" +
                            "\n" +
                            "【性味归经】性温，味辛。归肺经、胃经。\n" +
                            "\n" +
                            "【功效与作用】散风寒、通鼻窍。属解表药下属分类的辛温解表药。\n" +
                            "\n" +
                            "【临床应用】用量3～9克，水煎服；外用适量。用治风寒头痛、鼻塞、鼻渊、鼻流浊涕。\n" +
                            "\n" +
                            "【药理研究】具有麻醉、抗过敏、抗炎、降压、子宫兴奋、抗血小板凝聚、抗微生物、镇痛、改善微循环作用。\n" +
                            "\n" +
                            "【化学成分】含挥发油。油中主要成分为1,8-桉油精等。另含β-蒎烯、1,8-桉叶素、樟脑、望春花素、香橙烯、望春玉兰脂素A等成分。\n" +
                            "\n" +
                            "【使用禁忌】阴虚火旺者慎服。\n" +
                            "\n" +
                            "【配伍药方】①治鼻渊：辛夷15克，苍耳子7.5克，香白芷30克，薄荷叶1.5克，晒干，为粗末。每服6克，用葱、茶清食后调服。(《济生方》苍耳散)\n" +
                            "\n" +
                            "②治鼻渊、鼻鼽，鼻疮及痘后鼻疮：辛夷研末，入麝少许，葱自蘸入鼻数次，甚良。(《纲目》)\n" +
                            "\n" +
                            "③治鼻尖微赤及鼻中生疮：辛夷碾末，入脑、麝少许。绵裹纳之。(《丹溪心法》)\n" +
                            "\n" +
                            "④治鼻内室塞不通，不得喘息：辛夷、川芎各30克，细辛(去苗)8.5克，木通15克。上为细末，每用少许，绵裹塞鼻中，湿则易之。五七日瘥。(《证治准绳》川芎散)\n" +
                            "\n" +
                            "⑤治鼻塞不知香臭味：皂角、辛夷、石菖蒲等分。为末，绵裹塞鼻中。(《梅氏验方新编》)\n" +
                            "\n" +
                            "⑥治齿牙作痛，或肿或牙龈浮烂：辛夷30克，蛇床子60克，青盐15克。共为末掺之。(《本草汇言》)");

                    chineseMedicineDao.insert(medicine176);

                    ChineseMedicine medicine177=new ChineseMedicine();
                    medicine177.setName("西红花");
                    medicine177.setSortType("X");
                    medicine177.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545558578&di=49974137932521cca8a6d67f528c12d9&imgtype=jpg&er=1&src=http%3A%2F%2Fd.zwzpy.com%2Fupload%2Fmygoods%2F2016%2F08%2F03%2F1470206181162655.jpg");
                    medicine177.setData("【中药名】西红花\n" +
                            "\n" +
                            "【别名】藏红花、番红花。\n" +
                            "\n" +
                            "【英文名】Crocus Sativus。\n" +
                            "\n" +
                            "【药用部位】鸢尾科植物番红花Crocus sativus L的柱头。\n" +
                            "\n" +
                            "【植物形态】多年生草本，无地上茎。地下茎球形，自球茎生叶片9～15片。无柄，叶片线形，长15～20厘米，宽2～24毫米，叶缘反卷，在放大镜下观察表面具细毛。基部由4～5片鞘状鳞片包围。花顶生，直径2.5～4厘米：花被不分化，6片，倒卵圆形，淡紫色，花筒细管状，长4～6厘米；雄蕊3，花药大，黄色，基部箭形；雌蕊3，子房下位，心皮3合生成3室，花柱细长，黄色，顶端3深裂，伸出花被外，下垂，紫红色，柱头顶端略膨大面漏斗状，边缘有不整齐的锯齿，一侧具一裂隙。蒴果，长圆形，具三钝棱，长约3厘米，直径约1.5厘米。种子多数，球形。\n" +
                            "\n" +
                            "【产地分布】原产希腊和中东。现我国浙江、江苏、上海等引种成功。\n" +
                            "\n" +
                            "【采收加工】 10～11月下旬，晴天早晨日刚出时采花，然后摘取柱头，随即晒干，或于55～60℃烘干。\n" +
                            "\n" +
                            "【药材性状】弯曲的细丝状，暗红色，顶端较宽，向下渐细似喇叭状，下端为残留的黄色花柱，顶端边缘显不整齐齿状，体轻，质松软，干燥后质脆易断。将柱头投入水中则膨胀，并散出色素，水染成黄色。气特异，味微苦。\n" +
                            "\n" +
                            "【性味归经】性平，味甘。归心经、肝经。\n" +
                            "\n" +
                            "【功效与作用】活血化瘀、凉血解毒、解郁安神。属活血化瘀药分类下的活血调经药。\n" +
                            "\n" +
                            "【临床应用】用量3～9克；冲泡或浸酒炖。用治经闭、产后瘀阻、温毒发斑、忧郁痞闷、惊悸发狂等。\n" +
                            "\n" +
                            "【药理研究】抗凝血，兴奋子宫，抗肿瘤，改善记忆性障碍，兴奋肠道平滑肌。药理试验证明，本品有兴奋子宫、活血与止血、抗肾炎、抗动脉粥样硬化、抗癌、抗自由基氧化、促进视网膜动脉血流量等作用。\n" +
                            "\n" +
                            "【化学成分】主要含胡萝卜素和苦味素，系其药理活性物质。还含挥发油成分。胡萝卜色素为西红花的主要色素，含量约2%，主要系西红花苷元与各种糖所组成的各种糖苷。苦味素主要为西红花苦素。另含番红花苷1～4、反式和顺式番红花二甲酯、α-番红花酸、α-菠固醇、番红花苦苷等成分。\n" +
                            "\n" +
                            "【使用禁忌】月经过多者及孕妇忌服。\n" +
                            "\n" +
                            "【配伍药方】①治经闭，经痛，产后腰痛：番红花2克，丹参15克，益母草30克，香附12克。水煎服。(《青岛中草药手册》)\n" +
                            "\n" +
                            "②治产后瘀血：丹皮、当归各6克，大黄4.5克，番红花2克，干荷叶6克，研末。调服，每日3次，每次6克，开水送下。(《青岛中草药手册》)\n" +
                            "\n" +
                            "③治月经不调：番红花3克，黑豆150克，红糖90克。水煎服。(《青岛中草药手册》)\n" +
                            "\n" +
                            "④治腰背、胸膈、头项作痛：番红花碾烂，合羊心、牛心或鹿心，用火炙令红色，涂于心上。食之。(《品汇精要》)\n" +
                            "\n" +
                            "⑤治跌打损伤：番红花3克。煎汁，加白酒少许。外洗患处。(《青岛中草药手册》)\n" +
                            "\n" +
                            "⑥治吐血，不论虚实，何经所吐之血：番红花一朵，无灰酒一盏。将花入酒，炖出汁服之。(《纲目拾遗》引王士瑶方)\n" +
                            "\n" +
                            "⑦治各种痞结：藏红花每服一朵，冲汤下。忌食油荤、盐，宜食淡粥。(《纲目拾遗》)\n" +
                            "\n" +
                            "⑧治中耳炎：鲜番红花汁、鲜薄荷汁适量，加入白矾末少许，搅匀。滴耳中。(《青岛中草药手册》)\n" +
                            "\n");

                    chineseMedicineDao.insert(medicine177);

                    ChineseMedicine medicine178=new ChineseMedicine();
                    medicine178.setName("豨莶草");
                    medicine178.setSortType("X");
                    medicine178.setMedicineImageUrl("http://img0.imgtn.bdimg.com/it/u=3056003151,2577303985&fm=26&gp=0.jpg");
                    medicine178.setData("【中药名】豨莶草 xixiancao\n" +
                            "\n" +
                            "【别名】火莶、猪膏草、虎膏、狗膏、大叶草、虾钳草、铜锤草。\n" +
                            "\n" +
                            "【英文名】Siegesbeckiae Herba。\n" +
                            "\n" +
                            "【药用部位】菊科植物豨莶Siegesbeckia orientalis L.腺梗豨莶Siegesbeckia pubescens Makino或毛梗豨莶Siegesbeckiaglabrescens Makino的干燥地上部分。\n" +
                            "\n" +
                            "【植物形态】豨莶：一年生草本，高30～100厘米，被白色柔毛。茎直立，方形，常带紫色，枝上部密生短柔毛。叶对生，茎中部叶三角状卵形或卵状披针形，长4～10厘米，宽1.8～6.5厘米，两面被毛，下面有腺点，边缘有不规则的锯齿，顶端一枝梗最短，被紫褐色头状有柄腺毛，舌状花黄色，雌性，稍短，长达2.5毫米，管状花两性。瘦果稍膨胀而常弯曲，长3～3.5毫米，无冠毛。花期5～7月，果期7～9月。腺梗豨莶：一年生草本，株高40～100厘米。茎直立，上部二歧分枝，被开展的灰白色长柔毛和糙毛。基部叶卵状披针形，花期枯萎，中部叶卵形或菱状卵形，长3～12厘米，宽3～8厘米，先端渐尖，基部宽楔形，下延成具翅而长1～3厘米的柄，边缘有不规则的粗齿，上面深绿色，下面淡绿色，基出3脉，侧脉和网脉明显，两面被平伏的短柔毛，沿脉有长柔毛。头状花序，直径1.5~1.8厘米，花序梗长3～5毫米，密被紫褐色头状具柄腺毛和长柔毛。总苞宽钟状，总苞片密被紫褐色头状具柄腺毛，外层5片，线状匙形，长7～12毫米，内层卵状长圆形，长3.5毫米。舌状花黄色，舌片先端3齿裂，管状花黄色。瘦果，倒卵形，长2.5～3.5毫米。花、果期8～9月。毛梗豨莶：一年生草本，较瘦弱，高约50厘米。茎直立，方形，带紫色，茎上部分枝非二歧枝，疏生平伏短柔毛。叶对生，细圆形，有时三角状卵形，两面被毛，边缘有规则的锯齿，具柄。头状花序多数。排成圆锥状，花序梗被稀平伏短柔毛，总苞片条状匙形，舌状花黄色，雌性，长约2毫米，管状花两性。瘦果稍膨胀而弯曲，长约2毫米。花期8～10月，果期10～11月。\n" +
                            "\n" +
                            "【产地分布】豨莶：生于山坡、路边、林缘。主产于秦岭和长江流域以南。腺梗豨莶：生于山坡或路旁草地。主产于东北、华北、华东及湖南、湖北、广东、广西、贵州、云南、四川等地。毛梗豨莶：生于山坡，路边。主产于长江以南及西南各省区。\n" +
                            "\n" +
                            "【采收加工】夏、秋二季花开前和花期均可采割，除去杂质，晒干。\n" +
                            "\n" +
                            "【药材性状】茎略呈方柱形，多分枝，长30～110厘米，直径0.3～1厘米，表面灰绿色、黄棕色或紫棕色，有纵沟和细纵纹，被灰色柔毛，节明显，略膨大；质脆，易折断，断面黄白色或带绿色，髓部宽广，类白色，中空。叶对生，叶片多皱缩、卷曲，展平后呈卵圆形，灰绿色，边缘有钝锯齿，两面皆有白色柔毛，主脉3出。有的可见黄色头状花序，总苞片匙形。气微，味微苦。\n" +
                            "\n" +
                            "【性味归经】性寒，味辛、苦。归肝经、肾经。\n" +
                            "\n" +
                            "【功效与作用】祛风湿，利关节，解毒。属祛风湿药下属分类的祛风湿强筋骨药。\n" +
                            "\n" +
                            "【临床应用】内服：煎汤，用量9～12克，或入丸、散，外用适量。用治风湿痹痛，筋骨无力，腰膝酸软，四肢麻痹，半身不遂，风疹湿疮。\n" +
                            "\n" +
                            "【药理研究】抗炎作用；降压及舒张血管作用；抗病原微生物；豨莶草煎剂对细胞免疫和体液免疫均有明显的抑制作用；毛梗豨莶草醇提取物1.7克(生药) /千克剂量时，有明显的抗早孕作用；豨莶草溶液0.6克/毫升对小鼠肠系膜微循环障碍的血流恢复有显著促进作用，其作用与丹参注射液0.3克/毫升相当。\n" +
                            "\n" +
                            "【化学成分】豨莶：茎中含β-异丁酰氧基木香烯内酯，腺梗豨莶：全草含腺梗豨莶甙，腺梗豨莶醇，腺梗豨莶酸，毛梗豨莶：全草含豨莶精醇，稀中含奇任醇，16-乙酰基奇任醇，异亚丙基奇任醇。\n" +
                            "\n" +
                            "【使用禁忌】阴血不足者忌服，多服则令人吐。\n" +
                            "\n" +
                            "【配伍药方】①治高血压：豨莶草、臭梧桐、夏枯草各9克。水煎服，每日1次。(《青岛中草药手册》)\n" +
                            "\n" +
                            "②治慢性肾炎：豨莶草30克，地耳草15克。水煎冲红糖服。(《浙江药用植物志》)\n" +
                            "\n" +
                            "③治神经衰弱：豨莶草、丹参各15克，煎服。(《安徽中草药》)\n" +
                            "\n" +
                            "④治肠风下血：豨莶草，酒蒸为末，炼蜜丸。每服4克，白汤下。(《本草汇言》引《方脉正宗》)\n" +
                            "\n" +
                            "⑤治风热上攻，牙齿疼痛：豨莶草，霜后收之，晒干为粗末。每用9克，以滚汤泡，任意漱之，醋煎尤妙。(《古今医统》)\n" +
                            "\n" +
                            "⑥治急性黄疸型传染性肝炎：豨莶草30克，山栀子9克，车前草、广金钱草各15克。加水1000毫升，煎至300毫升，分2次服，每日1剂。(《全国中草药汇编》)");

                    chineseMedicineDao.insert(medicine178);

                    ChineseMedicine medicine179=new ChineseMedicine();
                    medicine179.setName("菥蓂");
                    medicine179.setSortType("X");
                    medicine179.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=26ee88063bc79f3d9becec62dbc8a674/7a899e510fb30f24ad7421fac895d143ac4b0359.jpg");
                    medicine179.setData("【中药名】菥蓂 ximing\n" +
                            "\n" +
                            "【别名】大荠、花叶荠、老鼓草、苏败酱、折嘎哇(藏名)。\n" +
                            "\n" +
                            "【英文名】Thlaspi Herba。\n" +
                            "\n" +
                            "【药用部位】十字花科植物菥蓂Thlaspi arvetse L.的干燥地上部分(带种子)。\n" +
                            "\n" +
                            "【植物形态】一年生草本，高20～40厘米，全株光滑无毛。茎直立，有分枝，表面粉绿色。单叶互生，基生叶有短柄，茎生叶无柄，基部抱茎，叶片椭圆形、倒卵形或披针形，先端尖，基部箭形，边缘具稀疏浅齿或粗齿，两面粉绿色。总状花序腋生及顶生，花萼4，卵形，绿色，边缘白色膜质，花瓣4，白色，雄蕊6，4强，花药卵形，雌蕊1，子房卵圆形而扁，先端微凹，绿色，2室。短角果扁平，卵圆形，具宽翅，先端深裂，熟时淡黄色，种子小，卵圆形而扁。花期4～7月。果期5～8月。\n" +
                            "\n" +
                            "【产地分布】生于山坡、草地、路旁或田畔。我国大部分地区均有分布。\n" +
                            "\n" +
                            "【采收加工】夏季果实成熟时采割，除去杂质，干燥。\n" +
                            "\n" +
                            "【药材性状】茎呈圆柱形，长20～40厘米，直径0.2～0.5厘米；表面黄绿色或灰黄色，有细纵棱线；质脆，易折断，断面髓部白色。叶互生，披针形，基部叶多为倒披针形，多脱落。总状果序生于茎枝顶端和叶腋，果实卵圆形而扁平，直径0.5～1.3厘米；表面灰黄色或灰绿色，中心略隆起，边缘有翅，宽约0.2厘米，两面中间各有1条纵棱线，先端凹陷，基部有细果梗，长约1厘米；果实内分2室，中间有纵隔膜，每室种子5～7粒。种子扁卵圆形。气微，味淡。\n" +
                            "\n" +
                            "【性味归经】性微寒，味辛。归肝经、胃经、大肠经。\n" +
                            "\n" +
                            "【功效与作用】清肝明目，和中利湿，解毒消肿。属利水渗湿药下属分类的利水消肿药。\n" +
                            "\n" +
                            "【临床应用】用量9～15克，鲜品加倍。治疗目赤肿痛，脘腹胀痛，胁痛，肠痈，水肿，带下，疮疖痈肿。\n" +
                            "\n" +
                            "【药理研究】具有杀菌作用；可用于治疗痛风以增加尿酸的排出。\n" +
                            "\n" +
                            "【化学成分】种子含黑芥甙、芥子甙，黑芥子甙经酶水解后生成异硫酸丙烯酯、卵磷脂、肌球朊酶。并含脂肪油，叶、根、果中均含维生素C。另含挥发油（芥子油）、蛋白质、蔗糖、卵磷脂和芥子酶等成分。\n" +
                            "\n" +
                            "【使用禁忌】尚不明确。\n" +
                            "\n" +
                            "【配伍药方】①治肾炎：菥萁鲜全草30～60克，水煎服。(《福建中草药》)\n" +
                            "\n" +
                            "②治产后子宫内膜炎：菥蓂干全草15克，水煎，调红糖服。(《福建中草药》)\n" +
                            "\n" +
                            "③治产后瘀血痛：菥蓂15克，水煎，冲失笑散(五灵脂、蒲黄)10克服。(《福建药物志》)\n" +
                            "\n");

                    chineseMedicineDao.insert(medicine179);

                    ChineseMedicine medicine180=new ChineseMedicine();

                    medicine180.setName("香薷");
                    medicine180.setSortType("X");
                    medicine180.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C0%2C1000%2C664%3Bc0%3Dbaike116%2C5%2C5%2C116%2C38/sign=4df6976ba6c27d1eb169618426e5815e/d058ccbf6c81800a8011acc1b33533fa838b470c.jpg");
                    medicine180.setData("【中药名】香薷 xiangru\n" +
                            "\n" +
                            "【别名】香茹、青香薷、华荠芋、小叶香薷、香茸、香菜、石香薷、石艾。\n" +
                            "\n" +
                            "【英文名】Moslae Herba。\n" +
                            "\n" +
                            "【药用部位】唇形科植物石香薷Mosla chinensis Maxim.或江香薷Mosla chinensis.Jiangxi angru.的地上部分。(本文以石香薷为例)\n" +
                            "\n" +
                            "【植物形态】直立草本，高9～35厘米。全株香气甚浓。茎细方柱形，多分枝，被白色疏柔毛。叶对生；柄短，密被柔毛；叶线状长圆形至披针形，先端锐尖或急尖，基部楔形，边缘具疏锯齿，上面深绿色，密被白色长柔毛，下面淡绿色，密布腺点。轮伞花序密集成头状总状花序，苞片覆瓦状排列，圆倒卵形，先端短尾尖，全缘，两面被疏柔毛，下面具凹陷腺点，边缘具睫毛；萼钟状，5裂，被长柔毛及腺点；花冠唇形，淡紫红色，长约6毫米，上唇2裂，下唇3裂；雄蕊4枚，上部2个较短。小坚果4个，球形，灰褐色，具深雕纹，无毛。花期6～9月，果期7～11月。\n" +
                            "\n" +
                            "【产地分布】多生于山野，路旁。有栽培。分布于陕西、甘肃、江苏等地。\n" +
                            "\n" +
                            "【采收加工】夏、秋季茎叶茂盛、果实成熟时采割，除去杂质，晒干。\n" +
                            "\n" +
                            "【药材性状】长30～50厘米，基部紫红色，上部黄绿色或淡黄色，全体密被白色茸毛，茎方柱形，直径1～2毫米，节明显，节间长4～7厘米；质脆，易折断。叶对生，多皱缩或脱落，叶片展平后呈长卵形或披针形，暗绿色或黄绿色，边缘有疏锯齿。花序顶生及腋生；苞片宽卵形，脱落或残存；花萼宿存，钟状，淡紫红色或灰绿色，先端5裂，密被茸毛。小坚果4，近圆球形，具网纹。气清香而浓，味凉而微辛。\n" +
                            "\n" +
                            "【性味归经】性微温，味辛。归肺经、胃经。\n" +
                            "\n" +
                            "【功效与作用】发汗解表、和中利湿。属解表药下属分类的辛温解表药。\n" +
                            "\n" +
                            "【临床应用】用量3～9克，水煎服，或研末。用治暑湿感冒、恶寒发热、头痛无汗、腹痛吐泻、小便不利。具有抗病原微生物、增强免疫、解痉、利尿、镇痛、镇静、止咳祛痰等作用。\n" +
                            "\n" +
                            "【药理研究】具有解热镇痛作用；对离体肠有抑制作用；可增强机体免疫；具有较强的广谱抗菌作用，具有一定的抗病毒作用；在体外对血管紧张素受体β-羟基-β-甲基戊二酸辅酶A还原酶均有明显抑制作用，可能具降压和降低胆固醇作用。尚有利尿、镇咳和祛痰作用。\n" +
                            "\n" +
                            "【化学成分】含有黄酮类、挥发油、萜类、脂肪酸等，另含香荆芥酚、对聚伞花素、对异丙基苯甲醇、β-蒎烯、4-蒈烯、α-松油烯、百里香酚、葎草烯、β-金合欢烯和柠檬烯。\n" +
                            "\n" +
                            "【使用禁忌】内服宜凉饮。表虚自汗、阴虚有热者禁用。\n" +
                            "\n" +
                            "【配伍药方】①治中暑烦渴：香薷60克。上一味，捣罗为散，每服6克，水一盏，煎服七分，不去滓温服，不拘时候。(《圣济总录》香薷散)\n" +
                            "\n" +
                            "②治霍乱吐利，四肢烦痛，冷汗出，多渴：香薷60克，蓼子30克。上二味粗捣筛。每服6克，水一盏，煎七分，去渣温服，日三。(《圣济总录》)\n" +
                            "\n" +
                            "③治小儿白秃，发不生，汗出：浓煮陈香薷汁少许，脂和胡粉敷上。(《子母秘录》)\n" +
                            "\n" +
                            "④治口臭：香薷一把，以水一斗煮，取三升，稍稍含之。(《千金要方》)\n" +
                            "\n" +
                            "⑤治多发性疖肿，痱子：鲜香薷适量，捣烂外敷。(江西《草药手册》)\n" +
                            "\n" +
                            "⑥治皮肤瘙痒，阴部湿疹：鲜香薷全草适量。水煎外洗。(《浙江药用植物志》)\n" +
                            "\n");

                    chineseMedicineDao.insert(medicine180);

                    ChineseMedicine medicine181=new ChineseMedicine();
                    medicine181.setName("西番莲");
                    medicine181.setSortType("X");
                    medicine181.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=5113127b602762d09433acedc185639f/ae51f3deb48f8c546c11687739292df5e1fe7fac.jpg");
                    medicine181.setData("【中药名】西番莲 xifanlian\n" +
                            "\n" +
                            "【别名】王蕊花、转心莲、龙珠菜。\n" +
                            "\n" +
                            "【英文名】Passillora Herb\n" +
                            "\n" +
                            "【药用部位】西番莲科植物西番莲Passillora caerulea L.的干燥全草。\n" +
                            "\n" +
                            "【植物形态】多年生草质藤本。茎圆柱形，略具棱槽，有多数分枝，老枝常带紫红色；卷须腋生，长13～17厘米。叶互生；叶柄长2～3厘米；中部散生2～6个小腺体；托叶较大，肾形，抱茎；叶掌状5深裂，长5～7厘米，宽6～8厘米，裂片长椭圆形，中央的较大，两侧的略小，全缘。单花腋生，花大，直径6～10厘米，淡绿色；苞片3，宽卵形，萼片5，背面近先端有一角状物；花瓣5，长圆状披针形，与萼片近等长；副花冠裂片3轮，丝状，白色，上下两端带蓝色或紫红色；内花冠流苏状，紫红色，其下具花盘；雄蕊5，花丝基部与子房柄合生；子房卵圆形，花柱3，紫红色。浆果卵形或近球形，熟时黄色。种子多数，有红色假种皮。花期5～7月。\n" +
                            "\n" +
                            "【产地分布】江西、广东、广西、四川、贵州、云南等地有引种栽培。\n" +
                            "\n" +
                            "【采收加工】夏、秋季地上部生长茂盛时采全草，晒干。\n" +
                            "\n" +
                            "【性味归经】性温，味苦。无归经。\n" +
                            "\n" +
                            "【功效与作用】祛风，除湿，活血，止痛。属活血化瘀药下属分类的活血止痛药。\n" +
                            "\n" +
                            "【临床应用】内服：煎汤，15～20克。外用：鲜品适量，捣敷。主治感冒头痛，鼻塞流涕，风湿关节痛，疝痛，痛经，神经痛，失眠，下痢，骨折。\n" +
                            "\n" +
                            "【药理研究】有促进放松和睡眠的作用，对于因紧张引起的失眠、头痛、肌肉疼痛有很好疗效，临床上用于治疗睡眠失调和焦虑不安；对防治高血压、高胆固醇血症、胃病有很好的疗效。还可以用于治疗瘫痪和心脏病。\n" +
                            "\n" +
                            "【化学成分】本品主要含白杨素、苯并二氮杂类化合物、新西兰鸡蛋果氰苷B-4-硫酸酯、牡荆素、导荭草素、芹菜素-8-C-双葡萄糖苷、焦性儿茶酚等成分。\n" +
                            "\n" +
                            "【使用禁忌】尚不明确。\n" +
                            "\n" +
                            "【配伍药方】①治外感风热咳嗽：西番莲茎叶、坝子花、枇杷叶各9克。煎水服。(《西昌中草药》)\n" +
                            "\n" +
                            "②治狂症(精神失常)：西番莲根15～25克，炖猪心(内加朱砂1克)1个吃。(《贵州草药》)\n" +
                            "\n" +
                            "③治失眠：西番莲果实15克，仙鹤草30克。煨水服。(《贵州草药》)\n" +
                            "\n" +
                            "④治经来腹痛：西番莲果1～2个，白薇根10克，泡酒服。(《贵州草药》)\n" +
                            "\n" +
                            "⑤治痢疾腹痛：西番莲根、拳参各10克。煨水服。(《贵州草药》)\n" +
                            "\n" +
                            "⑥治骨折：西番莲根15克，枇杷叶30克，水冬瓜根皮、续断根各15克。捣绒调酒，包敷患处。(《贵州草药》)");

                    chineseMedicineDao.insert(medicine181);

                    ChineseMedicine medicine182=new ChineseMedicine();
                    medicine182.setName("仙鹤草");
                    medicine182.setSortType("X");
                    medicine182.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=ff41fb68b5fd5266b3263446ca71fc4e/0df3d7ca7bcb0a468c957cba6863f6246a60affa.jpg");
                    medicine182.setData("【中药名】仙鹤草 xianhecao\n" +
                            "\n" +
                            "【别名】龙芽草、脱力草、狼牙草、群兰败毒草、老鹳嘴。\n" +
                            "\n" +
                            "【英文名】Agrimoniae Herba\n" +
                            "\n" +
                            "【药用部位】蔷薇科植物龙芽草Agrimonia pilosa Ledeb.的地上部分。\n" +
                            "\n" +
                            "【植物形态】多年生草本，高50～100厘米。全体具白色长毛。根状茎褐色，横走，短圆柱状，有时分歧，着生细长的须根，秋季地上部分枯萎时，自短小的当年根状茎先端生一冬芽，白色，圆锥形，向上弯曲。茎直立，绿色，老时带紫色，上部分枝。叶互生，单数羽状复叶，小叶7～21片，椭圆状卵形或倒卵形，先端尖，基部楔形，两面均绿色，有长柔毛，下面密布细小的金黄色腺点。夏季茎顶抽细长的总状花序，上生许多黄色小花。苞片2，萼筒倒圆锥形；花瓣5，长方倒卵形，先端微凹；雄蕊10枚或更多；心皮2个，贴合，上部露出2枚花柱。瘦果小，藏于外面有槽和顶端有一圈钩刺的萼筒内。花果期5～12月。\n" +
                            "\n" +
                            "【产地分布】生于山野、草坡、路旁。全国大部分地区均有分布。\n" +
                            "\n" +
                            "【采收加工】夏、秋季茎叶茂盛时采割，除去杂质，干燥。\n" +
                            "\n" +
                            "【药材性状】全体长50～100厘米，被白色柔毛，茎下部圆柱形，直径4～6毫米，红棕色，上部方柱形，四面略凹陷，绿褐色，有纵沟和棱线，有节；体轻，质硬，易折断，断面中空。单数羽状复叶互生，暗绿色，皱缩卷曲；质脆，易碎；叶片有大小2种，相间生于叶轴上，顶端小叶较大，完整小叶片展平后呈卵形或长椭圆形，先端尖，基部楔形，边缘有锯齿；托叶2，抱茎，斜卵形。总状花序细长，花萼下部呈筒状，萼筒上部有钩刺，先端5裂，花瓣黄色。气微，味微苦。\n" +
                            "\n" +
                            "【性味归经】性平，味苦、涩。归心经、肝经。\n" +
                            "\n" +
                            "【功效与作用】收敛止血、截疟、止痢、解毒。属止血药下属分类的收敛止血药。\n" +
                            "\n" +
                            "【临床应用】用量6～12克。水煎服，外用适量。用治咯血、吐血、崩漏下血、疟疾、血痢、脱力劳伤、痈肿疮毒、阴痒带下。\n" +
                            "\n" +
                            "【药理研究】具有抗凝血和抗血栓形成作用；有抗肿瘤作用及对阴道滴虫有良好杀灭作用。仙鹤草素有止血、调整心率、增加细胞抵抗力、降低血糖和消炎等作用；冬芽含酚性物质鹤芽酚，冬芽及根有较强的驱绦虫作用，对金黄色葡萄球菌、大肠杆菌、绿脓杆菌、福氏痢疾杆菌、伤寒杆菌均有抑制作用。\n" +
                            "\n" +
                            "【化学成分】含仙鹤草素、仙鹤内脂、鞣质、甾醇、有机酸、酚性成分、皂苷、黄酮苷、山柰酚-7-鼠李糖苷、芸香苷、木犀草素-7-葡萄糖苷、没食子酸等成分。\n" +
                            "\n" +
                            "【使用禁忌】表证发热者慎服。\n" +
                            "\n" +
                            "【配伍药方】①治尿血：仙鹤草、大蓟、木通各9克，茅根30克。水煎服。(《宁夏中草药》)\n" +
                            "\n" +
                            "②治便血：仙鹤草(焙干、入蚌粉炒)、槐花、百药煎，为末。每服9克。米泔调，空心服。(《卫生易简方》)\n" +
                            "\n" +
                            "③治赤白痢及咯血、吐血：仙鹤草9～18克。水煎服。(《岭南采药录》)\n" +
                            "\n" +
                            "④治中暑：仙鹤草全草30克。水煎服。(《湖南药物志》)\n" +
                            "\n" +
                            "⑤治乳痈，初起者消，成脓者溃，且能令脓出不多：仙鹤草30克，白酒半壶，煎至半碗，饱后服。(《百草镜》)");

                     chineseMedicineDao.insert(medicine182);

                     ChineseMedicine medicine183=new ChineseMedicine();
                     medicine183.setName("小驳骨");
                     medicine183.setSortType("X");
                     medicine183.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545809411&di=9419cbc937734d321af4dc502fc1208f&imgtype=jpg&er=1&src=http%3A%2F%2Ffile.cnkang.com%2Fcnkfile1%2FM00%2F16%2F8F%2Fo4YBAFmlIcCALJGBAAC5Qeg8i_s09.jpeg");
                     medicine183.setData("【中药名】小驳骨 xiaobogu\n" +
                             "\n" +
                             "【别名】驳骨丹、接骨草、小还魂、小叶金不换。\n" +
                             "\n" +
                             "【英文名】Gendarussae Herba\n" +
                             "\n" +
                             "【药用部位】爵床科植物小驳骨Gen-darussa vulgaris Nees的干燥地上部分。\n" +
                             "\n" +
                             "【植物形态】亚灌木，直立无毛，高约1米。茎圆柱形，节膨大，分枝多，嫩枝常深紫色。叶对生；纸质；叶柄长不及1厘米；叶片狭披针形至披针状线形，长5～10厘米，宽5～15毫米，先端渐尖，基部渐狭，全缘；侧脉每边6～8条，呈深紫色。穗状花序顶生，上部密生，下部间断；苞片对生，每苞片中有花2至数朵；萼近相等的5裂，裂片三角状披针形，长约4毫米；花冠白色或粉红色，长约1.3毫米，花冠管圆筒状，喉部稍扩大，冠檐二唇形，上唇长圆状卵形，下唇浅3裂；雄蕊2，花丝稍扁，花药具阔面斜的药隔，药室2，一个基部有尾状附属物；子房每室有2个胚珠，花柱线形。蒴果棒状，长1.2厘米，无毛。花期春季。\n" +
                             "\n" +
                             "【产地分布】生于村旁或路边的灌丛中，亦有栽培。分布于台湾、广东、海南、广西、云南等地。\n" +
                             "\n" +
                             "【采收加工】全年均可采收，除去杂质，晒干。\n" +
                             "\n" +
                             "【药材性状】本品茎呈圆柱形，有分枝，长40～90厘米，直径0.2～3厘米。茎表面黄绿色、淡绿褐色或褐绿色，有稀疏的黄色小皮孔；小枝微具四棱线，节膨大。质脆，易折断，断面黄白色。叶对生，卷缩破碎，展平后呈狭披针形或条状披针形，长4～14厘米，宽1～2厘米；先端渐尖，基部楔形，全缘，叶脉略带紫色。有的可见穗状花序，顶生或生于上部叶腋，苞片窄细，花冠二唇形。气微，味微辛、酸。\n" +
                             "\n" +
                             "【性味归经】性温，味辛。归肝经、肾经。\n" +
                             "\n" +
                             "【功效与作用】祛瘀止痛，续筋接骨。属祛风湿药下属分类的祛风湿强筋骨药。\n" +
                             "\n" +
                             "【临床应用】内服：煎汤，15~30克；或研末；或泡酒。外用：适量，鲜品捣敷；或研末调敷；或煎汤熏洗。用于跌打损伤，筋伤骨折，风湿骨痛，血瘀经闭，产后腹痛。\n" +
                             "\n" +
                             "【药理研究】小剂量使鼠体温升高，剂量过大则使体温降低，可致剧烈泻下，并可导致死亡。\n" +
                             "\n" +
                             "【化学成分】本品含β-谷固醇，爵床脂素和挥发油等成分。\n" +
                             "\n" +
                             "【使用禁忌】孕妇慎用。\n" +
                             "\n" +
                             "【配伍药方】①治风湿痛：小驳骨、大风艾、过山香、水菖蒲、红鹰不扑各适量。用水煲，熏洗患处。（《广西民间常用草药》）\n" +
                             "\n" +
                             "②治四肢神经痛：小驳骨、枫寄生、埔银、土烟头、钮子茄及一条根各20克。水煎服。（《台湾植物药材志》）\n" +
                             "\n" +
                             "③治经痛：小驳骨40克。水煎服。（《台湾植物药材志》）\n" +
                             "\n" +
                             "④治跌打伤：小驳骨茎及根40～75克，水煎服；或全草捣烂，酒炒后，趁热推跌打骨折处。（《台湾植物药材志》）\n" +
                             "\n" +
                             "⑤治骨折：小驳骨全株250克，枇杷叶500克，九节茶叶60克，小雄鸡一只。共捣烂，复位后，敷患处，1.5小时取去。（《广东省惠阳地区中草药》）");

                     chineseMedicineDao.insert(medicine183);

                     ChineseMedicine medicine184=new ChineseMedicine();
                     medicine184.setName("小蓟");
                     medicine184.setSortType("X");
                     medicine184.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=d0cbe88e62d9f2d3341c2cbdc885e176/91ef76c6a7efce1b36291fa5af51f3deb48f655b.jpg");
                     medicine184.setData("【中药名】小蓟 xiaoji\n" +
                             "\n" +
                             "【别名】刺儿菜、青青菜、野红花、刺蓟菜、刺角菜、刺儿草。\n" +
                             "\n" +
                             "【英文名】Cirsii Herba。\n" +
                             "\n" +
                             "【药用部位】菊科植物刺儿菜Cirsium setosum (Willd.) MB.的地上部分。\n" +
                             "\n" +
                             "【植物形态】多年生草本，高25～50厘米。茎基部生长多数须根。根状茎细长，先直伸后匍匐，白色，肉质。茎直立，微紫色，有纵槽，被白色柔毛，上部稍有分枝。叶互生，无柄，叶片长椭圆形或椭圆状披针形，长7～10厘米，宽1.5～2.5厘米，先端钝，有刺尖，基部狭窄或圆钝；全缘或微齿裂，边缘有金黄色小刺，两面均被有绵毛，开花后下部叶凋落。头状花序顶生，直立，花单性，雌雄异株，管状花，紫红色，雄花序较小，有不育雄蕊；雌花序较大，有不育雌蕊。瘦果椭圆形或长卵形，冠毛羽毛状。花期5～6月，果期5～7月。\n" +
                             "\n" +
                             "【产地分布】生于荒地、田间和路旁。全国各地均有分布。\n" +
                             "\n" +
                             "【采收加工】夏秋季花开时采割，除去杂质，鲜用或晒干。\n" +
                             "\n" +
                             "【药材性状】茎呈圆柱形，有的上部分枝，长5～30厘米，直径0.2～0.5厘米；表面灰绿色或微带紫色，有纵棱和白色柔毛；质脆，易折断，断面中空。叶互生，无柄或有短柄；叶片破碎或皱缩，完整者展开后呈长椭圆形或长圆状披针形，长3～12厘米，宽0.5～3厘米；全缘或微齿裂至羽状深裂，齿尖具针刺。上表面绿褐色，下表面灰绿色，两面均有白色柔毛，头状花序单个或数个顶生，总苞钟状，苞片黄绿色，5～8层，花紫红色。气微，味微苦。\n" +
                             "\n" +
                             "【性味归经】性凉，味甘、苦。归心经、肝经。\n" +
                             "\n" +
                             "【功效与作用】凉血止血、祛瘀消肿。属止血药下属分类的凉血止血药。\n" +
                             "\n" +
                             "【临床应用】用量4.5～9克，水煎服；鲜者30～60克，捣汁研末。外用：捣敷或煎水洗。用治吐血、衄血、尿血、便血、崩漏下血、外伤出血、黄疸、痈肿疮毒等。具有止血作用。对甲醛性关节炎有一定程度的消炎作用；还有镇静、抑菌和利胆作用\n" +
                             "\n" +
                             "【药理研究】水煎剂有直接的拟交感神经药的作用，对麻醉后破坏脊髓的大白鼠有去甲肾上腺素样的升压作用；对离体兔心和蟾蜍心脏均有兴奋作用。\n" +
                             "\n" +
                             "【化学成分】含胆碱、皂苷、儿茶酚胺类物质，并显生物碱皂苷的反应。\n" +
                             "\n" +
                             "【使用禁忌】脾胃虚寒者禁服。\n" +
                             "\n" +
                             "【配伍药方】①治卒吐血及泻鲜血：小蓟叶，捣汁，温服。(《梅师集验方》)\n" +
                             "\n" +
                             "②治吐血：小蓟、大蓟、侧柏叶各9克，仙鹤草、焦栀子各12克。水煎服。(《常用中草药图谱》)\n" +
                             "\n" +
                             "③治妇人阴痒不止：小蓟，不拘多少，水煮作汤，热洗，日三用之。(《妇人良方》)\n" +
                             "\n" +
                             "④治鼻窒、气息不通：小蓟一把。以水三升，煮取一升，分二服。(《千金要方》)\n" +
                             "\n" +
                             "⑤治高血压：小蓟、夏枯草各15克。煎水代茶饮。(《安徽中草药》)\n" +
                             "\n" +
                             "⑥治急性肾炎、泌尿系感染、尿痛浮肿：小蓟15克，生地9克，茅根60克。水煎服。(《天津中草药》)\n" +
                             "\n" +
                             "⑦治传染性肝炎，肝肿大：鲜小蓟根60克。水煎服，10天为1疗程。(《常用中草药图谱》)");

                     chineseMedicineDao.insert(medicine184);
                     ChineseMedicine medicine185=new ChineseMedicine();
                     medicine185.setName("喜树果");
                     medicine185.setSortType("X");
                     medicine185.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=ddafffc89d16fdfacc61cebcd5e6e731/d058ccbf6c81800a63988dedb13533fa838b47d7.jpg");
                     medicine185.setData("【中药名】喜树果 xishuguo\n" +
                             "\n" +
                             "【别名】喜果、千丈树、水栗子、天梓树、水桐树、旱莲、水桐树、野芭蕉。\n" +
                             "\n" +
                             "【英文名】Fructus Camptothecae\n" +
                             "\n" +
                             "【药用部位】珙桐科植物旱莲木Camptotheca acuminata Decne.的果实。\n" +
                             "\n" +
                             "【植物形态】落叶大乔木，树皮灰色，枝条有黄乳色皮孔。叶互生，卵状长方形或卵状椭圆形，长7～28厘米，宽5～12厘米，先端渐尖，基部圆或广楔形，全缘，边缘有纤毛，上面有疏毛或脱落，下面沿脉有毛，羽脉10～11对，叶柄红色，有疏毛。花单性同株，成球形头状花序，花萼5齿裂，花瓣5，绿色，雄花雄蕊10，不等长，退化子房有时存在，雌蕊子房下位，1室，柱头2～3裂，花盘明显。果序球状，直径约5厘米，瘦果线形或披针形，长约2.5厘米，有翅，亮褐色。花期8月，果期10～11月。\n" +
                             "\n" +
                             "【产地分布】生于海拔1000米以下空气较潮湿处。分布于西南、中南及江苏、浙江等地。有栽培。\n" +
                             "\n" +
                             "【采收加工】秋季待果实成熟后采收，晒干。\n" +
                             "\n" +
                             "【药材性状】呈披针形，长2～2.5厘米，宽5～7毫米，先端尖，有柱头残基，基部变狭，可见着生在花盘上的椭圆形凹点痕，两边有翅。表面棕色至棕黑色，微有光泽，有纵皱纹，有时可见数条角棱和黑色斑点。质韧，不易折断，断面纤维性，内有种子1粒，干缩成细条状。气微，味苦。\n" +
                             "\n" +
                             "【性味归经】性寒，味苦、涩。归脾经、胃经、肝经。\n" +
                             "\n" +
                             "【功效与作用】抗癌，散结，破血化瘀。属活血化瘀药下分类的破血消癥药。\n" +
                             "\n" +
                             "【临床应用】内服：煎汤，用量3～9克，或研末吞，或制成针剂、片剂。主治各种肿瘤，用于原发性肝癌，对胃癌、头颈部腺源性皮癌、白血病、膀胱癌等恶性肿瘤等。也用于治疗血吸虫病引起的肝脾肿大。\n" +
                             "\n" +
                             "【药理研究】抗肿瘤；有抑制成纤维细胞增生作用；抗早孕；抑制疱疹病毒。过去多从根中提取喜树碱，近年来研究发现果实疗效最佳，毒性小，喜树碱和羟基喜树碱含量最高，采集方便，又不损伤植株，故目前商品多收购果实，作为提取分离喜树碱和羟基喜树碱的原料。目前多用喜树果的提取物制成酒浸膏注射液及水煎膏片剂使用，其疗效与喜树碱纯品相似，而副作用明显低于喜树碱纯品。\n" +
                             "\n" +
                             "【化学成分】含喜树碱、羟基喜树碱、去氧喜树碱、喜树次碱，尚含桦皮酸及喜果苷。从喜树果中还分离得11-羟基喜树碱、10-甲氧基喜树碱、脱落酸、丁香脂素和β-谷甾醇，其中11-羟基喜树碱、10-甲氧基喜树碱为新生物碱。喜树的根、茎干、皮、叶和果实一样均含喜树碱，均有抗癌作用。\n" +
                             "\n" +
                             "【使用禁忌】内服不宜过量。\n" +
                             "\n" +
                             "【配伍药方】①治胃癌，直肠癌，肝癌，膀胱癌：喜树果研末，每日1次，每次6克。(《辨证施治》)\n" +
                             "\n" +
                             "②治白血病：喜树果30克，仙鹤草、鹿衔草、岩株、银花、凤尾草各30克，甘草9克。煎汁代茶饮。(《本草骈比》)\n" +
                             "\n");

                     chineseMedicineDao.insert(medicine185);

                     ChineseMedicine medicine186=new ChineseMedicine();
                     medicine186.setName("香橼");
                     medicine186.setSortType("X");
                     medicine186.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C3%2C1100%2C724%3Bc0%3Dbaike150%2C5%2C5%2C150%2C50/sign=3cf773df9022720e6f81b8ba46fb2675/a8014c086e061d953f7be18573f40ad162d9ca4a.jpg");
                     medicine186.setData("【中药名】香橼 xiangyuan\n" +
                             "\n" +
                             "【别名】枸橼、钩缘干、佛手柑、香泡树、香橼柑。\n" +
                             "\n" +
                             "【英文名】Citri Fructus。\n" +
                             "\n" +
                             "【来源】芸香科植物香圆Citrus wilsonii Tanaka.或枸橼Citrus medic L.的成熟果实。\n" +
                             "\n" +
                             "【植物形态】常绿乔木。高4～10米，树枝有棱及短刺。叶互生，单生复叶，革质，叶片长椭圆形，两面有半透明油点；叶柄有阔翼呈倒心形。花单生或簇生，也有呈总状花序顶生或腋生；花萼矮杯状，5裂，裂片三角形；花瓣5，白色，长圆状倒卵形；雄蕊25～36枚，着生于花盘四周，花丝连合；雌蕊l枚，子房上位，扁圆形，花柱圆柱形，柱头头状。柑果圆形，短径5～7厘米，顶端留有花柱基痕，呈乳头状突起，果皮粗糙或平滑，成熟时橙黄色。种子多数。花期4～5月，果期10～11月。\n" +
                             "\n" +
                             "【产地分布】分布于江苏、安徽、四川等地。\n" +
                             "\n" +
                             "【采收加工】成熟时采摘，整个或对剖两半后，晒干或烘干。\n" +
                             "\n" +
                             "【药材性状】类球形、半球形或圆片，直径4～7厘米。表面黑绿色或黄棕色，密被凹陷的小油点及网状隆起的粗皱纹，顶端有花柱残痕及隆起的环圈，基部有果梗残基。质坚硬。剖面或横切薄片，边缘油点明显；中果皮厚约0.5厘米；瓤囊9～11室，棕色或淡红棕色，间或有黄白色种子。气香，味酸而苦。\n" +
                             "\n" +
                             "【性味归经】性温，味辛、苦、酸。归肝经、脾经、肺经。\n" +
                             "\n" +
                             "【功效与作用】舒肝理气、宽中、化痰。属理气药。\n" +
                             "\n" +
                             "【临床应用】用量3～9克，内服煎汤。用治肝胃气滞、胸肋胀痛、脘腹痞满、呕吐噫气、痰多咳嗽。\n" +
                             "\n" +
                             "【药理研究】所含挥发油对胃肠道有温和刺激作用，能促进肠胃里蠕动和消化液分泌，排除肠内积气，另有祛痰、抗炎、抗病毒作用，所含橙皮苷有预防冻伤和抑制大鼠晶状体的醛还原酶作用。\n" +
                             "\n" +
                             "【化学成分】含挥发油，主要成分为香叶醛、柠檬烯等。另含橙皮苷、枸橼酸、牻牛儿醛、丁香烯、金合欢醛等成分。\n" +
                             "\n" +
                             "【使用禁忌】阴虚血燥及孕妇气虚者慎服。\n" +
                             "\n" +
                             "【配伍药方】①治气逆不进饮食或呕哕：陈极香橼两个，真川贝(去心)90克，当归(炒黑)45克，白通草(烘燥)30克，陈西瓜皮30克，甜桔梗9克。并研细末，用白檀香劈碎煎浓汁泛为丸，如梧桐子大。每服9克，开水送下。(《梅氏验方新编》香橼丸)\n" +
                             "\n" +
                             "②治臌胀：陈香橼(连瓤)一枚，大核桃肉(连皮)二枚，缩砂仁(去膜)6克。各煅存性为散，砂糖拌调。空心顿服。(《本经逢原》)\n" +
                             "\n" +
                             "③治三日疟：陈香橼一枚，去顶皮，入研细明雄黄，同内火中煅之，取出研极细。每服2.1克，干咽下，不用水。(《华佗神医秘传》)\n" +
                             "\n" +
                             "④治头风：香橼不拘新旧(切开)一枚。鸭蛋(煮熟，切两半)一枚，塞入香橼内。每边包在太阳穴上，得热即愈。(《串雅外编》)");

                     chineseMedicineDao.insert(medicine186);

                     ChineseMedicine medicine187=new ChineseMedicine();
                     medicine187.setName("西瓜皮");
                     medicine187.setSortType("X");
                     medicine187.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545811465&di=fc9946ef3e9ac2fc8b848d6b99256487&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.ph.126.net%2FNgbWjmBYGahNzfac-z_CBw%3D%3D%2F639511147103429546.jpg");
                     medicine187.setData("【中药名】西瓜皮 xiguapi\n" +
                             "\n" +
                             "【别名】西瓜青、西瓜翠衣、西瓜翠。\n" +
                             "\n" +
                             "【英文名】Exocarpium Citrulli\n" +
                             "\n" +
                             "【药理研究】葫芦科植物西瓜Citrulluslanatus (Thunb.) Matsum.et Nakai的外层干燥果皮。\n" +
                             "\n" +
                             "【植物形态】一年生蔓性草本。茎细弱，匍匐，有明显的棱沟。卷须2歧，被毛。叶互生，叶柄长3～12厘米，叶片三角状卵形、广卵形，长8～20厘米，宽5～18厘米，3深裂或近3全裂，中间裂片较长，两侧裂片较短，裂片再作不规则羽状深裂或二回羽状分裂，两面均为淡绿色，边缘波状或具疏齿。雌雄同株，雄花、雌花均单生于叶腋，雄花直径2～2.5厘米，花梗细，被长柔毛，花萼合生成广钟形，被长毛，先端5裂，裂片窄披针形或线状披针形，花冠合生成漏斗状，外面绿色，被长柔毛，上部5深裂，裂片卵状椭圆形或广椭圆形，先端钝。雌花较雄花大，花萼和雄花相似。瓠果近圆形或长椭圆形，直径约30厘米，表面绿色、浅绿色，多具深浅相问的条纹。种子多数，扁形，略呈卵形，黑色、红色、白色或黄色，或有斑纹，两面平滑，基部钝圆，经常边缘稍拱起。花、果期夏季。\n" +
                             "\n" +
                             "【产地分布】全国各地均有栽培。\n" +
                             "\n" +
                             "【采收加工】夏季收集西瓜皮，削去内层柔软部分，洗净，晒干。也有将外面青皮削去，仅取其中间部分者。\n" +
                             "\n" +
                             "【药材性状】外层果皮常卷成管状、纺锤状或不规则形的片块，大小不一，厚0.5～1厘米。外表面深绿色、黄绿色或淡黄白色，光滑或具深浅不等的皱纹，内表面色稍淡，黄白色至黄棕色，有网状筋脉(维管束)，常带有果柄。质脆，易碎，无臭，味淡。\n" +
                             "\n" +
                             "【性味归经】性凉，味甘。归心经、胃经、膀胱经。\n" +
                             "\n" +
                             "【功效与作用】清热，解渴，利尿。属清热药下分类的清热泻火药。\n" +
                             "\n" +
                             "【临床应用】内服：煎汤，用量9～30克，或焙干研末。外用：适量，烧存性研末撒。主治暑热烦渴，小便短少，水肿，口舌生疮。\n" +
                             "\n" +
                             "【化学成分】主要含天冬氨酸、苏氨酸、果糖、葡萄糖、蔗糖、番茄红素、维生素C等。\n" +
                             "\n" +
                             "【使用禁忌】中寒湿盛者禁服。\n" +
                             "\n" +
                             "【配伍药方】①治脱肛：西瓜皮50克，冰糖适量。水煎服。(《福建药物志》)\n" +
                             "\n" +
                             "②治小儿夏季热：西瓜皮、金银花各15克，太子参9克，扁豆花、薄荷(后下)各6克，鲜荷叶半张。煎服。(《安徽中草药》)\n" +
                             "\n" +
                             "③治高血压：西瓜皮10～12克，草决明子10克。煎汤代茶。(《食物中药与便方》)\n" +
                             "\n" +
                             "④治糖尿病，口渴，尿浑浊：西瓜皮、冬瓜皮各15克，天花粉12克，水煎服。(《食物中药与便方》)");

                     chineseMedicineDao.insert(medicine187);

                     ChineseMedicine medicine188=new ChineseMedicine();
                     medicine188.setName("小茴香");
                     medicine188.setSortType("X");
                     medicine188.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545811690&di=a53d1b10f58297c410d1fbf0c22a7e21&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3D40287a169ecad1c8c4b6f46716460265%2Fdcc451da81cb39dbc69b0d6fdb160924ab1830ab.jpg");
                     medicine188.setData("【中药名】小茴香 xiaohuixiang\n" +
                             "\n" +
                             "【别名】茴香、土茴香、野茴香。\n" +
                             "\n" +
                             "【英文名】Foeniculi Fructus。\n" +
                             "\n" +
                             "【来源】伞形科植物茴香Forniculum vulgare Mill.的果实。\n" +
                             "\n" +
                             "【植物形态】多年生草本，全株无毛，有强烈香气。茎直立，有浅纵沟纹，上部分枝开展。叶有柄，卵圆形至广三角形，长达30厘米，宽达40厘米，3～4回羽状分裂，深绿色，末回裂片线形至丝状，茎下部的叶柄基部鞘状，上部的叶柄一部分或全部成鞘状。复伞形花序顶生或侧生，顶生的花序较大，直径可达15厘米，无总苞及小苞片，花黄色，花瓣5，倒卵形，先端内折，雄蕊5枚，雌蕊1枚，子房下位。双悬果卵状长圆形，光滑，侧扁，长3.5～6毫米，宽1.5～ 2.5毫米。分果有5条隆起的纵棱，每棱槽中有油管1，合生面有2。\n" +
                             "\n" +
                             "【产地分布】原产于欧洲，现我国各地田园常见栽培。内蒙古产品质佳，销全国各地。\n" +
                             "\n" +
                             "【采收加工】秋季果实初熟时，采割植株，晒干，打下果实，除去杂质。盐小茴香：取净小茴香，用文火炒至表面呈深黄色、有焦香气味时，用盐水乘热喷入，焙干。或以净小茴香加盐水拌匀，略闷，置锅内用文火炒至微黄色，取出，晾干(每50千克小茴香，用食盐1.5千克)。\n" +
                             "\n" +
                             "【药材性状】双悬果细圆柱形，有时稍弯曲，长4～8毫米，直径1.5～2.5毫米，表面黄绿色或淡黄色，两端略尖，顶端有黄褐色的花柱残基，基部有时带有小的果柄，长0.4～1厘米，分果长椭圆形，有5条隆起的棱线，合生面平坦而较宽，横切面略呈五边形。有特异香气，味微甜而辛。\n" +
                             "\n" +
                             "【性味归经】性温，味辛。归肝经、脾经、胃经、肾经。\n" +
                             "\n" +
                             "【功效与作用】祛寒止痛、理气和胃。属温里药。\n" +
                             "\n" +
                             "【临床应用】用量3～6克，内服煎汤，治疗寒疝腹痛、睾丸偏坠、痛经、少腹冷痛、脘腹胀痛、食少吐泻、睾丸鞘膜积液。盐小茴香暖肾，散寒止痛，用于治疗寒疝腹痛、睾丸偏坠、经寒腹痛。\n" +
                             "\n" +
                             "【药理研究】促进胃肠蠕动；抗溃疡；利胆；松弛气管平滑肌；有性激素样作用；中枢麻痹；抑菌；抗肿瘤。煎剂能兴奋离体兔肠收缩和促进在体兔肠蠕动，茴香油、茴香脑均先兴奋肠管加强收缩，浓度增高出现解痉作用；给动物灌服或十二指肠给药，能抑制应激性胃溃疡；对部分肝切除大鼠，茴香油能使其肝再生度增加；丙醇提取物具有性激素样作用；此外，还具有中枢抑制、抗凝抗纤溶等作用。\n" +
                             "\n" +
                             "【化学成分】含挥发油、甾醇、三萜、香豆素和氨基酸等多种化合物。另含反式茴香脑、柠檬烯、α-香树脂醇、樟脑、茴香醛、小茴香酮、棕榈酸、香柑内酯、伞形花内酯等成分。\n" +
                             "\n" +
                             "【使用禁忌】热证及阴虚火旺者禁服。\n" +
                             "\n" +
                             "【配伍药方】①治寒疝疼痛：川楝子12克，木香9克，小茴香6克，吴茱萸(汤泡)3克。水煎服。(《医方集解》导气汤)\n" +
                             "\n" +
                             "②治外肾肿胀：小茴香(炒)、全蝎(炒)、穿山甲(炙)、木香各等分，为末，每服6克，酒调下。(《医统》四圣散)\n" +
                             "\n" +
                             "③治胃痛，腹痛：小茴香子、良姜、乌药根各6克，炒香附9克，水煎服。(《江西草药》)\n" +
                             "\n" +
                             "④治胁下疼痛：小茴香(炒)30克，枳壳(麸炒)15克。上为末，每服9克，盐汤调下。(《袖珍方》)\n" +
                             "\n" +
                             "⑤治腰痛：川芎(盐炒)45克，小茴香(炒)90克，苍术(葱白炒)60克。酒煮糊丸。盐、酒任下。(《慎斋遗书》三仙丹)");

                     chineseMedicineDao.insert(medicine188);

                     ChineseMedicine medicine189=new ChineseMedicine();
                     medicine189.setName("薤白");
                     medicine189.setSortType("X");
                     medicine189.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=9552952203f41bd5ce5ee0a630b3eaae/4e4a20a4462309f7781edf1b7f0e0cf3d7cad642.jpg");
                     medicine189.setData("【中药名】薤白 xiebai\n" +
                             "\n" +
                             "【别名】薤根、蕌头、小独蒜、野蒜、薤白头。\n" +
                             "\n" +
                             "【英文名】Allii Macrostemonis Bulbus。\n" +
                             "\n" +
                             "【来源】百合科植物小根蒜Allium macrostemon Bge.或薤Allium chinense G.Don的鳞茎。\n" +
                             "\n" +
                             "【植物形态】多年生草本，高达70厘米。鳞茎近球形，外被白色膜质鳞皮。叶基生，叶片线形，先端渐尖，基部鞘状，抱茎。花茎由叶丛中抽出，单一，直立，平滑无毛，伞形花序密而多花，近球形，顶生，花梗细，花被6，长圆状披针形，淡紫粉红色或淡紫色，雄蕊6枚，长于花被，花丝细长，雌蕊1枚，子房上位。蒴果。\n" +
                             "\n" +
                             "【产地分布】生于耕地杂草中及山地较干燥处。分布于黑龙江、吉林、辽宁等地。\n" +
                             "\n" +
                             "【采收加工】北方多在春季，南方多在夏、秋季采收。连根挖起，除去茎叶及须根，洗净，用沸水煮透，晒干或烘干。须置干燥处，防潮防蛀。炒薤白：将净薤白置人锅内，文火炒至外表面呈现焦斑为度，取出放凉。\n" +
                             "\n" +
                             "【药材性状】呈不规则卵圆形，高0.5～1.5厘米，直径0.5～1.8厘米。表面黄白色或淡黄棕色，皱缩，半透明，有类白色膜质鳞片包被，底部有突起的鳞茎盘。质硬，角质样。有蒜臭，味微辣。\n" +
                             "\n" +
                             "【性味归经】性温，味辛、苦。归心经、肺经、胃经、大肠经。\n" +
                             "\n" +
                             "【功效与作用】通阳散结、行气导滞。属理气药。\n" +
                             "\n" +
                             "【临床应用】用量5～9克，内服煎汤，或入丸散，治疗胸痹疼痛、痰饮咳喘、泻痢后重。外用，捣敷或捣汁涂。\n" +
                             "\n" +
                             "【药理研究】抗动脉粥样硬化；抗血小板凝集；干扰花生四烯酸代谢；抗氧化；抗菌等作用。\n" +
                             "\n" +
                             "【化学成分】本品主要含薤白苷A、薤白苷D、薤白苷E、薤白苷F、甲基烯丙基二硫化物、二甲基二硫化物、2,4-二甲基噻吩、5-甲基-1,2,3,4-四噻烷等成分。\n" +
                             "\n" +
                             "【使用禁忌】阴虚及发热者慎服。\n" +
                             "\n" +
                             "【配伍药方】①治胸痹不得卧，心痛彻背者：瓜蒌实(捣)一枚，薤白90克，半夏半升，白酒一斗。上四味，同煮。取四升。温服一升，日三服。(《金匮要略》瓜蒌薤白半夏汤)\n" +
                             "\n" +
                             "②治肺气喘急：用薤白研汁饮之。(《卫生易简方》)\n" +
                             "\n" +
                             "③治咽喉肿痛：薤白根，醋捣，敷肿处，冷即易之。(《圣惠方》)\n" +
                             "\n" +
                             "④治扭伤肿痛：鲜薤白和红酒糟捣烂敷患处。(《福建药物志》)\n" +
                             "\n" +
                             "⑤治头痛、牙痛：鲜薤白、红糖各15克。捣烂敷足掌心。(《福建药物志》)\n" +
                             "\n");

                     chineseMedicineDao.insert(medicine189);

                     ChineseMedicine medicine190=new ChineseMedicine();
                     medicine190.setName("萱草根");
                     medicine190.setSortType("X");
                     medicine190.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=8d6e61d3bb19ebc4d4757ecbe34fa499/b3119313b07eca8091676751992397dda1448322.jpg");
                     medicine190.setData("【中药名】萱草根 xuancaogen\n" +
                             "\n" +
                             "【别名】漏芦果、黄花菜根、竹叶麦冬。\n" +
                             "\n" +
                             "【英文名】Radix hemerocallis\n" +
                             "\n" +
                             "【来源】百合科植物萱草Hemerocallis fulva(L.)L.的干燥根。\n" +
                             "\n" +
                             "【植物形态】多年生草本，具短的根茎和肉质、肥大的纺锤状块根。叶基生，排成两列，叶片条形，长40～80厘米，宽1.5～3.5厘米，下面呈龙骨状突起。花葶粗壮，高60～80厘米，蝎尾状聚伞花序复组成圆锥状，具花6～12朵或更多，苞片卵状披针形，花橘红色至橘黄色，无香味，具短花梗，花被长7～12厘米，下部2～3厘米合生成花被管，外轮花被裂片3，长圆状披针形，宽1.2～1.8厘米，具平行脉，内轮裂片3，长圆形，宽达2.5厘米，具分枝的脉，中部具褐红色的色带，边缘波状皱褶，盛开时裂片反曲，雄蕊伸出，上弯，比花被裂片短，花柱伸出，上弯，比雄蕊长。蒴果长圆形。花、果期为5～7月。\n" +
                             "\n" +
                             "【产地分布】全国各地常见栽培，秦岭以南各地区有野生。\n" +
                             "\n" +
                             "【采收加工】夏、秋采挖，除去残茎、须根，洗净泥土，晒干。\n" +
                             "\n" +
                             "【药材性状】根茎呈短圆柱形，长1～1.5厘米，直径约1厘米。有的顶端留有叶残基，根簇生，多数已折断。完整的根长5～15厘米，上部直径3～4毫米，中下部膨大成纺锤形块根，直径0.5～1厘米，多干瘪抽皱，有多数纵皱及少数横纹，表面灰黄色或淡灰棕色。体轻，质松软，稍有韧性，不易折断，断面灰棕色或暗棕色，有多数放射状裂隙。气微香，味稍甜。\n" +
                             "\n" +
                             "【性味归经】性凉，味甘，有毒。归脾经、肝经、膀胱经。\n" +
                             "\n" +
                             "【功效与作用】清热利湿，凉血止血，解毒消肿。属止血药下分类的凉血止血药。\n" +
                             "\n" +
                             "【临床应用】内服：煎汤，6～9克；外用：适量，捣敷。主治黄疸，水肿，淋浊，带下，衄血，便血，崩漏，瘰疬，乳痈，乳汁不通。\n" +
                             "\n" +
                             "【药理研究】体外对结核杆菌有一定的抑制作用，有抗血吸虫作用；对不同疾病所引起的浮肿有不同程度的利尿作用。\n" +
                             "\n" +
                             "【化学成分】本品含大黄酚、黄花葸醌、美决明子素甲醚、决明子素、芦荟大黄素、大黄酸、小萱草根素、萱草酮、β-谷固醇、γ-谷固醇、萱草根素、甾类、酚类、氨基酸、糖类等成分。\n" +
                             "\n" +
                             "【使用禁忌】本品有毒，内服宜慎。不宜久服、过量，以免中毒。\n" +
                             "\n" +
                             "【配伍药方】①治大便后血：萱草根和生姜，油炒，酒冲服。(《圣济总录》)\n" +
                             "\n" +
                             "②治男、妇腰痛：萱草根十五个，猪腰子一个。水煎服三次。(《滇南本草》)\n" +
                             "\n" +
                             "③治心痛诸药不效：萱草根一寸，磨醋一杯，温服止。(《医统大全》)");

                     chineseMedicineDao.insert(medicine190);

                     ChineseMedicine medicine191=new ChineseMedicine();
                     medicine191.setName("续断");
                     medicine191.setSortType("X");
                     medicine191.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545834714&di=3ddc0fdbfd3421f0db89c8194e94cda6&imgtype=jpg&er=1&src=http%3A%2F%2Ffile.cnkang.com%2Fcnkfile1%2FM00%2F13%2F6D%2Fo4YBAFkrA_eAap7pAAGhf-Bdgzg27.jpeg");
                     medicine191.setData("【中药名】续断 xuduan\n" +
                             "\n" +
                             "【别名】北续断、接骨草、南草、鼓锤草、和尚头。\n" +
                             "\n" +
                             "【英文名】Dipsaci Radix。\n" +
                             "\n" +
                             "【来源】川续断科植物续断Dispsacus japonicus Miq.的根。\n" +
                             "\n" +
                             "【植物形态】多年生草本，高1米以上。根圆锥形，主根明显，外皮黄色或土黄色。茎直立，多分枝，具棱和浅沟，棱上有倒钩刺。基生叶长椭圆形，不裂或3裂，有长柄，茎生叶对生，倒卵状椭圆形，长达20厘米，宽达8厘米，有长柄，叶片3～5羽状深裂，中央裂处最大，两侧渐小，先端锐尖，基部楔形，下延成狭翅，边缘有粗锯齿，两面被疏白毛，背脉和叶柄均有钩刺。花序刺球状，顶生，长2～3厘米，基部有条状总苞片数片，苞片多数，螺旋密列，长倒卵形，顶端稍平截，中央有锥刺状长喙，喙有白色长刺毛，花萼皿状，4裂极浅，外被白色，花冠紫红色，漏斗状，基部呈短细筒，内外均被毛，裂片4，2片稍大，雄蕊4枚，稍伸出，子房下位，包于囊状小总苞中。果时苞片增大，达15毫米，喙刺稍长于片部，小总苞四棱柱状，顶有8齿。瘦果稍外露。\n" +
                             "\n" +
                             "【产地分布】生于山坡草丛较湿处。分布于全国各地。\n" +
                             "\n" +
                             "【采收加工】秋季采挖，除去根头及细根，洗净，阴干或烘干。炒续断：取续断片人锅内以文火炒至微焦；盐续断：取续断片入锅内，加入盐水拌炒至干透(每续断片50千克，用食盐l千克，加开水适量化水)；酒续断：取续断片用酒拌匀吸干，入锅内以文火炒干(每50千克续断片，用黄酒10千克)。\n" +
                             "\n" +
                             "【药材性状】长圆柱形，向下渐细，长5～15厘米，直径0.5～2厘米。表面黄褐色或土褐色，有纵皱纹及须根痕。质硬而脆，易折断，断面不平坦，维管束呈放射状排列，中心木质部淡褐色。气微香，味苦甜而后涩。\n" +
                             "\n" +
                             "【性味归经】性微温，味苦、辛。归肝经、肾经。\n" +
                             "\n" +
                             "【功效与作用】补肝肾、强筋骨、续折伤、止崩漏。属补虚药下分类的补阳药。\n" +
                             "\n" +
                             "【临床应用】用量9～15克，内服煎汤，或入丸散，治疗腰膝酸软、风湿痹痛、崩漏经多、胎漏下血、跌打损伤。外用捣敷。治腰痛：续断100克，破故纸、牛膝、木瓜、萆薢、杜仲各50克，研细末，炼蜜为丸桐子大，空心无灰酒下50或60丸；治跌打损伤：续断捣烂敷之。\n" +
                             "\n" +
                             "【药理研究】具有抗菌消炎、增强免疫调节、抗氧化及抗衰老、促进骨损伤愈合、抗早产流产的作用。\n" +
                             "\n" +
                             "【化学成分】含续断碱及挥发油。\n" +
                             "\n" +
                             "【使用禁忌】泻痢初起勿用。\n" +
                             "\n" +
                             "【配伍药方】 ①治老人风冷，转筋骨痛：续断、牛膝(去芦，酒浸)。上为细末。温酒调下6克，食前服。(《杨氏家藏方》续断散)\n" +
                             "\n" +
                             "②治妊娠胎动两三月堕，预宜服此：续断(酒浸)、杜仲(姜汁炒去丝)各60克。为末，枣肉煮，杵和丸，梧桐子大。每服三十丸，米饮下。(《纲目》)\n" +
                             "\n" +
                             "③治胃痛：续断9～15克。水煎服。忌酸辣食物。(《广西民族药简编》)\n" +
                             "\n" +
                             "④治乳痈，初起可消，久患可愈：续断(酒浸，炒)240克，蒲公英(日干，炒)120克;俱为末。每早晚各服9克，白汤调下。(《本草汇言》)\n" +
                             "\n" +
                             "⑤阳痿不举，遗精遗尿。该品甘温助阳，辛温散寒，用治肾阳不足，下元虚冷，阳痿不举，遗精滑泄，遗尿尿频等症。常与鹿茸、肉苁蓉、菟丝子等壮阳起痿之品配伍，如鹿茸续断散(《鸡峰普济方》);或与远志、蛇床子、山药等壮阳益阴，交通心肾之品同用，如远志丸(《外台秘要》);亦可与龙骨、茯苓等同用，用治滑泄不禁之症，如锁精丸(《瑞竹堂经验方》)。\n" +
                             "\n" +
                             "⑥腰膝酸痛，寒湿痹痛。该品甘温助阳，辛以散瘀，兼有补益肝肾，强健壮骨，通利血脉之功。可与萆薢、杜仲、牛膝等同用，用治肝肾不足，腰膝酸痛，如续断丹(《证治准绳》);亦可与防风、川乌等配伍，用治肝肾不足兼寒湿痹痛，如续断丸(《和剂局方》。\n" +
                             "\n" +
                             "⑦崩漏下血，胎动不安。该品补益肝肾，调理冲任，有固本安胎之功。可用于肝肾不足，崩漏不血，胎动不安等症。配伍侧柏炭、当归、艾叶等止血活血，温经养血之品，用治崩中下血久不止者(《永类钤方》);或以该品与桑寄生、阿胶等配伍，用治滑胎证，如寿胎丸(《医学衷中参西录》)。\n" +
                             "\n" +
                             "⑧跌打损伤，筋伤骨折。该品辛温破散之性，善能活血祛瘀;甘温补益之功，又能壮骨强筋，而有续筋接骨、疗伤止痛之能。用治跌打损伤，瘀血肿痛，筋伤骨折。常与桃仁、红花、穿山甲、苏木等配伍同用;或与当归、木瓜、黄芪等同用，治疗脚膝折损愈后失补，筋缩疼痛，如邱祖伸筋丹(《赛金丹》)。\n" +
                             "\n" +
                             "⑨长发：用续断汁沐头。(《普济方》)");

                     chineseMedicineDao.insert(medicine191);

                     ChineseMedicine medicine192=new ChineseMedicine();
                     medicine192.setName("徐长卿");
                     medicine192.setSortType("X");
                     medicine192.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=887b071f5cdf8db1a8237436684ab631/728da9773912b31b644acbae8418367adab4e117.jpg");
                     medicine192.setData("【中药名】徐长卿 xuchangqing\n" +
                             "\n" +
                             "【别名】鬼督邮、别仙踪、土细辛、料叼竹、一枝箭、三百根。\n" +
                             "\n" +
                             "【英文名】Cynanchi Paniculati Radix Et Rhizoma。\n" +
                             "\n" +
                             "【来源】萝蘼科植物徐长卿Cynanchum paniculatum (Bge.) Kitag.的根及根茎。\n" +
                             "\n" +
                             "【植物形态】多年生直立草本，高达1米，根细呈须状多至50余条，具特殊气味。茎不分枝，无毛或被微毛。叶对生，纸质，披针形至线形，两端急尖，两面无毛或上面具疏柔毛，叶缘稍反卷有睫毛。圆锥聚伞花序近顶腋生，有花10余朵，花萼内面有或无腺体，花冠黄绿色，近辐状，裂片长达4毫米，宽3毫米，副花冠裂片5，顶端钝，基部增厚，花粉块每室1个，下垂，臂短，平伸，子房椭圆形，柱头五角形，顶端略突起。瞢荚果。种子长圆形，长约3毫米，顶端具白绢质种毛，长1厘米。\n" +
                             "\n" +
                             "【产地分布】生于阳坡草丛中。分布于黑龙江、吉林、辽宁、内蒙古等地。\n" +
                             "\n" +
                             "【采收加工】夏、秋季采挖全草，扎成小把，除去杂质，晾干或晒干。药用根及根茎者则除去地上部分。炮制时拣去杂质略洗，切成小段，阴干。\n" +
                             "\n" +
                             "【药材性状】根茎呈不规则柱形，有盘节，长0.5～3.5厘米，直径2～4毫米，四周着生多数细长的根。根呈圆柱形，弯曲，长10～11厘米，直径1～1.5毫米，表面淡褐色或淡棕黄色，具微细的纵皱纹，并有纤细的须根。质脆，易折断，断面粉性，韧皮部黄白色，形成层环淡棕色，木质部细小，淡黄色，具丹皮香气，味微辛竦。\n" +
                             "\n" +
                             "【性味归经】性温，味辛。归肝经、胃经。\n" +
                             "\n" +
                             "【功效与作用】祛风化湿、行气通络。属祛风湿药下分类的祛风湿强筋骨药。\n" +
                             "\n" +
                             "【临床应用】用量3～12克，治疗风湿痹痛、胃痛胀满、牙痛、痛经、跌打肿痛等。与何首乌等合用以降血脂；本品治疗多种皮肤病；将本品制成针剂以治疗多种原因引起的疼痛，收到一定效果；水煎剂或片剂对治疗单纯型慢性支气管炎效果较佳，对喘息型较差；本品加阿胶、茜草治慢性再生障碍性贫血而获良效。\n" +
                             "\n" +
                             "【药理研究】具有镇静、镇痛、解热作用；可降压、抗心肌缺血、抗心律失常；降低血脂、抗动脉粥样硬化；抑制血小板聚集及抗血栓形成；抗炎和抗变态；使回肠张力下降、抑制胃肠蠕动；对肝微粒体代谢和子宫收缩有抑制作用；抗早孕作用。徐长卿提取液(除去丹皮酚主成分)、丹皮酚对小鼠热板法(扭体反应)证明有镇痛作用，给小鼠腹腔注射有镇静作用；煎剂有一定抑菌作用；丹皮酚具有抗炎及抗变态反应；徐长卿或丹皮酚具调血脂及抗动脉粥样硬化作用。毒性：徐长卿提取液腹腔给药，小鼠LDso为(32.93±1.03)克/千克；丹皮酚LDso为381克/千克。\n" +
                             "\n" +
                             "【化学成分】本品主要含丹皮酚、异丹皮酚、肉珊瑚苷元、丹皮酚原苷、2-羟基-6-甲氧基苯乙酮、去酚牛皮消苷元、徐长卿苷A、徐长卿苷B、徐长卿苷C、新徐长卿苷元F、徐长卿多糖等。\n" +
                             "\n" +
                             "【使用禁忌】不宜久煎，体弱者慎服。\n" +
                             "\n" +
                             "【配伍药方】①治风湿痛：徐长卿根24～30克，猪赤肉120克，老酒60克。酌加水煎成半碗，饭前服，日2次。(《福建民间草药》)\n" +
                             "\n" +
                             "②治慢性腰痛：徐长卿、虎杖各9克，红四块瓦5克。研末。每次0.6～1克，每日2～3次，温开水吞服。(《湖北中草药志》)\n" +
                             "\n" +
                             "③治寒气腹痛：徐长卿9克，小茴香6克。煎服。(《安徽中草药》)\n" +
                             "\n" +
                             "④治外伤肿痛：鲜徐长卿根、生栀子等量，同捣烂外敷;另用徐长卿9克，煎水，服时兑黄酒适量。(《安徽中草药》)\n" +
                             "\n" +
                             "⑤治血虚经闭：徐长卿6～9克，煨甜酒内服或炖肉吃，或研末吞服3克。(《贵阳民间药草》)");

                     chineseMedicineDao.insert(medicine192);

                     ChineseMedicine medicine193=new ChineseMedicine();
                     medicine193.setName("夏天无");
                     medicine193.setSortType("X");
                     medicine193.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=d1bbb2f5b3119313d34ef7e2045167b2/c9fcc3cec3fdfc0310bd9f8fd53f8794a4c2263b.jpg");
                     medicine193.setData("【中药名】夏天无 xiatianwu\n" +
                             "\n" +
                             "【别名】一粒金丹、洞里神仙、土元胡、伏地延胡索、无柄紫堇。\n" +
                             "\n" +
                             "【英文名】Corydalis Decumbentis Rhizoma。\n" +
                             "\n" +
                             "【来源】罂粟科植物伏生紫堇Corydatis decumbens (Thunb.) Pers.的块茎。\n" +
                             "\n" +
                             "【植物形态】多年生草本，全体无毛。块茎近球形，表面黑色，着生少数须根。茎细弱，丛生，不分枝。基生叶具长柄，叶片三角形，2回三出全裂，末回裂片具短柄，通常狭倒卵形；茎生叶2～3片，生茎下部以上或上部，形似基生叶，但较小，具稍长柄或无柄。总状花序顶生；苞片卵形或阔披针形.全缘；花淡紫红色，筒状唇形，上面花瓣边近圆形，先端微凹，矩圆筒形，直或向上微弯；雄蕊6，呈两体。蒴果线形，2瓣裂。种子细小。花期4～5月，果期5～6月。\n" +
                             "\n" +
                             "【产地分布】生于丘陵、低山坡或草地。喜生于温暖湿润、向阳、排水良好、土壤深厚的沙质地。分布于安徽、江苏、浙江、江西等地。\n" +
                             "\n" +
                             "【采收加工】春至初夏采块茎，去泥，洗净，晒干或鲜用。\n" +
                             "\n" +
                             "【药材性状】类球形、长圆形或不规则块状，长0.5～2厘米，直径0.5～1.5厘米。表面土黄色，棕色或暗绿色，有细皱纹，常有不规则的瘤状突起及细小的点状须根痕。质坚脆，断面黄白色或黄色，颗粒状或角质样，有的略带粉性。气无，味极苦。\n" +
                             "\n" +
                             "【性味归经】性温，味苦、微辛。归肝经。\n" +
                             "\n" +
                             "【功效与作用】活血、通络、行气止痛。属活血化瘀药下属分类的活血止痛药。\n" +
                             "\n" +
                             "【临床应用】用量5～16克，煎汤内服；或研末，1～3g；亦可制成丸剂。用治中风偏瘫、小儿麻痹后遗症、坐骨神经痛、风湿性关节炎、跌打损伤、腰肌劳损等。\n" +
                             "\n" +
                             "【药理研究】可引起动物产生“僵住症”，表现为木僵、嗜睡、肌肉僵硬，如随意改变其位置，可保持于该种姿势。药理实验表明，本品有抗张血管、抗血小板聚集、镇痛、解痉、降血压、松弛回肠平滑肌等作用。夏天无注射液在临床上治疗高血压脑血管病、骨关节肌肉疾病及青年近视等，均见良效。\n" +
                             "\n" +
                             "【化学成分】含四氢巴马亭(即延胡索乙素)、原阿片碱、盐酸巴马汀、空褐鳞碱、藤荷包牡丹定碱、夏天无碱、紫堇米定碱、比枯枯灵碱、掌叶防己碱等，其总碱含量达0.98%。应用高效薄层色谱分离及薄层扫描定量，对夏天无的化学成分及含量进行比较，结果表明，其延胡索乙素含量最高。\n" +
                             "\n" +
                             "【使用禁忌】尚不明确。\n" +
                             "\n" +
                             "【相关药方】①治高血压，脑瘤或脑栓塞所致偏瘫：鲜夏天无捣烂。每次大粒4～5粒，小粒8～9粒，每天1～3次，米酒或开水送服，连服3～12个月。(《浙江民间常用草药》)\n" +
                             "\n" +
                             "②治各型高血压病：a.夏天无研末冲服，每次2～4克。b.夏天无、钩藤、桑白皮、夏枯草。煎服。(江西《中草药学》)\n" +
                             "\n" +
                             "③治风湿性关节炎：夏天无粉每次9克，日2次。(江西《中草药学》)\n" +
                             "\n" +
                             "④治腰肌劳损：夏天无全草15克，煎服。(江西《中草药学》)\n" +
                             "\n");

                     chineseMedicineDao.insert(medicine193);

                     ChineseMedicine medicine194=new ChineseMedicine();
                     medicine194.setName("香附");
                     medicine194.setSortType("X");
                     medicine194.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=12d20da7367adab429dd1311eabdd879/30adcbef76094b364664a562a3cc7cd98c109d4f.jpg");
                     medicine194.setData("【中药名】香附 xiangfu\n" +
                             "\n" +
                             "【别名】莎草根、香附子、香头草、雀头香、雷公头。\n" +
                             "\n" +
                             "【英文名】Nutgrass Galingale Rhizome、Rhizome of Nutgrass Galingale、Cyperus Rotundus。\n" +
                             "\n" +
                             "【来源】莎草科植物莎草Cyperus rotundus L.的根茎。\n" +
                             "\n" +
                             "【植物形态】多年生草本。匍匐根茎长，先端具肥大纺锤形的根茎，外皮紫褐色，有棕毛或黑褐色毛状物。茎锐三角形，基部呈块茎状。叶窄线形短于秆；鞘棕色，常裂成纤维状。叶状苞片2～5；长侧枝聚伞花序简单或复出，辐射枝3～10；穗状花序轮廓为陀螺形；小穗3～10，线形，具花8～28朵；小穗轴具较宽的、白色透明的翅；鳞片覆瓦状排列，膜质，卵形或长圆状卵形，中间绿色，两侧紫红色或红棕色，具脉5～7条；雄蕊3，药线形；花柱长，柱头3。小坚果长圆状倒卵形，三棱状。花期6～8月，果期7～11月。\n" +
                             "\n" +
                             "【产地分布】生于山坡草地、耕地、路边及水边、湿地、河沟和沙地等。分布于江苏、安徽、浙江等地。\n" +
                             "\n" +
                             "【采收加工】秋季采挖，用火燎去须根及鳞叶，沸水略煮或蒸透后晒干，也可不经火燎或蒸煮直接晒干，均称“毛香附”；在放入竹笼中来回撞擦，或用竹筛去净灰屑及须毛，即为“光香附”。\n" +
                             "\n" +
                             "【药材性状】纺锤形，或略弯曲，长2～3.5厘米，直径0.5～1厘米。表面棕褐色或黑褐色，有不规则纵皱纹，并有明显而隆起的环节6～10个，节长2～6毫米，节上有众多朝向一方的棕色毛须，并残留根痕；去净毛须的较光滑，有细密的纵脊纹。质坚硬，蒸煮者断面角质样，棕黄色或棕红色，生晒者断面粉性，类白色；内皮层环明显，中柱色较深，维管束点清晰可见。气芳香特异，味微苦。\n" +
                             "\n" +
                             "【性味归经】性平，味辛、微苦、微甘。归肝经、三焦经。\n" +
                             "\n" +
                             "【功效与作用】行气解郁、调经止痛。属理气药。\n" +
                             "\n" +
                             "【临床应用】用量6～9克，煎汤内服。用治肝胃不和、气郁不舒、胸腹胁肋胀痛、痰饮痞满、月经不调、崩漏带下等。\n" +
                             "\n" +
                             "【药理研究】挥发油及醇提取物对大鼠有显著的镇痛和解热作用。有松弛平滑肌和雌激素样作用，有抗炎、保肝利胆等药理作用。还有有降血糖、降血脂作用；对胃黏膜有保护作用；可促进离体脂肪组织的分解；有抗病原微生物、抗氧化和细胞凋亡作用。\n" +
                             "\n" +
                             "【化学成分】含挥发油约1%，油中含有多种倍半萜及其氧化物，还含有少量单萜化合物和考布松等。另含α-香附酮、β-蒎烯、丁香烯、桉叶素、香附子烯、香附醇、胡萝卜苷、β-谷固醇、大黄素甲醚等成分。\n" +
                             "\n" +
                             "【使用禁忌】气虚无滞者慎服;阴虚、血热者禁服。\n" +
                             "\n" +
                             "【配伍药方】①治偏正头痛：川芎60克，香附(炒)120克。上为末。以茶调服，得腊茶清尤好。(《澹寮方》)\n" +
                             "\n" +
                             "②治吐血：香附(去毛)150克，甘草(锉，炙)30克。上二味，粗捣筛。每服6克，水一盏，煎取七分，去滓温服。(《圣济总录》香草汤)\n" +
                             "\n" +
                             "③治鼻衄：香附(为末)，妇人发(烧灰)，研匀，汤调方寸匕服。(《卫生易简方》)\n" +
                             "\n" +
                             "④治尿血：香附、地榆等分。各煎汤，先服香附汤三五呷，后服地榆汤。(《全生指迷方》)\n" +
                             "\n" +
                             "⑤安胎：香附(炒去毛)，为细末。浓煎紫苏汤调下3克。(《中藏经》铁罩散)");

                     chineseMedicineDao.insert(medicine194);

                     ChineseMedicine medicine195=new ChineseMedicine();
                     medicine195.setName("细辛");
                     medicine195.setSortType("X");
                     medicine195.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=5a86918f35d12f2eda08a6322eabbe07/0eb30f2442a7d933e9ff142bae4bd11373f00116.jpg");
                     medicine195.setData("【中药名】细辛 xixin\n" +
                             "\n" +
                             "【别名】白细辛、盆草细辛、金盆草、少辛、细草、独叶草。\n" +
                             "\n" +
                             "【英文名】AsariRadix Et Rhizoma。\n" +
                             "\n" +
                             "【来源】马兜铃科植物华细辛Asarum sieboldii Miq.的全草。\n" +
                             "\n" +
                             "【植物形态】多年生草本，根茎细长，节间密，叶1～2片，肾状心形，先端渐尖，上面散生短毛，下面仅叶脉散生较长的毛。花单生于叶腋，花被钟形或壶形，污紫色，花被质厚，筒部扁球形，顶端3裂，裂片平展，雄蕊12枚，花丝较花药长1.5倍，花柱6，蒴果肉质，近球形。花期5月，果期6月。\n" +
                             "\n" +
                             "【产地分布】生于海拔1200～2100米的林下阴湿处、山沟腐殖质厚的湿润土壤中。分布于河南、山东、安徽等地。\n" +
                             "\n" +
                             "【采收加工】夏季果熟期或初秋采挖，除去泥土，阴干。\n" +
                             "\n" +
                             "【药材性状】多数十棵扎成一把，常卷缩成团。根茎长5～20厘米，直径0.1～0.2厘米，节间长0.2～1厘米。根细长，密生节上，表面灰黄色，平滑或具纵皱纹，质脆易折断，断面黄白色，基生叶1～2，叶片较薄，心形，先端渐尖。花被裂片开展。果近球形。气味较弱。\n" +
                             "\n" +
                             "【性味归经】性温，味辛。归心经、肺经、肾经。\n" +
                             "\n" +
                             "【功效与作用】祛风、散寒、通窍止痛、温肺祛痰。属解表药下属分类的辛温解表药。\n" +
                             "\n" +
                             "【临床应用】用量1～3克，水煎服，外用研末撒、吹鼻或煎水含漱。用治风寒感冒，头痛，牙痛鼻塞鼻渊，风湿痹痛，痰饮喘咳。\n" +
                             "\n" +
                             "【药理研究】有局部麻醉作用，对关节炎有一定程度的抑制作用，还具有一定的抑菌作用，有增强脂质代谢及升高血糖的作用，调节机体平滑肌功能。挥发油能使麻醉动物血压下降，而煎剂则使血压上升；并具消炎和抗惊厥作用。还有镇痛、镇静、抑制发热、解热、抗组胺和抗变态反应和兴奋呼吸作用。\n" +
                             "\n" +
                             "【化学成分】含挥发油，主要成分甲基丁香酚、优香芹酮、黄樟醚、β-蒎烯、α-蒎烯、龙脑、异茴香醚、细辛酯素等。此外，尚含一种消旋-去钾乌药碱。微量元素有钾、钠、镁、钙、铁、锰、铜、锌等。另含山柰酚、山柰酚3-O-β-D-吡喃葡萄糖苷、γ-松油烯、β-松油烯、α-侧柏烯、细辛脑、(-)-细辛脂素等成分。\n" +
                             "\n" +
                             "【使用禁忌】气虚多汗者慎服，热病及阴虚、血虚者禁服。不宜与藜芦同用。本品服用剂量过大，可发生面色潮红、头晕、多汗，甚则胸闷、心悸、恶心、呕吐等副反应。\n" +
                             "\n" +
                             "【配伍药方】①治鼻塞，不闻香臭：细辛(去苗叶)、瓜蒂各0.3克。上二味，捣罗为散，以少许吹鼻中。(《圣济总录》)\n" +
                             "\n" +
                             "②治牙痛久不瘥：细辛(去苗叶)、荜茇，上二味等分，粗捣筛，每用3克，水一盏。煎十数沸，热漱冷吐。(《圣济总录》)\n" +
                             "\n" +
                             "③治牙痛：细辛、盆硝各3克，雄黄1.5克，牙皂二个。为末。用大蒜一枚，柞和为丸，梧桐子大每用一丸，绵裹之，如左牙疼塞左耳，右牙痛塞右耳。(《外科大成》)\n" +
                             "\n" +
                             "④治口舌生疮：细辛、黄连等分为末。先以布巾揩净患处，掺药在上，涎出即愈。(《卫生易简方》)\n" +
                             "\n" +
                             "⑤治口臭：细辛30克，甘草(炙微赤，锉)30克，桂心30克。上述药，捣细罗为散。每服不计时候，以熟水调下3克。(《圣惠方》细辛散)\n" +
                             "\n" +
                             "⑥治小儿口疮：细辛末，醋调，贴脐上。(《卫生家宝方》)\n" +
                             "\n" +
                             "⑦治蛇伤：细辛、白芷各15克，雄黄7.5克，为末，入麝香少许。每服6克，温酒调服。(《卫生易简方》)\n" +
                             "\n" +
                             "⑧治神经性皮炎：鲜细辛适量，洗净。捣烂成糊状，涂患处。每日2次。(《陕甘宁青中草药选》)");

                     chineseMedicineDao.insert(medicine195);

                     ChineseMedicine medicine196=new ChineseMedicine();
                     medicine196.setName("仙茅");
                     medicine196.setSortType("X");
                     medicine196.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C32%2C750%2C495%3Bc0%3Dbaike92%2C5%2C5%2C92%2C30/sign=990bca979f2bd407568889bd46b9b262/79f0f736afc379319a7ad296e3c4b74543a9117a.jpg");
                     medicine196.setData("【中药名】仙茅 xianmao\n" +
                             "\n" +
                             "【别名】地棕、独芋、仙茅参、独茅根、仙棕。\n" +
                             "\n" +
                             "【英文名】Common Curculigo Rhizome、Rhizome of Common Curculigo。\n" +
                             "\n" +
                             "【来源】石蒜科植物仙茅Curculigo orchioides Gaertn.的根茎。\n" +
                             "\n" +
                             "【植物形态】多年生草本。根茎延长，圆柱状，肉质，外皮褐色；根粗壮，肉质。地上茎不明显。叶狭披针形，先端渐尖基部下延成柄，再向下扩大呈鞘状，绿白色，边缘膜质；叶脉明显，有中脉；两面疏生长柔毛，后渐光滑。花腋生；花梗藏在叶鞘内；花杂性，上部为雄花，下部为两性花；苞片披针形，绿色，膜质，被长柔毛；花被下部细长管状，上部六裂，裂片披针形，内面黄色，外面白色，有长柔毛；雄蕊6，花丝短；子房狭长，被长柔毛。蒴果椭圆形，稍肉质，先端有喙，被长柔毛。种子稍呈球形，亮黑色，有喙，表面有波状沟纹。\n" +
                             "\n" +
                             "【产地分布】生于海拔1600米的林下草地或荒地上。分布于浙江、福建、江西等地。\n" +
                             "\n" +
                             "【采收加工】2～4月发芽前或7～9月苗枯萎时挖取根茎，洗净，除去须根和根头，晒干；或蒸后晒干。\n" +
                             "\n" +
                             "【药材性状】圆柱形，略弯曲，长3～10厘米，直径0.4～0.8厘米。表面黑褐色或棕褐色，粗糙，有纵轴沟及横皱纹与细孔状的粗根痕。质硬脆，易折断，断面平坦略呈角质状，淡褐色或棕褐色，近中心处色较深，并有一深色环。气微香，味微苦辛。\n" +
                             "\n" +
                             "【性味归经】性热，味辛，归肾经、肝经、脾经。\n" +
                             "\n" +
                             "【功效与作用】补肾阳、强筋骨、祛寒湿。属补虚药分类下的补阳药。\n" +
                             "\n" +
                             "【临床应用】用量3～9克，煎服；或入丸、散；或浸酒。用治阳痿精冷、小便失禁、崩漏、心腹冷痛、腰脚冷痹、痈疽、瘰疬等。\n" +
                             "\n" +
                             "【药理研究】药理研究表明，仙茅有雄激素样和适应原样作用，有抗衰老作用，并能增强免疫功能。仙茅有一定的毒性，一般可用米泔水浸泡后除去红水则毒减。近年来对其成分及作用有较多的研究，基本药理皆为了强壮作用，单用有效，亦入复方中用。另外还具有镇静抗惊厥、抗炎、雄性激素样、提高免疫功能、适应原样、提高下丘脑-垂体-性腺轴功能、提高Na+-K+-ATP酶活性、抗菌、降血糖、抗癌等作用。毒性很低。\n" +
                             "\n" +
                             "【化学成分】主要含多种环木菠萝烷型三萜及其糖苷、甲基苯酚、仙茅苷A、仙茅苷B、地衣二醇葡萄糖苷、仙茅萜醇、石蒜碱、胡萝卜苷和氯代甲基苯酚的多糖苷类。采用HPLC法测定仙茅中仙茅苷的含量。\n" +
                             "\n" +
                             "【使用禁忌】阴虚火旺者禁服，有小毒。\n" +
                             "\n" +
                             "【配伍药方】①治男子虚损，阳痿不举：仙茅(米泔浸去赤水，晒干)120克，淫羊藿(洗净)120克，五加皮120克。用绢袋装入，酒内浸入一月取饮。(《万氏家抄方》仙茅酒)\n" +
                             "\n" +
                             "②治阳痿，耳鸣：仙茅、金樱子根及果实各15克。炖肉吃。(《贵州草药》)\n" +
                             "\n" +
                             "③治老年遗尿：仙茅30克。泡酒服。(《贵州草药》)\n" +
                             "\n" +
                             "④治痈疽火毒，漫肿无头，色青黑者：仙茅不拘多少(连根须)煎，点水酒服;或以新鲜者捣烂敷之。有脓者溃，无脓者消。(《滇南本草》)\n" +
                             "\n" +
                             "⑤治鼻衄：仙茅、白茅根、踏地消各15克。煮猪精肉食。(《湖南药物志》)");
                     chineseMedicineDao.insert(medicine196);

                     ChineseMedicine medicine197=new ChineseMedicine();
                     medicine197.setName("西洋参");
                     medicine197.setSortType("X");
                     medicine197.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545813659&di=2beb3e07e751a5440829ff8436b91fca&imgtype=jpg&er=1&src=http%3A%2F%2Fyun.zhite.com%3A81%2Fzhiteinfo%2Fwp-content%2Fuploads%2F2016%2F09%2FXiyangshenli02.jpg");
                     medicine197.setData("【中药名】西洋参 xiyangshen\n" +
                             "\n" +
                             "【别名】花旗参、洋参、西洋人参。\n" +
                             "\n" +
                             "【英文名】Panacis Quinquefolii Radix。\n" +
                             "\n" +
                             "【来源】五加科植物西洋参Panax quinquefolium L.的根。\n" +
                             "\n" +
                             "【植物形态】多年生草本，高25～35厘米。根肉质，纺锤形，常分枝。茎直立，圆柱形，具纵条纹。掌状复叶，3年以上生者有3～5枚复叶轮生于茎顶。小叶通常5枚，近无柄，小叶片倒卵形、宽卵形至宽椭圆形，先端急尾尖，基部楔形，边缘具粗锯齿，上面叶脉有稀疏的刚毛。伞形花序单一，顶生，小花密集成圆球形，花萼绿色，钟状，花瓣5，绿白色，雄蕊5枚，与花瓣互生，雌蕊1枚，子房下位，花盘肉质。核果状浆果。\n" +
                             "\n" +
                             "【产地分布】原产于北美洲(加拿大及美国)，现我国东北三省、华北各省、陕西有较大量的栽培。\n" +
                             "\n" +
                             "【采收加工】秋季采挖，洗净，按参体大小分开干燥。干燥适宜温度为30～40℃，相对湿度应控制在60%以下，注意翻动及排潮。3周至1个月时间干透，按大、中、小分等。\n" +
                             "\n" +
                             "【药材性状】纺锤形、圆柱形或圆锥形，长3～12厘米，直径0.8～2厘米。表面浅黄褐色或黄白色，可见横向环纹及线状皮孑L，并有细密浅纵皱纹及须根痕。主根中下部有一至数条侧根，大多已折断。有的上端有根茎(芦头)，环节明显，茎痕(芦碗)圆形或半圆形，具不定根(芋]或已折断。体重，质坚实，不易折断，断面平坦，浅黄白色，略显粉性，皮部可见黄棕色点状树脂道，形成层环纹棕黄色，木部略呈放射状纹理。气微而特异，味微苦、甘。\n" +
                             "\n" +
                             "【性味归经】性凉，味甘、微苦。归心经、肺经、肾经。\n" +
                             "\n" +
                             "【功效与作用】补气养阴、清热生津。属补虚药下分类的补气药。\n" +
                             "\n" +
                             "【临床应用】用量3～6克。用于气虚阴亏、内热、咳喘、痰血、虚热烦倦、消渴、口燥咽干。治肠红：西洋参蒸桂圆。此外，还具抗疲劳、抗缺氧、抗休克、抗肿瘤和抗病毒作用。\n" +
                             "\n" +
                             "【药理研究】西洋参及人参皂苷Rb1对大脑皮层有镇静安定和中枢抑制作用；煎剂静脉注射能抗兔心律失常；皂苷静脉注射可抗兔心肌缺血；抑制血小板聚集和降低血液凝固性。还有改善记忆；对心血管系统有保护作用；增强机体抗应激能力；增强免疫功能；保护红细胞膜；对单纯疱疹病毒Ⅰ型感染细胞有保护作用；抗利尿等作用。\n" +
                             "\n" +
                             "【化学成分】主要含三萜皂苷，以人参皂苷Rb，的含量最高，具有以奥克梯醇为苷元的特征性成分假人参皂苷F11，另含人参皂苷R0、人参皂苷Rg1、人参皂苷Re等。\n" +
                             "\n" +
                             "【使用禁忌】不宜与藜芦同用。中阳衰微、胃有寒湿者忌服。\n" +
                             "\n" +
                             "【配伍药方】①治夏伤暑热，舌燥喉干，主生津润燥，敛气消烦：西洋参3克，麦冬9克，北五味子九粒。当茶饮。(《喉科金钥》生脉散)\n" +
                             "\n" +
                             "②治原因不明长期低热：西洋参3克，地骨皮6克，粉甘6克。同煎饮服。每剂浓煎2次，每日1剂。[《中西医结合杂志》1990，10(1)：14]\n" +
                             "\n" +
                             "③治顽固性盗汗：穞豆衣30克，西洋参3克。分别煎煮，合兑服，每日1剂。[《中西医结合杂志》1 990，10(1)：14]\n" +
                             "\n" +
                             "④治过度体力劳伤，疲乏难复：仙鹤草30克，红枣7枚，浓煎;另煎西洋参3克，合兑服。[《中西医结合杂志》1990，10(1)：14]\n" +
                             "\n" +
                             "⑤治食欲不振，体倦神疲：西洋参10克，白术10克，云芩10克。水煎服。[《大众中医药》1990，(3)：7]。");

                     chineseMedicineDao.insert(medicine197);

                     ChineseMedicine medicine198=new ChineseMedicine();
                     medicine198.setName("玄参");
                     medicine198.setSortType("X");
                     medicine198.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545831766&di=7fa53005181e73423029bddebce4bc55&imgtype=jpg&er=1&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20170810%2Ffeec53848f104dbfb8720c8cb914b342.jpeg");
                     medicine198.setData("【中药名】玄参 xuanshen\n" +
                             "\n" +
                             "【别名】元参、北玄参、黑参、山当归。\n" +
                             "\n" +
                             "【英文名】Scrophulariae Radix。\n" +
                             "\n" +
                             "【来源】玄参科植物北玄参Scrophularia buergeriana Miq. (S. oldhamiOliv.)的根。\n" +
                             "\n" +
                             "【植物形态】多年生草本，高60～120厘米。根肥大，圆柱形，下部常分枝，外皮灰褐色。茎直立，四棱形，有沟纹。下部的叶对生，上部的叶有时互生，均具柄，叶片卵形至长卵形，长5～12厘米，宽3.5～12厘米，先端尖，基部圆形或近截形，边缘具细锯齿。聚伞花序总花序紧缩成穗状，花序轴及花梗均被腺毛，花萼长2～3毫米，5裂几达基部，裂片近圆形，边缘膜质，花冠黄绿色，管部斜壶状，能育雄蕊4枚，退化雄蕊1枚，近圆形，贴生在花冠管上，子房上位，2室。蒴果卵形，长约6毫米。\n" +
                             "\n" +
                             "【产地分布】喜生于湿润土壤中。分布于黑龙江、吉林、辽宁、河北、内蒙古等地。\n" +
                             "\n" +
                             "【采收加工】于10～11月挖取根部，除去茎叶及泥土，剥脱子芽供留种栽培用，根部晒至半干且内部色变黑时，剪去芦头及须根，堆放3～4天(发汗)后，晒干或烘干。\n" +
                             "\n" +
                             "【药材性状】圆柱形，中部略粗或上粗下细，有的微弯似羊角，长6～20厘米，宽1～3厘米。表面灰褐色，有纵皱纹，有细根及细根痕。质坚实，难折断，断面略平坦，色黑，微有光泽。有焦糖气，味甘、微苦，以水浸泡，水呈黑色。以条粗壮、质坚实、断面色黑者为佳。\n" +
                             "\n" +
                             "【性味归经】性微寒，味甘、苦、咸。归肺经、胃经、肾经。\n" +
                             "\n" +
                             "【功效与作用】凉血滋阴、泻火解毒。属清热药下分类的清热凉血药。\n" +
                             "\n" +
                             "【临床应用】用量9～15克，内服煎汤，或入丸散，治疗热病伤阴、舌降烦渴、温毒发斑、津伤便秘、骨蒸劳嗽、目赤、咽痛、瘰疬、白喉、痈肿疮毒、高血压。外用捣敷或研末调敷。治咽喉连舌肿痛：玄参、射干、黄药各15克，水煎服。\n" +
                             "\n" +
                             "【药理研究】北玄参水浸、醇浸液灌服或注射给正常(猫、犬、兔)及肾型高血压犬均有降压作用，醇浸膏还能抗缺氧、抗心肌缺血、增加心肌营血量；水浸液对离体豚鼠支气管有明显的舒张作用，并能加强肾上腺素的作用。毒性：小鼠腹腔注射水煎剂的LD50为15.99～19.91克/千克。另具有解热、抗菌、保护心肌缺血、解痉、降血压、降血糖等作用。\n" +
                             "\n" +
                             "【主要成分】根中含哈巴苷(70%～80%)、8-邻甲基对香豆酰、哈巴苷，均系变黑的物质。另含哈巴俄苷、玄参三酯苷、玄参种苷、桃叶珊瑚苷、玄参环醚、京尼平苷、赛斯坦苷F、去咖啡酰毛蕊花糖苷、毛蕊花苷等。\n" +
                             "\n" +
                             "【使用禁忌】脾虚便溏或脾胃有湿者禁服。不宜与藜芦同用。\n" +
                             "\n" +
                             "【相关药方】①治口舌生疮，久不愈：玄参、天门冬(去心、焙)、麦门冬(去心、焙)各30克。捣罗为末，炼蜜和丸，如弹子大。每以绵裹一丸，含化咽津。(《圣济总录》玄参丸)\n" +
                             "\n" +
                             "②治鼻中生疮：用玄参，水渍软，塞鼻中，或为末涤之。(《卫生易简方》)\n" +
                             "\n" +
                             "③治夜卧口渴喉干：玄参二片含口中，即生津液。(《吉人集验方》)\n" +
                             "\n" +
                             "④治气虚血壅，小便赤浊，似血非血，似溺非溺，溺管疼痛：玄参、车前子各30克，水煎服。(《辨证录》玄车丹)\n" +
                             "\n" +
                             "⑤治因阴阳偏，火有余而水不足，遇事或多言则心烦，常感胸中扰壤，纷纭而嘈杂：玄参、麦冬各60克，水煎服。(《辨证录》玄冬汤)");

                     chineseMedicineDao.insert(medicine198);

                     ChineseMedicine medicine199=new ChineseMedicine();
                     medicine199.setName("相思子");
                     medicine199.setSortType("X");
                     medicine199.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545831918&di=55311129b2ead4c35d7a3abf5f61554c&imgtype=jpg&er=1&src=http%3A%2F%2Fimg0.114pifa.com%2Fg%2Fimg%2Fibank%2F2013%2F844%2F403%2F924304448_1644688428.jpg");
                     medicine199.setData("【中药名】相思子 xiangsizi\n" +
                             "\n" +
                             "【别名】红豆、云南豆子、相思豆。\n" +
                             "\n" +
                             "【英文名】Abri Semen。\n" +
                             "\n" +
                             "【来源】豆科植物相思子Abrus precatorius L.的种子。\n" +
                             "\n" +
                             "【植物形态】缠绕藤本。茎细长，稍木质化，疏生白色平伏毛。双数羽状复叶互生，小叶8~20对，具叶柄；总状花序腋生，花小，数朵簇生于花序轴的各个短枝上；花萼钟形，黄绿色，4齿裂，外被毛；花冠蝶形，淡紫色，旗瓣阔卵形，基部有三角状的爪；雄蕊9，合生成一束；子房上位，阔线形，被毛。荚果菱状长椭圆形，稍膨胀，黄绿色，被平伏毛，先端有弯曲的喙。种子1~6粒，椭圆形，上部鲜红色，基部靠近种脐部分黑色，有光泽。花期3~5月，果期5~6月。\n" +
                             "\n" +
                             "【产地分布】生于山坡、路旁、林下、灌丛中。分布于广东、广西、福建、云南、台湾等地。\n" +
                             "\n" +
                             "【采收加工】夏、秋季分批采摘成熟果荚，晒干，脱粒，扬净。\n" +
                             "\n" +
                             "【药材性状】长椭圆形略扁，少数近球形，长5~7毫米，直径3.5~4.5毫米。表面具光泽，一端约2/3处为朱红色，另一端为黑色，种脐凹陷，椭圆形类白色，位于黑色处的侧面。质坚硬，浸泡后除去种皮可见黄白色子叶2枚，肥厚。具青草气，味微苦涩。\n" +
                             "\n" +
                             "【性味归经】性平，味苦。归大肠经、胃经、心经。\n" +
                             "\n" +
                             "【功效与作用】催吐、杀虫、消肿。属杀虫止痒药。\n" +
                             "\n" +
                             "【临床应用】外用，不宜内服，以防中毒。外用捣烂涂敷患处。用治疥疮、顽癣、痈肿。\n" +
                             "\n" +
                             "【主要成分】含相思子三萜苷A、相思子三萜苷B、相思子三萜苷C、相思子三萜苷D，相思子凝集素.a、相思子凝集素-b，相思子毒蛋白I、相思子毒蛋白Ⅱ、相思子毒蛋白Ⅲ，相思子碱，相思子灵，相思豆碱，胆碱，胡卢巴碱，N，N-二甲基色氨酸甲酯的甲阳离子，下箴刺酮碱等，相思子凝集素有很强的致红细胞凝集作用。有抗肿瘤、抗过敏、抗组胺等作用。\n" +
                             "\n" +
                             "【使用禁忌】本品有毒，不宜内服。");

                     chineseMedicineDao.insert(medicine199);

                     ChineseMedicine medicine200=new ChineseMedicine();
                     medicine200.setName("西青果");
                     medicine200.setSortType("X");
                     medicine200.setMedicineImageUrl("http://fx120.120askimages.com/120ask_news/2017/0326/201703261490510892562773.jpeg");
                     medicine200.setData("【中药名】西青果 xiqingguo\n" +
                             "\n" +
                             "【别名】西藏青果、藏青果。\n" +
                             "\n" +
                             "【英文名】Chebulae Fructus Immaturus。\n" +
                             "\n" +
                             "【来源】使君子科植物诃子Terminalia chebuZa Retz.的干燥幼果。\n" +
                             "\n" +
                             "【植物形态】大乔木，高达20～30米。叶互生或近对生，近革质，椭圆形或卵形，长2～16厘米，宽3～8厘米，两面近无毛或幼时下面有微毛，叶柄长1.5～3厘米，多少有锈色短柔毛，有时近顶端有2腺体。圆锥花序顶生，由数个穗状花序组成，花序轴有毛，苞片条形，花两性，无梗，花萼杯状，长约2毫米，5裂三角形，外面无毛，内面有棕黄色长毛，无花瓣，雄蕊10，子房下位，上室，有毛或变无毛。核果椭圆形或近卵形，形如橄榄，长2.5～3.5厘米，宽2～2.5厘米，熟时黑色，通常有钝棱5～6条。\n" +
                             "\n" +
                             "【产地分布】生于海拔800～1540米的疏林中，或阳坡林缘。分布于广东、海南、广西、云南等地。\n" +
                             "\n" +
                             "【采收加工】9～10月摘取未成熟的幼果或采收被风吹落的幼果，沸水中略煮烫取出晒干或烘干。\n" +
                             "\n" +
                             "【药材性状】干燥幼果呈扁长卵形，略似橄榄，下部有果柄痕，外表黑褐色，有明显的纵皱纹。质坚硬，断面不平坦，有胶质样光泽，果肉厚，黄绿色，核不明显，稍空心，小者黑褐色，无空心。气无，味苦涩，微甘。\n" +
                             "\n" +
                             "【性味归经】性平，味苦、微甘、涩。归肺经、大肠经。\n" +
                             "\n" +
                             "【功效与作用】清热生津、利咽解毒。属清热药下属分类的清热解毒药。\n" +
                             "\n" +
                             "【临床应用】用量3~9克，内服煎汤；咽喉肿痛用本品2~3枚泡水服。用治慢性咽喉炎、声音嘶哑、咽喉干燥。\n" +
                             "\n" +
                             "【主要成分】含鞣质20%～40%。其中主要成分为诃子酸，诃黎勒酸，并含鞣云实素，原诃子酸，鞣花酸及没食子酸等。此外，尚含毒八角酸，奎尼酸，糖类及氨基酸等。\n" +
                             "\n" +
                             "【使用禁忌】风火喉痛及中寒者忌用。\n" +
                             "\n");
                     chineseMedicineDao.insert(medicine200);

                     ChineseMedicine medicine201=new ChineseMedicine();
                     medicine201.setName("霞草");
                     medicine201.setSortType("X");
                     medicine201.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike220%2C5%2C5%2C220%2C73/sign=2c847f1338a85edfee81f671283d6246/7e3e6709c93d70cfab72bc4ef4dcd100bba12bec.jpg");
                     medicine201.setData("【中药名】霞草 xiacao\n" +
                             "\n" +
                             "【别名】银柴胡、山蚂蚱。\n" +
                             "\n" +
                             "【英文名】暂缺。\n" +
                             "\n" +
                             "【来源】石竹科植物霞草Gypsophila oldhamiana Miq.的根。\n" +
                             "\n" +
                             "【植物形态】多年生草本，高70～120厘米。全株无毛，粉绿色；叶长圆状披针形，有纵脉3条；聚伞花序顶生，花萼钟状，裂片5。花瓣5，白色，先端2裂；雄蕊10；子房上位，花柱3，丝状。蒴果近球形，熟时先端6裂。种子卵形，微扁。花期6～7月，果期8～9月。\n" +
                             "\n" +
                             "【产地分布】多生于山地草原。分布于华北、东北、山西、甘肃、山东、河南等地。\n" +
                             "\n" +
                             "【采收加工】秋冬季采挖根部，除去残茎及须根，洗净，晒干。\n" +
                             "\n" +
                             "【药材性状】根呈圆柱形，圆锥形，扭曲不直，直径2～4厘米。根头部常有分叉，中上部有众多的疣状突起及支根痕。表面灰棕色或棕黄色，有粗而扭曲的纵沟纹。外皮多已除去，但纵皱的凹陷处有残余而形成黄白相间的纹理。体重，质硬，不易折断，断面中心黄白色，可见黄白相间排列成2～3圈断续的环纹(异型维管束)。气微，味苦涩，麻舌。\n" +
                             "\n" +
                             "【性味归经】性微寒，味甘、辛。归肝经。\n" +
                             "\n" +
                             "【功效与作用】清热凉血。属清热药下属分类的清热凉血药。\n" +
                             "\n" +
                             "【临床应用】用量3～9克，水煎服。在山东作银柴胡用，主治阴虚内热、虚劳骨蒸。在东北、河南等地则代商陆作利尿药用。\n" +
                             "\n" +
                             "【主要成分】根含皂苷，其苷元为丝石竹皂苷元( gypsogenin)。\n" +
                             "\n" +
                             "【使用禁忌】尚不明确。");
                     chineseMedicineDao.insert(medicine201);

                     ChineseMedicine medicine202=new ChineseMedicine();
                     medicine202.setName("小叶莲");
                     medicine202.setSortType("X");
                     medicine202.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545832634&di=9e0f53daaa41f0bd4c73a883ef3c0551&imgtype=jpg&er=1&src=http%3A%2F%2Fs8.sinaimg.cn%2Fbmiddle%2F5d5112bdta0edc748f0b7%26amp%3B690");
                     medicine202.setData("【中药名】小叶莲 xiaoyelian\n" +
                             "\n" +
                             "【别名】鸡素苔、铜筷子、桃耳七。\n" +
                             "\n" +
                             "【英文名】Sinopodophmlli Fructus。\n" +
                             "\n" +
                             "【来源】小檗科植物桃儿七Podophyllum hexandrum Royle的成熟果实。\n" +
                             "\n" +
                             "【植物形态】多年生草本，高50~60厘米。根茎粗壮，节上着生多数淡褐色或红褐色马尾状根，长可达15厘米。茎直立，单一，有纵向条纹，基部有2~4个膜质鞘。叶2~3枚，生于茎顶，有长柄，似茎的分枝；叶近圆形，3～5深裂，几裂至基部，裂片常再次分裂至中部；基部心形，边缘有疏锯齿。花单生，先叶开放；着生于叶柄的交叉处或稍上方，花梗长达5厘米；萼片6，早落；花瓣6，倒卵状长圆形，淡粉红色，2轮，外轮花瓣较大，先端圆，基部渐狭，边缘波状；雄蕊6枚，花药长圆形，花丝基部加宽；子房近圆形，几无花柱，柱头盾状。浆果卵圆形，下垂，熟时红色。种子多数，黑色。花期5～6月，果期7~9月。\n" +
                             "\n" +
                             "【产地分布】生于高山草丛中、林下或灌丛中。分布于陕西、甘肃、宁夏、青海、湖北等地。\n" +
                             "\n" +
                             "【采收加工】春秋季挖取，除去泥土，晒干。\n" +
                             "\n" +
                             "【药材性状】果实呈椭圆形或近圆形，多压扁，长3~5.5厘米，直径2～4厘米。表面紫红色或紫褐色，皱缩，有的可见露出的种子。顶端稍尖，果梗黄棕色，多脱落。果皮与果肉粘连成薄片，易碎，内具多数种子。种子近卵形，长约4毫米；表面红紫色，具细皱纹，一端有小突起；质硬；种仁白色，富油性。气微，味酸甜、涩；种子味苦。\n" +
                             "\n" +
                             "【性味归经】性平，味甘。归经无。\n" +
                             "\n" +
                             "【功效与作用】调经活血。属活血化瘀药下属分类的活血调经药。\n" +
                             "\n" +
                             "【临床应用】用量3~9克，多入丸散服。用于血瘀经闭，难产，死胎、胎盘不下。\n" +
                             "\n" +
                             "【主要成分】含木脂素类、黄酮类及皂苷、鞣质和多糖等成分。木脂素类成分主要为鬼臼脂素，黄酮类成分主要为槲皮素和山柰酚等。药理试验结果表明，小叶莲含有的鬼臼脂素有抗癌作用、抗单纯性疱疹病毒和免疫抑制、抗生育等作用，但毒性较大。而黄酮部分毒性较小，有镇咳，平喘、祛痰、抑菌作用。\n" +
                             "\n" +
                             "【使用禁忌】有小毒。");

                     chineseMedicineDao.insert(medicine202);

                     ChineseMedicine medicine203=new ChineseMedicine();
                     medicine203.setName("缬草");
                     medicine203.setSortType("X");
                     medicine203.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=07acf8b0b6fd5266b3263446ca71fc4e/f31fbe096b63f624ed07cd588744ebf81a4ca317.jpg");
                     medicine203.setData("【中药名】 缬草 xiecao\n" +
                             "\n" +
                             "【别名】满山香、拔地麻、甘松、五里香、小救驾。\n" +
                             "\n" +
                             "【英文名】Rhizoma Et Radix Valerianae。\n" +
                             "\n" +
                             "【来源】败酱科植物缬草Valeriana officinalisL.的根茎及根。\n" +
                             "\n" +
                             "【植物形态】多年生草本，高1～2米。根茎短，簇生多数须根，有特异香气。茎直立，有纵棱，基部、节上被粗毛。基生叶丛生，有长柄，柄基稍宽呈鞘状，早枯；茎生叶对生，羽状全裂，裂片3～15，披针形，全缘或疏生2～3浅齿，叶柄短或无。伞房状三出聚伞圆锥花序；花萼退化；花冠初时淡粉红色，后变白色，冠简短，基部一侧有小距状突起，先端5裂；雄蕊3，外露；子房下位，3室，仅l室发育。瘦果扁卵形，顶端约具12条羽状冠毛。花果期6～8月。\n" +
                             "\n" +
                             "【产地分布】分布于河北、东北、山东、湖南、湖北等地。多生于山地林缘、山坡、河岸及灌丛中。少数地区有栽培。\n" +
                             "\n" +
                             "【采收加工】秋季采挖根及根茎，洗净晒干。\n" +
                             "\n" +
                             "【药材性状】根茎短粗，顶端留存茎基及纤维状叶柄残基。根多数，圆柱形，长4～15厘米，直径0.2～0.4厘米，表面灰棕色或黄棕色，有细纵皱。质坚实而脆，易折断，断面白色或灰黄色，中央絮状而疏松，中央有淡褐色的木心。气特异，味微苦、辛。\n" +
                             "\n" +
                             "【性味归经】性平，味苦，微甘。归心经、肝经。\n" +
                             "\n" +
                             "【功效与作用】具有宁心安神的功效。属安神药下属分类的养心安神药。\n" +
                             "\n" +
                             "【临床应用】用量3～6克，水煎服。临床多用于治疗神经衰弱、失眠和癔病及其情绪激动。\n" +
                             "\n" +
                             "【主要成分】根含挥发油0.5%~2%，油中含异戊酸龙脑酯( bornylisovalerate)、乙酸龙脑酯(bornyl acetate)等。另含缬草碱(valerine)、缬草恰碱( chatinine)等生物碱类成分以及缬草素(valepotriatium)等。现代药理实验结果证实了缬草具有镇静安定及降温的作用。此外还证实缬草具有短暂的降压作用和保肝作用。\n" +
                             "\n" +
                             "【使用禁忌】体弱阴虚者慎用。");
                     chineseMedicineDao.insert(medicine203);
                     ChineseMedicine medicine204=new ChineseMedicine();
                     medicine204.setName("荨麻");
                     medicine204.setSortType("X");
                     medicine204.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=97708a743cadcbef15397654cdc645b8/dcc451da81cb39dbf12fd4e6dd160924aa183096.jpg");
                     medicine204.setData("【中药名】荨麻 xunma\n" +
                             "\n" +
                             "【别名】螫麻子、小荨麻、哈拉海。\n" +
                             "\n" +
                             "【英文名】Herba Urticae。\n" +
                             "\n" +
                             "【来源】荨麻科植物麻叶荨麻Urtica cannabina L.的全草。\n" +
                             "\n" +
                             "【植物形态】多年生草本。茎高达150厘米，有棱，生螫毛和紧贴的微柔毛，叶对生，叶片轮廓五角形，3深裂或3全裂，1回裂片再羽状深裂，两面疏生短柔毛，下面疏生螫毛，托叶离生，狭三角形。雌雄同株或异株，同株者雄花序生于下方。雄花序多分枝；雄花花被片4，雄蕊4枚；雌花花被片4深裂，花后增大，包着果实，有短柔毛和少数螫毛，柱头画笔头状。瘦果扁卵形，灰褐色，光滑。花期7~8月，果期8~9月。\n" +
                             "\n" +
                             "【产地分布】生于林缘、沟边、路旁等处。分布于黑龙江、吉林、辽宁、内蒙古等地。\n" +
                             "\n" +
                             "【采收加工】夏、秋季采收，鲜用或晒干备用。\n" +
                             "\n" +
                             "【药材性状】茎有棱，生螫毛和紧贴的微柔毛，叶对生，叶片轮廓五角形，长4~ 12厘米，宽3.5~12厘米，3深裂或3全裂，1回裂片再羽状深裂，两面疏生短柔毛，下面疏生螫毛，托叶离生，狭三角形。\n" +
                             "\n" +
                             "【性味归经】性温，味苦、辛。有小毒。归经未知。\n" +
                             "\n" +
                             "【功效与作用】祛风定惊、消食通便。属平肝息风药下属分类的息风止痉药。\n" +
                             "\n" +
                             "【临床应用】用于风湿关节痛、产后抽风、小儿惊风、小儿麻痹后遗症、高血压、消化不良、大便不通；外用治荨麻疹初起、蛇咬伤。煎汤内服，用量3～9克，外用适量，捣汁外搽或煎水洗患处。\n" +
                             "\n" +
                             "【主要成分】全草含多种维生素、鞣质；茎皮含蚁酸、丁酸及有刺激作用的酸性物质等。\n" +
                             "\n" +
                             "【使用禁忌】服用不当易造成上吐下泻。");
                     chineseMedicineDao.insert(medicine204);

                     ChineseMedicine medicine205=new ChineseMedicine();
                     medicine205.setName("溪黄草");
                     medicine205.setSortType("X");
                     medicine205.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=0f401baca9014c080d3620f76b12696d/d4628535e5dde711768bd5f3a7efce1b9d16615e.jpg");
                     medicine205.setData("【中药名】溪黄草 xihuangcao\n" +
                             "\n" +
                             "【别名】熊胆草、血风草、溪沟草。\n" +
                             "\n" +
                             "【英文名】Herba Isodi Lophanthoidis。\n" +
                             "\n" +
                             "【来源】唇形科植物线纹香茶菜Isodon iophanthoides (Buch-Ham. ex D.Don)H. Hara.的全草。\n" +
                             "\n" +
                             "【植物形态】多年生纤弱草本，下部常匍匐生根，并有球状块根。茎方柱形，具4沟槽，被短柔毛。叶对生，纸质，卵圆形或阔卵形，揉之有黄色液汁，顶端钝，基部楔尖、阔楔尖、圆形或微心形，边缘有圆锯齿，两面被分节的短毛，下面密生褐色腺点，上部叶的叶柄短。花白色或粉红色，两性或杂性，排成顶生的圆锥花序，末次分枝呈蜴尾状弯卷；苞片生于花序下部的呈叶状，生于花序上部的小，花萼钟状，外面疏生念珠状长柔毛和很密的红褐色腺点，萼檐齿状5裂，二唇形，上唇3齿较小；花冠疏生黄色腺点，冠管直，檐部二唇形，上唇反折，4圆裂，下唇阔卵圆形，扁平；雄蕊和花柱均长伸出。小坚果卵状长椭圆形。秋季开花。\n" +
                             "\n" +
                             "【产地分布】生于山坡、路旁、田边、溪旁、河岸及灌丛中。分布于福建、台湾、广东、广西、浙江等地。\n" +
                             "\n" +
                             "【采收加工】全年可收割2~3次，鲜用或晒干。\n" +
                             "\n" +
                             "【药材性状】全草青灰色。茎四棱形，被短毛。叶对生，多皱缩，完整叶片润湿展平后呈卵状椭圆形，先端尖，基部楔形，边缘有粗锯齿，叶脉背面明显.有短毛，纸质，水浸后以手指揉之，手指可被染成黄色。老株常见枝顶有聚伞花序。气微，味微甘、微苦。\n" +
                             "\n" +
                             "【性味归经】性寒，味苦。归肝经、胆经、大肠经。\n" +
                             "\n" +
                             "【功效与作用】清热利湿、凉血散瘀。属清热药下属分类的清热燥湿药。\n" +
                             "\n" +
                             "【临床应用】用量15~30克，煎服；鲜品加倍。现多用治黄疸型肝炎及胆囊炎，无黄疸型肝炎也可治疗；痢疾、肠炎、癃闭、小便不利、跌打瘀肿。\n" +
                             "\n" +
                             "【主要成分】主含黄酮苷、酚类、氨基酸、有机酸等。\n" +
                             "\n" +
                             "【使用禁忌】脾胃虚寒者慎服。\n" +
                             "\n");
                     chineseMedicineDao.insert(medicine205);
                     ChineseMedicine medicine206=new ChineseMedicine();
                     medicine206.setName("小飞扬草");
                     medicine206.setSortType("X");
                     medicine206.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=dc48a633c511728b24208470a995a8ab/908fa0ec08fa513d2bdf457c356d55fbb2fbd91e.jpg");
                     medicine206.setData("【中药名】小飞扬草 xiaofeiyangcao\n" +
                             "\n" +
                             "【别名】乳汁草、痢疾草、细叶飞扬草。\n" +
                             "\n" +
                             "【英文名】Thymifoious Euphorbia Herb\n" +
                             "\n" +
                             "【来源】大戟科植物千根草Euphorbia thymifoliaL.的全草。\n" +
                             "\n" +
                             "【植物形态】一年生草本，全草有白色乳汁，茎匍匐，多分枝，通常红色。叶为单叶，对生，椭圆形或矩圆形，先端钝圆，基部偏斜，边缘有极细的锯齿，稀全缘，两面被稀疏的短柔毛，稀无毛；叶柄长约1毫米；托叶披针形或线形，边缘毛状撕裂。花单性，雌雄同株；杯状聚伞花序单生或少数簇生于叶腋内；总苞陀螺状，顶端5浅裂，裂片内面被贴伏的柔毛；腺体4枚，漏斗状，有短柄及极小的白色花瓣状附属物；雄花少数；雌花的子房上位，3室，花柱离生，顶端2裂。蒴果卵状三棱形，被短柔毛；种子长圆状四棱形，具纵沟纹4~6条。花期夏季。\n" +
                             "\n" +
                             "【产地分布】生于园地、路边或山坡草地湿润的沙质土上。分布于广东、海南、广西、福建、台湾等地。\n" +
                             "\n" +
                             "【采收加工】夏、秋季采收，鲜用或晒干。\n" +
                             "\n" +
                             "【药材性状】根小，茎细长，红棕色，稍被毛，质稍韧，中空。叶对生，多皱缩，灰绿色或稍带紫色。花序生于叶腋，花小，干缩。有的带有三角形的蒴果。\n" +
                             "\n" +
                             "【性味归经】性凉，味酸、涩。归经无。\n" +
                             "\n" +
                             "【功效与作用】清热、解毒、利湿、止痒。属清热药下属分类的清热解毒药。\n" +
                             "\n" +
                             "【临床应用】用量15~50克，煎服；鲜品50~100克；或捣汁煎。外用：捣敷或煎水洗。用治痢疾、肠炎；过敏性皮炎、湿疹、皮肤瘙痒，煎水洗；乳痈，捣烂外敷。\n" +
                             "\n" +
                             "【主要成分】地上部分主含蒲公英赛醇、二十六烷醇等；全草又含生物碱、可水解鞣质成分。\n" +
                             "\n" +
                             "【使用禁忌】尚不明确。");
                     chineseMedicineDao.insert(medicine206);

                     ChineseMedicine medicine207=new ChineseMedicine();
                     medicine207.setName("仙人掌");
                     medicine207.setSortType("X");
                     medicine207.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=4959497afd1f4134f43a0d2c4476feaf/9345d688d43f87944b859cb7d21b0ef41bd53a35.jpg");
                     medicine207.setData("【中药名】仙人掌 xianrenzhang\n" +
                             "\n" +
                             "　　【别名】龙舌、霸王、火掌、仙巴掌。\n" +
                             "\n" +
                             "　　【英文名】Herba Opuntiae。\n" +
                             "\n" +
                             "　　【来源】仙人掌科植物仙人掌Opuntia dillenii (Ker-Gaur.) Haw.的地上部分或全株。\n" +
                             "\n" +
                             "　　【植物形态】多年生肉质植物，常丛生，灌木状。茎下部稍木质，近圆柱形，上部有分枝，具节；茎节扁平，倒卵形至长圆形，幼时鲜绿色，老时变蓝绿色，有时被白粉，其上散生小瘤体，每一瘤体上簇生数条针刺和多数倒生短刺毛，针刺黄色，杂以黄褐色斑纹。叶退化成钻状，早落。花单生或数朵聚生于茎节顶部边缘，鲜黄色；花被片多数，外部的带绿色，向内渐变为花瓣状，广倒卵形；雄蕊多数，排成数轮，花药2室；子房下位，1室，花柱粗壮，柱头白色。浆果多汁，倒卵形或梨形，紫红色。种子多数。花期5~6月。\n" +
                             "\n" +
                             "　　【产地分布】生于沿海沙滩的空旷处，向阳干燥的山坡、石上、路旁或村庄。分布于我国西南、华南以及浙江、福建、江西等地。\n" +
                             "\n" +
                             "　　【采收加工】栽培1年后即可随用随采。\n" +
                             "\n" +
                             "　　【药材性状】以全株入药(刺除外)。四季可采。鲜用或切片晒干。\n" +
                             "\n" +
                             "　　【性味归经】性寒，味苦。归胃经。\n" +
                             "\n" +
                             "　　【功效与作用】行气活血、凉血止血、解毒消肿。属清热药下属分类的清热解毒药。\n" +
                             "\n" +
                             "　　【临床应用】用量10~30克，煎服；或3~6克，焙干研末；或捣汁。外用鲜品适量捣敷。主治胃痛、痞块、痢疾、喉痛、肺热咳嗽、肺痨咯血、吐血、痔血、疮疡、乳痈、痄腮、癣疾、蛇虫咬伤、烫伤和冻伤。\n" +
                             "\n" +
                             "　　【主要成分】全草含无羁萜酮、无羁萜一3a-醇、蒲公英赛酮和蒲公英赛醇等成分。具降血糖、抗胃溃疡、抗炎、镇痛、抗应激等作用。此外，还能延缓衰老。\n" +
                             "\n" +
                             "　　【使用禁忌】虚寒者忌用，《岭南杂记》：其汁入目，使人失明。");

                     chineseMedicineDao.insert(medicine207);

                     ChineseMedicine medicine208=new ChineseMedicine();
                     medicine208.setName("寻骨风");
                     medicine208.setSortType("X");
                     medicine208.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=2bee2433aaec08fa320d1bf538875608/e1fe9925bc315c60e845eb6387b1cb134854776e.jpg");
                     medicine208.setData("【中药名】寻骨风 xungufeng\n" +
                             "\n" +
                             "【别名】白毛藤、清骨风、猫耳朵。\n" +
                             "\n" +
                             "【英文名】Wooly Datchmanspipe Herb, Herb of Wooly Datchmanspipe。\n" +
                             "\n" +
                             "【来源】马兜铃科植物绵毛马兜铃Aristolochia mollissima Hance的全草。\n" +
                             "\n" +
                             "【植物形态】多年生攀援草本，全株密被白色绵毛。根细圆柱形，棕黄色。茎细长，常攀附他物。叶互生，有长柄；叶片卵状心形或卵圆形，全缘，两面均被白色绵毛，下面更多。叶腋开花，花单生，花下有一叶苞片，花被管弯曲成烟斗状，先端向一侧展开成片状；黄色，中央紫色；雄蕊6，与花柱合生，药无柄，2室；子房下位，6室，花柱短。蒴果成熟时开裂；花期5月。\n" +
                             "\n" +
                             "【产地分布】生于海拔100~850米的山坡、草丛、沟边或路旁灌丛中。分布于江苏、浙江、安徽等地。\n" +
                             "\n" +
                             "【采收加工】宜于夏、秋季，或在5月开花前采收，连根挖出，晒干。\n" +
                             "\n" +
                             "【药材性状】参见【植物形态】项。以根茎红棕色者为佳。\n" +
                             "\n" +
                             "【性味归经】性平，味辛、苦。归肝经、胃经。\n" +
                             "\n" +
                             "【功效与作用】祛风通络、行气止痛。属祛风湿药分类下的祛风湿强筋骨药。\n" +
                             "\n" +
                             "【临床应用】用量10~15克；或浸酒。用治风湿痹痛、肢体麻木、筋骨拘挛、跌打损伤疼痛、胃痛、牙痛、疝痛等。\n" +
                             "\n" +
                             "【主要成分】所含的挥发性油和生物碱对大鼠甲醛性或蛋清性关节炎有明显的消肿及预防作用；对风湿性、类风湿性关节炎有明显止痛、改善关节功能的作用；本品全草粉末混于饲料中喂食小鼠，对铂氏腹水癌及腹水总细胞数均有明显的抑制作用，对铂氏癌皮下型瘤亦有明显抑制效果，煎剂内服亦有效。\n" +
                             "\n" +
                             "【使用禁忌】阴虚内热者禁用。\n" +
                             "\n");

                     chineseMedicineDao.insert(medicine208);

                     ChineseMedicine medicine209=new ChineseMedicine();
                     medicine209.setName("小檗皮");
                     medicine209.setSortType("X");
                     medicine209.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545833592&di=333fe34d9d516169c580ef61b040fa50&imgtype=jpg&er=1&src=http%3A%2F%2Fimg4.ph.126.net%2F98nCa12BU2bJ0SR3kJ0-AA%3D%3D%2F1033013164545791954.jpg");
                     medicine209.setData("【药名】小檗皮 xiǎo bò； pí；\n" +
                             "\n" +
                             "【别名】东北小檗、狗奶子、刀口药。\n" +
                             "\n" +
                             "【来源】小檗科植物黄栌木Berberis amurensis Rupr.的根皮或茎皮。\n" +
                             "\n" +
                             "【植物形态】落叶灌木，高2～4米。老枝灰色，圆柱形，微有棱槽，幼枝灰黄色，叶刺3叉，紫红色，长2～4厘米。叶丛生刺腋，长椭圆形，倒卵状矩圆形或卵形，长5～10厘米，宽2.5～5厘米，先端急尖或钝圆，基部渐狭，下延成柄，边缘密生不规则刺毛状小锯齿，下面有时被白粉，羽状网状脉明显。总状花序下垂，有花10～25朵，淡黄色，苞片1，披针形，小苞片2，三角形；萼片6，花瓣状；花瓣6，长卵形，较花萼稍短，顶端微凹，基部有长圆形腺体1对；雄蕊6，药瓣裂；子房上位，卵圆形，柱头头状，扁平。浆果椭圆形，熟时鲜红色，被淡蓝色粉。种子2，紫褐色。花期4～5月，果期9～10月。\n" +
                             "\n" +
                             "【产地分布】生于山地林缘、溪边或灌丛中。分布于陕西、山西、河北、山东、内蒙古、辽宁、吉林和黑龙江等地。\n" +
                             "\n" +
                             "【采收加工】春、秋季采挖根，洗净切片，晒干。\n" +
                             "\n" +
                             "【药材性状】呈类圆柱形，稍弯曲，有少数分枝，长短粗细不一。表面灰棕色，有细皱纹，栓皮易剥落。质坚硬，不易折断。折断面纤维性，鲜黄色，切断面近圆形或长圆形，有略呈放射状的纹理；髓小，黄白色。气微，味苦。\n" +
                             "\n" +
                             "【性味归经】性寒，味苦。\n" +
                             "\n" +
                             "【功效与作用】清热燥湿，泻火解毒。药理试验结果表明，三棵针具有提高白细胞水平的作用，另还有降压、抗心律失常等作用。\n" +
                             "\n" +
                             "【临床应用】用量9～15克，水煎服。临床常用于治疗痢疾、肠炎、黄疸、咽痛、上呼吸道感染、目赤、急性中耳炎等症。\n" +
                             "\n" +
                             "【主要成分】全株含生物碱，其中有小檗碱、巴马亭、药根碱、古伦胺碱、木兰花碱等。根皮因含大量上檗碱，所以根皮也可供药用。\n" +
                             "\n" +
                             "【使用禁忌】暂缺。");

                     chineseMedicineDao.insert(medicine209);

                     ChineseMedicine medicine210=new ChineseMedicine();
                     medicine210.setName("雪上一枝蒿");
                     medicine210.setSortType("X");
                     medicine210.setMedicineImageUrl("http://image04.71.net/image04/10/33/69/20/16e771cb-edf2-477d-86d1-3463ecce0074.jpg");
                     medicine210.setData("【药名】雪上一枝高 xuě shà；ng yī zhī gāo\n" +
                             "\n" +
                             "【别名】铁棒锤、铁牛七。\n" +
                             "\n" +
                             "【英文名】Aconitum, pendulum Busch.\n" +
                             "\n" +
                             "【来源】毛茛科植物铁棒锤的块根。\n" +
                             "\n" +
                             "【植物形态】多年生草本，块根长圆柱形或长圆锥形。茎直立，下部无毛，上部密被短柔毛。叶互生，茎下部叶果期枯落，茎生叶密生于中部以上，有短柄或近无柄，叶片3深裂，裂片再2~3回羽状深裂，小裂片线形至披针状线形，两面均无毛。总状花序顶生；花序轴密生伸展的黄色短柔毛；花蓝紫色，盔瓣船状镰形，淡黄绿色，稀紫色或带紫色。侧瓣宽倒卵形，下片斜长圆形，外面被短柔毛；蜜叶2，无毛，有长爪，距短小；花丝下部被疏柔毛；心皮5，密被开展的长柔毛。花期8~9月，果期9~10月。\n" +
                             "\n" +
                             "【生境分布】生于高山山坡草丛或林边。分布于陕西、四川、云南等地。\n" +
                             "\n" +
                             "【采收加工】秋末冬初采挖，除去须根及泥沙，晒干。\n" +
                             "\n" +
                             "【药材性状】圆锥形至纺锤状圆柱形，顶端有茎残基或根痕，长2。5厘米，直径0.5~1.5厘米。表面暗棕色或黑棕色，多数平滑或稍有纵皱纹及侧根痕。质硬，断面白色，有粉性，少数为角质样，黄色。气微，味麻舌。\n" +
                             "\n" +
                             "【性味归经】苦、辛，温。有大毒。\n" +
                             "\n" +
                             "【功效与作用】祛风、镇痛。动物实验表明，注射液具有镇痛作用，临床上用注射液局部或穴位注射治疗腰肌劳损、坐骨神经痛等有一定疗效，泡酒外擦对跌扑损伤或风湿关节疼痛有效。\n" +
                             "\n" +
                             "【临床应用】用量25~50毫克，极量一次70毫克，煎服或入丸散，日服1次。用治风湿疼痛、跌扑损伤。本品剧毒，多外用，应在医生指导下服用。\n" +
                             "\n" +
                             "【主要成分】含雪乌碱甲、雪乌碱乙、雪乌碱丙、雪乌碱丁等生物碱。\n" +
                             "\n" +
                             "【使用禁忌】\n" +
                             "\n");
                     chineseMedicineDao.insert(medicine210);

                     ChineseMedicine medicine211=new ChineseMedicine();
                     medicine211.setName("暴马丁香");
                     medicine211.setSortType("B");
                     medicine211.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=83a2db02740e0cf3b4fa46a96b2f997a/37d3d539b6003af32977aa70332ac65c1138b6d4.jpg");
                     medicine211.setData("【中药名】暴马丁香 baomadingxiang\n" +
                             "\n" +
                             "【别名】暴马子皮、白丁香、棒棒木、荷花丁香。\n" +
                             "\n" +
                             "【英文名】Syringae Cortex\n" +
                             "\n" +
                             "【药用部位】木犀科植物暴马丁香Syringa reticulate (Blume.)Hara var.mandshurica (Maxim.)Hara的树皮。\n" +
                             "\n" +
                             "【植物形态】灌木或小乔木。高3～8米。树皮暗灰褐色，有横纹，小枝灰褐色，皮孔明显，椭圆形，外凸。单叶对生，叶柄长约l厘米；叶片卵形或广卵形，长5～12厘米，宽3～9厘米，先端渐尖，或呈尾状，或钝，基部通常广楔形或近圆形，全缘；上面淡绿色，有光泽，下面灰绿色，叶脉明显突起。夏季开白色花，多花形成疏大顶生圆锥花序，长15～25厘米；小花梗长1～2毫米；萼钟状，4裂；花冠4裂，管部较萼略长；雄蕊2，花丝较花冠裂片约长2倍，伸出花冠外。蒴果长圆形，长约2.3厘米，常有小瘤突，熟时2裂；种子每室2粒，周围具纸质翅。\n" +
                             "\n" +
                             "【产地分布】生于河岸、林缘及针阔叶混交林内。分布于我国东北、华北和西北各地。朝鲜、日本、俄罗斯也有。\n" +
                             "\n" +
                             "【采收加工】春秋两季剥取树皮，晒干。\n" +
                             "\n" +
                             "【药材性状】暴马丁香药材呈槽状或卷筒状，长短不一，厚2～4毫米，外表面暗灰褐色，嫩皮平滑，有光泽，表皮粗糙，有横纹，皮孔椭圆形，暗黄色；内表面淡黄褐色。质脆，易折断，断面不整齐。气微香，味苦。以皮厚、味苦者为佳。\n" +
                             "\n" +
                             "【性味归经】性微寒，味苦。归肺经。\n" +
                             "\n" +
                             "【功效与作用】清肺祛痰、止咳、平喘、利水。属化痰止咳平喘药下分类的清化热痰药。\n" +
                             "\n" +
                             "【临床应用】用量25～50克，煎服；或入丸、散。治疗痰喘咳嗽、慢性支气管炎、水肿。\n" +
                             "\n" +
                             "【药理研究】暴马丁香具有镇咳、祛痰、平喘作用；能减少三级以下支气管上皮细胞的肥大增生。经动物试验有镇咳、化痰、平喘作用。本品全皮和内皮水煎剂对肺炎双球菌、流感杆菌均有较强的抑制作用。动物实验表明本品无明显毒性。\n" +
                             "\n" +
                             "【化学成分】含挥发油、鞣质及甾体类物质、含紫丁香苷、蒿属香豆精、3，4-二羟基-β-羟乙基苯、暴马子醛酸甲酯等成分。\n" +
                             "\n" +
                             "【使用禁忌】尚不明确。\n" +
                             "\n" +
                             "【配伍药方】①治支气管炎、哮喘：暴马丁香60克，水煎至茶色，加入白糖15克，连煎3次，每晚服1次；或暴马丁香1500克，甘草90克，共切碎，加水500毫升，煎至300毫升，每次10毫升，每日3次。(《陕甘宁青中草药选》)\n" +
                             "\n" +
                             "②治慢性气管炎：暴马子、小檗各15克，松萝6克。水煎服。(《全国中草药汇编》)\n" +
                             "\n" +
                             "③治心脏性浮肿：暴马子30克。切碎，水煎，日服2次。(《吉林中草药》)");
                     chineseMedicineDao.insert(medicine211);

                     ChineseMedicine medicine212=new ChineseMedicine();
                     medicine212.setName("八角莲");
                     medicine212.setSortType("B");
                     medicine212.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=94ddc15cb8b7d0a26fc40ccfaa861d6c/50da81cb39dbb6fd6b8f48c80324ab18962b3703.jpg");
                     medicine212.setData("【中药名】八角莲 bajiaolian\n" +
                             "\n" +
                             "【别名】八角连、金魁莲、旱八角、叶下花、一把伞、马眼莲、独叶一枝花、八角盘、六角莲、独脚莲、独角莲、八角金盘、山荷叶。\n" +
                             "\n" +
                             "【英文名】Rhizoma dysosmatis\n" +
                             "\n" +
                             "【药用部位】本品为小檗科植物八角莲、六角莲和川八角莲的根及根茎。\n" +
                             "\n" +
                             "【植物形态】多年生草本，植株高40～150厘米。根状茎粗状，横生，多须根；茎直立，不分枝，无毛，淡绿色。茎生叶2枚，薄纸质，互生，盾状，近圆形，直径达30厘米，4～9掌状浅裂，裂片阔三角形，卵形或卵状长圆形，长2.5～4厘米，基部宽5～7厘米，先端锐尖，不分裂，上面无毛，背面被柔毛，叶脉明显隆起，边缘具细齿；下部叶的柄长12～25厘米，上部叶柄长1～3厘米。花梗纤细、下弯、被柔毛；花深红色，5～8朵簇生于离叶基部不远处，下垂；萼片6，长圆状椭圆形，长0.6～1.8厘米，宽6～8毫米，先端急尖，外面被短柔毛，内面无毛；花瓣6，勺状倒卵形，长约2.5厘米，宽约8毫米，无毛；雄蕊6，长约1.8厘米，花丝短于花药，药隔先端急尖，无毛；子房椭圆形，无毛，花柱短，柱头盾状。浆果椭圆形，长约4厘米，直径约3.5厘米。种子多数。花期3～6月，果期5～9月。\n" +
                             "\n" +
                             "【产地分布】生于山谷山坡杂木林中阴湿地方。陕西、安徽、浙江、江西、福建、台湾、湖北、湖南、广西、广东、四川、贵州、西藏等省区。\n" +
                             "\n" +
                             "【采收加工】根茎洗净泥沙，晒干，切断备用。亦可鲜用。\n" +
                             "\n" +
                             "【药材性状】本品呈扁长的结节状，长6～15cm，直径2～4cm。表面黄棕色至棕褐色，上面有凹陷的茎基痕，陷窝略重叠，连珠状排列，茎基痕边缘有环状皱纹，底部可见筋脉点突起；下面略平坦，残留须根痕。质硬而脆，结节处易折断，断面淡红棕色或黄白色。气微，味苦。\n" +
                             "\n" +
                             "【性味归经】性凉，味苦、辛，有毒。归肺经、肝经。\n" +
                             "\n" +
                             "【功效与作用】化痰散结，祛瘀止痛，清热解毒。属拔毒生肌药。\n" +
                             "\n" +
                             "【临床应用】用量：水煎服，3～9g；外用，捣敷或磨涂、醋调敷患处。主治咳嗽、咽喉肿痛、瘰疬、瘿瘤、痈肿、疔疮、毒蛇咬伤、跌打损伤，痹证。\n" +
                             "\n" +
                             "【药理研究】①兴奋心肌作用。②扩张血管作用。全草中含树脂，能引起猫的吐、泻、死亡。\n" +
                             "\n" +
                             "【化学成分】根和根茎含抗癌成分鬼臼毒素和脱氧鬼臼毒素。此外，还分离出黄芪苷、金丝桃苷、槲皮索、山柰酚和谷甾醇。\n" +
                             "\n" +
                             "【使用禁忌】孕妇禁服，体质虚弱者慎服。\n" +
                             "\n" +
                             "【配伍药方】①肿毒初起：八角莲加红糖或酒糟适量，捣烂敷贴，日换2次。\n" +
                             "\n" +
                             "②疔疮：八角莲6g，蒸酒服，并将须根捣烂敷患处。\n" +
                             "\n" +
                             "③瘰疬：八角莲30～60g，黄酒60g。加水适量煎服。\n" +
                             "\n" +
                             "④带状疱疹：八角莲根研末，醋调涂患处。\n" +
                             "\n" +
                             "⑤单、双蛾喉痛：八角莲3g，磨汁吞咽。\n" +
                             "\n" +
                             "⑥跌打损伤：八角莲根3～9g，研末，酒送服，每日2次。\n" +
                             "\n" +
                             "⑦毒蛇咬伤：1.八角莲9～15g，捣烂，冲酒服，渣敷伤处周围。2.八角莲根白酒磨涂患处；亦可内服，每服6g。对神经性毒素反应，可取八角莲根5节，用75%酒精7ml，浸泡7天，取浸出液1～2ml，注入伤口内。\n" +
                             "\n" +
                             "⑧咳痰：八角莲12g，猪肺60～120g，糖适量。煲汤服。\n" +
                             "\n" +
                             "⑨体虚弱、劳伤咳嗽、虚汗盗汗：八角莲9g，蒸鸽子、炖鸡或炖猪肉250g服。");

                     chineseMedicineDao.insert(medicine212);

                     ChineseMedicine medicine213=new ChineseMedicine();
                     medicine213.setName("槟榔");
                     medicine213.setSortType("B");
                      medicine213.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=768b07a20924ab18f41be96554938da8/aec379310a55b319cf2960f143a98226cefc1765.jpg");
                      medicine213.setData("【中药名】槟榔 binglang\n" +
                              "\n" +
                              "【别名】槟榔子、大腹子、宾门、橄榄子、青仔、洗瘴丹、大腹槟榔、槟榔玉。\n" +
                              "\n" +
                              "【英文名】Arecae Semen\n" +
                              "\n" +
                              "【药用部位】棕榈科植物槟榔Areca catechu L.的成熟种子。\n" +
                              "\n" +
                              "【植物形态】常绿乔木。茎干直立，不分枝，叶丛生于茎顶端；羽状复叶，叶轴三菱形，具长叶鞘。花单性同株，肉穗花序生于最下部叶的叶鞘束下，多分枝，成圆锥状，苞片大，佛焰苞状，雄花小而多，无柄，生于分枝的上部，萼片3，极小，花瓣3；雄蕊6枚，退化雄蕊3枚；雌花较大而少，无柄，生于分枝的下部，萼片及花瓣各3，退化雄蕊6枚，子房上位，1室。坚果。\n" +
                              "\n" +
                              "【生境分布】均为栽培。分布于广东、海南、福建、台湾、广西等地。\n" +
                              "\n" +
                              "【采收加工】冬、春季果实成熟时采收，剥去果皮，取出种子晒干。\n" +
                              "\n" +
                              "【药材性状】扁球形或圆锥形。表面淡黄棕色或淡红棕色，具稍凹的网状沟纹，底部中心有圆形凹陷的珠孔，其旁有一明显疤痕状种脐。质坚硬，断面可见棕色种皮与白色胚乳相间的大理石花纹。气微，味涩、微苦。\n" +
                              "\n" +
                              "【性味归经】味苦、辛，性温。归胃经、大肠经。\n" +
                              "\n" +
                              "【功效与作用】驱虫、消积、下气、行水。属驱虫药。\n" +
                              "\n" +
                              "【临床应用】用量3～9克，煎服。用治绦虫、蛔虫、姜片虫病；虫积腹痛、积滞泻痢、里急后重、水肿脚气、疟疾。驱绦虫、姜片虫：30～60克。\n" +
                              "\n" +
                              "【药理研究】驱虫；兴奋胆碱受体；抗病原微生物；抗高血压；抗癌。\n" +
                              "\n" +
                              "【化学成分】种子含总生物碱0.3%～0.6%，主要为槟榔碱，及少量的槟榔次碱，去甲基槟榔碱，去甲基槟榔次碱，异去甲基槟榔次碱，槟榔副碱，高槟榔碱、硬脂酸、肉豆蔻酸、左旋表儿茶精、鞣质、原矢车菊素A-1等成分，大多与鞣酸结合形式存在。还含鞣质约15%，内有右旋儿茶精，左旋表儿茶精，原矢车菊素A-1，B-1和B-2以及称为槟榔鞣质A、B的两个系列化合物，这两个系列均系原矢车菊素的二聚体、三聚体、四聚体、五聚体。\n" +
                              "\n" +
                              "【使用禁忌】气虚下陷者禁服。\n" +
                              "\n" +
                              "【配伍药方】①治诸虫在脏，久不瘥者：槟榔(炮)15克为末。每服6克，以葱、蜜煎汤调服3克。(《圣惠方》)\n" +
                              "\n" +
                              "②治蛔虫攻痛：槟榔60克。酒二盏，煎一盏，匀二次服。(《食物本草》)\n" +
                              "\n" +
                              "③治食积满闷成痰涎呕吐：槟榔、半夏、砂仁、萝卜子、麦芽、干姜、白术各6克。水煎服。(《方脉正宗》)\n" +
                              "\n" +
                              "④治心脾疼：高良姜、槟榔等分(各炒)。上为细末，米饮调下。(《百一选方》)\n" +
                              "\n" +
                              "⑤治聤耳出脓：槟榔研末吹之。(《鲍氏小儿方》)");

                      chineseMedicineDao.insert(medicine213);

                      ChineseMedicine medicine214=new ChineseMedicine();
                      medicine214.setName("半夏");
                      medicine214.setSortType("B");
                      medicine214.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=c78915c04c086e067ea5371963611091/6c224f4a20a4462376e72c939822720e0cf3d716.jpg");
                      medicine214.setData("【中药名】半夏 banxia\n" +
                              "\n" +
                              "【别名】三叶半夏、三步跳、麻芋子、水芋、地巴豆。\n" +
                              "\n" +
                              "【英文名】Pinelliae Rhizoma。\n" +
                              "\n" +
                              "【药用部位】天南星科植物半夏Pinellia ternata (Thunb.) Breit.的块茎。\n" +
                              "\n" +
                              "【植物形态】多年生草本。块茎近球形。叶出自块茎顶端，在叶柄下部内侧生一白色珠芽；一年生的叶为单叶，卵状心形；2～3年后，叶为3小叶的复叶，小叶椭圆形至披针形，中间小叶较大，两侧的较小，先端锐尖，基部楔形，全缘，两面光滑无毛。肉穗花序顶生，花序梗常较叶柄长；佛焰苞绿色；花单性，无花被，雌雄同株；雄花着生在花序上部，白色，雄蕊密集成圆筒形，雌花着生于雄花的下部，绿色；花序中轴先端附属物延伸呈鼠尾状，直立伸出在佛焰苞外。浆果卵状椭圆形，绿色。花期5～7月，果期8～9月。\n" +
                              "\n" +
                              "【产地分布】生于山坡草地、荒地、包谷地、田边、河边及疏林下。除内蒙古、新疆、西藏外，全国各地均有分布。\n" +
                              "\n" +
                              "【采收加工】夏、秋季均可采挖，洗净泥土，除去外皮和须根，晒干或烘干。本品生用有毒，用前须炮制。炮制品有：生半夏、清半夏、姜半夏、法半夏等。\n" +
                              "\n" +
                              "【药材性状】类球形，有的稍偏斜，直径0.8～1.5厘米。表面白色或浅黄色，顶端中心有凹陷的茎痕，周围密布棕色凹点状的根痕；下端钝圆，较光滑。质坚实，断面白色，富粉性。气微，味辛辣、麻舌而刺喉。\n" +
                              "\n" +
                              "【性味归经】性温，味辛，有毒。归脾经、胃经、肺经。\n" +
                              "\n" +
                              "【功效与作用】燥湿化痰、降逆止呕、消痞散结。属清热药下属分类的清热燥湿药。\n" +
                              "\n" +
                              "【临床应用】内服用量3～9克；或入丸、散；外用研末调敷。用治湿痰冷饮、呕吐、反胃、咳喘痰多、胸膈胀满、痰厥头痛、头晕不眠等。\n" +
                              "\n" +
                              "【药理研究】具有镇吐、催吐、镇咳、祛痰、抗癌、抗生育、抗早孕、抗心律失常、抗实验性溃疡、抗矽肺、促使外周淋巴细胞分裂等作用。\n" +
                              "\n" +
                              "【主要成分】含挥发油，内含3-乙酰氨基-5-甲基异唑等60多种成分及16种氨基酸和多种无机元素。其中，所含的草酸钙针晶为半夏的刺激性成分之一，经炮制后，晶形发生变化，含量急剧下降，刺激性明显减弱。另含琥珀酸、丁基乙烯基醚、苯甲醛、半夏蛋白、姜辣醇、胰蛋白酶抑制剂等。\n" +
                              "\n" +
                              "【使用禁忌】阴虚燥咳、津伤口渴、血证及燥痰者禁服，孕妇慎服。不宜与川乌、制川乌、草乌、制草乌、附子同用，生品内服宜慎。\n" +
                              "\n" +
                              "【配伍药方】①治湿痰喘急，止心痛：半夏不拘多少，香油炒，为末，粥丸梧桐子大。每服三五十丸，姜汤下。(《丹溪心法》)\n" +
                              "\n" +
                              "②治诸呕吐，谷不得下者：半夏一升，生姜240克。上二味，以水七升，煮取一升半，分温再服。(《金匮要略》小半夏汤)\n" +
                              "\n" +
                              "③治头痛：半夏(汤洗七遍)、白僵蚕各15克，全蝎一个。上同为细末，以绿豆粉调贴于太阳穴上，干即易之。(《叶氏录验方》抽风膏)\n" +
                              "\n" +
                              "④下乳方：半夏(炮)三粒。为末，酒调服，即有乳。(《鲁府禁方》)\n" +
                              "\n" +
                              "⑤治蝎螫毒：用半夏、白矾等分为末，以醋和，敷伤处。(《景岳全书》)");

                      chineseMedicineDao.insert(medicine214);
                      ChineseMedicine medicine215=new ChineseMedicine();
                      medicine215.setName("白矾");
                      medicine215.setSortType("B");
                      medicine215.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=4720eceb39c79f3d9becec62dbc8a674/a8ec8a13632762d0791e0daba2ec08fa513dc686.jpg");
                      medicine215.setData("【中药名】白矾 baifan\n" +
                              "\n" +
                              "【别名】矾石、理石、白君、矾石、明矾、羽泽。\n" +
                              "\n" +
                              "【英文名】Alumen\n" +
                              "\n" +
                              "【药用部位】硫酸盐类明矾石族矿物明矾石Alunite经加工精制而成的结晶。\n" +
                              "\n" +
                              "【分布】分布于山西、河北、甘肃、浙江、安徽、福建、湖北等地。主产于浙江平阳、安徽无为、福建福鼎。\n" +
                              "\n" +
                              "【采收加工】全年皆可采挖，采收后将明矾石打碎，用水溶解，收集溶液，过滤，加热浓缩，放冷后析出结晶即得。\n" +
                              "\n" +
                              "【药材性状】不规则块状或粒状，无色或淡黄白色，透明或半透明。表面略平滑或凹凸不平，具细密纵棱，有玻璃样光泽，常披有白色细粉。质硬而脆，易砸碎，断面显玻璃样光泽。断口不平坦。气微，味酸，微甘而极涩。\n" +
                              "\n" +
                              "【性味归经】性寒，味涩、酸。有小毒。归肺经、脾经、肝经、大肠经。\n" +
                              "\n" +
                              "【功效与作用】祛痰燥湿、解毒杀虫、止血止泻、解毒杀虫、燥湿止痒。属清热药下属分类的清热燥湿药。\n" +
                              "\n" +
                              "【临床应用】用量1~3克，研末服，或入丸、散；外用适量，研末撒，或吹喉，或调敷，或化水洗漱。用治痰饮中风、癫痫、喉痹、疥疮湿疮、痈疽肿毒、水火烫伤、口舌生疮、烂弦风眼、耵耳流脓、鼻中息肉、痔疮、崩漏、衄血、外伤出血、久泻久痢、带下阴痒、脱肛、子宫下垂、便血、湿疹、疥癣。\n" +
                              "\n" +
                              "【药理研究】白矾具有抑制病原微生物的作用，如抑制革兰阳性菌、革兰阴性菌、真菌;抗阴道滴虫;具有收敛作用。\n" +
                              "\n" +
                              "【化学成分】白矾主含含水硫酸铝钾。本品对金黄色葡萄球菌、草绿色链球菌、溶血性链球菌、变异链球菌、肺炎双球菌、脑膜炎双球菌、变形杆菌、大肠杆菌、绿脓杆菌、炭疽杆菌、福氏及志贺氏痢疾杆菌、伤寒杆菌、副伤寒杆菌、白喉杆菌、破伤风杆菌、淋病双球菌等有明显抑制作用，对白色念珠菌、表皮癣菌、毛霉菌等均有一定抑制作用。\n" +
                              "\n" +
                              "【使用禁忌】白矾内服过量易致呕吐，体虚胃弱者慎服。\n" +
                              "\n" +
                              "【配伍药方】①治疗肿恶疮：白矾(生用)、黄丹各等分。上各另研，临用时各抄少许和匀，三棱针刺疮见血，待血尽上药，膏药盖之。(《卫生宝鉴》二仙散)\n" +
                              "\n" +
                              "②治癫狂因忧郁而得，痰涎阻塞包络心窍者：白矾90克，川郁金210克。二药共为末，糊丸梧桐子大。每服五六十丸，温汤下。(《本事方》白金丸)\n" +
                              "\n" +
                              "③治急喉痹：白矾9克，巴豆二个(去壳，作六瓣)。将矾于铫内，慢火熬化为水，置巴豆其内，候干，去巴豆。取矾研为末。每用少许吹入喉中。(《玉机微义》白矾散)\n" +
                              "\n" +
                              "④治湿疹、皮炎(亚急性期)：五月艾、百部、明矶、毛麝香各15克，煎水外洗。(《中药临床应用》五百明洗剂)\n" +
                              "\n" +
                              "⑤治黄水疮：枯白矾、熟松香、黄丹，三味等分，研极细末，真芝麻油调涂患处。(《本草原始》)\n" +
                              "\n" +
                              "⑥治小儿因剪脐，伤外风，致脐疮久不干：白矾(煅)、龙骨(煅)等分。上研为细末敷之，少少用。如无两味，但得一味亦可。(《小儿卫生总微论方》)\n" +
                              "\n" +
                              "⑦治小儿耳疮及头疮，口边肥疮，蜗疮：白矾(烧灰)30克，蛇床子30克。上药同细研为散，干掺于疮上。(《圣惠方》白矾散)\n" +
                              "\n" +
                              "⑧治粉刺：枯矾30克，生硫黄6克，白附子6克。上共为末，唾津调搽，临晚上药，次早洗去。(《万病回春》)");
                      chineseMedicineDao.insert(medicine215);
                      ChineseMedicine medicine216=new ChineseMedicine();
                      medicine216.setName("鳖甲");
                      medicine216.setSortType("B");
                      medicine216.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=6dbc6f73881001e95a311c5dd9671089/e7cd7b899e510fb3c154510ddc33c895d0430cde.jpg");
                      medicine216.setData("【中药名】鳖甲 biejia\n" +
                              "\n" +
                              "【别名】上甲、鳖壳、甲鱼壳、团鱼壳、鳖盖子。\n" +
                              "\n" +
                              "【英文名】Trionycis Carapax。\n" +
                              "\n" +
                              "【药用部位】鳖科动物鳖Trionyx sinensis Weigmann的背甲。\n" +
                              "\n" +
                              "【动物形态】体呈椭圆形，背面中央凸起，边缘凹入。头尖，颈粗长，吻突出。眼小。颈基部无颗粒状；头颈可完全缩入甲内。背腹甲均无角质板而被有软皮。背面橄榄绿色或黑棕色，上有表皮形成的小疣，呈纵行排列；边缘柔软，俗称裙边。腹面黄白色，有淡绿色班。背、腹骨板间无缘板接连。前肢5指，仅内侧3指有爪；后肢趾亦同。指、趾间具蹼。\n" +
                              "\n" +
                              "【产地分布】多生活于湖泊、小河及池塘里。分布于我国除宁夏、新疆、青海、西藏外的各地。\n" +
                              "\n" +
                              "【采收加工】3～9月捕捉。捕得后，砍去鳖头，将鳖身置沸水中煮l～2小时，至甲上硬皮能脱落时取出，剥下背甲，刮净残肉后晒干。\n" +
                              "\n" +
                              "【药材性状】椭圆形或卵圆形，背面隆起。外表面黑褐色或墨绿色，有细网纹及灰白色斑点，中间有1条纵棱，两侧各有左右对称的横凹纹8条，外皮脱落后，可见锯齿状嵌接缝。内表面类白色，中间有一条脊椎，两侧各有肋骨8条伸出边缘。质坚硬。气微腥，味微咸。\n" +
                              "\n" +
                              "【性味归经】性微寒，味咸。归肝经、肾经。\n" +
                              "\n" +
                              "【功效与作用】滋阴潜阳、软坚散结、退热除蒸。属补虚药下属分类的补阴药。\n" +
                              "\n" +
                              "【临床应用】用量9～24克，捣碎先煎；外用适量，研末撒或调敷。用治阴虚发热、劳热骨蒸、虚风内动、经闭，癥瘕。\n" +
                              "\n" +
                              "【药理研究】鳖甲有补血、抗肝纤维化、抗癌作用，并可增强实验动物免疫力。\n" +
                              "\n" +
                              "【化学成分】鳖甲含动物胶、角蛋白、骨胶原以及维生素D等。尚含17种人体必需氨基酸及碳酸钙、磷酸钙等。\n" +
                              "\n" +
                              "【使用禁忌】脾胃虚寒、食少便溏及孕妇禁服。\n" +
                              "\n" +
                              "【配伍药方】①治久患咳嗽肺虚成劳瘵，及吐血，咯血等证：鳖甲(醋炙)、阿胶(炒)各30克，鹿角霜10克，甘草15克。上为末。每服9克，水一盏，韭白一茎长三寸，煎八分，食后服。(《古今医统》)\n" +
                              "\n" +
                              "②治久患劳疟瘴等：鳖甲90克，酥炙令黄，为末。临发时温酒调下6克。(《圣惠方》)\n" +
                              "\n" +
                              "③治癖病：鳖甲、诃黎勒皮、干姜等分。研末为丸。空心下三十丸，再服。(《药性论》)\n" +
                              "\n" +
                              "④治小儿痫：鳖甲炙令黄，捣为末。取3克，乳服。亦可蜜丸如小豆大服。(《子母秘录》)\n" +
                              "\n" +
                              "⑤治慢性肝炎，肝脾肿大，转氨酶偏高：鳖甲30克，丹参、垂盆草各15克，鳖甲先煎60分钟，后下其他药，煎水服。每日服1剂，每剂药煎2次，上午、下午各服1次。(《补益药治病与健身》");
                      chineseMedicineDao.insert(medicine216);

                      ChineseMedicine medicine217=new ChineseMedicine();
                      medicine217.setName("斑蝥");
                      medicine217.setSortType("B");
                      medicine217.setMedicineImageUrl("http://s11.sinaimg.cn/mw690/004mlAYfzy6KluuPMV45a&690");
                      medicine217.setData("【中药名】斑蝥 banmao\n" +
                              "\n" +
                              "【别名】羊米虫、花斑毛、放屁虫、花壳虫、小豆虫、斑猫、花罗虫。\n" +
                              "\n" +
                              "【英文名】Mylabris。\n" +
                              "\n" +
                              "【药用部位】芫青科昆虫南方大斑蝥Mylabris phalerata Pallas或黄黑小斑蝥M.cichorii Linnaeus的全体。\n" +
                              "\n" +
                              "【动物形态】南方大斑蝥：体长1.5～3.0厘米，黑色，被黑绒毛。头部具粗密刻点，复眼大，略呈肾形。触角l对。前胸长略大于宽。鞘翅端部阔于基部，黑色，翅基部有2个大黄斑；翅中央前后各有一黄色波纹状横带。足有黑色长绒毛，前足、中足跗节均为5节，后足跗节为4节。腹面亦具黑色长绒毛。黄黑小斑蝥：与南方大斑蝥相似，体较小。\n" +
                              "\n" +
                              "【产地分布】多群集栖息和取食，多危害大豆花生、茄子及棉花的芽、叶、花等。我国大部分地区均有分布。\n" +
                              "\n" +
                              "【采收加工】7～8月于清晨露水未干时捕捉，捕捉时宜戴口罩及手套。捕得后，置布袋中，用沸水烫死，取出晒干。\n" +
                              "\n" +
                              "【药材性状】南方大斑蝥：呈长椭圆形，长1.5～2.5厘米。头部及口器向下垂，有较大的复眼及触角各1对，触角多脱落。背部鞘翅1对，黑色，有3条黄色或棕黄色横纹；鞘翅下有棕褐色薄膜状内翅2片。胸腹部乌黑色，胸部有足3对。有特殊臭气。黄黑小斑蝥：与南方大斑蝥相似，体较小，长1～1.5厘米。\n" +
                              "\n" +
                              "【性味归经】性热，味辛，有大毒。归肝经、胃经、肾经。\n" +
                              "\n" +
                              "【功效与作用】破血消癥、攻毒蚀疮、引赤发泡。属活血化瘀药下属分类的破血消癥药。\n" +
                              "\n" +
                              "【临床应用】用量0.03～0.06克，炮制后多人丸、散用。外用适量，研末或浸酒、醋涂患处。用治癥瘕肿块、积年顽癣、瘰疬、赘疣、痈疽不溃。\n" +
                              "\n" +
                              "【药理研究】具有抗肿瘤作用;由于刺激骨髓细胞DNA合成，能升高白细胞，还有增强机体免疫功能;具有明显的抗炎作用;有抗病毒、抗菌的作用;有促雌激素样作用，尚具有一定的雌激素样作用;还有局部刺激等作用。\n" +
                              "\n" +
                              "【化学成分】斑蝥主要含斑蝥素、脂肪及树脂。\n" +
                              "\n" +
                              "【使用禁忌】本品有大毒，内服慎用;凡体质虚弱者，心、肾功能不全者，消化道溃疡者，以及孕妇均禁服。\n" +
                              "\n" +
                              "【配伍药方】①治瘰疬结核：斑蝥1枚，黑豆7枚(生芽者)。同研为丸，如绿豆大。每服五丸，茶清下。小儿一丸。(《圣济总录》大效丸)\n" +
                              "\n" +
                              "②治疣痣黑子：斑蝥3个，以糯米15克炒黄，去米。入蒜1个，捣烂点之。(《纲目》)\n" +
                              "\n" +
                              "③治急心痛：斑蝥7个，胡椒49粒。同炒至斑蝥焦碎，去斑蝥不用，取净胡椒为末。作一服，热酒调下，不拘时候。(《卫生易简方》)\n" +
                              "\n" +
                              "④治面神经麻痹：斑蝥一个。研细。水调贴颊部，向左歪贴右侧，向右歪贴左侧，起泡后即取去药。(《山东中草药手册》)\n" +
                              "\n" +
                              "⑤治疟疾：斑蝥七个，麻黄、雄精各3.6克，朱砂1.5克。共研细末。每日用0.3～0.9克，调放膏药上，贴颈部第2骨节处。[《浙江中医杂志》1959，(7)：39]\n" +
                              "\n" +
                              "⑥治腰腿痛：斑蝥烘干，研粉。取火柴头大，压体表最痛点上，以胶布固定，5～6小时后起泡如蚕豆大，24小时后去药，挑破出水，涂以龙胆紫，不愈再敷。(《全国中草药汇编》)\n" +
                              "\n" +
                              "⑦治偏正头风：斑蝥1个，去头、翅、足，隔纸研细为末，筛去衣壳。将少许贴在膏药上，头左痛，贴右太阳穴;头右痛，贴左太阳穴，足半日取下。(《良方集腋》)");

                      chineseMedicineDao.insert(medicine217);
                      ChineseMedicine medicine218=new ChineseMedicine();
                      medicine218.setName("冰片");
                      medicine218.setSortType("B");
                      medicine218.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=7f15f6a168d0f703f2bf9d8e69933a58/f2deb48f8c5494ee0519afaf27f5e0fe99257e49.jpg");
                      medicine218.setData("【中药名】冰片 bingpian\n" +
                              "\n" +
                              "【别名】龙脑、龙脑香、艾片、机片。\n" +
                              "\n" +
                              "【英文名】Borneolum Syntheticum。\n" +
                              "\n" +
                              "【药用部位】菊科植物艾纳香Blumea balsamifera. DC.的叶中提取物中结晶，习称“艾片”。龙脑香科植物龙脑香Dryobalanops aromatica Gaertn.f.树脂的加工品，习称“龙脑冰片”。除此，人工合成冰片，又称“机制冰片”或“机片”，是用樟脑、松节油等经化学方法合成。\n" +
                              "\n" +
                              "【植物形态】艾纳香：多年生草本或灌木状。茎密被黄褐色柔毛。单叶互生，矩圆形或矩圆状披针形，上、下面均密被毛。头状花序多数排成圆锥花序；总苞片4～5层被白色绵毛；花全为管状，花冠黄色；缘花雌性，盘花两性；聚药雄蕊5；雌蕊1，子房下位。瘦果10棱，矩圆形，冠毛淡白色。花期3～5月，果期9～ 10月。龙脑香：常绿乔木，光滑无毛。叶互生，革质；叶片卵圆形，先端尖，基部钝圆形或阔楔形，全缘，两面无毛，有光泽。圆锥状花序，着生于枝上部的叶腋间，花两性，整齐；花托肉质，微凹；花萼5，覆瓦状排列；花瓣5，白色；雄蕊多数，离生；雌蕊1。干果卵圆形，果皮革质，不裂；种子1～2枚，具胚乳。\n" +
                              "\n" +
                              "【产地分布】艾片主产于广东、广西、云南、贵州等地，龙脑冰片主产于东南亚地区，我国台湾地区有引种。\n" +
                              "\n" +
                              "【采收加工】9～10月采收树叶，经蒸汽蒸馏，冷却，收取结晶。\n" +
                              "\n" +
                              "【药材性状】半透明结晶，直径2～8毫米，厚2～3毫米，白色。气清香，味辛凉浓烈。经升华后，形成半透明块状、片状结晶。燃之有浓黑烟。\n" +
                              "\n" +
                              "【性味归经】性寒，味辛、苦。归心经、脾经、肺经。\n" +
                              "\n" +
                              "【功效与作用】开窍醒神、清热止痛。属开窍药。\n" +
                              "\n" +
                              "【临床应用】用量0.03～0.1克。外用适量。用治热闭神昏、痰热内闭、暑热卒厥、小儿惊风及各种疮痈肿痛、溃后不敛、烫火伤、咽喉肿痛、喉痹、目赤肿痛、口舌生疮等。\n" +
                              "\n" +
                              "【药理研究】可抑制中枢神经系统，表现为明显的镇静镇痛作用，促进神经胶质细胞的生长和分裂；有抗炎、抗菌、抗生育作用，能与其他药物如四甲基吡嗪、水杨酸发生相互作用。局部应用对感觉神经具有轻微的刺激作用，有一定的止痛和防腐作用；服后能迅速通过血脑屏障进入神经中枢发挥作用；能显著延长戊巴比妥引起的小鼠睡眠时间与戊巴比妥产生协同作用，并能延长小鼠耐缺氧时间；较高浓度的冰片(0.5%)对多种细菌有抑制作用；对中、晚期妊娠小鼠有引产作用。\n" +
                              "\n" +
                              "【化学成分】本品主要含右旋龙脑。\n" +
                              "\n" +
                              "【使用禁忌】孕妇及气血虚者均应慎服。\n" +
                              "\n" +
                              "【配伍药方】①治头脑疼痛：龙脑3克，纸卷作拈，烧烟熏鼻，吐出痰涎，即愈。(《寿域神方》)\n" +
                              "\n" +
                              "②治眼生花翳：龙脑3克，川朴硝15克。上件药同研如粉。每以铜箸取如大豆大，点之。(《圣惠方》)\n" +
                              "\n" +
                              "③治风热喉痹：灯心草3克，黄柏(并烧存性)1.5克，白矾(煅)2.1克，冰片脑0.9克。为末。每以0.3～0.6克吹患处。(《濒湖集简方》)\n" +
                              "\n" +
                              "④治口疮咽燥：龙脑9克，黄柏90克。为蜜丸梧子大，每麦门冬汤下十丸。(《摘玄方》)\n" +
                              "\n" +
                              "⑤治痢疾，肛门肿胀如痔状：冰片研乳调搽。(《慎斋遗书》)\n" +
                              "\n" +
                              "⑥治痱疮：冰片(研)0.3克，黄柏(末)15克，白面60克，腊茶(末)30克。上拌匀，每以新绵揾药扑上，破者敷之。(《小儿卫生总微论方》)\n" +
                              "\n" +
                              "⑦治内外痔疮：龙脑0.3～0.6克。葱汁化搽之。(《简便单方》)");
                      chineseMedicineDao.insert(medicine218);

                      ChineseMedicine medicine219=new ChineseMedicine();
                      medicine219.setName("白鲜皮");
                      medicine219.setSortType("B");
                      medicine219.setMedicineImageUrl("http://www.qnong.com.cn/uploadfile/2016/0816/20160816085647519.jpg");
                      medicine219.setData("【中药名】白鲜皮 baixianpi\n" +
                              "\n" +
                              "【别名】鲜皮、北鲜皮、野花椒根皮、臭根皮。\n" +
                              "\n" +
                              "【英文名】Dictamni Cortex\n" +
                              "\n" +
                              "【药用部位】芸香科植物白鲜Dictamnus dasycarpus Turcz.的根皮。\n" +
                              "\n" +
                              "【植物形态】多年生草本，高约1米，全株具特异气味。根数条丛生，长圆柱形，具较强烈的羊膻样气味，外皮灰白色或近灰黄色，内面白色，木心坚硬，新鲜时易与皮部分离。茎直立，下部呈灌木状，外皮略带革质，常被白色细柔毛和腺体。奇数羽状复叶，互生，小叶通常9片，小叶片卵形至椭圆形，先端短渐尖，基部略带楔状或左右稍不对称，边缘具细锯齿，两面沿脉有细柔毛，叶柄及叶轴两侧有狭翼，狭翼密布明亮的油点。总状花序顶生，花轴、花梗、苞片及萼片均密被细柔毛和腺体，花瓣5。蒴果。\n" +
                              "\n" +
                              "【产地分布】生于山阳坡疏林或灌木丛中，以及开阔的多石山坡、平原草地。分布于黑龙江、吉林、辽宁、内蒙古等地。\n" +
                              "\n" +
                              "【采收加工】春、秋两季均可采挖，但以春季采挖者为佳。将根挖出后，洗净，除去细根及外面粗皮，纵向割开，抽去木心，晒干。\n" +
                              "\n" +
                              "【药材性状】卷筒状，长5～15厘米，直径1～2厘米，厚0.2～0.5厘米。外表面灰白色或淡灰黄色，具纵皱纹和侧根痕，常有突起的颗粒性小点，内表面淡黄色或类白色，有细纵纹，有时具小圆形侧根穿孑L。质轻而脆，易折断，折断时有白粉飞扬.断面不平坦，乳白色，略呈层片状，迎光可见闪亮的小结晶状物。有羊膻样气，味微苦。\n" +
                              "\n" +
                              "【性味归经】性寒，味苦。归脾经、胃经。\n" +
                              "\n" +
                              "【功效与作用】清热燥湿、祛风解毒。属清热药下分类的清热燥湿药。\n" +
                              "\n" +
                              "【临床应用】用量4.5～9克，内服煎汤，治疗湿热疮毒、黄水疮、湿疹、风疹、疥癣、疮癞、风湿痹、黄疸尿赤等症。外用适量，煎汤洗或研粉敷。\n" +
                              "\n" +
                              "【药理研究】对多种致病真菌具有抑制作用；能增强心肌收缩力，收缩血管；对子宫及肠平滑肌有强力的收缩作用；抑制免疫功能，尚具有一定的抗癌作用。水浸液对多种皮肤真菌均有不同程度的抑制作用；水浸液对温刺发热之兔有解热作用；对细胞免疫和体液免疫均有抑制作用，而不导致脾脏萎缩；本品多糖还能提高网状内皮系统的吞噬功能；白鲜碱对离体蛙心有兴奋作用，可使心肌张力增加，对离体兔耳血管有明显的收缩作用，崖椒碱能抗心律失常；此外本品能缩短凝血时间，松弛血管，梣酮对大鼠有抗生育、抗受精作用，白鲜碱有体外抗癌活性。胡卢巴碱大鼠灌服LD50为5克/千克。\n" +
                              "\n" +
                              "【化学成分】含白鲜碱、茵芋碱、梣酮、黄柏酮、补骨脂素、花椒毒素、槲皮素、异槲皮素等成分。\n" +
                              "\n" +
                              "【使用禁忌】虚寒证禁服。\n" +
                              "\n" +
                              "【配伍药方】①治皮肤湿疹、皮肤瘙痒：白鲜皮、苦参各90克，为水丸。每服6克，日2次，温开水送服。并可单用白鲜皮适量，煎汤，外洗。每日1～2次。(《青岛中草药手册》)\n" +
                              "\n" +
                              "②治鹅掌风：用白鲜皮入口嚼烂，手搓之。(《万氏秘传外科心法》)\n" +
                              "\n" +
                              "③治急性肝炎：白鲜皮9克，茵陈15克，栀子9克，大黄9克。水煎服。(《内蒙古中草药》)\n" +
                              "\n" +
                              "④治产后风虚：白鲜皮、独活各90克。为粗末，酒、水各二盏，煎取二盏。分三服。或单用白鲜皮亦妙。(《卫生易简方》)\n" +
                              "\n" +
                              "⑤治外伤出血：白鲜皮研细末，外敷。(《宁夏中草药手册》)\n" +
                              "\n");
                      chineseMedicineDao.insert(medicine219);

                      ChineseMedicine medicine220=new ChineseMedicine();
                      medicine220.setName("薄荷");
                      medicine220.setSortType("B");
                      medicine220.setMedicineImageUrl("http://res.youth.cn/article_201806_01_013_5b107494035a6_ocr.jpg");
                      medicine220.setData("【中药名】薄荷 bohe\n" +
                              "\n" +
                              "【别名】蕃荷菜、苏薄荷、鸡苏、蔢荷、夜息药、水益母、见肿消、土薄荷。\n" +
                              "\n" +
                              "【英文名】Menthae Haplocalycis Herba\n" +
                              "\n" +
                              "【药用部位】唇形科植物薄荷Mentha haplocalyx Briq.的地上部分。\n" +
                              "\n" +
                              "【植物形态】多年生草本。茎方形，被逆生的长柔毛及腺点。单叶对生，叶片短圆状披针形或披针形，两面有疏柔毛及黄色腺点；轮伞花序腋生；萼钟形，外被白色柔毛及腺点，10脉，5齿；花冠淡紫色，4裂，上裂片顶端2裂；雄蕊4，前对较长，均伸出花冠外。小坚果卵圆形，黄褐色。花期7～10月，果期10～11月。\n" +
                              "\n" +
                              "【产地分布】生于河边、沟边、路边、小溪边及山野湿地。我国南北均产，尤以江苏产者为佳。\n" +
                              "\n" +
                              "【采收加工】薄荷通常收割2次，第1次收割在小暑后大暑前(7月中下旬)，主要提取薄荷油用；第2次收割在霜降之前(10月中下旬)，主要作药材用，晒干或阴干。\n" +
                              "\n" +
                              "【药材性状】茎方柱形，长60～90厘米，直径2～8毫米；表面紫棕色或淡绿色，棱角处具茸毛，节间长1～5厘米，有对生分枝，质脆，断面中空或白色。叶对生，有短柄；叶片皱缩卷曲，湿润后展开，叶披针形、卵状披针形、长圆状披针形至椭圆形，两面均有柔毛及腺鳞(放大镜下观察呈凹点状)。茎上部轮伞花序腋生，疏离，花萼钟状，先端5齿裂，花冠多数存在，黄棕色。搓揉后有特殊香气，味辛凉。\n" +
                              "\n" +
                              "【性味归经】性凉，味辛。归肺经、肝经。\n" +
                              "\n" +
                              "【功效与作用】宣散风热、清头目、透疹。属解表药下属分类的辛凉解表药。\n" +
                              "\n" +
                              "【临床应用】用量3～6克，宜后下，外用适量。用治感冒风热、头痛、目赤、咽喉红肿疼痛、皮肤瘙痒、麻疹透发不畅等。\n" +
                              "\n" +
                              "【药理研究】具有发汗、解热、缓解胃肠平滑肌痉挛、兴奋中枢神经、促进呼吸道腺体分泌而具消炎及解痉等作用；还能刺激神经末梢冷感受器而产生冷感，并反向性地造成深部组织血管的变化而起到消炎、止痛、止痒作用；对革兰氏阻性、革兰氏阴性球菌、杆菌及多种病毒有一定的抑制作用。具有明显的解痉作用、保肝利胆作用、抗早孕及子宫的作用；对心血管，可麻痹心脏，扩张血管；对呼吸系统，具有祛痰作用及良好的止咳作用；促进透皮吸收，可用于抗微生物。\n" +
                              "\n" +
                              "【化学成分】薄荷中主要含挥发油，《中华人民共和国药典》规定本品挥发油含量不得少于0.80%(毫升/克)。另含左旋薄荷醇、异薄荷酮、胡薄荷酮、乙酸薄荷酯、异瑞福灵、薄荷异黄醐苷、迷迭香酸等成分\n" +
                              "\n" +
                              "【使用禁忌】阴虚血燥，肝阳偏亢，表虚汗多者忌服。本品芳香辛散，发汗耗气，故体虚多汗者不宜使用。\n" +
                              "\n" +
                              "【配伍药方】①治男妇伤风咳嗽，鼻塞声重：野薄荷6克，陈皮6克，杏仁6克(去皮尖)。引用竹叶十五片，水煎服。(《滇南本草》)\n" +
                              "\n" +
                              "②治口疮：薄荷、黄柏，等分。为末，入青黛少许搽之。(《赤水玄珠》赴筵散)\n" +
                              "\n" +
                              "③治血痢：薄荷叶煎汤单服。(《普济方》)\n" +
                              "\n" +
                              "④治皮肤瘾疹不透，瘙痒：薄荷叶10克，荆芥10克，防风10克，蝉蜕6克。水煎服。(《四川中药志》1979年)\n" +
                              "\n" +
                              "⑤治一切牙痛，风热肿痛尤妙：薄荷、樟脑、花椒各等分。上为细末，擦患处。(《医学统旨》擦牙定痛散)\n" +
                              "\n" +
                              "⑥清火化痰，利咽膈，治风热：薄荷末炼蜜丸，如芡子大，每噙一丸，白砂糖和之亦可。(《简便单方》)\n" +
                              "\n" +
                              "⑦治眼弦赤烂：薄荷，以生姜汁浸一宿，晒干为末，每用3克，沸汤泡洗。(《明目经验方》)\n" +
                              "\n" +
                              "⑧治结合膜炎：将薄荷叶用冷开水洗净后，浸入乳汁中10～30分钟。患眼用5%盐开水冲洗后。取薄荷叶盖于患眼上，经10分钟可再换1叶，每天数次。(《福建药物志》)");
                      chineseMedicineDao.insert(medicine220);

                        ChineseMedicine medicine221=new ChineseMedicine();
                        medicine221.setName("扁蓄");
                        medicine221.setSortType("B");
                        medicine221.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545923634&di=5b21d76a468f152bcc75d5e78b8ab6d3&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.qnong.com.cn%2Fuploadfile%2F2017%2F0318%2F20170318094536802.jpg");
                        medicine221.setData("【中药名】扁蓄 bianxu\n" +
                                "\n" +
                                "【别名】萹蓄、萹竹、道生草、地蓼、粉节草、百节、野铁扫把。\n" +
                                "\n" +
                                "【英文名】Herba Polygoni Avicularis。\n" +
                                "\n" +
                                "【药用部位】蓼科植物扁蓄Polygonum aviculareL.的地上部分。\n" +
                                "\n" +
                                "【植物形态】一年生草本，高15～50厘米。茎匍匐或斜上，基部分枝甚多，具明显的节及纵皱纹，幼枝上微有棱角。叶互生，叶柄短，叶片披针形至椭圆形，先端钝或尖，基部楔形，全缘，绿色，两面无毛，托叶鞘膜质，抱茎，下部绿色，上部透明无色，具明显脉纹，其上之多数平行脉常伸出成丝状裂片。花6~ 10朵簇生于叶腋，花梗短，苞片及小苞片均为白色透明膜质，花被绿色，具白色边缘，结果后，边缘变为粉红色，雄蕊通常8枚，子房长方形，柱头3枚。瘦果包围于宿存花被内，仅顶端小部分外露，卵形。\n" +
                                "\n" +
                                "【产地分布】生于山野路旁、荒地及河边等处。分布于全国各地。\n" +
                                "\n" +
                                "【采收加工】芒种至小暑间，茎叶生长茂盛时采收。割取地上部分，晒干。\n" +
                                "\n" +
                                "【药材性状】茎呈圆柱形而略扁，有分枝，长15～40厘米，直径0.2～0.3厘米。表面灰绿色或棕红色，有细密微突起的纵纹，节部稍膨大，有浅棕色膜质的托叶鞘，节间长约3厘米，质硬，易折断，断面髓部白色。叶互生，近无柄或具短柄，叶片多脱落或皱缩、破碎，完整者展平后披针形，全缘，两面均呈棕绿色或灰绿色。无臭，味微苦。\n" +
                                "\n" +
                                "【性味归经】性微寒，味苦。归膀胱经。\n" +
                                "\n" +
                                "【功效与作用】利尿通淋、杀虫、止痒。属利水渗湿药下分类的利尿通淋药。\n" +
                                "\n" +
                                "【临床应用】用量9～15克，内服煎汤，治疗膀胱热淋、小便短赤、淋沥涩痛、皮肤湿疹、阴痒带下。外用适量，煎洗患处。\n" +
                                "\n" +
                                "【药理研究】具有利尿、降压、抗菌作用，有轻度收敛作用。能加速血液凝固，增强子宫张力。对人血小板聚集有抑制作用；对大鼠和犬有利胆作用等。煎剂对大白鼠的，尿量及钠、钾排出量均增加，其灰分亦有同样效果；水及醇提物能加速血液凝固，使子宫张力增高，可用作流产及分娩后子宫出血的止血剂；水及醇提物静脉注射对猫、兔、狗有降压作用；1：10的浸出液，对某些真菌有抑制作用，对细菌的抑制作用较弱；此外，本品能增强呼吸运动的幅度及肺换气量，有轻度收缩作用，可作创伤用药。毒性：萹蓄作为牧草是有毒的，可使马、羊产生皮炎及胃肠紊乱，鸽对此植物的毒性作用最敏感。猫、兔口服浸剂( 10%～20%)或煎剂(1：40)的最小致死量为20毫克/千克，静脉注射水提取物(1：50)为2毫克/千克。\n" +
                                "\n" +
                                "【化学成分】含扁蓄苷、槲皮苷、绿原酸、杨梅苷、牡荆素、槲皮素、木犀草素、异牡荆素、金丝桃苷、木犀草素、东莨菪素、芥子酸、阿魏酸、丁香酸等成分。\n" +
                                "\n" +
                                "【使用禁忌】脾胃虚弱及阴虚患者慎服。\n" +
                                "\n" +
                                "【配伍药方】①治尿道炎，膀胱炎：鲜扁蓄60克，鲜车前草30克。捣烂绞汁。分2次服。(《福建药物志》)\n" +
                                "\n" +
                                "②治尿路结石：扁蓄、活血丹(金钱草)各15克，水煎服；或扁蓄、海金沙藤、车前草各30克，水煎服。(《浙江药用植物志》)\n" +
                                "\n" +
                                "③治乳糜尿：鲜萹蓄30～60克，加鸡蛋1～2只，生姜适量。水煎，食蛋服汤。(《浙江药用植物志》\n" +
                                "\n" +
                                "④治小便不通：萹蓄一握，水盅半，煎一盅，热服。(《卫生易简方》)\n" +
                                "\n" +
                                "⑤治黄疸：鲜萹蓄30～60克，黄蚬250克。水煎，当茶饮。(《福建药物志》)\n" +
                                "\n" +
                                "⑥治白带：鲜扁蓄90克，细叶艾根45克，粳米90克，白糖30克。先将粳米煮取米汤，再入各药，煎汁，去渣，加白糖。空腹服，每日1剂。(《浙南本草新编》)\n" +
                                "\n" +
                                "⑦治泻痢：a.扁蓄30克，仙鹤草30克。水煎服。(《四川中药志》1979年)；b.泄泻：扁蓄12克，车前9克，龙芽草15克。水煎服。(《湖南药物志》)\n" +
                                "\n" +
                                "⑧治胆道蛔虫症：扁蓄100克，醋100克，加水1碗，煎至1碗，每日分2次。(《长白山植物药志》)");
                        chineseMedicineDao.insert(medicine221);

                        ChineseMedicine medicine222=new ChineseMedicine();
                        medicine222.setName("败酱草");
                        medicine222.setSortType("B");
                        medicine222.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=ff3811f5d73f8794c7f2407cb3726591/3c6d55fbb2fb4316005f883723a4462309f7d33f.jpg");
                        medicine222.setData("【中药名】败酱草 baijiangcao\n" +
                                "\n" +
                                "【别名】败酱、黄花败酱、苦菜、豆豉草、观音菜。\n" +
                                "\n" +
                                "【英文名】Herba Patriniae\n" +
                                "\n" +
                                "【药用部位】败酱科植物黄花败酱Patrinia scabiosaefolia Fisch.或白花败酱Patrinia villosa (Thunb.) Juss的全草。\n" +
                                "\n" +
                                "【植物形态】多年生草本。地下茎细长，横走，有特殊臭气。基生叶对生，叶片披针形或卵形，2～3回羽状深裂，中央裂片较大，椭圆形或卵形，两侧裂片窄卵形至条形，两面疏被粗毛或近无毛。聚伞圆锥花序顶生，常5～9序集成疏大伞房状；苞片小；花小，黄色，花萼不明显；花冠简短，5裂；雄蕊4枚；子房下位。瘦果椭圆形，有3棱。花期7～9月。\n" +
                                "\n" +
                                "【产地分布】生于山坡林下、路边、沟旁。分布于四川、江西、福建等地。\n" +
                                "\n" +
                                "【采收加工】夏季花开前采挖，晒至半干，扎成束，再阴干。\n" +
                                "\n" +
                                "【药材性状】全长50～100厘米。根茎呈圆柱形，多向一侧弯曲，直径0.3～1厘米，表面暗棕色至紫棕色，有节，节间长多不超过2厘米，上有细根。茎圆柱形，直径0.2～0.8厘米；表面黄绿色至黄棕色，节明显，常有倒生粗毛，质脆，断面中部有髓或细小空洞。叶对生，叶片薄，多卷缩或破碎，完整者展平后呈羽状深裂至全裂，有5～11裂片，先端裂片较大，长椭圆形或卵形，两侧裂片狭椭圆形至条形，边缘有粗锯齿，上表面深绿色或黄棕色，下表面色较浅，两面疏生白毛，叶柄短或近无柄，基部略抱茎；茎上部叶较小，常3裂，裂片狭长，有的枝端带有伞房状聚伞圆锥花序。气特异，味微苦。\n" +
                                "\n" +
                                "【性味归经】性凉，味辛、苦。归胃经、大肠经、肝经。\n" +
                                "\n" +
                                "【功效与作用】清热解毒、祛瘀排脓。属清热药下属分类的清热解毒药。\n" +
                                "\n" +
                                "【临床应用】用量9～15克，煎服。用治阑尾炎、痢疾、肠炎、肝炎、眼结膜炎、产后瘀血腹痛。外用鲜品适量，捣烂敷患处，治疗痈肿疔疮。\n" +
                                "\n" +
                                "【药理研究】动物试验表明，提取物有促进肝细胞再生、防止肝细胞变性、改善肝功能的作用；醇提物有显著的镇静作用。体外试验，对金黄色葡萄球菌、溶血性链球菌、大肠杆菌等有较强抑制作用。\n" +
                                "\n" +
                                "【化学成分】含挥发油，主要为败酱烯、异败酱烯，另含黄花败酱皂苷A、黄花败酱皂苷B、黄花败酱皂苷C、黄花败酱皂苷D、黄花败酱皂苷E、黄花败酱皂苷F、黄花败酱皂苷G以及齐墩果酸等。还含白花败酱苷、常春藤皂苷元、β-谷固醇-β-D-葡萄糖苷、败酱皂苷等成分。\n" +
                                "\n" +
                                "【使用禁忌】脾胃虚弱及孕妇慎服。\n" +
                                "\n" +
                                "【配伍药方】①治吐血、衄血，因积热妄行者：败酱60克，黑山栀9克，怀熟地黄15克，灯心草3克。水煎，徐徐服。(《本草汇言》引《硕虎斋医话》)\n" +
                                "\n" +
                                "②治产后腰痛：败酱、当归各2.4克。川芎、芍药、桂心各1.8克。水二升，煮八合，分二服。忌葱。(《纲目》引《广济方》)\n" +
                                "\n" +
                                "③治产后腹痛如锥刺者：败酱150克。水四升，煮二升。每服二合，日三服。(《卫生易简方》)\n" +
                                "\n" +
                                "④治无名肿毒：鲜(败酱)全草30～60克。酒水各半煎服，渣捣烂敷患处。(《闽东本草》)\n" +
                                "\n" +
                                "⑤治肋间神经痛：败酱草60克。水煎服。(《浙江药用植物志》)\n" +
                                "\n" +
                                "⑥治赤白痢疾：鲜败酱草60克，冰糖15克。开水炖服。(《闽东本草》)");
                        chineseMedicineDao.insert(medicine222);

        ChineseMedicine medicine223=new ChineseMedicine();
        medicine223.setName("半枝莲");
        medicine223.setSortType("B");
        medicine223.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545923903&di=1ef3728d21ce318c96966b1f8458cac9&imgtype=jpg&er=1&src=http%3A%2F%2Fimg0.ph.126.net%2F4AIWSGUw26MjWJbs2zqQFQ%3D%3D%2F1143069880522119702.jpg");
        medicine223.setData("【中药名】半枝莲 banzhilian\n" +
                "\n" +
                "【别名】狭叶韩信草、急解索、细米草、偏头草、半向花、四方草、通经草、牙刷草、耳挖草。\n" +
                "\n" +
                "【英文名】Scutellariae Barbatae Herba\n" +
                "\n" +
                "【药用部位】唇形科植物半枝莲Scutellaria barbata D. Don.的全草。\n" +
                "\n" +
                "【植物形态】多年生直立草本。茎四棱形，分枝多，下部略呈紫色，无毛。叶交互对生，有短柄，叶片三角状长卵形至披针形，顶端略钝，边缘具疏钝齿，基部截形，叶上面深绿色，被稀柔毛，下面淡绿色，仅叶脉及边缘有稀柔毛。花顶生于茎及分枝的上部，每轮有花2朵，并生，集成偏一侧的总状花序；花萼紫色，萼筒外面密被短柔毛，内面无毛，上唇背部附有盾片，果期增大；花冠蓝紫色，外面密被长柔毛，内面无毛，冠筒基部前方囊状，下唇中间裂片呈盔状；雄蕊4枚，2强；花柱着生于子房基部，柱头2裂，果实成熟时上萼筒开裂而脱落，下萼筒宿存，露出4个扁球形小坚果。花期5～10月，果期6～11月。\n" +
                "\n" +
                "【产地分布】常生于田埂、溪边或潮湿草地上。分布于广东、浙江、福建、台湾、广西、海南等地。\n" +
                "\n" +
                "【采收加工】春、夏季开花期采收。拔取全株，除去泥沙，晒干。\n" +
                "\n" +
                "【药材性状】无毛或花轴上疏被毛。根纤细。茎丛生，较细，方柱形；表面暗紫色或棕绿色。叶对生，有短柄；叶片多皱缩，展平后呈三角状卵形或披针形；先端钝，基部宽楔形，全缘或有少数不明显的钝齿；上表面暗绿色，下表面灰绿色。花单生于茎枝上部叶腋，花萼裂片钝较圆；花冠二唇形，棕黄色或浅蓝紫色，被毛。果实扁球形，浅棕色。气微，味微苦。\n" +
                "\n" +
                "【性味归经】味辛、苦，性寒。归肺经、肝经、肾经。\n" +
                "\n" +
                "【功效与作用】清热解毒、活血化瘀、利尿。属清热药下属中的清热解毒药。\n" +
                "\n" +
                "【临床应用】用量15～30克，煎服；鲜品用量30～60克。外用鲜品适量，捣敷患处。用治晚期血吸虫病腹水、肝硬化腹水、毒蛇咬伤、疮疖痈肿。\n" +
                "\n" +
                "【药理研究】抗癌，对急性粒细胞型白血病( NvIL)血细胞有轻度抑制作用。提高免疫系统功能，但大剂量注射可抑制小鼠胸腺指数；有抑菌作用；有较强的对抗由组胺引起的平滑肌收缩作用。有很好的解痉祛痰作用。具有利尿、兴奋呼吸及解蛇毒的作用。\n" +
                "\n" +
                "【化学成分】主含生物碱、黄酮苷、皂苷、氨基酸、多糖、菊糖、红花素、异红花素、野黄芩苷、高山黄芩素、半枝莲素、半枝莲种素、对香豆酸、原儿茶酸等成分。\n" +
                "\n" +
                "【使用禁忌】孕妇和血虚者慎服。\n" +
                "\n" +
                "【配伍药方】①治胃气痛：半枝莲30克，猪肝或鸡1只(去头及脚尖，内脏)，水、酒各半炖熟。分2～3次服。(《泉州本草》)\n" +
                "\n" +
                "②治吐血、咯血、血淋及外伤出血：半枝莲30～50克。水煎去渣，加蜂蜜调服，伤处用渣外敷。(《浙南本草选编》)\n" +
                "\n" +
                "③治慢性肾炎水肿：半枝莲鲜草30克。切细捣烂，同鸡蛋搅匀蒸熟，做成蛋饼，候冷敷脐部，每日1次，约敷6小时。(《浙南本草选编》)\n" +
                "\n" +
                "④治肝炎：鲜半枝莲15克，红枣5个。水煎服。(《浙江民间常用草药》)\n" +
                "\n" +
                "⑤治带状疱疹：半枝莲加米泔水适量捣烂，取汁外涂，每日数次。(《浙南本草选编》)\n" +
                "\n" +
                "⑥治早期肺癌、肝癌、直肠癌：半枝莲、白花蛇舌草各30克。煎服。(《安徽中草药》)");
        chineseMedicineDao.insert(medicine223);

        ChineseMedicine medicine224=new ChineseMedicine();
        medicine224.setName("半边莲");
        medicine224.setSortType("B");
        medicine224.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545924057&di=446a480a57ac69936510d0f3a13d3c15&imgtype=jpg&er=1&src=http%3A%2F%2Fimgs.bzw315.com%2FUploadFiles%2Fimage%2FNews%2F2016%2F11%2F15%2F20161115142620_2335.jpg");
        medicine224.setData("【中药名】半边莲 banbianlian\n" +
                "\n" +
                "【别名】细米草、半边花、急解索、半边菊、金菊草、箭豆草。\n" +
                "\n" +
                "【英文名】Herba Lobeliae Chinensis。\n" +
                "\n" +
                "【药用部位】桔梗科植物半边莲Lobelia chinensis Lour.的全草。\n" +
                "\n" +
                "【植物形态】多年生矮小草本，高5～15厘米。全株光滑无毛，有乳汁。根细圆柱形，淡黄白色。茎细弱匍匐，上部直立。叶互生，无柄；叶片条形或条状披针形，全缘或有疏齿。叶腋开单生淡紫色或白色小花；花冠基部合成管状，上部向一边5裂展开，中央3裂片较浅，两侧裂片深裂至基部；雄蕊5，花丝基部分离，花药彼此连合，围抱柱头，花药位于下方的两个有毛，上方的3个无毛；子房下位。蒴果顶端2瓣开裂；种子细小，多数。花期5～8月，果期8～10月。\n" +
                "\n" +
                "【产地分布】生于水田边、路沟旁、潮湿的阴坡、荒地。分布于江苏、浙江、安徽等地。\n" +
                "\n" +
                "【采收加工】夏、秋季生长茂盛时采收，洗净晒干。生用或鲜用。\n" +
                "\n" +
                "【药材性状】全长15～35厘米，但常缠成团。根茎细长圆柱形，直径1～2毫米，表面淡黄色或黄棕色，多有细纵根。根细小，侧生细纤须根。茎细长，有分枝，灰绿色，节明显，有的可见附生的细根。叶互生，无柄，绿色，呈狭披针形或长卵圆形，长1～2厘米，宽2～5毫米，叶缘有疏锯齿。花梗细长，花小，单生于叶腋，花冠筒内有白色茸毛。花萼5裂，裂片绿色线形。气微，味微甘而辛。\n" +
                "\n" +
                "【性味归经】性平，味辛。归心经、小肠经、肺经。\n" +
                "\n" +
                "【功效与作用】利尿消肿、清热解毒。属清热药下属分类的清热解毒药。\n" +
                "\n" +
                "【临床应用】用量9～15克，鲜品30～90克(捣汁服)；外用适量，捣敷。用治毒蛇咬伤、腹胀水肿、黄疸尿少、小便不利、晚期血吸虫病及肝硬化腹水、跌打损伤，痈疖疔疮。\n" +
                "\n" +
                "【药理研究】动物实验证明本品有利尿作用；对毒蛇咬伤的狗有良好的保护作用；在非经口给药时能通过颈动脉球反射性地引起呼吸兴奋，大剂量时可引起血压下降；对小鼠剪尾之出血有止血作用；对金黄色葡萄球菌、伤寒杆菌、副伤寒杆菌、福氏痢疾杆菌、大肠杆菌、绿脓杆菌均有抑制作用。对自主神经节、肾上腺髓质、延脑各中枢(尤其是呕吐中枢)、神经肌肉接头以及颈动脉体的化学感受器都有先兴奋、后抑制的作用。对离体兔心和蛙心有兴奋作用，使收缩力加强，振幅增大，高浓度时则出现暂时的兴奋，继之抑制，最后发生传导阻滞和停搏。有利胆作用。有抗蛇毒作用。具有催吐作用，口服有轻泻作用。\n" +
                "\n" +
                "【化学成分】含生物碱，主要有半边莲碱、去氧半边莲碱、氧化半边莲碱、黄酮苷、琥珀酸、L-山梗菜碱等成分。\n" +
                "\n" +
                "【使用禁忌】虚证水肿禁服。\n" +
                "\n" +
                "【配伍药方】①治漆疮：半边莲全草捣汁搽。(《湖南药物志》)\n" +
                "\n" +
                "②治黄疸，水肿，小便不利：半边莲30克，白茅根30克。水煎，分2次用白糖调服。(《江西民间草药验方》)\n" +
                "\n" +
                "③治呕泻：半边莲15克，水杨柳12克，车前草30克，萝卜12克。捣烂，开水冲服。(《湖南药物志》)\n" +
                "\n" +
                "④治偏头痛：半边莲、五爪风、梨头草各9克。水煎兑酒服。(《湖南药物志》)\n" +
                "\n" +
                "⑤治百日咳：半边莲30克，煎汤，煮猪肺一个，喝汤吃肺。(《浙江民间常用草药》)");
        chineseMedicineDao.insert(medicine224);

        ChineseMedicine medicine225=new ChineseMedicine();
        medicine225.setName("白花蛇舌草");
        medicine225.setSortType("B");
        medicine225.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=d11cf833104c510fbac9ea4801304e48/d1a20cf431adcbef205158fda6af2edda2cc9fe7.jpg");
        medicine225.setData("【中药名】白花蛇舌草 baihuasheshecao\n" +
                "\n" +
                "【别名】蛇舌草、蛇删草、目目生珠草、蛇针草、节节结蕊草。\n" +
                "\n" +
                "【英文名】Herba Hedyotidis\n" +
                "\n" +
                "【药用部位】茜草科植物白花蛇舌草Oldenlandia diffusa (Willd) Roxb.的全草。\n" +
                "\n" +
                "【植物形态】一年生小草本。茎略扁，细长。叶对生，膜质，无柄，线形，顶端急尖，仅具1条主脉；托叶基部合生，顶端芒尖。春、夏季开花；花单生或成对生于叶腋，无花梗或有短的花梗；萼管球形，4裂，裂片长圆状披针形，顶端渐尖，边缘有缘毛；花冠白色，管状，顶部4裂，裂片卵状长圆形；雄蕊4枚，着生于冠管喉部，花药突出冠管外；花柱顶端2裂，裂片扩展。蒴果膜质，扁球形，顶部有宿存的萼檐裂片。种子细小，有棱。果期8～9月。\n" +
                "\n" +
                "【产地分布】生于水田田埂和潮湿的旷地上。分布于广东、海南、广西等地。\n" +
                "\n" +
                "【采收加工】夏季采收，洗净，鲜用或晒干。\n" +
                "\n" +
                "【药材性状】全草扭缠成团状，灰绿色或灰棕色。有主根1条，须根纤细。茎细而卷曲，质脆易折断，中央有白色髓部。叶多破碎，极皱缩，易脱落；有托叶。花腋生，多具梗。气微，味淡。\n" +
                "\n" +
                "【性味归经】性凉，味微苦、微甘。归胃经、大肠经、小肠经。\n" +
                "\n" +
                "【功效与作用】清热、利湿、解毒、抗癌。属清热药下属中的清热解毒药(一说为抗肿瘤药)。\n" +
                "\n" +
                "【临床应用】用量50～100克或捣汁，煎服。外用：捣敷。用治肺热喘咳、咽喉肿痛；湿热黄疸、胃炎、胆囊炎、胆石症；肠炎、痢疾、肾盂肾炎、尿道炎、盆腔炎；热毒疮疡、肺痈、肠痈、毒蛇咬伤。内服外敷均可。\n" +
                "\n" +
                "【药理研究】增强免疫功能；有抗菌作用，抗肿瘤；对离体兔肠大剂量呈显著的抑制作用，并可对抗乙酰胆碱或肾上腺素引起的肠兴奋或抑制作用。增强肾上腺皮质功能。\n" +
                "\n" +
                "【化学成分】挥发油中提取得34种化合物，其中最主要的是对乙烯基苯酚、对乙烯基愈创木酚和芳樟醇。另含有2-甲基-3-羟基-4-甲氧基葸醌、车叶草苷酸、鸡屎藤次苷等成分。\n" +
                "\n" +
                "【使用禁忌】阴疽，脾胃虚寒，孕妇慎用。\n" +
                "\n" +
                "【配伍药方】①治急慢性腹泻：鲜白花蛇舌草120克，煎水内服。(《四川中药志》1979年)\n" +
                "\n" +
                "②治咽喉肿痛、结合膜炎：白花蛇舌草鲜全草30～60克。水煎服。(《福建中草药》)\n" +
                "\n" +
                "③治阑尾炎：白花蛇舌草30克，金银花、败酱草各18克，红藤15克。煎服。(《安徽中草药》)\n" +
                "\n" +
                "④治毒蛇咬伤：鲜白花蛇舌草全草30～60克。捣烂绞汁或水煎服。(《福建中草药》)\n" +
                "\n" +
                "⑤治胃癌，食管癌，直肠癌：白花蛇舌草75克，薏苡仁30克，黄药子10克，乌药3克，龙葵3克，乌梅6克。水煎服。(《四川中药志》1979年)\n" +
                "\n" +
                "⑥治跌打损伤：鲜白花蛇舌草120克。水酒各半煎。内服。(《江西草药手册》)\n" +
                "\n" +
                "⑦治泌尿系感染：白花蛇舌草全草30克，野菊花30克，金银花30克，石韦15克。水煎服。(《湖南药物志》)");
        chineseMedicineDao.insert(medicine225);

        ChineseMedicine medicine226=new ChineseMedicine();
        medicine226.setName("白花丹");
        medicine226.setSortType("B");
        medicine226.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=9f862e2067224f4a43947b41689efb37/0823dd54564e9258fc74dee89682d158ccbf4e77.jpg");
        medicine226.setData("【中药名】白花丹 baihuadan\n" +
                "\n" +
                "【别名】假茉莉、白雪花、天槟榔。\n" +
                "\n" +
                "【英文名】Plumbinis\n" +
                "\n" +
                "【药用部位】白花丹科植物白花丹Plumbago zeyla-nicaL．的干燥全草或根。\n" +
                "\n" +
                "【植物形态】多年生蔓生亚灌木状草本，高2～3米。茎细弱，基部木质，多分枝，有细棱，节上带红色，除具腺外，光滑无毛。单叶互生；叶柄基部扩大而抱茎；叶片纸质，卵圆形至卵状椭圆形，长4～10厘米， 宽1.5～5厘米，先端尖，基部阔楔形，无毛，全缘。穗状花序顶生或腋生，长5～25厘米；苞片短于萼，边缘为干膜质；花萼管状，绿色，长约1厘米，上部5裂，具5棱，棱间干膜质，外被腺毛，有黏性；花冠白色或白而略带蓝色，高脚碟状，管狭而长，长约2厘米，先端5裂，扩展；雄蕊5，生于喉部；子房上位，1室，柱头5裂。蒴果膜质。花期10月至翌年3月，果期2月至翌年4月。\n" +
                "\n" +
                "【产地分布】多生于气候炎热的地区，常见于阴湿的沟边或村边路旁的旷地。分布于西南及福建、台湾、广东、广西等地。\n" +
                "\n" +
                "【采收加工】全年均可采，切段晒干或鲜用。\n" +
                "\n" +
                "【药材性状】本品主根呈细长圆柱形，长可达30厘米，直径约5毫米，略弯曲，表面灰褐色或棕红色。茎圆柱形，直径2毫米，表面淡褐色或黄绿色，具细纵棱，节明显，质硬，易折断，断面皮部呈纤维状，淡棕黄色，中间髓部淡黄白色或白色，质松。叶片皱缩、破碎，多已脱落，完整叶片展开后呈卵形或卵状长圆形，淡绿色或黄绿色。花序穗状，顶生或腋生，花序有腺体，花冠淡黄棕色。气微，味辛辣。\n" +
                "\n" +
                "【性味归经】性温，味辛、苦、涩。有毒。归经无。\n" +
                "\n" +
                "【功效与作用】祛风除湿，行气活血，解毒消肿。属活血化瘀药下属分类的活血止痛药。\n" +
                "\n" +
                "【临床应用】内服：煎汤，9～15克。外用：适量，煎水洗；或捣敷；或涂擦。主治风湿痹痛，心胃气痛，肝脾肿大，血瘀经闭，跌打扭伤，痈肿瘰疬，疥癣瘙痒，毒蛇咬伤。\n" +
                "\n" +
                "【药理研究】有抗生育作用。对离体小肠及子宫，小量兴奋，中量先兴奋后麻痹，大量则一开始即呈麻痹作用，尤其妊娠子宫；有抗微生物作用。对家兔的呼吸、血压有经度抑制，对离体蛙心有直接麻痹作用，心跳停于扩张期。有抗凝血活性。小量对中枢神经系统有兴奋作用。\n" +
                "\n" +
                "【化学成分】本品主要含白花丹素、白花丹酸、香草酸、β-谷固醇、异白花丹酮、白花丹酮、3,6'-双白花丹素等成分。\n" +
                "\n" +
                "【使用禁忌】孕妇禁服。外用时间不宜过长，以免起疱。\n" +
                "\n" +
                "【配伍药方】①治疮疖，毒蛇咬伤：白花丹鲜叶捣敷，有灼热感即取下。(《红河中草药》)\n" +
                "\n" +
                "②治厚皮癣：白花丹茎叶捣烂敷。（《广西药用植物图志》）\n" +
                "\n" +
                "③治眼翳：鲜白花丹叶捣烂贴印堂。见出水泡即除去。（《福建药物志》）\n" +
                "\n" +
                "④治脚底硬结疼痛：白花丹鲜叶1握，稀饭1撮，食盐少许。捣烂涂贴，日换1次。（《福建民间草药》）\n" +
                "\n" +
                "⑤治扭挫伤：白花丹根90克，白酒或60%乙醇500毫升浸泡3～5天，每日搽数次。（《湖南药物志》）");
        chineseMedicineDao.insert(medicine226);

        ChineseMedicine medicine227=new ChineseMedicine();
        medicine227.setName("白英");
        medicine227.setSortType("B");
        medicine227.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545924580&di=5fbef241d111a85e518d63e5b1d17717&imgtype=jpg&er=1&src=http%3A%2F%2Ffile.cnkang.com%2Fcnkfile1%2FM00%2F16%2F43%2FooYBAFmanW6AAvxXAALRSXmPqXs61.jpeg");
        medicine227.setData("【中药名】白英 baiying\n" +
                "\n" +
                "【别名】白毛藤、金钱绿毛龟、望冬红、毛葫芦。\n" +
                "\n" +
                "【英文名】Herba Solani Lyrati。\n" +
                "\n" +
                "【药用部位】茄科植物白英Solanum lyratum Thunb.的全草。\n" +
                "\n" +
                "【植物形态】多年生蔓性半灌木。茎长达5米，基部木质化，上部草质，具细毛。叶互生，上部叶多作戟状3裂或羽状多裂；下部叶长方形或卵状长方形，基部心脏形，先端尖，全缘，上面鲜绿色，下面较淡，两面均有细毛散生，沿叶脉较密；叶柄被细毛。聚伞花序生于枝顶或侧生与叶对生；枝梗、花柄及花均密被长柔毛，花柄细长；花萼漏斗状，萼片5，自基部向下反折，卵形或长方状披针形，顶端尖；雄蕊5枚，着生于花冠筒口，花丝短而扁，基部合生；雌蕊1，子房卵形，花柱细长，柱头半球形。浆果卵形或球形，初绿色，熟时红色至黑色。种子白色，扁圆。花期9～10月，果期11月。\n" +
                "\n" +
                "【产地分布】野生于路边或灌木丛中草。国内均有分布。\n" +
                "\n" +
                "【采收加工】夏、秋两季采收，洗净，晒干。\n" +
                "\n" +
                "【药材性状】茎类圆柱形，直径2～7毫米，表面黄绿色至暗棕色，密被灰白色茸毛，在较粗的茎上茸毛极少或无，具纵皱纹，且有光泽；质硬而脆，断面淡绿色，纤维性，中央空洞状。叶皱缩卷曲，密被茸毛。有的带淡黄色至暗红色果实。气微，味微苦。\n" +
                "\n" +
                "【性味归经】性寒，味甘、苦。归肝经、胆经、肾经。\n" +
                "\n" +
                "【功效与作用】清热、利湿、祛风、解毒。属祛风湿药下属分类的祛风湿清热药。\n" +
                "\n" +
                "【临床应用】用量15～30克，煎服；外用适量，煎水洗、捣敷或捣汁涂。用治疟疾、黄疸、水肿、淋病、风湿关节痛、丹毒、疔疮。\n" +
                "\n" +
                "【药理研究】有抗肿瘤及抗真菌作用。体外试验表明，龙葵碱对葡萄球菌和绿脓杆菌有抑制作用。\n" +
                "\n" +
                "【化学成分】含龙葵碱、花色苷及其苷元等成分。\n" +
                "\n" +
                "【使用禁忌】体虚无湿热者忌用。\n" +
                "\n" +
                "【配伍药方】①治肺癌：白英、垂盆草各30克。水煎服，每日1剂。(《全国中草药汇编>)\n" +
                "\n" +
                "②治风火赤眼：白英鲜叶捣烂，调人乳外敷眼睑。(《福建中草药》)\n" +
                "\n" +
                "⑧治中耳化脓：白英绞汁，滴耳中。(《湖南药物志》)\n" +
                "\n" +
                "④治疥疮：白英全草30～40克(干品24～36克)，和肥猪肉180克，酌加水煎，分两次吃下。(《福建民间草药》)\n" +
                "\n" +
                "⑤治咽喉肿痛，痈肿疮毒，淋巴结结核：白英、萝藦各30克。水煎服。(《陕甘宁青中草药选》)\n" +
                "\n" +
                "⑥治皮肤瘙痒症：白英、苦楝树叶，各适量，水煎汤洗患处。(《青岛中草药手册》)\n" +
                "\n" +
                "⑦治风湿关节痛：白英30克，忍冬30克，五加皮30克，好酒500克泡服。(《贵阳民间药草》)");
        chineseMedicineDao.insert(medicine227);

        ChineseMedicine medicine228=new ChineseMedicine();
        medicine228.setName("北刘寄奴");
        medicine228.setSortType("B");
        medicine228.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545924727&di=bf0e1cfa1665e594e4d586861c8f6b33&imgtype=jpg&er=1&src=http%3A%2F%2Ffile.cnkang.com%2Fcnkfile1%2FM00%2F22%2F0C%2FooYBAFrJgbmAVBUGAACHn8fsSew85.jpeg");
        medicine228.setData("【中药名】北刘寄奴 beiliujinu\n" +
                "\n" +
                "【别名】金钟茵陈、吹风草、鬼麻油、土茵陈。\n" +
                "\n" +
                "【英文名】Herba Siphonostegiae\n" +
                "\n" +
                "【药用部位】玄参科植物阴行草Siphonostegia chinensis Benth.的干燥全草。\n" +
                "\n" +
                "【植物形态】一年生草本，高30～70厘米。全株密被锈色短毛。根有分枝。茎单一，直立，上部多分枝，稍具棱角，茎上部带淡红色。叶对生；无柄或具短柄；叶片二回羽状全裂，条形或条状彼针形，长约8毫米，宽1～2毫米。花对生于茎枝上部，成疏总状花序；花梗极短，有1对小苞片，线形；萼筒长1～1.5厘米，有10条显著的主脉，萼齿5，长为筒部的1/4～1/3；花冠上唇红紫色，下唇黄色，长2～2.5厘米，筒部伸直，上唇镰状弯曲，额稍圆，背部密被长纤毛，下唇先端3裂，褶襞高拢成瓣状，外被短柔毛；雄蕊4，二强，花丝基部被毛，下部与花冠筒合生；花柱长，先端稍粗而弯曲。蒴果宽卵圆形，先端稍扁斜，包于宿存萼内。种子黑色。花期7～8月，果期8～10月。\n" +
                "\n" +
                "【产地分布】生于山坡及草地上。遍布全国各地。\n" +
                "\n" +
                "【采收加工】秋季采收，除去杂质，晒干。\n" +
                "\n" +
                "【药材性状】本品根短而弯曲，稍有分枝。茎圆柱形，表面灰棕色或棕黄色，长30～80厘米，密被锈色短毛，具棱；质脆，易折断，断面黄白色，边缘呈纤维性，中央为白色疏松的髓。叶对生，上部的叶多互生，多已破碎脱落，完整者呈羽状深裂，深绿色。总状花序顶生，花有短梗，带筒状花萼，长约1.5厘米，表面有10条隆起的纵棱，顶端5裂。棕黄色唇形花冠残留。蒴果长椭圆形，棕黑色，具多数纵纹，质脆易破裂。种子细小，多数，表面皱缩。气微，味淡。\n" +
                "\n" +
                "【性味归经】性寒，味苦。归脾经、胃经、肝经、胆经。\n" +
                "\n" +
                "【功效与作用】活血祛瘀，通经止痛，凉血，止血，清热利湿。属活血化瘀药下属分类的活血调经药。\n" +
                "\n" +
                "【临床应用】内服：煎汤，9～15克，鲜品30～60克；或研末。外用：适量，研末调敷。用于跌打损伤，外伤出血，瘀血经闭，月经不调，产后瘀痛，癥瘕积聚，血痢，血淋，湿热黄疸，水肿腹胀，白带过多。\n" +
                "\n" +
                "【药理研究】具有保肝利胆、降低血清胆固醇作用，有抗菌作用。\n" +
                "\n" +
                "【化学成分】本品含10-对香豆酰桃叶珊瑚苷，8-异马钱素和阿克苷。全草还含挥发油。\n" +
                "\n" +
                "【使用禁忌】体弱者慎用，孕妇忌服。\n" +
                "\n" +
                "【配伍药方】①治急性黄疸型肝炎：北刘寄奴30克。煎服。（《安徽中草药》）\n" +
                "\n" +
                "②治肠炎、痢疾：北刘寄奴30克，委陵菜15克。煎服。（《、安徽中草药》）\n" +
                "\n" +
                "③治淋浊：北刘寄奴15克，白茯苓12克。水煎。（《吉林中草药》）\n" +
                "\n" +
                "④治热闭，小便不利：北刘寄奴30～45克。水煎，调冬蜜服。日服1～2次。（《福建民间草药》）\n" +
                "\n" +
                "⑤治白带：北刘寄奴30克。水煎，冲黄酒，红糖服。（《浙江民间常用草药》）\n" +
                "\n" +
                "⑥治感冒，咳嗽：北刘寄奴9～15克。水煎服。（《浙江民间常用草药》）\n" +
                "\n" +
                "⑦治脚癣：鲜北刘寄奴适量。水煎洗患处。（《福建药物态》）\n" +
                "\n");
        chineseMedicineDao.insert(medicine228);

        ChineseMedicine medicine229=new ChineseMedicine();
        medicine229.setName("蓖麻子");
        medicine229.setSortType("B");
        medicine229.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=91b2c83e4c086e067ea5371963611091/8435e5dde71190ef5fedc6d0ce1b9d16fdfa60ae.jpg");
        medicine229.setData("【中药名】蓖麻子 bimazi\n" +
                "\n" +
                "【别名】蓖麻仁、大麻子、红大麻子。\n" +
                "\n" +
                "【英文名】Ricini Semen。\n" +
                "\n" +
                "【药用部位】大戟科植物蓖麻Ricinus communis L.的干燥成熟种子。\n" +
                "\n" +
                "【植物形态】一年生草本。株高1.5～2米。茎直立，分枝，中空。叶盾形，直径20～60厘米，掌状5～11裂，裂片卵形或窄卵形，缘具齿，无毛，叶柄长，托叶合生，早落，花单性，雌雄同株，无花瓣，聚伞圆锥花序，长约20厘米，顶生或与叶对生。雄花的萼3～5裂，直径约1厘米。雌花萼5裂，裂片不等大。蒴果，长圆形或近球形，长1.5～2.5厘米，直径1～1.4厘米。花期7～8月，果期9～10月。\n" +
                "\n" +
                "【产地分布】全国各地均有栽培。\n" +
                "\n" +
                "【采收加工】秋季采摘成熟果实，晒干，除去果壳，收集种子。\n" +
                "\n" +
                "【药材性状】呈椭圆形或卵形，稍扁，长0.9～1.8厘米，宽0.5～1厘米。表面光滑，有灰白色与黑褐色或黄棕色与红棕色相间的花斑纹。一面较平，一面较隆起，较平的一面有1条隆起的种脊；一端有灰白色或浅棕色突起的种阜。种皮薄而脆。胚乳肥厚，白色，富油性，子叶2，菲薄。气微，味微苦辛。\n" +
                "\n" +
                "【性味归经】性平，味甘、辛。归大肠经、肺经。\n" +
                "\n" +
                "【功效与作用】消肿拔毒，泻下通滞。属泻下药下属分类的润下药。\n" +
                "\n" +
                "【临床应用】内服：煎汤，用量2～5克，外用适量。用治大便燥结，痈疽肿毒，喉痹，瘰疬。\n" +
                "\n" +
                "【药理研究】泻下；抗肿瘤；抗艾滋病毒；抑制吞噬细胞功能；抑制细胞免疫；降血压与呼吸抑制；有退热作用。\n" +
                "\n" +
                "【化学成分】种子含蛋白质18%～26%，脂肪油64%～71%，碳水化合物2%，酚性物质2.50%，蓖麻毒蛋白及蓖麻碱0.087%～0.15%。种子还含凝集素和脂肪酶。种皮含30-去甲羽扇豆-3β-醇-20-酮。还含三酰甘油、亚油酸、磷脂酰胆碱、碱性蓖麻毒蛋白等成分。\n" +
                "\n" +
                "【使用禁忌】孕妇及便滑者忌服。\n" +
                "\n" +
                "【配伍药方】①治面上雀子斑：蓖麻子、密陀僧、硫黄各6克。上用羊髓和匀，临睡敷上、次早洗去。(《体仁汇编》)\n" +
                "\n" +
                "②治犬咬伤：蓖麻子五十粒。去壳。以井水研膏，先以盐水洗咬处，次以蓖麻膏贴。(《袖珍方》)\n" +
                "\n" +
                "③治风气头痛不可忍：乳香、蓖麻子等分。捣饼，随左右贴太阳穴。(《纲目》)\n" +
                "\n" +
                "④治耳聋：蓖麻一百颗(去皮)，大枣五枚(去皮、核)。上二味熟捣膏如杏大。纳耳中。(《千金要方》)\n" +
                "\n" +
                "⑤治子宫脱下：蓖麻子、枯矾等分。为末，安纸上托入.仍以蓖麻仁十四枚，研膏涂顶心。(《摘元方》)");
        chineseMedicineDao.insert(medicine229);

        ChineseMedicine medicine230=new ChineseMedicine();
        medicine230.setName("荜澄茄");
        medicine230.setSortType("B");
        medicine230.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=e4764ecff0deb48fef64a98c9176514c/0b55b319ebc4b7455a2563b8ccfc1e178b8215ee.jpg");
        medicine230.setData("【中药名】荜澄茄 bichengqie\n" +
                "\n" +
                "【别名】澄茄、毕澄茄、毕茄、山胡椒、味辣子、山苍子、木姜子、臭樟子。\n" +
                "\n" +
                "【英文名】Litseae Fructus。\n" +
                "\n" +
                "【药用部位】樟科植物山鸡椒Litsea cubeba (Lour.) Pers.的成熟果实。\n" +
                "\n" +
                "【植物形态】灌木或小乔木。高3～10米，有强烈香气。树皮灰褐色，小枝绿色。单叶互生，薄纸质，叶片披针形或长椭圆形，叶柄细。雌雄异株，伞形花序式的聚伞花序成束生长于叶腋，先叶开放，总花梗纤细，总苞片4，花4～6朵；花被裂片6，倒卵形；雄花直径约3毫米，雄蕊9枚，内向，3轮，每轮3枚，第3轮雄蕊基部有2腺体，花药4室，瓣裂，中央有退化雌蕊；雌花直径约2毫米，子房卵形，花柱短，柱头头状，有退化雄蕊6～12枚，呈舌状。浆果状核果，近球形，直径4～6毫米，熟时黑色，果梗长3～5毫米，总梗长7～10毫米。种子有脊。花期4～5月，果期7～11月。\n" +
                "\n" +
                "【产地分布】生于向阳山坡、林缘、灌木丛中。分布于江苏、浙江、云南等地。\n" +
                "\n" +
                "【采收加工】于秋季果实成熟时采收，除去杂质，晒干。\n" +
                "\n" +
                "【药材性状】类球形，直径4～6毫米。表面棕褐色至黑褐色，有网状皱纹。基部偶有宿萼及细果梗。除去外皮可见硬脆的果核，种子1粒，子叶2，黄棕色，富油性。气芳香，味稍辣而微苦。用量1.5~3克，内服煎汤。\n" +
                "\n" +
                "【性味归经】性温，味辛。归脾经、胃经、肾经、膀胱经。\n" +
                "\n" +
                "【功效与作用】温中散寒，行气止痛。属温里药。\n" +
                "\n" +
                "【临床应用】用量1.5～3克，内服煎汤。用治胃寒呕逆、脘腹冷痛、寒疝腹痛、寒湿、小便浑浊。\n" +
                "\n" +
                "【药理研究】具有调节胃肠运动、抗胃溃疡、镇静、镇痛及抗菌作用。药理实验表明，挥发油有松弛气管平滑肌及平喘的作用；对被动皮肤过敏反应有抑制作用；对黏膜有局部刺激作用，并能吸收，对泌尿道及呼吸道黏膜也能发挥此种作用；口服其挥发油，对尿路有某些防腐作用。\n" +
                "\n" +
                "【化学成分】含挥发油，油中主要成分为枸橼醛、甲基庚烯酮、芳樟醇、枸橼烯、香茅醛、莰烯，α-蒎烯、桧烯。此外，尚含柠檬醛、木兰箭毒碱、脂肪油以及少量柠檬烯等。\n" +
                "\n" +
                "【使用禁忌】阴虚血分有热，发热咳嗽禁用。\n" +
                "\n" +
                "【配伍药方】①治疗胃柿石症：荜澄茄、吴茱萸、鸡内金各5克，干姜、熟附片、陈皮、槟榔、莱菔子、木香、三棱、莪术各10克，焦山楂、丹参各20克，青黛3克。水煎服，每日1剂。外敷方：皮硝适量(视结石大小而定)，用棉皮包，外敷胃脘部，每日换1 次。治疗20例，全部治愈。疗程长者10天(10例)，疗程短者6天(10例)，平均8天。〔《浙江中医杂志》1990;25(10)：441〕\n" +
                "\n" +
                "②治疗脾胃虚弱，胸膈不快，不进饮食：荜澄茄不拘多少。为细末，姜汁打神曲末煮糊为丸，如桐子大。每饭七十丸，食后淡姜汤下。(《济生方》荜澄茄丸)\n" +
                "\n" +
                "③治疗伤寒呃噫日夜不定者：荜澄茄三分，高良姜三分。二物捣罗为散。每服二钱，水六分，煎十余沸，入少许醋搅匀，和滓如茶，热呷。(《本草图经》)\n" +
                "\n"
                );
        chineseMedicineDao.insert(medicine230);

        ChineseMedicine medicine231=new ChineseMedicine();
        medicine231.setName("荜茇");
        medicine231.setSortType("B");
        medicine231.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545925128&di=32748c79c4fbe7b2d9675ea6b094b562&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.bctyy.net%2Fupload%2Fphoto%2F20170902%2F15043372942240.jpg");
        medicine231.setData("【中药名】荜茇 biba(bo)\n" +
                "\n" +
                "【别名】荜拨、荜拨梨、椹圣、鼠尾。\n" +
                "\n" +
                "【英文名】Fructus Piperis Longi\n" +
                "\n" +
                "【药用部位】胡椒科植物荜茇Piper longum L.的近成熟或成熟果穗。\n" +
                "\n" +
                "【植物形态】多年生草质藤本。茎下部匍匐，枝横卧，有纵棱和沟槽，幼时密被粉状短柔毛。单叶，互生，纸质，叶片卵圆形、卵形或卵状长圆形，长6～12厘米，宽2.5～10厘米，先端短尖或短渐尖，基部心形或耳状，两面叶脉上被极细的粉状短柔毛，下面密而显著，基出脉5～7条；叶柄下部的长达9厘米，中部的长1～2厘米，顶端近无柄，具密柔毛，花单性，雌雄异株，穗状花序与叶对生，无花被；雄蕊总花梗长2～3厘米，被粉状短柔毛，穗长4～6厘米，直径约3毫米，花直径约1.5毫米，苞片1，近圆形，雄蕊2枚，花药椭圆形，2室，花丝短；雌穗总花梗长1.5厘米密被柔毛，穗长1.5～2.5厘米，直径约4毫米，于果期延长，苞片圆形，花的直径不及l毫米，子房上位，倒卵形，1室，下部与花序轴合生，无花柱，柱头3。浆果卵形，先端尖，基部嵌陷于花序轴内与之结合，呈圆形，顶端有脐状突起，成熟时为红褐色。花期7～9月，果期10月至翌年春季。\n" +
                "\n" +
                "【产地分布】生于热带林下。广西、广东、海南、福建等地有栽培。\n" +
                "\n" +
                "【采收加工】果穗由绿变黑时采摘，除去杂质，晒干。\n" +
                "\n" +
                "【药材性状】圆柱形，稍弯曲，由多数小浆果集合而成。表面黑褐色或棕色，有斜向排列整齐的小突起，基部有果穗梗残存或脱落。质硬而脆，易折断，断面不整齐，颗粒状。小浆果球形。有特异香气，味辛辣。\n" +
                "\n" +
                "【性味归经】味辛，性热。归胃经、大肠经。\n" +
                "\n" +
                "【功效与作用】温中散寒、下气止痛。属温里药。\n" +
                "\n" +
                "【临床应用】用量1.5～3克，煎服。用治脘腹冷痛、呕吐、泄泻、偏头痛；外治牙痛。荜茇，捣细罗为散，每于食前用清粥饮调下半钱，治痰饮恶心(《圣惠方》)。\n" +
                "\n" +
                "【药理研究】醇提物具有抗溃疡、抗心肌缺血作用。挥发油能抗心律失常、调血脂，并能抑制中枢神经。耐缺氧、镇静、镇痛、解热、广谱抗菌、对冠状血管和肠管平滑肌有很强的松弛作用等。\n" +
                "\n" +
                "【化学成分】主含酰胺类化合物、木脂素、挥发油、胡椒碱、四氢胡椒酸、棕榈酸、荜茇明宁碱、长柄胡椒碱、荜茇明碱等成分。\n" +
                "\n" +
                "【使用禁忌】阴虚火旺者禁服。\n" +
                "\n" +
                "【配伍药方】①治冷痰饮恶心：荜茇，捣细罗为散。每于食前用清粥饮调下1.5克。(《圣惠方》)\n" +
                "\n" +
                "②治偏头痛：荜茇为末。令患者口中含温水，左边痛令左鼻吸一字，右边痛令右鼻吸一字。(《经验后方》)\n" +
                "\n" +
                "③治牙痛：荜茇、胡椒。上二味等分，捣罗为末，化蜡丸，如麻子大，每用一丸，纳蛀孔中。(《圣济总录》荜茇丸)\n" +
                "\n");
        chineseMedicineDao.insert(medicine231);

        ChineseMedicine medicine232=new ChineseMedicine();
        medicine232.setName("柏子仁");
        medicine232.setSortType("B");
        medicine232.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=ffa24fce59afa40f28cbc68fca0d682a/37d3d539b6003af3b6d5249c362ac65c1138b6db.jpg");
        medicine232.setData("【中药名】柏子仁 baiziren\n" +
                "\n" +
                "【别名】柏实、柏子、侧柏子、柏仁。\n" +
                "\n" +
                "【英文名】Platycladi Semen。\n" +
                "\n" +
                "【药用部位】柏科植物侧柏Platycladus orientails (L.) Franco的种仁。\n" +
                "\n" +
                "【植物形态】常绿乔木。株高20米。树皮浅灰色。枝条开展，小枝扁平，排列成复叶状。叶全为鳞片状，长1～3 毫米，交互对生。雌雄同株，球花七于枝顶。雄球花有6对交互对生的雄蕊，花药2～4。雌球花有4对交互对生的珠鳞.仅中间两对珠鳞各有胚珠1～2枚。球果当年成熟，熟时开裂，卵球形，径1.5～2厘米，种鳞4对，木质，近扁平，背部上方有一弯曲的钩状尖头，中部种鳞各有1～2粒种子。种子长卵形，长约4毫米。花期4～5月，当年10月球果成熟。\n" +
                "\n" +
                "【产地分布】生于海拔300~3300米的平原、山坡或山崖。分布于除青海、新疆外的全国各地\n" +
                "\n" +
                "【采收加工】秋、冬季采收成熟种子，晒干，除去种皮，收集种仁。\n" +
                "\n" +
                "【药材性状】长卵形或椭圆形。表面黄白色或淡黄棕色，外包膜质内种皮，顶端略尖，有深褐色的小点，基部钝圆。质软，富油性。气微香，味淡。\n" +
                "\n" +
                "【性味归经】性平，味甘。归心经、肾经、大肠经。\n" +
                "\n" +
                "【功效与作用】养心安神、止汗、润肠。属安神药下属分类的养心安神药。\n" +
                "\n" +
                "【临床应用】用量3~9克。用治虚烦失眠、心悸怔忡、阴虚盗汗、肠燥便秘。\n" +
                "\n" +
                "【药理研究】改善记忆再现障碍、记忆消去及获得障碍。\n" +
                "\n" +
                "【化学成分】种子含皂苷、脂肪油及挥发油。还含谷甾醇、柏木醇、红松内酯、脂肪酸等化合物。\n" +
                "\n" +
                "【使用禁忌】便溏及痰多者慎服。\n" +
                "\n" +
                "【配伍药方】①治老人虚秘：柏子仁、大麻子仁、松子仁等分。同研，溶白蜡丸梧桐子大，以少黄丹汤服二三十丸，食前服。(《本草衍义》)\n" +
                "\n" +
                "②治小儿囟开不合：防风45克，柏子仁、白及各30克。上为细末，用乳汁调涂囟门上，每日1次。(《千金要方》柏仁散)\n" +
                "\n" +
                "③治视力减退：柏子仁、猪肝，加适量猪油蒸后内服。(《苗族药物集》)\n" +
                "\n" +
                "④治脱发：当归、柏子仁各250克，共研细末，炼蜜为丸，每日3次，每次饭后服6～9克。(《全国中草药新医疗法展览会技术资料选编》)\n" +
                "\n" +
                "⑤治腮腺炎，疮肿：鲜柏子仁捣烂，蛋清调敷患处。(《青岛中草药手册》)");
        chineseMedicineDao.insert(medicine232);

        ChineseMedicine medicine233=new ChineseMedicine();
        medicine233.setName("补骨脂");
        medicine233.setSortType("B");
        medicine233.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545925636&di=988e6edb4fc3d05892bee1fd89a317be&imgtype=jpg&er=1&src=http%3A%2F%2Fimg000.hc360.cn%2Fhb%2FMTQ2ODQ2ODg0MDAyMC0xMjg4MjEzMTc2.jpg");
        medicine233.setData("【中药名】补骨脂 buguzhi\n" +
                "\n" +
                "【别名】破故纸、胡韭子、婆固脂、补骨鸱、黑故子。\n" +
                "\n" +
                "【英文名】Psoraleae Fructus。\n" +
                "\n" +
                "【药用部位】豆科植物补骨脂Psoralea corylifoliaL.的成熟果实。\n" +
                "\n" +
                "【植物形态】一年生草本，全体被黄白色毛及黑褐色腺点。茎直立，枝坚硬，具纵棱。叶互生，枝端常侧生小叶1片；叶阔卵形或三角状卵形，先端圆形或钝，基部心形、斜心形或圆形，边缘有粗阔齿，叶两面均有显著的黑色腺点；叶柄被白色茸毛；托叶成对，三角状披针形，膜质。花多数，密集成穗状的总状花序；花轴腋生；萼钟状，基部联合呈管状，先端5齿，被黑色腺点；花冠蝶形，淡紫色或黄色；雄蕊10，1束，花药小；雌蕊1，子房上位，倒卵形或线形，花柱丝状。荚果椭圆形，有宿存花萼，果皮黑色，种子1，气香而腥。花期7～8月，果期9～10月。\n" +
                "\n" +
                "【产地分布】生于山坡、溪边或田边。主产于河南、四川、安徽等地。\n" +
                "\n" +
                "【采收加工】秋季果实成熟时采收，除去杂质，晒干。\n" +
                "\n" +
                "【药材性状】扁圆状肾形，一端略尖，少数有宿萼。长4～5.5毫米，宽2～4毫米，厚约1毫米。表面黑棕色或棕褐色，具微细网纹，在放大镜下可见众多点状凹凸纹理。质较硬脆，剖开后可见果皮与外种皮紧密贴生，厚不及0.5毫米，除去果皮后，可见种脐小点状，位于种子凹侧的上端略下处，合点位于另一端，种脊不明显。外种皮质较硬，灰白色，无胚乳，子叶两枚，肥厚，淡黄色至淡黄棕色，其内外表面常可见白色物质；胚很小，可见。宿萼基部联合，上端5裂，灰黄色，具茸毛，并密布褐色腺点。气芳香特异，味苦微辛。\n" +
                "\n" +
                "【性味归经】性温，味辛、苦。归肾经、脾经。\n" +
                "\n" +
                "【功效与作用】温肾助阳、纳气、止泻。属补虚药下属分类的补阳药。\n" +
                "\n" +
                "【临床应用】用量3～10克，外用适量。用治肾阳不足、命门火衰之腰膝冷痛、阳痿、遗精、尿频、脾肾阳虚泄泻、肾不纳气之虚喘、白癜风等。\n" +
                "\n" +
                "【药理研究】本品能扩张冠状动脉，兴奋心脏，提高心脏功率；能收缩子宫及缩短出血时间，减少出血量；抗早孕和有雌激素样作用；有致光敏作用，内服或外涂皮肤，经日光或紫外线照射，可使局部皮肤色素沉着。此外，尚有抗肿瘤、抗衰老、抑菌、杀虫及雌激素样作用。\n" +
                "\n" +
                "【化学成分】含香豆精类，主要有补骨脂素及异补骨脂素(白芷素)等，还含有查耳酮类、黄酮类、单萜酚类、花椒毒素、异补骨脂双氢黄酮、补骨脂双氢黄酮、异补骨脂查耳酮、补骨脂乙素、豆固醇、补骨脂酚、亚油等多种成分。\n" +
                "\n" +
                "【使用禁忌】阴虚火旺者忌服。\n" +
                "\n" +
                "【配伍药方】①治妊娠腰痛，状不可忍：补骨脂不以多少，瓦上炒令香熟，为末。嚼核桃肉半个，空心，温酒调下6克。(《妇人良方》通气散)\n" +
                "\n" +
                "②治打坠凝瘀，腰痛通用：补骨脂(炒香，研)、茴香(炒)、辣桂等分。上为末。每服6克，热酒调，食前进。(《直指方》茴香酒)\n" +
                "\n" +
                "③治妇人血崩：补骨脂(炒黄)、蒲黄(炒)、千年石灰、大黄各等分，为细末。每服9克，空心，用热酒调服。立止。(《重订瑞竹堂经验方》蒲黄散)\n" +
                "\n" +
                "④治赤白带下：补骨脂、石菖蒲等分(并锉，炒)。上为末。每服6克，用菖蒲浸酒调，温服。(《妇人良方》破故纸散)\n" +
                "\n" +
                "⑤治牙痛日久，肾虚也：补骨脂60克，青盐15克。炒，研，擦之。(《御药院方》)");
        chineseMedicineDao.insert(medicine233);

        ChineseMedicine medicine234=new ChineseMedicine();
        medicine234.setName("白扁豆");
        medicine234.setSortType("B");
        medicine234.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545925786&di=5f73b4d1dd8d1c6f9168868f7b8ebef2&imgtype=jpg&er=1&src=http%3A%2F%2Fpic.pingguolv.com%2Fuploads%2Fallimg%2F181105%2F77-1Q105161447.jpg");
        medicine234.setData("【中药名】白扁豆 baibiandou\n" +
                "\n" +
                "【别名】扁豆、藊豆、南扁豆、蛾眉豆、眉豆。\n" +
                "\n" +
                "【英文名】Semen Dolichoris Album。\n" +
                "\n" +
                "【药用部位】豆科草本植物扁豆Dolichos lablab L.的成熟种子。\n" +
                "\n" +
                "【植物形态】年生缠绕草本，茎近光滑。三出复叶互生，具长柄，小叶片广阔卵形，先端尖，基部广楔形或截形，全缘，两面被疏短柔毛。总状花序腋生，通常2～4朵聚生于花序轴的节上；小苞片2，早落；花萼钟状，萼齿5，边缘密被白色柔毛；花冠蝶形，白色或淡紫色；雄蕊10，2束；子房线形，被柔毛，基部有腺体，柱头头状，疏生白色短毛。荚果长椭圆形，扁平，微弯曲，先端具弯曲的喙。花期7～8月，果期9月。\n" +
                "\n" +
                "【产地分布】主产于江苏、河南、安徽、浙江等地。\n" +
                "\n" +
                "【采收加工】立冬前后采摘成熟荚果，晒干，取出种子，再晒干。\n" +
                "\n" +
                "【药材性状】扁椭圆形或扁卵圆形，长0.8～1.3厘米，宽6～9毫米。表面淡黄白色或淡黄色，平滑，略有光泽，有时可见棕黑色斑点，一端有隆起的白色眉状种阜，剥去后可见凹陷的种脐，紧接种阜的一端有珠孔，另端有短的种脊。质坚硬。种皮薄而脆，子叶肥厚，黄白色，角质。气微，味淡，嚼之有豆腥气。\n" +
                "\n" +
                "【性味归经】性微温，味甘。归脾经、胃经。\n" +
                "\n" +
                "【功效与作用】健脾化湿、和中消暑。属补虚药下分类的补气药。\n" +
                "\n" +
                "【临床应用】用量9～15克，煎汤内服；或生品捣研水绞汁；或入丸、散。外用：适量，捣敷。用治脾虚湿盛、运化失常而见食少便溏或泄泻；脾虚而湿浊下注，白带过多；暑湿吐泻等。扁豆内含毒性蛋白，生用有毒，加热后毒性大大减弱。\n" +
                "\n" +
                "【药理研究】抗菌、抗病毒；增强细胞免疫功能。100%本品煎剂用平板纸片法，对痢疾杆菌有抑制作用，对食物中毒引起的呕吐、急性胃肠炎有解毒作用。含两种不同的植物血球凝集素A、凝集素B，其中凝集素A为有毒成分，但加热后毒性大为减弱，凝集素B有抗胰蛋白酶的活性。\n" +
                "\n" +
                "【化学成分】含油0.62%，内有棕榈酸、亚油酸、反油酸、油酸、硬脂酸等，另含甾体、葫芦巴碱、花生酸、山萮酸、棉子糖、胡萝卜素、植物凝集素等成分。\n" +
                "\n" +
                "【使用禁忌】不宜多食，以免雍气滞脾。生用研末服宜慎。\n" +
                "\n" +
                "【配伍药方】①治心脾肠热，口舌干燥生疮：白扁豆(炒)、蒺藜子(炒)各60克。上二味，粗捣筛。每服15克，水一盏半，煎至一盏，去滓，日三服，不拘时。(《圣济总录》扁豆汤)\n" +
                "\n" +
                "②治一切药毒：白扁豆(生)晒干为细末，新汲水调下6～9克。(《百一选方》)\n" +
                "\n" +
                "③治慢性肾炎，贫血：白扁豆30克，红枣20粒。水煎服。(《福建药物志》)\n" +
                "\n" +
                "④治霍乱：白扁豆一升，香薷一升。上二味，以水六升，煮取二升，分服。单用亦得。(《千金要方》)\n" +
                "\n" +
                "⑤治疖肿：鲜白扁豆适量：加冬蜜少许，同捣烂敷患处。(《福建药物志》)");
        chineseMedicineDao.insert(medicine234);

        ChineseMedicine medicine235=new ChineseMedicine();
        medicine235.setName("白果");
        medicine235.setSortType("B");
        medicine235.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546070096&di=98ede48f6998fd302c62a8ef572b6e90&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.szthks.com%2Flocalimg%2F687474703a2f2f6777312e616c6963646e2e636f6d2f62616f2f75706c6f616465642f69362f5431717277624661705858585858585858585f2121302d6974656d5f7069632e6a7067.jpg");
        medicine235.setData("【中药名】白果 baiguo\n" +
                "\n" +
                "【别名】银杏核、公孙树子、鸭脚树子、灵眼、佛指甲。\n" +
                "\n" +
                "【英文名】Ginkgo Semen。\n" +
                "\n" +
                "【药用部位】银杏科植物银杏Ginkgo bilobaL.的除去外种皮的成熟种子。\n" +
                "\n" +
                "【植物形态】落叶高大乔木。树干直立。叶簇生于短枝或螺旋状散生于长枝上；叶片扇形，脉又状分枝；叶柄长。花单生异株。种子核果状，椭圆形至近球形，成熟时金黄色，外种皮肉质，熟时橙黄色，内种皮骨质，白色。花期4～5月，果期9～10月。\n" +
                "\n" +
                "【产地分布】我国华东、华北、华南地区等地均有种植。\n" +
                "\n" +
                "【采收加工】秋季采收，除去外种皮，洗净，稍蒸或煮后烘干或晒干。\n" +
                "\n" +
                "【药材性状】椭圆形或倒卵形，外壳(中果皮)骨质，光滑，厚约0.5毫米，表面乳白色，两面隆起，两侧边缘各有1纵棱，偶有3纵棱，顶端有一圆形突起，其中央为珠孔，基部渐尖，具小突起或无，内种皮为一层红褐色薄膜，种仁扁球形，淡黄绿色，胚乳肥厚，粉质，中央为1细长条形的胚，白色子叶2枚。气微清香，味微甘、苦。\n" +
                "\n" +
                "【性味归经】性平，味甘、苦、涩。有毒。归肺经、肾经。\n" +
                "\n" +
                "【功效与作用】敛肺定喘、止带浊、缩小便。属收涩药下属分类的固精缩尿止带药。\n" +
                "\n" +
                "【临床应用】用量4.5～9克。用治哮喘痰嗽、带下、白浊、小便频数、遗尿等。\n" +
                "\n" +
                "【药理研究】本品有抗菌、祛痰、清除自由基、解痉、降压、抗肿癌、调节免疫功能、抗脂质过氧化等的药理作用；可使主动脉输出量减少，冠状动脉流量增加；能显著提高动物常压耐缺氧能力；抗过敏；延缓衰老；抗微生物；治疗脑缺血；收缩离体子宫等作用。\n" +
                "\n" +
                "【化学成分】本品含银杏毒素、腰果酸、白果酸、氢化白果酸、氢化白果亚酸、白果醇、银杏二酚。\n" +
                "\n" +
                "【使用禁忌】生食有毒，不可多用，小儿更应注意。\n" +
                "\n" +
                "【配伍药方】①治梦遗：白果3粒，酒煮食，连服4～5天。(《湖南药物志》)\n" +
                "\n" +
                "②小便频数，遗尿：陈白果5粒，蜗牛3个(焙干)。研末冲服。(《陕甘宁青中草药选》)\n" +
                "\n" +
                "③止头风、眼疼：白果肉捣烂敷太阳穴。(《滇南本草》)\n" +
                "\n" +
                "④治眩晕跌倒，老年人更妙：鲜白果二个，去壳衣，研烂，空心开水冲服，至重者不过三五服愈。(《惠直堂经验方》)\n" +
                "\n" +
                "⑤治肺结核：白果核12克，白毛夏枯草30克，煎服。(《安徽中草药》)");
        chineseMedicineDao.insert(medicine235);

        ChineseMedicine medicine236=new ChineseMedicine();
        medicine236.setName("巴豆");
        medicine236.setSortType("B");
        medicine236.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546070612&di=37318a18b991b1270176deea40d92655&imgtype=jpg&er=1&src=http%3A%2F%2Ffile.youlai.cn%2Fcnkfile1%2FM01%2F03%2F2C%2Fo4YBAFubTymAFVfLAABLEHbj9CA98.jpeg");
        medicine236.setData("【中药名】巴豆 badou\n" +
                "\n" +
                "【别名】毒鱼子、巴仁、巴果、刚子、江子、老阳子、芒子、药子仁。\n" +
                "\n" +
                "【英文名】Crotonis Fructus。\n" +
                "\n" +
                "【药用部位】大戟科植物巴豆Croton tiglium L.的成熟果实。\n" +
                "\n" +
                "【植物形态】常绿小乔木。树皮深灰色，平滑，幼枝绿色，被稀疏星状柔毛；老枝具不明显黄色细纵裂纹。叶互生，托叶早落；叶片卵形，顶端渐长尖，基部圆形或阔楔形，叶缘有疏浅细锯齿，两面均具稀疏星状毛，掌状3出脉，近叶柄两侧各有一无柄腺体。花小，单性，雌雄同株，顶生总状花序，花绿色，雄上雌下，花梗有星状毛；雄花萼片5深裂，花瓣5，反卷，内面密生绵状毛，雄蕊15～20枚，着生于花盘边缘；花盘腺体与萼片对生；雌花花萼5裂，花瓣5，有的无花瓣，花柱3，柱头深2裂。蒴果长圆形至倒卵形，有3～4钝棱，密生星状毛。种子长卵形，淡褐色。花期3～6月，果期6～9月。\n" +
                "\n" +
                "【产地分布】生于山谷、林缘、溪旁或密林中。多为栽培。分布于广西、云南、贵州、四川等地。此外、浙江、江苏等地亦有栽培。\n" +
                "\n" +
                "【采收加工】秋季果实成熟时采收，堆置2～3天，摊开，干燥。\n" +
                "\n" +
                "【药材性状】卵圆形，一般具三棱，长1.8～2.2厘米，直径1.4～2厘米。表面灰黄色或稍深，粗糙，有纵线6条，顶端平截，基部有果梗痕。破开果壳，可见3室，每室含种子1粒。种子呈略扁的椭圆形，长1.2～1.5厘米，直径0.7～0.9厘米，表面棕色或灰棕色，一端有小点状的种脐及种阜的疤痕，另端有微凹的合点，其间有隆起的种脊；外种皮薄而脆，内种皮呈白色薄膜；种仁黄白色，油质。无臭，味辛辣。\n" +
                "\n" +
                "【性味归经】性热，味辛。有大毒。归胃经、大肠经。\n" +
                "\n" +
                "【功效与作用】外用蚀疮。属泻下药下属分类的峻下逐水药。\n" +
                "\n" +
                "【临床应用】内服：巴豆霜入丸、散，用量0.1～0.3克；外用：适量，研末涂患处，或捣烂以纱布包擦患处。用于恶疮疥癣、疣痣。\n" +
                "\n" +
                "【药理研究】刺激消化道，产生剧烈腹痛；催吐；兴奋肠肌；增加胆汁和胰腺分泌；抗病原微生物；抗肿瘤；促肿瘤发生；抗炎；致突变；可使血小板凝集。口服巴豆油1滴可致激烈腹泻。煎剂对金黄色葡萄球菌、流感杆菌等在体外均有一定抑制作用，对小鼠艾氏腹水癌等有明显抑制作用。\n" +
                "\n" +
                "【化学成分】本品含巴豆苷、巴豆酸、棕榈酸、花生酸、巴豆毒素Ⅰ、巴豆毒素Ⅱ、亚麻酸、巴豆醇等成分。尚含巴豆醇的双酯化合物及疏水性三酯化合物，具刺激性和致癌活性。\n" +
                "\n" +
                "【使用禁忌】无寒实积滞、体虚者及孕妇禁用，不宜与牵牛子同用。服巴豆后，不宜食热粥，饮开水等热物，以免加剧泻下。巴豆内服中毒能产生口腔、咽部及胃部的灼热感，刺痛，流涎，恶心，呕吐，上腹剧痛，剧烈腹泻，大便呈米泔样，尿中可出现蛋白、红细胞、白细胞，管型，并可起急性肾功能衰竭而致少尿尿闭。中毒重者出现谵语、发绀，脉细弱，体温和血压下降，呼吸困难，终致呼吸、循环衰竭而死亡。外用可使皮肤黏膜发赤起疱，形成炎症，乃至局部组织坏死，服巴豆后若泻下不止，可以黄连、黄柏或绿豆煎汤冷服，或食冷粥，饮大豆汁以缓解。\n" +
                "\n" +
                "【配伍药方】①治肝硬化腹水：巴豆霜3克，轻粉1.5克，放于四、五层纱布上，贴在肚脐上，表面再盖二层纱布，经1～2小时后感到刺痒时即可取下，待水泻，若不泻则再敷。(内蒙古《中草药新医疗法资料选编》)\n" +
                "\n" +
                "②治喉痹：白矾(捣碎)60克，巴豆(略捶破)15克。同于铫器内炒，候矾枯，去巴豆不用，碾矾为细末，遇病以水调灌，或干吹入咽喉中。(《百一选方》)\n" +
                "\n" +
                "③治咽喉闭塞，不通甚者：巴豆(去大皮)一枚。上钻中心，绵裹，令有出气处。内于鼻中，随时左右，时时吸气令入喉中，立效。(《圣惠方》)\n" +
                "\n" +
                "④治小儿痰喘：巴豆一粒，杵烂，绵裹塞鼻，痰即自下。(《古今医鉴》)\n" +
                "\n" +
                "⑤治一切疮毒及腐化瘀肉：巴豆去壳，炒焦，研膏，点肿处则解毒，涂瘀肉则自腐化。(《痈疽神秘验方》乌金膏)\n" +
                "\n"
                );
        chineseMedicineDao.insert(medicine236);

        ChineseMedicine medicine237=new ChineseMedicine();
        medicine237.setName("八角茴香");
        medicine237.setSortType("B");
        medicine237.setMedicineImageUrl("http://ztd00.photos.bdimg.com/ztd/w=700;q=50/sign=349da012b5315c60439569efbd8aba2e/8601a18b87d6277f4a4c3e3f21381f30e924fc5f.jpg");
        medicine237.setData("【中药名】八角茴香 bajiaohuixiang\n" +
                "\n" +
                "【别名】大茴香、八角、大料、八角珠。\n" +
                "\n" +
                "【英文名】Anisi Stellati Fructus\n" +
                "\n" +
                "【来源】木兰科八角茴香Illicium verum Hook.f.的成熟果实。\n" +
                "\n" +
                "【植物形态】常绿乔木。树皮灰绿色至红褐色，有不规则的裂纹。单叶互生或3～6片簇生于枝顶。叶片厚革质，长椭圆形或卵状披针形，先端渐尖或急尖，基部狭楔形，全缘，稍内卷，上面深绿色，有光泽，并具油点，下面疏生柔毛。花两性，单生于叶腋或近顶生，花梗短，花被片7～12，数轮，覆瓦状排列，内轮粉红色至深红色，雄蕊11～12枚，排成1～2轮，心皮8～9，离生。花柱较子房略短或与子房近等长。果实多由8个蓇葖果放射排列成八角形的聚合果，红褐色，木质。蓇葖果扁平，先端钝尖或钝，成熟时由腹缝线开裂。种子1粒，扁卵形，亮棕色。花期春、秋季，果期秋季至翌年春季。\n" +
                "\n" +
                "【产地分布】生于气候温暖、潮湿、土壤疏松的山地，栽培或野生。分布于广东、广西、贵州、云南等地。\n" +
                "\n" +
                "【采收加工】秋、冬季果实由绿变黄时采摘，置沸水中略烫后干燥或直接干燥。\n" +
                "\n" +
                "【药材性状】聚合果，多由8个蓇葖果组成，放射状排列于中轴上，蓇葖果长1～2厘米，宽0.3～0.5厘米，高0.6～1厘米；外表面红棕色，有不规则皱纹，顶端呈鸟喙状，上侧多开裂；内表面淡棕色，平滑，有光泽；质硬而脆。果梗长3～4厘米，连于基部中央，弯曲，常脱落。每个蓇葖果含瞢葖种子1粒，扁卵圆形，长约6毫米，红棕色或黄棕色，光亮，尖端有种脐；胚乳白色，富油性。气芳香，味辛、甜。\n" +
                "\n" +
                "【性味归经】性温。味辛。归肝经、肾经、脾经、胃经。\n" +
                "\n" +
                "【功效与作用】温阳散寒、理气止痛。属温里药。\n" +
                "\n" +
                "【临床应用】用量3～6克，煎服；或入丸、散。外用：适量，研末调敷。用治寒疝腹痛、肾虚腰痛、胃寒呕吐、脘腹冷痛。\n" +
                "\n" +
                "【药理研究】具有抑菌、升白细胞、雌激素样、刺激作用。动物实验表明，有升高白细胞和祛痰作用。醇提物对离体气管有抗组织胺作用；体外试验对枯草杆菌、大肠杆菌等多种细菌有抑制作用。\n" +
                "\n" +
                "【化学成分】含挥发油，其中主要成分为反式茴香醚、对丙烯苯基异戊烯醚等。另含槲皮素-3-O-鼠李糖苷、槲皮素、羟基桂皮酸、反式茴香脑、枝叶素等成分。\n" +
                "\n" +
                "【使用禁忌】阴虚火旺者慎服。\n" +
                "\n" +
                "【配伍药方】①治小肠气痛不可忍者：杏仁30克，葱白(和根捣，焙干)15克，八角茴香30克。上为末，每服9克，空心，温胡桃酒调下。(《续本事方》)\n" +
                "\n" +
                "②治疝气：茯苓、白术、山楂子(炒)、八角茴香(炒)、吴茱萸(炒)、荔枝核30克，枳实24克，橘核(炒)90克。为末，炼蜜为丸，每丸重4.5克，空心细嚼，姜汤下。(《摄生众妙方》茴香丸)\n" +
                "\n" +
                "③治膀胱偏坠疝气：八角茴香、白牵牛(炒)，二味各等分。为细末，空心酒调下。(《朱氏集验方》茴香散)\n" +
                "\n" +
                "④治腰痛如刺：八角茴香，炒研。每服6克，食前盐汤下。外以糯米一二升，炒热，袋盛，拴于痛处。(《简便单方》)\n" +
                "\n" +
                "⑤治大小便皆秘，腹胀如鼓，气促：大麻子(炒，去壳)15克，八角茴香七个。上作末，生葱白三七个，同研煎汤，调五苓散服。(《永类钤方》)");
        chineseMedicineDao.insert(medicine237);

        ChineseMedicine medicine238=new ChineseMedicine();
        medicine238.setName("菝葜");
        medicine238.setSortType("B");
        medicine238.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=4c96732ed7c451dae2fb04b9d7943903/08f790529822720e5bb0098a71cb0a46f21fab40.jpg");
        medicine238.setData("【中药名】菝葜 baqia\n" +
                "\n" +
                "【别名】金刚头、金刚根、假萆薢、金刚刺、冷饭巴。\n" +
                "\n" +
                "【英文名】Smilacis Chinae Rhizoma\n" +
                "\n" +
                "【来源】百合科植物菝葜Smilax chinaL.的根茎。\n" +
                "\n" +
                "【植物形态】落叶攀援灌木。根茎横走，呈不规则弯曲，肥厚质硬，疏生须根。茎细长，具倒生或平出疏刺。叶互生，宽卵形、圆形或卵状椭圆形，先端突尖或浑圆，基部心形、浅心形或宽楔形，全缘，3～5脉；叶鞘占叶柄的2/3，沿叶柄下部两侧有卷须2条，叶片脱落后留一段叶柄。伞形花序腋生；苞片卵状披针形；花单性，雌雄异株，花被片6，2轮，矩圆形，黄绿色；雄花外轮花被片3，长圆状椭圆形，内轮花被片3，卵状披针形；雌花具3～6枚退化雄蕊，子房上位，长卵形，3室，柱头3裂。浆果球形，熟时红色，有粉霜。花期4～5月，果期5～7月。\n" +
                "\n" +
                "【产地分布】生于海拔2000米以下的林下、灌丛中或山坡上。分布于江苏、福建、湖南、四川等地。\n" +
                "\n" +
                "【采收加工】秋末至次年春天采挖，除去须根及泥沙，洗净，切片或切块，晒干。\n" +
                "\n" +
                "【药材性状】弯曲扁圆柱形或不规则块状，有隆起的结节，长10～20厘米，直径2～4厘米。表面黄棕色或紫棕色，结节膨大处有坚硬的须根残基及芽痕，或留有坚硬弯曲的细根。质坚硬，难折断，断面黄棕色至红棕色，平坦，纤维性。气微，味微苦。\n" +
                "\n" +
                "【性味归经】性平，味甘、酸。归肝经、肾经。\n" +
                "\n" +
                "【功效与作用】祛风湿、利小便、消肿毒。属利水渗湿药下属分类的利尿通淋药。\n" +
                "\n" +
                "【临床应用】用量9～15克，煎服；或浸酒；或入丸、散。用治关节疼痛、肌肉麻木、泄泻、痢疾、水肿、淋病。外用适量，煎水洗，用治疗疮、肿毒、瘰疬、痔疮。\n" +
                "\n" +
                "【药理研究】具有抗菌、抗炎、抗肿瘤等作用。动物实验表明，其煎剂有利尿、解毒作用和抗锥虫作用，在试管内对金黄色葡萄球菌、绿脓杆菌和大肠杆菌有抑制作用。\n" +
                "\n" +
                "【化学成分】含菝葜皂苷A、菝葜皂苷B、菝葜皂苷C，其中菝葜皂苷B含量最多，水解得薯蓣皂苷元和三分子葡萄糖及三分子鼠李糖。另含二十八烷醛、多烷醇、落新妇苷、菝葜素、异内杞苷、山柰素、山柰酚、薯蓣皂苷、异黄杞苷等成分。\n" +
                "\n" +
                "【使用禁忌】对气质热性气质者有害，酌情需配适当的药物。忌醋、茶。\n" +
                "\n" +
                "【配伍药方】①治淋症：菝葜根(盐水炒)15克，银花9克，扁蓄6克。水煎服。(《湖南药物志》)\n" +
                "\n" +
                "②治血汞：菝葜根、算盘子根各30克。煎服。(《安徽中草药》)\n" +
                "\n" +
                "③治糖尿病：鲜菝葜根60～120克，配猪胰1具同炖服，每日1剂。(《浙南本草新编》)\n" +
                "\n" +
                "④治崩漏：菝葜根、棕榈炭各30克。煎服。(《安徽中草药》)\n" +
                "\n" +
                "⑤治吐血：菝葜根6克，地茶9克，水煎服。(《湖南药物志》)");
        chineseMedicineDao.insert(medicine238);

        ChineseMedicine medicine239=new ChineseMedicine();
        medicine239.setName("板蓝根");
        medicine239.setSortType("B");
        medicine239.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=c2e7f1faea50352aa56c2d5a322a9097/d000baa1cd11728bbf04726fcbfcc3cec3fd2c56.jpg");
        medicine239.setData("【中药名】板蓝根 banlangen\n" +
                "\n" +
                "【别名】北板蓝根、靛青根、蓝靛根。\n" +
                "\n" +
                "【英文名】Indigowoad Root, Root of Indigowoad\n" +
                "\n" +
                "【来源】十字花科植物菘蓝Isatis indigotica Fort.的根。\n" +
                "\n" +
                "【植物形态】二年生草本，高40～90厘米，无毛或稍有柔毛；主根直径5～8毫米，灰黄色。茎直立，上部多分枝，稍带粉霜。茎生叶矩圆状椭圆形，有柄。茎生叶矩圆形呈矩圆披针形，长5～7厘米，宽1～2厘米，先端钝，基部箭形，半抱茎，全缘或有不明显锯齿。花序复总状；花黄色，直径3～4毫米。短角果矩圆形，扁平，边缘有翅，长约1.3厘米，宽约4毫米，紫色，无毛，有短尖，基部渐狭。种子一个，椭圆形，长3毫米，褐色。\n" +
                "\n" +
                "【产地分布】主产于河北、江苏、安徽等地。\n" +
                "\n" +
                "【采收加工】初冬采挖，除去茎叶，洗净，晒干。\n" +
                "\n" +
                "【药材性状】圆柱形，稍扭曲，长10～40厘米，直径0.3～1.2厘米。表面灰黄色，有纵皱纹及支根痕，并有淡灰黄色横长的皮孔。根头略膨大，可见轮状排列的暗绿色叶柄残基、叶柄痕以及密集的疣点突起。质实而脆，折断面略平坦，皮部淡棕白色至淡棕色，占半径的1/2～3/4，木部黄色。气微，味微甜后涩。\n" +
                "\n" +
                "【性味归经】性寒，味苦。归心经、胃经。\n" +
                "\n" +
                "【功效与作用】清热解毒、凉血利咽。属清热药下分类的清热解毒药。\n" +
                "\n" +
                "【临床应用】用量9～15克。用治流感、流脑、乙脑、肺炎、丹毒、热毒发斑、神昏吐衄、咽肿、痄腮、疮疹等。\n" +
                "\n" +
                "【药理研究】具有抗细菌、病毒、抗内毒素、抗癌、增强免疫调节等作用，对乙型肝炎病毒DNA有中度抑制作用，对金黄色葡萄球菌等多种致病菌以及钩端螺旋体均有抑制作用。\n" +
                "\n" +
                "【化学成分】本品含(R，S)-告依春、表告伊春、靛蓝、靛玉红、3-谷固醇、羽扇豆醇、洋丁香酚苷、大黄酚等。并含有18种氨基酸，其中精氨酸的含量较高，氨基酸可能是板蓝根中的一个主要有效成分。\n" +
                "\n" +
                "【使用禁忌】脾胃虚寒、无实热火毒者慎服。\n" +
                "\n" +
                "【配伍药方】①治乙型脑炎轻型或中型：板蓝根30克，大青叶15克，银花15克，连翘15克，玄参15克，生地30克，生石膏(先煎)30克，黄芩12克，干地龙9克。水煎服。(中山医学院《中药临床应用》板蓝大青汤)\n" +
                "\n" +
                "②治流行性脑脊髓膜炎：板蓝根125克。水煎服。每2小时1次。(《山西中草药》)\n" +
                "\n" +
                "③治肝炎：板蓝根、茵陈各15克，赤芍9克，甘草3克。水煎服。转氨酶高者加夏枯草6克。(《新疆中草药》)\n" +
                "\n" +
                "④治肺炎：板蓝根、夏枯草各15克，虎杖30克，功劳叶12克，银花9克，青蒿9克。水煎服。(《湖北中草药志》)\n" +
                "\n" +
                "⑤治腮腺炎：板蓝根15克水煎服。药渣挤汁搽敷患处。(《山西中草药》)");
        chineseMedicineDao.insert(medicine239);

        ChineseMedicine medicine240=new ChineseMedicine();
        medicine240.setName("百部");
        medicine240.setSortType("B");
        medicine240.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=11ece3e45343fbf2d121ae71d117a1e5/4610b912c8fcc3ce144d0fdf9245d688d53f2049.jpg");
        medicine240.setData("【中药名】百部 baibu\n" +
                "\n" +
                "【别名】大春根菜、虱蚤草、穿山薯、大叶百部、野天门冬。\n" +
                "\n" +
                "【英文名】Stemonae Radix\n" +
                "\n" +
                "【来源】百部科植物直立百部Stemona sessilifolia(Miq.)Miq.、蔓生百部Stemona ja Ponica(Bl.)Miq.对叶百部Stemona tuberose Lour.的块根。(本文以对叶百部为例，右图亦为对叶百部)\n" +
                "\n" +
                "【植物形态】茎缠绕，叶对生，广卵形，长10～30厘米，宽3～10厘米，基部浅心形，叶脉7～13条，叶柄长3～10厘米。花单生或2～3朵成总状花序，总花梗腋生，与叶柄分离；花黄绿色带紫色条纹，花药附属物呈钻状或披针形。\n" +
                "\n" +
                "【产地分布】生于向阳处灌木林下、溪边、路边及山谷和阴湿岩石上。分布于湖北、广东、福建、四川、贵州。\n" +
                "\n" +
                "【药材性状】纺锤形或长条形，长8～24厘米，直径0.8～2厘米。表面淡黄棕色或灰棕色，具浅纵皱纹或不规则纵槽。质坚实，断面黄白色或暗棕色，中柱较大，中心类白色。\n" +
                "\n" +
                "【性味归经】性微温，味甘、苦。归肺经。\n" +
                "\n" +
                "【功效与作用】润肺止咳，杀虫灭虱。属化痰止咳平喘药下分类的止咳平喘药。\n" +
                "\n" +
                "【临床应用】用量3～9克，生用或蜜炙用。外用适量，水煎或酒浸。主治外感咳嗽，常配荆芥、桔梗、紫菀等。此外，可用生百部30克煎取浓汁30毫升，每晚临睡时作保留灌肠，连用5天为一疗程。另可用20%醇浸液或50%水煎液外用，灭头虱、体虱及虱卵。近年来，用百部治疗肺结核、百日咳、慢性气管炎等均有一定疗效。\n" +
                "\n" +
                "【药理研究】现代药理试验结果表明，能抗病原微生物；抗寄生虫；镇咳、祛痰、平喘；具有弱的抑制中枢作用；有毒。\n" +
                "\n" +
                "【化学成分】本品主要含百部碱、原百部碱、对叶百部碱、异对叶百部碱、二去氢对叶百部碱、直立百部碱、百部碱、对叶百部螺碱、豆固醇、苯甲酸、胡萝卜苷、芝麻素等。\n" +
                "\n" +
                "【使用禁忌】脾胃虚弱者慎服。\n" +
                "\n" +
                "【配伍药方】①治卒得咳嗽：生姜汁、百部汁合煎，服二合。(《肘后方》)\n" +
                "\n" +
                "②治三十年嗽：百部10斤。捣取汁，煎如饴。服一方寸匕，日三服。(《千金要方》)\n" +
                "\n" +
                "③治小儿百日咳：蜜炙百部、夏枯草各9克。水煎服。(《青岛中草药手册》)\n" +
                "\n" +
                "④治肺结核空洞：蜜炙百部、白及各12克，黄芩6克，黄精15克。煎服。(《安徽中草药》)\n" +
                "\n" +
                "⑤治肺实鼻塞，不闻香臭：百部60克，款冬花、贝母(去心)、白薇各30克。上四味，捣罗为散。每服3克。米饮调下。(《圣济总录》百部散)");
        chineseMedicineDao.insert(medicine240);

        ChineseMedicine medicine241=new ChineseMedicine();
        medicine241.setName("百合");
        medicine241.setSortType("B");
        medicine241.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=486e685e023b5bb5aada28ac57babe5c/a686c9177f3e6709a9672c0a3ac79f3df9dc55db.jpg");
        medicine241.setData("【中药名】百合 baihe\n" +
                "\n" +
                "【别名】重迈、百合蒜、蒜脑薯。\n" +
                "\n" +
                "【英文名】Lilii Bulbus\n" +
                "\n" +
                "【来源】百合科植物麝香百合Lilium longiflorum Thunb.的肉质鳞叶。\n" +
                "\n" +
                "【植物形态】多年生草本；高45～90厘米。鳞茎近球形，直径约5厘米。茎直立，绿色。叶互生，狭披针形或披针状线形，长5～15厘米，宽1～2厘米，顶端渐尖，叶全缘，两面无毛。花白色，极香，外面略带绿色，单朵顶生或2～3朵排成伞形；花梗长约3厘米；花被片6，靠合成喇叭形，长10～19厘米，基部蜜腺两边无乳突；雄蕊6枚，花丝长约15厘米，花药长圆形，背着，外露；花柱外伸，顶弯曲，子房柱形，具3棱，3室，每室有胚乳多数，柱头3浅裂。蒴果长圆形，长5～7厘米。花期5～6月；果期8～9月。\n" +
                "\n" +
                "【产地分布】多生于山坡草地。现全国各地有栽培。\n" +
                "\n" +
                "【采收加工】秋季采挖，洗净，剥取鳞叶，置沸水中略烫，干燥。\n" +
                "\n" +
                "【药材性状】长椭圆形，长2～5厘米，宽1～2厘米，中部厚1.3～4毫米。表面类白色、淡棕黄色或微带紫色，有数条纵直平行的白色维管束。顶端稍尖，基部较宽，边缘薄，微波状，略向内卷曲。质硬而脆，断面较平坦，角质样。无臭，味微苦。\n" +
                "\n" +
                "【性味归经】性寒，味甘。归心经、肺经。\n" +
                "\n" +
                "【功效与作用】养阴润肺、清心安神。属补虚药下分类的补阴药。\n" +
                "\n" +
                "【临床应用】用量6～12克，水煎服。用治阴虚久咳、痰中带血、虚烦惊悸、失眠多梦、精神恍惚。风寒咳嗽、脾胃虚寒、大便滑泄者忌服。\n" +
                "\n" +
                "【药理研究】现代药理试验结果表明，水煎剂对氨水引起的小鼠咳嗽有止咳作用，使小鼠肺灌流流量增加。此外，还有镇咳、平喘、祛痰；抗应激性损伤；镇静催眠；增强免疫功能；升高外周白细胞的作用等。\n" +
                "\n" +
                "【化学成分】本品主要含岷江百合苷、去酰百合皂苷、胡萝卜苷、去酰百合皂苷、百合皂苷、β-澳洲茄边碱、1-O-对香豆酰甘油、奴阿皂苷元等。\n" +
                "\n" +
                "【使用禁忌】风寒咳嗽及中寒便溏者禁服。\n" +
                "\n" +
                "【配伍药方】①治咳嗽不已，或痰中有血：款冬花、百合(焙，蒸)等分。上为细末，炼蜜为丸，如龙眼大。每服一丸，食后临卧细嚼，姜汤咽下，噙化尤佳。(《济生续方》百花膏)\n" +
                "\n" +
                "②治天疱湿疮：生百合捣涂，一二日即安。(《濒湖集简方》)\n" +
                "\n" +
                "③治耳聋、耳痛：干百合为末，温水服6克，日二服。(《千金要方》)\n" +
                "\n" +
                "④治神经衰弱，心烦失眠：百合15克，酸枣仁15克，远志9克。水煎服。(《新疆中草药手册》)\n" +
                "\n" +
                "⑤治肺痈：百合，或煮或蒸，频食，拌蜜蒸更好。(《经验广集》百合煎)");
        chineseMedicineDao.insert(medicine241);

        ChineseMedicine medicine242=new ChineseMedicine();
        medicine242.setName("白茅根");
        medicine242.setSortType("B");
        medicine242.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546091307&di=06b0a21fd73a700feb1e43315044930c&imgtype=jpg&er=1&src=http%3A%2F%2Ffx120.120askimages.com%2F120ask_news%2F2016%2F0802%2F201608021470146891792108.jpg");
        medicine242.setData("【中药名】白茅根 baimaogen\n" +
                "\n" +
                "【别名】丝茅根、茅根、毛草根、甜草根。\n" +
                "\n" +
                "【英文名】Imperatae Rhizoma\n" +
                "\n" +
                "【来源】禾本科植物白茅Imperata cylindrica Beauv. var. major (Nees)C.E.Hubb.的根茎。\n" +
                "\n" +
                "【植物形态】多年生直立草本。根状茎匍匐，外覆鳞片，秆丛生，直立，节上通常有长柔毛。叶多丛集于基部，线形或披针状线形，顶端渐尖或急尖，叶面及边缘粗糙，叶背光滑。圆锥花序紧缩成柱状，小穗柄一长一短，基部密生长白色丝状柔毛，第一颖较狭，有脉3～4条，第二颖较宽，有脉4～6条；第一外稃卵状长圆形，先端钝，内稃缺如；第二外稃披针形，先端尖，两侧略呈细齿状；内稃先端截平，具尖钝大小不同的数齿；雄蕊2枚，花药黄色；柱头2枚，紫色。颖果。\n" +
                "\n" +
                "【产地分布】生于山坡、荒地上。分布几遍全国。\n" +
                "\n" +
                "【采收加工】春、秋季采挖，洗净，晒干，除去须根及膜质叶鞘，捆成小把。\n" +
                "\n" +
                "【药材性状】长圆柱形。表面黄白色或淡黄色，微有光泽，具纵皱纹，节明显，稍突起，节间长短不等。体轻，质略脆，断面皮部白色，多有裂隙，放射状排列，中柱淡黄色，易与皮部剥离。无臭，味微甜。\n" +
                "\n" +
                "【性味归经】味甘，性寒。归肺经、胃经、膀胱经。\n" +
                "\n" +
                "【功效与作用】凉血止血，清热利尿。属止血药下属中的凉血止血药。\n" +
                "\n" +
                "【临床应用】用量9～30克，煎服；或鲜品30～60克，捣汁外用。用治血热吐血、衄血、尿血、热病烦渴、黄疸、水肿、热淋涩痛、急性肾炎水肿。鲜用凉血益阳。\n" +
                "\n" +
                "【药理研究】具有利尿、促凝血、增加免疫功能等作用，另据研究，水浸剂对提高乙型肝炎表面抗原阳性的转阴率有显著效果。此外，还具有抗菌作用。\n" +
                "\n" +
                "【化学成分】含三萜类化合物芦竹素、白茅素、羊齿烯醇、乔木萜烷、异乔木萜醇、西米杜鹃醇等，并含可溶性钙，含糖18.8%，主要为葡萄糖、蔗糖、少量果糖和木糖等。尚含单酸类及其钾盐、胡萝卜素类、叶绿素及白头翁素、棕榈酸等。\n" +
                "\n" +
                "【使用禁忌】脾胃虚寒、溲多不渴者禁服。\n" +
                "\n" +
                "【配伍药方】①治吐血不止：白茅根一握。水煎服之。(《千金翼方》)\n" +
                "\n" +
                "②治伤肺唾血：白茅根一味。捣筛为散，服方寸匕，日三。(《外台》引《深师方》)\n" +
                "\n" +
                "③治血热鼻衄：白茅根汁一合。饮之。(《妇人良方》)\n" +
                "\n" +
                "④治胃火上冲，牙龈出血：鲜白茅根60克，生石膏60克，白糖30克。水煎，冲白糖服。(《河南中草药手册》)\n" +
                "\n" +
                "⑤治疗胃出血：白茅根、生荷叶各30克，侧柏叶、藕节各9克，黑豆少许。水煎服。(《全国中草药汇编》)");
        chineseMedicineDao.insert(medicine242);

        ChineseMedicine medicine243=new ChineseMedicine();
        medicine243.setName("白附子");
        medicine243.setSortType("B");
        medicine243.setMedicineImageUrl("http://l.b2b168.com/2016/08/29/16/201608291620560362144.jpg");
        medicine243.setData("【中药名】白附子 baifuzi\n" +
                "\n" +
                "【别名】禹白附、牛奶白附、鸡心白附、野慈菇、野半夏。\n" +
                "\n" +
                "【英文名】Typhonii Rhizoma。\n" +
                "\n" +
                "【来源】天南星科植物独角莲Typhonium giganteum Engl.的块茎。\n" +
                "\n" +
                "【植物形态】多年生草本。块茎卵圆形，直径2～5厘米。叶基生；叶柄肥大肉质，基部扩大成鞘，长20～40厘米；叶片大，戟状箭形或卵状宽椭圆形，先端渐尖，基部箭形，全缘或略呈波状，侧脉6～10对。花梗从块茎处生出，肥厚，圆柱形，长8～15厘米，绿色，常带紫色纵条斑点；肉穗花序顶生，佛焰苞上部展开，先端渐尖，下部筒状；肉穗花序几无梗，顶端具圆柱状附属器，紫色；花雌雄同株；雄花部分在上&rsquo；长约1.5厘米，花药2，顶端开裂；中部长约2.5厘米处着生中性花，雌花部分在下，长约1.5厘米；子房圆柱形，顶端近六角形，1室，通常具2～3个基生胚珠。浆果红色。花期6～7月，果期8～9月。\n" +
                "\n" +
                "【产地分布】生于林下或山涧阴湿地；也有栽培。分布于河北、河南、山东、山西等地。\n" +
                "\n" +
                "【采收加工】秋季采挖，除去须根及外皮，晒干。\n" +
                "\n" +
                "【药材性状】椭圆形或卵圆形，长2～5厘米，直径1～3厘米。表面白色至黄白色，略粗糙，有环纹及须根痕，顶端有茎痕或芽痕。质坚硬，断面白色，粉性。无臭，味淡、麻辣刺舌。\n" +
                "\n" +
                "【性味归经】性温，味辛。有毒。归胃经、肝经。\n" +
                "\n" +
                "【功效与作用】祛风痰、定惊搐、解毒、散结、止痛。属化痰止咳平喘药下属分类的温化寒痰药。\n" +
                "\n" +
                "【临床应用】一般炮制后用，用量3～6克，水煎服；外用生品适量捣烂，敷膏或研末以酒调敷患处。用治中风痰壅、口眼歪斜、语言涩謇、痰厥头痛、偏正头痛、喉痹咽痛、破伤风；外治瘰疬痰核、毒蛇咬伤。\n" +
                "\n" +
                "【药理研究】现代研究证实，有抗结核杆菌的作用，其疗效仅次于链霉素。有显著抗凝血酶作用和镇痛作用，还具有镇静、抗惊厥、抗炎、抑菌、催吐和刺激等作用。\n" +
                "\n" +
                "【化学成分】含β-谷甾醇及其葡萄糖苷、内消旋肌醇、粘液质、蔗糖，可能尚有皂苷。还含胆碱、尿嘧啶、琥珀酸、棕榈酸、亚油酸、油酸、白附子凝集素、白附子胆碱、三亚油酸及二棕榈酸甘油酯等。\n" +
                "\n" +
                "【使用禁忌】血虚生风、内热生惊及孕妇慎服。\n" +
                "\n" +
                "【配伍药方】①治口眼歪斜：制白附子12克，僵蚕、全蝎各9克。共为细末，分9包。每次1包，每日3次，黄酒送下。(《陕甘宁青中草药选》)\n" +
                "\n" +
                "②治偏、正头痛，三叉神经痛：制白附子、白芷、猪牙皂角各30克。共为细末，每次3克，每日2次，开水送服。(《陕甘宁青中草药选》)\n" +
                "\n" +
                "③治腰腿痛，关节痛：制白附子4.5克，鸡血藤12克，牛膝9克，独活9克，五加皮12克。水煎服。(《山东中草药手册》)\n" +
                "\n" +
                "④治疔肿痈疽：白附子根研末，用醋、酒调涂。(《黑龙江常用中草药手册》)\n" +
                "\n" +
                "⑤治疗瘰疬：白附子球茎捣烂外敷。(江西《草药手册》)");
        chineseMedicineDao.insert(medicine243);

        ChineseMedicine medicine244=new ChineseMedicine();
        medicine244.setName("白薇");
        medicine244.setSortType("B");
        medicine244.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=48f5586e16178a82da3177f2976a18e8/b17eca8065380cd75bf0004fa944ad34588281a2.jpg");
        medicine244.setData("【中药名】白薇 baiwei\n" +
                "\n" +
                "【别名】春草、芒草、薇草、白龙须、老君须、龙胆白薇。\n" +
                "\n" +
                "【英文名】Cynanchi Atrati Radix Et Rhizoma。\n" +
                "\n" +
                "【来源】萝摩科植物白薇Cynanchum atratum Bge.的根及根茎。\n" +
                "\n" +
                "【植物形态】多年生草本，高40～70厘米，植物体具白色乳汁。根茎短，簇生多数细长的条状根，根长达20厘米以上，直径2～3毫米，外皮土黄色。茎直立，圆柱形，密被灰白色短柔毛。叶对生，具短柄，叶片卵形或卵状长圆形，先端短渐尖，基部圆形，全缘，两面均被有白色茸毛，尤以叶背及脉上为密。花多数，密集成伞形聚伞花序，花深紫色，副花冠5裂，与合蕊柱等长，花粉块每室1个。瞢英果。种子多数，种毛白色。\n" +
                "\n" +
                "【产地分布】生于山坡或树林边缘。分布于黑龙江、吉林、辽宁等地。\n" +
                "\n" +
                "【采收加工】春、秋两季挖采根部，除去残茎、泥土，晒干。\n" +
                "\n" +
                "【药材性状】类圆柱形，有结节，长1.5～5厘米，直径0.5～1.2厘米。上面可见茎基，直径在5毫米以上，下面及两侧簇生多数细长的根似马尾状。\n" +
                "\n" +
                "【性味归经】性寒，味苦、咸。归胃经、肝经、肾经。\n" +
                "\n" +
                "【功效与作用】清热、凉血、利尿。属清热药下分类的清热凉血药。\n" +
                "\n" +
                "【临床应用】用量4.5～9克，内服煎汤，或入丸散，治疗阴虚潮热、热病后期低热不退、热淋尿涩等症。 治体虚低烧、夜眠出汗：白薇、地骨皮各12克；治金疮出血不止：白薇末贴之。\n" +
                "\n" +
                "【药理研究】甾体多糖苷能使心肌收缩力增强，心率减慢;水提物有一定的祛痰作用，而无镇咳和平喘作用。毒性：腹腔注射醇提物LD50为7.5克/千克。具有退热，抗炎等作用。\n" +
                "\n" +
                "【化学成分】根中主要含强心苷、挥发油等，挥发油中主要成分是白薇素，强心苷中主要成分为甾体多糖苷。另含直立白薇苷、白薇正苷A、白前苷元A、β-香树素乙酸酯、油酸乙酯、β-谷固醇等。\n" +
                "\n" +
                "【使用禁忌】血虚无热、阳气外越、中寒食少便溏者慎服，汗多亡阳者禁服。\n" +
                "\n" +
                "【配伍药方】①治虚热盗汗：白薇、地骨皮各12克，银柴胡、鳖甲各9克。水煎服。(《河北中草药》)\n" +
                "\n" +
                "②治肺结核潮热：白薇9克，葎草果实15克，地骨皮12克。水煎服。(《青岛中草药手册》)\n" +
                "\n" +
                "③治风湿关节痛：白薇、臭山羊、大鹅儿肠根各15克。泡酒服。(《贵州草药》)\n" +
                "\n" +
                "④治金疮血不止：白薇末贴之。(《儒门事亲》)\n" +
                "\n" +
                "⑤治火眼：白薇30克。水煎服。(《湖南药物志》)");
        chineseMedicineDao.insert(medicine244);

        ChineseMedicine medicine245=new ChineseMedicine();
        medicine245.setName("白蔹");
        medicine245.setSortType("B");
        medicine245.setMedicineImageUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=440bc7d88618367ab984778f4f1ae0b1/09fa513d269759eec9376a3ab2fb43166c22dfd8.jpg");
        medicine245.setData("【中药名】白蔹 bai lian\n" +
                "\n" +
                "【别名】山葡萄秧、野番薯、鹅抢蛋、见肿消。\n" +
                "\n" +
                "【英文名】Ampelopsis Radix。\n" +
                "\n" +
                "【来源】葡萄科植物白蔹Ampelopsis japonica(Thunb.) Makino.的块根。\n" +
                "\n" +
                "【植物形态】木质藤本。块根矩圆形，数个聚生，外皮红棕色，内面白色或浅红棕色。茎多分枝，带淡紫色，散生点状皮孔，卷须与叶对生。掌状复叶互生，小叶3～5，常羽状分裂，裂片卵形，先端渐尖，边缘疏生粗锯齿，基部楔形，叶轴有阔翅；叶柄带淡紫色。聚伞花序与叶对生，序梗细长而缠绕；花小，萼片5，不明显；花瓣5，淡黄色；雄蕊5枚，与花瓣对生，花丝短；花盘杯状，边缘稍分裂；子房2室，花柱甚短。浆果圆球形或肾形，熟时蓝色。花期7～8月，果期9～10月。\n" +
                "\n" +
                "【产地分布】生于山野、路旁杂草丛中。分布于华北、华东及中南地区。\n" +
                "\n" +
                "【采收加工】春、秋季采挖，除去泥沙及细根，切成纵瓣或斜片，晒干。\n" +
                "\n" +
                "【药材性状】纵瓣长圆形或近纺锤形，长4～10厘米，直径1～2厘米。切而周边常向内卷曲，中部有一突起的棱线；外皮红棕色或红褐色，有纵皱纹、细横纹及横长皮孔，易层层脱落，脱落处呈淡红棕色。斜片卵圆形，长2.5～5厘米，宽2～3厘米。切面类白色或浅红棕色，可见放射状纹理，周边较厚，微翘起或略弯曲。体轻，质硬脆，易折断，折断时，有粉尘飞出。气微，味甘。\n" +
                "\n" +
                "【性味归经】性微寒，味苦。归心经、胃经。\n" +
                "\n" +
                "【功效与作用】清热解毒、消痈散结。属清热药下属分类的清热解毒药。\n" +
                "\n" +
                "【临床应用】用量4.5～9克，水煎服；鲜品捣烂或干品研细粉外敷。用治痈疽发背、疔疮、瘰疬、水火烫伤等。\n" +
                "\n" +
                "【药理研究】为外科常用中药，历代皆用治疮疡、疖肿等，以外用效果较好，近年来化学及药理方面的研究报道表明，水浸剂对共心性毛癣菌、奥杜盎小孢子菌、腹股沟表面癣菌等有抑制作用。另有报道，提取物醋酸乙酯可溶部分，对四氯化碳致小鼠肝损伤具有保护作用。\n" +
                "\n" +
                "【化学成分】本品含酒石酸、延胡索酸、槲皮素、白藜芦醇、大黄素、α-菠固醇、原儿茶酸、鞣质、黄酮苷、葡萄糖等。另含粘液质、淀粉。\n" +
                "\n" +
                "【使用禁忌】脾胃虚寒及无实火者禁服，孕妇慎服。不宜与川乌、制川乌、草乌、制草乌、附子同用。\n" +
                "\n" +
                "【配伍药方】①治烫火灼烂：白蔹末敷之。(《备急方》)\n" +
                "\n" +
                "②治诸物哽咽：白蔹、白芷等分。为末，水服6克。(《圣惠方》)\n" +
                "\n" +
                "③治湿热白带：白蔹、苍术各6克。研细末，每服3克，每日2次，白糖水送下。(《全国中草药汇编》)\n" +
                "\n" +
                "④治腹股沟疝：白蔹30克，水煎加白糖冲服。(《青岛中草药手册》)\n" +
                "\n" +
                "⑤治疮口不敛：白蔹、白及、络石藤各15克，取干者。为细末，干撒疮上。(《鸡峰普济方》白蔹散)");
        chineseMedicineDao.insert(medicine245);

        ChineseMedicine medicine246=new ChineseMedicine();
        medicine246.setName("白芍");
        medicine246.setSortType("B");
        medicine246.setMedicineImageUrl("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=0f3ac036f71f4134f43a0d2c4476feaf/060828381f30e92411bdc1e446086e061c95f7ce.jpg");
        medicine246.setData("【中药名】白芍 baishao\n" +
                "\n" +
                "【别名】金芍药、白芍药。\n" +
                "\n" +
                "【英文名】Paeoniae Radix Alba。\n" +
                "\n" +
                "【来源】毛茛科植物芍药Paeonia lactiflora Pall.的根。\n" +
                "\n" +
                "【植物形态】多年生草本。根肥大，通常圆柱形或略呈纺锤形。茎直立，光滑无毛。叶互生；具长柄；2回三出复叶，小叶片椭圆形至披针形，先端渐尖或锐尖，基部楔形，全缘，叶缘具极细乳突，上面深绿色，下面淡绿色，叶脉在下面隆起。花甚大，单生于花茎的分枝顶端；萼片3，叶状；花瓣10片左右或更多，倒卵形，白色、粉红色或红色；雄蕊多数，花药黄色；心皮分离。瞢葖果卵形，先端钩状向外弯。花期5～7月，果期6～7月。\n" +
                "\n" +
                "【产地分布】生于山坡、山谷的灌木丛或高原丛中。分布于浙江、安徽、四川等地。\n" +
                "\n" +
                "【采收加工】夏、秋采挖已栽植4～5年生的芍药根，除去根茎及须根，洗净，刮去粗皮，加入沸水中略煮，使根发软，捞出晒干。\n" +
                "\n" +
                "【药材性状】圆柱形，粗细均匀，大多顺直。长5～20厘米，直径1～2.5厘米。表面棕色或浅棕色，外皮未去尽处显棕褐色斑痕，较粗糙，有明显的纵皱纹及细根痕，偶见横向皮孔。质坚实而重，不易折断，切断面灰白色或微带棕色，角质样，木质部呈放射状。气无，味微苦而酸。\n" +
                "\n" +
                "【性味归经】性微寒，味苦、酸。归肝经、脾经。\n" +
                "\n" +
                "【功效与作用】平肝止痛、养血调经、敛阴止汗。属补虚药下属分类的补血药。\n" +
                "\n" +
                "【临床应用】用量4.5～9克，煎服；或入丸散。用治头痛眩晕、胸胁疼痛、泻痢腹痛、自汗盗汗、阴虚发热、月经不调、崩漏、带下等。\n" +
                "\n" +
                "【药理研究】应用HPLC研究表明，芍药根经去皮、水煮后苷类成分略有下降，但其中的有害成分苯甲酸则明显降低。有解痉、镇痛、抗炎、抗心肌缺血等药理作用。白芍应用较广泛，有的学者认为许多作用类似“人参”。野生品种与栽培品种作用相似。对中枢有抑制作用，可解热降温，镇静催眠;具有解痉、抗炎、抗溃、增强细胞免疫和体液免疫、扩张血管，增加血流量、耐缺氧、降血压、抑制血小板凝集、抗菌、保肝、抗诱变抗肿瘤、抑制肥大细胞组织胺释放、神经接头去极化等作用;有毒。\n" +
                "\n" +
                "【化学成分】本品含芍药苷、羟基芍药苷、苯甲酰芍药苷、芍药内酯苷、氧化芍药苷、白芍苷、常春藤皂苷元、芍药苷元酮、没食子酰芍药苷、山柰酚-3，7-二-O-β-D-葡萄糖苷、胡萝卜苷、β-谷固醇、芍药乳糖酮等，挥发油中主要含苯甲酸、牡丹酚等。\n" +
                "\n" +
                "【使用禁忌】不宜与藜芦同用，虚寒证不宜单用。\n" +
                "\n" +
                "【配伍药方】①治妇人妊娠腹中疼痛：当归90克，白芍480克，茯苓120克，白术120克，泽泻240克，川芎240克。上六味，杵为散。取方寸匕，酒和，日三服。(《金匮要略》当归芍药散)\n" +
                "\n" +
                "②治产后血气攻心腹痛：白芍60克，桂(去粗皮)、甘草(炙、锉)各30克。上三味，粗捣筛。每服9克，水一盏，煎至七分，去滓温服，不拘时候。(《普济方》芍药汤)\n" +
                "\n" +
                "③治衄血，咯血：白芍30克，犀角末0.3克。为末，水服，血止为限。(《古今录验》)\n" +
                "\n" +
                "④治衄血，汗血：白芍75克，生地黄汁二合，生藕汁一合，生姜汁少许。上四味，捣白芍药为末，先煎三物汁令沸。每以半盏，入熟水一合，白芍药末6克，搅匀，食后温饮之。(《圣济总录》白芍药散)\n" +
                "\n" +
                "⑤治泄痢腹痛：黄芩、白芍各30克，甘草15克。为粗末，每服15克，水煎。(《保命集》黄芩芍药汤)");
        chineseMedicineDao.insert(medicine246);

        ChineseMedicine medicine247=new ChineseMedicine();
        medicine247.setName("白芷");
        medicine247.setSortType("B");
        medicine247.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=66febdead662853586edda73f1861da3/83025aafa40f4bfb0643594c004f78f0f7361813.jpg");
        medicine247.setData("【中药名】白芷 baizhi\n" +
                "\n" +
                "【别名】香白芷、芷、苻蒿。\n" +
                "\n" +
                "【英文名】Angelicae Dahuricae Radix。\n" +
                "\n" +
                "【来源】伞形科植物杭白芷Angelica dahurica( Fisch. ex Hoffm)Benth. et Hook.f.var. formosana(Boiss.) Shan et Yuan或白芷Angelica dahurica(Fisch.ex Hoffm.)Benth. etHook.f.的根，产于河南长葛、禹县者习称“禹白芷”，产于河北安国者习称“祁白芷”。\n" +
                "\n" +
                "【植物形态】多年生草本，高1～2.5米。主根圆柱形，有分枝，外皮黄褐色。茎直立，基部直径5～9厘米，常带紫色，有纵沟纹。茎下部叶羽状分裂，有长柄；茎中部叶2～3回羽状分裂，叶柄下部成囊状膜质鞘；末回裂片长圆形、卵形或线状披针形，边缘具白色软骨质粗锯齿，基部沿叶轴下延成翅状；茎上部叶具囊状鞘。复伞形花序，总苞片无或1～2，鞘状；伞幅18～40(～70)；小总苞片5～10或更多，长约1厘米，比花梗长或等长；花白色。双悬果椭圆形，分果具5棱，侧棱翅状，无毛。花期7～9月，果期9～10月。\n" +
                "\n" +
                "【产地分布】生于草甸、灌木丛、河边沙土或石砾质土中，我国北方有栽培。分布于河北、河南、山西、内蒙古、陕西等地。\n" +
                "\n" +
                "【采收加工】夏、秋季叶黄时采挖，除去须根及泥沙，晒干或低温干燥。\n" +
                "\n" +
                "【药材性状】长圆锥形，长7～24厘米，直径1.5～2厘米。表面灰白色或黄白色，较光滑，有多数皮孑L样横向突起散生，并有支根痕.顶端有凹陷的茎痕。质坚硬而重，断面类白色，粉性，形成层环棕色，近圆形，皮部散有多数棕色油点。气芳香，味辛、微苦。\n" +
                "\n" +
                "【性味归经】性温，味辛。归胃经、大肠经、肺经。\n" +
                "\n" +
                "【功效与作用】散风除湿、通窍止痛、消肿排脓。属解表药下属分类的辛温解表药。\n" +
                "\n" +
                "【临床应用】用量3～9克，水煎服；或入丸、散；外用：适量，研末撒或调敷。用治感冒头痛、眉棱骨痛、鼻塞、鼻渊、牙痛、白带、疮疡肿痛。有较好的止痛作用，尤其对头痛等有良好的效果，并有解毒、消炎作用。近来发现，对心脏冠脉有扩张作用，可考虑用治冠心病。\n" +
                "\n" +
                "【药理研究】水煎剂还对多种细菌如大肠杆菌、宋氏痢疾杆菌、变形杆菌、伤寒杆菌、副伤寒杆菌、绿脓杆菌、霍乱杆菌等有抑制作用。对人型结核杆菌亦有抑制作用，甲醇提取物有抗辐射作用。另外还具有解热、镇痛、抗炎;缩短凝血时间、扩张冠状血管、降血压;光敏作用;具有抗微生物等作用。\n" +
                "\n" +
                "【化学成分】含挥发油。挥发油中检出29种化合物，含量较高的有甲基环葵烷、1-十四碳烯、月桂酸乙酯、数种呋喃香豆素。另外还含欧前胡素、白芷毒素、东莨菪素、白当归素、异欧前胡内酯、水合白当归素、异回芹内酯、氧化前胡素、茴香脑、香芹酚、棕榈酸等。\n" +
                "\n" +
                "【使用禁忌】阴虚血热者忌服，阴虚阳亢头痛者禁服。\n" +
                "\n" +
                "【配伍药方】①治头痛：石膏、白芷各6克，薄荷叶、芒硝各9克，郁金3克。上为极细末，口含水，鼻内搐之。(《兰室秘藏》白芷散)\n" +
                "\n" +
                "②治鼻渊：辛夷、葛风、白芷各2.4克，苍耳子3.6克，川芎1.5克，北细辛2.1克，甘草0.9克。白水煎，连服四剂，忌牛肉。(《疡医大全》)\n" +
                "\n" +
                "③治鼻流清涕不止：白芷为细末，以葱白捣烂为丸。小豆大。每服吮十丸，茶水送下。(《证治准绳》白芷丸)\n" +
                "\n" +
                "④治溃疡病胃痛：白芷、白芍、白及各10～30克，白豆蔻6～12克。每日1剂，水煎分服。(《中医杂志》l983，(12)：34四白汤)\n" +
                "\n" +
                "⑤治肿毒热痛：醋调白芷末敷之。(《卫生易简方》)");
        chineseMedicineDao.insert(medicine247);

        ChineseMedicine medicine248=new ChineseMedicine();
        medicine248.setName("白前");
        medicine248.setSortType("B");
        medicine248.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=94cbfc0939dbb6fd3156ed74684dc07d/730e0cf3d7ca7bcbcf578fddbe096b63f624a810.jpg");
        medicine248.setData("【中药名】白前 baiqian\n" +
                "\n" +
                "【别名】竹叶白前、草白前、鹅管白前、嗽药。\n" +
                "\n" +
                "【英文名】Cynanchum glaucescens。\n" +
                "\n" +
                "【来源】萝摩科植物芫花叶白前Cynanchum glaucescens (Decne.) Hand. -Mazz.的根及根茎。\n" +
                "\n" +
                "【植物形态】多年生草本。茎直立，幼枝被棕色茸毛。叶对生，几无柄；叶片椭圆形，先端圆或锐尖，基部楔形，全缘。聚伞花序腋；花萼黄绿色，近于全裂；花冠黄白色，深5裂；副花冠5，黄绿色；雄蕊5，药覆盖蕊柱先端；子房上位，心皮2，花柱2。瞢荚果1～2，狭长卵形。花期8月，果期9～10月。\n" +
                "\n" +
                "【产地分布】生于江边河岸及沙石间。主产于江苏、浙江、安徽等地。\n" +
                "\n" +
                "【采收加工】8月间挖根，或拔起全株，割去地上部分，洗净，晒干。\n" +
                "\n" +
                "【药材性状】根茎圆柱形，较短小，或略呈块状；表面灰绿色或淡黄色，平滑或有细纵纹。节明显，节间长1～2厘米，质地较坚硬，折断面中空，髓腔较小。节上簇生纤细弯曲的根，细根稍粗长，直径约1毫米，分枝的细根少，质脆，易折断。\n" +
                "\n" +
                "【性味归经】性微温，味辛、苦。归肺经。\n" +
                "\n" +
                "【功效与作用】降气、消痰、止咳。属化痰止咳平喘药下分类的止咳平喘药。\n" +
                "\n" +
                "【临床应用】用量3～9克，煎服；或入丸、散。用治肺气壅、咳嗽、多痰、胸满喘急等。\n" +
                "\n" +
                "【药理研究】具有镇咳、祛痰、平喘、抗炎等药理作用。\n" +
                "\n" +
                "【化学成分】含三萜皂苷，已分离得芫花叶白前苷元A、芫花叶白前苷元B和芫花叶白前苷元C-单黄夹竹桃糖苷，另得芫花叶白前苷元H、芫花叶白前苷元I、芫花叶白前苷元J等。本品还含白前皂苷、β-谷固醇、槲皮素-7-O-α-L鼠李糖苷、琥珀酸、华北白前醇等。\n" +
                "\n" +
                "【使用禁忌】阴虚火旺、肺肾气虚咳嗽者慎服。\n" +
                "\n" +
                "【配伍药方】①治肝炎：白前鲜根30克，白英30克，阴行草15克。水煎服。(江西《草药手册》)\n" +
                "\n" +
                "②治水肿：白前鲜根30克，星宿菜根、地菍根、灯心草各15克。水煎，酌加红糖调服。(江西《草药手册》)\n" +
                "\n" +
                "③治胃痛：白前根、威灵仙根各15克，肖梵天花根24克。水煎服。(《福建药物志》)\n" +
                "\n" +
                "④治跌打损伤：白前根15克，鸡蛋1粒或海蛏干30克，胁痛加香附子9克，青皮3克。水煎服。(《福建药物志》)\n" +
                "\n" +
                "⑤治麻疹：白前、葛根各15克。水煎服。(《福建药物志》)");
        chineseMedicineDao.insert(medicine248);

        ChineseMedicine medicine249=new ChineseMedicine();
        medicine249.setName("白术");
        medicine249.setSortType("B");
        medicine249.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=b422579489d4b31ce4319ce9e6bf4c1a/adaf2edda3cc7cd973df780e3b01213fb90e9173.jpg");
        medicine249.setData("【中药名】白术 baizhu\n" +
                "\n" +
                "【别名】于术、冬术、浙术、山蓟、山精。\n" +
                "\n" +
                "【英文名】Macrocephalae Rhizoma。\n" +
                "\n" +
                "【来源】菊科植物白术Atractylodes macrocephala Koidz.的根茎。\n" +
                "\n" +
                "【植物形态】多年生草本。根茎粗大，略呈拳状。茎直立，上部分枝，基部木质化，具不明显纵槽。单叶互生；叶片3深裂，偶为5深裂，椭圆形至卵状披针形，先端渐尖，基部渐狭下延呈柄状，叶缘均有刺状齿，上面绿色，下面淡绿色，叶脉凸起显著。头状花序顶生；总苞钟状，膜质，覆瓦状排列；基部叶状苞一轮包围总苞；花多数，着生于平坦的花托上；花冠管状，淡黄色，紫色先端5裂；雄蕊5，花药线形，花丝离生；雌蕊1，子房下位，密被淡褐色茸毛，花柱细长，柱头头状。瘦果长圆状椭圆形，微扁，被黄白色茸毛，冠毛羽状。花期9~10月，果期10~11月。\n" +
                "\n" +
                "【产地分布】生于山坡、林边及灌木林中。分布于安徽、浙江、江西等地。\n" +
                "\n" +
                "【采收加工】霜降至立冬采挖，除去茎叶和泥土，烘干或晒干，再除去须根即可。烘干者称“烘术”；晒干者称“生晒术”，亦称“冬术”。\n" +
                "\n" +
                "【药材性状】根茎略呈圆柱状块形，下部两侧膨大，长3~12厘米，直径2~7厘米。表面灰黄色或灰棕色，有瘤状突起及断续的纵皱纹和沟纹，并有须根痕，顶端有残留的茎基和芽痕。质坚实，不易折断，断面不平坦，淡黄色至淡棕色，并有棕色油室散在，烘干者断面角质样，色较深，有裂隙。气清香，味甜微辛，嚼之略带黏液性。\n" +
                "\n" +
                "【性味归经】性温，味甘、苦。归脾经、胃经。\n" +
                "\n" +
                "【功效与作用】健脾、益气、燥湿利水、止汗、安胎。属补虚药下属分类的补气药。\n" +
                "\n" +
                "【临床应用】用量6~12克，煎服；或入丸、散；或熬膏。用治脾胃气弱、不思饮食、倦怠少气、虚胀、泄泻、痰饮、水肿、黄疸、湿痹、小便不利、头晕、自汗、胎气不安等。\n" +
                "\n" +
                "【药理研究】有明显且持久的抗利尿作用，对小鼠艾氏腹水癌、淋巴肉瘤腹水瘤及人109食管癌都有显著的抑制作用；对胃肠系统有双向调节作用，能抗胃溃疡；此外，还有解痉、保肝、抗菌等药理作用。还具有促进肠胃蠕动、利胆、抗氧化、降血糖、抗凝血、抗菌、扩张血管、抑制心脏、镇静等作用。\n" +
                "\n" +
                "【化学成分】主要含有挥发油，挥发油中的主要成分为苍术酮。白术中还分离得一种多糖——甘露聚糖AM-3，有免疫增强作用。可应用薄层扫描法测定白术中苍术酮的含量。另外本品还含α-葎草烯、石竹烯、桉叶醇、双白术内酯、东莨菪素、γ-固醇等\n" +
                "\n" +
                "【使用禁忌】阴虚内热、津液亏耗者慎服;内有实邪雍滞者禁服。\n" +
                "\n" +
                "【配伍药方】①治脾虚胀满：白术60克，橘皮120克。为末，酒糊丸，梧桐子大。每食前木香汤送下三十丸。(《全生指迷方》宽中丸)\n" +
                "\n" +
                "②治痞，消食，强胃：白术60克，枳实(麸炒黄色，去穗)30克。上同为极细末，荷叶裹烧饭为丸，如梧桐子大。每服五十丸，多用白汤下，无时。(《内外伤辨惑论》引张洁古枳术丸)\n" +
                "\n" +
                "③治自汗不止：白术末，饮服方寸匕。日二服。(《千金要方》)\n" +
                "\n" +
                "④治风瘙瘾疹：白术末。酒服方寸匕，日三。(《千金要方》)\n" +
                "\n" +
                "⑤治嘈杂：白术(土炒)120克，黄连(姜汁炒)60克。上为末，神曲糊丸，黍米大。每服百余丸，姜汤下。(《景岳全书》术连丸)");
        chineseMedicineDao.insert(medicine249);

        ChineseMedicine medicine250=new ChineseMedicine();
        medicine250.setName("白及");
        medicine250.setSortType("B");
        medicine250.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=f7475fb54c086e067ea5371963611091/7acb0a46f21fbe0924bd00906b600c338644ad57.jpg");
        medicine250.setData("【中药名】白及 baiji\n" +
                "\n" +
                "【别名】白芨、连及草、冻疮药、甘根、白鸡儿。\n" +
                "\n" +
                "【英文名】Bletillae Rhizoma\n" +
                "\n" +
                "【来源】兰科植物白及Bletilla striata (Thunb.) Reichb.f.的块茎。\n" +
                "\n" +
                "【植物形态】多年生草本。块茎三角状扁球形或不规则菱形，肥厚肉质，富黏性，常数个相连。茎直立。叶3～5片，叶片披针形或宽披针形，先端渐尖，基部下延呈长鞘状抱茎，全缘。总状花序顶生，有花3～8朵；苞片披针形，早落；花紫色或淡红色；萼片3，花瓣状，与花瓣近等长，狭长圆形；花瓣3，唇瓣倒卵形，白色或具紫脉，上部3裂，中裂片边缘有波状齿，先端内凹，中央具5条褶片，侧裂片抱合蕊柱，稍伸向中裂片，但不及中裂片的1/2；雄蕊与雌蕊结合成合蕊柱，两侧有窄翅，柱头顶端有1枚雄蕊，花粉块8，成2群，扁而长；子房下位，圆柱形，扭曲。蒴果圆柱形，两端稍尖，具6条纵肋。花期4～5月，果期7～9月。\n" +
                "\n" +
                "【产地分布】生长于山野、山谷较潮湿处。分布于四川、贵州、云南等地。\n" +
                "\n" +
                "【采收加工】夏、秋季采收，除去茎叶及须根，洗净，置沸水中煮或蒸至无白心，晒至半干，除去外皮，晒干。\n" +
                "\n" +
                "【药材性状】不规则扁圆形，多有2～3个爪状分枝，长1.5～5厘米，淡黄白色，有数圈同心环节和棕色点状须根痕，上面有凸起的茎痕，下面有连接另一块茎的痕迹。质坚硬，不易折断，断面类白色，角质样。无臭，味苦，嚼之有黏性。\n" +
                "\n" +
                "【性味归经】性微寒，味苦、甘、涩。归肺经、肝经、胃经。\n" +
                "\n" +
                "【功效与作用】收敛止血、消肿生肌。属止血药下属分类的收敛止血药。\n" +
                "\n" +
                "【临床应用】用量6～15克，煎服，或3～6克，研粉吞服。用治咳血吐血、肺结核咳血、溃疡病出血。外用适量。用治外伤出血、疮疡肿毒、皮肤皲裂。不宜与乌头类药材同用。\n" +
                "\n" +
                "【药理研究】提取物对实验动物有良好的止血作用，可能与所含胶状成分有关，作用原理可能为物理性的。2%白及葡萄糖注射液做腹腔注射试验，对DAB诱发的大鼠肝癌的发生有明显抑制作用。具有止血、保护黏膜、抗肿瘤、抗菌等作用。\n" +
                "\n" +
                "【化学成分】本品含有联苄化合物、联菲化合物黄酮类、环烯醚萜苷类、固醇、有机酸、糖及苷类、氨基酸等化学成分。另含白及胶质(为由4份甘露糖和1份葡萄糖组成的葡配甘露聚糖)淀粉、葡萄糖、黏液质和蒽醌类化合物大黄素甲醚。\n" +
                "\n" +
                "【使用禁忌】外感及内热壅盛者禁服。不宜与川乌、制川乌、草乌、制草乌、附子同用。\n" +
                "\n" +
                "【配伍药方】①治支气管扩张咯血，肺结核咯血：白及、海螵蛸、三七各180克。共研细粉，每服9克，每日3次。(《全国中草药汇编》)\n" +
                "\n" +
                "②治肺叶痿败，喘咳夹红者：嫩白及12克研末，陈阿胶6克。冲汤调服。(《医醇媵义》白胶汤)\n" +
                "\n" +
                "③治肠胃出血：白及、地榆各等量。炒焦，研末。每服3克，温开水送服，每日2～3次。(《浙江民间常用草药》)\n" +
                "\n" +
                "④治肺痨：白及、百合各60克，红糖30克。药先煎，加入红糖熬成膏状。每次服1茶匙。(《湖南药物志》)");
        chineseMedicineDao.insert(medicine250);

        ChineseMedicine medicine251=new ChineseMedicine();
        medicine251.setName("北沙参");
        medicine251.setSortType("B");
        medicine251.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=0d276b8f0cd79123f4ed9c26cc5d32e7/7c1ed21b0ef41bd56ac9d37351da81cb38db3d9a.jpg");
        medicine251.setData("【中药名】北沙参 beishashen\n" +
                "\n" +
                "【别名】真北沙参、莱阳参、辽沙参、海沙参、银条参。\n" +
                "\n" +
                "【英文名】Glehniae Radix。\n" +
                "\n" +
                "【来源】伞形科植物珊瑚菜Glehnia littoralis Fr. Schmidt ex Miq.的根。\n" +
                "\n" +
                "【植物形态】多年生草本，高5～20厘米。主根细长，圆柱形，长达30厘米，直径0.5～1.5厘米，很少分枝。茎下部埋沙内，直立，不分枝。基生叶具长柄，基部呈宽鞘状，边缘膜质；叶片革质，卵圆形或宽三角状卵形，1～3回3出分裂至深裂。两面疏生细柔毛或无毛；茎上部叶不裂，卵形，边缘有锯齿。复伞形花序顶生，密生灰褐色绒毛；花瓣5枚，先端内折；雄蕊5枚；子房下位，花柱基扁圆锥形，花柱2裂。双悬果圆球形或椭圆形，直径达1厘米，果棱有木栓质翅，被棕色粗毛。花期5～7月，果期6～8月。\n" +
                "\n" +
                "【产地分布】栽培于肥沃疏松的沙质土壤或野生于海边沙滩。分布于辽宁、河北、山东等地。\n" +
                "\n" +
                "【采收加工】夏、秋季采挖，除去须根，洗净，稍晾，置沸水中烫后，除去外皮，干燥。或洗净直接干燥。\n" +
                "\n" +
                "【药材性状】细长圆柱形，偶有分枝，长15～45厘米，直径0.4～1.2厘米。表面淡黄白色，略粗糙，偶有残存外皮，不去外皮的表面黄棕色。全体有细纵皱纹及纵沟，并有棕黄色点状细根痕；顶端常留有黄棕色根茎残基；上端稍细，中部略粗.下部渐细。质脆，易折断，断面皮部浅黄白色，木部黄色。气特异，味微甘。\n" +
                "\n" +
                "【性味归经】性微寒，味甘、微苦。归肺经、胃经。\n" +
                "\n" +
                "【功效与作用】养阴清肺、益胃生津。属补虚药下属分类的补阴药。\n" +
                "\n" +
                "【临床应用】用量4.5～9克，水煎服。用治肺热燥咳、劳嗽痰血、热病津伤口渴。\n" +
                "\n" +
                "【药理研究】对心脏和血压有一定的影响，如水浸液在低浓度时能增强离体蟾蜍心脏的收缩，高浓度时出现抑制作用，心室停跳，但能恢复，对在体心脏亦有作用。近年来药理研究有较大进展，发现具有解热止痛、镇痛，免疫抑制等作用。\n" +
                "\n" +
                "【化学成分】本品含花椒毒素、别异欧前胡内酯、东莨菪素、印度榅桲素、佛手柑内酯、开环异落叶树脂酚、咖啡酸、丁香苷、香豆素类化合物等成分。另据报道，含挥发油、氨基酸及北沙参多糖。\n" +
                "\n" +
                "【使用禁忌】不宜与藜芦同用。风寒咳嗽及中寒便溏者禁服；痰热咳嗽者慎服。\n" +
                "\n" +
                "【配伍药方】①治一切阴虚火炎，似虚似实，逆气不降，清气不升，烦渴咳嗽，胀满不食：北沙参15克。水煎服。(《本草汇言》引《林仲先医案》)\n" +
                "\n" +
                "②治阴虚火炎，咳嗽无痰，骨蒸劳热，肌肤枯燥，口苦烦渴等证：北沙参、麦门冬、知母、川贝母、怀熟地黄、鳖甲、地骨皮各120克。或作丸，或作膏。每早服9克，白汤下。(《本草汇言》引《卫生易简方》)\n" +
                "\n" +
                "③治阴虚咳嗽或久咳音哑：北沙参、玄参、知母、牛蒡子各9克，生地黄15克。水煎服。(《山西中草药》)\n" +
                "\n" +
                "④治肺结核咳嗽：北沙参9克，麦冬6克，甘草3克。开水冲泡，代茶饮服。(《食物中药与便方》)\n" +
                "\n" +
                "⑤治急慢性支气管炎：北沙参、车前子各10克，生甘草5克，水煎。每日2～3次分服。(《食物中药与便方》)\n" +
                "\n" +
                "⑥治各种肺热咳嗽脓痰，咯血，衄血，哮喘：北沙参90克，诃子，栀子、茜草、紫草、紫草茸各15克，川楝子9克。共为细粉。每次3～6克，每日2～3次煎服。(《中国民族药志》七味沙参汤)\n" +
                "\n" +
                "⑦治热病后干渴，食欲不振：北沙参、麦冬、石斛各12克。生地黄、玉竹各15克。水煎服。(《青岛中草药手册》)\n" +
                "\n" +
                "⑧治虚火牙痛：北沙参、地骨皮各15克，生地黄、知母各9克，细辛1.5克。水煎服。(《安徽中草药》)");
        chineseMedicineDao.insert(medicine251);

        ChineseMedicine medicine252=new ChineseMedicine();
        medicine252.setName("巴戟天");
        medicine252.setSortType("B");
        medicine252.setMedicineImageUrl("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=a4a989131bd8bc3ed2050e98e3e2cd7b/8694a4c27d1ed21bc6dc12ccaf6eddc451da3f85.jpg");
        medicine252.setData("【中药名】巴戟天 bajitian\n" +
                "\n" +
                "【别名】巴戟、鸡肠风、兔子肠、巴吉天。\n" +
                "\n" +
                "【英文名】Morindae Officinalis Radix。\n" +
                "\n" +
                "【来源】茜草科植物巴戟天Morinda ojj:icinalis How昀干燥根。\n" +
                "\n" +
                "【植物形态】藤状灌木。根肉质肥厚，圆柱形，不规则地断续膨大，呈念珠状。小枝幼时被短粗毛，后变粗糙。叶对生，柄长4～8毫米，叶片长圆形，长6～10厘米，宽2.5～4.5厘米，先端急尖或短渐尖，基中钝或圆，上面被稀疏糙毛或无毛，下面沿中脉被粗短毛，脉腋内有短束毛。花序头状，3至数个伞形排列于枝端，总花梗长3～10毫米，头状花序有2～10朵，萼片2～3厘米长，裂片三角形，不等大，花冠白色，肉质，长可达7毫米，裂片4(3)，长椭圆形，内弯，雄蕊4，子房长约1.5毫米，花柱长约0.6毫米，2深裂，核果近球形，直径6～11毫米，熟时红色，小核有种子4粒，近卵形或倒卵形，背部隆起，侧面平坦，被白色短毛。花期4～7，果期6～11月。\n" +
                "\n" +
                "【产地分布】生于山谷、溪边、山地林下，分布于福建、江西、广东、海南、广西等省区，大量栽培于广东、广西。\n" +
                "\n" +
                "【采收加工】全年均可采挖，洗净，除去须根，晒至六七成千，轻轻捶扁，晒干。\n" +
                "\n" +
                "【药材性状】呈扁圆柱形，略弯曲，长短不等，直径0.5～2厘米。表面灰黄色或暗灰色，具纵纹和横裂纹，有的皮部横向断离露出木部；质韧，断面皮部厚，紫色或淡紫色，易与木部剥离；木部坚硬，黄棕色或黄白色，直径1～5毫米。气微，味甘而微涩。。\n" +
                "\n" +
                "【性味归经】性微温，味甘、辛。归肾经、肝经。\n" +
                "\n" +
                "【功效与作用】补肾阳，强筋骨，祛风湿。属补虚药下属分类的补阳药。\n" +
                "\n" +
                "【临床应用】内服：煎汤，6～15克；或入丸、散，亦可浸酒或熬膏。用治阳痿遗精，宫冷不孕，月经不调，少腹冷痛，风湿痹痛，筋骨痿软。\n" +
                "\n" +
                "【药理研究】有增强抗疲劳能力、抗炎、升高血中白细胞等作用。\n" +
                "\n" +
                "【化学成分】根含蒽醌类成分：甲基异茜草素、甲基异茜草素-1-甲醚，大黄素甲醚，2-羟基羟甲基蒽醌，1-羟基蒽醌 ，1-羟基-2-甲基蒽醌，1，6-二羟基-2，4-二甲氧基蒽醌，1，6-二羟基-2-甲氧基蒽醌，2-甲基蒽醌。又含葡萄糖，耐斯糖，甘露糖，β-谷甾醇，棕榈酸，维生素C，十九烷，24-乙基胆甾醇。根皮含锌、锰、铁、铬等23种元素。\n" +
                "\n" +
                "【使用禁忌】阴虚火旺及有湿热之证者忌服本品。\n" +
                "\n" +
                "【配伍药方】①治虚赢阳道不举，五劳七伤百病，能食，下气：巴戟天、土牛膝各三斤。以酒五斗浸之，去滓温服，常令酒气相及，勿至醉吐。(《千金要方》)\n" +
                "\n" +
                "②治阳痿：巴戟天6克，补骨脂6克。水煎服。(《甘肃中医验方集锦》)\n" +
                "\n" +
                "③治小便不禁：益智仁、巴戟天(去心，二味以青盐、酒煮)、桑螵蛸、菟丝子(酒蒸)各等分。为细末，酒煮糊为丸，如梧桐大。每服二十丸，食前用盐酒或盐汤送下。(《奇效良方》)\n" +
                "\n" +
                "④治偏坠：巴戟天(去心)、川楝(炒)、茴香(炒)等分，为末。每服6克，温酒调下。(《卫生易简方》)\n" +
                "\n" +
                "⑤治阳衰气弱，精髓空虚，形神憔悴，腰膝痿痹，或女人血海干虚，经脉断续，子嗣难成：巴戟天240克，当归、枸杞子各120克，广陈皮、川黄柏各30克。俱用酒拌炒，共为末，炼蜜丸，梧桐子大，每早晚各服9克。白汤下，男妇皆可用。(《本草汇言》)");
        chineseMedicineDao.insert(medicine252);

        ChineseMedicine medicine253=new ChineseMedicine();
        medicine253.setName("卜芥");
        medicine253.setSortType("B");
        medicine253.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546092587&di=f5027f1207f0c1c0248191e7a705b95b&imgtype=jpg&er=1&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F7a899e510fb30f247c287ad1ce95d143ac4b03b5.jpg");
        medicine253.setData("【中药名】卜芥 bujie\n" +
                "\n" +
                "【别名】老虎耳、狼毒、老虎芋、大附子、姑婆芋。\n" +
                "\n" +
                "【英文名】Hoodshaped Alocasia Rhizome。\n" +
                "\n" +
                "【来源】为天南星科植物尖尾芋Alocasia cucullata (Lour.) Schott的根茎。叶亦入药，治疗毒疮。\n" +
                "\n" +
                "【植物形态】直本草木。地下茎粗壮，肉质。地上茎圆柱形，黑褐色，具环形叶痕，通常基部伸出许多短缩的芽条，发出新枝。叶互生；叶柄由中部至基部强烈扩大成宽鞘；叶片膜质至草质，深绿色，宽卵状心形，长15～40厘米，宽10～18厘米，先端尖，基部微凹，全缘，叶脉两面突起。花序柄圆柱形，稍粗壮，常单生；佛焰苞近肉质，管长圆状卵形，淡绿色至深绿色，檐部狭舟状，边缘内卷，先端具狭长的凸尖，肉穗花序比佛焰苞短；雄花序位于上部，雌花的雌蕊子房一室，附属器淡绿色，黄绿色，狭圆锥形。浆果淡红色，球形，通常有种子一颗。\n" +
                "\n" +
                "【产地分布】生于海拔2000米以下的溪谷湿地或田边，亦栽培于庭院或药圃。分布中国南部和东南部，四川等地亦有栽培。\n" +
                "\n" +
                "【采收加工】全年均可采收。挖取根茎，洗净，鲜用或切片哂干。\n" +
                "\n" +
                "【药材性状】根状茎圆形或椭圆形，黑褐色，具环形叶痕，表面不平整，直径2.5～6厘米，表面具皱纹。横切片厚2～4毫米，常卷曲成各种形态，质轻，脆，易折断，断面白色，粗糙，呈颗粒状。气微，味辛，微苦，嚼之麻舌而刺喉。以质轻，易折断，断面白色呈颗粒状，嚼之麻舌而刺喉者为佳。\n" +
                "\n" +
                "【性味归经】性寒，微苦，有大毒。无归经。\n" +
                "\n" +
                "【功效与作用】清热解毒，散结止痛。属清热药下属分类的清热解毒药。\n" +
                "\n" +
                "【临床应用】内服： 煎汤，3～9克(鲜品30～60克。需炮制，宜煎2小时以上)。外用：适量，捣敷。主治流感，钩端螺旋体病，疮疡痈毒初起，瘰疬，蜂窝织炎，毒蛇咬伤，毒蜂螫伤。\n" +
                "\n" +
                "【药理研究】卜芥水提醇沉液有抗眼镜蛇蛇毒、眼镜王蛇蛇毒和银环蛇蛇毒的作用。\n" +
                "\n" +
                "【化学成分】含延胡索酸(fumaric acid)、焦黏酸(pyromucic acid)、苹果酸(malic acid)、β-谷甾醇(β-sitosterol)、草酸钙(calciumoxalate)、皂毒苷(sapotoxin)和氨基酸等。\n" +
                "\n" +
                "【使用禁忌】生品有大毒，禁作内服。内服需经炮制且不可过量。外用宜慎，因本吕外敷有致泡作用。\n" +
                "\n" +
                "【相关药方】①治钩端螺旋体病：(老虎芋)鲜品125克，炒焦，加食盐少许同炒，放500～1000毫升清水煮1～3小时，得药液约300毫升，分2～3次服。(南药《巾草药学》)\n" +
                "\n" +
                "②治无名肿毒，毒蛇咬伤，毒蜂螫伤：(尖尾芋)鲜根状茎适量。刮去粗皮，捣烂敷患处，每次5～10分钟，蛇伤敷伤口周围。(《浙江药用植物志》)");
        chineseMedicineDao.insert(medicine253);

        ChineseMedicine medicine254=new ChineseMedicine();
        medicine254.setName("八角枫");
        medicine254.setSortType("B");
        medicine254.setMedicineImageUrl("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=ba6fa228700e0cf3b4fa46a96b2f997a/faf2b2119313b07ee0bd5f320ed7912397dd8c83.jpg");
        medicine254.setData("【中药名】八角枫 bajiaofeng\n" +
                "\n" +
                "【别名】白金条、白龙须、八角梧桐、华瓜木。\n" +
                "\n" +
                "【英文名】Radix Alangii\n" +
                "\n" +
                "【来源】八角枫科植物八角枫Alangium chinense (Lour.)Harms。的侧根或细须根。\n" +
                "\n" +
                "【植物形态】落叶灌木或乔木；树皮淡灰色，平滑；枝条水平状展开。单叶互生，圆形、卵形或椭圆形，顶端渐尖，基部两侧不对称，阔楔形、截形、稀近心形，全缘或有2～3裂，裂片大小不一，背面脉分叉处常有丛毛，主脉4～6，叶柄红色。二歧聚伞花序腋生，具小花8～30朵，花白色，后变乳黄色，花丝基部及花柱疏生粗短毛。核果卵圆形，熟时黑色。花期6～7月，果期9～10月。\n" +
                "\n" +
                "【产地分布】生于较阴湿的山谷、山坡的杂木林中。分布于长江流域及其以南各地。\n" +
                "\n" +
                "【采收加工】夏秋两季采挖，除去泥沙，晒干。\n" +
                "\n" +
                "【药材性状】侧根：圆柱形，略波状弯曲，长短不一，有分枝，可见须根痕；表面灰黄色至棕黄色，栓皮显纵裂纹或剥落；质坚脆，断面不平坦，纤维性，黄白色。须根：着生于侧根中下部，纤长，略弯曲，有分枝，表面黄棕色，具细纵纹，有的外皮纵裂，质硬而脆，断面黄白色，粉性。气微，味淡。\n" +
                "\n" +
                "【性味归经】味辛、苦，性温，有毒。归心经、肝经。\n" +
                "\n" +
                "【功效与作用】祛风除湿、舒筋活络、散瘀止痛。属活血化瘀药下属中的活血止痛药。\n" +
                "\n" +
                "【临床应用】用量须根1.5～3克，侧根3～6克，煎服或泡酒服(一般宜饭后股)，用量由小逐渐加大，切勿过量；外用适量，煎水洗风湿痛处。用治四肢麻木、跌扑损伤。须根(白龙须)毒性较大，中毒轻者头昏、无力，重者可因呼吸抑制而致死。在抢救中首先考虑人工呼吸，其他对症治疗亦需及时。作肌肉松弛剂使用时，更应在有抢救条件的医院进行。\n" +
                "\n" +
                "【药理】根煎剂或生物总碱，无论是静脉注射还是腹腔注射，均有明显的肌松作用，但不影响动物的清醒状态；一般引起肌松作用的剂量能增强离体家兔肠管的节律性收缩， 并呈量效关系。小剂量对离体蛙心和兔心灌注时，可使收缩加强；加大剂量收缩减弱，甚至导致房室传递阻滞，但可自行恢复，还具抗菌作用，对心血管系统有抑制作用，增强肠、子宫平滑肌收缩，增强催眠药作用，抗早孕与抗着床，抗炎等，有肾毒性。\n" +
                "\n" +
                "【化学成分】含喜树次碱、消旋毒黎碱、β-香树脂醇己酸酯、三十烷醇、β-谷固醇，根、根皮中含八角枫碱，以须根中含量最多，达0.73%，分得的单体生物碱主要是dl-毒藜碱；还分离出吐根碱、N-甲基吐根酚碱，此外还有强心苷、酚类、氨基酸、有机酸、树脂等。\n" +
                "\n" +
                "【使用禁忌】八角枫有毒，使用时需严格掌握剂量，一般宜从小量开始，至病人出现软弱无力、疲倦时即不应再增用量。孕妇忌服，小儿和年老体弱者慎用，肝、肾、肺功能欠佳、心律不齐者忌用。\n" +
                "\n" +
                "【相关药方】①治虚弱，喘咳：八角枫根3克，炖肉吃。(《贵州草药》)\n" +
                "\n" +
                "②治筋骨疼痛：白龙须1.2克，白牛膝9克。炖猪脚吃。(《曲靖专区中草药》)\n" +
                "\n" +
                "③治小儿惊风：八角枫根1.5克，煎水服。(《贵州草药》)\n" +
                "\n" +
                "④治鼻出血：八角枫6克，水煎服。(《贵州民间方药集》)\n" +
                "\n" +
                "⑤治过敏性皮炎：八角枫根(适量)，煎水外洗。(《云南中草药》)");
        chineseMedicineDao.insert(medicine254);

        ChineseMedicine medicine255=new ChineseMedicine();
        medicine255.setName("白头翁");
        medicine255.setSortType("B");
        medicine255.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=3c7e5a0aefc4b7452099bf44ae957572/1c950a7b02087bf43cfa8bcaf6d3572c11dfcffb.jpg");
        medicine255.setData("【中药名】白头翁 baitouweng\n" +
                "\n" +
                "【别名】老翁须、白头公、白头翁草、野丈人、胡王使者。\n" +
                "\n" +
                "【英文名】Pulsatillae Radix。\n" +
                "\n" +
                "【来源】毛茛科植物白头翁Pulsatilla chinensis (Bge.) Regel的根。\n" +
                "\n" +
                "【植物形态】多年生草本，高达50厘米，全株密被白色长柔毛。主根粗壮，圆锥形，有时扭曲，外皮黄褐色。叶基生，3全裂，顶生小裂片具短柄，侧生小叶片无柄，上面疏被伏毛，下面密被伏毛。花茎1～2，花后伸长，密被长柔毛，花单一，萼片6，花瓣状，紫色，外面密被长绵毛，雄蕊多数，雌蕊有多数离生心皮，花柱丝状，果时延长，密被白色羽状毛。瘦果密集成头状，顶端有细长的羽毛状宿存花柱。\n" +
                "\n" +
                "【产地分布】生于山野、山坡及田野间，喜生于向阳处。分布于黑龙江、吉林、辽宁、内蒙古、河北等地。\n" +
                "\n" +
                "【采收加工】春季(4～6月)或秋季(8～9月)挖根，除去叶及残留的花茎和须根，保留根头白色茸毛，去净泥土，晒干。\n" +
                "\n" +
                "【药材性状】长圆柱形或圆锥形，稍弯曲，有时扭曲而稍扁，长5～20厘米，直径0.5～2厘米。表面黄棕色或棕褐色，有不规则的纵皱纹或纵沟，中部有时分出2～3支根，皮部易脱落而露出黄色木质部，且常朽蚀成凹洞，可见纵向突起的网状花纹，根头部稍膨大，有时分叉，顶端残留数层鞘状叶柄基及幼叶，密生白色长茸毛。质硬脆.折断面稍平坦，黄白色，皮部与木质部间有时出现空隙。气微，味微苦涩。以条粗长、质坚实者为佳。\n" +
                "\n" +
                "【性味归经】性寒，味苦。归胃经、大肠经。\n" +
                "\n" +
                "【功效与作用】清热解毒、凉血止痢。属清热药下分类的清热凉血药。\n" +
                "\n" +
                "【临床应用】用量10～15克，内服煎汤，或入丸散，治疗细菌性痢疾、阿米巴痢疾、鼻血、痔疮出血等。外用适量，捣敷，此外，还有抗滴虫、镇静、镇痛等作用。治疗原虫性痢疾：白头翁根茎15～30克，水煎分3次服，7天为一疗程；治疗细菌性痢疾：白头翁15克，黄柏9克，秦皮5克，木香、陈皮、甘草各2.5克，水煎服。\n" +
                "\n" +
                "【药理研究】煎剂及其皂苷体内外均有明显的抗阿米巴原虫作用，毒性很低;水提醇沉注射液能明显抑制体内移植瘤和荷瘤小鼠存活时间，还能提高机体免疫力，降低脾指数，升高胸腺指数;乙醇提取液对试管内的多种细菌和真菌有不同程度的抑制作用，抗菌有效成分是原白头翁素和白头翁素。具有抗菌、抗阿米巴原虫、抗病原体、镇静、镇痛等作用。\n" +
                "\n" +
                "【化学成分】全草含原白头翁素，根含三萜皂苷，水解后得苷元及葡萄糖和鼠李糖。另含白头翁皂苷B4、白桦脂酸、白头翁素、白头翁灵、莽草酸等。\n" +
                "\n" +
                "【使用禁忌】虚寒泻痢者慎服。\n" +
                "\n" +
                "【相关药方】①治热痢下重：白头翁60克，黄连、黄柏、秦皮各90克。上四味，以水七升，煮取二升，去滓。温服一升，不愈更服。(《金匮要略》白头翁汤)\n" +
                "\n" +
                "②治温疟发作、昏迷如死：白头翁30克，柴胡、半夏、黄芩、槟榔各6g，甘草2.1克，水煎服。(《本草汇言》)\n" +
                "\n" +
                "③治外痔：白头翁，以根捣细贴之，逐血止痛。(《卫生易简方》)\n" +
                "\n" +
                "④治男子疝气，或偏坠：白头翁、荔枝核各60克，俱酒浸，炒为末，每早服9克。白汤调下。(《本草汇言》)⑤治气喘：白头翁6克，水煎服。(《文堂集验方》)");
        chineseMedicineDao.insert(medicine255);

        ChineseMedicine medicine256=new ChineseMedicine();
        medicine256.setName("北豆根");
        medicine256.setSortType("B");
        medicine256.setMedicineImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546092977&di=91c20c0947d55c401273dbc5d4e4af6c&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.shuzibencao.com%2Fmedias%2Fsys_master%2F0000029000%2F0000029000%2Fh09%2Fhc4%2F8856105320478%2F0000029000-DESCRIPTION-20180514145557-99.jpg");
        medicine256.setData("【中药名】北豆根 beidougen\n" +
                "\n" +
                "【别名】磨石豆根、山花子根、蝙蝠葛根、马串铃、山豆根、苦豆根。\n" +
                "\n" +
                "【英文名】Menispermi Rhizoma。\n" +
                "\n" +
                "【来源】防己科植物蝙蝠葛Menispermum dauricum DC.的根茎。\n" +
                "\n" +
                "【植物形态】多年生缠绕草本。长达10米以上，全体近无毛。根茎多横生，细长。茎基部稍木质，小枝绿色，有纵条纹。叶互生，叶柄盾状着生，长6～12厘米，被稀短毛；叶片圆肾形或卵圆形，长、宽为5～15厘米，先端尖，基部心形或截形，叶缘近全缘或5～7浅裂，裂片近三角形，上面绿色，下面苍白色，具掌状脉5～7条。花序腋生，短圆锥状，花小，单性异株，雄花序总花梗长3～7厘米，花梗长5～7毫米，基部具小苞片1，线状披针形，雄花萼片5，窄倒卵形，花瓣6～9，黄绿色，卵圆形，较花萼小，雄蕊10～20枚，花药球形，雌花通常具3心皮，分离，花柱短。核果球形，熟时黑紫色。种子l粒，马蹄形。\n" +
                "\n" +
                "【产地分布】生于山地灌木丛中或攀缘于岩石上。分布于黑龙江、吉林、辽宁、河北、内蒙古等地。\n" +
                "\n" +
                "【采收加工】春、秋季采挖，除去茎叶、须根和泥土，晒干。\n" +
                "\n" +
                "【药材性状】呈细长圆柱形，常弯曲，有时可见分枝，长约50厘米，直径3～8毫米。表面黄棕色至暗棕色，有纵皱纹及稀疏的细根或突起的细根痕，外皮易成片脱落。质韧，不易折断，折断面不整齐.纤维性，维管束呈放射状排列，木质部淡黄色，中心有类白色的髓。气微，味苦。\n" +
                "\n" +
                "【性味归经】味苦，性寒；有小毒。归肺经、胃经、大肠经。\n" +
                "\n" +
                "【功效与作用】清热解毒、消肿止痛，属清热药下分类的清热解毒药。\n" +
                "\n" +
                "【临床应用】用量6～9克，内服煎汤，治疗咽喉肿痛、肺热咳嗽、外痔。治疗扁桃体炎和咽喉炎：北豆根、鬼针草等量，煎服；治痢疾、肠炎：北豆根15克，水煎服，或北豆根15克、徐长卿9克，水煎服。此外，具抗菌作用，对金葡菌、溶血性链球菌等有抑制作用。\n" +
                "\n" +
                "【药理研究】本品含的蝙蝠葛碱为广泛的抗心律失常药，直接抑制窦房结，减慢心率，明显抑制豚鼠离体灌流心脏去甲肾上腺素出胞释放量，能降低血压、扩张冠状动脉、抑制心肌和抗心律失常；蝙蝠葛苏林碱静脉注射能明显对抗小鼠常压缺氧，抗脑缺血。毒性：蝙蝠葛碱腹腔注射小鼠LD50为(205±24)毫克/千克。近年来，蝙蝠葛碱主要试用于治疗心律失常及高血压病。另外本品还具有抗心律失常、降压、抑制血小板聚集、抗血栓形成的作用；尚具有抗炎、镇痛、镇咳祛痰、抑菌、局麻、松弛肌肉等作用。\n" +
                "\n" +
                "【化学成分】根茎中含有多种生物碱，如山豆根碱、去甲山豆根碱、山豆根酚碱、蝙蝠葛碱、蝙蝠葛苏林碱、木兰花碱、青藤碱、N-去甲尖防己碱、尖防己碱、蝙蝠葛辛、山豆根碱、青藤碱等成分。\n" +
                "\n" +
                "【使用禁忌】脾虚便溏者禁服，孕妇及有肝病患者慎服。剂量不宜过大。\n" +
                "\n" +
                "【相关药方】①治咽喉肿痛：a.北豆根、射干各3克。共研细末，吹入咽喉。b.北豆根9克，桔梗3克，酸浆8个。水煎，日服2次。(a、b方出自《吉林中草药》)c.北豆根、玄参、牛蒡子各15克，枯梗7.5克，甘草5克。水煎服。(《长白山植物药志》)\n" +
                "\n" +
                "②治慢性扁桃体炎：北豆根9克，金莲花3克，生甘草6克。水煎服。(《河北中草药》)\n" +
                "\n" +
                "③治肺热咳嗽：北豆根、前胡、牛蒡子、枇杷叶各9克。水煎服。(《陕甘宁青中草药选》)\n" +
                "\n" +
                "④治痢疾、肠炎：北豆根9克，徐长卿9克。水煎服。(《浙江药用植物志》)\n" +
                "\n" +
                "⑤治牙痛：北豆根9克，玄参、地骨皮各6克，甘草3克，水煎服。(《全国中草药汇编》)\n" +
                "\n" +
                "⑥治腰痛：北豆根30克，白酒50毫米，浸7天，每日2次，每次饮1杯。(《吉林中草药》)\n" +
                "\n" +
                "⑦治湿热黄疸：北豆根9克，茵陈15克，生大黄6克，栀子9克。水煎服。(《陕甘宁青中草药选》)\n" +
                "\n" +
                "⑧治秃疮：北豆根30克，白糖适量，共研末，用鸡蛋捣成膏，敷患处。(《吉林中草药》)");
        chineseMedicineDao.insert(medicine256);

        ChineseMedicine medicine257=new ChineseMedicine();
        medicine257.setName("白首乌");
        medicine257.setSortType("B");
        medicine257.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=a902ae5c252eb938f86072a0b40bee50/35a85edf8db1cb13b4b191aed754564e93584bb7.jpg");
        medicine257.setData("【中药名】白首乌 baishouwu\n" +
                "\n" +
                "【别名】隔山消、白何首乌、隔山撬、白木香。\n" +
                "\n" +
                "【英文名】Radix Cynanchi Bungei\n" +
                "\n" +
                "【来源】萝藦科植物牛皮消Cynanchum auriculatumRoyle ex Wight的干燥块根。\n" +
                "\n" +
                "【植物形态】蔓性半灌木。具乳汁。根肥厚，类圆柱形，表面黑褐色，断面白色。茎被微柔毛。叶对生；叶柄长3～9cm；叶片心形至卵状心形，长4～12cm，宽3～10cm，先端短渐尖，基部深心形，两侧呈耳状内弯，全缘，上面深绿色，下面灰绿色，被微毛。聚伞花序伞房状，腋生；总花梗圆柱形，长10～15cm，着花约30朵；花萼近5全裂，裂片卵状长圆形，反折；花冠辐状，5深裂，裂片反折，白色，内具疏柔毛；副花冠浅杯状。裂片椭圆形，长于合蕊柱，在每裂片内面的中部有一个三角形的舌状鳞片；雄蕊5，着生于花冠基部，花丝连成筒状，花药2室，附着于柱头周围，每室有黄色花粉块1个，长圆形，下垂；雌蕊由2枚离生心皮组成，柱头圆锥状，先端2裂。蓇葖果双生，基部较狭，中部圆柱形，上部渐尖，长约8cm，直径约1cm。种子卵状椭圆形至倒楔形。边缘具狭翅，先端有一束白亮的长绒毛。花期6～9月，果期7～11月。\n" +
                "\n" +
                "【产地分布】生于海拔3500m以下的山坡岩石缝中、灌丛中或路旁、墙边、河流及水沟边潮湿地。分布于华东、中南及河北、陕西、甘肃、台湾、四川、贵州、云南等地。山东、江苏有栽培。\n" +
                "\n" +
                "【采收加工】春初或秋季采挖块根，洗净泥土，除去残茎和须根，晒干，或趁鲜切片晒干。鲜品随采随用。\n" +
                "\n" +
                "【药材性状】根长圆柱形、长纺锤形或结节状圆柱形，稍弯曲，长7～15cm，直径1～4cm。表面浅棕色，有明显的纵皱纹及横长皮孔，栓皮脱落处土黄色或浅黄棕色，具网状纹理。质坚硬，断面类白色，粉性，具鲜黄色放射状纹理。气微，味微甘后苦。\n" +
                "\n" +
                "【性味归经】性平，味甘、微苦。归肝经、肾经、脾经、胃经。\n" +
                "\n" +
                "【功效与作用】补肝肾，强筋骨，益精血，健脾消食，解毒疗疮。属补虚药下分类的补血药。\n" +
                "\n" +
                "【临床应用】内服：煎汤，用量6～15g，鲜品加倍；研末，每次1～3g；或浸酒。外用：适量，鲜品捣敷。主治腰膝酸痛，阳痿遗精，头晕耳鸣，心悸失眠，食欲不振，小儿疳积，产后乳汁稀少，疮痈肿痛，毒蛇咬伤。\n" +
                "\n" +
                "【药理】具有抗氧化、抗肿瘤作用，调节免疫功能，影响心脏功能，降血脂；具有改善溶血性贫血及促进毛发生长等作用。\n" +
                "\n" +
                "【化学成分】含隔山消苷C₃；N、隔山消苷C₁；N、隔山消苷C₁；G，隔山消苷K₁；N、牛皮消苷A、牛皮消苷B、牛皮消苷C、萝藦胺、牛皮消素、萝藦苷元、12-O-桂皮酰基去酰萝藦苷元、戟叶牛皮消苷、白首乌二苯酮、多种氨基酸、维生素、无机元素。\n" +
                "\n" +
                "【使用禁忌】内服不宜过量。\n" +
                "\n" +
                "【相关药方】①治腰腿疼痛，关节不利：白首乌15g，牛膝6g，菟丝子9g，补骨脂6g，枸杞子9g。水煎服。(《山西中草药》)\n" +
                "\n" +
                "②治阳痿：白首乌、淫羊藿、山药、党参各9～12g。水煎服。(《陕甘宁青中草药选》)\n" +
                "\n" +
                "③治神经衰弱，阳痿，遗精：白首乌15g，酸枣仁9g，太子参9g，枸杞子12g。水煎服。(《山西中草药》)\n" +
                "\n" +
                "④治胃痛，痢疾腹痛：白首乌、蒲公英各9g。水煎服。(《安徽中草药》)\n" +
                "\n" +
                "⑤治脚气水肿：白首乌、车前子各6g。水煎去渣，每日分2次服。(《食物中药与便方》)\n" +
                "\n" +
                "⑥治小儿脾胃虚弱，消化不良，食积，腹泻：白首乌、糯米草、鸡屎藤各等分，研末备用。每次9g，加米粉18g，蒸熟食。(《四川中药志》1982年)\n" +
                "\n" +
                "⑦治乳汁不足：牛皮消根(去皮)30g，母鸡1只(去内脏)。将药放入鸡腹内，炖熟，去药渣，汤肉同服，不放盐。(《湖北中草药志》)\n" +
                "\n");
        chineseMedicineDao.insert(medicine257);

        ChineseMedicine medicine258=new ChineseMedicine();
        medicine258.setName("白药子");
        medicine258.setSortType("B");
        medicine258.setMedicineImageUrl("http://img007.hc360.cn/hb/MTQ3MjA2NjY3NjMxMy02ODI4MzM3NzU=.jpg");
        medicine258.setData("【中药名】白药子 baiyaozi\n" +
                "\n" +
                "【别名】白药、白药根、山乌龟、盘花地不容。\n" +
                "\n" +
                "【英文名】Radix Stephaniae Cepharantha\n" +
                "\n" +
                "【来源】防己科植物金线吊乌龟(头花千金藤)Stephania cep-hurantha Havata的干燥块根。\n" +
                "\n" +
                "【植物形态】多年生落叶藤本。块根肥厚，椭圆形或呈不规则块状，长3～10cm，直径2～9cm。老茎基部稍木质化，有细沟纹，略带紫色；叶互生；叶柄盾状着生；叶片圆三角形，或扁圆形，长5～9cm，宽与长近相等或大于长度；先端钝圆，常具小突尖，基部微凹或平截，全缘或微呈波状，上面绿色，下面粉白色，两面无毛，掌状脉5～9条，纸质。花小，单性，雌雄异株；雄株为复头状聚伞花序，腋生，总花序梗长1～2cm，花序梗顶端有盘状花托，约有20朵花；雄花：萼片6(～8)，排成2轮；花瓣3，淡绿色，内面有2个大腺体；雄蕊6，花丝合生成柱状，花药环生呈圆盘状；雌株为单头状聚伞花序，腋生，总花梗较短，顶端有盘状花托；雌花；花被左右对称；花萼1(～2)，生于花的一侧；花瓣2(～3)；子房球形。核果紫红色，球形，果梗短，肉质，内果皮直径4～5mm，背部有4行小横肋状雕纹。花期6～7月，果期8～9月。\n" +
                "\n" +
                "【产地分布】生长于肥沃湿润的草丛、山坡路旁阴处或灌木林中，亦生于石灰质石山上。分布于江苏、安徽、浙江、江西、福建、台湾、湖南、广东、广西、贵州等地。\n" +
                "\n" +
                "【采收加工】全年或秋末冬初采挖，除去须根、泥土，洗净，切片，晒干。\n" +
                "\n" +
                "【药材性状】块根呈不规则团块或短圆柱形，直径2～9cm，其下常有几个略短圆柱形的根相连，稍弯曲，有缢缩的横沟，根的远端有时纤细，其后膨大成椭圆形，并常数个相连成念珠状；根的顶端有根茎残基。市售品多为横切或纵切的不规则块片，直径2～7cm，厚0.2～1.5cm，表面棕色或暗褐色，有皱纹及须根痕，切面粉性足，类白色或灰白色，可见筋脉纹(维管束)，呈点状或条纹状排列。质硬脆，易折断，断面粉性。气微，味苦。\n" +
                "\n" +
                "【性味归经】性凉，味苦、辛，有小毒。归肺经、胃经。\n" +
                "\n" +
                "【功效与作用】清热解毒，祛风止痛，凉血止血。属清热药下分类的清热解毒药。\n" +
                "\n" +
                "【临床应用】内服：煎汤，用量9～1 5g；或入丸、散。外用：适量，捣敷或研末敷。主治咽喉肿痛、热毒痈肿、风湿痹痛、腹痛、泻痢、吐血、衄血、外伤出血。\n" +
                "\n" +
                "【药理】有解蛇毒、抗结核、抗麻风、抗变态反应、促进骨髓组织增生等作用。\n" +
                "\n" +
                "【化学成分】本品含左旋异紫堇定、异粉防己碱、轮环藤宁碱、头花千金藤碱、小檗胺等成分。\n" +
                "\n" +
                "【使用禁忌】脾虚及泄泻者禁服。\n" +
                "\n" +
                "【相关药方】①治胃及十二指肠溃疡：白药子1000g，甘草500g，研末，每日三日，每次3g，用开水送服。(《湖南药物志》)\n" +
                "\n" +
                "②治扭挫伤：白药子30g，连钱草30g，三七草15g，捣烂敷伤处：㈡湖南药物志》)\n" +
                "\n" +
                "③治肝硬化腹水：白药子(用老糠炒制)9g，车前15g，过路黄、白花蛇舌草、瓜子金、丹参各30g。水煎服。(《江西草药》)\n" +
                "\n" +
                "④治风湿性关节炎：白药子30g，蜈蚣兰、活血丹各15g。黄酒500g，浸3天，每日服2次，每次1调羹，饭后服。(《浙江民间常用草药》)\n" +
                "\n" +
                "⑤治乳汁少：白药子为末，每服3g，煎猪蹄汤调下。(《卫生易简方》)");
        chineseMedicineDao.insert(medicine258);

        ChineseMedicine medicine259=new ChineseMedicine();
        medicine259.setName("白兰花");
        medicine259.setSortType("B");
        medicine259.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=7d494b01d162853586edda73f1861da3/d043ad4bd11373f0641b5e6ca00f4bfbfbed0418.jpg");
        medicine259.setData("【中药名】白兰花 bailanhua\n" +
                "\n" +
                "【别名】白兰、白玉兰。\n" +
                "\n" +
                "【英文名】Micheliae Albae Flos。\n" +
                "\n" +
                "【来源】木兰科植物白兰Michetia alba DC.的花。\n" +
                "\n" +
                "【植物形态】常绿乔木。高达10~20米(江、浙等地天气较寒，常呈灌木状，高仅1~2米)，树皮灰色，幼枝和芽被白色柔毛。叶薄革质，互生，卵状椭圆形或长圆形，长10~25厘米，宽4~9厘米，两端均渐狭，两面无毛或于下面被疏毛，小脉网状；叶柄长1.5~2厘米，上有短的托叶痕迹，约为柄全长的1/3或1/4。花白色，单花腋生，极香，长3~4厘米；萼片长圆形，花瓣线状，长3,2厘米；雄蕊多数，多列，花丝扁平；心皮多数，胚珠在每心皮内多于2，螺旋状排列于延长有柄的花托上，子房被毛，柱头头状。果近球形，由多数开裂的心皮组成，多不结实。花期7月。\n" +
                "\n" +
                "【产地分布】生于路旁或庭院中。分布于福建、广东、广西、云南、四川、江苏、浙江、安徽、江西等地。\n" +
                "\n" +
                "【采收加工】夏末秋初花开时采收，鲜用或晒干。\n" +
                "\n" +
                "【药材性状】白色，单花腋生，极香，长3~4厘米；萼片长圆形；花瓣线状，长3.2厘米；雄蕊多数，多列，花丝扁平；心皮多数，胚珠在每心皮内多于2，螺旋状排列于延长有柄的花托上，子房被毛，柱头头状。\n" +
                "\n" +
                "【性味归经】性温，味苦、辛。归肺经、胃经。\n" +
                "\n" +
                "【功效与作用】止咳、化浊。属化痰止咳平喘药下属分类的止咳平喘药。\n" +
                "\n" +
                "【临床应用】用量10~15克。用治胸闷腹胀、中暑、咳嗽、慢性支气管炎、前列腺炎、妇女白带。\n" +
                "\n" +
                "【主要成分】叶含生物碱、挥发油、酚类。鲜叶含油0.7%，油主要成分为芳樟醇、甲基丁香油酚和苯乙醇。根和茎皮含黄心树宁碱、氧化黄心树宁碱、柳叶木兰碱和白兰花碱。用其蒸馏液(1：4)做动物实验，镇咳(氨水引咳法)、祛痰(酚红法)、平喘(组织胺致喘)作用都不强。\n" +
                "\n" +
                "【使用禁忌】尚不明确。");
        chineseMedicineDao.insert(medicine259);

        ChineseMedicine medicine260=new ChineseMedicine();
        medicine260.setName("白马骨");
        medicine260.setSortType("B");
        medicine260.setMedicineImageUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=46b85ceef3deb48fef64a98c9176514c/810a19d8bc3eb13588b87134a61ea8d3fc1f44c6.jpg");
        medicine260.setData("【中药名】白马骨 baimagu\n" +
                "\n" +
                "【别名】六月雪、路边鸡、路边荆。\n" +
                "\n" +
                "【英文名】Root Of Snow Of June。\n" +
                "\n" +
                "【来源】茜草科植物白马骨Serissa serissoides (DC.) Druce.的地上部分。\n" +
                "\n" +
                "【植物形态】小灌木。小枝密生，嫩枝有毛。叶狭椭圆形或椭圆状披针形，长7~15毫米，宽2~5毫米，顶端有小凸尖，基部渐狭成叶柄；托叶宿存，基部宽，顶端有刺齿。花单生或数朵腋生或顶生；花萼裂片三角形，长约1毫米；花冠淡红色，漏斗状，4~6裂；雄蕊4~6，生于花冠筒上；子房2室，柱头2裂。核果小，球形。花期5~6月，果期7~8月。\n" +
                "\n" +
                "【产地分布】生于向阳山坡、路旁、溪谷边或空旷地。分布于广东、广西、四川、贵州、江西、江苏、浙江、福建等地。\n" +
                "\n" +
                "【采收加工】10~11月割取茎叶，鲜用或晒干。根宜栽培3~4年，冬季挖掘，抖去泥土，晒干或烘干。\n" +
                "\n" +
                "【药材性状】枝呈深灰色，表面有纵裂隙，栓皮往往剥离。嫩枝深灰色，节处围有膜质的托叶，花全生于枝端，花萼呈灰白色，5裂，膜质。枝质稍硬，折断面带纤维性。叶大部脱落，少数留存，绿黄色，薄革质，卷曲不平，质脆易折断。\n" +
                "\n" +
                "【性味归经】性凉，味苦、微辛。归肝经、脾经\n" +
                "\n" +
                "【功效与作用】疏风、清热、利湿、解毒。属清热药下属分类的清热解毒药。\n" +
                "\n" +
                "【临床应用】用量10~15克。用治时行感冒、咳嗽咽痛、牙龈肿痛、目赤肿痛、湿热黄疸、暑湿泻痢、淋浊带下、风湿热痹、风痛、痈疽肿毒、瘰疬恶疮、跌打损伤、毒蛇咬伤。\n" +
                "\n" +
                "【主要成分】煎剂及乙醇浸剂对大鼠蛋清性关节炎有显著抑制作用；对甲醛性关节炎也有一定抑制作用；对葡萄球菌有抑制作用。\n" +
                "\n" +
                "【使用禁忌】脾胃虚寒慎服。\n"
                );
        chineseMedicineDao.insert(medicine260);

        ChineseMedicine medicine261=new ChineseMedicine();
        medicine261.setName("白背叶根");
        medicine261.setSortType("B");
        medicine261.setMedicineImageUrl("http://img.bimg.126.net/photo/U7bhPhw16UP6Ob8OaQS_fA==/4852347123532062492.jpg");
        medicine261.setData("【中药名】白背叶根 baibeiyegen\n" +
                "\n" +
                "【别名】白膜根、白朴根。\n" +
                "\n" +
                "【英文名】Radix Et Rhizoma Malloti Apeltae。\n" +
                "\n" +
                "【来源】大戟科植物白背叶Mallotus apelta (Loureiro) Mueller Argoviensis.的根。\n" +
                "\n" +
                "【植物形态】灌木或小乔木。小枝、叶柄和花序均被灰白色星状茸毛。叶互生，圆卵形，先端渐尖，基部近截形或短截形，具2腺点，全缘或不规则3裂，有稀疏钝齿，上面近无毛，下面灰白色，密被星状茸毛，有细密棕色腺体。圆锥花序顶生或腋生，被黄褐色茸毛；花单性，雌雄异株；雄花簇生，萼片3到4，卵形，外被毛，内有红色腺点，无花瓣，雄蕊多数，花丝分离；雌花单生，无花瓣，子房3室，密生星状茸毛，花柱3。蒴果近球形，密生羽毛状软刺，种子细小，圆形，黑色，有光泽。花期6~7月，果期10~ 11月。\n" +
                "\n" +
                "【产地分布】生于山谷、村边、路旁、灌木丛或草丛中。分布于广东、广西、福建、湖南等地。\n" +
                "\n" +
                "【采收加工】全年可采挖，挖取根部，除去须根及泥沙，洗净，切成块、片，晒干。\n" +
                "\n" +
                "【药材性状】不规则块状或圆柱形短段。表面黑褐色或黄褐色，皮薄，可撕离，略带纤维性。质坚硬，难折断，断面纤维性，皮部薄，黄褐色，木部淡黄白色，密布小孔。气微，味微苦。\n" +
                "\n" +
                "【性味归经】性平，味微涩、微苦。归肝经。\n" +
                "\n" +
                "【功效与作用】清热利湿、益气固脱、疏肝活血。属清热药下属分类的清热燥湿药。\n" +
                "\n" +
                "【临床应用】用量15~30克，煎服。用治慢性肝炎、肝脾肿大、肠炎、脱肛、淋浊、疝气、子宫下垂、白带、目赤肿痛。外用适量，浸酒滴耳，用治耳内流脓。临床用治慢性肝炎，对降低转氨酶和缩小肝、脾有一定作用。\n" +
                "\n" +
                "【主要成分】含酚类、氨基酸、鞣质、糖类和甾类成分。\n" +
                "\n" +
                "【使用禁忌】尚不明确。");
        chineseMedicineDao.insert(medicine261);

        ChineseMedicine medicine262=new ChineseMedicine();
        medicine262.setName("白屈菜");
        medicine262.setSortType("B");
        medicine262.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=736a0ae99058d109d0eea1e0b031a7da/b21bb051f81986189844861348ed2e738bd4e622.jpg");
        medicine262.setData("【中药名】白屈菜 baiqucai\n" +
                "\n" +
                "【别名】地黄连、牛金花、断肠草。\n" +
                "\n" +
                "【英文名】Chelidonii Herba。\n" +
                "\n" +
                "【来源】罂栗科植物白屈菜Chelidonium majus L.的带花全草。\n" +
                "\n" +
                "【植物形态】多年生草本。主根圆锥状，土黄色。茎直立，高30~100厘米，多分枝，有白粉，疏生白色细长柔毛，断之有黄色乳汁。叶互生，1~2回单数羽状全裂；全裂片5~8对，不规则深裂，上面近无毛，下面疏生短柔毛，有白粉；茎生叶与基生叶形相同。花数朵，近伞状排列，苞片小，卵形，花柄丝状，有短柔毛；萼片2，早落，椭圆形，外面疏生柔毛；花瓣4，黄色，卵圆形，雄蕊多数，花丝黄色；雌蕊1，无毛，花柱短。蒴果条状圆柱形，长达3.5厘米。种子多数，卵形，细小，黑褐色，有光泽及网纹。花期5~7月，果期6~8月。\n" +
                "\n" +
                "【产地分布】生于山坡或山谷林边草地。分布于我国东北、江苏、山东等地。\n" +
                "\n" +
                "【采收加工】5~7月开花时采收地上部分，置通风处干燥，切段。\n" +
                "\n" +
                "【药材性状】根圆柱形，多有分枝，密生须根。茎干瘪中空，表面黄绿色，有白粉。叶互生，多皱缩，破碎，完整者展平后为1~2回羽状分裂，裂片近对生，先端钝，边缘具不整齐的缺刻，上表面黄绿色，下表面灰绿色，具白色柔毛，脉上尤多。花瓣4，卵圆形，黄色；雄蕊多数，雌蕊1枚。蒴果细圆柱形。种子多数，卵状，细小，表面黑色。气微，味微苦。\n" +
                "\n" +
                "【性味归经】性温，味辛、苦。归肺经、胃经。\n" +
                "\n" +
                "【功效与作用】破瘀止血、解痉止痛，止咳平喘。属化痰止咳平喘药下属分类的止咳平喘药。\n" +
                "\n" +
                "【临床应用】用量9~18克，煎服；外用适量，捣敷患处。用治胃脘挛痛，咳嗽气喘，百日咳、水肿。\n" +
                "\n" +
                "【主要成分】含白屈菜碱、白屈菜红碱、血根碱、氧化白屈菜碱、原阿片碱、&beta；-别隐品碱、DL-四氢黄连碱、小檗碱、白屈菜酸、苹果酸、柠檬酸、琥珀酸。另含皂苷、强心苷及黄酮醇、白屈菜醇等。\n" +
                "\n" +
                "【使用禁忌】尚不明确。");
        chineseMedicineDao.insert(medicine262);

        ChineseMedicine medicine263=new ChineseMedicine();
        medicine263.setName("白苏子");
        medicine263.setSortType("B");
        medicine263.setMedicineImageUrl("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=e19cf14dda160924c828aa49b56e5e9f/1f178a82b9014a90a162d0a6a3773912b31bee49.jpg");
        medicine263.setData("【中药名】白苏子 baisuzi\n" +
                "\n" +
                "【别名】荏子、玉苏子。\n" +
                "\n" +
                "【英文名】Fruit of Common Perilla\n" +
                "\n" +
                "【来源】唇形科植物白苏Perilla frutescens (L.) Britt.的成熟果实。\n" +
                "\n" +
                "【植物形态】一年生直立草本。有香气，高0.5~1.5米。茎绿色，圆角四棱形，多分枝，除基部外，密生细长白毛。叶对生；叶片卵形或圆形，长3～9.5厘米，宽2~8厘米，先端急尖或渐尖，基部圆形或宽楔形，边缘有粗锯齿，两面均绿色而具毛，下面稍苍淡且有腺点；叶柄长10~45毫米，密被白毛。总状花序腋生及顶生；苞片卵形，先端急尖或尾状；萼钟状，具5齿和10条脉纹，分二唇，外面有毛及腺点，内面喉部有长柔毛1圈；花冠白色，管状，二唇形，外面有毛，内面中部有毛1圈，上唇2浅裂，裂片较宽，先端略凹，下唇3裂，两侧的裂片半圆形，中裂片横椭圆形，向下折屈；雄蕊4，2强，稍伸出，花丝无毛，花粉囊2室；子房4裂，花柱无毛，稍伸出，柱头2裂。小坚果褐色或灰白色，倒卵形，长约2毫米，径约1.7毫米。花期8~9月，果期9～10月。\n" +
                "\n" +
                "【产地分布】生于山脚路旁。分布于我国华东、中南及西南等地。\n" +
                "\n" +
                "【采收加工】8~10月果实成熟时割取地上部分，曝晒，脱粒，晒干。\n" +
                "\n" +
                "【药材性状】种子卵圆形或类球形，直径1.5~ 2.5毫米。表面灰色或淡灰色，有明显隆起的网状花纹，基部稍尖，有淡棕色果柄痕。果皮薄而脆，易压碎。种皮膜质，子叶富油质。气微香，味有油腻感。\n" +
                "\n" +
                "【性味归经】性温，味辛。归肺经、大肠经。\n" +
                "\n" +
                "【功效与作用】下气消痰、润肠通便。属泻下药分类下的润下药。\n" +
                "\n" +
                "【临床应用】用量5~10克。用治咳逆、痰喘、肠燥便秘。\n" +
                "\n" +
                "【主要成分】种子含脂肪油，主要是甘油三亚油酸酯和甘油三棕榈酸酯，挥发油主要是左旋紫苏醛、白苏烯酮、松茸醇和左旋芳樟醇。\n" +
                "\n" +
                "【使用禁忌】久虚，脾虚便滑者不宜。");
        chineseMedicineDao.insert(medicine263);

        ChineseMedicine medicine264=new ChineseMedicine();
        medicine264.setName("半枫荷");
        medicine264.setSortType("B");
        medicine264.setMedicineImageUrl("http://img.huamu.com/data/upload/article/201710/13/141/201710131615415015.jpg");
        medicine264.setData("【药名】半枫荷 bà；n fēng hé；\n" +
                "\n" +
                "【别名】翻白叶树根、半边枫树、白背枫根。\n" +
                "\n" +
                "【英文名】Pterospermum heterophyllum Hance.\n" +
                "\n" +
                "【来源】梧桐科植物翻白叶树的根。\n" +
                "\n" +
                "【植物形态】常绿乔木，高可达20米。树皮灰色或灰褐色，小枝被红色或黄色短柔毛。叶异型，革质，幼树或萌蘖枝上的叶为盾形，掌状3~5深裂；成年树的叶为长圆形至卵状长圆形；顶端钝或渐尖，基部钝形、截形或斜心形，下面密被黄褐色茸毛。托叶线状长圆形。花单生或2~4朵组成腋生的聚伞花序；小苞片鳞片状，与萼紧靠；萼片线形，两面均被毛；花瓣5，青白色，倒披针形，与萼等长；雄蕊15枚，退化雄蕊5枚，线状；子房5室，被毛。蒴果木质，长圆状卵形，密被黄褐色茸毛。种子具膜质长翅。\n" +
                "\n" +
                "【产地分布】生于山坡、平原、丘陵地疏林或密林中。分布于广东、海南、福建、广西、台湾等地。\n" +
                "\n" +
                "【采收加工】全年均可采收。挖取根部，抖去泥土，洗净，斩成片块，晒干。\n" +
                "\n" +
                "【药材性状】不规则片块状。栓皮表面灰褐色或红褐色，有纵皱纹及疣状皮孔。质坚硬。断面皮部棕褐色，具细密纹理。纵断面有纵向纹理及不规则的纵裂隙，纤维性。气微，味淡微涩。\n" +
                "\n" +
                "【性味归经】味甘，性温。归脾经、肝经。\n" +
                "\n" +
                "【功效与作用】祛风除湿、活血消肿。属祛风湿药下属中的祛风湿散寒药，主治风湿痹痛，腰肌劳损，手足酸麻无力，跌打损伤。\n" +
                "\n" +
                "【临床应用】用量30~ 60克，煎服或浸酒。用治风寒湿痹、风湿痿软、腰肌劳损、手足麻痹、产后风瘫、关节屈伸不利、跌打损伤肿痛。\n" +
                "\n" +
                "【主要成分】目的从金缕半枫荷根茎抗炎有效的乙酸乙酯萃取部位分离鉴定化合物。方法通过巴豆油鼠耳刺激药理实验确定金缕半枫荷抗炎有效部位，利用硅胶柱层析等手段分离单体化合物，借助质谱和核磁等光谱学测定确定化合物结构。结果从该植物中分离鉴定了9个化合物：齐墩果酸、3-羰基齐墩果酸、2&alpha；，3&beta；-二羟基齐墩果酸、2&alpha；，3&beta；，23-三羟基齐墩果酸、鞣酸-3，3'；二甲醚、鞣酸-3，3'；，4-三甲醚、鞣酸-3，3'；-二甲醚-4-O-&beta；-D-木糖苷、&beta；-谷甾醇和硬脂酸。结论首次从金缕半枫荷中分离得到上述化合物。\n" +
                "\n" +
                "【使用禁忌】暂无。\n"
                );
        chineseMedicineDao.insert(medicine264);

        ChineseMedicine medicine265=new ChineseMedicine();
        medicine265.setName("博落回");
        medicine265.setSortType("B");
        medicine265.setMedicineImageUrl("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=1ba02da6cc1b9d169eca923392b7dfea/79f0f736afc3793167333950e9c4b74543a9116b.jpg");
        medicine265.setData("【药名】博落回 bó； luò； huí；\n" +
                "\n" +
                "【别名】落回、号筒草、叭拉筒、山梧桐。\n" +
                "\n" +
                "【英文名】Macleaya cordata( Wild.)R.Br.\n" +
                "\n" +
                "【来源】罂粟科植物博落回的全草。\n" +
                "\n" +
                "【植物形态】多年生高大草本或呈亚灌木状，高达2.5米，折断有黄色汁液。根粗大，橘红色。茎直立，圆柱形，中空，表面有白粉。叶互生，广卵形，长7～24厘米，宽5～20厘米，7～9浅裂，边缘波状或具波状牙齿，下面有白粉，掌状脉；具叶柄。大型圆锥花序顶生或腋生；花梗长2～5毫米，苞片披针形，有毛；萼片2，白色，倒披针形，开花时脱落；无花瓣；雄蕊20～36.花丝与花药等长，药线形，黄色；雌蕊1，子房倒卵形，花柱短，柱头肥厚。蒴果倒披针形或狭倒卵形，有白粉。种子4～6粒，熟时黑色，有光泽。花期6~7月，果期7～8月。\n" +
                "\n" +
                "【产地分布】分布于长江流域中下游及西南、广东、广西、台湾。产于全国大部分地区。为民间用药。\n" +
                "\n" +
                "【采收加工】夏、秋二季采挖全草，去泥土，晒干。\n" +
                "\n" +
                "【药材性状】全体带有白粉。茎圆柱形，中空。表面光滑，灰绿色或带有暗红紫色。叶互生，具叶柄，完整叶阔卵形，边缘具不规则波状齿，上面灰绿色，下面灰面色，具密细毛，掌状脉，叶柄基部膨大而抱茎。圆锥花序顶生或腋生。具多数花，苞片披针形，萼2片，黄白色，倒披针形，无花瓣，雄蕊多数，花丝细而扁，雄蕊1。蒴果倒披针形或倒卵状形。气微，味苦。\n" +
                "\n" +
                "【性味归经】性温，味辛、苦。有毒。归心经、胃经、肝经\n" +
                "\n" +
                "【功效与作用】消肿解毒，杀虫止痒。属杀虫止痒药。\n" +
                "\n" +
                "【临床应用】本品有毒，不作内服。外用适量，捣烂外敷或煎水熏洗。用于疔毒脓肿、慢性溃疡、烫伤、蜂虫叮咬、顽癣。\n" +
                "\n" +
                "【主要成分】博落回含多种生物碱，如血根碱( Sanguinarine)、白屈菜红碱( Chelerythrine)、博罗回碱(Bocconine)。此外，还分出原阿片碱(protopine)等成分。博罗回的毒性来自于生物碱，临床上已有口服或肌注后中毒或死亡的报道，主要为引起急性心源性脑缺血综合征。动物实验也证明，将博罗回注射液注入兔耳静脉，可引起心电图T波倒置，并可出现多源性多发性室性期前收缩，伴有短暂的阵发性心动过速。阿托品对此有对抗作用。\n" +
                "\n" +
                "【使用禁忌】有大毒，禁内服。口服易引起中毒，轻者出现口渴、头晕、恶心、呕吐、胃烧灼感及四肢麻木、乏力；重者出现烦躁、嗜唾、昏迷、精神异常、心律失常而死亡。");
        chineseMedicineDao.insert(medicine265);

        ChineseMedicine medicine266=new ChineseMedicine();
        medicine266.setName("白鹤灵芝");
        medicine266.setSortType("B");
        medicine266.setMedicineImageUrl("http://a4.att.hudong.com/05/64/300000764046131544642627014_950.jpg");
        medicine266.setData("【药名】白鹤灵芝 bá；i hè； lí；ng zhī\n" +
                "\n" +
                "【别名】癣草、白鹤灵芝草、大叶灵芝草。\n" +
                "\n" +
                "【英文名】Rhinacanthus nasutus(L.)Kurz.\n" +
                "\n" +
                "【来源】爵床科植物白鹤灵芝的枝或叶。\n" +
                "\n" +
                "【植物形态】灌木，高1~2米。幼枝被毛。叶对生，卵形、椭圆形或长圆形，有短柄。花单生或2~3朵排成小聚伞花序，花冠管延长，2唇形，白蒴果长椭圆形，种子4粒或少数。\n" +
                "\n" +
                "【产地分布】野生于丘陵或平原的荒地、路边、河旁等处。分布于广东、云广西、福建等地。\n" +
                "\n" +
                "【采收加工】秋季采收。在离地面8~10厘米处割取枝叶，晒至七八成干时捆扎成把，继续晒至全干即可。\n" +
                "\n" +
                "【药材性状】茎略呈类圆柱形，有6条细棱的纵皱纹。嫩茎灰绿色，老茎色，节稍膨大。老茎质坚硬，难折断，断面呈纤维性，木质部淡绿色，髓色。叶对生，有短柄，叶片椭圆形，全缘，黄绿色。气清香，味淡。\n" +
                "\n" +
                "【性味归经】味甘、淡，性平。归肺经。\n" +
                "\n" +
                "【功效与作用】清肺止咳、利湿止痒。属杀虫止痒药，具抗真菌、病毒作用。\n" +
                "\n" +
                "【临床应用】用量10~15克，煎汤服。外用适量，鲜叶配75%酒精共捣烂，水洗患处。用治肺结核早期。外用治体癣、湿疹。\n" +
                "\n" +
                "【主要成分】茎叶含3，4一二氢一3，3一二甲基一二氢柰并2，3-b]吡喃一5，二酮、白鹤灵芝醌等成分。\n" +
                "\n" +
                "【使用禁忌】暂缺。");
        chineseMedicineDao.insert(medicine266);

        ChineseMedicine medicine267=new ChineseMedicine();
        medicine267.setName("布渣叶");
        medicine267.setSortType("B");
        medicine267.setMedicineImageUrl("http://www.ziyimall.com/file_img/products/63/201603/7/20160307163420eLdOcMQO.jpg");
        medicine267.setData("【药名】布渣叶 bù； zhā yè；\n" +
                "\n" +
                "【别名】崩补叶、泡卜布、山茶叶、蓑衣子、破布叶、麻布叶。\n" +
                "\n" +
                "【英文名】Microcos paniculata Linn.\n" +
                "\n" +
                "【来源】椴树科植物破布叶的叶。\n" +
                "\n" +
                "【植物形态】灌木或小乔木。树皮灰黑色。单叶互生，纸质，卵形或卵状长圆形，顶端渐尖，基部浑圆，幼叶两面均被星状柔毛，后无毛或近无毛，边缘有小锯齿，基出脉3条，网脉很明显，叶柄粗壮长；托叶线状披针形，长约为叶柄之半。圆锥花序顶生或生于上部叶腋内，花序和花梗均密被灰黄色星状柔毛；萼片5枚，长圆形，被星状柔毛；花瓣5片，长圆形，两面均被毛；雄蕊多数，离生；子房近球形，无毛。核果近球形或倒卵状圆球形，黑褐色，无毛。\n" +
                "\n" +
                "【产地分布】生于山谷、丘陵、平地或村边、路旁的灌木丛中。分布于广东、海南、云南、广西等地。\n" +
                "\n" +
                "【采收加工】夏、秋季采收。摘取叶片，阴干或晒干(不宜在烈日下曝晒，否则色黄质次)。\n" +
                "\n" +
                "【药材性状】叶片多皱缩或破碎。完整者展平后呈卵状长圆形或倒卵状矩圆形，黄绿色、绿褐色或黄棕色，有短柄。先端渐尖，基部圆钝，稍偏斜，边缘具细齿。基出脉3条，侧脉羽状，小脉网状。叶脉及叶柄被柔毛。纸质，易破碎。气微，味淡，微酸涩。\n" +
                "\n" +
                "【性味归经】味甘、淡，性微寒。归脾经、胃经。\n" +
                "\n" +
                "【功效与作用】清热消滞、利湿退黄。属清热药下属中的清热泻火药，用治急性黄疸型肝炎，单纯性消化不良。水提物对离体豚鼠心脏有增加冠脉血流量作用，并能提高小鼠耐缺氧能力，对垂体后叶素所致的大鼠急性心肌缺血有保护作用，增加麻醉兔的脑血流量，降低血压与脑血管阻力。\n" +
                "\n" +
                "【临床应用】用量15~30克，煎服。用治感冒、食滞、湿热食滞之脘腹胀痛、食少泄泻、湿热黄疸。\n" +
                "\n" +
                "【主要成分】含黄酮类成分，有异鼠李黄素、山柰黄素、槲皮黄素、5，6，4&rsquo；-三羟基-3&rsquo；-甲氧基黄酮-7-0-鼠李糖基葡萄糖苷、5，6，8，4&rsquo；-四羟基黄酮-7-0-鼠李糖苷等。\n" +
                "\n" +
                "【使用禁忌】孕妇慎服，");
        chineseMedicineDao.insert(medicine267);

        ChineseMedicine medicine268=new ChineseMedicine();
        medicine268.setName("白花银背藤");
        medicine268.setSortType("B");
        medicine268.setMedicineImageUrl("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=2c93e8850ff431ada8df4b6b2a5fc7ca/ac6eddc451da81cba7ff8eda5166d0160924318f.jpg");
        medicine268.setData("【药名】白花银背藤 bá；i huā yí；n bè；i té；ng\n" +
                "\n" +
                "【别名】藤续断、白牛藤、白背藤。\n" +
                "\n" +
                "【英文名】Argyreia seguinii Vant. (Levl.) ex Levl.\n" +
                "\n" +
                "【来源】旋花科植物白花银背藤的藤茎。\n" +
                "\n" +
                "【植物形态】藤本，高达3米。茎圆柱形，被短茸毛。单叶互生，叶片宽卵形，先端锐尖或渐尖，基部圆形或微心形，上面无毛，下面密被灰白色茸毛，全缘，侧脉平行。聚伞花序腋生，总花梗短，密被灰白色茸毛；苞片宿存，卵圆形，外面被茸毛，内面紫色；萼片5，狭长圆形，外面密被灰白色长柔毛；花冠管漏斗状，白色，外面密被白色长柔毛，冠檐5，浅裂；雄蕊及花柱内藏；花柱丝状，柱头头状。果实椭圆形，不开裂。花期5~7月，果期6~9月。\n" +
                "\n" +
                "【生境分布】生于山坡、路边、灌丛中。分布于贵州、广西及云南东南部。\n" +
                "\n" +
                "【采收加工】全年可采，洗净，切段，晒干。\n" +
                "\n" +
                "【药材性状】斜切的段或片，直径0.5～4厘米。表面黄褐色或黄棕色，有浅纵沟及不规则的纵纹或龟裂纹，皮孔点状。质坚韧，难折断。切面有数个同心环纹，髓部灰黄色。气微，味微涩。\n" +
                "\n" +
                "【性味归经】微涩，温。\n" +
                "\n" +
                "【功效与作用】补气血、止咳、驳骨、止血生肌。\n" +
                "\n" +
                "【临床应用】用量20～50克，煎服。用治内伤出血、贫血头昏、咳嗽、骨折。\n" +
                "\n" +
                "【主要成分】水提液对在体和离体子宫有明显收缩作用，并有较强的抗炎及止血作用。\n" +
                "\n" +
                "【使用禁忌】");
        chineseMedicineDao.insert(medicine268);


    }

    public static void addHotSearch()
    {
        HotSearchDao dao=MyApplication.getDaoSession().getHotSearchDao();
        HotSearch search1=new HotSearch("党参");
        HotSearch search2=new HotSearch("白术");
        HotSearch search3=new HotSearch("山药");
        HotSearch search4=new HotSearch("黄精");
        HotSearch search5=new HotSearch("扁豆");
        HotSearch search6=new HotSearch("甘草");
        HotSearch search7=new HotSearch("鹿茸");
        HotSearch search8=new HotSearch("冬虫夏草");
        HotSearch search9=new HotSearch("阿胶");
        HotSearch search10=new HotSearch("熟地");
        HotSearch search11=new HotSearch("当归");
        HotSearch search12=new HotSearch("黄连");
        HotSearch search13=new HotSearch("枸杞");
        HotSearch search14=new HotSearch("金银花");
        HotSearch search15=new HotSearch("连翘");
        HotSearch search16=new HotSearch("土茯苓");

        dao.insert(search1);
        dao.insert(search2);
        dao.insert(search3);
        dao.insert(search4);
        dao.insert(search5);
        dao.insert(search6);
        dao.insert(search7);
        dao.insert(search8);
        dao.insert(search9);
        dao.insert(search10);
        dao.insert(search11);
        dao.insert(search12);
        dao.insert(search13);
        dao.insert(search14);
        dao.insert(search15);
        dao.insert(search16);
    }

    public static  void addSecondCategory()
    {
        ChinesePatentDrugSecondCategoryDao dao=MyApplication.getDaoSession().getChinesePatentDrugSecondCategoryDao();

        ChinesePatentDrugSecondCategory secondCategory=new ChinesePatentDrugSecondCategory();
        secondCategory.setName("辛温解表药");
        secondCategory.setFirstCategoryName("解表药");
        dao.insert(secondCategory);

        ChinesePatentDrugSecondCategory secondCategory2=new ChinesePatentDrugSecondCategory();
        secondCategory2.setName("辛凉解表药");
        secondCategory2.setFirstCategoryName("解表药");
        dao.insert(secondCategory2);

        ChinesePatentDrugSecondCategory secondCategory3=new ChinesePatentDrugSecondCategory();
        secondCategory3.setName("扶正解表药");
        secondCategory3.setFirstCategoryName("解表药");
        dao.insert(secondCategory3);

        ChinesePatentDrugSecondCategory secondCategory4=new ChinesePatentDrugSecondCategory();
        secondCategory4.setName("表里双解药");
        secondCategory4.setFirstCategoryName("解表药");
        dao.insert(secondCategory4);

        ChinesePatentDrugSecondCategory secondCategory5=new ChinesePatentDrugSecondCategory();
        secondCategory5.setName("攻下药");
        secondCategory5.setFirstCategoryName("泻下药");
        dao.insert(secondCategory5);

        ChinesePatentDrugSecondCategory secondCategory6=new ChinesePatentDrugSecondCategory();
        secondCategory6.setName("润下药");
        secondCategory6.setFirstCategoryName("泻下药");
        dao.insert(secondCategory6);

        ChinesePatentDrugSecondCategory secondCategory7=new ChinesePatentDrugSecondCategory();
        secondCategory7.setName("峻下药");
        secondCategory7.setFirstCategoryName("泻下药");
        dao.insert(secondCategory7);

        ChinesePatentDrugSecondCategory secondCategory8=new ChinesePatentDrugSecondCategory();
        secondCategory8.setName("清热解毒药");
        secondCategory8.setFirstCategoryName("清热药");
        dao.insert(secondCategory8);

        ChinesePatentDrugSecondCategory secondCategory9=new ChinesePatentDrugSecondCategory();
        secondCategory9.setName("清热泻火药");
        secondCategory9.setFirstCategoryName("清热药");
        dao.insert(secondCategory9);

        ChinesePatentDrugSecondCategory secondCategory10=new ChinesePatentDrugSecondCategory();
        secondCategory10.setName("清热解暑药");
        secondCategory10.setFirstCategoryName("清热药");
        dao.insert(secondCategory10);

        ChinesePatentDrugSecondCategory secondCategory11=new ChinesePatentDrugSecondCategory();
        secondCategory11.setName("清虚热药");
        secondCategory11.setFirstCategoryName("清热药");
        dao.insert(secondCategory11);

        ChinesePatentDrugSecondCategory secondCategory12=new ChinesePatentDrugSecondCategory();
        secondCategory12.setName("温中祛寒药");
        secondCategory12.setFirstCategoryName("温里药");
        dao.insert(secondCategory12);

        ChinesePatentDrugSecondCategory secondCategory13=new ChinesePatentDrugSecondCategory();
        secondCategory13.setName("温经散寒药");
        secondCategory13.setFirstCategoryName("温里药");
        dao.insert(secondCategory13);

        ChinesePatentDrugSecondCategory secondCategory14=new ChinesePatentDrugSecondCategory();
        secondCategory14.setName("回阳救逆药");
        secondCategory14.setFirstCategoryName("温里药");
        dao.insert(secondCategory14);

        ChinesePatentDrugSecondCategory secondCategory15=new ChinesePatentDrugSecondCategory();
        secondCategory15.setName("活血化瘀药");
        secondCategory15.setFirstCategoryName("理血药");
        dao.insert(secondCategory15);

        ChinesePatentDrugSecondCategory secondCategory16=new ChinesePatentDrugSecondCategory();
        secondCategory16.setName("止血药");
        secondCategory16.setFirstCategoryName("理血药");
        dao.insert(secondCategory16);

        ChinesePatentDrugSecondCategory secondCategory17=new ChinesePatentDrugSecondCategory();
        secondCategory17.setName("补气药");
        secondCategory17.setFirstCategoryName("补益药");
        dao.insert(secondCategory17);

        ChinesePatentDrugSecondCategory secondCategory18=new ChinesePatentDrugSecondCategory();
        secondCategory18.setName("补血药");
        secondCategory18.setFirstCategoryName("补益药");
        dao.insert(secondCategory18);

        ChinesePatentDrugSecondCategory secondCategory19=new ChinesePatentDrugSecondCategory();
        secondCategory19.setName("气血双补药");
        secondCategory19.setFirstCategoryName("补益药");
        dao.insert(secondCategory19);

        ChinesePatentDrugSecondCategory secondCategory20=new ChinesePatentDrugSecondCategory();
        secondCategory20.setName("补阳药");
        secondCategory20.setFirstCategoryName("补益药");
        dao.insert(secondCategory20);

        ChinesePatentDrugSecondCategory secondCategory21=new ChinesePatentDrugSecondCategory();
        secondCategory21.setName("补阴药");
        secondCategory21.setFirstCategoryName("补益药");
        dao.insert(secondCategory21);

        ChinesePatentDrugSecondCategory secondCategory22=new ChinesePatentDrugSecondCategory();
        secondCategory22.setName("固表止汗药");
        secondCategory22.setFirstCategoryName("固涩药");
        dao.insert(secondCategory22);

        ChinesePatentDrugSecondCategory secondCategory23=new ChinesePatentDrugSecondCategory();
        secondCategory23.setName("制酸止痛药");
        secondCategory23.setFirstCategoryName("固涩药");
        dao.insert(secondCategory23);

        ChinesePatentDrugSecondCategory secondCategory24=new ChinesePatentDrugSecondCategory();
        secondCategory24.setName("涩肠止泻药");
        secondCategory24.setFirstCategoryName("固涩药");
        dao.insert(secondCategory24);

        ChinesePatentDrugSecondCategory secondCategory25=new ChinesePatentDrugSecondCategory();
        secondCategory25.setName("涩精止遗药");
        secondCategory25.setFirstCategoryName("固涩药");
        dao.insert(secondCategory25);

        ChinesePatentDrugSecondCategory secondCategory26=new ChinesePatentDrugSecondCategory();
        secondCategory26.setName("固崩止带药");
        secondCategory26.setFirstCategoryName("固涩药");
        dao.insert(secondCategory26);

        ChinesePatentDrugSecondCategory secondCategory27=new ChinesePatentDrugSecondCategory();
        secondCategory27.setName("凉开药");
        secondCategory27.setFirstCategoryName("开窍药");
        dao.insert(secondCategory27);

        ChinesePatentDrugSecondCategory secondCategory28=new ChinesePatentDrugSecondCategory();
        secondCategory28.setName("温开药");
        secondCategory28.setFirstCategoryName("开窍药");
        dao.insert(secondCategory28);

        ChinesePatentDrugSecondCategory secondCategory29=new ChinesePatentDrugSecondCategory();
        secondCategory29.setName("燥湿和胃药");
        secondCategory29.setFirstCategoryName("祛湿药");
        dao.insert(secondCategory29);

        ChinesePatentDrugSecondCategory secondCategory30=new ChinesePatentDrugSecondCategory();
        secondCategory30.setName("清热祛湿药");
        secondCategory30.setFirstCategoryName("祛湿药");
        dao.insert(secondCategory30);

        ChinesePatentDrugSecondCategory secondCategory31=new ChinesePatentDrugSecondCategory();
        secondCategory31.setName("渗湿利水药");
        secondCategory31.setFirstCategoryName("祛湿药");
        dao.insert(secondCategory31);

        ChinesePatentDrugSecondCategory secondCategory32=new ChinesePatentDrugSecondCategory();
        secondCategory32.setName("温化寒湿药");
        secondCategory32.setFirstCategoryName("祛湿药");
        dao.insert(secondCategory32);

        ChinesePatentDrugSecondCategory secondCategory33=new ChinesePatentDrugSecondCategory();
        secondCategory33.setName("祛风除湿药");
        secondCategory33.setFirstCategoryName("祛湿药");
        dao.insert(secondCategory33);

        ChinesePatentDrugSecondCategory secondCategory34=new ChinesePatentDrugSecondCategory();
        secondCategory34.setName("燥湿化痰药");
        secondCategory34.setFirstCategoryName("化痰止咳平喘药");
        dao.insert(secondCategory34);

        ChinesePatentDrugSecondCategory secondCategory35=new ChinesePatentDrugSecondCategory();
        secondCategory35.setName("清热化痰药");
        secondCategory35.setFirstCategoryName("化痰止咳平喘药");
        dao.insert(secondCategory35);

        ChinesePatentDrugSecondCategory secondCategory36=new ChinesePatentDrugSecondCategory();
        secondCategory36.setName("润肺化痰药");
        secondCategory36.setFirstCategoryName("化痰止咳平喘药");
        dao.insert(secondCategory36);

        ChinesePatentDrugSecondCategory secondCategory37=new ChinesePatentDrugSecondCategory();
        secondCategory37.setName("温肺化痰药");
        secondCategory37.setFirstCategoryName("化痰止咳平喘药");
        dao.insert(secondCategory37);

        ChinesePatentDrugSecondCategory secondCategory38=new ChinesePatentDrugSecondCategory();
        secondCategory38.setName("治风化痰药");
        secondCategory38.setFirstCategoryName("化痰止咳平喘药");
        dao.insert(secondCategory38);

        ChinesePatentDrugSecondCategory secondCategory39=new ChinesePatentDrugSecondCategory();
        secondCategory39.setName("止咳平喘药");
        secondCategory39.setFirstCategoryName("化痰止咳平喘药");
        dao.insert(secondCategory39);

        ChinesePatentDrugSecondCategory secondCategory40=new ChinesePatentDrugSecondCategory();
        secondCategory40.setName("疏散外风药");
        secondCategory40.setFirstCategoryName("治风解痉药");
        dao.insert(secondCategory40);

        ChinesePatentDrugSecondCategory secondCategory41=new ChinesePatentDrugSecondCategory();
        secondCategory41.setName("平息内风药");
        secondCategory41.setFirstCategoryName("治风解痉药");
        dao.insert(secondCategory41);

        ChinesePatentDrugSecondCategory secondCategory42=new ChinesePatentDrugSecondCategory();
        secondCategory42.setName("消导药");
        secondCategory42.setFirstCategoryName("消导药");
        dao.insert(secondCategory42);

        ChinesePatentDrugSecondCategory secondCategory43=new ChinesePatentDrugSecondCategory();
        secondCategory43.setName("理气药");
        secondCategory43.setFirstCategoryName("理气药");
        dao.insert(secondCategory43);

        ChinesePatentDrugSecondCategory secondCategory44=new ChinesePatentDrugSecondCategory();
        secondCategory44.setName("安神药");
        secondCategory44.setFirstCategoryName("安神药");
        dao.insert(secondCategory44);

        ChinesePatentDrugSecondCategory secondCategory45=new ChinesePatentDrugSecondCategory();
        secondCategory45.setName("外用药");
        secondCategory45.setFirstCategoryName("外用药");
        dao.insert(secondCategory45);

    }

    public static void addChinesePatentDrug()
    {
        ChinesePatentDrugDao dao=MyApplication.getDaoSession().getChinesePatentDrugDao();
        ChinesePatentDrug drug=new ChinesePatentDrug();
        drug.setName("通宣理肺丸");
        drug.setSecondCategoryName("辛温解表药");
        drug.setImageUrl("http://www.qgyyzs.net/upload/2011-7/qgyyzs_201172914839603.jpg");
        drug.setData("【药名】通宣理肺丸 Tongxuan Lifei Wan\n" +
                "\n" +
                "【处方】紫苏叶144克、前胡96克、桔梗96克、苦杏仁72克、麻黄96克、甘草72克、陈皮96克、 半夏(制)72克、茯苓96克、枳壳(炒)96克、黄芩96克.\n" +
                "\n" +
                "【组方分析】方中麻黄、紫苏叶发散风寒，宣肺止咳，为君药。桔梗、前胡、苦杏仁宣降肺气，化痰止咳，为臣药。佐以黄芩清泄肺热;枳壳理气宽胸;半夏、茯苓、陈皮燥湿化痰。使以甘草调和诸药。诸药相配，共奏解表散寒，宣肺止咳之功。\n" +
                "\n" +
                "【功能主治】解表散寒，宣肺止咳。用于风寒束表，肺气不宣所致的感冒咳嗽，症见发热、恶寒、咳嗽、鼻塞流涕、头痛、无汗、肢体酸痛。\n" +
                "\n" +
                "【制备方法】以上十一味，粉碎成细粉，过筛，混匀。每100克、粉末用炼蜜35~45克、加适量的水泛丸，干燥，制成水蜜丸;或加炼蜜130~160克制成大蜜丸，即得。\n" +
                "\n" +
                "【剂型规格】丸剂。水蜜丸每100丸重10克;大蜜丸每丸重6克。\n" +
                "\n" +
                "【用法用量】口服。水蜜丸一次7克、，大蜜丸一次2丸，一日2~3次。\n" +
                "\n" +
                "【质量控制】\n" +
                "\n" +
                "1.为黑棕色至黑褐色的水蜜丸或大蜜丸，味微甜、略苦。\n" +
                "\n" +
                "2.显微鉴别桔梗，苦杏仁、麻黄、甘草、紫苏叶、陈皮、半夏、茯苓、黄芩、枳壳;薄层色谱法鉴别麻黄(盐酸麻黄碱)、紫苏叶、黄芩(黄芩苷)。\n" +
                "\n" +
                "3.除应符合丸剂有关的各项规定外，按照高效液相色谱法测定，本品含麻黄以盐酸麻黄碱(C10H15O.HCl)计，水蜜丸1克不得少于0.30毫克，大蜜丸每丸不得少于1.2毫克。\n" +
                "\n" +
                "【现代研究】主要有发汗、镇咳、化痰、镇痛、平喘、抑菌等作用。实验研究证明本品对感冒病毒有抑制作用，对感冒的预防有积极的意义。对枸橼酸所致的豚鼠咳嗽具有显著的镇咳作用，对氨气所致的小鼠咳嗽也有显著的镇咳作用。本品对大鼠蛋清性足肿有显著的抑制作用，对小鼠毛细血管通透性增高有显著的抑制作用。对大肠杆菌内毒素致大鼠发热有降温作用。能增加呼吸道分泌，具有一定的祛痰作用。能对抗组胺所致的支气管痉挛，增加肺灌流量，还能调节整体功能。\n" +
                "\n" +
                "【临床应用】 临床用于感冒、气管炎、支气管炎、急性鼻炎、荨麻疹等属外感风寒，肺气不宣者。\n" +
                "\n" +
                "【其他制剂】\n" +
                "\n" +
                "1.通宣理肺膏(《部颁标准》中药成方制剂第17册) 口服。一次15克，一日2次。\n" +
                "\n" +
                "2.通宣理肺口服液(《新药转正标准》第11册) 口服。一次20毫升，一日2~3次。\n" +
                "\n" +
                "【方歌】通宣理肺用麻苏，桔梗苦杏合前胡，芩枳夏苓陈甘草，宣肺止咳且解表。\n" +
                "\n" +
                "【贮藏】密封，置于阴凉处。");
        dao.insert(drug);


    }

    public static void addAcuPoint()
    {
        AcuPointDao dao= MyApplication.getDaoSession().getAcuPointDao();
        AcuPoint point=new AcuPoint();
        point.setName("膈关");
        point.setSortType("足太阳膀胱经穴");
        point.setImageUrl("http://m.ayskjaj.com/UploadImage/Product/20170626142759884.jpg");
        point.setData("【穴位名】膈关 Geguan 属足太阳膀胱经穴\n" +
                "\n" +
                "【定位与取法】在背部，当第7胸椎棘突下，旁开3寸。俯卧位，取穴用骨度分寸法与体表标志法。\n" +
                "\n" +
                "【局部解剖】皮肤&rarr；皮下组织&rarr；斜方肌&rarr；菱形肌&rarr；竖脊肌。浅层布有第7、8胸神经后支的皮支和伴行的动、静脉。深层有肩胛背神经，肩胛背动、静脉，第7、8胸神经后支的肌支和相应的肋间后动、静脉背侧支的分支或属支。\n" +
                "\n" +
                "【功能主治】①调理胃气：腹胀，腹痛，肠鸣，呃逆，呕吐，饮食不下。②通经活络：脊背强痛，胁肋疼痛。现用于肋间神经痛，膈肌痉挛，胃出血等。\n" +
                "\n" +
                "【操作方法】斜刺0.5~0.8寸，不可深刺，避免伤及内脏。可酌情使用灸法或拔罐法。\n" +
                "\n" +
                "【配伍举例】配足三里、公孙，有健脾消积，和胃理气的作用，主治食欲不振，胃痛，肠炎。");
        dao.insert(point);

        AcuPoint point2=new AcuPoint();
        point2.setName("膈俞");
        point2.setSortType("足太阳膀胱经穴");
        point2.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550649509555&di=0f7ff5cb4101767cc00196fbfaa1c1b6&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn%2Fw1280h720%2F20171214%2Fd58d-fypsqiz8013458.jpg");
        point2.setData("【穴位名】膈俞 Geshu 足太阳膀胱经穴\n" +
                "\n" +
                "【定位与取法】在背部，当第7胸椎棘突下，旁开1.5寸。俯卧位，取穴用骨度分寸法与体表标志法。\n" +
                "\n" +
                "【局部解剖】皮肤&rarr；皮下组织&rarr；斜方肌&rarr；背阔肌&rarr；竖脊肌。浅层布有第7、8胸神经后支的内侧皮支和伴行的肋间后动、静脉支的内侧皮支。深层有第7、8胸神经后支的肌支和相应的肋间后动、静脉背侧支的分支或属支。\n" +
                "\n" +
                "【功能主治】①调理胃气：腹胀，腹痛，肠鸣，呃逆，呕吐，饮食不下。②调理血气：出血证，血虚证，血瘀证。③止咳平喘：咳嗽，气喘。④通经活络：脊背强痛，胁肋疼痛。⑤退虚热：潮热，盗汗。现用于贫血，慢性出血性疾病，膈肌痉挛，胃炎，肠炎等。\n" +
                "\n" +
                "【操作方法】斜刺0.5~0.8寸，不可深刺，避免伤及内脏。可酌情使用灸法或拔罐法。\n" +
                "\n" +
                "【配伍举例】①配肝俞、脾俞，有健脾统血，和营补血的作用，主治贫血，白细胞及血小板减少。②配曲池、三阴交，有祛风清热，活血止痒的作用，主治荨麻疹，皮肤瘙痒。");
        dao.insert(point2);

        AcuPoint point3=new AcuPoint();
        point3.setName("关元俞");
        point3.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550649870027&di=386d530e872eb1295da3d29c18bef4f6&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fq_70%2Cc_zoom%2Cw_640%2Fupload%2F20161011%2F875eac4420104c7788bccd4bb30c1a03_th.jpg");
        point3.setSortType("足太阳膀胱经穴");
        point3.setData("【穴位名】关元俞 Guanyuanshu 属足太阳膀胱经穴\n" +
                "\n" +
                "【定位与取法】在腰部，当第5腰椎棘突下，旁开1.5寸。俯卧位，取穴用骨度分寸法与体表标志法。\n" +
                "\n" +
                "【局部解剖】皮肤&rarr；皮下组织&rarr；胸腰筋膜浅层&rarr；竖脊肌。浅层布有第5腰神经和第1骶神经后支的皮支和伴行的动、静脉支的皮支。深层有第5腰神经后支的肌支。\n" +
                "\n" +
                "【功能主治】①调理二便：腹胀，腹泻，痢疾，便秘，痔疮；小便不利，遗尿，尿频，尿痛。②调理肾气：遗精，阳痿，白浊，白带，月经不调，痛经。③通经活络：腰骶疼痛，下肢疼痛。现用于慢性肠炎，慢性盆腔炎，膀胱炎等。\n" +
                "\n" +
                "【操作方法】直刺0.8寸。可酌情使用灸法或拔罐法。\n" +
                "\n" +
                "【配伍举例】配中极、水道，有清热除湿，调理下焦的作用，主治排尿困难。");
        dao.insert(point3);
    }

    public static void addPrescription()
    {
        PrescriptionDao dao=MyApplication.getDaoSession().getPrescriptionDao();
        Prescription prescription=new Prescription();
        prescription.setName("麻黄汤");
        prescription.setSortType("解表剂");
        prescription.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546269933&di=9f730e86924591fb51b6d423d74d23ae&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.km1818.com%2Flife%2Fpages%2Fchannel_500%2F1160420%2F500.1461114516000.6461%2Fimage%2F2014011660193697.jpg");
        prescription.setData("【方剂名】麻黄汤，出自《伤寒论》\n" +
                "\n" +
                "【组成】 麻黄三两去节(9g)，桂枝二两去皮(6g)，杏仁七十个去皮尖(6g)，甘草一两炙(3g)。\n" +
                "\n" +
                "【用法】上四味，以水九升，先煮麻黄，减二升，去上沫，内诸药，煮取二升半，去滓，温服八合。覆取微似汗，不须啜粥，余如桂枝法将息。 现代用法：水煎服，温覆取微汗。\n" +
                "\n" +
                "【功效】发汗解表，宣肺平喘。\n" +
                "\n" +
                "【主治】外感风寒表实证。恶寒发热，头身疼痛，无汗而喘，苔薄白，脉浮紧。\n" +
                "\n" +
                "【方解】本方证为风寒束表，肺气失宣所致。风寒束表，邪正相争，则恶寒发热；皮毛闭塞，经气不利，头身疼痛，无汗；肺气失宣，上逆为喘咳；苔薄白，脉浮紧均为外感风寒表实证的征象。治宜发汗宣肺，既解在表之寒邪，又开郁闭之肺气。方中麻黄性温辛 散人肺经，既开泄腠理散寒邪，又宣畅肺气平喘咳，为君药。桂枝通营达卫，既助麻黄发 汗解表，又畅行营阴止疼痛，使表邪祛营卫和，为臣药。麻桂相配，一宣卫气之郁闭开腠理，一通营阴之滞以和营卫，二药相须为用，透营畅卫，为辛温解表的常用组合。杏仁苦 降人肺，肃降肺气以平喘咳，与麻黄配伍，一宣一降，以复肺之宣肃功能，增平喘止咳之 功，为佐药。炙甘草调和诸药，既益气扶正，又能缓和麻、桂之峻烈之性，使汗出而不伤 正，为使药而兼佐药之用。四药合用，发汗解表以散寒，宣降肺气以平喘。\n" +
                "\n" +
                "【临床运用】\n" +
                "\n" +
                "1.用方要点：本方为治外感风寒表实证的基础方，以恶寒发热，无汗而喘，脉浮紧 为辨证要点。\n" +
                "\n" +
                "2.现代应用：本方常用于治疗感冒、流行性感冒以及急性支气管炎、支气管哮喘等属风寒表实证者。\n" +
                "\n" +
                "【使用注意】本方为发汗峻剂，药后一经汗出，则不宜再服。对于外感表虚自汗，外感风热，体虚外感等，均忌用。\n" +
                "\n" +
                "【附】大青龙汤(《伤寒论》)：麻黄六两，去节(12g)，桂枝二两(6g)，甘草二两炙(6g)，杏仁四十枚去皮尖(6g)，生姜三两切(9g)，大枣十枚擘(3g)，石膏如鸡子大碎 (18g)。水煎服。功效：发汗解表，清热除烦。主治：外寒内热，寒热俱重，身体疼痛， 不汗出而烦躁，脉浮紧。\n" +
                "\n" +
                "【方歌】麻黄汤中用桂枝，杏仁甘草四般施， 发热恶寒头项痛，无汗而喘服之宜。");

        dao.insert(prescription);


    }

    public static void addMedicalBook()
    {
        MedicalBookDao dao=MyApplication.getDaoSession().getMedicalBookDao();

        MedicalBook book=new MedicalBook();
        book.setName("上古天真论");
        book.setSortType("素问");
        book.setBookName("黄帝内经");
        book.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551016138&di=8ec61e38067dc966e2d695f4c0e3c537&imgtype=jpg&er=1&src=http%3A%2F%2Fwxpic.7399.com%2FnqvaoZtoY6GlxJuvYKfWmZlkxaJhpc-bna9iqJqJea%2F2fmZ1pkoKpfpyxgp6UsNNjq4vQbKB7ymuFi35ment9qmmx35usbZiur9J2p5OwmKBsrrGMoKHEfqKuh2F_bpmnq62YndGjpp2byoOBgJfQe5-ZuWejeZFvaGVy.jpg");
        book.setData("昔在黄帝，生而神灵，弱而能言，幼而徇齐，长而敦敏，成而登天。乃问于天师曰：余闻上古之人，春秋皆度百岁，而动作不衰；今时之人，年半百而动作皆衰者，时世异耶，人将失之耶。\n" +
                "\n" +
                "　　岐伯对曰：上古之人，其知道者，法于阴阳，和于术数，食饮有节，起居有常，不妄作劳，故能形与神俱，而尽终其天年，度百岁乃去。今时之人不然也，以酒为浆，以妄为常，醉以入房，以欲竭其精，以耗散其真，不知持满，不时御神，务快其心，逆于生乐，起居无节，故半百而衰也。\n" +
                "\n" +
                "　　夫上古圣人之教下也，皆谓之虚邪贼风，避之有时，恬淡虚无，真气从之，精神内守，病安从来。是以志闲而少欲，心安而不惧，形劳而不倦，气从以顺，各从其欲，皆得所愿。故美其食，任其服，乐其俗，高下不相慕，其民故曰朴。是以嗜欲不能劳其目，淫邪不能惑其心，愚智贤不肖不惧于物，故合于道。所以能年皆度百岁，而动作不衰者，以其德全不危也。\n" +
                "\n" +
                "　　帝曰：人年老而无子者，材力尽耶，将天数然也。\n" +
                "\n" +
                "　　岐伯曰：女子七岁。肾气盛，齿更发长； 二七而天癸至，任脉通，太冲脉盛，月事以时下，故有子； 三七，肾气平均，故真牙生而长极； 四七，筋骨坚，发长极，身体盛壮； 五七，阳明脉衰，面始焦，发始堕； 六七，三阳脉衰于上，面皆焦，发始白； 七七，任脉虚，太冲脉衰少，天癸竭，地道不通，故形坏而无子也。 丈夫八岁，肾气实，发长齿更； 二八，肾气盛，天癸至，精气溢泻，阴阳和，故能有子； 三八，肾气平均，筋骨劲强，故真牙生而长极； 四八，筋骨隆盛，肌肉满壮； 五八，肾气衰，发堕齿槁； 六八，阳气衰竭于上，面焦，发鬓颁白； 七八，肝气衰，筋不能动，天癸竭，精少，肾藏衰，形体皆极； 八八，则齿发去。肾者主水，受五藏六府之精而藏之，故五藏盛，乃能泻。 今五藏皆衰，筋骨解堕，天癸尽矣。故发鬓白，身体重，行步不正，而无子耳。\n" +
                "\n" +
                "　　帝曰：有其年已老而有子者何也。\n" +
                "\n" +
                "　　岐伯曰：此其天寿过度，气脉常通，而肾气有余也。此虽有子，男不过尽八八，女不过尽七七，而天地之精气皆竭矣。\n" +
                "\n" +
                "　　帝曰：夫道者年皆百数，能有子乎。\n" +
                "\n" +
                "　　岐伯曰：夫道者能却老而全形，身年虽寿，能生子也。\n" +
                "\n" +
                "　　黄帝曰：余闻上古有真人者，提挈天地，把握阴阳，呼吸精气，独立守神，肌肉若一，故能寿敝天地，无有终时，此其道生。中古之时，有至人者，淳德全道，和于阴阳，调于四时，去世离俗，积精全神，游行天地之间，视听八达之外，此盖益其寿命而强者也，亦归于真人。其次有圣人者，处天地之和，从八风之理，适嗜欲于世俗之间。无恚嗔之心，行不欲离于世，被服章，举不欲观于俗，外不劳形于事，内无思想之患，以恬愉为务，以自得为功，形体不敝，精神不散，亦可以百数。其次有贤人者，法则天地，象似日月，辨列星辰，逆从阴阳，分别四时，将从上古合同于道，亦可使益寿而有极时。");

        dao.insert(book);

        MedicalBook book2=new MedicalBook();
        book2.setName("九针十二原");
        book2.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551016138&di=8ec61e38067dc966e2d695f4c0e3c537&imgtype=jpg&er=1&src=http%3A%2F%2Fwxpic.7399.com%2FnqvaoZtoY6GlxJuvYKfWmZlkxaJhpc-bna9iqJqJea%2F2fmZ1pkoKpfpyxgp6UsNNjq4vQbKB7ymuFi35ment9qmmx35usbZiur9J2p5OwmKBsrrGMoKHEfqKuh2F_bpmnq62YndGjpp2byoOBgJfQe5-ZuWejeZFvaGVy.jpg");
        book2.setSortType("灵枢");
        book2.setBookName("黄帝内经");
        book2.setData("黄帝问于岐伯曰：余子万民，养百姓而收其租税；余哀其不给而属有疾病。余欲勿使被毒药，无用砭石，欲以微针通其经脉，调其血气，荣其逆顺出入之会。令可传于后世，必明为之法，令终而不灭，久而不绝，易用难忘，为之经纪，异其章，别其表里，为之终始。令各有形，先立针经。愿闻其情。\n" +
                "\n" +
                "　　歧伯答曰：臣请推而次之，令有纲纪，始于一，终于九焉。请言其道！小针之要，易陈而难入。粗守形，上守神。神乎神，客在门。未睹其疾，恶知其原？刺之微，在速迟。粗守关，上守机，机之动，不离其空。空中之机，清静而微。其来不可逢，其往不可追。知机之道者，不可挂以发。不知机道，扣之不发。知其往来，要与之期。粗之闇乎，妙哉，工独有之。往者为逆，来者为顺，明知逆顺，正行无间。迎而夺之，恶得无虚？追而济之，恶得无实？迎之随之，以意和之，针道毕矣。\n" +
                "\n" +
                "　　凡用针者，虚则实之，满则泄之，宛陈则除之，邪胜则虚之。大要曰：徐而疾则实，疾而徐则虚。言实与虚，若有若无。察后与先。若存若亡。为虚与实，若得若失。\n" +
                "\n" +
                "　　虚实之要，九针最妙，补泻之时，以针为之。泻曰，必持内之，放而出之，排阳得针，邪气得泄。按而引针，是谓内温，血不得散，气不得出也。补曰，随之随之，意若妄之。若行若按，如蚊虻止，如留如还，去如弦绝，令左属右，其气故止，外门已闭，中气乃实，必无留血，急取诛之。\n" +
                "\n" +
                "　　持针之道，坚者为宝。正指直刺，无针左右。神在秋亳，属意病者。审视血脉者，刺之无殆。方刺之时，必在悬阳，及与两卫。神属勿去，知病存亡。血脉者在俞横居，视之独澄，切之独坚。\n" +
                "\n" +
                "　　九针之名，各不同形。一曰镵针，长一寸六分；二曰员针，长一寸六分；三曰鍉针，长三寸半；四曰锋针，长一寸六分；五曰铍针，长四寸，广二分半；六曰员利针，长一寸六分；七曰亳（bó）针，长三寸六分；八曰长针，长七寸；九曰大针，长四寸。镵针者，头大末锐，去泻阳气；员针者，针如卵形，揩摩分间，不得伤肌肉者，以泻分气；鍉针者，锋如黍粟之锐，主按脉，勿陷以致其气；锋针者，刃三隅以发痼疾，铍针者，末如剑锋，以取大脓；员利针者，大如厘，且员且锐，中身微大，以取暴气；亳针者，尖如蚊虻喙，静以徐往，微以久留之而养，以取痛痹；长针者，锋利身薄，可以取远痹；大针者，尖如梃，其锋微员，以泻机关之水也。九针毕矣。夫气之在脉也，邪气在上，浊气在中，清气在下。故针陷脉则邪气出，针中脉则浊气出，针太深则邪气反沉、病益。故曰：皮肉筋脉，各有所处。病各有所宜。各不同形，各以任其所宜，无实无虚。损不足而益有余，是谓甚病。病益甚，取五脉者死，取三脉者恇；夺阴者死，夺阳者狂，针害毕矣。\n" +
                "\n" +
                "　　刺之而气不至，无问其数。刺之而气至，乃去之，勿复针。针各有所宜，各不同形，各任其所。为刺之要，气至而有效，效之信，若风之吹云，明乎若见苍天，刺之道毕矣。\n" +
                "\n" +
                "　　黄帝曰：愿闻五脏六腑所出之处。\n" +
                "\n" +
                "　　歧伯曰：五脏五俞，五五二十五俞，六腑六俞，六六三十六俞，经脉十二，络脉十五，凡二十七气，以上下。所出为井，所溜为荥，所注为俞，所行为经，所入为合，二十七气所行，皆在五俞也。\n" +
                "\n" +
                "　　节之交，三百六十五会，知其要者，一言而终，不知其要，流散无穷。所言节者，神气之所游行出入也。非皮肉筋骨也。\n" +
                "\n" +
                "　　观其色，察其目，知其散复。一其形，听其动静，知其邪正，右主推之，左持而御之，气至而去之。\n" +
                "\n" +
                "　　凡将用针，必先诊脉，视气之剧易，乃可以治也。五脏之气，已绝于内，而用针者反实其外，是谓重竭。重竭必死，其死也静。治之者辄反其气，取腋与膺。五脏之气，已绝于外，而用针者反实其内，是谓逆厥。逆厥则必死，其死也躁。治之者反取四末。\n" +
                "\n" +
                "　　刺之害中而不去，则精泄；害中而去，则致气。精泄则病益甚而恇，致气则生为痈疡。\n" +
                "\n" +
                "　　五脏有六腑，六腑有十二原，十二原出于四关，四关主治五脏。五脏有疾，当取之十二原。十二原者，五脏之所以禀三百六十五节气味也。五脏有疾也，应出十二原。十二原各有所出。明知其原，睹其应，而知五脏之害矣。阳中之少阴，肺也，其原出于太渊，太渊二。阳中之太阳，心也，其原出于大陵，大陵二。阴中之少阳，肝也，其原出于太冲，太冲二。阴中之至阴，脾也，其原出于太白，太白二。阴中之太阴，肾也，其原出于太溪，太溪二。膏之原，出于鸠尾，鸠尾一。肓之原，出于脖胦，脖胦一。凡此十二原者，主治五脏六腑之有疾者也。\n" +
                "\n" +
                "　　胀取三阳，飧泄取三阴。\n" +
                "\n" +
                "　　禀，今夫五脏之有疾也，譬犹刺也，犹污也，犹结也，犹闭也。刺虽久犹可拔也，污虽久犹可雪也，结虽久犹可解也，闭虽久犹可决也。或言久疾之不可取者，非其说也。夫善用针者，取其疾也，犹拔刺也，犹雪污也，犹解结也，犹决闭也。疾虽久，犹可毕也。言不可治者，未得其术也。\n" +
                "\n" +
                "　　刺诸热者，如以手探汤；刺寒清者，如人不欲行。阴有阳疾者，取之下陵三里，正往无殆，气下乃止，不下复始也。疾高而内者，取之阴之陵泉；疾高而外者，取之阳之陵泉也。");
        dao.insert(book2);



        MedicalBook book3=new MedicalBook();

        book3.setName("露水");
        book3.setSortType("水部");
        book3.setBookName("本草纲目");
        book3.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550475515283&di=e59f98faddc8a6347887959d4c1655ba&imgtype=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fq_mini%2Cc_zoom%2Cw_640%2Fupload%2F20170801%2Fbd0970eb206a46b095bc39d22b7b6e41_th.jpg");
        book3.setData("释名\n" +
                "在秋露重的时候，早晨去花草间收取。\n" +
                "\n" +
                "气味\n" +
                "甘、平、无毒。\n" +
                "\n" +
                "主治\n" +
                "用以煎煮润肺杀虫的药剂，或把治疗疥癣、虫癞的散剂调成外敷药，可以增强疗效。\n" +
                "白花露：止消渴。\n" +
                "百花露：能令皮肤健好。\n" +
                "柏叶露、菖蒲露：每天早晨洗眼睛，能增强视力。\n" +
                "韭叶露：治白癜风。每天早晨涂患处。");
        dao.insert(book3);

        MedicalBook book4=new MedicalBook();
        book4.setImageUrl("http://images.bookuu.com/book/C/01682/2701154-fm.jpg");
        book4.setName("四君子汤");
        book4.setBookName("汤头歌诀");
        book4.setSortType("补益之剂");
        book4.setData("四君子汤中和义　参术茯苓甘草比\n" +
                "益以夏陈名六君　祛痰补气阳虚饵\n" +
                "除祛半夏名异功　或加香砂胃寒使");

        dao.insert(book4);

        MedicalBook book5=new MedicalBook();
        book5.setName("伤寒论序");
        book5.setSortType("序");
        book5.setBookName("伤寒论");
        book5.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550479545531&di=9fc2c01c3a6f12467df698a8a82be49a&imgtype=0&src=http%3A%2F%2Fmmbiz.qpic.cn%2Fmmbiz_jpg%2FsTuMs1ibczsqAFlSvhC9dJ2sia7MA5hkzezslianrNABwlTAtO2h4klxWcyhxHQxQhnib8F5x6fWyao36Mces8AIAg%2F640%3Fwx_fmt%3Djpeg");
        book5.setData("夫伤寒论，盖祖述大圣人之意，诸家莫其伦拟，故晋·皇甫谧序《甲乙针经》云：伊尹以元圣之才，撰用神农本草，以为汤液；汉·张仲景论广汤液，为十数卷，用之多验；近世太医令王叔和，撰次仲景遗论甚精，皆可施用。是仲景本伊尹之法，伊尹本神农之经，得不谓祖述大圣人之意乎？张仲景，《汉书》无传，见《名医录》云：南阳人，名机，仲景乃其字也。举孝廉，官至长沙太守，始受术于同郡张伯祖，时人言，识用精微过其师，所著论，其言精而奥，其法简而详，非浅闻寡见者所能及。自仲景于今八百余年，惟王叔和能学之，其间如葛洪、陶景、胡洽、徐之才、孙思邈辈，非不才也，但各自名家，而不能修明之。开宝中，节度使高继冲，曾编录进上，其文理舛错，未尝考正；历代虽藏之书府，亦缺于仇校。是使治病之流，举天下无或知者。国家诏濡臣校正医书，臣奇续被其选。以为百病之急，无急于伤寒，今先校定张仲景《伤寒论》十卷，总二十二篇，证外合三百九十七法，除复重，定有一百一十二方，今请颁行。\n" +
                "\n" +
                "太子右赞善大夫臣高保衡、尚书屯田员外郎臣孙奇、尚书司封郎中秘阁校理臣林亿等谨上。");
        dao.insert(book5);

        MedicalBook book6=new MedicalBook();
        book6.setBookName("难经");
        book6.setName("一难");
        book6.setSortType("论脉");
        book6.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550481510547&di=259794b75e19e869c1c9273914b27d6a&imgtype=0&src=http%3A%2F%2Fdingyue.nosdn.127.net%2FTWOvdIKvpXg1pF4KKS46%3DbJSRcGrHabdG%3DD4j1RS7zfZ21543496457514compressflag.jpg ");
        book6.setData("曰：十二经皆有动脉，独取寸口，以决五脏六腑死生吉凶之法，何谓也？\n" +
                "\n" +
                "　　然：寸口者，脉之大会，手太阴之脉动也。人一呼脉行三寸，一吸脉行三寸，呼吸定息，脉行六寸。人一日一夜，凡一万三千五百息，脉行五十度，周于身。漏水下百刻，营卫行阳二十五度，行阴亦二十五度，为一周也，故五十度复会于手太阴。寸口者，五脏六腑之所终始，故法取于寸口也。");
        dao.insert(book6);

        MedicalBook book7=new MedicalBook();
        book7.setName("医学源流第一");
        book7.setBookName("医学三字经");
        book7.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550482644783&di=363a7ec60fb7096c01ad40490f5d5990&imgtype=0&src=http%3A%2F%2Fwww.kfzimg.com%2FG03%2FM01%2F18%2F8F%2FpYYBAFSx1L2AHX5hAAPYkdLU6Uo791_b.jpg");
        book7.setSortType("卷之一");
        book7.setData("医之始 本岐黄 黄，黄帝也；岐，岐伯也。君臣问答，以明经络、脏腑、运气、治疗之原，所以为医之祖。虽《神农本经》在黄帝之前，而神明用药之理，仍始于《内经》也。\n" +
                "\n" +
                "灵枢作 素问详 《灵枢》九卷、《素问》九卷，通谓之《内经》，《汉书·艺文志》载《黄帝内经十八篇》是也。医门此书，即业儒之五经也。\n" +
                "\n" +
                "难经出 更洋洋 洋洋，盛大也。《难经》八十一章，多阐发《内经》之旨，以补《内经》所未言，即间有与《内经》不合者，其时去古未远，别有考据也。秦越人，号扁鹊，战国人，着《难经》。\n" +
                "\n" +
                "越汉季 有南阳 张机，字仲景，居南阳，官长沙，东汉人也。着《伤寒杂病论》《金匮玉函经》。\n" +
                "\n" +
                "六经辨 圣道彰 《内经》详于针灸，至伊芳尹有汤液治病之法，扁鹊、仓公因之。仲师出而杂病伤寒专以方药为治，其方俱原本于神农、黄帝相传之经方，而集其大成。\n" +
                "\n" +
                "伤寒着 金匮藏 王肯堂谓《伤寒论》义理如神龙出没，首尾相顾，鳞甲森然。金匮玉函，示宝贵秘藏之意也。其方非南阳所自造，乃上古圣人所传之方，所谓经方是也。其药悉本于《神农本经》。非此方不能治此病，非此药不能成此方，所投必效，如桴鼓之相应。\n" +
                "\n" +
                "垂方法 立津梁 仲师，医中之圣人也。儒者不能舍至圣之书而求道，医者岂能外仲师之书以治疗。\n" +
                "\n" +
                "李唐后 有千金 唐·孙思邈，华原人，隐居太白山。着《千金方》《千金翼方》各三十卷。宋仁宗命高保衡、林亿校正，后列《禁经》二卷。今本分为九十三卷。较《金匮》虽有浮泛偏杂之处，而用意之奇，用药之巧，亦自成一家。\n" +
                "\n" +
                "外台继 重医林 唐·王焘着《外台秘要》四十卷，分一千一百四门，论宗巢氏，方多秘传，为医门之类书。\n" +
                "\n" +
                "后作者 渐浸淫 等而下之，不足观也已。\n" +
                "\n" +
                "红紫色 郑卫音 间色乱正，靡音忘倦。\n" +
                "\n" +
                "迨东垣 重脾胃 金·李杲，字明之，号东垣老人。生于世宗大定二十年，金亡入元，十七年乃终，年七十二，旧本亦题元人。作《脾胃论》《辨惑论》《兰室秘藏》。后人附以诸家合刻，有《东垣十书》传世。\n" +
                "\n" +
                "温燥行 升清气 如补中益气及升阳散火之法，如苍术、白术、羌活、独活、木香、陈皮、葛根之类，最喜用之。\n" +
                "\n" +
                "虽未醇 亦足贵 人谓东垣用药，如韩信将兵，多多益善。然驳杂之处，不可不知。惟以脾胃为重，故亦可取。\n" +
                "\n" +
                "若河间 专主火 金·刘完素，字守真，河间人。事迹俱详《金史·方技传》。主火之说，始自河间。\n" +
                "\n" +
                "遵之经 断自我 《原病式》十九条，俱本《内经·至真要大论》，多以火立论，而不能参透经旨。如火之平气曰升明，火之太过曰赫曦，火之不及曰伏明，其虚实之辨，若冰炭之反也。\n" +
                "\n" +
                "一二方 奇而妥 如六一散、防风通圣散之类，皆奇而不离于正也。\n" +
                "\n" +
                "丹溪出 罕与俦 元·朱震亨，字彦修，号丹溪，金华人。其立方视诸家颇高一格。\n" +
                "\n" +
                "阴宜补 阳勿浮 《丹溪心法》以补阴为主，谓阳常有余，阴常不足。诸家俱辨其非，以人得天地之气以生，有生之气，即是阳气，精血皆其化生也。\n" +
                "\n" +
                "杂病法 四字求 谓气、血、痰、郁是也。一切杂病，只以此四字求之。气用四君子汤，血用四物汤，痰用二陈汤，郁用越鞠丸。参差互用，各尽其妙。\n" +
                "\n" +
                "若子和 主攻破 张子和(戴人)书中，所主多大黄、芒硝、牵牛、芫花、大戟、甘遂之类，意在驱邪。邪去则正安，不可畏攻而养病。\n" +
                "\n" +
                "病中良 勿太过 子和之法，实症自不可废，然亦宜中病而即止；若太过，则元气随邪气而俱散，挽无及矣。\n" +
                "\n" +
                "四大家 声名噪 刘河间、张子和、李东垣、朱丹溪为金、元四大家，《张氏医通》之考核不误。\n" +
                "\n" +
                "必读书 错名号 李士材《医宗必读》四大家论，以张为张仲景，误也。仲景为医中之圣，三子岂可与之并论。\n" +
                "\n" +
                "明以后 须酌量 言医书充栋汗牛，可以博览之，以广见识，非谓诸家所着皆善本也。\n" +
                "\n" +
                "详而备 王肯堂 金坛王宇泰，讳肯堂。着《证治准绳》，虽无所采择，亦医林之备考也。\n" +
                "\n" +
                "薛氏按 说骑墙 明·薛己，号立斋，吴县人。着《薛氏医按》十六种，大抵以四君子、六君子、逍遥散、归脾汤、六八味丸主治，语多骑墙。\n" +
                "\n" +
                "士材说 守其常 李中梓，号士材，国朝人也。着《医宗必读》《士材三书》。虽曰浅率，却是守常，初学人所不废也。\n" +
                "\n" +
                "景岳出 着新方 明·张介宾，字会卿，号景岳，山阴人。着《类经质疑录》。全书所用之方，不外新方八阵，其实不足以名方。古圣人明造化之机，探阴阳之本，制出一方，非可以思议及者。若仅以熟地补阴，人参补阳，姜附祛寒，芩连除热，随拈几味，皆可名方，何必定为某方乎？\n" +
                "\n" +
                "石顽续 温补乡 张璐，字路玉，号石顽，国朝人。着《医通》，立论多本景岳，以温补为主。\n" +
                "\n" +
                "献可论 合二张 明·宁波赵献可，号养葵。着《医贯》。大旨重于命门，与张石顽、张景岳之法相同。\n" +
                "\n" +
                "诊脉法 濒湖昂 明·李时珍，字东璧，号濒湖。着《本草纲目》五十二卷，杂收诸说，反乱《神农本经》之旨。卷末刻《脉学》颇佳，今医多宗之。\n" +
                "\n" +
                "数子者 各一长 知其所长，择而从之。\n" +
                "\n" +
                "揆诸古 亦荒唐 理不本于《内经》，法未熟乎仲景，纵有偶中，亦非不易矩 。\n" +
                "\n" +
                "长沙室 尚彷徨 数子虽曰私淑长沙，升堂有人，而入室者少矣！\n" +
                "\n" +
                "惟韵伯 能宪章 慈溪柯琴，字韵伯，国朝人。着《伤寒论注》、《论翼》，大有功于仲景，而《内经》之旨，赖之以彰。\n" +
                "\n" +
                "徐尤着 本喻昌 徐彬，号忠可；尤怡，号在泾。二公《金匮》之注，俱本喻嘉言。考嘉言名昌，江西南昌人。崇祯中以选举入都，卒无所就。遂专务于医，着《尚论篇》，主张太过，而《医门法律》颇能阐发《金匮》之秘旨。\n" +
                "\n" +
                "大作者 推钱塘 张志聪，号隐庵；高世 ，号士宗。俱浙江钱塘人也。国朝康熙间，二公同时学医，与时不合，遂闭门着书，以为传道之计。所注《内经》《本草经》《伤寒论》《金匮》等书，各出手眼，以发前人所未发，为汉后第一书。今医畏其难，而不敢谈及。\n" +
                "\n" +
                "取法上 得慈航 取法乎上，仅得其中。切不可以《医方集解》《本草备要》《医宗必读》《万病回春》《本草纲目》《东医宝鉴》《冯氏锦囊》《景岳全书》《薛氏医按》等书，为快捷方式也。今之医辈，于此书并未寓目，止取数十种庸陋之方，冀图幸中，更不足论也。");
        dao.insert(book7);

        MedicalBook book8=new MedicalBook();

        book8.setName("序");
        book8.setBookName("濒湖脉学");
        book8.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550487522042&di=42796f328f96269cad5ca707d1a1498c&imgtype=jpg&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D3459238586%2C1441666765%26fm%3D214%26gp%3D0.jpg");
        book8.setSortType("序");
        book8.setData("时珍曰：宋有俗子，杜撰脉诀，鄙陋讹谬，医学习诵，以为权舆，逮臻颁白，脉理竟昧。戴同父常刊其误，先考月池翁著《四诊发明》八卷，皆精诣奥室，浅学未能窥造珍，因撮粹撷华僭撰此书，以便习读，为脉指南。世之医病两家，咸以脉为首务。不知脉乃四诊之末，谓之巧者尔。上士欲会其全，非备四诊不可。\n" +
                "\n" +
                "　　明嘉靖甲子上元日，谨书于濒湖薖所。");
        dao.insert(book8);

        MedicalBook book9=new MedicalBook();
        book9.setName("藏府经络先后病脉证一");
        book9.setBookName("金匮要略");
        book9.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550506358300&di=2ef01e8acf081e3a280f01e6f8178b92&imgtype=0&src=http%3A%2F%2Ffile.gucn.com%2Ffile%2FCurioPicfile%2F8-80607-2008040809165253870.JPG");
        book9.setSortType("卷上");
        book9.setData("（论十三首 脉证二条）\n" +
                "\n" +
                "　　问曰：上工治未病，何也？师曰：夫治未病者，见肝之病，知肝传脾，当先实脾，四季脾旺不受邪，即勿补之。中工不晓相传，见肝之病，不解实脾，惟治肝也。\n" +
                "\n" +
                "　　夫肝之病，补用酸，助用焦苦，益用甘味之药调之。酸入肝，焦苦入心，甘入脾。脾能伤肾，肾气微弱，则水不行；水不行，则心火气盛，则伤肺；肺被伤，则金气不行；金气不行，则肝气盛。故实脾，则肝自愈。此治肝补脾之要妙也。肝虚则用此法，实则不在用之。经曰：虚虚实实，补不足，损有余，是其义也。余藏准此。\n" +
                "\n" +
                "　　夫人禀五常，因风气而生长，风气虽能生万物，亦能害万物，如水能浮舟，亦能覆舟。若五藏元真通畅，人即安和。客气邪风，中人多死。千般疢难，不越三条；一者，经络受邪，入藏府，为内所因也；二者，四肢九窍，血脉相传，壅塞不通，为外皮肤所中也；三者，房室、金刃、虫兽所伤。以此详之，病由都尽。\n" +
                "\n" +
                "　　若人能养慎，不令邪风干杵经络，适中经络，未流传藏府，即医治之，四肢才觉重滞，即导引、吐纳、针灸、膏摩，勿令九窍闭塞；更能无犯王法、禽兽灾伤，房室勿令竭乏，服食节其冷、热、苦、酸、辛、甘，不遗形体有衰，病则无由入其腠理。（腠者，是三焦通会元真之处，为血气所注；理者，是皮肤藏府之纹理也）\n" +
                "\n" +
                "　　问曰：病人有气色见于面部，愿闻其说。师曰：鼻头色青，腹中痛，苦冷者死（一云腹中冷，苦痛者死）。鼻头色微黑色，有水气；色黄者，胸上有寒；色白者，亡血也。设微赤非时者死。其目正圆者痉，不治。又色青为痛，色黑为劳，色赤为风，色黄者便难，色鲜明者有留饮。\n" +
                "\n" +
                "　　师曰：病人语声寂然喜惊呼者，骨节间病；语声喑喑然不彻者，心膈间病；语声啾啾然细而长者，头中病（一作痛）。\n" +
                "\n" +
                "　　师曰：息摇肩者，心中坚，息引胸中，上气者，咳息张口，短气者，肺痿唾沫。\n" +
                "\n" +
                "　　师曰：吸而微数，其病在中焦，实也，当下之即愈，虚者不治。在上焦者，其吸促，在下焦者，其吸远，此皆难治。呼吸动摇振振者，不治。\n" +
                "\n" +
                "　　师曰：寸口脉动者，因其旺时而动，假令肝旺色青，四时各随其色。肝色青而反白，非其时色脉，皆当病。\n" +
                "\n" +
                "　　问曰：有未至而至，有至而不至，有至而不去，有至而太过，何谓也？师曰：冬至之后，甲子夜半少阳起，少阴之时，阳始生，天得温和。以未得甲子，天因温和，此为末至而至也；以得甲子，而天未温和，为至而不至也；以得甲子，而大大寒不解，此为至而不去也；以得甲子，而天温如盛夏五六月时，此为至而太过也。\n" +
                "\n" +
                "　　师曰：病人脉浮者在前，其病在表；浮者在后，其病在里，腰痛背强不能行，必短气而极也。\n" +
                "\n" +
                "　　问曰：经云：\"厥阳独行\"，何谓也？师曰：此为有阳无阴，故称厥阳。\n" +
                "\n" +
                "　　师曰：寸脉沉大而滑，沉则为实，滑则为气，实气相搏，血气入藏即死，入府即愈，此为卒厥，何谓也？师曰：唇口青，身冷，为入藏，即死；如身和，汗目出，为入府，即愈。\n" +
                "\n" +
                "　　问曰：脉脱，入藏即死，入府即愈，何谓也？师曰：非为一病，百病皆然。譬如浸淫疮，从口起流向四肢者可治，从四肢流来入口者不可治；病在外者可治，入里者即死。\n" +
                "\n" +
                "　　问曰：阳病十八何谓也？师曰：头痛、项、腰、脊、臂、脚掣痛。阴病十八，何谓也？师曰：咳、上气、喘、哕、咽、肠鸣、胀满、心痛、拘急。五藏病各有十八，合为九十病；人又有六微，微有十八病，合为一百八病，五劳、七伤、六极、妇人三十六病，不在其中。\n" +
                "\n" +
                "　　清邪居上，浊邪居下，大邪中表，小邪中里，馨饪之邪，从口入者，宿食也。五邪中人，各有法度，风中于前，寒中于暮，湿伤于下，雾伤于上，风令脉浮，寒令脉急，雾伤皮肤，湿流关节，食伤脾胃，极寒伤经，极热伤络。\n" +
                "\n" +
                "　　问曰：病有急当救里救表者，何谓也？师曰：病，医下之，续得下利清谷不止，身体疼痛者，急当救里；后身体疼痛，清便自调者，急当救表也。夫病痼疾加以卒病，当先治其卒病，后乃治其痼疾也。\n" +
                "\n" +
                "　　师曰：五藏病各有所*得者愈，五藏病各有所恶，各随其所不喜者为病。病者素不应食，而反暴思之，必发热也。夫诸病在藏，欲攻之，当随其所得而攻之，如渴者，与猪苓汤。余皆仿此。");
        dao.insert(book9);

        MedicalBook book10=new MedicalBook();
        book10.setName("序");
        book10.setBookName("千金要方");
        book10.setImageUrl("http://img2.imgtn.bdimg.com/it/u=2023907281,616051849&fm=26&gp=0.jpg");
        book10.setSortType("序");
        book10.setData("夫清浊剖判，上下攸分，三才肇基，五行 落，万物淳朴，无得而称。燧人氏出，观斗极以定方名，始有火化。伏羲氏作，因之而画八卦、立庖厨，滋味既兴， 瘵萌起。大圣神农氏悯黎元之多疾，遂尝百药以救疗之，犹未尽善。黄帝受命，创制九针，与方士岐伯、雷公之伦，备论经脉，旁通问难，详究义理，以为经论，故后世可得根据而畅焉。春秋之际，良医和缓，六国之时，则有扁鹊，汉有仓公，仲景，魏有华佗，并皆探赜索隐，穷幽洞微，用药不过二三，灸炷不逾七八，而疾无不愈者。晋宋以来，虽复名医间出，然治十不能愈五六，良由今人嗜欲太甚，立心不常，淫放纵逸，有阙摄养所致耳。余缅寻圣人设教，欲使家家自学，人人自晓。君亲有疾不能疗之者，非忠孝也。末俗小人，多行诡诈，倚傍圣教而为欺 ，遂令朝野士庶咸耻医术之名。多教子弟诵短文，枸小策，以求出身之道。医治之术，阙而弗论，吁可怪也。嗟乎！深乖圣贤之本意。吾幼遭风冷，屡造医门，汤药之资，罄尽家产。所以青衿之岁，高尚兹典；白首之年，未尝释卷。至于切脉诊候，采药合和，服饵节度，将息避慎，一事长于己者，不远千里伏膺取决。至于弱冠，颇觉有悟，是以亲邻国中外有疾厄者，多所济益。在身之患，断绝医门，故知方药本草不可不学。吾见诸方部帙浩博，忽遇仓猝，求检至难，比得方讫，疾已不救矣。呜呼！痛夭枉之幽厄，惜堕学之昏愚，乃博采群经，删裁繁重，务在简易，以为《备急千金要方》一部，凡三十卷。虽不能究尽病源，但使留意于斯者，亦思过半矣。以为人命至重，有贵千金，一方济之，德逾于此，故以为名也。未可传于士族，庶以贻厥私门。张仲景曰∶当今居世之士，曾不留神医药，精究方术，上以疗君亲之疾，下以救贫贱之厄，中以保身长全，以养其生。而但竞逐荣势，企踵权豪，孜孜汲汲，唯名利是务，崇饰其末，而忽弃其本，欲华其表而悴其内，皮之不存，毛将安附？进不能爱人知物，退不能爱躬知己，卒然遇邪风之气，婴非常之疾，患及祸至而后震栗。身居厄地，蒙蒙昧昧，戆若游魂，降志屈节，钦望巫祝，告究归天，束手受败。 百年之寿命，将至贵之重器，委付庸医，恣其所措，咄嗟喑呜，厥身已毙，神明消灭，变为异物，幽潜重泉，徒为涕泣。夫举世昏迷，莫能觉悟，自弃若是，夫何荣势之云哉。此之谓也。");
        dao.insert(book10);

        MedicalBook book11=new MedicalBook();
        book11.setName("原病篇");
        book11.setBookName("温病条辨");
        book11.setSortType("卷首·原病篇");
        book11.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550508459283&di=cd0b974d14a4a8975f0d244d30e99188&imgtype=0&src=http%3A%2F%2Fwww.wzljl.cn%2Fimages%2Fattachement%2Fjpg%2Fsite2%2F20091112%2F0025115fff9a0c6622cd49.jpg");
        book11.setData("一、《六元正纪大论》曰∶辰戌之岁，初之气，民厉温病。卯酉之岁，二之气，厉大至，民善暴死，终之气，其病温。寅申之岁，初之气，温病乃起。丑未之岁，二之气，温厉大行，远近咸若。子午之岁，五之气，其病温。己亥之岁，终之气，其病温厉。\n" +
                "\n" +
                "叙气运，原温病之始也。每岁之温，有早暮微盛不等，司天在泉，主气客气，相加临而然也。细考《素问》注自知，兹不多赘。按吴又可谓温病非伤寒，温病多而伤寒少，甚通。谓非其时而有其气，未免有顾此失彼之诮。盖时和岁稔，天气以宁，民气以和，虽当盛之岁亦微；至于凶荒兵火之后，虽应微之岁亦盛，理数自然之道，无足怪者。\n" +
                "\n" +
                "二、《阴阳应象大论》曰∶喜怒不节，寒暑过度，生乃不固。故重阴必阳，重阳必阴，故曰∶冬伤于寒，春必病温。\n" +
                "\n" +
                "上节统言司天之病，此下专言人受病之故。\n" +
                "\n" +
                "细考宋元以来诸名家，皆不知温病伤寒之辨。\n" +
                "\n" +
                "如庞安常之《卒病论》，朱肱之《活人书》，韩HT 和之《微旨》，王氏之《证治》，刘守真之《伤寒医鉴》、《伤寒直格》，张子和之《伤寒心镜》等书，非以治伤寒之法治温病，即将温暑认作伤寒，而疑麻桂之法不可用，遂别立防风通圣、双解通圣、九味羌活等汤，甚至于辛温药中加苦寒，王安道《溯洄集》中辨之最详，兹不再辩。论温病之最详者，莫过张景岳、吴又可、喻嘉言三家。时医所宗者，三家为多，请略陈之∶按张景岳、喻嘉言皆着讲寒字，并未理会本文上有“故曰”二字，上文有“重阴必阳、重阳必阴”二句，张氏立论出方，悉与伤寒混，谓温病即伤寒，袭前人之旧，全无实得，固无足论。喻氏立论，虽有分析，中篇亦混入伤寒少阴、厥阴证，出方亦不能外辛温发表、辛热温里，为害实甚。以苦心力学之士，尚不免智者千虑之失，尚何怪后人之无从取法，随手杀人哉！甚矣学问之难也！吴又可实能识得寒温二字，所见之证，实无取乎辛温辛热甘温，又不明伏气为病之理，以为何者为即病之伤寒，何者为不即病待春而发之温病，遂直断温热之原非风寒所中，不责己之不明，反责经言之谬。瑭推原三子之偏，各自有说∶张氏混引经文，将论伤寒之文，引证温热，以伤寒化热之后，经亦称热病故也，张氏不能分析，遂将温病认作伤寒。喻氏立论，开口言春温，当初春之际，所见之病，多有寒证，遂将伤寒认作温病。吴氏当崇祯凶荒兵火之际，满眼温疫，遂直辟经文“冬伤于寒、春必病温”之文。盖皆各执己见，不能融会贯通也。瑭按伏气为病，如春温、冬咳、温疟，《内经》已明言之矣。亦有不因伏气，乃司天时令现行之气，如前列《六元正纪》所云是也。此二者，皆理数之常者也。更有非其时而有其气，如又可所云戾气，间亦有之，乃其变也。惟在司命者善查其常变而补救之。\n" +
                "\n" +
                "三、《金匮真言论》曰∶夫精者身之本也，故藏于精者，春不病温。\n" +
                "\n" +
                "《易》曰∶履霜坚冰至，圣人恒示戒于早，必谨于微。记曰∶凡事豫则立。经曰∶上工不治已病治未病，圣人不治已乱治未乱。此一节当与月令参看，与上条冬伤于寒互看，盖谓冬伤寒则春病温，惟藏精者足以避之。故《素问》首章《上古天真论》，即言男女阴精之所以生，所以长，所以枯之理；次章紧接《四气调神大论》，示人春养生以为夏奉长之地，夏养长以为秋奉收之地，秋养收以为冬奉藏之地，冬养藏以为春奉生之地。盖能藏精者一切病患皆可却，岂独温病为然哉！《金匮》谓五脏元真通畅，人即安和是也。何喻氏不明此理，将冬伤于寒作一大扇文本，将不藏精又作一大扇文本，将不藏精而伤于寒，又总作一大扇文本，勉强割裂《伤寒论》原文以实之，未免有过虑则凿之弊。不藏精三字须活看，不专主房劳说，一切人事之能摇动其精者皆是，即冬日天气应寒而阳不潜藏，如春日之发泄，甚至桃李反花之类亦是。\n" +
                "\n" +
                "四、《热论篇》曰∶凡病伤寒而成温者，先夏至日者为病温；后夏至日者为病暑，暑当与汗出，勿止。\n" +
                "\n" +
                "温者，暑之渐也。先夏至，春候也。春气温，阳气发越，阴精不足以承之，故为病温。后夏至，温盛为热，热盛则湿动，热与湿搏而为暑也。勿者，禁止之词。勿止暑之汗，即治暑之法也。\n" +
                "\n" +
                "五、《刺志论》曰∶气盛身寒，得之伤寒；气虚身热，得之伤暑。\n" +
                "\n" +
                "此伤寒暑之辨也。经语分明如此，奈何世人悉以治寒法治温暑哉！\n" +
                "\n" +
                "六、《生气通天论》曰∶因于暑，汗，烦则喘喝，静则多言。\n" +
                "\n" +
                "暑中有火，性急而疏泄，故令人自汗。火与心同气相求，故善烦（烦从火从页，谓心气不宁，而面若火烁也）。烦则喘喝者，火克金故喘，郁遏胸中清廓之气，故欲喝而呻之。其或邪不外张而内藏于心，则静；心主言，暑邪在心，虽静亦欲自言不休也。\n" +
                "\n" +
                "七、《论疾诊尺篇》曰∶尺肤热甚，脉盛躁者，病温也；其脉盛而滑者，病且出也。\n" +
                "\n" +
                "此节以下，诊温病之法。\n" +
                "\n" +
                "经之辨温病分明如是，何世人悉谓伤寒，而悉以伤寒足三阴经温法治之哉！张景岳作《类经》，割裂经文，蒙混成章，由未细心 绎也。尺肤热脉，火烁精也；脉盛躁，精被火煎沸也；脉盛而滑，邪机向外也。\n" +
                "\n" +
                "八、《热病篇》曰∶热病三日，而气口静人迎躁者，取之诸阳五十九刺，以泻其热而出其汗，实其阴以补其不足者。身热甚，阴阳皆静者，勿刺也；其可刺者，急取之，不汗出则泄。所谓勿刺者，有死征也。热病七日八日动喘而弦者，急刺之，汗且自出，浅刺手大指间。热病七日八日脉微小，病者溲血，口中干，一日半而死，脉代者一日死。热病已得汗出而脉尚躁，喘，且复热，勿刺肤，喘甚者死。热病七日八日脉不躁，躁不散数，后三日中有汗，三日不汗四日死；未曾汗者，勿腠刺之。\n" +
                "\n" +
                "热病不知所痛，耳聋不能自收，口干，阳热甚，阴颇有寒者，热在骨髓，死不可治。\n" +
                "\n" +
                "热病已得汗而脉尚躁盛，此阴脉之极也，死；其得汗而脉静者，生。热病者，脉尚躁盛而不得汗者，此阳脉之极也，死（阳脉之极，虽云死征，较前阴阳俱静有差，此证犹可大剂急急救阴，亦有活者。盖已得汗而阳脉躁甚，邪强正弱，正尚能与邪争，若留得一分正气，便有一分生理，只在留之得法耳。至阴阳俱静，邪气深入下焦阴分，正无捍邪之意，直听邪之所为，不死何待）。脉盛躁，得汗静者生。\n" +
                "\n" +
                "热病不可刺者有九∶一曰汗不出，大颧发赤，哕者死。二曰泄而腹满甚者死。三曰目不明，热不已者死。四曰老人婴儿，热而腹满者死。五曰汗大出，呕。下血者死。六曰舌本烂，热不已者死。七曰咳而衄汗不出，出不至足者死。八曰髓热者死。九曰热而痉者死，腰折、螈 、齿噤 也。凡此九者不可刺也。\n" +
                "\n" +
                "太阳之脉色荣颧骨，热病也，与厥阴脉争见者，死期不过三日。\n" +
                "\n" +
                "少阳之脉色荣颊前，热病也，与少阴脉争见者，死期不过三日。\n" +
                "\n" +
                "此节历叙热病之死征，以禁人之刺，盖刺则必死也。然刺固不可，亦间有可药而愈者。盖刺法能泄能通，开热邪之闭结最速，至于益阴以留阳，实刺法之所短，而汤药之所长也。\n" +
                "\n" +
                "热病三日而气口静人迎躁者，邪机尚浅，在上焦，故取之诸阳以泄其阳邪，阳气通则汗随之；实其阴以补其不足者，阳盛则阴衰，泻阳则阴得安其位，故曰实其阴，泻阳之有余，即所以补阴之不足，故曰补其不足也（实其阴以补其不足，此一句，实治温热之吃紧大纲。盖热病未有不耗阴者，其耗之未尽则生，尽则阳无留恋，必脱而死也。真能体味此理，思过半矣。此论中治法，实从此处入手）。\n" +
                "\n" +
                "身热甚而脉之阴阳皆静，脉证不应，阳证阴脉，故曰勿刺。\n" +
                "\n" +
                "热病七、八日动喘而弦，喘为肺气实，弦为风火鼓荡，故浅刺手大指间，以泄肺气，肺之热痹开则汗出。大指间，肺之少商穴也。\n" +
                "\n" +
                "热证七、八日脉微小者，邪气深入下焦血分，逼血从小便出，故溲血，肾精告竭，阴液不得上潮，故口中干；脉至微小，不惟阴精竭，阳气亦从而竭矣，死象自明。倘脉实者可治，法详于后。\n" +
                "\n" +
                "热病已得汗，脉尚躁而喘，故知其复热也；热不为汗衰，火热克金故喘，金受火克，肺之化源欲绝，故死。间有可治，法详于后。\n" +
                "\n" +
                "热病不知所痛，正衰不与邪争也；耳聋，阴伤精欲脱也；不能自收，真气惫也；口干热甚，阳邪独盛也；阴颇有寒，此寒字，作虚字讲，谓下焦阴分颇有虚寒之证，以阴精亏损之人，真气败散之象已见，而邪热不退，未有不乘其空虚而入者，故曰热在骨髓，死不治也。\n" +
                "\n" +
                "其有阴衰阳盛而真气未至溃败者，犹有治法，详见于后。\n" +
                "\n" +
                "热病已得汗而脉尚躁盛，此阴虚之极，故曰死。然虽不可刺，犹可以药沃之得法，亦有生者，法详于后。\n" +
                "\n" +
                "脉躁盛不得汗，此阳盛之极也。阳盛而至于极，阴无容留之地，故亦曰死。然用药开之得法，犹可生，法详于后。\n" +
                "\n" +
                "汗不出而颧赤，邪盛不得解也；哕，脾阴病也。阴阳齐病，治阳碍阴，治阴碍阳，故曰死也。泄而腹满甚，脾阴病重也，亦系阴阳皆病。目不明，精散而气脱也。经曰∶精散视岐，又曰气脱者目不明。热犹未已，仍铄其精而伤其气，不死得乎！老人婴儿，一则孤阳已衰，一则稚阳未足，既得温热之阳病，又加腹满之阴病，不必至于满甚，而已有死道焉。汗不出为邪阳盛，呕为正阳衰；下血者，热邪深入不得外出，必逼迫阴络之血下注，亦为阴阳两伤也。舌本烂，肾脉胆脉心脉皆循喉咙系舌本，阳邪深入，则一阴一阳之火结于血分，肾水不得上济，热退犹可生，热仍不止，故曰死也。咳而衄，邪闭肺络，上行清道，汗出邪泄可生，不然则化源绝矣。髓热者，邪入至深至于肾部也。热而痉，邪入至深至于肝部也。以上九条，虽皆不可刺，后文亦间立治法，亦有可生者。太阳之脉色荣颧骨为热病者，按手太阳之脉，由目内 斜络于颧，而与足太阳交，是颧者两太阳交处也，太阳属水，水受火沸，故色荣赤为热病也；与厥阴脉争见，厥阴，木也，水受火之反克，金不来生木反生火，水无容足之地，故死速也。少阳之脉色荣颊前为热病者，按手少阳之脉，出耳前，过客主人前（足少阳穴），交颊至目锐 而交足少阳，是颊前两少阳交处也，少阳属相火，火色现于二经交会之处，故为热病也；与少阴脉争见，少阴属君火，二火相炽，水难为受，故亦不出三日而死也。\n" +
                "\n" +
                "九、《评热病论》∶帝曰∶有病温者，汗出辄复热，而脉躁疾，不为汗衰，狂言不能食，病名为何？岐伯曰∶病名阴阳交，交者死也。\n" +
                "\n" +
                "人所以汗出者，皆生于谷，谷生于精。今邪气交争于骨肉而得汗者，是邪却而精胜也。精胜则当能食而不复热。复热者，邪气也，汗者，精气也。\n" +
                "\n" +
                "今汗出而辄复热者，邪气胜也；不能食者，精无俾也；病而留者，其寿可立而倾也。且夫《热论》曰∶汗出而脉尚躁盛者死。今脉不与汗相应，此不胜其病也，其死明矣。狂言者，是失志，失志者死。\n" +
                "\n" +
                "今见三死，不见一生，虽愈必死也。\n" +
                "\n" +
                "此节语意自明，经谓必死之证，谁敢谓生，然药之得法，有可生之理，前所谓针药各异用也，详见后。\n" +
                "\n" +
                "十、《刺热篇》曰∶肝热病者，小便先黄，腹痛多卧，身热。热争则狂言及惊，胁满痛，手足躁，不得安卧，庚辛甚，甲乙大汗，气逆则庚辛日死。刺足厥阴、少阳，其逆则头痛员员脉引冲头也。\n" +
                "\n" +
                "肝病小便先黄者，肝脉络阴器；又肝主疏泄。\n" +
                "\n" +
                "肝病则失其疏泄之职，故小便先黄也。腹痛多卧，木病克脾土也。热争，邪热甚而与正气相争也。\n" +
                "\n" +
                "狂言及惊，手厥阴心包病也，两厥阴同气，热争，则手厥阴亦病也。胁满痛，肝脉行身之两旁，胁其要路也。手足躁不得安卧，肝主风，风淫四末，又木病克土，脾主四肢，木病热，必吸少阴肾中真阴，阴伤，故骚扰不得安卧也。庚辛金日克木，故甚。甲乙肝木旺时，故汗出而愈。气逆谓病重而不顺其可愈之理，故逢其不胜之日而死也。刺足厥阴、少阳，厥阴系本脏，少阳，厥阴之腑也，并刺之者，病在脏，泻其腑也。逆则头痛以下，肝主升，病极而上升之故。\n" +
                "\n" +
                "自庚辛日甚以下之理，余脏仿此。\n" +
                "\n" +
                "十一、心热病者，先不乐，数日乃热。热争则卒心痛，烦闷善呕，头痛面赤无汗；壬癸甚，丙丁大汗，气逆则壬癸死。刺手少阴、太阳。\n" +
                "\n" +
                "心病先不乐者，心包名膻中，居心下代君用事，经谓膻中为臣使之官，喜乐出焉，心病故不乐也。卒心痛，凡实痛，皆邪正相争，热争，故卒然心痛也。烦闷，心主火，故烦，膻中气不舒，故闷。\n" +
                "\n" +
                "呕，肝病也，两厥阴同气，膻中代心受病，故热甚而争之后，肝病亦见也，且邪居膈上，多善呕也。头痛，火升也。面赤，火色也。无汗，汗为心液，心病故汗不得通也。\n" +
                "\n" +
                "十二、脾热病者，先头重，颊痛，烦心，颜青，欲呕，身热；热争则腰痛，不可用俯仰，腹满泄，两颔痛；甲乙甚，戊己大汗，气逆则甲乙死。刺足太阴、阳明。\n" +
                "\n" +
                "脾病头先重者。脾属湿土，性重，经谓湿之中人也，首如裹，故脾病头先重也。颊，少阳部也，土之与木，此负则彼胜，土病而木病亦见也。烦心，脾脉注心也。颜青欲呕，亦木病也。腰痛不可用俯仰，腰为肾之府，脾主制水，肾为司水之神，脾病不能制水，故腰痛；再脾病胃不能独治，阳明主合同束而利机关，故痛而至于不可用俯仰也。腹满泄，脾经本病也。颔痛，亦木病也。\n" +
                "\n" +
                "十三、肺热病者，先淅然厥，起毫毛，恶风寒，舌上黄，身热；热争则喘咳，痛走胸膺背，不得太息，头痛不堪，汗出而寒；丙丁甚，庚辛大汗，气逆则丙丁死。刺手太阴、阳明，出血如大豆，立已。\n" +
                "\n" +
                "肺病先恶风寒者，肺主气，又主皮毛，肺病则气贲郁不得捍卫皮毛也。舌上黄者，肺气不化则湿热聚而为黄苔也（按苔字，方书悉作胎。胎乃胎包之胎，特以苔生舌上，故从肉旁。不知古人借用之字甚多。盖湿热蒸而生苔，或黄、或白、或青、或黑。皆因病之深浅、或寒、或热、或燥、或湿而然，如春夏间石上土 之阴面生苔者然。故本论苔字。\n" +
                "\n" +
                "悉从草不从肉）。喘，气郁极也。咳，火克金也。胸膺，背之府也，皆天气主之，肺主天气，肺气郁极，故痛走胸膺背也，走者，不定之词。不得太息，气郁之极也。头痛不堪，亦天气贲郁之极也。汗出而寒，毛窍开，故汗出，汗出卫虚，故恶寒，又肺本恶寒也。\n" +
                "\n" +
                "十四、肾热病者，先腰痛， 酸，苦渴数饮，身热；热争则项痛而强， 寒且酸，足下热，不欲言，其逆则项痛，员员澹澹然；戊己甚，壬癸大汗。气逆则戊己死。刺足少阴、太阳。\n" +
                "\n" +
                "肾病腰先痛者，腰为肾之府，又肾脉贯脊会于督之长强穴。 ，肾脉入跟中，以上 内，太阳之脉亦下贯 内，即 也；酸，热烁液也。苦渴数饮，肾主五液而恶燥，病热则液伤而燥，故苦渴而饮水求救也。项，太阳之脉，从巅入络脑，还出别下项∶肾病至于热争，脏病甚而移之腑，故项痛而强也。 寒且酸， 义见上，寒，热极为寒也；酸，热烁液也。足下热，肾脉从小指之下，斜趋足心涌泉穴，病甚而热也。不欲言，心主言，肾病则水克火也。员员澹澹，状其痛之甚而无奈也。\n" +
                "\n" +
                "十五、肝热病者，左颊先赤；心热病者，颜先赤；脾热病者，鼻先赤；肺热病者，右颊先赤；肾热病者，颐先赤。病虽未发，见赤色者刺之，名曰治未病。\n" +
                "\n" +
                "此节言五脏欲病之先，必各现端绪于其部分，示人早治，以免热争则病重也。\n" +
                "\n" +
                "十六、《热论篇》∶帝曰∶热病已愈，时有所遗者，何也？岐伯曰∶诸遗者，热甚而强食之，故有所遗也。若此者，皆病已衰而热有所藏，因其谷气相薄，两热相合，故有所遗也。帝曰∶治遗奈何？岐伯曰∶视其虚实，调其逆从，可使必已也。帝曰∶病热当何禁之？岐伯曰∶病热少愈，食肉则复，多食则遗，此其禁也。\n" +
                "\n" +
                "此节言热病之禁也，语意自明。大抵邪之着人也，每借有质以为根据附，热时断不可食，热退必须少食，如兵家坚壁清野之计，必俟热邪尽退，而后可大食也。\n" +
                "\n" +
                "十七、《刺法论》∶帝曰∶余闻五疫之至，皆相染易，无问大小，病状相似，不施救疗，如何可得不相移易者？岐伯曰∶不相染者，正气存内，邪不可干。\n" +
                "\n" +
                "此言避疫之道。\n" +
                "\n" +
                "按此下尚有避其毒瓦斯若干言，以其想青气想白气等，近于祝由家言，恐后人附会之词，故节之，要亦不能外“正气存内、邪不可干”二句之理，语意已尽，不必滋后学之惑也。\n" +
                "\n" +
                "十八、《玉板论要》曰∶病温虚甚死。\n" +
                "\n" +
                "病温之人，精血虚甚，则无阴以胜温热，故死。\n" +
                "\n" +
                "十九、《平人气象论》曰∶人一呼脉三动，一吸脉三动而躁，尺热曰病温，尺不热脉滑曰病风，脉涩曰痹。\n" +
                "\n" +
                "呼吸俱三动，是六、七至脉矣，而气象又躁急，若尺部肌肉热，则为病温。盖温病必伤金水二脏之津液，尺之脉属肾，尺之穴属肺也，此处肌肉热，故知为病温。其不热而脉兼滑者，则为病风，风之伤人也，阳光受之，尺为阴，故不热也。如脉动躁而兼涩，是气有余而血不足，病则为痹矣。");
        dao.insert(book11);

        MedicalBook book12=new MedicalBook();

        book12.setName("丹沙");
        book12.setBookName("神农本草经");
        book12.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550508882993&di=cd785d58f97dafeb14bbc5175bed4b68&imgtype=0&src=http%3A%2F%2Fimg.kongfz.cn%2F20111119%2F1436659%2F1436659VXXxSm_b.jpg");
        book12.setSortType("上品·玉石部");
        book12.setData("味甘、微寒。\n" +
                "\n" +
                "　　主身体五藏百病，养精神，安魂魄，益气，明目，杀精魅邪恶鬼。久服，通神明不老。能化为汞，生山谷。（太平《御览》引，多有生山谷三字，《大观本》，作生符陵山谷，俱作黑字，考生山谷是经文，后人加郡县耳，宜改为白字，而以郡县为黑字，下皆仿此）。\n" +
                "\n" +
                "　　《吴普》本草曰：丹沙，神农甘、黄帝苦，有毒。扁鹊苦，李氏大寒，或生武陵，采无时，能化未成水银，畏磁石，恶咸水（太平《御览》）。\n" +
                "\n" +
                "　　《名医》曰：作末，名真朱，光色如云母，可折者良，生符陵山谷，采无时。\n" +
                "\n" +
                "　　案《说文》云：丹，巴越之赤石也，象采丹井、象丹形，古文作日，亦作彤、沙、水散石也。澒，丹沙所化为水银也。管子地数篇云：山上有丹沙者，其下有金，《淮南子》地形训云：赤夭七百岁生赤丹，赤丹七百岁生赤澒。高诱云：赤丹，丹沙也。《山海经》云：丹粟，粟、沙，音之缓急也，沙，旧作砂非，汞，即澒省文。《列仙传》云：赤斧能作水澒，炼丹，与消石服之，按金石之药。古人云久服轻身延年者谓当避谷，绝人道，或服数十年乃效耳。今人和肉食服之，遂多相反，转以成疾，不可疑古书之虚证。");
        dao.insert(book12);

    }

    public static void addUser()
    {
        UserDao dao=MyApplication.getDaoSession().getUserDao();

        User defaultUser=new User();
        defaultUser.setName("defaultUser");

        dao.insert(defaultUser);

        LearningProgressDao dao1=MyApplication.getDaoSession().getLearningProgressDao();

        LearningProgress progress=new LearningProgress();
        progress.setUserId(defaultUser.getId());
        progress.setLearningSubject("中药学");
        progress.setLastLearningPosition(0);
        progress.setLastLearningPercent(0);
        dao1.insert(progress);

        LearningProgress progress1=new LearningProgress();
        progress1.setUserId(defaultUser.getId());
        progress1.setLearningSubject("方剂学");
        progress1.setLastLearningPosition(0);
        progress1.setLastLearningPercent(0);
        dao1.insert(progress1);

        LearningProgress progress2=new LearningProgress();
        progress2.setUserId(defaultUser.getId());
        progress2.setLearningSubject("中成药");
        progress2.setLastLearningPosition(0);
        progress2.setLastLearningPercent(0);
        dao1.insert(progress2);

        LearningProgress progress3=new LearningProgress();
        progress3.setUserId(defaultUser.getId());
        progress3.setLearningSubject("经典医书");
        progress3.setLastLearningPosition(0);
        progress3.setLastLearningPercent(0);
        dao1.insert(progress3);

        LearningProgress progress4=new LearningProgress();
        progress4.setUserId(defaultUser.getId());
        progress4.setLearningSubject("针灸学");
        progress4.setLastLearningPosition(0);
        progress4.setLastLearningPercent(0);
        dao1.insert(progress4);



    }

    public static List<LearningProgress> getLearningProgressList(Context context)
    {
        return getUser(context).getList();
    }



    public static User getUser(Context context)
    {
        UserDao userDao=MyApplication.getDaoSession().getUserDao();

        String account= SharePreferenceUtil.getLoginAccount(context);

        List<User> userList=userDao.queryBuilder().where(UserDao.Properties.Name.eq(account)).list();
        return userList.get(0);
    }

    public static void addExam()
    {
        ExaminationDao dao=MyApplication.getDaoSession().getExaminationDao();
        Examination examination1=new Examination();
        examination1.setTitle("首次全面系统整理、补充《神农本草经》的本草著作是");
        examination1.setSortType("中药学专业知识(一)");
        examination1.setSectionA("《本草纲目》");
        examination1.setSectionB("《新修本草》");
        examination1.setSectionC("《本草经集注》");
        examination1.setSectionD("《本草纲目拾遗》");
        examination1.setSectionE("《证类本草》");
        examination1.setCorrectSection("C");
        dao.insert(examination1);

        Examination examination2=new Examination();
        examination2.setTitle("古代新增药物最多的本草著作是");
        examination2.setSortType("中药学专业知识(一)");
        examination2.setSectionA("《中华本草》");
        examination2.setSectionB("《本草纲目拾遗》");
        examination2.setSectionC("《本草纲目》");
        examination2.setSectionD("《证类本草》");
        examination2.setSectionE("《新修本草》");
        examination2.setCorrectSection("B");
        dao.insert(examination2);

        Examination examination3=new Examination();
        examination3.setTitle("古代载药最多的本草著作是");
        examination3.setSortType("中药学专业知识(一)");
        examination3.setSectionA("《神农本草经》");
        examination3.setSectionB("《本草纲目拾遗》");
        examination3.setSectionC("《本草纲目》");
        examination3.setSectionD("《新修本草》");
        examination3.setSectionE("《证类本草》");
        examination3.setCorrectSection("C");
        dao.insert(examination3);

        Examination examination4=new Examination();
        examination4.setTitle("首创按药物自然属性进行分类的本草著作是");
        examination4.setSortType("中药学专业知识(一)");
        examination4.setSectionA("《神农本草经》");
        examination4.setSectionB("《本草经集注》");
        examination4.setSectionC("《本草纲目》");
        examination4.setSectionD("《新修本草》");
        examination4.setSectionE("《本草拾遗》");
        examination4.setCorrectSection("B");
        examination4.setAnswer("本题考查历代本草代表作《本草经集注》的学术价值(分类)。按药物自然属性分类的本草著作有《本草经集注》、《新修本草》、《本草纲目》等，但作为首创该种分类法的是南北朝时期，梁代陶弘景撰著的《本草经集注》。注意“首创”关键词。");
        dao.insert(examination4);

        Examination examination5=new Examination();
        examination5.setTitle("《本草纲目拾遗》新增的药味数是");
        examination5.setSortType("中药学专业知识(一)");
        examination5.setSectionA("921种");
        examination5.setSectionB("730种");
        examination5.setSectionC("716种");
        examination5.setSectionD("850种");
        examination5.setSectionE("365种");
        examination5.setCorrectSection("C");
        examination5.setAnswer("本题考查历代本草代表作《本草纲目拾遗》的学术价值(新增药味数)。清代赵学敏的《本草纲目拾遗》创古代本草新增药物之冠，载药为921种，新增药就有716种。《本草经集注》730种，《新修本草》载药850种，《神农本草经》载药365种。");
        dao.insert(examination5);

        Examination examination6=new Examination();
        examination6.setTitle("不属于解表药使用注意的内容是");
        examination6.setSortType("中药学专业知识(二)");
        examination6.setSectionA("体虚汗多者忌服");
        examination6.setSectionB("淋病者慎用");
        examination6.setSectionC("热病津亏者忌服");
        examination6.setSectionD("疮疡初起兼表证者忌服");
        examination6.setSectionE("失血兼表证者慎服");
        examination6.setCorrectSection("D");
        examination6.setAnswer("本题考查解表药的使用注意。体虚汗多及热病津亏者忌服发汗力强的解表药；久患疮疡、淋病、失血兼表证者慎重使用发汗解表药；可用于疮疡初起兼表证者，故选D。");
        dao.insert(examination6);

        Examination examination7=new Examination();
        examination7.setTitle("发散风寒药的性味是");
        examination7.setSortType("中药学专业知识(二)");
        examination7.setSectionA("苦寒");
        examination7.setSectionB("苦温");
        examination7.setSectionC("辛凉");
        examination7.setSectionD("甘寒");
        examination7.setSectionE("辛温");
        examination7.setCorrectSection("E");
        dao.insert(examination7);

        Examination examination8=new Examination();
        examination8.setTitle("发散风热药的性味是");
        examination8.setSortType("中药学专业知识(二)");
        examination8.setSectionA("辛温");
        examination8.setSectionB("苦寒");
        examination8.setSectionC("辛凉");
        examination8.setSectionD("淡寒");
        examination8.setSectionE("甘温");
        examination8.setCorrectSection("C");
        dao.insert(examination8);

        Examination examination9=new Examination();
        examination9.setTitle("生用解表，蜜炙平喘的药物是");
        examination9.setSortType("中药学专业知识(二)");
        examination9.setSectionA("桂枝");
        examination9.setSectionB("麻黄");
        examination9.setSectionC("紫苏");
        examination9.setSectionD("荆芥");
        examination9.setSectionE("羌活");
        examination9.setCorrectSection("B");
        dao.insert(examination9);

        Examination examination10=new Examination();
        examination10.setTitle("麻黄配石膏共同体现的功效是");
        examination10.setSortType("中药学专业知识(二)");
        examination10.setSectionA("发汗解表");
        examination10.setSectionB("清热泻火");
        examination10.setSectionC("利水消肿");
        examination10.setSectionD("调和营卫");
        examination10.setSectionE("清肺平喘");
        examination10.setCorrectSection("E");
        dao.insert(examination10);


        Examination examination12=new Examination();
        examination12.setTitle("中医学的指导思想是");
        examination12.setSortType("中药学综合知识与技能");
        examination12.setSectionA("阴阳学说");
        examination12.setSectionB("五行学说五行学说");
        examination12.setSectionC("精气学说");
        examination12.setSectionD("整体观念");
        examination12.setSectionE("辨证论治");
        examination12.setCorrectSection("D");
        examination12.setAnswer("本题考查对中医学独特理论体系中指导思想的认识。中医学是一个具有独特理论的医学体系，这一理论体系的各个方面都充分体现着整体认识的方法，因此说“整体观念”是其指导思想。");
        dao.insert(examination12);

        Examination examination13=new Examination();
        examination13.setTitle("中医学认识疾病和治疗疾病的基本思路是");
        examination13.setSortType("中药学综合知识与技能");
        examination13.setSectionA("整体观念");
        examination13.setSectionB("恒动观念");
        examination13.setSectionC("同病异治");
        examination13.setSectionD("异病同治");
        examination13.setSectionE("辨证论治");
        examination13.setCorrectSection("E");
        examination13.setAnswer("本题考查对中医学独特理论体系中诊治特点的认识。中医学对疾病的理性认识过程就是“辨证”，治疗疾病的过程就是“论治”，所以说“辨证论治”是中医学认识疾病和治疗疾病的基本思路。");
        dao.insert(examination13);

        Examination examination14=new Examination();
        examination14.setTitle("根据中NN论，“症”“证”“病”含义不同，下列表述中属于“证”的是");
        examination14.setSortType("中药学综合知识与技能");
        examination14.setSectionA("气虚血瘀");
        examination14.setSectionB("心悸");
        examination14.setSectionC("胸痹");
        examination14.setSectionD("胸胁胀满");
        examination14.setSectionE("胸痛彻背");
        examination14.setCorrectSection("A");
        examination14.setAnswer("本题考查“症”“证”“病”的定义。“气虚血瘀”属于证，“胸胁胀满”“胸痛彻背”属于症，“胸痹”属于病，至于“心悸”本身是一个症状，但同时在中医学的范围内也可属病名的范畴。");
        dao.insert(examination14);

        Examination examination15=new Examination();
        examination15.setTitle("下列表述中属于证的是");
        examination15.setSortType("中药学综合知识与技能");
        examination15.setSectionA("水痘");
        examination15.setSectionB("麻疹");
        examination15.setSectionC("风寒犯肺");
        examination15.setSectionD("头痛");
        examination15.setSectionE("寒");
        examination15.setCorrectSection("C");
        examination15.setAnswer("本题主要考查对“症”“证”“病”含义的理解。水痘、麻疹属于病名，头痛、恶寒是具体的症状。");
        dao.insert(examination15);

        Examination examination16=new Examination();
        examination16.setTitle("下列表述中属于症的是");
        examination16.setSortType("中药学综合知识与技能");
        examination16.setSectionA("消渴");
        examination16.setSectionB("恶寒");
        examination16.setSectionC("肺痈");
        examination16.setSectionD("水肿");
        examination16.setSectionE("咳嗽");
        examination16.setCorrectSection("B");
        examination16.setAnswer("恶寒是疾病发展过程中的临床表现，属“症”的范畴。消渴、肺痈、水肿、咳嗽具有特定的症状和体征，属“病”的范畴，因此，本题的答案为B。");
        dao.insert(examination16);


        Examination examination19=new Examination();
        examination19.setTitle("下列表述药物剂型的重要性错误的是");
        examination19.setSortType("药学专业知识(一)");
        examination19.setSectionA("芳香水剂为液体剂型");
        examination19.setSectionB("颗粒剂为固体剂型");
        examination19.setSectionC("溶胶剂为半固体剂型");
        examination19.setSectionD("气雾剂为气体分散型");
        examination19.setSectionE("软膏剂为半固体剂型");
        examination19.setCorrectSection("C");
        examination19.setAnswer("溶胶剂属于胶体溶液。");
        dao.insert(examination19);


        Examination examination21=new Examination();
        examination21.setTitle("下列表述药物剂型的重要性错误的是");
        examination21.setSortType("药学专业知识(一)");
        examination21.setSectionA("剂型可改变药物的作用性质");
        examination21.setSectionB("剂型能改变药物的作用速度");
        examination21.setSectionC("改变剂型可降低(或消除)药物的毒副作用");
        examination21.setSectionD("剂型决定药物的治疗作用");
        examination21.setSectionE("剂型可影响疗效");
        examination21.setCorrectSection("D");
        examination21.setAnswer("本题考查药物剂型的重要性，药物剂型与给药途径、临床治疗效果有着十分密切的关系，药物剂型必须与给药途径相适应，良好的剂型可以发挥出良好的药效，但不是决定药物的治疗作用。故本题答案应选D。");
        dao.insert(examination21);

        Examination examination22=new Examination();
        examination22.setTitle("药物剂型进行分类的方法不包括");
        examination22.setSortType("药学专业知识(一)");
        examination22.setSectionA("按给药途径分类");
        examination22.setSectionB("按分散系统分类");
        examination22.setSectionC("按制法分类");
        examination22.setSectionD("按形态分类");
        examination22.setSectionE("按药物种类分类");
        examination22.setCorrectSection("E");
        examination22.setAnswer("本题考查药物剂型的五种分类方法，分别是按给药途径分类、按分散系统分类、按制法分类、按形态分类以及按作用时间分类。故本题答案应选E。");
        dao.insert(examination22);

        Examination examination23=new Examination();
        examination23.setTitle("制剂中药物的化学降解途径不包括");
        examination23.setSortType("药学专业知识(一)");
        examination23.setSectionA("水解");
        examination23.setSectionB("氧化");
        examination23.setSectionC("结晶");
        examination23.setSectionD("脱羧");
        examination23.setSectionE("异构化");
        examination23.setCorrectSection("C");
        dao.insert(examination23);

        Examination examination24=new Examination();
        examination24.setTitle("地西泮的适应证不包括");
        examination24.setSortType("药学专业知识(二)");
        examination24.setSectionA("镇静催眠");
        examination24.setSectionB("抗癫痫和抗惊厥");
        examination24.setSectionC("紧张性头痛");
        examination24.setSectionD("特发性震颤");
        examination24.setSectionE("三叉神经痛");
        examination24.setCorrectSection("E");
        dao.insert(examination24);

        Examination examination25=new Examination();
        examination25.setTitle("老年人对苯二氮革类药较为敏感，用药后可致平衡功能失调，觉醒后可发生步履蹒跚、思维迟钝等症状，在临床上被称为");
        examination25.setSortType("药学专业知识(二)");
        examination25.setSectionA("震颤麻痹综合征");
        examination25.setSectionB("老年期痴呆");
        examination25.setSectionC("“宿醉”现象");
        examination25.setSectionD("戒断综合征");
        examination25.setSectionE("锥体外系反应");
        examination25.setCorrectSection("C");
        examination25.setAnswer("本题考查苯二氮棹类镇静催眠药的用药监护。关注老年人对苯二氮革类的敏感性和“宿醉”现象。(1)静脉注射——更易出现呼吸抑制、低血压、心动过缓甚至心跳停止。(2)老年人对苯二氮棹类药物较敏感——服药后可产生过度镇静、肌肉松弛作用，觉醒后可发生震颤、思维迟缓、运动障碍、认知功能障碍、步履蹒跚、肌无力等“宿醉”现象，极易跌倒和受伤。因此，必须告知患者晨起时宜小心，避免跌倒。故答案选C。 ");
        dao.insert(examination25);

        Examination examination26=new Examination();
        examination26.setTitle("关于苯二氮革类镇静催眠药的叙述，不正确的是");
        examination26.setSortType("药学专业知识(二)");
        examination26.setSectionA("是目前最常用的镇静催眠药");
        examination26.setSectionB("临床上用于治疗焦虑症");
        examination26.setSectionC("可用于心脏电复律前给药");
        examination26.setSectionD("可用于治疗小儿高热惊厥");
        examination26.setSectionE("长期应用不会产生依赖性和成瘾性");
        examination26.setCorrectSection("E");
        dao.insert(examination26);

        Examination examination27=new Examination();
        examination27.setTitle("地西泮的药理作用不包括");
        examination27.setSortType("药学专业知识(二)");
        examination27.setSectionA("抗精神分裂症作用");
        examination27.setSectionB("抗惊厥作用");
        examination27.setSectionC("抗癫痫作用");
        examination27.setSectionD("中枢性肌松作用");
        examination27.setSectionE("抗焦虑作用");
        examination27.setCorrectSection("A");
        dao.insert(examination27);

        Examination examination28=new Examination();
        examination28.setTitle("关于地西泮作用特点的描述，不正确的是");
        examination28.setSortType("药学专业知识(二)");
        examination28.setSectionA("小于镇静剂量时即有抗焦虑作用");
        examination28.setSectionB("剂量加大可引起麻醉");
        examination28.setSectionC("镇静催眠作用强");
        examination28.setSectionD("可引起暂时性记忆缺失");
        examination28.setSectionE("有良好的抗癫痫作用");
        examination28.setCorrectSection("B");
        dao.insert(examination28);

        Examination examination29=new Examination();
        examination29.setTitle("有关药学服务(PS)的目的，以下说法最正确的是");
        examination29.setSortType("药学综合知识与技能");
        examination29.setSectionA("提高患者用药的方便性");
        examination29.setSectionB("提高患者用药的有效性");
        examination29.setSectionC("提高患者用药的经济性");
        examination29.setSectionD("提高患者用药的安全性");
        examination29.setSectionE("改善和提高人类生活质量");
        examination29.setCorrectSection("E");
        dao.insert(examination29);

        Examination examination30=new Examination();
        examination30.setTitle("有关药学服务的“基本要素”，以下说法最正确的是");
        examination30.setSortType("药学综合知识与技能");
        examination30.setSectionA("为公众服务");
        examination30.setSectionB("提供药学专业知识");
        examination30.setSectionC("“与药物有关”的服务");
        examination30.setSectionD("提供药物信息和知识");
        examination30.setSectionE("以提供信息和知识的形式服务");
        examination30.setCorrectSection("C");
        examination30.setAnswer("本题考查药学服务的“基本要素”。依据药学服务的含义“药师应用药学专业知识向公众提供直接的、负责任的、与药物应用有关的服．务，以期提高药物治疗的安全、有效、经济和适宜性”可知，药学服务的“基本要素”是“与药物有关”的服务(C)。本题的“陷阱”是其余备选答案都包括“知识”、“服务”等貌似可选择的关键词。");
        dao.insert(examination30);

        Examination examination31=new Examination();
        examination31.setTitle("药学服务的对象是");
        examination31.setSortType("药学综合知识与技能");
        examination31.setSectionA("患者");
        examination31.setSectionB("患者家属");
        examination31.setSectionC("医务人员");
        examination31.setSectionD("广大公众");
        examination31.setSectionE("护理人员");
        examination31.setCorrectSection("D");
        dao.insert(examination31);

        Examination examination32=new Examination();
        examination32.setTitle("“药学服务具有很强的社会属性”，其中的含义指“药学服务的对象”是");
        examination32.setSortType("药学综合知识与技能");
        examination32.setSectionA("住院患者");
        examination32.setSectionB("门诊患者");
        examination32.setSectionC("家庭患者");
        examination32.setSectionD("社区患者");
        examination32.setSectionE("全社会所有用药的患者");
        examination32.setCorrectSection("E");
        dao.insert(examination32);

        Examination examination33=new Examination();
        examination33.setTitle("药学服务是药师对患者的关怀和责任。下列人员中，属于药学服务重要人群的是");
        examination33.setSortType("药学综合知识与技能");
        examination33.setSectionA("门诊患者");
        examination33.setSectionB("社区患者");
        examination33.setSectionC("住院患者");
        examination33.setSectionD("过敏体质者");
        examination33.setSectionE("药品消费者");
        examination33.setCorrectSection("D");
        examination33.setAnswer("本题考查药学服务的重要人群。备选答案的“陷阱”是混淆药学服务的对象(广大公众)与“重要人群”。在“门诊患者”、“社区患者”、“住院患者”、“药品消费者”中，都会包含药学服务的“重要人群”，但却不都是“重要人群”。而“过敏体质者”(D)则都是药学服务的重要人群。");
        dao.insert(examination33);



    }

}
