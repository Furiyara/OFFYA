# Türkçe Flick Klavye (başlangıç projesi)

Bu, Android için bir **klavye uygulaması başlangıç projesidir**. Japon tipi 12 tuşlu düzene benzer bir görünüm ve kaydırma ile karakter seçme davranışı içerir.

## Önemli notlar
- Bu ZIP kaynak kodudur; hazır APK değildir.
- Derlemek için bilgisayarda Android Studio gerekir.
- Bu ilk prototipte tuş grupları basitleştirilmiştir; tam Türkçe üretim klavyesi değildir.
- Her tuşta kısa dokunma temel karakteri, sağ/sol/yukarı/aşağı kaydırma ise ilgili gruptaki başka karakteri seçer.
- Harf tahmini, sözlük, otomatik düzeltme, Shift/büyük-küçük harf ve tam ergonomik flick yerleşimi henüz eklenmemiştir.

## Android Studio'da açma
1. ZIP'i çıkar.
2. Android Studio'da **Open** deyip `TurkceFlickKlavye` klasörünü seç.
3. Gradle senkronizasyonunun bitmesini bekle.
4. Telefonu USB ile bağla ve Run ile yükle.
5. Telefonda **Ayarlar > Genel yönetim > Klavye listesi ve varsayılan** (menü adları değişebilir) bölümünden **Türkçe Flick Klavye**'yi etkinleştir.
6. Klavye seçicisinden bu klavyeyi varsayılan olarak seç.

Android sürümüne ve Android Studio kurulumuna göre derleme ayarı gerekebilir.
