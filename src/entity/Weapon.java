/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Mabiyev
 */
class Weapon {
    int damage;
    String name;
    double sps;
    int ammo;
    int clipsize;
    Soldier owner;
    int accuracy;
    
    //main constructor
    public Weapon(double sps, String name, int clipsize, Soldier owner, int damage) {
        this.sps = sps;
        this.ammo = clipsize;
        this.clipsize = clipsize;
        this.owner = owner;
        this.damage = damage;
    }
    
    //Default constructor. bolt-action karabiner 98
    public Weapon(Soldier owner){
        this(.5, "Karabiner98",  5, owner, 50);
    }
    //Constructor for a russian-made bolt-action. Damage is 10 points bigger due to russian bias.
    public Weapon(String name, Soldier owner){
        this(0.5, name,  5, owner, 60);
    }
    //Constructor for a german mp40 with custom firing speed
    public Weapon(double sps, Soldier owner){
        this(sps, "MP40 custom",  30, owner, 35);
    }
    //Constructor for a custom PPSH.
    public Weapon(double sps, int damage, Soldier owner){
        this(sps, "PPSH-41 custom", 60, owner, damage);
        }
    
    
    //Shoot function is performed by the owner.
    public void Shoot(){
        if(ammo<=0){
            owner.reload();
            this.ammo=clipsize;
        }
        else{
            ammo--;
            owner.marksmanshipRoll(accuracy, sps, ammo, damage);
        }
    }
    
       
}
