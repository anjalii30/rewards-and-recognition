package com.rar.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="criteria")
public class Criterias implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "criteria_Id")
    private long criteriaId;

    @Column(name="criteria_desc",nullable = false)
    private String criteria_desc;



    @OneToMany(
            mappedBy = "criteria")
    private List<RewardsCriteria> rewards = new ArrayList<>();

/*    public List<RewardsCriteria> getRewards() {
        return rewards;
    }

    public void setRewards(List<RewardsCriteria> rewards) {
        this.rewards = rewards;
    }*/

//
//    //rewards
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.MERGE,
//            },
//            mappedBy = "criterias")
//    private Set<Rewards> rewards1 = new HashSet<>();
//
//

//
//    //nominationscriterias
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE,
//            },
//            mappedBy = "criterias2")
//    private Set<Nominations> nominations = new HashSet<>();
//
//

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            })
//    @JoinTable(
//            name = "nominations_criterias",
//            joinColumns = {@JoinColumn(name = "Criterias_Id")},
//            inverseJoinColumns = {@JoinColumn(name = "User_Id"),@JoinColumn(name = "Reward_Id")}
//    )
//    private Set<Nominations> nominations = new HashSet<>();


    public Criterias() {
    }

    public Criterias(String criteria_desc) {
        this.criteria_desc = criteria_desc;
    }

    public long getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(long criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getCriteria_desc() {
        return criteria_desc;
    }

    public void setCriteria_desc(String criteria_desc) {
        this.criteria_desc = criteria_desc;
    }
//
//    public Set<Rewards> getRewards1() {
//        return rewards1;
//    }
//
//    public void setRewards1(Set<Rewards> rewards1) {
//        this.rewards1 = rewards1;
//    }

//    public Set<Nominations> getNominations() {
//        return nominations;
//    }
//
//    public void setNominations(Set<Nominations> nominations) {
//        this.nominations = nominations;
//    }

    @Override
    public String toString() {
        return "Criteria{" +
                "criteriaId=" + criteriaId +
                ", criteria_desc='" + criteria_desc + '\'' +
                '}';
    }




}
