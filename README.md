# Jetpack-Compose-Automatic-Slider

## [Watch it On YouTube](https://www.youtube.com/watch?v=rsHyjOTkxlM)

If you want slider Indicator, Please add the code

# Add this code
```
HorizontalPagerIndicator(
  pagerState = pagerState,
  modifier = Modifier
      .align(Alignment.CenterHorizontally)
      .padding(16.dp)
)
```
        
# Example:
```
Column(
    modifier = Modifier.fillMaxSize()
) {
  HorizontalPager(
      state = pagerState,
      modifier = Modifier
          .weight(1f)
          .padding(0.dp, 40.dp, 0.dp, 40.dp)
  ) {
    ........
  }

  HorizontalPagerIndicator(
    pagerState = pagerState,
    modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(16.dp)
  )
}
```

## License
```
Copyright 2020 MakeItEasyDev

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
