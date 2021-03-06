package com.example.pepepicsextended;

import android.graphics.Bitmap;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.os.FileObserver;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yuyakaido.android.cardstackview.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    static int maxLinks = 10;
    int had = 0;
    public static String[] links = new String[10];
    Bitmap image;
    ImageView pepe;

    List<RowItem> items = new ArrayList<>();

    private CardStackLayoutManager manager;
    private CustomListViewAdapter adapter;

    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int i;

    String TAG = "tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        initializeCards();

    }

    private List<RowItem> addList() {
        List<RowItem> items = new ArrayList<>();

        items.add(new RowItem("Pepe","https://external-preview.redd.it/1RYAwUdiRnc3uAlpxXteyZY2cGcvwJwTwpQjISGwrNw.png?auto=webp&s=c79a217254de72a64ae2632c82aacc649f4acb4a"));
        items.add(new RowItem("Pepe2","https://png.pngitem.com/pimgs/s/107-1078027_pepe-meme-rarepepe-terrorist-football-pepe-the-frog.png"));
        items.add(new RowItem("Pepe3","https://i.imgflip.com/31tu3r.png"));
        items.add(new RowItem("Pepe4","https://pngimg.com/uploads/snoop_dogg/snoop_dogg_PNG20.png"));

        ArrayList<String> cachedPepes = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        for(int i=0;i<10;i++) {
            int currentID = (int) (Math.random() * 15000);

            String pepeName = getRandName(builder);

            File pepePic = new File("E:/frens/Frens-LessDuplicates/abcd.jpg");
            System.out.println(pepePic.exists());

            items.add(new RowItem(pepeName,"https://i.imgflip.com/31tu3r.png"));
            builder.delete(0,builder.length());
        }
        items.add(new RowItem("Pepe","https://external-preview.redd.it/1RYAwUdiRnc3uAlpxXteyZY2cGcvwJwTwpQjISGwrNw.png?ato=webp&s=c79a217254de72a64ae2632c82aacc649f4acb4au"));

        return items;
    }

    public String getRandName(StringBuilder builder)
    {
        int currentID = (int) (Math.random() * 15000);
        builder.append(currentID);
        builder.append("_randomPepe_");
        builder.append(Integer.toHexString(currentID));

        return builder.toString();
    }


    public void cacheFrogs() {
        for(int i = 0; i<9; i++) {
            Glide.with(this)
                    .load(links[i])
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .preload();
        }
    }

    public void loadFrog(String imageLink) {
        int randInt = (int) (Math.random() * maxLinks);
        for(int i = randInt;i==had;i=randInt) {
            System.out.println(randInt);
            randInt = (int) (Math.random() * maxLinks);
        }

        System.out.println(randInt);
        final int finalRandInt = randInt;

        had = randInt;

        Glide.with(this).load(MainActivity.links[randInt]).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(pepe);
    }

    public void init() {
        links[0] = "smb://DESKTOP-EB6M3NH/Frens-LessDuplicates/Frens/00001_randomPepe_0x1.jpg";
        links[1] = "https://pngimg.com/uploads/snoop_dogg/snoop_dogg_PNG20.png";
        links[2] = "https://external-preview.redd.it/1RYAwUdiRnc3uAlpxXteyZY2cGcvwJwTwpQjISGwrNw.png?auto=webp&s=c79a217254de72a64ae2632c82aacc649f4acb4a";
        links[3] = "https://png.pngitem.com/pimgs/s/107-1078027_pepe-meme-rarepepe-terrorist-football-pepe-the-frog.png";
        links[4] = "https://www.freeiconspng.com/uploads/pepe-transparent-png-8.png";
        links[5] = "https://i.imgflip.com/31tu3r.png";
        links[6] = "https://images.gutefrage.net/media/fragen/bilder/wieso-darf-man-auf-manchen-seiten-pepe-nicht-als-profilbild-nehmen/0_big.png?v=1553616829000";
        links[7] = "https://assets.popbuzz.com/2017/08/rare-pepe-economy-1487773339-list-handheld-0.png";
        links[8] = "https://cdn.wallpapersafari.com/94/31/wHOvMm.jpg";
        links[9] = "https://www.westfalen-blatt.de/var/storage/images/wb/startseite/owl/lokales/kreis-guetersloh/verl/2629384-weihnachtsmarkt-im-verler-gymnasium-bietet-ein-aussergewoehnliches-programm-schueler-lassen-seifenblasen-brennen/75384262-1-ger-DE/Weihnachtsmarkt-im-Verler-Gymnasium-bietet-ein-aussergewoehnliches-Programm-Schueler-lassen-Seifenblasen-brennen_image_630_420f_wn.jpg";

        //cacheFrogs();
    }

    private void paginate() {
        List<RowItem> old = adapter.getItems();
        List<RowItem> baru = new ArrayList<>(addList());
        CardStackCallback callback = new CardStackCallback(old, baru);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callback);
        adapter.setItems(baru);
        hasil.dispatchUpdatesTo(adapter);
    }

    public void initializeCards() {
        final CardStackView cardStackView = findViewById(R.id.pepeStack);

        manager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                //Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {
                //Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + " d=" + direction);
                if (direction == Direction.Right){
                    Toast.makeText(MainActivity.this, "Direction Right", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Top){
                    Toast.makeText(MainActivity.this, "Direction Top", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Left){
                    Toast.makeText(MainActivity.this, "Direction Left", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Bottom){
                    Toast.makeText(MainActivity.this, "Direction Bottom", Toast.LENGTH_SHORT).show();
                }

                // Paginating
                if (manager.getTopPosition() == adapter.getItemCount() - 5){
                        //paginate();
                }
                if(manager.getTopPosition() == adapter.getItemCount()-1) {
                    cardStackView.setVisibility(View.GONE);
                    adapter = new CustomListViewAdapter(addList());
                    cardStackView.setAdapter(adapter);
                    manager.setTopPosition(0);
                    cardStackView.setVisibility(View.VISIBLE);
                    System.out.println("Ending...");
                }
            }

            @Override
            public void onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardAppeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", name: " + tv.getText());
            }
        });

        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(40.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new CustomListViewAdapter(addList());
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());


    }
}
