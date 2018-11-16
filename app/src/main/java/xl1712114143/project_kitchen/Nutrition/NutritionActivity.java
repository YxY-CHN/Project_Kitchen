package xl1712114143.project_kitchen.Nutrition;

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

public class NutritionActivity extends AppCompatActivity {
    int[] images = new int[] { R.drawable.jidanbuding, R.drawable.niunaijidangeng, R.drawable.suchaoxilanhua};
    String[] names = new String[] { "鸡蛋布丁","牛奶鸡蛋羹","素炒西兰花"};
    String[] briefs = new String[] {"营养餐","营养餐","营养餐" };
    String[] contents = new String[] {
            "\n原料：\n\t\t\t鲜奶400克，蛋500克。各式水果丁适量,砂糖300克，香草粉少许。\n做法：\n\t\t\t1、准备白糖30g，牛奶500ml。\n\t\t\t2、牛奶用温火加热，把白糖放进去，糖完全融解了就关火晾凉。\n\t\t\t" + "3、鸡蛋四个，搅拌均匀。\n\t\t\t" + "4、筛过2-3遍，让蛋液更细腻不含杂质。\n\t\t\t" + "5、将蛋液加入放凉的牛奶中。\n\t\t\t" + "6、加一勺香草精，搅拌均匀。\n\t\t\t" + "7、蛋壳外边用锡纸包一下，底部做成平的，这样一个是让蛋壳在烤箱里可以放的平稳，也同时为了保护蛋壳在烤制的时候不会裂开。\n\t\t\t" + "8、把蛋液倒入蛋壳中。\n\t\t\t" + "9、烤箱180度，上下火，约20分钟左右，布丁凝固即可。装不到蛋壳里的鸡蛋布丁液可以装到其他的烤碗中烤制就行。",
            "\n原料：\n\t\t\t鸡蛋1个、牛奶150ML、盐1小勺、橄榄油、食醋少许。\n做法：\n\t\t\t1、鸡蛋洗干净后破壳后加入盐1小勺和少许橄榄油，搅打均匀，最好用蛋抽子，会较容易；\n\t\t\t" + "2、在蛋液中慢慢注入牛奶，继续搅打均匀；\n\t\t\t" + "3、用细筛网将蛋液慢慢过滤两遍，滤去泡沫，将蛋液慢慢倒入炖盅中。没有筛网可以将蛋液静止放置一段时间，没有炖盅的可以倒入碗内，上覆保鲜膜，再用牙签略扎几个小眼；\n\t\t\t" + "4、碗中加入水烧开后，放入炖盅，注意水要到炖盅深度的一半就好，盖上盖子，隔水炖5分钟即可。",
            "\n原料：\n\t\t\t西兰花(250克)，蘑菇(鲜蘑)(100克)，胡萝卜(50克)，大蒜(5克)，盐(2克)，味精(1克)，植物油(20克)\n做法：\n\t\t\t1.西兰花切小朵；大蒜剥去蒜皮，拍碎剁成蒜末；\n\t\t\t" + "2.胡萝卜去皮洗净，切片；\n\t\t\t" + "3.西兰花、蘑菇、胡萝卜放入开水中氽烫30秒捞出；\n\t\t\t" + "4.锅中倒入20克油烧热，把蒜末爆香，再放入西兰花、洋菇、胡萝卜炒匀；\n\t\t\t" + "5.放入调味料（盐、味精）即可。"};

    private ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_nutrition);
        myList = (ListView) findViewById(R.id.nutritionList);
        ArrayList<Map<String, String>> sceneryList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, String> sceneryItem = new HashMap<String, String>();
            sceneryItem.put("name", names[i]);
            sceneryItem.put("brief", briefs[i]);
            sceneryItem.put("image", images[i] + "");
            sceneryList.add(sceneryItem);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, sceneryList,
                R.layout.nutrition_item,
                new String[] { "image", "name", "brief" }, new int[] {
                R.id.image, R.id.name, R.id.brief });
        myList.setAdapter(adapter);
        myList.setOnItemClickListener(new myOnItemClickListener());

    }
    private class myOnItemClickListener implements OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Intent intent = new Intent();
            intent.setClass(NutritionActivity.this, NutritionShowActivity.class);
            intent.putExtra("image", images[position]);
            intent.putExtra("content", contents[position]);
            startActivity(intent);
        }
    }
}

