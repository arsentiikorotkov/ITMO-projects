package com.example.contacts

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.databinding.ActivityMainBinding
import com.example.contacts.databinding.ListItemBinding

const val ISSHOWN_KEY = "ISSHAWN"

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var isShown = false

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(ISSHOWN_KEY, isShown)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isShown = savedInstanceState.getBoolean(ISSHOWN_KEY)
    }

    private lateinit var contacts: List<Contact>
    private var size = -1
    private val myRequestId = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState != null) {
            isShown = savedInstanceState.getBoolean(ISSHOWN_KEY)
        }
        checkPermission()
        //написать количество контактов
        if (size != -1 && !isShown) {
            isShown = true
            Toast.makeText(
                applicationContext,
                resources.getQuantityString(
                    R.plurals.contactsLoaded,
                    size,
                    size
                ),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun checkPermission() {
        //проверка разрешения
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //нет разрешения, запрашиваем
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.READ_CONTACTS),
                myRequestId
            )
        } else {
            //есть разрешение
            contacts = fetchAllContacts()
            size = contacts.size
            val myRecyclerView = binding.recyclerView
            myRecyclerView.adapter = ContactAdapter(this, contacts)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            myRequestId -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //пользователь согласился
                    recreate()
                } else {
                    //пользователь не согласился
                    Toast.makeText(
                        applicationContext,
                        R.string.permissionNotReceived,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    class Contact(val name: String, val number: String)

    class ContactViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        private var binding: ListItemBinding = ListItemBinding.bind(root)
        val name: TextView = binding.name
        val number: TextView = binding.number
        val imageButton: ImageButton = binding.imageButton
    }

    class ContactAdapter(
        private val context: Context,
        private val contacts: List<Contact>,
    ) : RecyclerView.Adapter<ContactViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
            return ContactViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false),
            )
        }
        
        override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
            val current = contacts[position]
            with(holder) {
                name.text = current.name
                number.text = current.number

                name.setOnClickListener {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:${current.number}")
                        )
                    )
                }

                number.setOnClickListener {
                    name.callOnClick()
                }

                imageButton.setOnClickListener {
                    val sms = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:${current.number}"))
                    sms.putExtra("sms_body", context.getString(R.string.defaultMessage))
                    context.startActivity(sms)
                }
            }
        }

        override fun getItemCount() = contacts.size
    }

    private fun Context.fetchAllContacts(): List<Contact> {
        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
            .use { cursor ->
                if (cursor == null) return emptyList()
                val builder = ArrayList<Contact>()
                while (cursor.moveToNext()) {
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                            ?: "N/A"
                    val phoneNumber =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            ?: "N/A"

                    builder.add(Contact(name, phoneNumber))
                }
                return builder
            }
    }
}