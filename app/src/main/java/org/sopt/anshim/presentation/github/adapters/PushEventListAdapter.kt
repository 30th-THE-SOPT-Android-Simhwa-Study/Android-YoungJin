import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.anshim.databinding.ItemPushEventBinding
import org.sopt.anshim.domain.models.github.PushEventSingleCommitInfo

class PushEventListAdapter() :
    ListAdapter<PushEventSingleCommitInfo, RecyclerView.ViewHolder>(diffCallback) {

    class ViewHolder(private val binding: ItemPushEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PushEventSingleCommitInfo) {
            binding.push = item
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemPushEventBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val data = currentList[position]
        when (viewHolder) {
            is ViewHolder -> viewHolder.bind(data)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<PushEventSingleCommitInfo>() {
            override fun areItemsTheSame(
                oldItem: PushEventSingleCommitInfo,
                newItem: PushEventSingleCommitInfo,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PushEventSingleCommitInfo,
                newItem: PushEventSingleCommitInfo,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}