#include <iostream>
#include <vector>

template <typename Implementation>
struct Neurons 
{
    template <typename ConnectTo>
	void connect(ConnectTo& other);
	
	Implementation& impl() { return *static_cast<Implementation*>(this); }
};

struct Neuron: Neurons<Neuron>
{
	std::vector<Neuron*> in;
	std::vector<Neuron*> out;
	unsigned int ID;
	Neuron() 
	{
		static int ID = 1;
		this->ID = ID++;
	}

	Neuron* begin() { return this; }
	Neuron* end() { return this + 1; }
};

struct NeuronLayer: std::vector<Neuron>, Neurons<NeuronLayer>
{
	NeuronLayer(int number_of_neurons) 
	{
		while (number_of_neurons-- > 0)
			emplace_back(Neuron{});
	}
};

template <typename Implementation>
template <typename ConnectTo>
void Neurons<Implementation>::connect(ConnectTo &other) 
{
	for(Neuron& from : *static_cast<Implementation*>(this))
	{
	    for(Neuron& to : other)
	    {
	        from.out.emplace_back(&to);
	        to.in.push_back(&from);
	    }
	}
}

template <typename T>
std::ostream &operator<<(std::ostream &console, Neurons<T> &obj)
{
	T& node = obj.impl();
	for(Neuron& neuron : node)
	{
	    for (Neuron *n : neuron.in)
            console << n->ID << "\t>\t" << neuron.ID << "*" << "\n";
        for (Neuron *n : neuron.out)
            console << neuron.ID << "*\t>\t" << n->ID << "\n";   
	}
	return console;
}

int main() 
{
	Neuron single_neuron_1, single_neuron_2;
	NeuronLayer layer_1{1}, layer_2{2};
	
	single_neuron_1.connect(layer_1);
	single_neuron_1.connect(single_neuron_2);
	layer_1.connect(layer_2);
	layer_2.connect(single_neuron_2);
	
	std::cout << single_neuron_1 << "\n";
	std::cout << single_neuron_2 << "\n";
	std::cout << layer_1 << "\n";
	std::cout << layer_2 << "\n";
}
