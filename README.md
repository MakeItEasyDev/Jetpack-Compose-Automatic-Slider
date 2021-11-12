# Jetpack-Compose-Automatic-Slider

## [Watch it On YouTube](https://www.youtube.com/watch?v=rsHyjOTkxlM)

If you want slider Indicator, Please add the code

# Add this code
  HorizontalPagerIndicator(
    pagerState = pagerState,
    modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(16.dp)
  )
        
# Example:
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
