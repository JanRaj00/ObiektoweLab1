package agh.cs.lab1;

public interface IEnergyChangeObserver {
    void energyChanged(int oldEnergy, int newEnergy, Animal animal);
}
