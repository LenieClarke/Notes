package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private FloatingActionButton fab;

    static NoteDataAdapter adapter;
    static List<NoteData> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        list = findViewById(R.id.list);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddingNewNoteActivity.class);
            startActivity(intent);
        });

        adapter = new NoteDataAdapter(this, notes);
        list.setAdapter(adapter);

        list.setOnItemLongClickListener((parent, view, position, id) -> {
            adapter.deleteNote(position);
            Toast.makeText(MainActivity.this, R.string.toastNoteDeleted, Toast.LENGTH_LONG).show();
            return true;
        });

        list.setOnItemClickListener((parent, view, position, id) -> {
            NoteData noteData = adapter.getItem(position);
            Toast.makeText(MainActivity.this, noteData.getTitle() + "\n"
                            + noteData.getSubtitle() + "\n" + noteData.getDeadline(),
                    Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}