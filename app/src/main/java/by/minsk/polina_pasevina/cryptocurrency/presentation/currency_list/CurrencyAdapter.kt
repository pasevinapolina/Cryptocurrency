package by.minsk.polina_pasevina.cryptocurrency.presentation.currency_list

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.minsk.polina_pasevina.cryptocurrency.R
import kotlinx.android.synthetic.main.list_item_currency.view.*

class CurrencyAdapter(
    private val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    private val currencies: MutableList<CurrencyViewState> = mutableListOf()

    fun update(newCurrencies: List<CurrencyViewState>) {
        val diffResult = DiffUtil.calculateDiff(
            CurrencyDiffCallback(
                currencies,
                newCurrencies
            )
        )
        currencies.clear()
        currencies.addAll(newCurrencies)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = currencies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencies[position]
        holder.update(currency)
    }

    inner class CurrencyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var currency: CurrencyViewState

        init {
            itemView.setOnClickListener { onItemClicked(currency.id) }
        }

        fun update(currency: CurrencyViewState) {
            this.currency = currency
            onItemChanged()
        }

        private fun onItemChanged() {
            itemView.apply {
                textViewCurrencyName.text = currency.name
                textViewCurrencyPrice.text = currency.price?.let {
                    String.format(context.getString(R.string.activity_currency_list_price), it)
                }.orUnknown()
            }
        }

        private fun Any?.orUnknown() = this?.toString() ?: itemView.context.getString(R.string.activity_currency_list_price_unknown)
    }

    private class CurrencyDiffCallback(
        private val oldItems: List<CurrencyViewState>,
        private val newItems: List<CurrencyViewState>
    ) : DiffUtil.Callback() {

        override fun getNewListSize() = newItems.size

        override fun getOldListSize() = oldItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldItems[oldItemPosition].id == newItems[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldItems[oldItemPosition] == newItems[newItemPosition]

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int) = Any()
    }
}