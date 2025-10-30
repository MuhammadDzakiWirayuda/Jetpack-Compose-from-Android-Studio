# Jetpack-Compose-from-Android-Studio
**Product Catalog App**

**📱 Tentang Aplikasi**
Aplikasi Product Catalog adalah aplikasi mobile yang dibangun menggunakan Jetpack Compose untuk menampilkan katalog produk dengan navigasi yang intuitif dan pengiriman data yang efisien antar layar.


**🎯 Pola Navigasi yang Diimplementasikan**

**1. Bottom Navigation Pattern**
Aplikasi menggunakan bottom navigation dengan 3 tab utama:

**Home** - Halaman beranda dengan overview aplikasi

**Products** - Daftar produk yang tersedia

**Profile** - Informasi pengguna dan pengaturan

**2. Stack Navigation**

Implementasi navigasi hierarkis dengan kemampuan:

**Push** - Membuka halaman baru di atas stack

**Pop** - Kembali ke halaman sebelumnya

**Pop to Root** - Kembali ke halaman awal tab

**3. Conditional Bottom Bar**
Bottom navigation bar disembunyikan secara otomatis ketika berada di halaman detail untuk pengalaman pengguna yang lebih baik.


**🔄 Mekanisme Pengiriman Data**

**1. Type-Safe Navigation dengan Kotlin Serialization**
kotlin
@Serializable
data class ProductDetailRoute(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: String
)

**2. Data Class untuk Produk**
kotlin
@Serializable
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val imageUrl: String = ""
)

**3. Mekanisme Pengiriman Data**
Dari ProductsScreen ke ProductDetailScreen: Data produk dikirim sebagai parameter navigasi

**4. Serialization/Deserialization**: Objek Product di-serialize menjadi route parameters

**5. Type Safety**: Compile-time safety dengan typed navigation




📸 Tangkapan Layar

**1. Halaman Home**


<img width="371" height="755" alt="image" src="https://github.com/user-attachments/assets/4d01ff4c-769f-44dd-9894-e6a5fe862ed2" />


Halaman beranda dengan fitur overview dan navigasi ke produk


**2. Halaman Products**

<img width="369" height="761" alt="image" src="https://github.com/user-attachments/assets/0e435f21-027e-4177-a48c-92dc0f1f60be" />


Daftar produk yang dapat diklik untuk melihat detail

**3. Halaman Product Detail**

<img width="356" height="757" alt="image" src="https://github.com/user-attachments/assets/53733709-67ad-4ab2-ba10-2ab87492e059" />


Detail produk dengan informasi lengkap dan harga dalam format Rupiah

**4. Halaman Profile**


<img width="360" height="762" alt="image" src="https://github.com/user-attachments/assets/09f1cfc6-56bc-4ed6-bd9c-c46a04a5360a" />


Profil pengguna dengan berbagai menu pengaturan

**🛠️ Teknologi yang Digunakan**
**Jetpack Compose** - Modern UI toolkit

**Navigation Compose** - Type-safe navigation

**Material Design 3** - Design system

**Kotlin Serialization** - Data serialization

**Compose Material3** - UI components

**📋 Fitur Utama**

✅ Navigasi Bottom Tab dengan 3 halaman utama

✅ Pengiriman Data antar screen menggunakan @Serializable

✅ UI Responsif dengan Material Design 3

✅ Format Harga dalam mata uang Rupiah

✅ Type-safe Navigation dengan compile-time safety


**🚀 Cara Menjalankan**

1. Clone repository ini

2. Buka project di Android Studio

3. Build dan run aplikasi

4. Aplikasi akan berjalan di device/emulator dengan API level 24+

**📝 Catatan Implementasi**

**Pola Navigasi**: Menggunakan kombinasi bottom navigation dan stack navigation

**Data Passing**: Menggunakan Kotlin Serialization untuk type-safe data transfer

**State Management**: Menggunakan Navigation Compose built-in state handling

**UI Consistency**: Mengikuti Material Design 3 guidelines
