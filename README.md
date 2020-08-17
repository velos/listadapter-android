# ListAdapter

This library is an implementation of
[ListAdapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/ListAdapter)
that removes the need to implement your own
[RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView)
[Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter),
[ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder)s
and [DiffUtil.Callback](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil.Callback)
while allowing better separation between your
[ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel) and view.

It automatically handles:

* Heterogenous view types
* [DiffUtil](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil) callbacks for reordering animations
* [ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder)
implementation: views are automatically cached using Kotlin synthetics


## How to use
Create an instance of [ListAdapter](library/src/main/java/com/velosmobile/listadapter/ListAdapter.kt).

From your view model, return a list of [ItemContent](library/src/main/java/com/velosmobile/listadapter/ItemContent.kt)s to your Activity or Fragment.

Build a list of [Item](library/src/main/java/com/velosmobile/listadapter/Item.kt)s and submit it to your [ListAdapter](library/src/main/java/com/velosmobile/listadapter/ListAdapter.kt) using `ListAdapter.submitList()`.

`ItemContent` should be a Kotlin data class with fields defining the properties for the item's views. The internal DiffUtil callback relies on `ItemContent.equals()` to determine if the contents of an item in the list has changed.

`Item` requires you to implement the following:
- `layout: Int`: the layout resource to use
- `content: ItemContent`: the content describing this item
- `fun bind(view: View)`: bind the view to `ItemContent`

## Example
See [MainActivity.kt](app/src/main/java/com/velosmobile/listadapter/app/MainActivity.kt)
and [MainViewModel.kt](app/src/main/java/com/velosmobile/listadapter/app/viewmodel/MainViewModel.kt).