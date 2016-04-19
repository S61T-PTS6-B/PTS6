/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.MileageRate;

/**
 *
 * @author koenv
 */
public interface IMileageRateService {
    public void createMileageRate(MileageRate mr);
    
    public List<MileageRate> getAllRates();
    
    public MileageRate getRateById(String id);
    
    public List<MileageRate> getRateByPrizeCategory(double prizecategory);
    
    public List<MileageRate> getRateByInterval(double interval);
    
    public List<MileageRate> getRateByRegio(String regio);
    
    public void changeMileageRate(MileageRate mr, double mar);
    
    public void changeRegio(MileageRate mr, String regio);
    
    public void changePrizeCategory(MileageRate mr, double prizecategory);
    
    public void changeInterval(MileageRate mr, double interval);
    
}
