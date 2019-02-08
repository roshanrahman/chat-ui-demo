package com.example.chatuidemo

import android.content.Context
import android.net.ConnectivityManager
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_offline.*

class MainActivity : AppCompatActivity() {

    var counter = 0

    val messages: ArrayList<Message> = arrayListOf<Message>(
        Message(Message.CENTERED, "8 FEBRUARY 2019"),
        Message(Message.SENDER, "Hello"),
        Message(Message.RECEIVER, "Hi dude, what's up?"),
        Message(Message.SENDER, "Nothing much man, just chilling"),
        Message(Message.SENDER, "How did your trip go?"),
        Message(Message.RECEIVER, "It was awesome! 😍"),
        Message(Message.RECEIVER, "Seriously, a lot of fun"),
        Message(Message.SENDER, "Wow, sounds really good. Took any pictures?"),
        Message(Message.RECEIVER, "Totally"),
        Message(Message.RECEIVER, "Wait a minute"),
        Message(Message.RECEIVER, "Look!", R.drawable.island),
        Message(Message.SENDER, "Woah 😲👌"),
        Message(Message.SENDER, "Looks fantastic!"),
        Message(Message.RECEIVER, "Yeah, and btw did I tell you about the food?"),
        Message(Message.SENDER, "Ah no, you're making me really jealous"),
        Message(Message.RECEIVER, "Here you go", R.drawable.burger),
        Message(Message.SENDER, "Dude, that looks soo good 😋😋😋"),
        Message(Message.RECEIVER, "Yeah, it was great!"),
        Message(Message.RECEIVER, "So tell me about your weekend"),
        Message(Message.SENDER, "I sat all day applying on Internshala"),
        Message(Message.RECEIVER, "Ah, you've been quite busy"),
        Message(Message.SENDER, "Yeah 😅"),
        Message(Message.CENTERED, "9 FEBRUARY 2019"),
        Message(Message.RECEIVER, "Did you buy the new laptop you so badly wanted?"),
        Message(Message.SENDER, "Of course I did. Take a look"),
        Message(Message.SENDER, "", R.drawable.laptop),
        Message(Message.RECEIVER, "Awesome 😎👍"),
        Message(Message.RECEIVER, "Congrats my dude"),
        Message(Message.SENDER, "Thanks a lot"),
        Message(Message.SENDER, "Send more pics of your trip"),
        Message(Message.RECEIVER, "I'll send you pics of my dinner, wait.."),
        Message(Message.SENDER, "😂😂😂"),
        Message(Message.RECEIVER, "Delicious", R.drawable.chicken),
        Message(Message.RECEIVER, "", R.drawable.drinks),
        Message(Message.SENDER, "Damn, lucky you. I am eating cup noodles."),
        Message(Message.SENDER, "Anyway, got to go! Catch you later dude. Have fun"),
        Message(Message.RECEIVER, "Sure dude, see ya. Take care. 😁"),
                Message(Message.CENTERED, "10 FEBRUARY 2019"),
    Message(Message.SENDER, "Hello"),
    Message(Message.RECEIVER, "Hi dude, what's up?"),
    Message(Message.SENDER, "Nothing much man, just chilling"),
    Message(Message.SENDER, "How did your trip go?"),
    Message(Message.RECEIVER, "It was awesome! 😍"),
    Message(Message.RECEIVER, "Seriously, a lot of fun"),
    Message(Message.SENDER, "Wow, sounds really good. Took any pictures?"),
    Message(Message.RECEIVER, "Totally"),
    Message(Message.RECEIVER, "Wait a minute"),
    Message(Message.RECEIVER, "Look!", R.drawable.island),
    Message(Message.SENDER, "Woah 😲👌"),
    Message(Message.SENDER, "Looks fantastic!"),
    Message(Message.RECEIVER, "Yeah, and btw did I tell you about the food?"),
    Message(Message.SENDER, "Ah no, you're making me really jealous"),
    Message(Message.RECEIVER, "Here you go", R.drawable.burger),
    Message(Message.SENDER, "Dude, that looks soo good 😋😋😋"),
    Message(Message.RECEIVER, "Yeah, it was great!"),
    Message(Message.RECEIVER, "So tell me about your weekend"),
    Message(Message.SENDER, "I sat all day applying on Internshala"),
    Message(Message.RECEIVER, "Ah, you've been quite busy"),
    Message(Message.SENDER, "Yeah 😅")
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_DOWN)
            handleScreenClick()
        return super.dispatchTouchEvent(event)
        }

    fun handleScreenClick() {
        if(!isOnline()) {
            offlineFragmentContainer.visibility = View.VISIBLE
            return
        }
        offlineFragmentContainer.visibility = View.GONE
        if(counter < messages.size) {
            var m: Message = messages.get(counter)
            if(m.messageType == Message.SENDER) {
                var messageView: View? = View.inflate(applicationContext, R.layout.sender_message, null)
                var messageText = messageView?.findViewById<TextView>(R.id.message)
                var messageImage = messageView?.findViewById<ImageView>(R.id.messageImage)
                messageText?.text = m.message
                if(m.message.equals("")) messageText?.visibility = View.GONE
                if(m.imageSrc != null) messageImage?.setImageResource(m.imageSrc!!)
                messageContainer.addView(messageView)
            }
            if(m.messageType == Message.RECEIVER) {
                var messageView: View? = View.inflate(applicationContext, R.layout.receiver_message, null)
                var messageText = messageView?.findViewById<TextView>(R.id.message)
                var messageImage = messageView?.findViewById<ImageView>(R.id.messageImage)
                if(m.imageSrc != null) messageImage?.setImageResource(m.imageSrc!!)
                if(m.message.equals("")) messageText?.visibility = View.GONE
                messageText?.text = m.message
                messageContainer.addView(messageView)

            }
            if(m.messageType == Message.CENTERED) {
                var messageView: View? = View.inflate(applicationContext, R.layout.centered_message, null)
                var messageText = messageView?.findViewById<TextView>(R.id.message)
                messageText?.setText(m.message)
                messageContainer.addView(messageView)

            }
            counter += 1
        }

    }

    fun isOnline(): Boolean {
        val connManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
