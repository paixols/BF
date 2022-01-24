package com.bf.bfmap.map

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bf.bfmap.R
import com.bf.bfmap.app.BFApplication
import com.bf.bfmap.databinding.FragmentMapBinding
import com.bf.bfmap.databinding.MapBottomSheetBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MapFragment :
    Fragment(),
    OnMapReadyCallback,
    MapActions,
    GoogleMap.OnMarkerClickListener {

    /*UI*/
    private lateinit var binding: FragmentMapBinding
    private lateinit var bottomSheetBinding: MapBottomSheetBinding
    private lateinit var bottomSheet: BottomSheetDialog

    /*Map*/
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var mapMarker: MarkerOptions
    private lateinit var googleMap: GoogleMap

    /*VM*/
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel by viewModels<MapViewModel> { viewModelFactory }

    /*LifeCycle*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        (activity?.application as BFApplication)
            .appComponent
            .mapComponent()
            .create()
            .inject(fragment = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_map, container, false
        )
        setupBottomSheet(inflater)
        setupFuelRequests()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_satellite -> {
                viewModel.saveMapType(GoogleMap.MAP_TYPE_SATELLITE)
                true
            }
            R.id.action_terrain -> {
                viewModel.saveMapType(GoogleMap.MAP_TYPE_TERRAIN)
                true
            }
            R.id.action_atlas -> {
                viewModel.saveMapType(GoogleMap.MAP_TYPE_NORMAL)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*Map*/
    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
        setMapActions()
        setCameraPosition()
        setMapType()
    }

    override fun setMapActions() {
        googleMap.setOnMapLongClickListener { latLng ->
            googleMap.clear()
            mapMarker = getMarkerOptions(latLng)
            googleMap.addMarker(mapMarker)
        }
        googleMap.setOnMarkerClickListener(this)
    }

    override fun getMarkerOptions(latLng: LatLng): MarkerOptions =
        MarkerOptions()
            .position(latLng)
            .title(getString(R.string.marker_car_parked))
            .snippet(getString(R.string.marker_snippet))
            .draggable(false)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))

    override fun onMarkerClick(p0: Marker): Boolean {
        p0.showInfoWindow()
        return false
    }

    override fun setCameraPosition() {
        //San Mateo, CA - parking lot
        viewModel.mapLocation.value?.let { cameraPosition ->
            googleMap.moveCamera(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition.fromLatLngZoom(
                        cameraPosition.first,
                        cameraPosition.second
                    )
                )
            )
        }
    }

    override fun setMapType() {
        viewModel.mapType.observe(this.viewLifecycleOwner, {
            googleMap.mapType = it
        })
    }

    /*Fuel Request & Navigation*/
    private fun setupBottomSheet(inflater: LayoutInflater) {
        bottomSheet = BottomSheetDialog(requireContext())
        bottomSheetBinding = MapBottomSheetBinding.inflate(inflater, null, false)
        bottomSheet.setContentView(bottomSheetBinding.root)
        bottomSheetBinding.btnNext.setOnClickListener {
            saveMapLocation()
            bottomSheet.hide()
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun saveMapLocation() {
        viewModel.saveMapPosition(mapMarker.position, googleMap.cameraPosition.zoom)
        viewModel.saveBoostLocation()
    }

    private fun setupFuelRequests() {
        binding.btnRequestFuel.setOnClickListener {
            if (!this::mapMarker.isInitialized) {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.no_marker),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                showBottomModalSheet()
            }
        }
    }

    private fun showBottomModalSheet() {
        binding.btnRequestFuel.visibility = View.GONE
        bottomSheet.show()
        bottomSheet.setOnDismissListener {
            binding.btnRequestFuel.visibility = View.VISIBLE
        }
    }

}
