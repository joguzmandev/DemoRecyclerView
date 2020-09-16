package org.softhk.demo

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.github_user_item.*
import org.softhk.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter: GithubUserAdapter by lazy { GithubUserAdapter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.adapter.addGithubUsersList(getGithubUserListDummy(50))

        with(this.binding.githubuserRecyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        this.binding.swipe.setOnRefreshListener{
            this.binding.swipe.isRefreshing = true
            Handler().postDelayed({
                this.binding.swipe.isRefreshing = false
                Toast.makeText(this, "Recycle has been updated", Toast.LENGTH_SHORT).show()
            }, 5000)
        }
    }


    private fun getGithubUserListDummy(elements: Int): MutableList<GithubUserDummy> {
        var list = mutableListOf<GithubUserDummy>()
        for (item in 0 until elements) {
            val avatar = when (item % 5) {
                1 -> R.drawable.ic_avatar_1
                2 -> R.drawable.ic_avatar_2
                3 -> R.drawable.ic_avatar_3
                4 -> R.drawable.ic_avatar_4
                else -> R.drawable.ic_avatar_5
            }

            val occupation = when (item % 5) {
                1 -> "Software Engineer"
                2 -> "Senior Android Developer"
                3 -> "Q&A"
                4 -> "Backend Developer"
                else -> "SQL Developer"
            }

            val user = GithubUserDummy("User $item", occupation, avatar)

            list.add(user)
        }

        return list
    }


}

