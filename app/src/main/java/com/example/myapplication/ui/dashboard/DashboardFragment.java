package com.example.myapplication.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDashboardBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;


public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private EditText Title_Task,Description_Task,Date_Task,Time_Task;
    private Button Location_Save, Invite_Vol,Task_Post;
    private String Document_Path;

    private static final String KEY_TITLE = "Title", KEY_DESCRIPTION = "Description",KEY_DATE= "Date",KEY_TIME ="Time";


    private FirebaseFirestore Fstore;
    Query Fquery;
    private static final int LIMIT = 50;

    private DocumentReference TaskDataRef;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //------------------------------------------my add-----------------------------------------
        // View v = inflater.inflate(R.layout.fragment_notifications,container,false);
       // Update_Button = root.findViewById(R.id.update_button);

        Title_Task = root.findViewById(R.id.title_task_elder);
        Description_Task = root.findViewById(R.id.description_task_elder);
        Date_Task = root.findViewById(R.id.date_task_elder);
        Time_Task = root.findViewById(R.id.time_task_elder);

        Location_Save = root.findViewById(R.id.Button_location_elder);
        Invite_Vol = root.findViewById(R.id.inviteVolunteer_elder);
        Task_Post = root.findViewById(R.id.PostTask_elder);

        Document_Path = com.example.myapplication.ElderlyLoginActivity.GetUserEmail();//这样可以考虑用add还是set

        Firebase_Init();

        Task_Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SendPost();
                //Toast.makeText(getActivity(), "这是一个测试"+Document_Path, Toast.LENGTH_SHORT).show();
            }
        });

        Location_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "还没想好", Toast.LENGTH_SHORT).show();
            }
        });
       // Document_Path = com.example.myapplication.ElderlyLoginActivity.GetUserEmail();





        return root;
    }

    public void Firebase_Init()
    {
        Fstore = FirebaseFirestore.getInstance();
        Fquery = Fstore.collection("restaurants")
                .orderBy("avgRating", Query.Direction.DESCENDING).limit(LIMIT);

        TaskDataRef= Fstore.collection("Task").document(Document_Path);

    }


    public void SendPost()
    {

        String title =  Title_Task.getText().toString();
        String description =  Description_Task.getText().toString();
        String date =  Date_Task.getText().toString();
        String time = Time_Task.getText().toString();


        HashMap<String,Object> TaskInfo = new HashMap<>();
        TaskInfo.put(KEY_TITLE,title);
        TaskInfo.put(KEY_DESCRIPTION,description);
        TaskInfo.put(KEY_DATE,date);
        TaskInfo.put(KEY_TIME,time);




        /*
        firestore.collection("users").add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getActivity(),"成功？", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"失败？", Toast.LENGTH_LONG).show();
            }
        });

         */
        //Toast.makeText(getActivity(), "这是一个测试"+Document_Path, Toast.LENGTH_SHORT).show();


        Fstore.collection("Task").document(Document_Path).set(TaskInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity() ,"发送失败", Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });



        //Load_profile();

    }


    public void Load_profile() {

        TaskDataRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String title = documentSnapshot.getString(KEY_TITLE);
                            String description =  documentSnapshot.getString(KEY_DESCRIPTION);
                            String date =  documentSnapshot.getString(KEY_DATE);
                            String time = documentSnapshot.getString(KEY_TIME);
                            //String description = documentSnapshot.getString(KEY_DESCRIPTION);



                            Title_Task.setText(title);
                            Description_Task.setText(description);
                            Date_Task.setText(date);
                            Time_Task.setText(time);

                        } else {
                           // Toast.makeText(getActivity(), "Your Profile is empty!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}