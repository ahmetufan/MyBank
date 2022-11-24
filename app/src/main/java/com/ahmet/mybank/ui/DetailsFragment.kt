package com.ahmet.mybank.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ahmet.mybank.databinding.FragmentDetailsBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val gelen by navArgs<DetailsFragmentArgs>()
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = Firebase.analytics

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        gelen.let {
            binding.apply {
                val data = gelen.veri


                if (data.dc_SEHIR == "") {
                    sehirText.text = "Veri yok"
                } else {
                    sehirText.text = gelen.veri.dc_SEHIR
                }
                if (data.dc_ILCE == "") {
                    ilceText.text = "Veri yok"
                } else {
                    ilceText.text = gelen.veri.dc_ILCE
                }
                if (data.dc_BANKA_SUBE == "") {
                    bankaSubeText.text = "Veri yok"
                } else {
                    bankaSubeText.text = gelen.veri.dc_BANKA_SUBE
                }
                if (data.dc_BANK_KODU == "") {
                    bankaKoduText.text = "Veri yok"
                } else {
                    bankaKoduText.text = gelen.veri.dc_BANK_KODU
                }
                if (data.dc_ADRES_ADI == "") {
                    adresAdiText.text = "Veri yok"
                } else {
                    adresAdiText.text = gelen.veri.dc_ADRES_ADI
                }
                if (data.dc_ADRES == "") {
                    adresText.text = "Veri yok"
                } else {
                    adresText.text = gelen.veri.dc_ADRES
                }
                if (data.dc_BOLGE_KOORDINATORLUGU == "") {
                    bolgeKoordinatorText.text = "Veri yok"
                } else {
                    bolgeKoordinatorText.text = gelen.veri.dc_BOLGE_KOORDINATORLUGU
                }
                if (data.dc_EN_YAKIM_ATM == "") {
                    atmText.text = "Veri yok"
                } else {
                    atmText.text = gelen.veri.dc_EN_YAKIM_ATM
                }
                if (data.dc_POSTA_KODU == "0" || data.dc_POSTA_KODU == "") {
                    postaKoduText.text = "Veri yok"
                } else {
                    postaKoduText.text = gelen.veri.dc_POSTA_KODU
                }
            }
        }


        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, gelen.veri.dc_SEHIR)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

        

        binding.imageView8.setOnClickListener {

            val location: Uri =
                Uri.parse("geo:0,0?q=" + gelen.veri.dc_ADRES)
            val mapIntent = Intent(Intent.ACTION_VIEW, location)
            startActivity(mapIntent)

        }


    }
}