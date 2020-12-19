package agh.cs.lab1.Code;

public interface IEnergyChangeObserver {
    void energyChanged(int oldEnergy, int newEnergy, Animal animal);
}
