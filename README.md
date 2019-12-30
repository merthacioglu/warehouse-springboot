Spring Boot kullanιlarak hazırlanmış web tabanlı bir ürün deposu uygulamasıdır.

MVC yapısı üzerine inşa edilmiş uygulamada,
- Model katmanında Java Persistence API ve CrudRepository interface'inden faydanılmıştır.
- Model ile Controller arasındaki etkileşimler için Service sınıfları hazırlanmıştır.
- URL'leri işleyip gerekli view'u döndürme ve view'ların ihtiyaç duyduğu verilerin aktarımı için Controller sınıfları hazırlanmıştır.
- Ayrıca authentication kontrolü ile sayfaların kullanıcıya kapatılması ve model'deki alanlara ait kısıtların kontrolü için sırasıyla SecurityConfig ve Validator sınıfları kullanılmıştır. 

Main metodunun olduğu sınıftaki doSomethingAfterStartup() metodunda ön tanımlı kullanıcı ve ürün bilgileri yer almaktadır. Bu bilgiler kullanılarak giriş yapılabilir.