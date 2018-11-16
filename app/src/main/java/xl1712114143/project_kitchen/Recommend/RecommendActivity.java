package xl1712114143.project_kitchen.Recommend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import xl1712114143.project_kitchen.MainActivity;
import xl1712114143.project_kitchen.R;

public class RecommendActivity extends AppCompatActivity {
    int[] images = new int[] { R.drawable.gongbaojiding, R.drawable.hualiuyupian, R.drawable.tangculiyu};
    String[] names = new String[] { "宫保鸡丁","滑溜鱼片","糖醋鲤鱼"};
    String[] briefs = new String[] { "家常菜","家常菜","家常菜"};
    String[] contents = new String[] {
            "\n原料：\n\t\t\t脯肉（225克）、花生米（50克）。辅料：葱（45克）、姜（10克）。调料：干辣椒（8克）、花椒（1.5克）、色拉油（60克）、食盐（2克）、料酒（2克）、味精（1克）、酱油（6.5克）、白糖（10克）、醋（7克）、水淀粉（22克）。\n制作步骤：\n\t\t\t1.将鸡脯肉用刀背拍一下，切成小丁，加入一汤匙料酒、半汤匙食用油、半茶匙白胡椒、半茶匙盐，一茶匙淀粉腌渍10分钟，再用水淀粉拌匀。\n" + "将大葱洗净切段，干辣椒洗净，剪去两头去除辣椒籽，黄瓜切丁。\n\t\t\t2.在小碗中调入酱油、香醋、盐、姜汁、白砂糖和料酒，混合均匀制成调味料汁。\n\t\t\t3.锅中留底油，烧热后将花椒和干辣椒放入，用小火煸炸出香味，随后放入大葱段。\n\t\t\t4.放入鸡丁，放1汤匙料酒，将鸡丁滑炒变色，然后倒入水淀粉。\n\t\t\t5.最后调入料汁，再放入熟花生米，翻炒均匀，用水淀粉勾芡即成。",
            "\n原料：\n\t\t\t主料：偏口鱼450克。冬笋25克、木耳25克\n\t\t\t" + "配料：鸡蛋清25克。绍酒20克、精盐4克、湿淀粉50克。花生油100克、葱10克、蒜5克、芝麻油10克\n制作步骤：\n\t\t\t1、将偏口鱼肉洗净，劈成长5厘米、宽3厘米、厚0.2厘米的片放入碗内，加入绍酒、精盐、调匀入味。再放入鸡蛋清，湿淀粉拌匀。冬笋切成片，待用。\n\t\t\t2、炒锅内加入花生油，置中火上烧至四成热（约100℃）时，将鱼片逐步下锅滑熟呈白色，捞出沥油。\n\t\t\t3、炒锅内留油，中火烧至六成热（约150℃）加葱、蒜偏炒，放进绍酒一烹，加入清汤、精盐、冬笋片、木耳烧开，撇去浮沫。\n\t\t\t4、放入鱼片用小火煨透，用湿淀粉勾成薄芡，淋上芝麻油，装盘即可。",
            "\n原料：\n\t\t\t鲤鱼750克，白糖200克，酱油、料酒各10克，清汤300克，花生油1500克，葱、姜各2克，醋120克，蒜茸、精盐各3克，湿淀粉100克。\n做法：\n\t\t\t1.鲤鱼去鳞、内脏、两腮，鱼身两侧每2.5厘米直剞后斜剞成翻刀，提起鱼尾使刀口张开，料酒、精盐撒入刀口稍腌。\n\t\t\t" + "2.清汤、酱油、料酒、醋、白糖、精盐、湿淀粉对成芡汁。\n\t\t\t" + "3.在刀口处撒上湿淀粉后，放在七成热的油中炸至外皮变硬，移微火浸炸3分钟，再上旺火炸至金黄色，捞出摆盘，用手将鱼捏松。\n\t\t\t" + "4.将葱、姜、蒜放入锅中炸出香味后倒入对好的芡汁，起泡时用炸鱼的沸油冲入汁内，加以略炒迅速烧到鱼上即可。",};

    private ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_recommend);
        myList = (ListView) findViewById(R.id.recommendList);
        ArrayList<Map<String, String>> sceneryList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, String> sceneryItem = new HashMap<String, String>();
            sceneryItem.put("name", names[i]);
            sceneryItem.put("brief", briefs[i]);
            sceneryItem.put("image", images[i] + "");
            sceneryList.add(sceneryItem);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, sceneryList,
                R.layout.recommend_item,
                new String[] { "image", "name", "brief" }, new int[] {
                R.id.image, R.id.name, R.id.brief });
        myList.setAdapter(adapter);
        myList.setOnItemClickListener(new myOnItemClickListener());

    }
    private class myOnItemClickListener implements OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Intent intent = new Intent();
            intent.setClass(RecommendActivity.this,RecommendShowActivity.class);
            intent.putExtra("image", images[position]);
            intent.putExtra("content", contents[position]);
            startActivity(intent);
        }
    }
}
