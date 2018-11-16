package xl1712114143.project_kitchen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import xl1712114143.project_kitchen.Add.ShowItem;
import xl1712114143.project_kitchen.Nutrition.NutritionActivity;
import xl1712114143.project_kitchen.Recommend.RecommendActivity;

public class Fragment_kitchen extends Fragment implements OnBannerListener {
    private Banner banner;
    private ArrayList<Integer> list_path;
    private ArrayList<String> list_title;
    private WrapContentListView myList;
    private LinearLayout recommend;
    private LinearLayout nutrition;
    private LinearLayout newProduct;
    private SearchView searchView;
    private SimpleAdapter adapter;

    int[] images = new int[]{R.drawable.bazirou, R.drawable.gongbaojiding, R.drawable.jidanbuding, R.drawable.hualiuyupian, R.drawable.jiachangdoufu, R.drawable.niunaijidangeng, R.drawable.mapodoufu, R.drawable.shuizhuroupian, R.drawable.tangculiyu, R.drawable.yuxiangrousi, R.drawable.suchaoxilanhua, R.drawable.suchaoxilanhua};
    String[] names = new String[]{"把子肉", "宫保鸡丁", "鸡蛋布丁", "滑溜鱼片", "家常豆腐", "牛奶鸡蛋羹", "麻婆豆腐", "水煮肉片", "糖醋鲤鱼", "鱼香肉丝", "素炒西兰花", "test"};
    String[] briefs = new String[]{"家常菜", "家常菜", "营养餐", "家常菜", "家常菜", "营养餐", "家常菜", "家常菜", "家常菜", "家常菜", "营养餐", "test"};
    String[] contents = new String[]{"\n原料：\n\t\t\t五花肉（长15cm，厚1.5cm)15千克，四喜丸子(只重50克）100个，鸡蛋角（只重60克）60个，海带结（只重100克）100个，面筋肋（每把 50克）80把，豆筋（每根100克）120根，兰花干（每块70克）70块，素鸡片（片重60克）180片，各种调料适量。\n制作步骤：\n\t\t\t1．做把子肉，要选80斤左右的白条猪,不肥不瘦。一斤切八块，蒲草捆好。冒两遍,二遍血水清汤。放入坛子，不放盐全靠酱油调味,猛火开锅文火炖，炖好的把子肉肥的不腻、瘦的不柴。入口有醇厚的余香,掉在地上，象豆腐一样摔的稀碎。\n\t\t\t2．徐州的把子肉是选取有肥有瘦的猪肉,切成长条，用麻绳捆成一把,煮好，再放在酱油中炖。\n\t\t\t3．把子肉的精彩便是有肥肉的存在才能产生出肥而不腻的上佳口感。\n\t\t\t4．北方的“把子肉“却不加糖,长方形的大块，只是酱油八角在高筒瓦罐中炖熟。火候到处,一启封香气四溢。趁热连肉带汁浇在白米饭上，亦十分甘美。", "\n原料：\n\t\t\t脯肉（225克）、花生米（50克）。辅料：葱（45克）、姜（10克）。调料：干辣椒（8克）、花椒（1.5克）、色拉油（60克）、食盐（2克）、料酒（2克）、味精（1克）、酱油（6.5克）、白糖（10克）、醋（7克）、水淀粉（22克）。\n制作步骤：\n\t\t\t1.将鸡脯肉用刀背拍一下，切成小丁，加入一汤匙料酒、半汤匙食用油、半茶匙白胡椒、半茶匙盐，一茶匙淀粉腌渍10分钟，再用水淀粉拌匀。\n" + "将大葱洗净切段，干辣椒洗净，剪去两头去除辣椒籽，黄瓜切丁。\n\t\t\t2.在小碗中调入酱油、香醋、盐、姜汁、白砂糖和料酒，混合均匀制成调味料汁。\n\t\t\t3.锅中留底油，烧热后将花椒和干辣椒放入，用小火煸炸出香味，随后放入大葱段。\n\t\t\t4.放入鸡丁，放1汤匙料酒，将鸡丁滑炒变色，然后倒入水淀粉。\n\t\t\t5.最后调入料汁，再放入熟花生米，翻炒均匀，用水淀粉勾芡即成。", "\n原料：\n\t\t\t鲜奶400克，蛋500克。各式水果丁适量,砂糖300克，香草粉少许。\n做法：\n\t\t\t1、准备白糖30g，牛奶500ml。\n\t\t\t2、牛奶用温火加热，把白糖放进去，糖完全融解了就关火晾凉。\n\t\t\t" + "3、鸡蛋四个，搅拌均匀。\n\t\t\t" + "4、筛过2-3遍，让蛋液更细腻不含杂质。\n\t\t\t" + "5、将蛋液加入放凉的牛奶中。\n\t\t\t" + "6、加一勺香草精，搅拌均匀。\n\t\t\t" + "7、蛋壳外边用锡纸包一下，底部做成平的，这样一个是让蛋壳在烤箱里可以放的平稳，也同时为了保护蛋壳在烤制的时候不会裂开。\n\t\t\t" + "8、把蛋液倒入蛋壳中。\n\t\t\t" + "9、烤箱180度，上下火，约20分钟左右，布丁凝固即可。装不到蛋壳里的鸡蛋布丁液可以装到其他的烤碗中烤制就行。", "\n原料：\n\t\t\t主料：偏口鱼450克。冬笋25克、木耳25克\n\t\t\t" + "配料：鸡蛋清25克。绍酒20克、精盐4克、湿淀粉50克。花生油100克、葱10克、蒜5克、芝麻油10克\n制作步骤：\n\t\t\t1、将偏口鱼肉洗净，劈成长5厘米、宽3厘米、厚0.2厘米的片放入碗内，加入绍酒、精盐、调匀入味。再放入鸡蛋清，湿淀粉拌匀。冬笋切成片，待用。\n\t\t\t2、炒锅内加入花生油，置中火上烧至四成热（约100℃）时，将鱼片逐步下锅滑熟呈白色，捞出沥油。\n\t\t\t3、炒锅内留油，中火烧至六成热（约150℃）加葱、蒜偏炒，放进绍酒一烹，加入清汤、精盐、冬笋片、木耳烧开，撇去浮沫。\n\t\t\t4、放入鱼片用小火煨透，用湿淀粉勾成薄芡，淋上芝麻油，装盘即可。", "\n原料：\n\t\t\t豆腐(700克)猪肉(肥瘦)(100克)青蒜(50克)猪油（炼制）(100克)料酒(25克) 盐(7克)豆瓣辣酱(50克)味精(2克)香油(15克)\n做法：\n\t\t\t1、豆腐改成5厘米宽的对角的三角形，1厘米厚的片，用平盘装上，撒上盐腌一下，滗去水分；\n\t\t\t" + "2、 猪肉剁成末；\n\t\t\t" + "3、大蒜切成2厘米的段；\n\t\t\t" + "4、 豆瓣辣酱要剁碎；\n\t\t\t" + "5. 将猪油烧沸后下入豆腐，待两面都煎黄后就取出；\n\t\t\t" + "6、 另外，再将油烧沸，下入猪肉末炒熟，烹料酒，加入豆瓣辣酱炒香；\n\t\t\t" + "7、 再加入豆腐、盐、味精和汤，焖入味，收干汁，放入大蒜和香油，装入盘内，即可。\n\n\t\t\t" + "小提示：水别放太少，鸡精多些味道更好，豆腐一定要慢火烧透才入味。", "\n原料：\n\t\t\t鸡蛋1个、牛奶150ML、盐1小勺、橄榄油、食醋少许。\n做法：\n\t\t\t1、鸡蛋洗干净后破壳后加入盐1小勺和少许橄榄油，搅打均匀，最好用蛋抽子，会较容易；\n\t\t\t" + "2、在蛋液中慢慢注入牛奶，继续搅打均匀；\n\t\t\t" + "3、用细筛网将蛋液慢慢过滤两遍，滤去泡沫，将蛋液慢慢倒入炖盅中。没有筛网可以将蛋液静止放置一段时间，没有炖盅的可以倒入碗内，上覆保鲜膜，再用牙签略扎几个小眼；\n\t\t\t" + "4、碗中加入水烧开后，放入炖盅，注意水要到炖盅深度的一半就好，盖上盖子，隔水炖5分钟即可。", "\n原料：\n\t\t\t豆腐（250克）、牛肉（75克）、蒜苗（1根）、豆豉（15克）、花椒粉（3克）、黄酒（5克）、盐（3克）、豌豆淀粉（5克）、花生油（15克）、小葱（5克）等。\n做法：\n\t\t\t1.将豆腐切成2厘米见方的块，放入加了少许盐的沸水中氽一下，去除豆腥味，捞出用清水浸泡；豆豉等剁碎，蒜苗切段，姜切末；\n\t\t\t" + "2.炒锅烧热，放油，放入牛肉馅炒散；待牛肉馅炒成金黄色，放入豆瓣酱同炒；放入豆豉、姜末、辣椒粉同炒至牛肉上色；\n\t\t\t" + "3.下肉汤煮沸；放入豆腐煮3分钟；加酱油、蒜苗段、糖，用盐调味，再用湿淀粉勾芡；盛出后撒上花椒面、葱末即可。", "\n原料：\n\t\t\t猪里脊肉(150克)白菜(50克)鸡蛋(30克)胡椒(3克)豆瓣酱(10克) 姜(10克)大葱(10克)辣椒(红、尖、干)(5克)花椒(5克)酱油(10克)料酒(8克)味精(5克) 盐(10克)淀粉(豌豆)(10克)植物油(50克)\n做法：\n\t\t\t1. 将猪里脊肉切片，鸡蛋清和淀粉、盐、味精、料酒调匀成糊，涂抹在肉片上；\n\t\t\t" + "2. 白菜叶、姜洗净切片，葱白切段；\n\t\t\t" + "3. 将35克植物油入锅，烧热，倒入花椒、干辣椒慢火炸，待辣椒呈金黄色捞出；\n\t\t\t" + "4. 然后，将辣椒、花椒切成细末；\n\t\t\t" + "5. 用锅中油爆炒豆瓣辣酱，然后将白菜叶、葱白、姜、肉汤、酱油、胡椒粉、料酒、鸡精等调料放入，略搅几下，使之调匀；\n\t\t\t" + "6. 随即放入肉片，再炖，肉片熟后，将肉片盛起，将剁碎的干辣椒、花椒末撒上；\n\t\t\t" + "7. 用剩余的植物油烧开，淋在肉片上，使热油把干辣椒、花椒粉、肉片再炸一下，即可使麻、辣、浓香四溢。\n\n小提示：\n\t\t\t1.干辣椒和花椒记得要炸至棕红色，炸之后油还可以炒蔬菜。\n\t\t\t" + "2.豆瓣酱要记得翻炒出红油，而且最后的油要烧的特别的热，这样才能够将蒜末的香味逼出。", "\n原料：\n\t\t\t鲤鱼750克，白糖200克，酱油、料酒各10克，清汤300克，花生油1500克，葱、姜各2克，醋120克，蒜茸、精盐各3克，湿淀粉100克。\n做法：\n\t\t\t1.鲤鱼去鳞、内脏、两腮，鱼身两侧每2.5厘米直剞后斜剞成翻刀，提起鱼尾使刀口张开，料酒、精盐撒入刀口稍腌。\n\t\t\t" + "2.清汤、酱油、料酒、醋、白糖、精盐、湿淀粉对成芡汁。\n\t\t\t" + "3.在刀口处撒上湿淀粉后，放在七成热的油中炸至外皮变硬，移微火浸炸3分钟，再上旺火炸至金黄色，捞出摆盘，用手将鱼捏松。\n\t\t\t" + "4.将葱、姜、蒜放入锅中炸出香味后倒入对好的芡汁，起泡时用炸鱼的沸油冲入汁内，加以略炒迅速烧到鱼上即可。", "\n原料：\n\t\t\t猪肉、黑木耳、玉兰片、葱、蒜、姜、泡辣椒、肉汤（或水）。\n做法：\n\t\t\t1.将泡发的黑木耳切丝，玉兰片（可选）切丝，猪瘦肉切丝，葱姜蒜切小粒，泡椒剁细；\n\t\t\t" + "2.将肉丝用少许盐和淀粉将肉丝腌制五到十分钟，并将淀粉、盐、白糖、醋、生抽、肉汤（或水）兑成芡汁；\n\t\t\t" + "3.热锅下冷油，油温热后，下肉丝迅速划炒散开；\n\t\t\t" + "4.肉丝炒散至发白后，迅速放入姜、蒜米、泡椒；\n\t\t\t" + "5.炒至出香味并呈红色时下葱花、木耳、豌豆尖、玉兰片炒匀；\n\t\t\t" + "6.下芡汁收汁起锅即可。", "\n原料：\n\t\t\t西兰花(250克)，蘑菇(鲜蘑)(100克)，胡萝卜(50克)，大蒜(5克)，盐(2克)，味精(1克)，植物油(20克)\n做法：\n\t\t\t1.西兰花切小朵；大蒜剥去蒜皮，拍碎剁成蒜末；\n\t\t\t" + "2.胡萝卜去皮洗净，切片；\n\t\t\t" + "3.西兰花、蘑菇、胡萝卜放入开水中氽烫30秒捞出；\n\t\t\t" + "4.锅中倒入20克油烧热，把蒜末爆香，再放入西兰花、洋菇、胡萝卜炒匀；\n\t\t\t" + "5.放入调味料（盐、味精）即可。", "test"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kitchen, container, false);


        recommend = view.findViewById(R.id.recommend);
        nutrition = view.findViewById(R.id.nutrition);
        newProduct = view.findViewById(R.id.newProduct);
        myList = view.findViewById(R.id.kitchenList);
        banner = view.findViewById(R.id.banner);
        searchView = view.findViewById(R.id.searchView);
        myList.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, names));
        myList.setTextFilterEnabled(true);


        //SearchView控制
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
                    myList.setFilterText(newText);
                } else {
                    myList.clearTextFilter();
                }
                return false;
            }
        });


        //ListView控制
        ArrayList<Map<String, String>> sceneryList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, String> sceneryItem = new HashMap<String, String>();
            sceneryItem.put("name", names[i]);
            sceneryItem.put("brief", briefs[i]);
            sceneryItem.put("content", contents[i]);
            sceneryItem.put("image", images[i] + "");
            sceneryList.add(sceneryItem);

            adapter = new SimpleAdapter(getContext(), sceneryList, R.layout.kitchen_item, new String[]{"image", "name", "brief"}, new int[]{R.id.image, R.id.name, R.id.brief});
            myList.setAdapter(adapter);
            myList.setOnItemClickListener(new myOnItemClickListener());
        }
        initView();
        return view;
    }


    private class myOnItemClickListener implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String, String> map = (Map<String, String>) adapter.getItem(position);
            Intent intent = new Intent();
            intent.setClass(getContext(), KitchenShowActivity.class);
            intent.putExtra("image", Integer.parseInt(map.get("image")));
            intent.putExtra("content", map.get("content"));
            startActivity(intent);
        }
    }


    //轮播图控制

    private void initView() {
        //放图片地址的集合
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();

        list_path.add(R.drawable.gongbaojiding);
        list_path.add(R.drawable.jidanbuding);
        list_path.add(R.drawable.shuizhuroupian);
        list_path.add(R.drawable.suchaoxilanhua);
        list_path.add(R.drawable.bazirou);
        list_title.add("闻名中外特色美食");
        list_title.add("饭后甜点吹弹可破");
        list_title.add("新创名菜川菜家常");
        list_title.add("绿色营养饮食必备");
        list_title.add("中华小吃水乳交融");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();


    }

    //轮播图的监听
    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getContext(), "这是第" + position + "张轮播图", Toast.LENGTH_SHORT).show();
        Log.i("tag", "你点了第" + position + "张轮播图");
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
//            Picasso.with(context).load((R.drawable.black_background) path).resize(16*20,9*20).into(imageView);
//            imageView.setImageResource(R.drawable.black_background);
            Picasso.with(context).load((int) path).into(imageView);
        }
    }


    //3个选项控制跳转
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RecommendActivity.class);
                startActivity(intent);
            }
        });
        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NutritionActivity.class);
                startActivity(intent);
            }
        });
        newProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShowItem.class);
                startActivity(intent);
            }
        });
    }

}

