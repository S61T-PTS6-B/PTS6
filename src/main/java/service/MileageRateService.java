/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.MileageRateDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.MileageRate;

/**
 *
 * @author koenv
 */
@Stateless
public class MileageRateService {
    @EJB
    MileageRateDAO mrd;
    
    public void createMileageRate(MileageRate mr)
    {
        mrd.createMileageRate(mr);
    }
    
    public List<MileageRate> getAllRates() 
    {
        return mrd.getAllRates();
    }
    
    public List<MileageRate> getRateByPrizeCategory(double prizecategory)
    {
        return mrd.getRatePerPrizeCategory(prizecategory);
    }
    
    public List<MileageRate> getRateByInterval(double interval) 
    {
        return mrd.getRatePerInterval(interval);
    }

    public List<MileageRate> getRateByRegio(String regio)
    {
        return mrd.getRatePerArea(regio);
    }
    
    public void changeMileageRate(MileageRate mr, double mar)
    {
        mrd.changeRate(mr, mar);
    }
    
    public void changeRegio(MileageRate mr, String regio)
    {
        mrd.changeRegio(mr, regio);
    }
    
    public void changePrizeCategory(MileageRate mr, double prizecategory)
    {
        mrd.changePrizeCategory(mr, prizecategory);
    }
    
    public void changeInterval(MileageRate mr, double interval)
    {
        mrd.changeInterval(mr, interval);
    }
}
