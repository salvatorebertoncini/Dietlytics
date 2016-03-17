package model;

import java.io.*;

public class MFood implements Serializable {
	
	private int id;
	private String name;
	private int edible;
	private int kcal;
	private int kj;
	private int water;
	private int protot;
	private int proani;
	private int proveg;
	private int glucot;
	private int amid;
	private int glucos;
	private int liptot;
	private int sattot;
	private int monins;
	private int polinstot;
	private int acoleic;
	private int aclino;
	private int aclinon;
	private int othpolin;
	private int colest;
	private int fiber;
	private int alcool;
	private int ferro;
	private int ca;
	private int na;
	private int k;
	private int p;
	private int zn;
	private int b1;
	private int b2;
	private int b3;
	private int c;
	private int b6;
	private int folic;
	private int retin;
	private int betacat;
	private int e;
	private int d;
	
	public MFood(){}
	
	public MFood(String name){
		this.name=name;
		this.id=id;
	}
	
	public MFood(int id, String name){
		this.name=name;
		this.id=id;
	}
	
	public MFood(String name, int kcal, int kj,	int protot, int colest, int fiber, int alcool, int ferro){
		this.name=name;
		this.kcal=kcal;
		this.kj=kj;
		this.protot=protot;
		this.colest=colest;
		this.fiber=fiber;
		this.alcool=alcool;
		this.ferro=ferro;
	}
	
	public MFood(int id, String name, int edible, int kcal, int kj, int water, int protot, int proani, int proveg,
			int glucot, int amid, int glucos, int liptot, int sattot, int monins, int polinstot, int acoleic,
			int aclino, int aclinon, int othpolin, int colest, int fiber, int alcool, int ferro, int ca, int na, int k,
			int p, int zn, int b1, int b2, int b3, int c, int b6, int folic, int retin, int betacat, int e, int d) {
		this.id = id;
		this.name = name;
		this.edible = edible;
		this.kcal = kcal;
		this.kj = kj;
		this.water = water;
		this.protot = protot;
		this.proani = proani;
		this.proveg = proveg;
		this.glucot = glucot;
		this.amid = amid;
		this.glucos = glucos;
		this.liptot = liptot;
		this.sattot = sattot;
		this.monins = monins;
		this.polinstot = polinstot;
		this.acoleic = acoleic;
		this.aclino = aclino;
		this.aclinon = aclinon;
		this.othpolin = othpolin;
		this.colest = colest;
		this.fiber = fiber;
		this.alcool = alcool;
		this.ferro = ferro;
		this.ca = ca;
		this.na = na;
		this.k = k;
		this.p = p;
		this.zn = zn;
		this.b1 = b1;
		this.b2 = b2;
		this.b3 = b3;
		this.c = c;
		this.b6 = b6;
		this.folic = folic;
		this.retin = retin;
		this.betacat = betacat;
		this.e = e;
		this.d = d;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEdible() {
		return edible;
	}
	public void setEdible(int edible) {
		this.edible = edible;
	}
	public int getKcal() {
		return kcal;
	}
	public void setKcal(int kcal) {
		this.kcal = kcal;
	}
	public int getKj() {
		return kj;
	}
	public void setKj(int kj) {
		this.kj = kj;
	}
	public int getWater() {
		return water;
	}
	public void setWater(int water) {
		this.water = water;
	}
	public int getProtot() {
		return protot;
	}
	public void setProtot(int protot) {
		this.protot = protot;
	}
	public int getProani() {
		return proani;
	}
	public void setProani(int proani) {
		this.proani = proani;
	}
	public int getProveg() {
		return proveg;
	}
	public void setProveg(int proveg) {
		this.proveg = proveg;
	}
	public int getGlucot() {
		return glucot;
	}
	public void setGlucot(int glucot) {
		this.glucot = glucot;
	}
	public int getAmid() {
		return amid;
	}
	public void setAmid(int amid) {
		this.amid = amid;
	}
	public int getGlucos() {
		return glucos;
	}
	public void setGlucos(int glucos) {
		this.glucos = glucos;
	}
	public int getLiptot() {
		return liptot;
	}
	public void setLiptot(int liptot) {
		this.liptot = liptot;
	}
	public int getSattot() {
		return sattot;
	}
	public void setSattot(int sattot) {
		this.sattot = sattot;
	}
	public int getMonins() {
		return monins;
	}
	public void setMonins(int monins) {
		this.monins = monins;
	}
	public int getPolinstot() {
		return polinstot;
	}
	public void setPolinstot(int polinstot) {
		this.polinstot = polinstot;
	}
	public int getAcoleic() {
		return acoleic;
	}
	public void setAcoleic(int acoleic) {
		this.acoleic = acoleic;
	}
	public int getAclino() {
		return aclino;
	}
	public void setAclino(int aclino) {
		this.aclino = aclino;
	}
	public int getAclinon() {
		return aclinon;
	}
	public void setAclinon(int aclinon) {
		this.aclinon = aclinon;
	}
	public int getOthpolin() {
		return othpolin;
	}
	public void setOthpolin(int othpolin) {
		this.othpolin = othpolin;
	}
	public int getColest() {
		return colest;
	}
	public void setColest(int colest) {
		this.colest = colest;
	}
	public int getFiber() {
		return fiber;
	}
	public void setFiber(int fiber) {
		this.fiber = fiber;
	}
	public int getAlcool() {
		return alcool;
	}
	public void setAlcool(int alcool) {
		this.alcool = alcool;
	}
	public int getFerro() {
		return ferro;
	}
	public void setFerro(int ferro) {
		this.ferro = ferro;
	}
	public int getCa() {
		return ca;
	}
	public void setCa(int ca) {
		this.ca = ca;
	}
	public int getNa() {
		return na;
	}
	public void setNa(int na) {
		this.na = na;
	}
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	public int getZn() {
		return zn;
	}
	public void setZn(int zn) {
		this.zn = zn;
	}
	public int getB1() {
		return b1;
	}
	public void setB1(int b1) {
		this.b1 = b1;
	}
	public int getB2() {
		return b2;
	}
	public void setB2(int b2) {
		this.b2 = b2;
	}
	public int getB3() {
		return b3;
	}
	public void setB3(int b3) {
		this.b3 = b3;
	}
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	public int getB6() {
		return b6;
	}
	public void setB6(int b6) {
		this.b6 = b6;
	}
	public int getFolic() {
		return folic;
	}
	public void setFolic(int folic) {
		this.folic = folic;
	}
	public int getRetin() {
		return retin;
	}
	public void setRetin(int retin) {
		this.retin = retin;
	}
	public int getBetacat() {
		return betacat;
	}
	public void setBetacat(int betacat) {
		this.betacat = betacat;
	}
	public int getE() {
		return e;
	}
	public void setE(int e) {
		this.e = e;
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
		private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
			aOutputStream.writeObject (id);
			aOutputStream.writeObject (name);
			aOutputStream.writeObject (edible);
			aOutputStream.writeObject (kcal);
			aOutputStream.writeObject (kj);
			aOutputStream.writeObject (water);
			aOutputStream.writeObject (protot);
			aOutputStream.writeObject (proani);
			aOutputStream.writeObject (proveg);
			aOutputStream.writeObject (glucot);
			aOutputStream.writeObject (amid);
			aOutputStream.writeObject (glucos);
			aOutputStream.writeObject (liptot);
			aOutputStream.writeObject (sattot);
			aOutputStream.writeObject (monins);
			aOutputStream.writeObject (polinstot);
			aOutputStream.writeObject (acoleic);
			aOutputStream.writeObject (aclino);
			aOutputStream.writeObject (aclinon);
			aOutputStream.writeObject (othpolin);
			aOutputStream.writeObject (colest);
			aOutputStream.writeObject (fiber);
			aOutputStream.writeObject (alcool);
			aOutputStream.writeObject (ferro);
			aOutputStream.writeObject (ca);
			aOutputStream.writeObject (na);
			aOutputStream.writeObject (k);
			aOutputStream.writeObject (p);
			aOutputStream.writeObject (zn);
			aOutputStream.writeObject (b1);
			aOutputStream.writeObject (b2);
			aOutputStream.writeObject (b3);
			aOutputStream.writeObject (c);
			aOutputStream.writeObject (b6);
			aOutputStream.writeObject (folic);
			aOutputStream.writeObject (retin);
			aOutputStream.writeObject (betacat);
			aOutputStream.writeObject (e);
			aOutputStream.writeObject (d);
		}
		
		private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
			id=(int) aInputStream.readObject();
			name=(String) aInputStream.readObject();
			edible=(int) aInputStream.readObject();
			kcal=(int) aInputStream.readObject();
			kj=(int) aInputStream.readObject();
			water=(int) aInputStream.readObject();
			protot=(int) aInputStream.readObject();
			proani=(int) aInputStream.readObject();
			proveg=(int) aInputStream.readObject();
			glucot=(int) aInputStream.readObject();
			amid=(int) aInputStream.readObject();
			glucos=(int) aInputStream.readObject();
			liptot=(int) aInputStream.readObject();
			sattot=(int) aInputStream.readObject();
			monins=(int) aInputStream.readObject();
			polinstot=(int) aInputStream.readObject();
			acoleic=(int) aInputStream.readObject();
			aclino=(int) aInputStream.readObject();
			aclinon=(int) aInputStream.readObject();
			othpolin=(int) aInputStream.readObject();
			colest=(int) aInputStream.readObject();
			fiber=(int) aInputStream.readObject();
			alcool=(int) aInputStream.readObject();
			ferro=(int) aInputStream.readObject();
			ca=(int) aInputStream.readObject();
			na=(int) aInputStream.readObject();
			k=(int) aInputStream.readObject();
			p=(int) aInputStream.readObject();
			zn=(int) aInputStream.readObject();
			b1=(int) aInputStream.readObject();
			b2=(int) aInputStream.readObject();
			b3=(int) aInputStream.readObject();
			c=(int) aInputStream.readObject();
			b6=(int) aInputStream.readObject();
			folic=(int) aInputStream.readObject();
			retin=(int) aInputStream.readObject();
			betacat=(int) aInputStream.readObject();
			e=(int) aInputStream.readObject();
			d=(int) aInputStream.readObject();
		}
		
	}
