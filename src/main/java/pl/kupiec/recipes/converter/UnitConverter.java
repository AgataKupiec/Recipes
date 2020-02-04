package pl.kupiec.recipes.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.kupiec.recipes.entity.Unit;
import pl.kupiec.recipes.repository.UnitRepository;


public class UnitConverter implements Converter<String, Unit> {
    @Autowired
    private UnitRepository unitRepository;
    
    @Override
    public Unit convert(String s) {
        if (s.trim().equals("0") || s.trim().equals("")) {
            return null;
        }
        Unit unit = unitRepository.findById(Long.parseLong(s)).get();
        return unit;
    }
}