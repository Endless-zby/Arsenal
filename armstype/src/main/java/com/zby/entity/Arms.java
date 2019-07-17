package com.zby.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tb_arms")
public class Arms implements Serializable {
    @Id
    private int id;          //分类id
    private int Cold_Weapon;  //轻兵器
    private int Firearms;    //枪械
    private int Artillery;   //火炮
    private int AFVs;        //装甲战斗车辆
    private int Warship;     //舰艇
    private int Spacecraft;  //军用航天器
    private int Aircraft;     //军用航空器
    private int Chemical_Weapon;     //化学武器
    private int Riot_Gun;    //防暴武器
    private int Biological_Weapon;   //生物武器
    private int Ammo;    //弹药
    private int Bomb;    //核武器
    private int PGM;     //精确制导武器
    private int Invisible_Weapon;    //隐形武器
    private int New_Weapon;  //新概念武器

    public Arms(int id, int cold_Weapon, int firearms, int artillery, int AFVs, int warship, int spacecraft, int aircraft, int chemical_Weapon, int riot_Gun, int biological_Weapon, int ammo, int bomb, int PGM, int invisible_Weapon, int new_Weapon) {
        this.id = id;
        Cold_Weapon = cold_Weapon;
        Firearms = firearms;
        Artillery = artillery;
        this.AFVs = AFVs;
        Warship = warship;
        Spacecraft = spacecraft;
        Aircraft = aircraft;
        Chemical_Weapon = chemical_Weapon;
        Riot_Gun = riot_Gun;
        Biological_Weapon = biological_Weapon;
        Ammo = ammo;
        Bomb = bomb;
        this.PGM = PGM;
        Invisible_Weapon = invisible_Weapon;
        New_Weapon = new_Weapon;
    }

    public Arms() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCold_Weapon() {
        return Cold_Weapon;
    }

    public void setCold_Weapon(int cold_Weapon) {
        Cold_Weapon = cold_Weapon;
    }

    public int getFirearms() {
        return Firearms;
    }

    public void setFirearms(int firearms) {
        Firearms = firearms;
    }

    public int getArtillery() {
        return Artillery;
    }

    public void setArtillery(int artillery) {
        Artillery = artillery;
    }

    public int getAFVs() {
        return AFVs;
    }

    public void setAFVs(int AFVs) {
        this.AFVs = AFVs;
    }

    public int getWarship() {
        return Warship;
    }

    public void setWarship(int warship) {
        Warship = warship;
    }

    public int getSpacecraft() {
        return Spacecraft;
    }

    public void setSpacecraft(int spacecraft) {
        Spacecraft = spacecraft;
    }

    public int getAircraft() {
        return Aircraft;
    }

    public void setAircraft(int aircraft) {
        Aircraft = aircraft;
    }

    public int getChemical_Weapon() {
        return Chemical_Weapon;
    }

    public void setChemical_Weapon(int chemical_Weapon) {
        Chemical_Weapon = chemical_Weapon;
    }

    public int getRiot_Gun() {
        return Riot_Gun;
    }

    public void setRiot_Gun(int riot_Gun) {
        Riot_Gun = riot_Gun;
    }

    public int getBiological_Weapon() {
        return Biological_Weapon;
    }

    public void setBiological_Weapon(int biological_Weapon) {
        Biological_Weapon = biological_Weapon;
    }

    public int getAmmo() {
        return Ammo;
    }

    public void setAmmo(int ammo) {
        Ammo = ammo;
    }

    public int getBomb() {
        return Bomb;
    }

    public void setBomb(int bomb) {
        Bomb = bomb;
    }

    public int getPGM() {
        return PGM;
    }

    public void setPGM(int PGM) {
        this.PGM = PGM;
    }

    public int getInvisible_Weapon() {
        return Invisible_Weapon;
    }

    public void setInvisible_Weapon(int invisible_Weapon) {
        Invisible_Weapon = invisible_Weapon;
    }

    public int getNew_Weapon() {
        return New_Weapon;
    }

    public void setNew_Weapon(int new_Weapon) {
        New_Weapon = new_Weapon;
    }
}
