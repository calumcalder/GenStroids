package game;

import java.awt.Graphics2D;

public abstract class Entity {
	
	/*
	 * definitions lel
	 */
	
	private Vec pos, vel;
	private double ang, angvel;

	private boolean active, tobedeleted = false;
	private int uID, entID;

	public Entity() {
		this.pos = new Vec();
		this.vel = new Vec();

		this.uID = Entities.getUID();
	}
	
	public void setPos(double x, double y) {
		this.pos.x = x;
		this.pos.y = y;
	}
	public void setPos(Vec pos) {
		this.pos = pos;
	}
	public Vec getPos() {
		return pos;
	}

	public int getEntID() {
		return entID;
	}
	public void setEntID(int entID) {
		this.entID = entID;
	}

	public void setVel(double xvel, double yvel) {
		this.vel.x = xvel;
		this.vel.y = yvel;
	}
	public void setVel(Vec vel) {
		this.vel = vel;
	}
	public Vec getVel() {
		return this.vel;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isActive() {
		return active;
	}

	public void move() {
		this.bounce();
		this.pos.x += this.vel.x;
		this.pos.y += this.vel.y;
		this.setAng(this.ang + this.angvel);
	}

	public void bounce() {
		if (this.pos.x <= 0) {
			this.vel.x = 1;
		}
		if (this.pos.x >= Main.BOARD_SIZE_X) {
			this.vel.x = -1;
		}
		if (this.pos.y <= 0) {
			this.vel.y = 1;
		}
		if (this.pos.y >= Main.BOARD_SIZE_Y) {
			this.vel.y = -1;
		}
	}
	
	public double getHeading(Entity ent) {
		double dy = ent.getPos().y - this.getPos().y;
		double dx = ent.getPos().x - this.getPos().x;
		return Math.atan2(dy, dx);
	}
	
	public double getDist(Entity ent) {
		double dy = ent.getPos().y - this.getPos().y;
		double dx = ent.getPos().x - this.getPos().x;
		return Math.sqrt(dy*dy + dx*dx);
	}
	
	public double getAng() {
		return ang;
	}
	public void setAng(double theta) {
		while(theta> Math.PI)
			theta -= Math.PI*2;
		while(theta < -Math.PI)
			theta += Math.PI*2;
		
		this.ang = theta;
	}

	public double getAngVel() {
		return angvel;
	}
	public void setAngVel(double angvel) {
		this.angvel = angvel;
	}
	
	public int getUID() {
		return this.uID;
	}
	
	public void delete() {
		this.tobedeleted = true;
	}
	public boolean isToBeDeleted() {
		return tobedeleted;
	}

	public abstract void draw(Graphics2D g);
}
