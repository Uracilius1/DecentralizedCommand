/*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package entity;
//
//
///**
// *
// * @author Mabiyev
// */
//public class Soldier extends Entity {
//    private String name;
//    private int health;
//    private String status_effects;
//    private double[] skills;
//    private Weapon weapon;
//    private String[] inventory;
//    private boolean team;
//    private Soldier[] contacts;
//    private Soldier target;
//    private int xcord, ycord;
//    private int engagement_status;
//    private String character;
//    private int[] scenesGenerated;
//
//    public Soldier(String id, int health, String status_effects, double[] skills, String[] inventory, boolean team, Soldier[] contacts, int xcord, int ycord, String character) {
//        this.name = id;
//        this.health = health;
//        this.status_effects = status_effects;
//        this.skills = skills;
//        this.weapon = new Weapon("Marusya", this);
//        this.inventory = inventory;
//        this.team = team;
//        this.contacts = contacts;
//        this.xcord = xcord;
//        this.ycord = ycord;
//        this.character=character;
//    }
//
//    public void reload() {
//        throw new UnsupportedOperationException("reloading not supported yet.");
//    }
////skills has 5 places in the array. skills[0] is accuracy, skills[1] is tactics, skills[2] is movement, skills[3] is bravery, skills[4] is loyalty
//    public void marksmanshipRoll(int weapon_accuracy, double sps, int ammo, int damage) {
//        double roll = weapon_accuracy+(skills[0]*0.25);
//      
//        if(target.getStatusEffects().contains("m")){
//            roll-=20;
//        }
//        if(status_effects.contains("o")){
//            roll-=20;
//        }
//        if(target.status_effects.contains("r")){
//            roll+=10;
//        }
//        
//        if(target.status_effects.contains("c")){
//            roll-=10;
//        }
//        if(status_effects.contains("e")){
//            roll+=10;
//        }
//        else if(status_effects.contains("s")){
//            roll-=roll;
//        }
//        if(target.status_effects.contains("u")){
//            roll+=50;
//        }
//        else if(target.status_effects.contains("f")&&status_effects.contains("fm")){
//            roll+=30;
//        }
//        
//        double randNumber = Math.random();
//        double d = randNumber * 100;
//        //DEBUG: FOLLOWING VARIABLE MEANT FOR DEBUG
//        String debug = "was";
//        if(roll<d){
//           debug+=" not";
//        }
//        else{
//            target.getShot(damage);
//            }
//        //DEBUG: FOLLOWING LINES CAN BE DELETED IN FULL RELEASE. USED FOR DEBUG.
//        System.out.printf("Soldier %s %s successful in hitting enemy %s.\n", this.name, debug, target.getName());
//    }
//    
//    //Algorithm of deciding a target dependent solely on the threat the enemy poses. Will think about keeping his status effects (as cover, flanking) in mind later.
//    public void decideTarget(){    
//        for(Soldier contact:contacts){
//            if (target.getWeapon().sps*target.getWeapon().damage<contact.getWeapon().sps*contact.getWeapon().damage){
//                target = contact;
//        }
//    }
//    }
//    public void die(){
//        addStatusEffects("D");
//    }
//    public void getShot(int damage){
//        
//        double randNumber = Math.random();
//        double d = randNumber * 100;
//        //Debug:following variable is used for debug
//        String debug = "shot";
//        if(d<10){
//            damage*=2;
//            debug+=" in the head";
//        }else if(d<50){
//            damage*=.3;
//            debug+="in the leg";
//        }else{
//            debug+="in the torso";
//            }
//        
//        if(damage>=100){
//            this.die();
//                    }
//        //DEBUG: FOLLOWING LINES CAN BE DELETED IN FULL RELEASE. USED FOR DEBUG
//        System.out.printf("Soldier %s was %s .\n", this.name, debug);
//    }
//    public void detectEnemies(){
//    }
//    //Initiative-dependent method. Makes a soldier either decide or stay idle
//    public void tacticRoll(){
//        double roll=skills[1];
//        double randNumber = Math.random();
//        double d = randNumber * 100;
//        
//        if(roll>d&&(this.status_effects.contains("r")||this.status_effects.contains("f")||this.status_effects.contains("s"))){
//            chooseTacticDefensive();
//        }
//        else{
//            chooseTacticOffensive();
//                }
//    }
//    public void chooseTacticDefensive(){
//        //SetOfCommands for the soldier to do anything. REQUIRES MOVE COMMAND, WHICH IS DEPENDENT ON PATHFINDING AND COLLISIONDETECTION. coming soon.
//    }
//    public void chooseTacticOffensive(){
//    }
//    public void move(){
//        //requires development of pathfinding for the game as well as collision detection. COMING SOON!
//    }
//    public void fireAtTarget(){
//        weapon.Shoot();
//    }
//    public String[] generateScene(){
//        String[] response = {"stringscene", "answeryes", "answerno", "conclusiontoansweryes", "conclusiontoanswerno"};
//        double randNumber = Math.random();
//        double d = randNumber * 3;
//        switch(character){
//                case "Rebel":
//                    String concatzero = "You awake from a sudden gunshot. One of your soldiers has been on-patrol. when you get there...";
//                    if(d==0 && scenesGenerated[0]!=1){
//                            concatzero+="This bastard has been cooking a rat!";
//                            response[0] = concatzero;
//                            response[1] = "Reprimand him.";
//                            response[2] = "Help him cook the rat.";
//                            response[3] = "Come on, sir. We haven't been eating for too much and you're reprimanding me for helping us get at least something?   loyalty-10 initiative-5 supply-5";
//                            response[4] = "You silently enjoy your meal. Hopefully your leniency to these soldiers will not serve to them being disobedient on the battlefield.   loyalty+5 initiative +10";
//                            scenesGenerated[0]=1;
//                            return response;
//                        }
//                    else if(d==1 && scenesGenerated[1]!=1){
//                        concatzero+="When you come closer to the patrolling soldier, you can see him breathing heavily. \'I got some bastard who was hanging about the camp! Could you go check?\'. Following where he points, you come across a dead civilian. His head was blown clean off. After some time, when you are able to think properly, you decide what to do with your soldier:";
//      
//                            response[0] = concatzero;
//                            response[1] = "Lie that he shot an animal and advise him to better identify targets next time";
//                            response[2] = "Show him what he has done";
//                            response[3] = "Aw, crap! I'll do better next time! You sincerely hope he won't.   spotting+5 shooting+5 initiative -10";
//                            response[4] = "He utters something unintelligible \'you have to help me sir, please? I did not want for this to happen. How did he even end up here?\' after closer examination, the civilian turns out to have binoculars and a sketch of the batallion's current positions. \'Ha! my shooting is quite good! I knew i didn't have to identify my targets!\'   initiative+10";
//                            scenesGenerated[1]=1;
//                            return response;
//            }
//                    else if(scenesGenerated[2]!=1){
//                        concatzero+="Your soldier has ambushed a german patrol, just scraping by with his life";
//      
//                            response[0] = concatzero;
//                            response[1] = "tell him that he should've been careful";
//                            response[2] = "congratulate him with a good hunt";
//                            response[3] = "'Aye, aye ya wank of course I will now that you're telling me.'   initiative -10 loyalty -15 shooting+5";
//                            response[4] = "Hey, next time youse send me on patrol I'll bring you german heads aye? initiative+20 loyalty-5 shooting+5";
//                            scenesGenerated[2]=1;
//                            return response;
//                    }
//                    else{
//                    return null;
//                    }
//                case "Quiet":
//                    String concatone = "Sitting near the campfire, your comrade asks...";
//                    if(d==0 && scenesGenerated[0]!=1){
//                            concatone+="...for you to tell a story";
//                            response[0] = concatone;
//                            response[1] = "Tell him a story from your childhood";
//                            response[2] = "Lie about your past";
//                            response[3] = "Thank you for telling me that, it is quite funny. You know, I have a story just like this...   loyalty+5 initiative+5";
//                            response[4] = "Sir, I've spotted that you're lying. I cannot trust a leader who does not trust me. I hope you will try to become better.   loyalty-10 initiative+5";
//                            scenesGenerated[0]=1;
//                            return response;
//                        }
//                    else if(d==1 && scenesGenerated[1]!=1){
//                        concatone+="if you wanted to go to the river and get some fresh water, this is quite stale. You got that jug two days ago, and with boiling it couldn't become stale so fast. It takes two hours to get to the river.";
//      
//                            response[0] = concatone;
//                            response[1] = "Obey in hopes that they find you a dilligent leader";
//                            response[2] = "Reject";
//                            response[3] = "You return from your journey. The squad is sleeping. You were left to patrol. +5 loyalty -10 initiative";
//                            response[4] = "I was just testing you anyway. I really like that you are thinking with your head, would you care to help us practice shooting while wme have some free time?   loyalty+5 shooting+10";
//                            scenesGenerated[1]=1;
//                            return response;
//            }
//                    else if(scenesGenerated[2]!=1){
//                        concatone+="What is your purpose in being here?";
//      
//                            response[0] = concatone;
//                            response[1] = "To become stronger";
//                            response[2] = "To defend my motherland and my parents";
//                            response[3] = "He falls silent.   loyalty +5";
//                            response[4] = "He falls silent.   loyalty +5";
//                            scenesGenerated[2]=1;
//                            return response;
//                    }
//                    else{
//                    return null;
//                        }
//                    case "Communist":
//                    String concatwo = "While out patrolling, one of your soldiers says:";
//                    if(d==0 && scenesGenerated[0]!=1){
//                            concatwo+="...'In how many days do you suppose the western capitalist world falls after our victory?'";
//                            response[0] = concatwo;
//                            response[1] = "Mention that you only stopped retreating some weeks ago";
//                            response[2] = "Be very optimistic";
//                            response[3] = "Yes, but you have seen what the Germans had, sir. The fact that we managed to pull through is a testament to our strength. Why don't you a little bit more positive next time?   loyalty-5 initiative+5";
//                            response[4] = "'Ten days? Yes, I think so myself in fact.'   loyalty+5 initiative+5";
//                            scenesGenerated[0]=1;
//                            return response;
//                        }
//                    else if(d==1 && scenesGenerated[1]!=1){
//                        concatwo+="You remember that time near Zhytomir when that Junior Lieutenant was appropriating shit? What did you do to him?";
//      
//                            response[0] = concatwo;
//                            response[1] = "What could I do? I was a squad leader";
//                            response[2] = "I punched his ass and was demoted to private. He then died in the Kiev pocket not soon after.";
//                            response[3] = "That's fair, but doesn't it go against our and tovarish Stalin's dogma? +5 loyalty -10 initiative";
//                            response[4] = "Commendable. A pure communist you are!   loyalty+5 movement+5";
//                            scenesGenerated[1]=1;
//                            return response;
//            }
//                    else if(scenesGenerated[2]!=1){
//                        concatwo+="If I were to blow up tomorrow, what would you write to my ma?";
//      
//                            response[0] = concatwo;
//                            response[1] = "laugh it off";
//                            response[2] = "tell the truth";
//                            response[3] = "Very funny. Thank you for hearing me out, sir.   loyalty -5 morale -5";
//                            response[4] = "'Well, if you are blown to bits, I will write to your mother that you are the hero of the Soviet Union and they are planning to put you in a special type of grave' -'You mean the unknown soldier's grave? Oh god.   morale -10";
//                            scenesGenerated[2]=1;
//                            return response;
//                    }
//                    else{
//                    return null;
//                        }
//
//        
//                
//    }
//        return null;
//
//
//    }
//
//
//
//
//
//
//
//
//    public String getName() {
//        return name;
//    }
//
//    public int getHealth() {
//        return health;
//    }
//
//    public String getStatusEffects() {
//        return status_effects;
//    }
//
//    public double[] getSkills() {
//        return skills;
//    }
//
//    public Weapon getWeapon() {
//        return weapon;
//    }
//
//    public String[] getInventory() {
//        return inventory;
//    }
//
//    public boolean isTeam() {
//        return team;
//    }
//
//    public Soldier[] getContacts() {
//        return contacts;
//    }
//
//    public Soldier getTarget() {
//        return target;
//    }
//
//    public int getXcord() {
//        return xcord;
//    }
//
//    public int getYcord() {
//        return ycord;
//    }
//
//    public int getEngagementStatus() {
//        return engagement_status;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setHealth(int health) {
//        this.health = health;
//    }
//
//    public void setStatusEffects(String status_effects) {
//        this.status_effects = status_effects;
//    }
//    public void addStatusEffects(String concat){
//        this.status_effects+=concat;
//        }
//    public void removeStatusEffects(String c){
//        this.status_effects = this.status_effects.replace(c,"");
//    }
//    public void setSkills(double[] skills) {
//        this.skills = skills;
//    }
//
//    public void setWeapon(Weapon weapon) {
//        this.weapon = weapon;
//    }
//
//    public void setInventory(String[] inventory) {
//        this.inventory = inventory;
//    }
//
//    public void setTeam(boolean team) {
//        this.team = team;
//    }
//
//    public void setContacts(Soldier[] contacts) {
//        this.contacts = contacts;
//    }
//
//    public void setTarget(Soldier target) {
//        this.target = target;
//    }
//
//    public void setXcord(int xcord) {
//        this.xcord = xcord;
//    }
//
//    public void setYcord(int ycord) {
//        this.ycord = ycord;
//    }
//
//    public void setEngagement_status(int engagement_status) {
//        this.engagement_status = engagement_status;
//    }
//    
//    
//
//
//}
//
