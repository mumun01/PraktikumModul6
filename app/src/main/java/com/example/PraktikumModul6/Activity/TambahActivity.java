package com.example.PraktikumModul6.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.PraktikumModul6.API.APIRequestData;
import com.example.PraktikumModul6.API.RetroServer;
import com.example.PraktikumModul6.Model.ResponModel;
import com.example.PraktikumModul6.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etJurusan, etEmail;
    private Button btnSimpan;
    private String nama, jurusan, email;
    private Button btnlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etJurusan = findViewById(R.id.et_jurusan);
        etEmail = findViewById(R.id.et_email);
        btnSimpan = findViewById(R.id.btn_simpan);
        btnlist = findViewById(R.id.btn_list);

        btnlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TambahActivity.this, MainActivity.class));
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                jurusan = etJurusan.getText().toString();
                email = etEmail.getText().toString();

                if (nama.trim().equals("")){
                    etNama.setError("Harus Di isi");
                }
                else if(jurusan.trim().equals("")){
                    etJurusan.setError("Harus Di isi");
                }
                else if(email.trim().equals("")){
                    etEmail.setError("Harus Di isi");
                }
                else{
                    createData();
                }
            }
        });
    }

    private void createData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponModel> simpanData = ardData.ardCreateData(nama, jurusan, email);

        simpanData.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Berhasil Di Simpan", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
