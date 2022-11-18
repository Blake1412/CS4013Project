public class Menu {
        private int menuID;
        private String category;
        private String item;
        private int price;

        public Menu(int ID, String category, String item, int price){
                this.menuID = ID;
                this.category = category;
                this.item = item;
                this.price = price;

        }
        public int getMenuID() {
                return menuID;
        }

        public void setMenuID(int ID) {
                this.menuID = menuID;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public String getItem() {
                return item;
        }

        public void setItem(String item) {
                this.item = item;
        }

        public int getPrice() {
                return price;
        }

        public void setPrice(int price) {
                this.price = price;

        }
}
