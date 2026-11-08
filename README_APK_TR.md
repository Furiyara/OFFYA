# Türkçe Flick Klavye — APK hazırlama paketi

**Bu ZIP'in içindeki dosyaları yalnızca bir ZIP uygulamasıyla açmak APK oluşturmaz.**
ZIP uygulamaları dosyaları çıkarır; Android uygulaması üretmek için derleme ortamı gerekir.

Bu projeye, GitHub Actions üzerinden APK derlemeye yarayan bir iş akışı eklendi. APK'yı almak için:
1. ZIP'i çıkar.
2. GitHub'da yeni bir depo (repository) oluştur.
3. Çıkarılmış klasörün içindeki dosyaları depoya yükle. `.github/workflows/build-apk.yml` dosyasının da yüklendiğinden emin ol.
4. Depoda **Actions** sekmesini aç.
5. **Android APK oluştur** iş akışını seçip **Run workflow** düğmesine bas.
6. İşlem tamamlanınca çalışmanın altındaki **Artifacts** bölümünden `Turkce-Flick-Klavye-APK` dosyasını indir.
7. İndirilen artifact ZIP'ini aç; içindeki `app-debug.apk` dosyası telefona kurulacak dosyadır.

## Bilmen gerekenler
- Bu, ilk prototip kaynak kodudur; Gboard düzeyinde tamamlanmış bir ürün değildir.
- Tema galerisi/fotoğraf seçme özelliği henüz bu prototipe eklenmemiştir.
- Derleme sırasında hata çıkarsa loga göre proje ayarı düzeltmek gerekebilir.
