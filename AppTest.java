  @Test
   public void testFound() {
      ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
      assertTrue(App.search(array, 4));
    }

    @Test
    public void testNotFound() {
      ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
      assertFalse(App.search(array, 5));
    }

    @Test
    public void testEmptyArray() {
      ArrayList<Integer> array = new ArrayList<>();
      assertFalse(App.search(array, 1));
    }

    @Test
    public void testNull() {
      assertFalse(App.search(null, 1));
    }
