package Txapelketa;

public class Txapelketa {
	protected Adabegia root;
	
	public String txapelduna() {
		return txapelduna(root);
	}
	
	private String txapelduna(Adabegia z) {
		if (z.info.golakEskuin==-1 && z.info.golakEzker==-1) return z.info.irabazlea;
		else {
			String irabazlea;
			if (z.info.golakEzker>=z.info.golakEskuin) {
				irabazlea=txapelduna(z.ezker);
				txapelduna(z.eskuin);
			}
			else {
				irabazlea=txapelduna(z.eskuin);
				txapelduna(z.ezker);
			}
			z.info.irabazlea=irabazlea;
			return irabazlea;
		}
	}
}
