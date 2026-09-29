<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginStart="12dp"
    android:layout_marginTop="8dp"
    android:layout_marginEnd="12dp"
    android:layout_marginBottom="8dp"
    app:cardCornerRadius="16dp"
    app:cardElevation="4dp">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="16dp">

        <TextView
            android:id="@+id/restaurantName"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:text="Restaurant Name"
            android:textColor="@color/text_primary"
            android:textSize="22sp"
            android:textStyle="bold"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

        <TextView
            android:id="@+id/restaurantCuisine"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_marginTop="6dp"
            android:text="Cuisine"
            android:textColor="@color/text_secondary"
            android:textSize="14sp"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintConstraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/restaurantName" />

        <TextView
            android:id="@+id/restaurantRating"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="12dp"
            android:background="@color/accent"
            android:padding="8dp"
            android:text="★ 4.9"
            android:textColor="@color/white"
            android:textStyle="bold"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/restaurantCuisine" />

        <TextView
            android:id="@+id/restaurantDeliveryInfo"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_marginTop="12dp"
            android:text="25-35 دقيقة | التوصيل مجاني فوق 50 ريال"
            android:textColor="@color/text_primary"
            android:textSize="15sp"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/restaurantRating" />

        <TextView
            android:id="@+id/restaurantAddress"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_marginTop="8dp"
            android:text="Address"
            android:textColor="@color/text_secondary"
            android:textSize="14sp"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/restaurantDeliveryInfo" />

    </androidx.constraintlayout.widget.ConstraintLayout>

</androidx.cardview.widget.CardView>
