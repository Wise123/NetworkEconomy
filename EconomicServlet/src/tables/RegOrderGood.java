package tables;

public class RegOrderGood {
		int idOrder;
		int idGood;
		public RegOrderGood() {
		}
		public RegOrderGood(int idOrder, int idGood) {
			super();
			this.idOrder = idOrder;
			this.idGood = idGood;
		}
		public int getIdOrder() {
			return idOrder;
		}
		public void setIdOrder(int idOrder) {
			this.idOrder = idOrder;
		}
		public int getIdGood() {
			return idGood;
		}
		public void setIdGood(int idGood) {
			this.idGood = idGood;
		}
		
		
	}
