package com.example.myflexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment


/**
 * A simple [Fragment] subclass.
 * Use the [OptionDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OptionDialogFragment : DialogFragment() {
    // create private late init var for button
    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    // create private late init var for RadioGroup
    private lateinit var rgOptions: RadioGroup
    // create private late init var for RadioButton
    private lateinit var rbSaf: RadioButton
    private lateinit var rbMou: RadioButton
    private lateinit var rbLvg: RadioButton
    private lateinit var rbMoyes: RadioButton
    // create private var for option dialog listener
    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    // create fun onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initialize btnChoose
        btnChoose = view.findViewById(R.id.btn_choose)
        // initialize btnClose
        btnClose = view.findViewById(R.id.btn_close)
        // initialize rgOptions
        rgOptions = view.findViewById(R.id.rg_options)
        // initialize rbSaf
        rbSaf = view.findViewById(R.id.rb_saf)
        // initialize rbMou
        rbMou = view.findViewById(R.id.rb_mou)
        // initialize rbLvg
        rbLvg = view.findViewById(R.id.rb_lvg)
        // initialize rbMoyes
        rbMoyes = view.findViewById(R.id.rb_moyes)

        // set on click listener for btnChoose
        btnChoose.setOnClickListener {
            // create val checkedRadioButtonId
            val checkedRadioButtonId = rgOptions.checkedRadioButtonId
            // check if checkedRadioButtonId not equal to -1
            if (checkedRadioButtonId != -1) {
                // create var coach: String? = null
                var coach: String? = when (checkedRadioButtonId) {
                    // if true, set coach to Saf
                    rbSaf.id -> rbSaf.text.toString().trim()
                    // if true, set coach to Mou
                    rbMou.id -> rbMou.text.toString().trim()
                    // if true, set coach to Lvg
                    rbLvg.id -> rbLvg.text.toString().trim()
                    // if true, set coach to Moyes
                    rbMoyes.id -> rbMoyes.text.toString().trim()
                    // if true, set coach to null
                    else -> null
                }
                // call onOptionChosen method
                optionDialogListener?.onOptionChosen(coach)
                // dismiss dialog
                dialog?.dismiss()
            }
        }

        // set on click listener for btnClose
        btnClose.setOnClickListener {
            // dialog cancel
            dialog?.cancel()
        }
    }

    // create override fun OnAttach
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // create val fragment = parentFragment
        val fragment = parentFragment
        // check if fragment is DetailCategoriFragment
        if (fragment is DetailCategoryFragment) {
            // set optionDialogListener to fragment
            optionDialogListener = fragment.optionDialogListener
        }
    }

    // create override fun onDetach
    override fun onDetach() {
        super.onDetach()
        // set optionDialogListener to null
        optionDialogListener = null
    }

    // create interface OnOptionDialogListener
    interface OnOptionDialogListener {
        // create fun onOptionChosen
        fun onOptionChosen(text: String?)
    }
}