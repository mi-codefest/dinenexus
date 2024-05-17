package com.example.codefest20

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationChannel.DEFAULT_CHANNEL_ID
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.fragment.findNavController
import com.example.codefest20.databinding.FragmentFirstBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                context?.let {
                    sendNotification(it)
                }

            } else {
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                activity?.let {
                    /*if(ActivityCompat.shouldShowRequestPermissionRationale(
                        it, Manifest.permission.POST_NOTIFICATIONS)){
                        MaterialAlertDialogBuilder(requireActivity().applicationContext)
                            .setTitle("Notification Permission")
                            .setMessage("We need your permission to send the push")
                            .setCancelable(true)
                            .setPositiveButton(positive) { _, _ ->
                                dialogCallback?.invoke(DialogSelection.Positive)
                            }
                            .setNegativeButton(negative) { _, _ ->
                                dialogCallback?.invoke(DialogSelection.Negative)
                            }
                            .setOnCancelListener {
                                dialogCallback?.invoke(DialogSelection.Cancel)
                            }
                            .show()
                            .expandSize(maxHeight)
                    }*/
                }

            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.createNotificationChannels()

        binding.buttonActiveStay.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.buttonSearch.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_searchFragment)
        }

        binding.lunchtimebutton.setOnClickListener {

            context?.let { context ->
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    sendNotification(context)
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }

    }

    @SuppressLint("MissingPermission")
    private fun sendNotification(context: Context) {
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            Intent(context, MainActivity::class.java).apply {
                data = Uri.parse("marriott://mobile-codefest/eat-food")
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notificationManager = NotificationManagerCompat.from(context)
        val builder =
            NotificationCompat.Builder(context,"com.codefest.20" ).apply {
                val content =
                    "Its lunch time. Lets find good restaurant where you can earn Bonvoy points"
                setContentTitle("Its lunch time")
                setSmallIcon(R.drawable.ic_notification_10)
                setContentText(content)
                setAutoCancel(true)
                setStyle(NotificationCompat.BigTextStyle().bigText(content))
                priority = NotificationManager.IMPORTANCE_DEFAULT
                pendingIntent?.let { intent ->
                    setContentIntent(intent)
                }
            }
        notificationManager.notify(100, builder.build())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

fun Context.createNotificationChannels() {
    val manager = NotificationManagerCompat.from(this)
    manager.createNotificationChannel(NotificationChannel("com.codefest.20","Code Fest",NotificationManager.IMPORTANCE_NONE))

}