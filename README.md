# Jetpack-Compose-Automatic-Slider

# YouTube link -> https://www.youtube.com/watch?v=rsHyjOTkxlM

If you want slider Indicator, Please add the code

# Example:
  HorizontalPager(
      state = pagerState,
      modifier = Modifier
          .weight(1f)
          .padding(0.dp, 40.dp, 0.dp, 40.dp)
  ) {
    ........
  }
  
# Add this code
  HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
