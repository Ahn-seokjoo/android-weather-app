## Weather 어플리케이션 - Android
---
### **설명**
* Seoul, London, Chicago 세도시의 날씨를 표시하는 앱입니다. 
  위의 순서대로 출력해야 하며 오늘을 포함한 6일간의 날씨를 표시합니다.
  오늘은 Today, 내일은 Tommorrow로 표시하며 metaweather.com에서 api를 얻어와 표시합니다. 스크롤이 가능한 리스트여야 합니다.
---
### **실행 영상**  
#
  - ![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/67602108/118836521-e0580180-b8fe-11eb-9727-7a792a1955de.gif)
  - 

### **사용 외부 라이브러리**
---
1. Layout
- [recyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)
2. Jetpack
- recyclerView
- ViewModel
- Fragment
3. [Glide](https://github.com/bumptech/glide)
4. [Retrofit2](https://github.com/square/retrofit)

### **개발 환경**
---
- 언어 - **Kotlin**
- minSdkVersion - 26
- targetSdkVersion - 30
- target - Android 11.0(Google APIs) 
- testDevice - Nexus 5X API 30(VM)
