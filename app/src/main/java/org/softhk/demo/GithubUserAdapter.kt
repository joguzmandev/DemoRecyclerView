package org.softhk.demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.softhk.demo.databinding.GithubUserItemBinding

class GithubUserAdapter constructor(
    private val context: Context,
    private val clickListenerInterface: ItemClickListener
) : RecyclerView.Adapter<GithubUserAdapter.GithubUserViewHolder>() {

    private val githubUserList: MutableList<GithubUserDummy> by lazy {
        mutableListOf()
    }

    fun addGithubUsersList(list: List<GithubUserDummy>) {
        if (githubUserList.isNotEmpty())
            githubUserList.clear()
        this.githubUserList.addAll(list)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.github_user_item, parent, false)
        return GithubUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        val githubUser = githubUserList[position]

        holder.itemView.setOnClickListener{
            clickListenerInterface.onItemClicked(githubUser)
        }
        with(holder.binding) {
            Glide.with(context).load(githubUser.avatar).into(avatarImageView)
            userNameTextView.text = githubUser.username
            occupationTextView.text = githubUser.occupation
        }
    }

    override fun getItemCount() = githubUserList.size


    inner class GithubUserViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val binding: GithubUserItemBinding = GithubUserItemBinding.bind(view)


    }

    interface ItemClickListener{
        fun onItemClicked(itemClicked:GithubUserDummy)
    }
}